package com.proyecto.pablocalvillo.converter;

import org.dozer.DozerBeanMapper;
import org.springframework.stereotype.Component;

import com.proyecto.pablocalvillo.entity.Race;
import com.proyecto.pablocalvillo.model.RaceModel;


@Component("raceConverter")
public class RaceConverter {
	
	DozerBeanMapper mapper = new DozerBeanMapper();
	
	public RaceModel entity2model(Race race) {
		return mapper.map(race, RaceModel.class);
	}
	
	public Race model2entity(RaceModel raceModel) {
		return mapper.map(raceModel, Race.class);
	}
	

}
