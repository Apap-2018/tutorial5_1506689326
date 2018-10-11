package com.apap.tutorial5.service;

import com.apap.tutorial5.model.FlightModel;
import com.apap.tutorial5.repository.FlightDB;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * 
 *  FlightServiceImpl
 */
@Service
@Transactional
public class FlightServiceImpl implements FlightService {
	@Autowired
	private FlightDB flightDb;
	
	@Override
	public void addFlight(FlightModel flight) {
		flightDb.save(flight);
	}

	@Override
	public void deleteFlight(long id) {
		// TODO Auto-generated method stub
		flightDb.deleteById(id);
	}

	@Override
	public void updateFlight(FlightModel flight, long id) {
		// TODO Auto-generated method stub
		FlightModel updateFlight = flightDb.findFlightById(id);
		updateFlight.setFlightNumber(flight.getFlightNumber());
		updateFlight.setOrigin(flight.getOrigin());
		updateFlight.setDestination(flight.getDestination());
		updateFlight.setTime(flight.getTime());
		flightDb.save(updateFlight);
		
	}

	@Override
	public FlightModel getFlightDetailByLicenseNumber(String licenseNumber) {
		// TODO Auto-generated method stub
		return flightDb.findByPilotLicenseNumber(licenseNumber);
	}

	@Override
	public FlightModel getFlight(long id) {
		// TODO Auto-generated method stub
		return flightDb.findFlightById(id);
	}

	@Override
	public List<FlightModel> getFlightList() {
		// TODO Auto-generated method stub
		return flightDb.findAll();
	}
	
	
	
}
