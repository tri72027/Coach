package com.coach.application.coachservice.trip.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.coach.application.coachservice.car.service.IRepoCar;
import com.coach.application.coachservice.route.service.IRepoRoute;
import com.coach.application.coachservice.statustrip.service.IRepoStatusTrip;
import com.coach.application.coachservice.trip.dto.TripRequest;
import com.coach.application.coachservice.trip.dto.TripResponse;
import com.coach.application.common.base.BaseResponse;
import com.coach.application.entity.CarEntity;
import com.coach.application.entity.RouteEntity;
import com.coach.application.entity.StatusTripEntity;
import com.coach.application.entity.TripEntity;

@Service
public class TripImplement implements TripService {
	@Autowired
	IRepoTrip repo;
	@Autowired
	public IRepoCar repoCar;
	@Autowired
	public IRepoRoute repoRoute;
	@Autowired
	public IRepoStatusTrip repoStatusTrip;
	@Autowired
	public TripProcess process;
	@Autowired
	public TripDeleteProcess processDelete;
	
	@Override
	public void save(TripRequest req) {

		TripEntity list = new TripEntity();
		list.setDate(req.getDate());
		Optional<CarEntity> carID = repoCar.findById(req.getCarID());
		Optional<RouteEntity> routeID = repoRoute.findById(req.getRouteID());
		Optional<StatusTripEntity> statusID = repoStatusTrip.findById(req.getStatusID());
		list.setCar(carID.get());
		list.setRoute(routeID.get());
		list.setStatus(statusID.get());
		repo.save(list);

	}
	@Override
	public BaseResponse getAll() {
		BaseResponse cmRep = new BaseResponse();
		List<TripEntity> list = repo.findAll();
		if(list.isEmpty())
		{
			cmRep.setError("khong ton tai");
			return cmRep;
		}
		List<TripResponse> rep = list.stream().map(TripResponse::new).collect(Collectors.toList());
		cmRep.setContent(rep);
		return cmRep;
	}

	@Override
	public BaseResponse getByID(TripRequest req) {
		BaseResponse cmRep = new BaseResponse();
		Optional<TripEntity> rep = repo.findById(req.getTripID());
		if(rep.isEmpty())
		{
			cmRep.setError("khong ton tai");
			return cmRep;
		}
		TripResponse resp = new TripResponse();
		resp.setCarID(rep.get().getCar().getCarID());
		resp.setDate(rep.get().getDate());
		resp.setRouteID(rep.get().getRoute().getRouteID());
		resp.setStatusID(rep.get().getStatus().getStatusID());
		cmRep.setContent(resp);
		return cmRep;
	}
	@Override
	
	public BaseResponse fullTrip(TripRequest req) {
		// TODO Auto-generated method stub
		BaseResponse cmRep = new BaseResponse();
		 String queryString = process.createQueryString(req);
		 String count = process.createQueryCountString();
		 cmRep=process.setDataQuery(queryString,count, req);  
		 
		
		return cmRep;
	}
	@Override
	public BaseResponse getByIDAndDate(TripRequest req) {
		BaseResponse cmRep = new BaseResponse();
		List<TripEntity> rep = repo.findByRouteAndDate(req.getRouteID(), req.getDate());
		if(rep.isEmpty())
		{
			cmRep.setError("khong ton tai");
			return cmRep;
		}
		List<TripResponse> resp = rep.stream().map(TripResponse::new).collect(Collectors.toList());
		cmRep.setContent(resp);
		return cmRep;
	}
	@Override
	public BaseResponse getByRouteID(TripRequest req) {
		BaseResponse cmRep = new BaseResponse();
		Optional<RouteEntity> route = repoRoute.findById(req.getRouteID());
		List<TripEntity> rep = repo.findByRoute(route.get());
		if(rep.isEmpty())
		{
			cmRep.setError("khong ton tai");
			return cmRep;
		}
		List<TripResponse> resp = rep.stream().map(TripResponse::new).collect(Collectors.toList());
		cmRep.setContent(resp);
		return cmRep;
	}
	@Override
	public void delete(TripRequest req) {
		Optional<TripEntity> rep = repo.findById(req.getTripID());
		
		Optional<StatusTripEntity> statusID = repoStatusTrip.findById((long) 3);
		String query = processDelete.createTicketQueryString();
		processDelete.setDataTicketQuery(query, rep.get().getTripID());
		rep.get().setStatus(statusID.get());
		repo.save(rep.get());
		
	}
	@Override
	public void deleteCheck(List<TripRequest> req) {
		for (TripRequest ticketRequest : req) {
			Optional<TripEntity> rep = repo.findById(ticketRequest.getTripID());
			Optional<StatusTripEntity> statusID = repoStatusTrip.findById((long) 3);
			String query = processDelete.createTicketQueryString();
			processDelete.setDataTicketQuery(query, rep.get().getTripID());
			rep.get().setStatus(statusID.get());
			ticketRequest.setStatusID(statusID.get().getStatusID());
			repo.save(rep.get());
		}
		
	}
	@Override
	public void update(TripRequest req) {
		Optional<TripEntity> rep = repo.findById(req.getTripID());
		if(req.getDate()!=null)
		{
			rep.get().setDate(req.getDate());
		}
		if(req.getCarID()!=null)
		{
			Optional<CarEntity> car = repoCar.findById(req.getCarID());
			rep.get().setCar(car.get());
		}
		if(req.getRouteID()!=null)
			
		{
			Optional<RouteEntity> route = repoRoute.findById(req.getRouteID());
			rep.get().setRoute(route.get());
		}
		if(req.getStatusID()!=null)
		{
			Optional<StatusTripEntity> statusID = repoStatusTrip.findById(req.getStatusID());
			rep.get().setStatus(statusID.get());
		}
		repo.save(rep.get());
		
	}
	

}
