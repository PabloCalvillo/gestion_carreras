package com.proyecto.pablocalvillo.model;

public class ParticipationModel {
	
	private int id, posicion;
	private String coche, carrera;

	public ParticipationModel() {
		super();
	}

	public ParticipationModel(int id, String coche, String carrera, int posicion) {
		super();
		this.id = id;
		this.coche = coche;
		this.carrera = carrera;
		this.posicion = posicion;
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

	@Override
	public String toString() {
		return "ParticipationModel [id=" + id + ", coche=" + coche + ", carrera=" + carrera + ", posicion="
				+ posicion + "]";
	}
	
}
