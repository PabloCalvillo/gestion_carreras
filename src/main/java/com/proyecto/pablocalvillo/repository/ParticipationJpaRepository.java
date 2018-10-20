package com.proyecto.pablocalvillo.repository;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.proyecto.pablocalvillo.entity.Participation;

@Repository("participationJpaRepository")
public interface ParticipationJpaRepository extends JpaRepository<Participation, Serializable> {}
