package com.cibertec.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.cibertec.app.entity.Proveedor;
import com.cibertec.app.service.ProveedorService;

@Controller
@RequestMapping("/vendedor/proveedor")
public class ProveedorVenController {

	
    @Autowired
    private ProveedorService proveedorService;

    @GetMapping
    public String listProveedor(Model model) {
        model.addAttribute("proveedor", proveedorService.listarTodosProveedor());
        return "vendedor/proveedor/index";
    }

    @GetMapping("/new")
    public String createProveedor(Model model) {
        model.addAttribute("proveedor", new Proveedor());
        return "vendedor/proveedor/create";
    }

    @PostMapping("/guardar")
    public String saveProveedor(@ModelAttribute Proveedor proveedor) {
        proveedorService.guardarProveedor(proveedor);
        return "redirect:/vendedor/proveedor";
    }

    @GetMapping("/edit/{id}")
    public String editProveedor(@PathVariable Integer id, Model model) {
        model.addAttribute("proveedor", proveedorService.buscarProveedorById(id));
        return "vendedor/proveedor/edit";
    }

    @PostMapping("/update/{id}")
    public String updateProveedor(@PathVariable Integer id, @ModelAttribute Proveedor proveedor) {
        Proveedor existente = proveedorService.buscarProveedorById(id);
        existente.setNomprov(proveedor.getNomprov());
        existente.setTfnprov(proveedor.getTfnprov());
        existente.setDirprov(proveedor.getDirprov());
        proveedorService.actualizarProveedor(existente);
        return "redirect:/vendedor/proveedor";
    }

    @GetMapping("/delete/{id}")
    public String deleteProveedor(@PathVariable Integer id) {
        proveedorService.eliminarProveedorById(id);
        return "redirect:/vendedor/proveedor";
    }
	
}
