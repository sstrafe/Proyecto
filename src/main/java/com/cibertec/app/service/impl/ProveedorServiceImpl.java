package com.cibertec.app.service.impl;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cibertec.app.entity.Proveedor;
import com.cibertec.app.repository.ProveedorRepository;
import com.cibertec.app.service.ProveedorService;


@Service
public class ProveedorServiceImpl implements ProveedorService {

	@Autowired
	ProveedorRepository proveedorRepository;
	
	  
	@Override
	public Proveedor guardarProveedor(Proveedor userEntity) {
		return proveedorRepository.save(userEntity);
	}
	
	@Override
	public List<Proveedor> listarTodosProveedor() {
		return proveedorRepository.findAll();
	}

	@Override
	public Proveedor actualizarProveedor(Proveedor userEntity) {
		return proveedorRepository.save(userEntity);
	}

	@Override
	public void eliminarProveedorById(Integer idProd) {
		proveedorRepository.deleteById(idProd);
	}

	@Override
	public Proveedor buscarProveedorById(Integer idProd) {
		return proveedorRepository.findById(idProd).get();
	}


	
	
	
	
}
