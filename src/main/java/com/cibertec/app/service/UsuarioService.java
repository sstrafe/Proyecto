package com.cibertec.app.service;

import java.util.List;

import com.cibertec.app.entity.Rol;
import com.cibertec.app.entity.Usuario;



public interface UsuarioService {

    public Usuario guardarUsuario(Usuario registroDTO);
	
	public List<Usuario> listarTodosUsuario();
	
	public Usuario validarCredenciales(String username, String clave);
	
	public Rol validadRol(String descripcion);
	
	public Usuario buscarByUsuario(String username);
}
