package com.proyecto.pablocalvillo.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.proyecto.pablocalvillo.converter.ParticipationConverter;
import com.proyecto.pablocalvillo.entity.Participation;
import com.proyecto.pablocalvillo.model.ParticipationModel;
import com.proyecto.pablocalvillo.repository.CarJpaRepository;
import com.proyecto.pablocalvillo.repository.ParticipationJpaRepository;
import com.proyecto.pablocalvillo.repository.RaceJpaRepository;
import com.proyecto.pablocalvillo.service.ParticipationService;

@Service
public class ParticipationServiceImpl implements ParticipationService {

	@Autowired
	@Qualifier("participationJpaRepository")
	private ParticipationJpaRepository participationJpaRepository;

	@Autowired
	@Qualifier("carJpaRepository")
	private CarJpaRepository carJpaRepository;

	@Autowired
	@Qualifier("raceJpaRepository")
	private RaceJpaRepository raceJpaRepository;
	
	@Autowired
	@Qualifier("participationConverter")
	private ParticipationConverter participationConverter;

	@Override
	public List<ParticipationModel> listAllParticipations() {
		List<ParticipationModel> participationsModel = participationJpaRepository.findAll().stream()
				.map(participation -> participationConverter.entity2model(participation)).collect(Collectors.toList());
		return participationsModel;
	}
	
	@Override
	public List<ParticipationModel> listParticipations(String matricula) {
		List<ParticipationModel> participationsModel = new ArrayList<ParticipationModel>();
		participationJpaRepository.findByidCoche(carJpaRepository.findByMatricula(matricula).getId()).forEach(participation -> {
			participationsModel.add(participationConverter.entity2model(participation));
		});;
		return participationsModel;
	}
	
	@Override
	public List<ParticipationModel> listRaceParticipations(int idCarrera) {
		List<ParticipationModel> participationsModel = new ArrayList<ParticipationModel>();
		participationJpaRepository.findByidCarrera(idCarrera).forEach(participation -> {
			participationsModel.add(participationConverter.entity2model(participation));
		});
		return participationsModel;
	}

	@Override
	public Participation addParticipation(ParticipationModel participationModel) {
		return participationJpaRepository.save(participationConverter.model2entity(participationModel));
	}

	@Override
	public int removeParticipation(int id) {
		participationJpaRepository.deleteById(id);
		return 0;
	}

	@Override
	public Participation updateParticipation(ParticipationModel participationModel) {
		return participationJpaRepository.save(participationConverter.model2entity(participationModel));
	}
}

