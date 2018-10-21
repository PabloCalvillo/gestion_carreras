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

import com.proyecto.pablocalvillo.model.RaceModel;
import com.proyecto.pablocalvillo.service.impl.RaceServiceImpl;

@Controller
@RequestMapping("/races")
public class RaceController {

	private static final String ADD_RACES_VIEW = "addRace";
	private static final String EDIT_RACES_VIEW = "editRaces";

	@Autowired
	@Qualifier("raceServiceImpl")
	private RaceServiceImpl raceServiceImpl;

	@GetMapping("/listRaces")
	public ModelAndView listRaces() {
		ModelAndView mav = new ModelAndView(ADD_RACES_VIEW);
		mav.addObject("race", new RaceModel());
		return mav;
	}

	@GetMapping("/add")
	public ModelAndView add() {
		ModelAndView mav = new ModelAndView(ADD_RACES_VIEW);
		mav.addObject("race", new RaceModel());
		return mav;
	}

	@PostMapping("/addRace")
	public String addRace(@ModelAttribute("race") RaceModel raceModel) {
		raceServiceImpl.addRace(raceModel);
		return "redirect:/races/add";
	}

	@GetMapping("/edit")
	public ModelAndView edit() {
		ModelAndView mav = new ModelAndView(EDIT_RACES_VIEW);
		mav.addObject("races", raceServiceImpl.listAllRaces());
		return mav;
	}

	@GetMapping("/removeRace")
	public String removeRace(@RequestParam(name = "id", required = true, defaultValue = "NULL") int id) {
		raceServiceImpl.removeRace(id);
		return "redirect:/races/edit";
	}

}
