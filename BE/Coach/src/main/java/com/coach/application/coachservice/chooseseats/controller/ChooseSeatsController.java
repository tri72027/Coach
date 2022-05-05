package com.coach.application.coachservice.chooseseats.controller;

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

import com.coach.application.coachservice.chooseseats.dto.ChooseSeatsRequest;
import com.coach.application.coachservice.chooseseats.service.ChooseSeatsService;
import com.coach.application.common.base.BaseResponse;

@RestController
@CrossOrigin
@RequestMapping("/chooseseats")
public class ChooseSeatsController {

	@Autowired
	public ChooseSeatsService service;

	@PostMapping("/save")
	@ResponseBody
	public ResponseEntity<?> save(@RequestBody ChooseSeatsRequest req) {
		BaseResponse rep = service.save(req);
		return new ResponseEntity<>( HttpStatus.CREATED);
	}

	@GetMapping("/getall")
	@ResponseBody
	public ResponseEntity<?> getAll() {
		BaseResponse rep = service.getAll();
		return new ResponseEntity<>(rep, HttpStatus.OK);
	}

	@PostMapping("/getbyid")

	@ResponseBody
	public ResponseEntity<?> getByID(@RequestBody ChooseSeatsRequest req) {
		BaseResponse rep = service.getByID(req);
		return new ResponseEntity<>(rep, HttpStatus.OK);
	}

	@PostMapping("/getbytripandcarandseat")

	@ResponseBody
	public ResponseEntity<?> getByTripAndCarAndSeats(@RequestBody ChooseSeatsRequest req) {
		BaseResponse rep = service.getByTripAndCarAndSeats(req);
		return new ResponseEntity<>(rep, HttpStatus.OK);
	}
	@PostMapping("/delete")

	@ResponseBody
	public ResponseEntity<?> delete(@RequestBody ChooseSeatsRequest req) {
		 service.delete(req);
		return new ResponseEntity<>(HttpStatus.OK);
	}
}
