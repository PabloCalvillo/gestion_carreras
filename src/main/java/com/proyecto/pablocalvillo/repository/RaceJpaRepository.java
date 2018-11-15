package com.proyecto.pablocalvillo.repository;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.proyecto.pablocalvillo.entity.Race;

@Repository("raceJpaRepository")
public interface RaceJpaRepository extends JpaRepository<Race, Serializable> {
	
	public abstract Race findByNombre(String nombre);
	public abstract List<Race> findByCiudad(String ciudad);
	public abstract Race findById(int id);
}
