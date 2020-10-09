package com.bharath.flightreservation.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bharath.flightreservation.controllers.ReservationController;
import com.bharath.flightreservation.dto.ReservationRequest;
import com.bharath.flightreservation.entity.Flight;
import com.bharath.flightreservation.entity.Passenger;
import com.bharath.flightreservation.entity.Reservation;
import com.bharath.flightreservation.repos.FlightRepository;
import com.bharath.flightreservation.repos.PassengerRepository;
import com.bharath.flightreservation.repos.ReservationRepository;
import com.bharath.flightreservation.util.EmailUtil;
import com.bharath.flightreservation.util.PDFGenerator;

@Service
public class ReservationServiceImpl implements ReservationService {

	private static final Logger LOGGER = LoggerFactory.getLogger(ReservationController.class);
	
	@Value("${com.bharath.flightreservation.itinerary.dirpath}")
	private String pdfDir;

	@Autowired
	private PassengerRepository passengerRepository;

	@Autowired
	private FlightRepository flightRepository;

	@Autowired
	private ReservationRepository reservationRepository;

	@Autowired
	private PDFGenerator pdfGenerator;

	@Autowired
	private EmailUtil emailUtil;

	@Override
	@Transactional
	public Reservation bookFlight(ReservationRequest request) {
		// card processing
		LOGGER.info("Inside service - bookFlight(), requst is " + request);
		Flight flight = flightRepository.findById(request.getFlightId()).get();

		Passenger passenger = new Passenger();
		passenger.setFirstName(request.getPassengerFirstName());
		passenger.setLastName(request.getPassengerLastName());
		passenger.setEmail(request.getPassengerEmail());
		passenger.setPhone(request.getPassengerPhone());
		Passenger savedPassenger = passengerRepository.save(passenger);

		Reservation reservation = new Reservation();
		reservation.setCheckedIn(false);
		reservation.setFlight(flight);
		reservation.setPassenger(savedPassenger);

		Reservation savedReservation = reservationRepository.save(reservation);
		
		LOGGER.info("Inside service - bookFlight(), savedReservation is " + savedReservation);
		
		String filePath = pdfDir + savedReservation.getId() + ".pdf";
		pdfGenerator.generateItinerary(savedReservation, filePath);

		LOGGER.info("Inside service - bookFlight(), sending email");
		emailUtil.sendItinerary(passenger.getEmail(), filePath);

		return savedReservation;
	}

}
