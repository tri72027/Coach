package com.coach.application.coachservice.province.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.coach.application.coachservice.province.dto.ListProvinceRequest;
import com.coach.application.coachservice.province.dto.ProvinceReponse;
import com.coach.application.coachservice.province.dto.ProvinceRequest;
import com.coach.application.common.base.BaseResponse;
import com.coach.application.entity.ProvinceEntity;

@Service
public class ProvinceImplement implements ProvinceService {
	@Autowired
	public IRepoProvince repo;

	@Override
	public void save(ProvinceRequest requestList) {

		ProvinceEntity province = new ProvinceEntity();
		province.setCode(requestList.getCode());
		province.setName(requestList.getName());
		repo.save(province);

	}

	@Override
	public void savelist(ListProvinceRequest listCreateProvinceRequest) {
		// TODO Auto-generated method stub

		for (int i = 0; i < listCreateProvinceRequest.getListCreateProvinceRequest().size(); i++) {
			ProvinceEntity provinceEntity = new ProvinceEntity();
			provinceEntity.setCode(listCreateProvinceRequest.getListCreateProvinceRequest().get(i).getCode());
			provinceEntity.setName(listCreateProvinceRequest.getListCreateProvinceRequest().get(i).getName());
			repo.save(provinceEntity);

		}
	}

	@Override
	public BaseResponse get() {
		BaseResponse cmRep = new BaseResponse();
		List<ProvinceEntity> listProvince = repo.findAll();
//		Page<ProvinceEntity> page =repo.findAll(PageRequest.of(1, 5));
		if (listProvince.isEmpty()) {
			cmRep.setError("ko ton tai");
			return cmRep;
		}
		List<ProvinceReponse> rep = listProvince.stream().map(ProvinceReponse::new).collect(Collectors.toList());
		// List<GetProvinceReponse> rep=
		// page.stream().map(GetProvinceReponse::new).collect(Collectors.toList());
		/*
		 * List<GetProvinceReponse> xxx = new ArrayList<>(); for(int i = 0; i <
		 * listProvince.size(); i++) { xxx.add(new
		 * GetProvinceReponse(listProvince.get(i)));
		 * 
		 * }
		 */
//		System.out.println(xxx);

		cmRep.setContent(rep);
		return cmRep;
	}

	@Override
	public BaseResponse getid(ProvinceRequest ProvinceRequest) {
		// TODO Auto-generated method stub
		BaseResponse cmRes = new BaseResponse();
		Optional<ProvinceEntity> listProvince = repo.findById(ProvinceRequest.getProvinceID());
		if (listProvince.isEmpty()) {
			cmRes.setError("ko ton tai");
			return cmRes;
		}
		ProvinceRequest rep = new ProvinceRequest();
		rep.setProvinceID(listProvince.get().getProvinceID());
		rep.setCode(listProvince.get().getCode());
		rep.setName(listProvince.get().getName());
		cmRes.setContent(rep);
		return cmRes;
	}

	@Override
	public void update(ProvinceRequest updateProvinceRequest) {
		
		Optional<ProvinceEntity> province = repo.findById(updateProvinceRequest.getProvinceID());
		if(!(updateProvinceRequest.getCode()==null))
		{
			province.get().setCode(updateProvinceRequest.getCode());
		}
		if(!(updateProvinceRequest.getName()==null))
		{
			province.get().setName(updateProvinceRequest.getName());
		}
		
		repo.save(province.get());

	}

}
