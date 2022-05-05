package com.coach.application.coachservice.trip.service;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.Tuple;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.coach.application.coachservice.route.dto.RouteRequest;
import com.coach.application.coachservice.trip.dto.FullTripResponse;
import com.coach.application.coachservice.trip.dto.TripRequest;
import com.coach.application.common.base.BaseResponse;
import com.coach.application.common.base.ContentResponse;

@Component
public class TripProcess {
	@Autowired
	EntityManager em;
	private String from;
	private String where;
	public BaseResponse setDataQuery(String Query, String Count, TripRequest req) {
		BaseResponse cmRep = new BaseResponse();
		Query query = em.createNativeQuery(Query, Tuple.class);
		Query count = em.createNativeQuery(Count);
		Long routeID = req.getRouteID();
		Date date = req.getDate();
		Long carID = req.getCarID();
		Long statusID = req.getStatusID();
		Long tripID = req.getTripID();
		if (req.getRouteID() != null) {
			query.setParameter("routeID", routeID);
			count.setParameter("routeID", routeID);
			
		}
		if (req.getDate() != null) {
			query.setParameter("date", date);
			count.setParameter("date", date);
		}

		if (req.getCarID() != null) {
			query.setParameter("carID", carID);
			count.setParameter("carID", carID);
		}
		if (req.getStatusID() != null) {
			query.setParameter("statusID", statusID);
			count.setParameter("statusID", statusID);
		}
		if (req.getTripID() != null) {
			query.setParameter("tripID", tripID);
			count.setParameter("tripID", tripID);
		}
	
		int offSet = (req.getPageNum() - 1) * req.getPageSize();
		query.setParameter("offSet", offSet);
		query.setParameter("pageSize", req.getPageSize());

		 int totalElements = ((Number) count.getSingleResult()).intValue();
		List<Tuple> list = query.getResultList();
		List<Object> listRes = new ArrayList();

		for (Tuple tuple : list) {

			FullTripResponse res = new FullTripResponse();
			res.setTripID(((BigInteger) tuple.get("tripID")).longValue());
			res.setDate(((Date) tuple.get("date")));
			res.setCarID(((BigInteger) tuple.get("carID")).longValue());
			res.setRouteID(((BigInteger) tuple.get("routeID")).longValue());
			res.setCarCode((String) tuple.get("carCode"));
			res.setCarName((String) tuple.get("carName"));
			res.setLicensePlates((String) tuple.get("licensePlates"));
			res.setCarPrice(((Double) tuple.get("carPrice")).doubleValue());
			res.setCarAmount(((Integer) tuple.get("carAmount")).intValue());
			res.setDepartureTime((String) tuple.get("departureTime"));
			res.setJourneyTime((String) tuple.get("journeyTime"));
			res.setRoutePrice(((Double) tuple.get("routePrice")).doubleValue());
			res.setProvinceStartID(((BigInteger) tuple.get("provinceStartID")).longValue());
			res.setProvinceEndID(((BigInteger) tuple.get("provinceEndID")).longValue());
			res.setProvinceStartName((String) tuple.get("provinceStartName"));
			res.setProvinceEndName((String) tuple.get("provinceEndName"));
			res.setRoute((String) tuple.get("route"));
			res.setStatusID(((BigInteger) tuple.get("statusID")).longValue());
			res.setStatusName((String) tuple.get("statusName"));
			listRes.add(res);
		}
		ContentResponse content = new ContentResponse(listRes, req.getPageNum(), req.getPageSize(), totalElements);
		cmRep.setContent(content);
		return cmRep;

	}

	public String createQueryString(TripRequest req) {
		StringBuffer select = new StringBuffer(" SELECT ");
		StringBuffer from = new StringBuffer(" FROM TRIP AS T ");
		StringBuffer where = new StringBuffer(" WHERE 1 AND ");
		StringBuffer limit = new StringBuffer("LIMIT :offSet, :pageSize");

		select.append(" t.trip_id as tripID, t.date , t.car_id as carID , t.route_id as routeID, ").append(
				" c.code as carCode,c.name as carName,c.license_plates as licensePlates, c.price as carPrice, c.amount as carAmount, ")
				.append(" r.departure_time as departureTime, r.journey_time as journeyTime, r.price as routePrice, r.province_start as provinceStartID, r.province_end as provinceEndID , provinceStartName.name as provinceStartName, provinceEndName.name as provinceEndName, CONCAT(provinceStartName.name,'→', provinceEndName.name) as route, ST.name as statusName, T.status as statusID");
		from.append(" left join car  as c on  t.car_id = c.car_id ")
				.append(" left join route as r on t.route_id = r.route_id ")
				.append(" left join (select prs.province_id  as id , prs.name from province as prs)as provinceStartName on  provinceStartName.id = r.province_start ")
				.append(" left join (select pre.province_id as id, pre.name from province as pre)as provinceEndName on  provinceEndName.id = r.province_end ");
		from.append(" left join status_trip  as ST on  t.status = ST.status_id ");
		 
			if (req.getRouteID() != null) {
				where.append(" t.route_id = :routeID and ");
			}
			if (req.getDate() != null) {
				where.append(" t.date = :date and  ");
			}

			if (req.getCarID() != null) {
				where.append(" t.car_id = :carID and  ");
			}
			if (req.getStatusID() != null) {
				where.append(" t.status = :statusID and  ");
			}
			if (req.getTripID() != null) {
				where.append(" t.trip_id = :tripID and  ");
			}
		
		
		where.append(" 1 ");
		this.where = where.toString();
		this.from = from.toString();
		StringBuffer queryString = new StringBuffer();
		queryString.append(select).append(from).append(where).append(limit);
		return queryString.toString();

	}
	public String createQueryCountString() {
		StringBuffer queryString = new StringBuffer();
		StringBuffer select = new StringBuffer(" SELECT Count(trip_id)");
		StringBuffer from = new StringBuffer();
		StringBuffer where = new StringBuffer();
		from.append(this.from);
		where.append(this.where);
		StringBuffer querycountString = new StringBuffer();
		querycountString.append(select).append(from).append(where);
		return querycountString.toString();
	}
}
