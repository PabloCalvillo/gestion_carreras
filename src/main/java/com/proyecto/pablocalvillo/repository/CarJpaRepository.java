package com.proyecto.pablocalvillo.repository;

import java.io.Serializable;

import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

import com.proyecto.pablocalvillo.entity.Car;

@Repository("carJpaRepository")
public interface CarJpaRepository extends JpaRepository<Car, Serializable> {}
