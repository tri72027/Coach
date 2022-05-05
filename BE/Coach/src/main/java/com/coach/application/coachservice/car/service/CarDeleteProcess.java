package com.coach.application.coachservice.car.service;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.Tuple;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.coach.application.coachservice.route.dto.RouteRequest;
import com.coach.application.coachservice.route.dto.SearchRouteResponse;
import com.coach.application.coachservice.ticket.dto.TicketRequest;
import com.coach.application.common.base.BaseResponse;

@Component
public class CarDeleteProcess {
	@Autowired
	EntityManager em;

	@Transactional
	public void setDataTicketQuery(String Query, Long tripID) {
		Query query = em.createNativeQuery(Query);
		
		query.setParameter("tripID", tripID);
		
		query.executeUpdate();
	}

	public String createTicketQueryString() {

		String queryString;
		queryString = "update ticket set status = 4 where trip_id in (select trip_id from trip where trip_id = :tripID)";
		return queryString.toString();

	}
	@Transactional
	public void setDataTripQuery(String Query, Long carID) {
		Query query = em.createNativeQuery(Query);
		
		query.setParameter("carID", carID);
		
		query.executeUpdate();
	}

	public String createTripQueryString() {

		String queryString;
		queryString = "update trip set status = 3 where route_id in (select route_id from route where car_id = :carID)";
		return queryString.toString();

	}
}
