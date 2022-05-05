package com.coach.application.coachservice.ticket.controller;

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

import com.coach.application.coachservice.ticket.dto.TicketRequest;
import com.coach.application.coachservice.ticket.service.TicketService;
import com.coach.application.common.base.BaseResponse;

@RestController
@CrossOrigin
@RequestMapping("/ticket")
public class TicketController {
	@Autowired
	public TicketService service ;

	@PostMapping("/save")
	@ResponseBody
	public ResponseEntity<?> save(@RequestBody TicketRequest req)
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
	public ResponseEntity<?> getByID(@RequestBody TicketRequest req)
	{
		BaseResponse  rep = service.getByID(req);
		return new ResponseEntity<>(rep,HttpStatus.OK);
	}
	
	@PostMapping("/getbytripid")
	@ResponseBody
	public ResponseEntity<?> getByTripID(@RequestBody TicketRequest req)
	{
		BaseResponse  rep = service.getByTripID(req);
		return new ResponseEntity<>(rep,HttpStatus.OK);
	}
	@PostMapping("/getticket")
	@ResponseBody
	public ResponseEntity<?> getHistory(@RequestBody TicketRequest req)
	{
		BaseResponse  rep = service.getManagerTicket(req);
		return new ResponseEntity<>(rep,HttpStatus.OK);
	}
	
	@PostMapping("/getticketdetail")
	@ResponseBody
	public ResponseEntity<?> getDetail(@RequestBody TicketRequest req)
	{
		BaseResponse  rep = service.getDetailTicket(req);
		return new ResponseEntity<>(rep,HttpStatus.OK);
	}
	
	
	@PostMapping("/delete")
	@ResponseBody
	public ResponseEntity<?> delete(@RequestBody TicketRequest req)
	{
		 service.delete(req);
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	@PostMapping("/deletecheck")
	@ResponseBody
	public ResponseEntity<?> delete(@RequestBody List<TicketRequest> req)
	{
		 service.deleteCheck(req);
		return new ResponseEntity<>(HttpStatus.OK);
	}
	@PostMapping("/update")
	@ResponseBody
	public ResponseEntity<?> update(@RequestBody TicketRequest req)
	{
		 service.update(req);
		return new ResponseEntity<>(HttpStatus.OK);
	}
}
