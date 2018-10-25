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

import com.proyecto.pablocalvillo.converter.ParticipationConverter;
import com.proyecto.pablocalvillo.model.ParticipationModel;
import com.proyecto.pablocalvillo.service.ParticipationService;
import com.proyecto.pablocalvillo.service.impl.CarServiceImpl;
import com.proyecto.pablocalvillo.service.impl.RaceServiceImpl;

@Controller
@RequestMapping("/participation")
public class ParticipationController {
	
	private static final String ADD_PARTICIPATION = "addParticipation";
	private static final String PARTICIPATIONS_VIEW = "participations";
	
	@Autowired
	@Qualifier("participationServiceImpl")
	private ParticipationService participationServiceImpl;
	
	@Autowired
	@Qualifier("participationConverter")
	private ParticipationConverter participationConverter;
	
	@Autowired
	@Qualifier("raceServiceImpl")
	private RaceServiceImpl raceServiceImpl;
	
	@Autowired
	@Qualifier("carServiceImpl")
	private CarServiceImpl carServiceImpl;
	
	@GetMapping("/listParticipations")
	public ModelAndView listAllParticipations() {
		ModelAndView mav = new ModelAndView(PARTICIPATIONS_VIEW);
		mav.addObject("participations", participationServiceImpl.listAllParticipations());
		return mav;
	}
	
	@GetMapping("/add")
	public ModelAndView add() {
		ModelAndView mav = new ModelAndView(ADD_PARTICIPATION);
		mav.addObject("participation", new ParticipationModel());
		mav.addObject("races", raceServiceImpl.listAllRaces());
		mav.addObject("cars", carServiceImpl.listAllCars());
		return mav;
	}
	
	@PostMapping("/addParticipation")
	public String addParticipation(@ModelAttribute("participation") ParticipationModel participationModel) {
		participationServiceImpl.addParticipation(participationModel);		
		return "redirect:/participation/add";
	}
	
	@GetMapping("/removeParticipation")
	public String removeParticipation(@RequestParam(name = "id", required = true, defaultValue = "NULL") int id) {
		participationServiceImpl.removeParticipation(id);
		return "redirect:/participation/listParticipations";
	}

}
