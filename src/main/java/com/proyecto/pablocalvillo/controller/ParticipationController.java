package com.proyecto.pablocalvillo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.proyecto.pablocalvillo.model.ParticipationModel;
import com.proyecto.pablocalvillo.service.impl.ParticipationServiceImpl;
import com.proyecto.pablocalvillo.service.impl.RaceServiceImpl;

@Controller
@RequestMapping("/participation")
public class ParticipationController {
	
	private static final String PARTICIPATIONS_VIEW = "participations";
	
	@Autowired
	@Qualifier("participationServiceImpl")
	private ParticipationServiceImpl participationServiceImpl;
	
	@Autowired
	@Qualifier("raceServiceImpl")
	private RaceServiceImpl raceServiceImpl;
	
	@GetMapping("/listAllParticipations")
	public ModelAndView listAllParticipations() {
		ModelAndView mav = new ModelAndView(PARTICIPATIONS_VIEW);
		mav.addObject("participations", participationServiceImpl.listAllParticipations());
		return mav;
	}
	
	@PostMapping("/addParticipationCar")
	public String addParticipationCar(@ModelAttribute("participation") ParticipationModel participationModel, RedirectAttributes redirectAttributes) {
		System.out.println(participationModel);
		try {
			participationServiceImpl.addParticipation(participationModel);
			redirectAttributes.addFlashAttribute("success", true);
		} catch(Exception e) {
			redirectAttributes.addFlashAttribute("success", false);
			return "redirect:/cars/editCar?matricula=" + participationModel.getCoche();
		}	
		return "redirect:/cars/editCar?matricula=" + participationModel.getCoche();
	}
	
	@PostMapping("/addParticipationRace")
	public String addParticipationRace(@ModelAttribute("participation") ParticipationModel participationModel, RedirectAttributes redirectAttributes) {
		System.out.println(participationModel + "----------------------------------------------");
		try {
			participationServiceImpl.addParticipation(participationModel);
			redirectAttributes.addFlashAttribute("success", true);
		} catch(Exception e) {
			redirectAttributes.addFlashAttribute("success", false);
			return "redirect:/races/editRace?id=" + raceServiceImpl.findByName(participationModel.getCarrera()).getId();
		}	
		return "redirect:/races/editRace?id=" + raceServiceImpl.findByName(participationModel.getCarrera()).getId();
	}
	
	@GetMapping("/removeParticipation")
	public String removeParticipation(@RequestParam(name = "id", required = true, defaultValue = "NULL") int id) {
		participationServiceImpl.removeParticipation(id);
		return "redirect:/participation/listAllParticipations";
	}
}
