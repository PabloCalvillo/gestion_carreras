package com.proyecto.pablocalvillo.model;

import java.sql.Date;

public class RaceModel {
	
	private int id;
	private String nombre, ciudad;
	private Date fecha;
	
	public RaceModel() {
		super();
	}

	public RaceModel(int id, String nombre, String ciudad, Date fecha) {
		super();
		this.id = id;
		this.nombre = nombre.replace(" ", "-");
		this.ciudad = ciudad;
		this.fecha = fecha;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre.replace(" ", "-");;
	}

	public String getCiudad() {
		return ciudad;
	}

	public void setCiudad(String ciudad) {
		this.ciudad = ciudad;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	@Override
	public String toString() {
		return "RaceModel [id=" + id + ", nombre=" + nombre + ", ciudad=" + ciudad + ", fecha=" + fecha + "]";
	}
}
