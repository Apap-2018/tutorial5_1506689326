package com.apap.tutorial5.service;

import com.apap.tutorial5.model.PilotModel;
import com.apap.tutorial5.repository.PilotDB;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 
 * PilotServiceImpl
 */

@Service
@Transactional
public class PilotServiceImpl implements PilotService {
	@Autowired
	private PilotDB pilotDb;
	
	@Override
	public PilotModel getPilotDetailByLicenseNumber(String licenseNumber) {
		return pilotDb.findByLicenseNumber(licenseNumber);
	}
	
	@Override
	public void addPilot(PilotModel pilot) {
		pilotDb.save(pilot);
	}

	@Override
	public void deletePilot(long id) {
		// TODO Auto-generated method stub
		pilotDb.deleteById(id);
	}

	@Override
	public void updatePilot(PilotModel pilot, String licenseNumber) {
		// TODO Auto-generated method stub
		PilotModel updatePilot = pilotDb.findByLicenseNumber(licenseNumber);
		updatePilot.setName(pilot.getName());
		updatePilot.setFlyHour(pilot.getFlyHour());
		pilotDb.save(updatePilot);
	}

}
