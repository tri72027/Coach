package com.coach.application.coachservice.user.service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.coach.application.coachservice.role.service.IRepoRole;
import com.coach.application.coachservice.user.dto.JwtResponse;
import com.coach.application.coachservice.user.dto.MessageResponse;
import com.coach.application.coachservice.user.dto.UserRequest;
import com.coach.application.coachservice.user.dto.UserResponse;
import com.coach.application.common.base.BaseResponse;
import com.coach.application.entity.RoleEntity;
import com.coach.application.entity.UserAndRolesEntity;
import com.coach.application.entity.UserEntity;
import com.coach.application.security.config.JwtUtils;
import com.coach.application.security.service.UserDetailsImpl;
import com.coach.application.common.security.ERole;
import com.coach.application.coachservice.userandrole.service.IRepoUserAndRole;

@Service
public class UserImplement implements UserService {
	@Autowired
	public IRepoUser repo;
	@Autowired
	public IRepoRole repoRole;
	@Autowired
	AuthenticationManager authenticationManager;
	@Autowired
	PasswordEncoder passwordEncoder;
	@Autowired
	IRepoUserAndRole repoUserAndRole;
	@Autowired
	JwtUtils jwtUtils;

	@Override
	public BaseResponse getAll() {
		BaseResponse cmRep = new BaseResponse();
		List<UserEntity> list = repo.findAll();
		if (list.isEmpty()) {
			cmRep.setError("khong ton tai");
			return cmRep;
		}
		List<UserResponse> rep = list.stream().map(UserResponse::new).collect(Collectors.toList());
		cmRep.setContent(rep);
		return cmRep;
	}

	@Override
	public BaseResponse getByID(UserRequest req) {
		BaseResponse cmRep = new BaseResponse();
		Optional<UserEntity> rep = repo.findById(req.getUserID());
		if (rep.isEmpty()) {
			cmRep.setError("khong ton tai");
			return cmRep;
		}
		UserResponse resp = new UserResponse();
		resp.setUserID(rep.get().getUserID());
		resp.setUserName(rep.get().getUserName());
		resp.setPassword(rep.get().getPassword());
		resp.setCreateTime(rep.get().getCreateTime());
		resp.setModifyTime(rep.get().getModifyTime());
//		Optional<RoleEntity> roleID = repoRole.findById(rep.get().getRoleID().getRoleID());
//		resp.setRoleID(roleID.get().getRoleID());
		cmRep.setContent(resp);
		return cmRep;
	}

	@Override
	public JwtResponse signin(UserRequest req) {
		Authentication authencitation = authenticationManager
				.authenticate(new UsernamePasswordAuthenticationToken(req.getUserName(), req.getPassword()));
		SecurityContextHolder.getContext().setAuthentication(authencitation);
		String jwt = jwtUtils.generateJwtToken(authencitation);
		UserDetailsImpl userDetail = (UserDetailsImpl) authencitation.getPrincipal();
		List<String> roles = userDetail.getAuthorities().stream().map(item -> item.getAuthority())
				.collect(Collectors.toList());

		return new JwtResponse( userDetail.getUserID(), userDetail.getUsername(),jwt);
	}

	@Override
	public MessageResponse signup(UserRequest req) {
		MessageResponse message = new MessageResponse("User registered successfully!");
		if (repo.existsByUserName(req.getUserName())) {
			message.setMessage("Error: Username is already taken!");
		}

		UserEntity user = new UserEntity(req.getUserName(), passwordEncoder.encode(req.getPassword()),
				req.getCreateTime());

		String strRoles = req.getRole();
		RoleEntity roleID;
		if (strRoles == null) {
			RoleEntity userRole = repoRole.findByName(ERole.ROLE_USER )
					.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
			roleID = userRole;
		} else {

			switch (strRoles) {
			case "admin":
				RoleEntity adminRole = repoRole.findByName(ERole.ROLE_ADMIN)
						.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
				roleID = adminRole;

				break;
			case "mod":
				RoleEntity modRole = repoRole.findByName(ERole.ROLE_MODERATOR)
						.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
				roleID = modRole;

				break;
			default:
				RoleEntity userRole = repoRole.findByName(ERole.ROLE_USER)
						.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
				roleID = userRole;
			}

		}
		
		//user.setUserandroles();
		 UserEntity users = repo.save(user);
		 if(users !=null)
		 {
			 UserAndRolesEntity userAndRole = new UserAndRolesEntity();
			 userAndRole.setRoles(roleID);
			 userAndRole.setUsers(users);
			 repoUserAndRole.save(userAndRole);
		 }
		return message;
	}

}
