package com.proyecto.pablocalvillo.model;

import java.sql.Date;

public class ParticipationModel {
	
	private int id, posicion;
	private String coche, carrera;
	private Date fecha;

	public ParticipationModel() {
		super();
	}

	public ParticipationModel(int id, String coche, String carrera, int posicion, Date fecha) {
		super();
		this.id = id;
		this.coche = coche;
		this.carrera = carrera;
		this.posicion = posicion;
		this.fecha = fecha;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCoche() {
		return coche;
	}

	public void setCoche(String coche) {
		this.coche = coche;
	}

	public String getCarrera() {
		return carrera;
	}

	public void setCarrera(String carrera) {
		this.carrera = carrera;
	}

	public int getPosicion() {
		return posicion;
	}

	public void setPosicion(int posicion) {
		this.posicion = posicion;
	}
	
	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	@Override
	public String toString() {
		return "ParticipationModel [id=" + id + ", coche=" + coche + ", carrera=" + carrera + ", posicion="
				+ posicion + "]";
	}
	
}
