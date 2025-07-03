package com.cibertec.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.cibertec.app.entity.Cliente;
import com.cibertec.app.service.ClienteService;

@Controller
@RequestMapping("/vendedor/cliente")
public class ClienteVenController {

    @Autowired
    private ClienteService service;

    // Listar clientes
    @GetMapping
    public String listClientes(Model model) {
        model.addAttribute("clientes", service.listarTodosCliente());
        return "vendedor/cliente/index";
    }

    // Formulario para crear cliente
    @GetMapping("/new")
    public String createCliente(Model model) {
        model.addAttribute("cliente", new Cliente());
        return "vendedor/cliente/create";
    }

    // Guardar cliente
    @PostMapping
    public String saveCliente(@ModelAttribute Cliente cliente) {
        service.guardarCliente(cliente);
        return "redirect:/vendedor/cliente";
    }

    // Formulario para editar cliente
    @GetMapping("/edit/{id}")
    public String editCliente(@PathVariable Integer id, Model model) {
        Cliente cliente = service.buscarClienteById(id);
        model.addAttribute("cliente", cliente);
        return "vendedor/cliente/edit";
    }

    // Actualizar cliente
    @PostMapping("/update/{id}")
    public String updateCliente(@PathVariable Integer id, @ModelAttribute Cliente cliente) {
        Cliente existente = service.buscarClienteById(id);
        existente.setDni(cliente.getDni());
        existente.setNombre(cliente.getNombre());
        existente.setDireccionClie(cliente.getDireccionClie());
        existente.setTelefono(cliente.getTelefono());
        service.actualizarCliente(existente);
        return "redirect:/vendedor/cliente";
    }

    // Eliminar cliente
    @GetMapping("/delete/{id}")
    public String deleteCliente(@PathVariable Integer id) {
        service.eliminarClienteById(id);
        return "redirect:/vendedor/cliente";
    }

	
	
}
