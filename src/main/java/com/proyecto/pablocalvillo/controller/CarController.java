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

import com.proyecto.pablocalvillo.model.CarModel;
import com.proyecto.pablocalvillo.service.impl.CarServiceImpl;
import com.proyecto.pablocalvillo.service.impl.RaceServiceImpl;

@Controller
@RequestMapping("/cars")
public class CarController {

	private static final String EDIT_CARS_VIEW = "editCars";
	private static final String ADD_CAR_VIEW = "addCar";
	private static final String EDIT_CAR_VIEW = "editCar";

	@Autowired
	@Qualifier("raceServiceImpl")
	private RaceServiceImpl raceServiceImpl;

	@Autowired
	@Qualifier("carServiceImpl")
	private CarServiceImpl carServiceImpl;

	@GetMapping("/listCars")
	public ModelAndView listAllCars() {
		ModelAndView mav = new ModelAndView(EDIT_CARS_VIEW);
		mav.addObject("cars", carServiceImpl.listAllCars());
		return mav;
	}

	@GetMapping("/add")
	public ModelAndView add() {
		ModelAndView mav = new ModelAndView(ADD_CAR_VIEW);
		mav.addObject("car", new CarModel());
		return mav;
	}

	@PostMapping("/addCar")
	public String addCar(@ModelAttribute("car") CarModel carModel) {
		try {
			carServiceImpl.addCar(carModel);
		} catch (Exception e) {
			return "redirect:/cars/add";
		}
		return "redirect:/cars/add";
	}

	@PostMapping("/updateCar")
	public String updateCar(@ModelAttribute("car") CarModel carModel) {
		carServiceImpl.updateCar(carModel);
		return "redirect:/cars/listCars";
	}

	@GetMapping("/editCar")
	public ModelAndView editCar(
			@RequestParam(name = "matricula", required = true, defaultValue = "NULL") String matricula) {
		ModelAndView mav = new ModelAndView(EDIT_CAR_VIEW);
		mav.addObject(carServiceImpl.findByMatricula(matricula));
		mav.addObject("races", raceServiceImpl.listAllRaces());
		return mav;
	}

	@GetMapping("/removeCar")
	public String removeCar(
			@RequestParam(name = "matricula", required = true, defaultValue = "NULL") String matricula) {
		carServiceImpl.removeCar(matricula);
		return "redirect:/cars/listCars";
	}

}
