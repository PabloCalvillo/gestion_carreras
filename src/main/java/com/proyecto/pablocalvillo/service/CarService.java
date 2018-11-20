package com.proyecto.pablocalvillo.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.proyecto.pablocalvillo.entity.Car;
import com.proyecto.pablocalvillo.model.CarModel;

public interface CarService {
	
	public abstract List<CarModel> listAllCars();
	public abstract Car addCar(CarModel carModel);
	public abstract int removeCar(String matricula);
	public abstract Car updateCar(CarModel carModel);
	public abstract Car findByMatricula(String matricula);

}
