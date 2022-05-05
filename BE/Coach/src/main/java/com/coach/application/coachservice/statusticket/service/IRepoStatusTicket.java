package com.coach.application.coachservice.statusticket.service;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.coach.application.entity.StatusTicketEntity;
@Repository
public interface IRepoStatusTicket  extends JpaRepository<StatusTicketEntity, Long>{

}
