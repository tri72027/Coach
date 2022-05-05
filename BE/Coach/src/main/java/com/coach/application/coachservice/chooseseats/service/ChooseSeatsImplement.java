package com.coach.application.coachservice.chooseseats.service;

import java.util.List;
import java.util.Optional;
import java.util.Timer;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.coach.application.coachservice.car.service.IRepoCar;
import com.coach.application.coachservice.chooseseats.dto.ChooseSeatsRequest;
import com.coach.application.coachservice.chooseseats.dto.ChooseSeatsResponse;
import com.coach.application.coachservice.trip.service.IRepoTrip;
import com.coach.application.common.base.BaseResponse;
import com.coach.application.entity.CarEntity;
import com.coach.application.entity.ChooseSeatsDisableEntity;
import com.coach.application.entity.TripEntity;

@Service
public class ChooseSeatsImplement  implements ChooseSeatsService{
@Autowired
public IRepoChooseSeats repo;
@Autowired
public IRepoTrip repoTrip;
@Autowired
public IRepoCar repoCar;

	@Override
	public BaseResponse save(ChooseSeatsRequest req) {
		BaseResponse cmRep = new BaseResponse();
		ChooseSeatsDisableEntity entity = new ChooseSeatsDisableEntity();
		Optional<TripEntity> trip = repoTrip.findById(req.getTripID());
		Optional<CarEntity> car = repoCar.findById(req.getCarID());
		entity.setTrip(trip.get());
		entity.setCar(car.get());
		entity.setSeats(req.getSeats());
		ChooseSeatsDisableEntity resp = new ChooseSeatsDisableEntity();
		resp = repo.save(entity);
		cmRep.setContent(resp);
		AutoDeteleChooseSeats myTask = new AutoDeteleChooseSeats(repo, resp.getChooseSeatID());
		    Timer timer = new Timer();
		    timer.schedule(myTask, 600000);
		    
		    return cmRep;
	}

	@Override
	public BaseResponse getAll() {
		// TODO Auto-generated method stub
		BaseResponse cmRep = new BaseResponse();
		List<ChooseSeatsDisableEntity> list = repo.findAll();
		if (list.isEmpty()) {
			cmRep.setError("ko ton tai");
			return cmRep;
		}
		List<ChooseSeatsResponse> rep = list.stream().map(ChooseSeatsResponse::new).collect(Collectors.toList());
		cmRep.setContent(rep);
		return cmRep;
	}

	@Override
	public BaseResponse getByID(ChooseSeatsRequest req) {
		BaseResponse cmRep = new BaseResponse();
		Optional<ChooseSeatsDisableEntity> rep = repo.findById(req.getChooseSeatID());
		if(rep.isEmpty())
		{
			cmRep.setError("ko ton tai");
			return cmRep;
		}
		ChooseSeatsResponse resp = new ChooseSeatsResponse();
		resp.setChooseSeatID(rep.get().getChooseSeatID());
		resp.setTripID(rep.get().getTrip().getTripID());
		resp.setCarID(rep.get().getCar().getCarID());
		resp.setSeats(rep.get().getSeats());
		cmRep.setContent(resp);
		return cmRep;
	}

	@Override
	public BaseResponse getByTripAndCarAndSeats(ChooseSeatsRequest req) {
		// TODO Auto-generated method stub
		BaseResponse cmRep = new BaseResponse();
		Optional<TripEntity> trip = repoTrip.findById(req.getTripID());
		Optional<CarEntity> car = repoCar.findById(req.getCarID());
		ChooseSeatsDisableEntity rep = repo.findByTripAndCarAndSeats(trip.get(), car.get(), req.getSeats());

		ChooseSeatsResponse resp = new ChooseSeatsResponse();
		resp.setChooseSeatID(rep.getChooseSeatID());
		resp.setTripID(rep.getTrip().getTripID());
		resp.setCarID(rep.getCar().getCarID());
		resp.setSeats(rep.getSeats());
		cmRep.setContent(resp);
		return cmRep;
	}

	@Override
	public void delete(ChooseSeatsRequest req) {
		
		Optional<TripEntity> trip = repoTrip.findById(req.getTripID());
		Optional<CarEntity> car = repoCar.findById(req.getCarID());
		ChooseSeatsDisableEntity rep = repo.findByTripAndCarAndSeats(trip.get(), car.get(), req.getSeats());
		
		repo.deleteById(rep.getChooseSeatID());
	}

}
