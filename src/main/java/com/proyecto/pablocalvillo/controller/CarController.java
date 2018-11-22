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
import com.proyecto.pablocalvillo.service.CarService;
import com.proyecto.pablocalvillo.service.ParticipationService;
import com.proyecto.pablocalvillo.service.RaceService;
import com.proyecto.pablocalvillo.service.impl.FileServiceImpl;

@Controller
@RequestMapping("/cars")
public class CarController {

	private static final String EDIT_CARS_VIEW = "editCars";
	private static final String ADD_CAR_VIEW = "addCar";
	private static final String EDIT_CAR_VIEW = "editCar";

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
	@Qualifier("fileServiceImpl")
	FileServiceImpl fileServiceImpl;

	@Autowired
	@Qualifier("queryDSLCar")
	QueryDSLCar queryDSLCar;

	@GetMapping("/listCars")
	public ModelAndView listAllCars() {
		ModelAndView mav = new ModelAndView(EDIT_CARS_VIEW);
		mav.addObject("cars", carService.listAllCars());
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
		if (carService.findByMatricula(carModel.getMatricula()) == null) {
			try {
				carModel.setFoto(fileServiceImpl.saveFile(file));
			} catch (IOException e) {
				e.printStackTrace();
			}
			carService.addCar(carModel);
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
		if (carService.findByMatricula(carModel.getMatricula()) == null) {
			try {
				if (!file.isEmpty()) {
					fileServiceImpl.removeFile(queryDSLCar.getFotoId(carModel.getId()));
					carModel.setFoto(fileServiceImpl.saveFile(file));
				} else {
					carModel.setFoto(queryDSLCar.getFotoId(carModel.getId()));
				}
				carService.addCar(carModel);
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
		mav.addObject(carService.findByMatricula(matricula));
		mav.addObject("races", raceService.listAllRaces());
		mav.addObject("participation", new ParticipationModel());
		mav.addObject("participations", participationService.listParticipations(matricula));
		return mav;
	}

	@GetMapping("/removeCar")
	public String removeCar(
			@RequestParam(name = "matricula", required = true, defaultValue = "NULL") String matricula, RedirectAttributes redirectAttributes) {
		if (queryDSLCar.getFotoMatricula(matricula) != "") {
			fileServiceImpl.removeFile(queryDSLCar.getFotoMatricula(matricula));
		}
		carService.removeCar(matricula);
		redirectAttributes.addFlashAttribute("success", true);
		return "redirect:/cars/listCars";
	}

	@GetMapping("/removeParticipation")
	public String removeParticipation(@RequestParam Map<String, String> requestParams) throws Exception {
		participationService.removeParticipation(Integer.parseInt(requestParams.get("id")));
		return "redirect:/cars/editCar?matricula=" + requestParams.get("coche");
	}
}
