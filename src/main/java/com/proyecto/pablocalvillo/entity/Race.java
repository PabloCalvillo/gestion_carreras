package com.proyecto.pablocalvillo.entity;

import java.sql.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "carreras")
public class Race {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	private int id;
	
	@ManyToMany
	@JoinTable(
			name = "participaciones",
			joinColumns = @JoinColumn(name = "idCarrera"),
			inverseJoinColumns = @JoinColumn(name = "idCoche")
			)
	private List<Car> coches;
	
	@Column(name = "nombre", length= 50)
	@NotNull
	private String nombre;
	
	@Column(name = "fecha")
	@NotNull
	private Date fecha;
	
	@Column(name = "ciudad", length= 50)
	@NotNull
	private String ciudad;

	public Race() {
		super();
	}

	public Race(int id, @NotNull String nombre, @NotNull Date fecha, @NotNull String ciudad) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.fecha = fecha;
		this.ciudad = ciudad;
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
		this.nombre = nombre;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public String getCiudad() {
		return ciudad;
	}

	public void setCiudad(String ciudad) {
		this.ciudad = ciudad;
	}
}
