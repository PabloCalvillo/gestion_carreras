package com.proyecto.pablocalvillo.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.proyecto.pablocalvillo.converter.CarConverter;
import com.proyecto.pablocalvillo.entity.Car;
import com.proyecto.pablocalvillo.model.CarModel;
import com.proyecto.pablocalvillo.repository.CarJpaRepository;
import com.proyecto.pablocalvillo.service.CarService;

@Service
public class CarServiceImpl implements CarService {
	
	// private String file_folder = ".//src//main//resources//files//";
	
	@Autowired
	@Qualifier("carJpaRepository")
	private CarJpaRepository carJpaRepository;
	
	@Autowired
	@Qualifier("carConverter")
	private CarConverter carConverter;

	@Override
	public List<CarModel> listAllCars() {
		List<CarModel> carsModel = new ArrayList<CarModel>();
		carJpaRepository.findAll().forEach(car -> {
			carsModel.add(carConverter.entity2model(car));
		});
		return carsModel;
	}

	@Override
	public Car addCar(CarModel carModel) {
		Car car = carConverter.model2entity(carModel);
		return carJpaRepository.save(car);
	}

	@Override
	public int removeCar(int id) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Car updateCar(CarModel carModel) {
		// TODO Auto-generated method stub
		return null;
	}

	

}
