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

import com.coach.application.coachservice.route.dto.RouteRequest;
import com.coach.application.coachservice.ticket.dto.SearchTicketResponse;
import com.coach.application.coachservice.ticket.dto.TicketRequest;
import com.coach.application.common.base.BaseResponse;
import com.coach.application.common.base.ContentResponse;

@Component
public class TicketSearchProcess {
	@Autowired
	public EntityManager em;
	private String where;
	private String from;

	public BaseResponse setDataQuery(String Query, String Count, TicketRequest req) {
		BaseResponse cmRep = new BaseResponse();
		Query query = em.createNativeQuery(Query, Tuple.class);
		Query count = em.createNativeQuery(Count);

		if (!(req.getInfo() == null)) {

			if (req.getInfo().getPhone() != null && req.getInfo().getPhone() != "") {
				String phone = req.getInfo().getPhone();
				query.setParameter("phone", phone);
				count.setParameter("phone", phone);
			}
			if (req.getInfo().getFullName() != null && req.getInfo().getFullName() != "") {
				String fullname = req.getInfo().getFullName();
				query.setParameter("fullname", fullname);
				count.setParameter("fullname", fullname);
			}
		}
		if (req.getTicketCode() != null && req.getTicketCode() != "") {
			String ticketCode = req.getTicketCode();
			query.setParameter("ticketCode", ticketCode);
			count.setParameter("ticketCode", ticketCode);
		}

		if (req.getDateBuyTicket() != null) {
			Date dateBuyTicket = req.getDateBuyTicket();
			query.setParameter("dateBuyTicket", dateBuyTicket);
			count.setParameter("dateBuyTicket", dateBuyTicket);
		}
		if (req.getDateGo() != null) {
			Date dateGo = req.getDateGo();
			query.setParameter("dateGo", dateGo);
			count.setParameter("dateGo", dateGo);
		}
		if (req.getProvinceStart() != null) {
			Long provinceStart = req.getProvinceStart();
			query.setParameter("provinceStart", provinceStart);
			count.setParameter("provinceStart", provinceStart);
		}
		if (req.getProvinceEnd() != null) {
			Long provinceEnd = req.getProvinceEnd();
			query.setParameter("provinceEnd", provinceEnd);
			count.setParameter("provinceEnd", provinceEnd);
		}
		if (req.getStatusID() != null) {
			Long status = req.getStatusID();
			query.setParameter("status", status);
			count.setParameter("status", status);
		}
		int offSet = (req.getPageNum() - 1) * req.getPageSize();
		query.setParameter("offSet", offSet);
		query.setParameter("pageSize", req.getPageSize());

		int totalElements = ((Number) count.getSingleResult()).intValue();
		List<Tuple> list = query.getResultList();
		List<Object> listRes = new ArrayList();
		for (Tuple tuple : list) {
			SearchTicketResponse res = new SearchTicketResponse();
			res.setTicketID(((BigInteger) tuple.get("ticketID")).longValue());
			res.setDateBuyTicket(((Date) tuple.get("dateBuyTicket")));
			res.setDateGo(((Date) tuple.get("dateGo")));
			res.setTicketCode(((String) tuple.get("ticketCode")));
			res.setFullName(((String) tuple.get("fullName")));
			res.setPhone(((String) tuple.get("phone")));
			res.setRoute(((String) tuple.get("route")));
			res.setPrice(((Double) tuple.get("price")).doubleValue());
			res.setStatus(((String) tuple.get("status")));
			res.setStatusID(((BigInteger) tuple.get("statusID")).longValue());
			res.setTripID(((BigInteger) tuple.get("tripID")).longValue());
			res.setProvinceStartID(((BigInteger) tuple.get("provinceStartID")).longValue());
			res.setProvinceEndID(((BigInteger) tuple.get("provinceEndID")).longValue());
			res.setSeats(((String) tuple.get("seats")));
			res.setStatusID(((BigInteger) tuple.get("statusID")).longValue());
			if (((BigInteger) tuple.get("infoID")) != null) {
				res.setInfoID(((BigInteger) tuple.get("infoID")).longValue());
			} else {
				res.setInfoID((long) 0);
			}

			listRes.add(res);
		}
		ContentResponse content = new ContentResponse(listRes, req.getPageNum(), req.getPageSize(), totalElements);
		cmRep.setContent(content);
		return cmRep;
	}

	public String createQueryString(TicketRequest req) {
		StringBuffer select = new StringBuffer(" SELECT ");
		StringBuffer from = new StringBuffer(" FROM TICKET AS T ");
		StringBuffer where = new StringBuffer(" WHERE 1 AND ");
		StringBuffer limit = new StringBuffer("LIMIT :offSet, :pageSize");
		select.append(
				" T.ticket_id AS ticketID, T.date AS dateBuyTicket,TR.date AS dateGo,T.trip_id as tripID, T.ticket_code AS ticketCode, T.seats,T.status as statusID,provinceStart.province_id as provinceStartID,provinceEnd.province_id as   provinceEndID , CONCAT(provinceStart.name, '  →  ' ,provinceEnd.name) AS route, T.price AS price, T.info_id AS infoID,I.fullname as fullName, i.phone,  ST.name AS status  ");
		from.append(" LEFT JOIN status_ticket AS ST ON T.status = ST.status_id ");
		from.append(" LEFT JOIN trip  AS TR ON TR.trip_id = T.trip_id");
		from.append(" LEFT JOIN route AS R ON TR.route_id = R.route_id");
		from.append(
				" LEFT JOIN (SELECT province_id,  name FROM province ) AS provinceStart ON R.province_start = provinceStart.province_id");
		from.append(
				" LEFT JOIN (SELECT province_id, name FROM province) AS provinceEnd ON R.province_end = provinceEnd.province_id");
		from.append(" LEFT JOIN info AS I ON T.info_id = I.info_id ");
		if (req.getTicketCode() != null && req.getTicketCode() != "") {
			where.append("  T.ticket_code = :ticketCode AND ");
		}
		if (req.getDateBuyTicket() != null) {
			where.append("  T.date = :dateBuyTicket AND ");
		}
		if (req.getDateGo() != null) {
			where.append("  TR.date = :dateGo AND ");
		}
		if (req.getProvinceStart() != null) {
			where.append("  R.province_start = :provinceStart AND ");
		}
		if (req.getProvinceEnd() != null) {
			where.append("  R.province_end = :provinceEnd AND ");
		}
		if (req.getStatusID() != null) {
			where.append("  T.status = :status AND ");
		}
		if (!(req.getInfo() == null)) {
			if (req.getInfo().getPhone() != null && req.getInfo().getPhone() != "") {
				where.append("  I.phone like  CONCAT('%',:phone,'%') AND ");
			}
			if (req.getInfo().getFullName() != null && req.getInfo().getFullName() != "") {
				where.append("  I.fullname like  CONCAT('%',:fullname,'%') AND ");
			}
		}
		where.append(" 1 ");
		this.from = from.toString();
		this.where = where.toString();
		StringBuffer queryString = new StringBuffer();
		queryString.append(select).append(from).append(where).append(limit);
		return queryString.toString();
	}

	public String createQueryCountString() {
		StringBuffer queryString = new StringBuffer();
		StringBuffer select = new StringBuffer(" SELECT Count(ticket_id)");
		StringBuffer from = new StringBuffer();
		StringBuffer where = new StringBuffer();
		from.append(this.from);
		where.append(this.where);
		StringBuffer querycountString = new StringBuffer();
		querycountString.append(select).append(from).append(where);
		return querycountString.toString();
	}

}
