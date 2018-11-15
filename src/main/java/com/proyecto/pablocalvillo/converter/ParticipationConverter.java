package com.proyecto.pablocalvillo.converter;

import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.proyecto.pablocalvillo.entity.Participation;
import com.proyecto.pablocalvillo.model.ParticipationModel;
import com.proyecto.pablocalvillo.repository.CarJpaRepository;
import com.proyecto.pablocalvillo.repository.QueryDSLRace;
import com.proyecto.pablocalvillo.repository.RaceJpaRepository;


@Component("participationConverter")
public class ParticipationConverter {

	DozerBeanMapper mapper = new DozerBeanMapper();
	
	@Autowired
	@Qualifier("raceJpaRepository")
	private RaceJpaRepository raceJpaRepository;
	
	@Autowired
	@Qualifier("carJpaRepository")
	private CarJpaRepository carJpaRepository;
	
	@Autowired
	@Qualifier("queryDSLRace")
	private QueryDSLRace queryDSLRace;
	
	public ParticipationModel entity2model(Participation participation) {
		ParticipationModel participationModel = new ParticipationModel();
		participationModel.setId(participation.getId());
		participationModel.setCarrera(queryDSLRace.getName(participation.getIdCarrera()));
		participationModel.setCoche(carJpaRepository.findById(participation.getIdCoche()).getMatricula());
		participationModel.setPosicion(participation.getPosicion());
		participationModel.setFecha(queryDSLRace.getFecha(participation.getIdCarrera()));
		return participationModel;
	}
	
	public Participation model2entity(ParticipationModel participationModel) {
		Participation participation = new Participation();
		participation.setId(participationModel.getId());
		participation.setIdCarrera(queryDSLRace.getId(participationModel.getCarrera()));
		participation.setIdCoche(carJpaRepository.findByMatricula(participationModel.getCoche()).getId());
		participation.setPosicion(participationModel.getPosicion());
		return participation;
		
	}
}
