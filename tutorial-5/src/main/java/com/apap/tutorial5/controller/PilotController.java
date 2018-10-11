package com.apap.tutorial5.controller;

import com.apap.tutorial5.model.PilotModel;
import com.apap.tutorial5.model.FlightModel;
import com.apap.tutorial5.service.PilotService;
import com.apap.tutorial5.service.FlightService;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.PathVariable;
/**
 * 
 *  PilotController
 */
@Controller
public class PilotController {
	@Autowired
	private PilotService pilotService;
	private FlightService flightService;
	
	@RequestMapping("/")
	private String home() {
		return "home";
	}
	
	@RequestMapping(value = "/pilot/add", method = RequestMethod.GET)
	private String add(Model model) {
		model.addAttribute("pilot", new PilotModel());
		return "addPilot";
	}
	
	@RequestMapping(value = "/pilot/add", method = RequestMethod.POST)
	private String addPilotSubmit(@ModelAttribute PilotModel pilot) {
		pilotService.addPilot(pilot);
		return "add";
	}
	
	@RequestMapping(value = "/pilot/view", method = RequestMethod.GET)
	private String viewPilot(@RequestParam ("licenseNumber") String licenseNumber, Model model) {
		PilotModel pilot = pilotService.getPilotDetailByLicenseNumber(licenseNumber);
		model.addAttribute("pilot",pilot);
		model.addAttribute("Flight",pilot.getPilotFlight());
		return "view-pilot";
	}
	
	@RequestMapping(value="/pilot/delete/{id}", method = RequestMethod.GET)
	private String delPilot(@PathVariable(value = "id")Long id) {
		pilotService.deletePilot(id);
		return "HapusPilot";
	}
	
	@RequestMapping(value="/pilot/update/{licenseNumber}", method = RequestMethod.GET)
	private String updtPilot(@PathVariable(value = "licenseNumber")String licenseNumber, Model model) {
		PilotModel pilot = pilotService.getPilotDetailByLicenseNumber(licenseNumber);
		model.addAttribute("update", pilot);
		return "updateDataPilot";
	}
	
	@RequestMapping(value = "/pilot/update", method = RequestMethod.POST)
	private String pilotSubmitUpdate(@ModelAttribute PilotModel pilot) {
		pilotService.updatePilot(pilot, pilot.getLicenseNumber());
		return "updPilot";
	}
	
}
