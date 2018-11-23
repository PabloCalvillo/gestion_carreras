package com.proyecto.pablocalvillo.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.proyecto.pablocalvillo.converter.CarConverter;
import com.proyecto.pablocalvillo.entity.Car;
import com.proyecto.pablocalvillo.model.CarModel;
import com.proyecto.pablocalvillo.repository.CarJpaRepository;
import com.proyecto.pablocalvillo.repository.QueryDSLCar;
import com.proyecto.pablocalvillo.service.CarService;

@Service
public class CarServiceImpl implements CarService {
	
	@Autowired
	@Qualifier("carJpaRepository")
	private CarJpaRepository carJpaRepository;
	
	@Autowired
	@Qualifier("carConverter")
	private CarConverter carConverter;
	
	@Autowired
	@Qualifier("queryDSLCar")
	private QueryDSLCar queryDSLCar;

	@Override
	public List<CarModel> listAllCars() {
		List<CarModel> carsModel = carJpaRepository.findAll().stream()
				.map(car -> carConverter.entity2model(car)).collect(Collectors.toList());
		return carsModel;
	}

	@Override
	public Car addCar(CarModel carModel) {
		return carJpaRepository.save(carConverter.model2entity(carModel));
	}

	@Override
	public int removeCar(String matricula) {
		carJpaRepository.delete(carJpaRepository.findByMatricula(matricula));
		return 0;
	}

	@Override
	public Car updateCar(CarModel carModel) {
		return carJpaRepository.save(carConverter.model2entity(carModel));
	}

	@Override
	public Car findByMatricula(String matricula) {
		return carJpaRepository.findByMatricula(matricula);
	}

	@Override
	public Car findById(int id) {
		return carJpaRepository.findById(id);
	}
}
