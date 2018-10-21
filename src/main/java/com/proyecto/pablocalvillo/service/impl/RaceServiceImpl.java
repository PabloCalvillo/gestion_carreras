package com.proyecto.pablocalvillo.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.proyecto.pablocalvillo.converter.RaceConverter;
import com.proyecto.pablocalvillo.entity.Race;
import com.proyecto.pablocalvillo.model.RaceModel;
import com.proyecto.pablocalvillo.repository.RaceJpaRepository;
import com.proyecto.pablocalvillo.service.RaceService;

@Service
public class RaceServiceImpl implements RaceService {
	
	@Autowired
	@Qualifier("raceJpaRepository")
	private RaceJpaRepository raceJpaRepository;
	
	@Autowired
	@Qualifier("raceConverter")
	private RaceConverter raceConverter;

	@Override
	public List<RaceModel> listAllRaces() {
		List<RaceModel> racesModel = new ArrayList<RaceModel>();
		raceJpaRepository.findAll().forEach(race -> {
			racesModel.add(raceConverter.entity2model(race));
		});
		return racesModel;
	}

	@Override
	public Race addRace(RaceModel raceModel) {
		return raceJpaRepository.save(raceConverter.model2entity(raceModel));
	}

	@Override
	public int removeRace(int id) {
		raceJpaRepository.deleteById(id);
		return 0;
	}

	@Override
	public Race updateRace(RaceModel raceModel) {
		// TODO Auto-generated method stub
		return null;
	}

}
