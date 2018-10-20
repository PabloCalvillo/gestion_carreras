package com.proyecto.pablocalvillo.converter;

import org.dozer.DozerBeanMapper;
import org.springframework.stereotype.Component;

import com.proyecto.pablocalvillo.entity.Participation;
import com.proyecto.pablocalvillo.model.ParticipationModel;


@Component("participationConverter")
public class ParticipationConverter {

	DozerBeanMapper mapper = new DozerBeanMapper();
	
	public ParticipationModel entity2model(Participation participation) {
		return mapper.map(participation, ParticipationModel.class);
	}
	
	public Participation model2entity(ParticipationModel participationModel) {
		return mapper.map(participationModel, Participation.class);
	}
}
