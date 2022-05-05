package com.coach.application.coachservice.route.service;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.Tuple;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.coach.application.coachservice.route.dto.RouteRequest;
import com.coach.application.coachservice.route.dto.SearchDropdownRouteResponse;
import com.coach.application.coachservice.route.dto.SearchRouteResponse;
import com.coach.application.common.base.BaseResponse;
import com.coach.application.common.base.ContentResponse;

@Component
public class RouteSearchProcess {
	@Autowired
	EntityManager em;
	private String where;
	private String from;

	public BaseResponse setDataQuery(String Query, String Count, RouteRequest req) {
		Query query = em.createNativeQuery(Query, Tuple.class);
		Query count = em.createNativeQuery(Count);
		BaseResponse cmRep = new BaseResponse();

		if (req.getProvinceStart() != null) {
			Long provineStartID = req.getProvinceStart();
			query.setParameter("provineStartID", provineStartID);
			count.setParameter("provineStartID", provineStartID);
		}
		if (req.getProvinceEnd() != null) {
			Long provineEndID = req.getProvinceEnd();
			query.setParameter("provineEndID", provineEndID);
			count.setParameter("provineEndID", provineEndID);
		}
		if (req.getDepartureTime() != null && req.getDepartureTime() != "") {
			String departureTime = req.getDepartureTime();
			query.setParameter("departureTime", departureTime);
			count.setParameter("departureTime", departureTime);
		}
		if (req.getJourneyTime() != null && req.getJourneyTime() != "") {
			String journeyTime = req.getJourneyTime();
			query.setParameter("journeyTime", journeyTime);
			count.setParameter("journeyTime", journeyTime);
		}
		if (req.getStatusID() != null) {
			Long status = req.getStatusID();
			query.setParameter("status", status);
			count.setParameter("status", status);
		}
		int offSet = (req.getPageNum() - 1) * req.getPageSize();
		query.setParameter("offSet", offSet);
		query.setParameter("pageSize", req.getPageSize());
	
		int totalElements = 0;
		List<Tuple> list = query.getResultList();
		if(list.isEmpty())
		{
				return cmRep;
			 
		}
		else 
		{

			 totalElements = ((Number) count.getSingleResult()).intValue();
			 List<Object> resp = new ArrayList();
				for (Tuple tuple : list) {
					SearchRouteResponse res = new SearchRouteResponse();
					res.setRouteID(((BigInteger) tuple.get("routeID")).longValue());
					res.setDepartureTime(((String) tuple.get("departureTime")));
					res.setJourneyTime(((String) tuple.get("journeyTime")));
					res.setProvinceStart(((BigInteger) tuple.get("provinceStartID")).longValue());
					res.setProvinceEnd(((BigInteger) tuple.get("provinceEndID")).longValue());
					res.setPrice(((Double) tuple.get("price")).doubleValue());
					res.setProvinceStartName(((String) tuple.get("provinceStartName")));
					res.setProvinceEndName(((String) tuple.get("provinceEndName")));
					res.setStatusID(((BigInteger) tuple.get("statusID")).longValue());
					res.setStatusName(((String) tuple.get("statusName")));
					resp.add(res);
				}
				ContentResponse content = new ContentResponse(resp, req.getPageNum(), req.getPageSize(), totalElements);
				cmRep.setContent(content);
			return cmRep;
		}
	
	}

	public String createQueryString(RouteRequest req) {

		StringBuffer select = new StringBuffer(" SELECT ");
		StringBuffer from = new StringBuffer(" FROM ROUTE AS R ");
		StringBuffer where = new StringBuffer(" WHERE 1 AND ");
		StringBuffer limit = new StringBuffer("LIMIT :offSet, :pageSize");

		select.append(
				" R.route_id as routeID, R.departure_time as departureTime, R.journey_time as journeyTime, R.province_start as provinceStartID, R.province_end AS provinceEndID, R.price, provinceStart.name AS provinceStartName, provinceEnd.name AS provinceEndName, R.status as statusID, ST.name as statusName   ");
		from.append(
				" LEFT JOIN (SELECT P.province_id, P.name FROM PROVINCE AS P) as provinceStart on provinceStart.province_id = R.province_start ");
		from.append(
				" LEFT JOIN (SELECT P.province_id, P.name FROM PROVINCE AS P) as provinceEnd on provinceEnd.province_id = R.province_end ");
		from.append(" LEFT JOIN status as ST on ST.status_id = R.status ");
		if (req.getProvinceStart() != null) {
			where.append(" R.province_Start = :provineStartID AND ");
		}
		if (req.getProvinceEnd() != null) {
			where.append(" R.province_End = :provineEndID AND ");
		}

		if (req.getDepartureTime() != null && req.getDepartureTime() != "") {
			where.append(" R.departure_time like CONCAT('%',:departureTime,'%') AND ");
		}
		if (req.getJourneyTime() != null && req.getJourneyTime() != "") {
			where.append(" R.journey_timelike CONCAT('%',:journeyTime AND,'%') ");
		}
		if (req.getStatusID() != null) {
			where.append(" R.status = :status AND ");
		}

		where.append(" 1 ");
		this.where = where.toString();
		this.from = from.toString();
		StringBuffer queryString = new StringBuffer();
		queryString.append(select).append(from).append(where).append(limit);

		return queryString.toString();

	}

	public String createQueryCountString(RouteRequest req) {
		StringBuffer queryString = new StringBuffer();
		StringBuffer select = new StringBuffer(" SELECT Count(route_id)");
		StringBuffer from = new StringBuffer();
		StringBuffer where = new StringBuffer();
		from.append(this.from);
		where.append(this.where);
		StringBuffer querycountString = new StringBuffer();
		querycountString.append(select).append(from).append(where);
		return querycountString.toString();
	}

	public String createQueryRouteSearchString() {

		StringBuffer select = new StringBuffer(" SELECT ");
		StringBuffer from = new StringBuffer(" FROM ROUTE AS R ");
		StringBuffer where = new StringBuffer(" WHERE 1 AND ");
	
		select.append(
				" R.route_id as routeID,  CONCAT(provinceStart.name,'→',provinceEnd.name) AS route, R.journey_time as journeyTime, R.departure_time as departureTime  ");
		from.append(
				" LEFT JOIN (SELECT P.province_id, P.name FROM PROVINCE AS P) as provinceStart on provinceStart.province_id = R.province_start ");
		from.append(
				" LEFT JOIN (SELECT P.province_id, P.name FROM PROVINCE AS P) as provinceEnd on provinceEnd.province_id = R.province_end ");
		from.append(" LEFT JOIN status as ST on ST.status_id = R.status ");
	
		
			where.append(" R.status = 1 AND ");
		

		where.append(" 1 ");

		StringBuffer queryString = new StringBuffer();
		queryString.append(select).append(from).append(where);

		return queryString.toString();

	}
	public BaseResponse setDataRouteSearchQuery(String Query) {
		Query query = em.createNativeQuery(Query, Tuple.class);
		BaseResponse cmRep = new BaseResponse();

		List<Tuple> list = query.getResultList();
		List<Object> resp = new ArrayList();
		for (Tuple tuple : list) {
			SearchDropdownRouteResponse res = new SearchDropdownRouteResponse();
			res.setRouteID(((BigInteger) tuple.get("routeID")).longValue());
			res.setRoute(((String) tuple.get("route")));
			res.setDepartureTime(((String) tuple.get("departureTime")));
			res.setJourneyTime(((String) tuple.get("journeyTime")));
		
			resp.add(res);
		}	
		cmRep.setContent(resp);
		return cmRep;
	}
}
