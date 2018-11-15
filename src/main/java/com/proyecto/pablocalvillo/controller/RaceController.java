package com.proyecto.pablocalvillo.controller;

import java.util.Map;

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
import com.proyecto.pablocalvillo.model.RaceModel;
import com.proyecto.pablocalvillo.service.impl.CarServiceImpl;
import com.proyecto.pablocalvillo.service.impl.ParticipationServiceImpl;
import com.proyecto.pablocalvillo.service.impl.RaceServiceImpl;

@Controller
@RequestMapping("/races")
public class RaceController {

	private static final String ADD_RACES_VIEW = "addRace";
	private static final String EDIT_RACES_VIEW = "editRaces";
	private static final String EDIT_RACE_VIEW = "editRace";
	private static final String FIND_RACES_VIEW = "findRaces";

	@Autowired
	@Qualifier("raceServiceImpl")
	private RaceServiceImpl raceServiceImpl;

	@Autowired
	@Qualifier("carServiceImpl")
	private CarServiceImpl carServiceImpl;

	@Autowired
	@Qualifier("participationServiceImpl")
	ParticipationServiceImpl participationServiceImpl;

	@GetMapping("/add")
	public ModelAndView add() {
		ModelAndView mav = new ModelAndView(ADD_RACES_VIEW);
		mav.addObject("race", new RaceModel());
		return mav;
	}

	@PostMapping("/addRace")
	public String addRace(@ModelAttribute("race") RaceModel raceModel, RedirectAttributes redirectAttributes) {
		if (raceServiceImpl.findByName(raceModel.getNombre()) == null) {
			raceServiceImpl.addRace(raceModel);
			redirectAttributes.addFlashAttribute("success", true);
		} else {
			redirectAttributes.addFlashAttribute("success", false);
			return "redirect:/races/add";
		}
		return "redirect:/races/add";
	}

	@PostMapping("/updateRace")
	public String updateRace(@ModelAttribute("race") RaceModel raceModel, RedirectAttributes redirectAttributes) {
		try {
			raceServiceImpl.addRace(raceModel);
			redirectAttributes.addFlashAttribute("successEdit", true);
		} catch (Exception e) {
			redirectAttributes.addFlashAttribute("successEdit", false);
			return "redirect:/races/editRace?id=" + raceModel.getId();
		}
		return "redirect:/races/editRace?id=" + raceModel.getId();
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

	@GetMapping("/editRace")
	public ModelAndView editRace(@RequestParam(name = "id", required = true, defaultValue = "NULL") int id) {
		ModelAndView mav = new ModelAndView(EDIT_RACE_VIEW);
		mav.addObject("race", raceServiceImpl.findById(id));
		mav.addObject("participations", participationServiceImpl.listRaceParticipations(id));
		mav.addObject("participation", new ParticipationModel());
		mav.addObject("cars", carServiceImpl.listAllCars());
		return mav;
	}

	@GetMapping("/removeParticipation")
	public String removeParticipation(@RequestParam Map<String, String> requestParams) throws Exception {
		participationServiceImpl.removeParticipation(Integer.parseInt(requestParams.get("id")));
		return "redirect:/races/editRace?id=" + raceServiceImpl.findByName(requestParams.get("carrera")).getId();
	}

	@GetMapping("/find")
	public ModelAndView findCity(@RequestParam(name = "ciudad", required = true, defaultValue = "NULL") String ciudad) {
		ModelAndView mav = new ModelAndView(FIND_RACES_VIEW);
		mav.addObject("races", raceServiceImpl.findByCiudad(ciudad));
		return mav;
	}

}
