package com.proyecto.pablocalvillo.controller;

import java.io.IOException;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.proyecto.pablocalvillo.model.CarModel;
import com.proyecto.pablocalvillo.model.ParticipationModel;
import com.proyecto.pablocalvillo.repository.QueryDSLCar;
import com.proyecto.pablocalvillo.service.impl.CarServiceImpl;
import com.proyecto.pablocalvillo.service.impl.FileServiceImpl;
import com.proyecto.pablocalvillo.service.impl.ParticipationServiceImpl;
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

	@Autowired
	@Qualifier("participationServiceImpl")
	ParticipationServiceImpl participationServiceImpl;

	@Autowired
	@Qualifier("fileServiceImpl")
	FileServiceImpl fileServiceImpl;

	@Autowired
	@Qualifier("queryDSLCar")
	QueryDSLCar queryDSLCar;

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
	public String addCar(@ModelAttribute("car") CarModel carModel, @ModelAttribute("file") MultipartFile file,
			RedirectAttributes redirectAttributes) {
		if (carServiceImpl.findByMatricula(carModel.getMatricula()) == null) {
			try {
				carModel.setFoto(fileServiceImpl.saveFile(file));
			} catch (IOException e) {
				e.printStackTrace();
			}
			carServiceImpl.addCar(carModel);
			redirectAttributes.addFlashAttribute("success", true);
		} else {
			redirectAttributes.addFlashAttribute("success", false);
			return "redirect:/cars/add";

		}

		return "redirect:/cars/add";
	}

	@PostMapping("/updateCar")
	public String updateCar(@ModelAttribute("car") CarModel carModel, @ModelAttribute("file") MultipartFile file,
			RedirectAttributes redirectAttributes) {
		if (carServiceImpl.findByMatricula(carModel.getMatricula()) == null) {
			try {
				if (!file.isEmpty()) {
					fileServiceImpl.removeFile(queryDSLCar.getFoto(carModel.getMatricula()));
					carModel.setFoto(fileServiceImpl.saveFile(file));
				} else {
					carModel.setFoto(queryDSLCar.getFoto(carModel.getMatricula()));
				}
				carServiceImpl.addCar(carModel);
				redirectAttributes.addFlashAttribute("successEdit", true);

			} catch (Exception e) {
				redirectAttributes.addFlashAttribute("successEdit", false);
				return "redirect:/cars/editCar?matricula=" + carModel.getMatricula();
			}
		} else {
			redirectAttributes.addFlashAttribute("success", false);
		}
		return "redirect:/cars/editCar?matricula=" + carModel.getMatricula();

	}

	@GetMapping("/editCar")
	public ModelAndView editCar(
			@RequestParam(name = "matricula", required = true, defaultValue = "NULL") String matricula) {
		ModelAndView mav = new ModelAndView(EDIT_CAR_VIEW);
		mav.addObject(carServiceImpl.findByMatricula(matricula));
		mav.addObject("races", raceServiceImpl.listAllRaces());
		mav.addObject("participation", new ParticipationModel());
		mav.addObject("participations", participationServiceImpl.listParticipations(matricula));
		return mav;
	}

	@GetMapping("/removeCar")
	public String removeCar(
			@RequestParam(name = "matricula", required = true, defaultValue = "NULL") String matricula) {
		if (queryDSLCar.getFoto(matricula) != "") {
			fileServiceImpl.removeFile(queryDSLCar.getFoto(matricula));
		}
		carServiceImpl.removeCar(matricula);
		return "redirect:/cars/listCars";
	}

	@GetMapping("/removeParticipation")
	public String removeParticipation(@RequestParam Map<String, String> requestParams) throws Exception {
		participationServiceImpl.removeParticipation(Integer.parseInt(requestParams.get("id")));
		return "redirect:/cars/editCar?matricula=" + requestParams.get("coche");
	}
}
