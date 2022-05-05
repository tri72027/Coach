package com.coach.application.coachservice.ticket.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.coach.application.coachservice.info.service.IRepoInfo;
import com.coach.application.coachservice.statusticket.service.IRepoStatusTicket;
import com.coach.application.coachservice.statustrip.service.IRepoStatusTrip;
import com.coach.application.coachservice.ticket.dto.TicketRequest;
import com.coach.application.coachservice.ticket.dto.TicketResponse;
import com.coach.application.coachservice.trip.service.IRepoTrip;
import com.coach.application.coachservice.user.service.IRepoUser;
import com.coach.application.common.base.BaseResponse;
import com.coach.application.entity.InfoEntity;
import com.coach.application.entity.StatusTicketEntity;
import com.coach.application.entity.TicketEntity;
import com.coach.application.entity.TripEntity;
import com.coach.application.entity.UserEntity;

@Service
public class TicketImplement implements TicketService {
	@Autowired
	public IRepoTicket repo;
	@Autowired
	public IRepoTrip repoTrip;
	@Autowired
	public IRepoStatusTicket repoStatus;
	@Autowired
	public IRepoInfo repoInfo;
	@Autowired
	public IRepoUser repoUser;
	@Autowired
	public TicketSearchProcess processSearch;
	@Autowired
	public TicketDetailProcess processDetail;

	@Override
	public void save(TicketRequest req) {
		TicketEntity list = new TicketEntity();
		list.setDate(req.getDateBuyTicket());
		list.setTicketCode(req.getTicketCode());
		Optional<TripEntity> tripID = repoTrip.findById(req.getTripID());
		list.setTrip(tripID.get());
		list.setSeats(req.getSeat());
		list.setAmountSeat(req.getAmountSeats());
		list.setPrice(req.getPrice());
		if (!(req.getInfo() == null)) {
			InfoEntity infoEntity = new InfoEntity();
			infoEntity.setFullName(req.getInfo().getFullName());
			infoEntity.setPhone(req.getInfo().getPhone());
			infoEntity.setAddress(req.getInfo().getAddress());
			infoEntity.setIdentificationNumber(req.getInfo().getIdentificationNumber());
			infoEntity.setEmail(req.getInfo().getEmail());

			if (!(req.getUserID() == null)) {
				Optional<UserEntity> userID = repoUser.findById(req.getUserID());
				infoEntity.setUserId(userID.get());
			}
			InfoEntity info = repoInfo.save(infoEntity);

			if (!(info == null)) {
				list.setInfoID(info);
			}
		}
		Optional<StatusTicketEntity> statusID = repoStatus.findById(req.getStatusID());
		list.setStatus(statusID.get());

		repo.save(list);
	}

	@Override
	public BaseResponse getAll() {
		// TODO Auto-generated method stub
		BaseResponse cmRep = new BaseResponse();
		List<TicketEntity> list = repo.findAll();
		if (list.isEmpty()) {
			cmRep.setError("khong ton tai");
			return cmRep;
		}
		List<TicketResponse> rep = list.stream().map(TicketResponse::new).collect(Collectors.toList());
		cmRep.setContent(rep);
		return cmRep;
	}

	@Override
	public BaseResponse getByID(TicketRequest req) {
		BaseResponse cmRep = new BaseResponse();
		Optional<TicketEntity> rep = repo.findById(req.getTicketID());
		if (rep.isEmpty()) {
			cmRep.setError("khong ton tai");
			return cmRep;
		}
		TicketResponse resp = new TicketResponse();
		resp.setTicketID(rep.get().getTicketID());
		resp.setTicketCode(rep.get().getTicketCode());
		resp.setDate(rep.get().getDate());
		resp.setSeat(rep.get().getSeats());
		resp.setTripID(rep.get().getTrip().getTripID());
		resp.setPrice(rep.get().getPrice());
		resp.setStatusID(rep.get().getStatus().getStatusID());
		if (!(rep.get().getInfoID() == null)) {
			resp.setInfoID(rep.get().getInfoID().getInfoID());
		} else {
			resp.setInfoID((long) 0);
		}

		cmRep.setContent(resp);
		return cmRep;
	}

	@Override
	public BaseResponse getByTripID(TicketRequest req) {
		// TODO Auto-generated method stub
		BaseResponse cmRep = new BaseResponse();
		Optional<TripEntity> Trip = repoTrip.findById(req.getTripID());
		
//		Optional<StatusTicketEntity> status = repoStatus.findById(req.getStatusID());
//		List<TicketEntity> resp = repo.findByTripAndStatus(Trip.get(), status.get());
		List<TicketEntity> resp = repo.findByTrip(Trip.get());
		if (resp.isEmpty()) {
			cmRep.setError("khong ton tai");
			return cmRep;
		}
;
		List<TicketResponse> rep = resp.stream().map(TicketResponse::new).collect(Collectors.toList());
		cmRep.setContent(rep);
		return cmRep;

	}

	@Override
	public BaseResponse getManagerTicket(TicketRequest req) {
		BaseResponse cmRep = new BaseResponse();
		String Query = processSearch.createQueryString(req);
		String count = processSearch.createQueryCountString();
		cmRep = processSearch.setDataQuery(Query,count, req);
		
		return cmRep;
	}

	@Override
	public void delete(TicketRequest req) {
		Optional<TicketEntity> rep = repo.findById(req.getTicketID());
		Optional<StatusTicketEntity> statusID = repoStatus.findById((long) 4);
		rep.get().setStatus(statusID.get());
		repo.save(rep.get());
	}

	@Override

	public void deleteCheck(List<TicketRequest> req) {
		for (TicketRequest ticketRequest : req) {
			Optional<TicketEntity> rep = repo.findById(ticketRequest.getTicketID());
			Optional<StatusTicketEntity> statusID = repoStatus.findById((long) 4);
			ticketRequest.setStatusID((long) 4);
			repo.save(rep.get());
		}
	}

	@Override
	public void update(TicketRequest req) {
		
		Optional<TicketEntity> rep = repo.findById(req.getTicketID());
		if (req.getTicketCode() != null && req.getTicketCode() != "") {
			rep.get().setTicketCode(req.getTicketCode());
		}
		if (!(req.getAmountSeats()==null)) {
			rep.get().setAmountSeat(req.getAmountSeats());
		}
		if (!(req.getPrice()==null)) {
			rep.get().setPrice(req.getPrice());
		}
		if (req.getSeat() != null && req.getSeat() != "") {
			rep.get().setSeats(req.getSeat());
		}
		if (req.getTripID() != null) {
			Optional<TripEntity> Trip = repoTrip.findById(req.getTripID());
			rep.get().setTrip(Trip.get());
		}
		if (!(req.getStatusID()==null)) {
			Optional<StatusTicketEntity> status = repoStatus.findById(req.getStatusID());
			rep.get().setStatus(status.get());
		}
		repo.save(rep.get());
		
		
	}

	@Override
	public BaseResponse getDetailTicket(TicketRequest req) {
		BaseResponse cmRep = new BaseResponse();
		String Query = processDetail.createQueryString(req);
		cmRep = processDetail.setDataQuery(Query, req);
		return cmRep;
	}

}
