package com.cibertec.app.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name = "cliente")
public class Cliente implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name = "idclie")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idClie;
	
	@Column(name = "dni")
	private String dni;
	
	@Column(name = "nombre")
	private String nombre;
	
	@Column(name = "direccion_clie")
	private String direccionClie;

	@Column(name = "telefono")
	private String telefono;

	public int getIdClie() {
		return idClie;
	}

	public void setIdClie(int idClie) {
		this.idClie = idClie;
	}

	public String getDni() {
		return dni;
	}

	public void setDni(String dni) {
		this.dni = dni;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getDireccionClie() {
		return direccionClie;
	}

	public void setDireccionClie(String direccionClie) {
		this.direccionClie = direccionClie;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public Cliente() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Cliente(int idClie, String dni, String nombre, String direccionClie, String telefono) {
		super();
		this.idClie = idClie;
		this.dni = dni;
		this.nombre = nombre;
		this.direccionClie = direccionClie;
		this.telefono = telefono;
	}

	
	
	
	
	
}