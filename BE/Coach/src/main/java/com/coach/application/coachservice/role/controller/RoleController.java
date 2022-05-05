package com.coach.application.coachservice.role.controller;

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

import com.coach.application.coachservice.role.dto.RoleRequest;
import com.coach.application.coachservice.role.service.RoleService;
import com.coach.application.common.base.BaseResponse;

@RestController
@CrossOrigin()
@RequestMapping("/role")
public class RoleController {
	@Autowired
	public RoleService service;

	@GetMapping("/getall")

	@ResponseBody
	public ResponseEntity <?>getAll() {
		BaseResponse list = service.getAll();
		return new ResponseEntity<>(list, HttpStatus.OK);

	}

	@PostMapping("/getbyid")
	@ResponseBody
	
	
	public ResponseEntity <?>getById(@RequestBody RoleRequest req) {
		BaseResponse list = service.getByID(req);
		return new ResponseEntity<>(list, HttpStatus.OK);

	}
	
	@PostMapping("/save")
	@ResponseBody
	public  ResponseEntity<?> create(@RequestBody RoleRequest req) {
		service.save(req);
		return new ResponseEntity<>(HttpStatus.CREATED);

	}
}
