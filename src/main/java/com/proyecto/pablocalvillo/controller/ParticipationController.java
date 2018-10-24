package com.proyecto.pablocalvillo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.proyecto.pablocalvillo.converter.ParticipationConverter;
import com.proyecto.pablocalvillo.model.ParticipationModel;
import com.proyecto.pablocalvillo.service.ParticipationService;

@Controller
@RequestMapping("participation")
public class ParticipationController {
	
	private static final String ADD_PARTICIPATION = "addParticipation";
	private static final String EDIT_RACES_VIEW = "editRaces";
	
	@Autowired
	@Qualifier("participationServiceImpl")
	private ParticipationService participationServiceImpl;
	
	@Autowired
	@Qualifier("participationConverter")
	ParticipationConverter participationConverter;
	
	@GetMapping("/listParticipations")
	public ModelAndView listAllParticipations() {
		ModelAndView mav = new ModelAndView(EDIT_RACES_VIEW);
		mav.addObject("participations", participationServiceImpl.listAllParticipations());
		return mav;
	}
	
	@GetMapping("/add")
	public ModelAndView add() {
		ModelAndView mav = new ModelAndView(ADD_PARTICIPATION);
		mav.addObject("participation", new ParticipationModel());
		return mav;
	}
	
	@PostMapping("/addParticipation")
	public String addParticipation(@ModelAttribute("participation") ParticipationModel participationModel) {
		participationServiceImpl.addParticipation(participationModel);		
		return "redirect:/races/add";
	}

}
