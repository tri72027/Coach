package com.jwt.demojwt.repo;

import java.util.Optional;

import org.hibernate.metamodel.model.convert.spi.JpaAttributeConverter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.jwt.demojwt.Entity.User;
@Repository
public interface IRepoUser extends JpaRepository<User, Long>{

	Optional<User> findByUserName(String username);
	Boolean existsByUserName(String username);
	Boolean existsByEmail(String email);
	
}
