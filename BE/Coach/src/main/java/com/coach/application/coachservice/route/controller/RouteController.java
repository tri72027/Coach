package com.coach.application.coachservice.route.controller;

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

import com.coach.application.coachservice.route.dto.RouteRequest;
import com.coach.application.coachservice.route.service.RouteService;
import com.coach.application.common.base.BaseResponse;

@RestController
@CrossOrigin()
@RequestMapping("/route")
public class RouteController {
	@Autowired
	public RouteService service;

	@PostMapping("/save")
	@ResponseBody
	public ResponseEntity<?> create(@RequestBody RouteRequest req) {
		service.save(req);
		return new ResponseEntity<>(HttpStatus.CREATED);

	}

	@GetMapping("/getall")

	@ResponseBody
	public ResponseEntity<?> getAll() {
		BaseResponse list = service.getAll();
		return new ResponseEntity<>(list, HttpStatus.OK);

	}

	@PostMapping("/getbyprovincestart")
	@ResponseBody

	public ResponseEntity<?> getProvinceStart(@RequestBody RouteRequest req) {
		BaseResponse list = service.getProvinceStart(req);
		return new ResponseEntity<>(list, HttpStatus.OK);

	}

	@PostMapping("/getbyid")
	@ResponseBody

	public ResponseEntity<?> getById(@RequestBody RouteRequest req) {
		BaseResponse list = service.getbyId(req);
		return new ResponseEntity<>(list, HttpStatus.OK);

	}

	@PostMapping("/searchroute")
	@ResponseBody
	public ResponseEntity<?> searchRoute(@RequestBody RouteRequest req) {
		BaseResponse list = service.searchRoute(req);
		return new ResponseEntity<>(list, HttpStatus.OK);

	}
	
	@PostMapping("/delete")
	@ResponseBody
	public ResponseEntity<?> delete(@RequestBody RouteRequest req) {
		service.delete(req);
		return new ResponseEntity<>(HttpStatus.OK);

	}

	@PostMapping("/deletecheck")
	@ResponseBody
	public ResponseEntity<?> deleteCheck(@RequestBody List<RouteRequest> req) {
		service.deleteCheck(req);
		return new ResponseEntity<>(HttpStatus.OK);

	}
	@PostMapping("/update")
	@ResponseBody
	public ResponseEntity<?> deleteCheck(@RequestBody RouteRequest req) {
		service.update(req);
		return new ResponseEntity<>(HttpStatus.OK);

	}

	@GetMapping("/searchdropdownroute")
	@ResponseBody
	public ResponseEntity<?> searchDropDownRoute() {
		BaseResponse list = service.searchDropdown();
		return new ResponseEntity<>(list, HttpStatus.OK);

	}
	
}
