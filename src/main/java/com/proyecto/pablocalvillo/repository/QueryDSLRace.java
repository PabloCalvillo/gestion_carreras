package com.proyecto.pablocalvillo.repository;

import java.sql.Date;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import com.proyecto.pablocalvillo.entity.Car;
import com.proyecto.pablocalvillo.entity.QRace;
import com.proyecto.pablocalvillo.entity.Race;
import com.querydsl.jpa.impl.JPAQuery;

@Repository("queryDSLRace")
public class QueryDSLRace {
	
	private QRace qrace = QRace.race;
	
	@PersistenceContext
	private EntityManager em;
	
	public String getName(int id) {
		JPAQuery<Race> query = new JPAQuery<Race>(em);
		
		return query.select(qrace.nombre).from(qrace).where(qrace.id.eq(id)).fetchOne();
	}
	
	public int getId(String name) {
		JPAQuery<Race> query = new JPAQuery<Race>(em);
		
		return query.select(qrace.id).from(qrace).where(qrace.nombre.eq(name)).fetchOne();
	}
	
	public Date getFecha(int id) {
		JPAQuery<Race> query = new JPAQuery<Race>(em);
		
		return query.select(qrace.fecha).from(qrace).where(qrace.id.eq(id)).fetchOne();
	}
	
	public Race findByNombreAndId(String nombre, int id) {
		JPAQuery<Car> query = new JPAQuery<Car>(em);
		
		return query.select(qrace).from(qrace).where(qrace.nombre.eq(nombre)).where(qrace.id.eq(id)).fetchOne();
	}

}
