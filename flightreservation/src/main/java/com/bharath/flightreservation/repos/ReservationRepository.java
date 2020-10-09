package com.bharath.flightreservation.repos;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bharath.flightreservation.entity.Flight;
import com.bharath.flightreservation.entity.Reservation;
import com.bharath.flightreservation.entity.User;

public interface ReservationRepository extends JpaRepository<Reservation, Long> {

}
