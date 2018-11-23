package com.proyecto.pablocalvillo.service;

import java.util.List;

import com.proyecto.pablocalvillo.entity.Participation;
import com.proyecto.pablocalvillo.model.ParticipationModel;

public interface ParticipationService {
	
	public abstract List<ParticipationModel> listAllParticipations();
	public abstract List<ParticipationModel> listCarParticipations(String matricula);
	public abstract List<ParticipationModel> listRaceParticipations(int idCarrera);
	public abstract Participation addParticipation(ParticipationModel participationModel);
	public abstract int removeParticipation(int id);

}
