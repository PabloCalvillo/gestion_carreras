package com.proyecto.pablocalvillo.controller;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.proyecto.pablocalvillo.model.CarModel;
import com.proyecto.pablocalvillo.service.impl.CarServiceImpl;

@Controller
@RequestMapping("/cars")
public class CarController {
	private static final String CARS_VIEW = "cars";
	@Autowired
	@Qualifier("carServiceImpl")
	private CarServiceImpl carServiceImpl;
	
	@GetMapping("/listCars")
	public ModelAndView listAllCars() {
		ModelAndView mav = new ModelAndView(CARS_VIEW);
		mav.addObject("car", new CarModel());
		mav.addObject("cars", carServiceImpl.listAllCars());
		return mav;
	}
	
	@PostMapping("/addCar")
	public String addCar(@ModelAttribute("car") CarModel carModel) {
		carServiceImpl.addCar(carModel);
		return "redirect:/cars/listCars";
	}
}
