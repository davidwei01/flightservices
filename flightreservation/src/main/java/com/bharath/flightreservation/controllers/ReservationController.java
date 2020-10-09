package com.bharath.flightreservation.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.bharath.flightreservation.dto.ReservationRequest;
import com.bharath.flightreservation.entity.Flight;
import com.bharath.flightreservation.entity.Reservation;
import com.bharath.flightreservation.repos.FlightRepository;
import com.bharath.flightreservation.services.ReservationService;

@Controller
public class ReservationController {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(ReservationController.class);
	
	@Autowired
	FlightRepository flightRepository;
	
	@Autowired
	ReservationService reservationervice;

	@RequestMapping("/showCompleteReservation")
	public String showCompleteReservation(@RequestParam("flightId") Long flightId, ModelMap modelMap) {
		LOGGER.info("Inside showCompleteReservation() ");
		Flight flight = flightRepository.findById(flightId).get();
		LOGGER.info("Inside showCompleteReservation(), flight is " + flight);
		modelMap.addAttribute("flight", flight);
		return "completeReservation";
	}
	@RequestMapping(value = "/completeReservation", method=RequestMethod.POST)
	public String completeReservation(ReservationRequest request, ModelMap modelMap) {
		LOGGER.info("Inside completeReservation(), request is " + request);
		Reservation reservation = reservationervice.bookFlight(request);
		LOGGER.info("Inside completeReservation(), reservation is " + reservation);
		modelMap.addAttribute("msg", "Reservation is successfully completed, here is the reservation id: " + reservation.getId());
		return "reservationConfirmation";
	}
}
