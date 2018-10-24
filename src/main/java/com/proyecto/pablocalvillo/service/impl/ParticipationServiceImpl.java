package com.proyecto.pablocalvillo.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.proyecto.pablocalvillo.converter.ParticipationConverter;
import com.proyecto.pablocalvillo.entity.Participation;
import com.proyecto.pablocalvillo.model.ParticipationModel;
import com.proyecto.pablocalvillo.repository.CarJpaRepository;
import com.proyecto.pablocalvillo.repository.ParticipationJpaRepository;
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
	@Qualifier("participationConverter")
	private ParticipationConverter participationConverter;

	@Override
	public List<ParticipationModel> listAllParticipations() {
		List<ParticipationModel> participationsModel = new ArrayList<ParticipationModel>();
		participationJpaRepository.findAll().forEach(participation -> {
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
