package com.bharath.flightreservation.repos;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bharath.flightreservation.entity.Role;

public interface RoleRepository extends JpaRepository<Role, Long> {

}
