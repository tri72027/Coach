package com.coach.application.coachservice.statuscar.controller;

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

import com.coach.application.coachservice.statuscar.dto.StatusCarRequest;
import com.coach.application.coachservice.statuscar.service.StatusCarService;
import com.coach.application.common.base.BaseResponse;

@RestController
@CrossOrigin
@RequestMapping("/statuscar")
public class StatusCarController {
	@Autowired
	public StatusCarService service;

	

	@PostMapping("/save")
	@ResponseBody
	public ResponseEntity<?> save(@RequestBody StatusCarRequest req) {
		service.save(req);
		return new ResponseEntity<>(HttpStatus.CREATED);
	}
	@GetMapping("/getall")
	@ResponseBody
	public ResponseEntity<?> getAll() {
		BaseResponse rep = service.getAll();
		return new ResponseEntity<>(rep,HttpStatus.OK);

	};
	
	@PostMapping("/getbyid")
	
	@ResponseBody
	public ResponseEntity<?>getByID(@RequestBody StatusCarRequest req)
	{
		BaseResponse rep = service.getByID(req);
		return new ResponseEntity<>(rep,HttpStatus.OK);
	}
	
}
