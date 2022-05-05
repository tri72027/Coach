	package com.coach.application.coachservice.route.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.coach.application.coachservice.province.service.IRepoProvince;
import com.coach.application.coachservice.route.dto.RouteRequest;
import com.coach.application.coachservice.route.dto.RouteResponse;
import com.coach.application.coachservice.status.service.IRepoStatus;
import com.coach.application.coachservice.statusticket.service.IRepoStatusTicket;
import com.coach.application.coachservice.statustrip.service.IRepoStatusTrip;
import com.coach.application.coachservice.ticket.service.IRepoTicket;
import com.coach.application.coachservice.trip.service.IRepoTrip;
import com.coach.application.common.base.BaseResponse;
import com.coach.application.entity.ProvinceEntity;
import com.coach.application.entity.RouteEntity;
import com.coach.application.entity.StatusEntity;
import com.coach.application.entity.StatusTicketEntity;
import com.coach.application.entity.StatusTripEntity;
import com.coach.application.entity.TicketEntity;
import com.coach.application.entity.TripEntity;

@Service
public class RouteImplement implements RouteService {
	@Autowired
	public IRepoRoute repo;

	@Autowired
	public IRepoProvince repoProvine;
	@Autowired
	public IRepoTrip repoTrip;
	@Autowired
	public IRepoTicket repoTicket;
	@Autowired
	public IRepoStatusTrip repoStatusTrip;
	@Autowired
	public IRepoStatusTicket repoStatusTicket;
	@Autowired
	public IRepoStatus repoStatus;

	@Autowired
	public RouteSearchProcess processSearch;

	@Autowired
	public RouteDeleteProcess processDelete;

	@Override
	public void save(RouteRequest req) {
		// TODO Auto-generated method stub
		RouteEntity routeEntity = new RouteEntity();
		routeEntity.setDepartureTime(req.getDepartureTime());
		routeEntity.setJourneyTime(req.getJourneyTime());
		routeEntity.setPrice(req.getPrice());
		Optional<ProvinceEntity> provinceStart = repoProvine.findById(req.getProvinceStart());
		Optional<ProvinceEntity> provinceEnd = repoProvine.findById(req.getProvinceEnd());
		Optional<StatusEntity> status = repoStatus.findById(req.getStatusID());
		routeEntity.setProvinceStart(provinceStart.get());
		routeEntity.setProvinceEnd(provinceEnd.get());
		routeEntity.setStatus(status.get());
		repo.save(routeEntity);
	}

	@Override
	public BaseResponse getAll() {

		BaseResponse cmRep = new BaseResponse();
		List<RouteEntity> list = repo.findAll();
		if (list.isEmpty()) {
			cmRep.setError("ko ton tai");
			return cmRep;
		}
		List<RouteResponse> rep = list.stream().map(RouteResponse::new).collect(Collectors.toList());
		cmRep.setContent(rep);
		// TODO Auto-generated method stub
		return cmRep;
	}

	@Override
	public BaseResponse getProvinceStart(RouteRequest req) {
		BaseResponse cmRep = new BaseResponse();
		Optional<ProvinceEntity> provinceStart = repoProvine.findById(req.getProvinceStart());
		// Use JPArepository
		RouteEntity list = repo.findRouteByProvinceStart(provinceStart.get());
		// Use Query
//	RouteEntity list = repo.findRouteByProvinceStart(provinceStart.get().getProvinceID());

		RouteResponse response = new RouteResponse();
		response.setDepartureTime(list.getDepartureTime());
		response.setJourneyTime(list.getJourneyTime());
		response.setPrice(list.getPrice());
		response.setProvinceStart(list.getProvinceStart().getProvinceID());
		response.setProvinceEnd(list.getProvinceEnd().getProvinceID());
		response.setRouteID(list.getRouteID());
		response.setProvinceStartName(list.getProvinceStart().getName());
		response.setProvinceEndName(list.getProvinceEnd().getName());
		cmRep.setContent(response);
		return cmRep;

	}

	@Override
	public BaseResponse getbyId(RouteRequest req) {
		// TODO Auto-generated method stub

		BaseResponse cmRes = new BaseResponse();
		RouteResponse response = new RouteResponse();
		Optional<RouteEntity> listRoute = repo.findById(req.getRouteID());
		if (listRoute.isEmpty()) {
			cmRes.setError("ko ton tai");
			return cmRes;
		}

		response.setRouteID(listRoute.get().getRouteID());
		response.setDepartureTime(listRoute.get().getDepartureTime());
		response.setJourneyTime(listRoute.get().getJourneyTime());
		response.setPrice(listRoute.get().getPrice());
		response.setProvinceStart(listRoute.get().getProvinceStart().getProvinceID());
		response.setProvinceEnd(listRoute.get().getProvinceEnd().getProvinceID());
		response.setProvinceStartName(listRoute.get().getProvinceStart().getName());
		response.setProvinceEndName(listRoute.get().getProvinceEnd().getName());
		response.setStatusID(listRoute.get().getStatus().getStatusID());
		cmRes.setContent(response);
		return cmRes;
	}

	@Override
	public BaseResponse searchRoute(RouteRequest req) {
		BaseResponse cmRes = new BaseResponse();
		String query = processSearch.createQueryString(req);
		String count = processSearch.createQueryCountString(req);
		cmRes = processSearch.setDataQuery(query,count, req);
		return cmRes;
	}

	@Override
	public void delete(RouteRequest req) {

		Optional<RouteEntity> route = repo.findById(req.getRouteID());
		Optional<StatusEntity> statusID = repoStatus.findById((long) 3);
		List<TripEntity> trip = repoTrip.findByRoute(route.get());
		String queryDeleteTicket = processDelete.createTicketQueryString();
		String queryDeleteTrip = processDelete.createTripQueryString();
		for (TripEntity tripEntity : trip) {
			processDelete.setDataTicketQuery(queryDeleteTicket, tripEntity.getTripID());
		}
		processDelete.setDataTripQuery(queryDeleteTrip, route.get().getRouteID());
		route.get().setStatus(statusID.get());
		repo.save(route.get());
		
	}

	@Override
	public void deleteCheck(List<RouteRequest> req) {
		for (RouteRequest routeRequest : req) {
			Optional<RouteEntity> route = repo.findById(routeRequest.getRouteID());
			Optional<StatusEntity> statusID = repoStatus.findById((long) 3);
			List<TripEntity> trip = repoTrip.findByRoute(route.get());
			String queryDeleteTicket = processDelete.createTicketQueryString();
			String queryDeleteTrip = processDelete.createTripQueryString();
			for (TripEntity tripEntity : trip) {
				processDelete.setDataTicketQuery(queryDeleteTicket, tripEntity.getTripID());
			}
			processDelete.setDataTripQuery(queryDeleteTrip, route.get().getRouteID());
			route.get().setStatus(statusID.get());
			repo.save(route.get());
		}

	}

	@Override
	public void update(RouteRequest req) {
		Optional<RouteEntity> rep = repo.findById(req.getRouteID());

		if (req.getProvinceStart() != null) {

			Optional<ProvinceEntity> provinceStart = repoProvine.findById(req.getProvinceStart());
			rep.get().setProvinceStart(provinceStart.get());

		}
		if (req.getProvinceEnd() != null) {
			Optional<ProvinceEntity> provinceEnd = repoProvine.findById(req.getProvinceEnd());
			rep.get().setProvinceEnd(provinceEnd.get());
		}
		if (req.getDepartureTime() != null && req.getDepartureTime() != "") {
			rep.get().setDepartureTime(req.getDepartureTime());

		}
		if (req.getJourneyTime() != null && req.getJourneyTime() != "") {
			rep.get().setJourneyTime(req.getJourneyTime());
		}
		if (req.getStatusID() != null) {
			Optional<StatusEntity> statusID = repoStatus.findById(req.getStatusID());
			rep.get().setStatus(statusID.get());
		}
		repo.save(rep.get());
	}

	@Override
	public BaseResponse searchDropdown( ) {
		BaseResponse cmRes = new BaseResponse();
		String query = processSearch.createQueryRouteSearchString();
		cmRes = processSearch.setDataRouteSearchQuery(query);
		return cmRes;
	}

}
