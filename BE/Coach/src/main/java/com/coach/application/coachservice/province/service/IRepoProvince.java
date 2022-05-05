package com.coach.application.coachservice.province.service;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.coach.application.entity.ProvinceEntity;

@Repository
public interface IRepoProvince extends JpaRepository<ProvinceEntity, Long> {

}
