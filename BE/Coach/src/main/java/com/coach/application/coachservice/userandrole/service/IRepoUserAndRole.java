package com.coach.application.coachservice.userandrole.service;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.coach.application.entity.UserAndRolesEntity;
@Repository
public interface IRepoUserAndRole extends JpaRepository<UserAndRolesEntity, Long>{

}
