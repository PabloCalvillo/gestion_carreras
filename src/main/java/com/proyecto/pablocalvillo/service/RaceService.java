package com.proyecto.pablocalvillo.service;

import java.util.List;

import com.proyecto.pablocalvillo.entity.Race;
import com.proyecto.pablocalvillo.model.RaceModel;

public interface RaceService {
	
	public abstract List<RaceModel> listAllRaces();
	public abstract Race addRace(RaceModel raceModel);
	public abstract int removeRace(int id);
	public abstract Race updateRace(RaceModel raceModel);
	public abstract Race findById(int id);
	public abstract Race findByNombre(String nombre);
	public abstract Race findByNombreAndId(String nombre, int id);
}
