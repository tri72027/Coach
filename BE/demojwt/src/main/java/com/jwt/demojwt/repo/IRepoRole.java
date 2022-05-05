package com.jwt.demojwt.repo;

import java.util.Optional;

import org.hibernate.metamodel.model.convert.spi.JpaAttributeConverter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.jwt.demojwt.Entity.Roles;
import com.jwt.demojwt.common.ERole;
@Repository
public interface IRepoRole extends JpaRepository<Roles, Long>{

	Optional<Roles> findByName(ERole username);
	
	
}
