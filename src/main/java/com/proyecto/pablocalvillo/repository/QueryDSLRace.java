package com.proyecto.pablocalvillo.repository;

import java.sql.Date;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import com.proyecto.pablocalvillo.entity.QRace;
import com.proyecto.pablocalvillo.entity.Race;
import com.querydsl.jpa.impl.JPAQuery;

@Repository("queryDSLRace")
public class QueryDSLRace {
	
	private QRace qrace = QRace.race;
	
	@PersistenceContext
	private EntityManager em;
	
	public Race find(int id) {
		
		JPAQuery<Race> query = new JPAQuery<Race>(em);
		
		return query.select(qrace).from(qrace).where(qrace.id.eq(id)).fetchOne();
	}
	
	public Date getDate(int id) {
		
		JPAQuery<Race> query = new JPAQuery<Race>(em);
		
		return (Date) query.select(qrace.fecha).from(qrace).where(qrace.id.eq(id)).fetchOne();
	}

}
