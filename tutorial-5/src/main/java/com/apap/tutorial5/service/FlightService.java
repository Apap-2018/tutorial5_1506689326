package com.apap.tutorial5.service;
import com.apap.tutorial5.model.FlightModel;
import java.util.List;

public interface FlightService {
	void addFlight(FlightModel flight);
	void deleteFlight(long id);
	void updateFlight(FlightModel flight, long id);
	FlightModel getFlightDetailByLicenseNumber(String licenseNumber);
	FlightModel getFlight(long id);
	List<FlightModel> getFlightList();
	
	
}
