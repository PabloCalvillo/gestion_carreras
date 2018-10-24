package com.proyecto.pablocalvillo.service;

import java.util.List;

import com.proyecto.pablocalvillo.entity.Participation;
import com.proyecto.pablocalvillo.model.ParticipationModel;

public interface ParticipationService {
	
	public abstract List<ParticipationModel> listAllParticipations();
	public abstract Participation addParticipation(ParticipationModel participationModel);
	public abstract int removeParticipation(int id);
	public abstract Participation updateParticipation(ParticipationModel participationModel);

}
