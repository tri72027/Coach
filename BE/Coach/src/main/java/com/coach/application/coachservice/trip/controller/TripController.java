package com.coach.application.coachservice.trip.controller;

import java.util.List;

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

import com.coach.application.coachservice.trip.dto.TripRequest;
import com.coach.application.coachservice.trip.service.TripService;
import com.coach.application.common.base.BaseResponse;

@RestController
@CrossOrigin
@RequestMapping("/trip")
public class TripController {

	@Autowired
	TripService service;
	

	@PostMapping("/save")
	@ResponseBody
	public  ResponseEntity<?> save(@RequestBody TripRequest req)
	{
		service.save(req);
		return new ResponseEntity<>(HttpStatus.CREATED);
	}


	@GetMapping("/getall")
	@ResponseBody
	public ResponseEntity<?> getAll() {
		BaseResponse rep = service.getAll();
		return new ResponseEntity<>(rep, HttpStatus.OK);

	}
	
	@PostMapping("/getbyid")
	@ResponseBody
	public ResponseEntity<?> getByID(@RequestBody TripRequest req)
	{
		BaseResponse  rep = service.getByID(req);
		return new ResponseEntity<>(rep,HttpStatus.OK);
	}
	
	@PostMapping("/gettrip")
	@ResponseBody
	public ResponseEntity<?> getByIDAndDate(@RequestBody TripRequest req)
	{
		
		return new ResponseEntity<>(service.fullTrip(req),HttpStatus.OK);
	}

	@PostMapping("/getbyroute")
	@ResponseBody
	public ResponseEntity<?> getByRoute(@RequestBody TripRequest req)
	{
		BaseResponse  rep = service.getByRouteID(req);
		return new ResponseEntity<>(rep,HttpStatus.OK);
	}

	@PostMapping("/delete")
	@ResponseBody
	public ResponseEntity<?> delete(@RequestBody TripRequest req) {
		service.delete(req);
		return new ResponseEntity<>(HttpStatus.OK);

	}

	@PostMapping("/deletecheck")
	@ResponseBody
	public ResponseEntity<?> deleteCheck(@RequestBody List<TripRequest> req) {
		service.deleteCheck(req);
		return new ResponseEntity<>(HttpStatus.OK);

	}
	@PostMapping("/update")
	@ResponseBody
	public ResponseEntity<?> deleteCheck(@RequestBody TripRequest req) {
		service.update(req);
		return new ResponseEntity<>(HttpStatus.OK);

	}
}
