package com.cibertec.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.cibertec.app.entity.Cliente;
import com.cibertec.app.service.ClienteService;

@Controller
@RequestMapping("/administrador/cliente")
public class ClienteController {

    @Autowired
    private ClienteService service;

    // Listar clientes
    @GetMapping
    public String listClientes(Model model) {
        model.addAttribute("clientes", service.listarTodosCliente());
        return "administrador/cliente/index";
    }

    // Formulario para crear cliente
    @GetMapping("/new")
    public String createCliente(Model model) {
        model.addAttribute("cliente", new Cliente());
        return "administrador/cliente/create";
    }

    // Guardar cliente
    @PostMapping
    public String saveCliente(@ModelAttribute Cliente cliente) {
        service.guardarCliente(cliente);
        return "redirect:/administrador/cliente";
    }

    // Formulario para editar cliente
    @GetMapping("/edit/{id}")
    public String editCliente(@PathVariable Integer id, Model model) {
        Cliente cliente = service.buscarClienteById(id);
        model.addAttribute("cliente", cliente);
        return "administrador/cliente/edit";
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
        return "redirect:/administrador/cliente";
    }

    // Eliminar cliente
    @GetMapping("/delete/{id}")
    public String deleteCliente(@PathVariable Integer id) {
        service.eliminarClienteById(id);
        return "redirect:/administrador/cliente";
    }

}
