package com.coach.application.coachservice.ticket.service;

import java.math.BigInteger;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.Tuple;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.coach.application.coachservice.ticket.dto.SearchDetailTicketResponse;
import com.coach.application.coachservice.ticket.dto.SearchTicketResponse;
import com.coach.application.coachservice.ticket.dto.TicketRequest;
import com.coach.application.common.base.BaseResponse;

@Component
public class TicketDetailProcess {
	@Autowired
	public EntityManager em;

	public BaseResponse setDataQuery(String Query, TicketRequest req) {
		BaseResponse cmRep = new BaseResponse();
		Query query = em.createNativeQuery(Query, Tuple.class);
		Long ticketID = req.getTicketID();
		
		if (req.getTicketID() != null) {
			query.setParameter("ticketID", ticketID);
		}

		List<Tuple> list = query.getResultList();
		List<SearchDetailTicketResponse> listRes = new ArrayList();
		for (Tuple tuple : list) {
			SearchDetailTicketResponse res = new SearchDetailTicketResponse();
			res.setTicketID(((BigInteger) tuple.get("ticketID")).longValue());
			res.setDateBuyTicket(((Date) tuple.get("dateBuyTicket")));
			res.setDateGo(((Date) tuple.get("dateGo")));
			res.setTicketCode(((String) tuple.get("ticketCode")));
			res.setAmountSeats(((Integer)tuple.get("amountSeats")).intValue());
			res.setSeats(((String) tuple.get("seats")));
			res.setPrice(((Double) tuple.get("price")).doubleValue());
			res.setStatus(((String) tuple.get("status")));
			res.setFullName(((String) tuple.get("fullName")));
			res.setPhone(((String) tuple.get("phone")));
			res.setRoute(((String) tuple.get("route")));
			res.setCarName(((String) tuple.get("carName")));
			res.setLicensePlates(((String) tuple.get("LicensePlates")));
			res.setDepartureTime(((String) tuple.get("departureTime")));
			listRes.add(res);
		}
		cmRep.setContent(listRes);
		return cmRep;
	}

	public String createQueryString(TicketRequest req) {
		StringBuffer select = new StringBuffer(" SELECT ");
		StringBuffer from = new StringBuffer(" FROM TICKET AS T ");
		StringBuffer where = new StringBuffer(" WHERE 1 AND ");
		select.append(
				" T.ticket_id AS ticketID, T.date AS dateBuyTicket,TR.date AS dateGo,T.trip_id as tripID, T.ticket_code AS ticketCode, T.amount_seats as amountSeats,T.seats,T.status as statusID,provinceStart.province_id as provinceStartID,provinceEnd.province_id as   provinceEndID , CONCAT(provinceStart.name, '  →  ' ,provinceEnd.name) AS route, T.price AS price, T.info_id AS infoID,I.fullname as fullName, i.phone,  ST.name AS status ,C.license_plates as licensePlates, C.name as carName , R.departure_time as departureTime");
		from.append(" LEFT JOIN status_ticket AS ST ON T.status = ST.status_id ");
		from.append(" LEFT JOIN trip  AS TR ON TR.trip_id = T.trip_id");
		from.append(" LEFT JOIN route AS R ON TR.route_id = R.route_id");
		from.append(
				" LEFT JOIN (SELECT province_id,  name FROM province ) AS provinceStart ON R.province_start = provinceStart.province_id");
		from.append(
				" LEFT JOIN (SELECT province_id, name FROM province) AS provinceEnd ON R.province_end = provinceEnd.province_id");
		from.append(" LEFT JOIN info AS I ON T.info_id = I.info_id ");
		from.append(" LEFT JOIN car AS C ON TR.car_id = C.car_id ");
		if (req.getTicketCode() != null && req.getTicketCode() != "") {
			where.append("  T.ticket_code = :ticketCode AND ");
		}
		if (req.getTicketID() != null) {
			where.append("  T.ticket_id = :ticketID AND ");
		}
		
		where.append(" 1 ");
		StringBuffer queryString = new StringBuffer();
		queryString.append(select).append(from).append(where);
		return queryString.toString();
	}
}
