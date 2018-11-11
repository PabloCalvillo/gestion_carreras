package com.proyecto.pablocalvillo.model;

public class CarModel {
	
	private int id, potencia;
	private String marca, modelo, matricula, color, foto;
	
	public CarModel() {
		super();
	}
	public CarModel(int id, int potencia, String marca, String modelo, String matricula, String color, String foto) {
		super();
		this.id = id;
		this.potencia = potencia;
		this.marca = marca;
		this.modelo = modelo;
		this.matricula = matricula;
		this.color = color;
		this.foto = foto;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getPotencia() {
		return potencia;
	}
	public void setPotencia(int potencia) {
		this.potencia = potencia;
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
	public String getMatricula() {
		return matricula;
	}
	public void setMatricula(String matricula) {
		this.matricula = matricula;
	}
	public String getColor() {
		return color;
	}
	public void setColor(String color) {
		this.color = color;
	}
	public String getFoto() {
		return foto;
	}
	public void setFoto(String foto) {
		this.foto = foto;
	}
	@Override
	public String toString() {
		return "CarModel [id=" + id + ", potencia=" + potencia + ", marca=" + marca + ", modelo=" + modelo
				+ ", matricula=" + matricula + ", color=" + color + ", foto=" + foto + "]";
	}
	
}
