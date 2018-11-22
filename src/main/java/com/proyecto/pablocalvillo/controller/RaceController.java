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
import com.proyecto.pablocalvillo.repository.QueryDSLRace;
import com.proyecto.pablocalvillo.service.CarService;
import com.proyecto.pablocalvillo.service.ParticipationService;
import com.proyecto.pablocalvillo.service.RaceService;

@Controller
@RequestMapping("/races")
public class RaceController {

	private static final String ADD_RACES_VIEW = "addRace";
	private static final String EDIT_RACES_VIEW = "editRaces";
	private static final String EDIT_RACE_VIEW = "editRace";

	@Autowired
	@Qualifier("raceServiceImpl")
	private RaceService raceService;

	@Autowired
	@Qualifier("carServiceImpl")
	private CarService carService;

	@Autowired
	@Qualifier("participationServiceImpl")
	private ParticipationService participationService;

	@Autowired
	@Qualifier("queryDSLRace")
	private QueryDSLRace queryDSLRace;

	@GetMapping("/add")
	public ModelAndView add() {
		ModelAndView mav = new ModelAndView(ADD_RACES_VIEW);
		mav.addObject("race", new RaceModel());
		return mav;
	}

	@PostMapping("/addRace")
	public String addRace(@ModelAttribute("race") RaceModel raceModel, RedirectAttributes redirectAttributes) {
		raceModel.setNombre(raceModel.getNombre().replaceAll("-", " "));
		System.out.println(raceModel.getNombre());
		if (raceService.findByNombre(raceModel.getNombre()) == null) {
			raceService.addRace(raceModel);
			redirectAttributes.addFlashAttribute("success", true);
		} else {
			redirectAttributes.addFlashAttribute("success", false);
			return "redirect:/races/add";
		}
		return "redirect:/races/add";
	}

	@PostMapping("/updateRace")
	public String updateRace(@ModelAttribute("race") RaceModel raceModel, RedirectAttributes redirectAttributes) {
		if (raceService.findByNombreAndId(raceModel.getNombre(), raceModel.getId()) != null) {
			try {
				raceService.addRace(raceModel);
				redirectAttributes.addFlashAttribute("successEdit", true);
			} catch (Exception e) {
				redirectAttributes.addFlashAttribute("successEdit", false);
				return "redirect:/races/editRace?id=" + raceModel.getId();
			}

		} else if (raceService.findByNombre(raceModel.getNombre()) == null) {
			raceService.addRace(raceModel);
			redirectAttributes.addFlashAttribute("successEdit", true);
		} else {
			redirectAttributes.addFlashAttribute("successEdit", false);
		}

		return "redirect:/races/editRace?id=" + raceModel.getId();
	}

	@GetMapping("/edit")
	public ModelAndView edit() {
		ModelAndView mav = new ModelAndView(EDIT_RACES_VIEW);
		mav.addObject("races", raceService.listAllRaces());
		return mav;
	}

	@GetMapping("/removeRace")
	public String removeRace(@RequestParam(name = "id", required = true, defaultValue = "NULL") int id,
			RedirectAttributes redirectAttributes) {
		raceService.removeRace(id);
		redirectAttributes.addFlashAttribute("success", true);
		return "redirect:/races/edit";
	}

	@GetMapping("/editRace")
	public ModelAndView editRace(@RequestParam(name = "id", required = true, defaultValue = "NULL") int id) {
		ModelAndView mav = new ModelAndView(EDIT_RACE_VIEW);
		mav.addObject("race", raceService.findById(id));
		mav.addObject("participations", participationService.listRaceParticipations(id));
		mav.addObject("participation", new ParticipationModel());
		mav.addObject("cars", carService.listAllCars());
		return mav;
	}

	@GetMapping("/removeParticipation")
	public String removeParticipation(@RequestParam Map<String, String> requestParams) throws Exception {
		participationService.removeParticipation(Integer.parseInt(requestParams.get("id")));
		return "redirect:/races/editRace?id=" + raceService.findByNombre(requestParams.get("carrera")).getId();
	}

}
