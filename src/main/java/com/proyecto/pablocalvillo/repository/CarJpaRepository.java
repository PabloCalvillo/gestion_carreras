package com.proyecto.pablocalvillo.repository;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.proyecto.pablocalvillo.entity.Car;

@Repository("carJpaRepository")
public interface CarJpaRepository extends JpaRepository<Car, Serializable> {
	
	public abstract Car findByMatricula(String matricula);
	
	public abstract Car findById(int id);
}
