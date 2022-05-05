package com.coach.application.coachservice.car.controller;

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

import com.coach.application.coachservice.car.dto.CarRequest;
import com.coach.application.coachservice.car.service.CarService;
import com.coach.application.coachservice.route.dto.RouteRequest;
import com.coach.application.common.base.BaseResponse;

@RestController
@CrossOrigin
@RequestMapping("/car")
public class CarController {

	@Autowired
	public CarService service;

	@PostMapping("/save")
	@ResponseBody
	public ResponseEntity<?> save(@RequestBody CarRequest req) {
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
	public ResponseEntity<?> getByID(@RequestBody CarRequest req) {
		BaseResponse rep = service.getByID(req);
		return new ResponseEntity<>(rep, HttpStatus.OK);
	}

	@PostMapping("/searchcar")

	@ResponseBody
	public ResponseEntity<?> searchCar(@RequestBody CarRequest req) {
		BaseResponse rep = service.searchCar(req);
		return new ResponseEntity<>(rep, HttpStatus.OK);
	}

	@PostMapping("/delete")
	@ResponseBody
	public ResponseEntity<?> delete(@RequestBody CarRequest req) {
		service.delete(req);
		return new ResponseEntity<>(HttpStatus.OK);

	}

	@PostMapping("/deletecheck")
	@ResponseBody
	public ResponseEntity<?> deleteCheck(@RequestBody List<CarRequest> req) {
		service.deleteCheck(req);
		return new ResponseEntity<>(HttpStatus.OK);

	}
	@PostMapping("/update")
	@ResponseBody
	public ResponseEntity<?> deleteCheck(@RequestBody CarRequest req) {
		service.update(req);
		return new ResponseEntity<>(HttpStatus.OK);

	}
}
