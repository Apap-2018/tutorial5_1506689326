package com.apap.tutorial5.controller;

import com.apap.tutorial5.model.FlightModel;
import com.apap.tutorial5.model.PilotModel;
import com.apap.tutorial5.service.FlightService;
import com.apap.tutorial5.service.PilotService;

import java.util.List;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 
 * FlightController
 */
@Controller
public class FlightController {
	@Autowired
	private FlightService flightService;
	
	@Autowired
	private PilotService pilotService;

	//private PilotModel pilot;

	
	@RequestMapping(value = "/flight/add/{licenseNumber}", method = RequestMethod.GET)
	private String add(@PathVariable(value = "licenseNumber") String licenseNumber, Model model) {
		//FlightModel flight = new FlightModel();
		PilotModel pilot = pilotService.getPilotDetailByLicenseNumber(licenseNumber);
		//flight.setPilot(pilot);
		pilot.setPilotFlight(new ArrayList<FlightModel>());
		pilot.getPilotFlight().add(new FlightModel());
		model.addAttribute("pilot", pilot);
		return "addFlight";
	}
	
	//controller add row 
	@RequestMapping(value = "/flight/add", method = RequestMethod.POST)
	private String addFlightSubmit(@ModelAttribute FlightModel flight) {
		flightService.addFlight(flight);
		return "add";
	}
	
	//controller submit semua data flight yg ditambah
	@RequestMapping(value="/flight/add/{licenseNumber}", params={"add"})
	public String addRow(@ModelAttribute PilotModel pilot, BindingResult bindingResult, Model model) {
		pilot.getPilotFlight().add(new FlightModel());
	    model.addAttribute("pilot", pilot);
	    return "addFlight";
	}
	
	@RequestMapping(value = "/flight/add/{licenseNumber}", params={"submit"}, method = RequestMethod.POST)
	private String addFlightSubmit(@ModelAttribute PilotModel pilot) {
		PilotModel thisPilot = pilotService.getPilotDetailByLicenseNumber(pilot.getLicenseNumber());
		for (FlightModel flight:pilot.getPilotFlight()) {
			flight.setPilot(thisPilot);
			flightService.addFlight(flight);
		}
		return "add";
	}
	
	
	
	//batas edit 
	@RequestMapping(value = "/flight/view")
	private String viewFlight(@RequestParam (value = "flightNumber" )String flightNumber, Model model) {
		
		List<FlightModel> archive = flightService.getFlightList();
		List<FlightModel> archiveView = new ArrayList();
		
		for(FlightModel looping : archive) {
			if(looping.getFlightNumber().equals(flightNumber)) {
				archiveView.add(looping);
			}
		}
		if(archiveView.size() == 0) {
			return "error";
		}
		
		model.addAttribute("fnum", flightNumber);
		model.addAttribute("flights", archiveView);
		return "view-flight";
		
	}
	
	@RequestMapping(value="/flight/delete", method = RequestMethod.POST)
	private String delPilot(@ModelAttribute PilotModel pilot, Model model) {
		for(FlightModel flight : pilot.getPilotFlight()){
			flightService.deleteFlight(flight.getId());
		}
		return "hapusFlight";
	}
	
	@RequestMapping(value="/flight/update/{id}", method = RequestMethod.GET)
	private String updtFlight(@PathVariable(value = "id")Long id, Model model) {
		FlightModel flight = flightService.getFlight(id);
		PilotModel pilot = pilotService.getPilotDetailByLicenseNumber(flight.getPilot().getLicenseNumber());
		flight.setPilot(pilot);
		model.addAttribute("updFlight", flight);
		return "updFlight";
	}
	
	@RequestMapping(value = "flight/update", method = RequestMethod.POST)
	private String flightSubmitUpdate(@ModelAttribute FlightModel flight) {
		flightService.updateFlight(flight, flight.getId());
		return "updateDataFlight";
	}
	

}
