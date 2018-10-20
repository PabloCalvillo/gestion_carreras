package com.proyecto.pablocalvillo.entity;

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
@Table(name = "coches")
public class Car {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	private int id;
	
	@ManyToMany
	@JoinTable(
			name = "participaciones",
			joinColumns = @JoinColumn(name = "idCoche"),
			inverseJoinColumns = @JoinColumn(name = "idCarrera")
			)
	private List<Race> carreras;
	
	@Column(name= "matricula", length= 20)
	@NotNull
	private String matricula;
	
	@Column(name= "marca", length= 20)
	@NotNull
	private String marca;
	
	@Column(name= "modelo", length= 20)
	@NotNull
	private String modelo;
	
	@Column(name= "color", length= 20)
	@NotNull
	private String color;
	
	@Column(name= "potencia")
	@NotNull
	private int potencia;
	
	@Column(name= "foto")
	private String foto;

	public Car() {
		super();
	}

	public Car(int id, @NotNull String matricula, @NotNull String marca, @NotNull String modelo, @NotNull String color,
			@NotNull int potencia, String foto) {
		super();
		this.id = id;
		this.matricula = matricula;
		this.marca = marca;
		this.modelo = modelo;
		this.color = color;
		this.potencia = potencia;
		this.foto = foto;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getMatricula() {
		return matricula;
	}

	public void setMatricula(String matricula) {
		this.matricula = matricula;
	}

	public String getMarca() {
		return marca;
	}

	public void setMarca(String marca) {
		this.marca = marca;
	}

	public String getModelo() {
		return modelo;
	}

	public void setModelo(String modelo) {
		this.modelo = modelo;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public int getPotencia() {
		return potencia;
	}

	public void setPotencia(int potencia) {
		this.potencia = potencia;
	}

	public String getFoto() {
		return foto;
	}

	public void setFoto(String foto) {
		this.foto = foto;
	}
	
}
