package com.proyecto.pablocalvillo.model;

public class ParticipationModel {
	
	private int id, idCoche, idCarrera, posicion;

	public ParticipationModel() {
		super();
	}

	public ParticipationModel(int id, int idCoche, int idCarrera, int posicion) {
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

	@Override
	public String toString() {
		return "ParticipationModel [id=" + id + ", idCoche=" + idCoche + ", idCarrera=" + idCarrera + ", posicion="
				+ posicion + "]";
	}
	
}
