package com.proyecto.pablocalvillo.converter;

import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.proyecto.pablocalvillo.entity.Participation;
import com.proyecto.pablocalvillo.model.ParticipationModel;
import com.proyecto.pablocalvillo.repository.CarJpaRepository;
import com.proyecto.pablocalvillo.repository.QueryDSLCar;
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
	@Qualifier("queryDSLCar")
	private QueryDSLCar queryDSLCar;
	
	@Autowired
	@Qualifier("queryDSLRace")
	private QueryDSLRace queryDSLRace;
	
	public ParticipationModel entity2model(Participation participation) {
		ParticipationModel participationModel = new ParticipationModel();
		participationModel.setId(participation.getId());
		participationModel.setCarrera(queryDSLRace.find(participation.getIdCarrera()).getNombre());
		participationModel.setCoche(queryDSLCar.find(participation.getIdCoche()).getMatricula());
		participationModel.setPosicion(participation.getPosicion());
		participationModel.setFecha(raceJpaRepository.findById(participation.getIdCarrera()).get().getFecha());
		return participationModel;
	}
	
	public Participation model2entity(ParticipationModel participationModel) {
		Participation participation = new Participation();
		participation.setId(participation.getId());
		participation.setIdCarrera((raceJpaRepository.findByNombre(participationModel.getCarrera()).getId()));
		participation.setIdCoche(carJpaRepository.findByMatricula(participationModel.getCoche()).getId());
		participation.setPosicion(participationModel.getPosicion());
		return participation;
		
	}
}
