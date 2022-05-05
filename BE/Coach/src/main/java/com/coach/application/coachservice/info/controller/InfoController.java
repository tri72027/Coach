package com.coach.application.coachservice.info.controller;

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

import com.coach.application.coachservice.info.dto.InfoRequest;
import com.coach.application.coachservice.info.service.InfoService;
import com.coach.application.common.base.BaseResponse;

@RestController
@CrossOrigin
@RequestMapping("/info")
public class InfoController {
	@Autowired
	public InfoService service;
	
	@PostMapping("/save")
	@ResponseBody
	public  ResponseEntity<?> save(@RequestBody InfoRequest req) {
		service.save(req);
		return new ResponseEntity<>(HttpStatus.CREATED);
	}
	
	@GetMapping("/getall")
	@ResponseBody
	public ResponseEntity<?> getAll()
	{
		BaseResponse rep = service.getAll();
		return new ResponseEntity<>(rep,HttpStatus.OK);
	}
	@PostMapping("/getbyid")
	
	@ResponseBody
	public ResponseEntity<?>getByID(@RequestBody InfoRequest req)
	{
		BaseResponse rep = service.getByID(req);
		return new ResponseEntity<>(rep,HttpStatus.OK);
	}
}
