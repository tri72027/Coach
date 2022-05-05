package com.coach.application.coachservice.car.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.coach.application.coachservice.car.dto.CarRequest;
import com.coach.application.coachservice.car.dto.CarResponse;
import com.coach.application.coachservice.statuscar.service.IRepoStatusCar;
import com.coach.application.coachservice.trip.service.IRepoTrip;
import com.coach.application.common.base.BaseResponse;
import com.coach.application.entity.CarEntity;
import com.coach.application.entity.RouteEntity;
import com.coach.application.entity.StatusCarEntity;
import com.coach.application.entity.StatusEntity;
import com.coach.application.entity.TripEntity;

@Service
public class CarImplement implements CarService {

	@Autowired
	public IRepoCar repo;
	@Autowired
	public IRepoTrip repoTrip;
	@Autowired
	public SearchCarProcess processSearch;
	@Autowired
	public CarDeleteProcess processDelete;
	@Autowired
	public IRepoStatusCar repoStatusCar;
	@Override
	public void save(CarRequest req) {
	
		CarEntity rep = new CarEntity();
		rep.setCode(req.getCode());
		rep.setName(req.getName());
		rep.setLicensePlates(req.getLicensePlates());
		rep.setPrice(req.getPrice());
		rep.setAmount(req.getAmount());
		Optional<StatusCarEntity> status = repoStatusCar.findById(req.getStatusID());
		rep.setStatus(status.get());
		repo.save(rep);
	}

	@Override
	public BaseResponse getAll() {
		BaseResponse cmRep = new BaseResponse();
		List<CarEntity> list = repo.findAll();
		if (list.isEmpty()) {
			cmRep.setError("ko ton tai");
			return cmRep;
		}
		List<CarResponse> rep = list.stream().map(CarResponse::new).collect(Collectors.toList());
		cmRep.setContent(rep);
		return cmRep;
	}

	@Override
	public BaseResponse getByID(CarRequest req) {
		BaseResponse cmRep = new BaseResponse();
		Optional<CarEntity> rep = repo.findById(req.getCarID());
		if(rep.isEmpty())
		{
			cmRep.setError("ko ton tai");
			return cmRep;
		}
		CarResponse resp = new CarResponse();
		resp.setCarID(rep.get().getCarID());
		resp.setCode(rep.get().getCode());
		resp.setName(rep.get().getName());
		resp.setLicensePlates(rep.get().getLicensePlates());
		resp.setAmount(rep.get().getAmount());
		resp.setPrice(rep.get().getPrice());
		resp.setStatusID(rep.get().getStatus().getStatusID());
		cmRep.setContent(resp);
		return cmRep;
	}

	@Override
	public BaseResponse searchCar(CarRequest req) {
		BaseResponse cmRep = new BaseResponse();
		String query = processSearch.createQueryString(req);
		String count = processSearch.createQueryCountString();
		cmRep = processSearch.setDataSearchQuery(query, count, req);
		return cmRep;
	}

	@Override
	public void delete(CarRequest req) {

		Optional<CarEntity> car = repo.findById(req.getCarID());
		Optional<StatusCarEntity> statusID = repoStatusCar.findById((long) 3);
		List<TripEntity> trip = repoTrip.findByCar(car.get());
		String queryDeleteTrip = processDelete.createTripQueryString();
		String queryDeleteTicket = processDelete.createTicketQueryString();
		for (TripEntity tripEntity : trip) {
			processDelete.setDataTripQuery(queryDeleteTrip, tripEntity.getCar().getCarID());
			processDelete.setDataTicketQuery(queryDeleteTicket, tripEntity.getTripID());
			;
			
		}
		car.get().setStatus(statusID.get());
		repo.save(car.get());
	}

	@Override
	public void deleteCheck(List<CarRequest> req) {
		
		for (CarRequest carRequest : req) {
			Optional<CarEntity> car = repo.findById(carRequest.getCarID());
			Optional<StatusCarEntity> statusID = repoStatusCar.findById((long) 3);
			List<TripEntity> trip = repoTrip.findByCar(car.get());
			String queryDeleteTrip = processDelete.createTripQueryString();
			String queryDeleteTicket = processDelete.createTicketQueryString();
			for (TripEntity tripEntity : trip) {
				processDelete.setDataTripQuery(queryDeleteTrip, tripEntity.getCar().getCarID());
				processDelete.setDataTicketQuery(queryDeleteTicket, tripEntity.getTripID());
			}
			car.get().setStatus(statusID.get());
			repo.save(car.get());
		}
		
	}

	@Override
	public void update(CarRequest req) {
		Optional<CarEntity> car = repo.findById(req.getCarID());
		
		if (req.getCarID() != null) {
			car.get().setCarID(req.getCarID());
		}
		;
		if (req.getCode() != null && req.getCode() != "") {
			car.get().setCode(req.getCode());
		}
		if (req.getLicensePlates() != null && req.getLicensePlates() != "") {
			car.get().setLicensePlates(req.getLicensePlates());
		}
		if (req.getName() != null && req.getName() != "") {
			car.get().setName(req.getName());
		}
		if (req.getAmount() != null) {
			car.get().setAmount(req.getAmount());
		}
		if (req.getPrice() != null) {
			car.get().setPrice(req.getPrice());
		}
		if(req.getStatusID()!=null)
		{
			Optional<StatusCarEntity> status = repoStatusCar.findById(req.getStatusID());
			car.get().setStatus(status.get());
		}
		repo.save(car.get());
		
	}
	
}
