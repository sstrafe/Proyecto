package com.cibertec.app.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/cliente/home")
public class ClienteUsuarioController {
	
	
	 @GetMapping
	    public String listClientes(Model model) {
	        
	        return "cliente/home/index";
	    }
	
}
