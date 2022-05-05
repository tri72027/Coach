package com.coach.application.coachservice.ticket.service;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.coach.application.entity.StatusTicketEntity;
import com.coach.application.entity.TicketEntity;
import com.coach.application.entity.TripEntity;
@Repository
public interface IRepoTicket extends JpaRepository<TicketEntity, Long>{
	public List<TicketEntity> findByTripAndStatus(TripEntity entity, StatusTicketEntity status);
	public List<TicketEntity> findByTrip(TripEntity entity);
}
