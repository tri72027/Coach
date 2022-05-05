package com.coach.application.coachservice.user.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.coach.application.coachservice.user.dto.JwtResponse;
import com.coach.application.coachservice.user.dto.MessageResponse;
import com.coach.application.coachservice.user.dto.UserRequest;
import com.coach.application.coachservice.user.service.UserService;
import com.coach.application.common.base.BaseResponse;

@RestController
@CrossOrigin()
@RequestMapping("/user")
public class UserController {

	@Autowired
	UserService service;

	@GetMapping("/getall")
	@ResponseBody
	public ResponseEntity<?> getAll() {
		BaseResponse rep = service.getAll();
		return new ResponseEntity<>(rep, HttpStatus.OK);

	}
	
	@PostMapping("/getbyid")
	@ResponseBody
	public ResponseEntity<?> getByID(@RequestBody UserRequest req)
	{
		BaseResponse  rep = service.getByID(req);
		return new ResponseEntity<>(rep,HttpStatus.OK);
	}
	
	@PostMapping("/signin")
	@ResponseBody
	public ResponseEntity<?> signin(@RequestBody UserRequest req)
	{
		JwtResponse rep = service.signin(req);
		return new ResponseEntity<>(rep,HttpStatus.OK);
	}
	
	@PostMapping("/signup")
	@ResponseBody
	public ResponseEntity<?> signup(@RequestBody UserRequest req)
	{
		MessageResponse rep = service.signup(req);
		return new ResponseEntity<>(rep,HttpStatus.OK);
	}
}
