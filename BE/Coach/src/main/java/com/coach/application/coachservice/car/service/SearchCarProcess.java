package com.coach.application.coachservice.car.service;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.Tuple;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.coach.application.coachservice.car.dto.CarRequest;
import com.coach.application.coachservice.car.dto.CarResponse;
import com.coach.application.common.base.BaseResponse;
import com.coach.application.common.base.ContentResponse;

@Component
public class SearchCarProcess {
	@Autowired
	EntityManager em;
	public String where;
	public String from;

	public BaseResponse setDataSearchQuery(String Query, String Count, CarRequest req) {
		BaseResponse cmRep = new BaseResponse();
		Query query = em.createNativeQuery(Query, Tuple.class);
		Query count = em.createNativeQuery(Count);

		if (req.getCarID() != null) {
			Long carID = req.getCarID();
			query.setParameter("carID", carID);
			count.setParameter("carID", carID);
		}
		if (req.getCode() != null && req.getCode() != "") {
			String code = req.getCode();
			query.setParameter("code", code);
			count.setParameter("code", code);
		}
		if (req.getName() != null && req.getName() != "") {
			String name = req.getName();
			query.setParameter("name", name);
			count.setParameter("name", name);
		}
		if (req.getAmount() != null) {
			Integer amount = req.getAmount();
			query.setParameter("amount", amount);
			count.setParameter("amount", amount);
		}

		if (req.getLicensePlates() != null && req.getLicensePlates() != "") {
			String licensePlates = req.getLicensePlates();
			query.setParameter("licensePlates", licensePlates);
			count.setParameter("licensePlates", licensePlates);
		}
		if (req.getPrice() != null) {
			Double price = req.getPrice();
			query.setParameter("price", price);
			count.setParameter("price", price);
		}

		int offSet = (req.getPageNum() - 1) * req.getPageSize();
		query.setParameter("offSet", offSet);
		query.setParameter("pageSize", req.getPageSize());

		int totalElements = 0;
		List<Tuple> list = query.getResultList();
		if (list.isEmpty()) {
			return cmRep;

		} else {

			totalElements = ((Number) count.getSingleResult()).intValue();
			List<Object> resp = new ArrayList();
			for (Tuple tuple : list) {
				CarResponse res = new CarResponse();
				res.setCarID(((BigInteger) tuple.get("CarID")).longValue());
				res.setName(((String) tuple.get("name")));
				res.setCode(((String) tuple.get("code")));
				res.setLicensePlates(((String) tuple.get("licensePlates")));
				res.setAmount(((Integer) tuple.get("amount")).intValue());
				res.setPrice(((Double) tuple.get("price")).doubleValue());
				res.setStatusName(((String) tuple.get("statusName")));
				res.setStatusID(((BigInteger) tuple.get("statusID")).longValue());

				resp.add(res);
			}
			ContentResponse content = new ContentResponse(resp, req.getPageNum(), req.getPageSize(), totalElements);
			cmRep.setContent(content);
			return cmRep;
		}

	}

	public String createQueryString(CarRequest req) {

		StringBuffer select = new StringBuffer(" SELECT ");
		StringBuffer from = new StringBuffer(" FROM CAR AS R ");
		StringBuffer where = new StringBuffer(" WHERE 1 AND ");
		StringBuffer limit = new StringBuffer("LIMIT :offSet, :pageSize");
		select.append(
				" R.car_id as carID, R.code as code, R.license_plates as licensePlates,  R.name as name, R.price as price, R.amount as amount,R.status as statusID, ST.name as statusName");
		from.append(" LEFT JOIN status_car as ST on R.status = ST.status_id ");
		
		if (req.getCarID() != null) {
			where.append(" R.car_id = :carID AND ");
		}
		;
		if (req.getCode() != null && req.getCode() != "") {
			where.append(" R.code = :code AND ");
		}
		if (req.getLicensePlates() != null && req.getLicensePlates() != "") {
			where.append(" R.license_plates like CONCAT('%',:licensePlates,'%') AND ");
		}
		if (req.getName() != null && req.getName() != "") {
			where.append(" R.name like CONCAT('%',:name,'%') AND ");
		}
		if (req.getAmount() != null) {
			where.append(" R.amount = :amount AND ");
		}
		if (req.getPrice() != null) {
			where.append(" R.price = :price AND ");
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
		StringBuffer select = new StringBuffer(" SELECT Count(car_id) ");
		StringBuffer from = new StringBuffer();
		StringBuffer where = new StringBuffer();
		from.append(this.from);
		where.append(this.where);
		StringBuffer querycountString = new StringBuffer();
		querycountString.append(select).append(from).append(where);
		return querycountString.toString();
	}

}
