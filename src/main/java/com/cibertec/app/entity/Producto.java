package com.cibertec.app.entity;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


@Entity
@Table(name = "producto")
public class Producto implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name = "idproducto")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer idProd;
	
	@Column(name = "codigo")
	private String codigo;
	
	@Column(name = "descripcion")
	private String descripcion;
		
	@Column(name = "precio_compra")
	private BigDecimal precioCompra;
	
	@Column(name = "precio_venta")
	private Double precioVenta;
	
	@Column(name = "stock")
	private int stock;

	@ManyToOne
	@JoinColumn(name= "idcate")
	private Categoria categoria;
	
	public Producto(String codigo) {
		this.codigo = codigo;
	}
	    
	public void restarExistencia(int stock) {
	    this.stock -= stock;
	}
	    
	public boolean sinExistencia() {
	    return this.stock <= 0;
	}

	public Integer getIdProd() {
		return idProd;
	}

	public void setIdProd(Integer idProd) {
		this.idProd = idProd;
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public BigDecimal getPrecioCompra() {
		return precioCompra;
	}

	public void setPrecioCompra(BigDecimal precioCompra) {
		this.precioCompra = precioCompra;
	}

	public Double getPrecioVenta() {
		return precioVenta;
	}

	public void setPrecioVenta(Double precioVenta) {
		this.precioVenta = precioVenta;
	}

	public int getStock() {
		return stock;
	}

	public void setStock(int stock) {
		this.stock = stock;
	}

	public Categoria getCategoria() {
		return categoria;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public Producto(Integer idProd, String codigo, String descripcion, BigDecimal precioCompra, Double precioVenta,
			int stock, Categoria categoria) {
		super();
		this.idProd = idProd;
		this.codigo = codigo;
		this.descripcion = descripcion;
		this.precioCompra = precioCompra;
		this.precioVenta = precioVenta;
		this.stock = stock;
		this.categoria = categoria;
	}

	public Producto() {
		super();
		// TODO Auto-generated constructor stub
	}
	public String getNombre() {
	    return descripcion; 
	}
	
	
	
	
	
}