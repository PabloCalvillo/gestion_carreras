package com.proyecto.pablocalvillo.repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import com.proyecto.pablocalvillo.entity.Car;
import com.proyecto.pablocalvillo.entity.QCar;
import com.querydsl.jpa.impl.JPAQuery;

@Repository("queryDSLCar")
public class QueryDSLCar {

	private QCar qcar = QCar.car;

	@PersistenceContext
	private EntityManager em;

	public String getMatricula(int id) {
		JPAQuery<Car> query = new JPAQuery<Car>(em);

		return query.select(qcar.matricula).from(qcar).where(qcar.id.eq(id)).fetchOne();
	}

	public String getFotoId(int id) {
		JPAQuery<Car> query = new JPAQuery<Car>(em);

		return query.select(qcar.foto).from(qcar).where(qcar.id.eq(id)).fetchOne();
	}

	public String getFotoMatricula(String matricula) {
		JPAQuery<Car> query = new JPAQuery<Car>(em);

		return query.select(qcar.foto).from(qcar).where(qcar.matricula.eq(matricula)).fetchOne();
	}

	public Car findByMatriculaAndId(String matricula, int id) {
		JPAQuery<Car> query = new JPAQuery<Car>(em);
		
		return query.select(qcar).from(qcar).where(qcar.matricula.eq(matricula)).where(qcar.id.eq(id)).fetchOne();
	}
}
