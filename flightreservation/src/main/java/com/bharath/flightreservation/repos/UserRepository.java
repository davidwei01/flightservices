package com.bharath.flightreservation.repos;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bharath.flightreservation.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {

	User findByEmail(String email);

}
