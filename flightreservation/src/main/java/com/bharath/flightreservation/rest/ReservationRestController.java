package com.bharath.flightreservation.rest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bharath.flightreservation.controllers.ReservationController;
import com.bharath.flightreservation.dto.ReservationUpdateRequest;
import com.bharath.flightreservation.entity.Reservation;
import com.bharath.flightreservation.repos.ReservationRepository;

@RestController
@CrossOrigin
public class ReservationRestController {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(ReservationController.class);

	@Autowired
	private ReservationRepository reservationReposaitory;
	
	@RequestMapping("/reservations/{id}")
	public Reservation findReservation(@PathVariable("id") Long id) {
		LOGGER.info("Inside rest - findReservation(), id is " + id);
		return reservationReposaitory.findById(id).get();
	}
	
	@RequestMapping("/reservations")
	public Reservation updateReservation(@RequestBody ReservationUpdateRequest request) {
		LOGGER.info("Inside rest - updateReservation(), ReservationUpdateRequest is " + request);
		Reservation reservation = reservationReposaitory.findById(request.getId()).get();
		reservation.setCheckedIn(request.getCheckedIn());
		reservation.setNumberOfBags(request.getNumberOfBags());
		return reservationReposaitory.save(reservation);
	}
}
