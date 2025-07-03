package com.cibertec.app.service;

import java.util.List;

import com.cibertec.app.entity.Proveedor;



public interface ProveedorService  {

	public Proveedor guardarProveedor(Proveedor userEntity);

	public List<Proveedor> listarTodosProveedor();

	public Proveedor actualizarProveedor(Proveedor userEntity);

	public void eliminarProveedorById(Integer idProd);
	
	public Proveedor buscarProveedorById(Integer idProd);
	
	
	
}
