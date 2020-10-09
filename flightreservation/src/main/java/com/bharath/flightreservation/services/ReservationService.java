package com.bharath.flightreservation.services;

import com.bharath.flightreservation.dto.ReservationRequest;
import com.bharath.flightreservation.entity.Reservation;

public interface ReservationService {
	public Reservation bookFlight(ReservationRequest request);

}
