package com.proyecto.pablocalvillo.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import com.proyecto.pablocalvillo.entity.Participation;
import com.proyecto.pablocalvillo.entity.QParticipation;
import com.querydsl.jpa.impl.JPAQuery;

@Repository("queryDSLParticipation")
public class QueryDSLParticipation {
	
	QParticipation qparticipation = QParticipation.participation;
	
	@PersistenceContext
	private EntityManager em;
	
	public List<Participation> listOrder() {
		
		JPAQuery<Participation> query = new JPAQuery<Participation>(em);
		
		return query.from(qparticipation).orderBy(qparticipation.idCarrera.asc(), qparticipation.posicion.asc()).fetch();
	}
	

}
