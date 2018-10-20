package com.proyecto.pablocalvillo.converter;

import org.dozer.DozerBeanMapper;
import org.springframework.stereotype.Component;

import com.proyecto.pablocalvillo.entity.Car;
import com.proyecto.pablocalvillo.model.CarModel;

@Component("carConverter")
public class CarConverter {
	
	DozerBeanMapper mapper = new DozerBeanMapper();
	
	public CarModel entity2model(Car car) {
		return mapper.map(car, CarModel.class);
	}
	
	public Car model2entity(CarModel carModel) {
		return mapper.map(carModel, Car.class);
	}

}
