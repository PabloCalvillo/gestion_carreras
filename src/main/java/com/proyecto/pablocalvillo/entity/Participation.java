package com.proyecto.pablocalvillo.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "participaciones")
public class Participation {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	@Max(999)
	private int id;
	
	@Column(name = "idCoche")
	@NotNull
	private int idCoche;
	
	@Column(name = "idCarrera")
	@NotNull
	private int idCarrera;
	
	@Column(name = "posicion")
	@NotNull
	private int posicion;

	public Participation() {
		super();
	}

	public Participation(int id, int idCoche, int idCarrera, int posicion) {
		super();
		this.id = id;
		this.idCoche = idCoche;
		this.idCarrera = idCarrera;
		this.posicion = posicion;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getIdCoche() {
		return idCoche;
	}

	public void setIdCoche(int idCoche) {
		this.idCoche = idCoche;
	}

	public int getIdCarrera() {
		return idCarrera;
	}

	public void setIdCarrera(int idCarrera) {
		this.idCarrera = idCarrera;
	}

	public int getPosicion() {
		return posicion;
	}

	public void setPosicion(int posicion) {
		this.posicion = posicion;
	}
	
}
