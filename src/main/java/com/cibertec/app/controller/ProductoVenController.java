package com.cibertec.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.cibertec.app.entity.Producto;
import com.cibertec.app.service.CategoriaService;
import com.cibertec.app.service.ProductoService;

@Controller
@RequestMapping("/vendedor/producto")
public class ProductoVenController {
	

    @Autowired
    private ProductoService productoService;

    @Autowired
    private CategoriaService categoriaService;

    // Mostrar lista de productos
    @GetMapping
    public String listProductos(Model model) {
        model.addAttribute("productos", productoService.listarTodosProductos());
        model.addAttribute("categoriaList", categoriaService.listarTodosCategoria());
        return "vendedor/producto/index";
    }

    // Mostrar formulario de nuevo producto
    @GetMapping("/new")
    public String createProducto(Model model) {
        model.addAttribute("producto", new Producto());
        model.addAttribute("categoriaList", categoriaService.listarTodosCategoria());
        return "vendedor/producto/create";
    }

    // Guardar nuevo producto
    @PostMapping
    public String saveProducto(Producto producto) {
        productoService.guardarProducto(producto);
        return "redirect:/vendedor/producto";
    }

    // Mostrar formulario de edición
    @GetMapping("/edit/{id}")
    public String editProducto(@PathVariable Integer id, Model model) {
        Producto producto = productoService.buscarProductoById(id);
        model.addAttribute("producto", producto);
        model.addAttribute("categoriaList", categoriaService.listarTodosCategoria());
        return "vendedor/producto/edit";
    }

    // Actualizar producto
    @PostMapping("/update/{id}")
    public String updateProducto(@PathVariable Integer id, Producto producto) {
        Producto existentProducto = productoService.buscarProductoById(id);
        existentProducto.setIdProd(id);
        existentProducto.setDescripcion(producto.getDescripcion());
        existentProducto.setPrecioVenta(producto.getPrecioVenta());
        existentProducto.setPrecioCompra(producto.getPrecioCompra());
        existentProducto.setStock(producto.getStock());
        existentProducto.setCategoria(producto.getCategoria());
        productoService.actualizarProducto(existentProducto);
        return "redirect:/vendedor/producto";
    }

    // Eliminar producto
    @GetMapping("/delete/{id}")
    public String deleteProducto(@PathVariable Integer id) {
        productoService.eliminarProductoById(id);
        return "redirect:/vendedor/producto";
    }
	

}
