package com.coach.application.coachservice.chooseseats.service;

import java.util.Optional;
import java.util.TimerTask;

import org.springframework.stereotype.Service;

import com.coach.application.entity.ChooseSeatsDisableEntity;

@Service
public class AutoDeteleChooseSeats extends TimerTask {

	public IRepoChooseSeats chooseSeatRepo;

	private Long chooseSeatsID;

	public AutoDeteleChooseSeats(Long chooseSeatsID) {
		this.chooseSeatsID = chooseSeatsID;
	}

	public AutoDeteleChooseSeats(IRepoChooseSeats chooseSeatRepo, Long chooseSeatsID) {
		this.chooseSeatRepo = chooseSeatRepo;
		this.chooseSeatsID = chooseSeatsID;

	}

	public AutoDeteleChooseSeats() {
	}

	@Override
	public void run() {
		System.out.println("a " + this.chooseSeatsID);
		Optional<ChooseSeatsDisableEntity> rep = chooseSeatRepo.findById(chooseSeatsID);
		if (rep != null) {
			chooseSeatRepo.deleteById(this.chooseSeatsID);
		}
	}

}
