package com.bharath.flightreservation.repos;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bharath.flightreservation.entity.Flight;
import com.bharath.flightreservation.entity.Passenger;
import com.bharath.flightreservation.entity.User;

public interface PassengerRepository extends JpaRepository<Passenger, Long> {

}
