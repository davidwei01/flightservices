package com.bharath.flightcheckin.integration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.bharath.flightcheckin.integration.dto.Reservation;
import com.bharath.flightcheckin.integration.dto.ReservationUpdateRequest;

@Component
public class ReservationRestClientImpl implements ReservationRestClient {
	
	@Autowired
	RestTemplate restTemplate;

	private static final String RESERVATION_CLIENT_URL = "http://localhost:8080/flightreservation/reservations/";

	@Override
	public Reservation findReservation(Long id) {
	//	RestTemplate restTemplate = new RestTemplate();
		Reservation reservation = restTemplate
				.getForObject(RESERVATION_CLIENT_URL + id, Reservation.class);
		return reservation;
	}

	@Override
	public Reservation updateReservation(ReservationUpdateRequest request) {
	//	RestTemplate restTemplate = new RestTemplate();
		Reservation reservation = restTemplate
				.postForObject(RESERVATION_CLIENT_URL, request, Reservation.class);
		return reservation;
	}

}
