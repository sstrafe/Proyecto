package com.cibertec.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.cibertec.app.entity.Producto;
import com.cibertec.app.service.CategoriaService;
import com.cibertec.app.service.ProductoService;

@Controller
public class ProductoController {

    @Autowired
    private ProductoService productoService;

    @Autowired
    private CategoriaService categoriaService;

    // Mostrar lista de productos
    @GetMapping("/administrador/producto")
    public String listProductos(Model model) {
        model.addAttribute("productos", productoService.listarTodosProductos());
        model.addAttribute("categoriaList", categoriaService.listarTodosCategoria());
        return "administrador/producto/index";
    }

    // Mostrar formulario de nuevo producto
    @GetMapping("/administrador/producto/new")
    public String createProducto(Model model) {
        model.addAttribute("producto", new Producto());
        model.addAttribute("categoriaList", categoriaService.listarTodosCategoria());
        return "administrador/producto/create";
    }

    // Guardar nuevo producto
    @PostMapping("/administrador/producto/guardar")
    public String saveProducto(Producto producto) {
        productoService.guardarProducto(producto);
        return "redirect:/administrador/producto";
    }

    // Mostrar formulario de edición
    @GetMapping("/administrador/producto/edit/{id}")
    public String editProducto(@PathVariable Integer id, Model model) {
        Producto producto = productoService.buscarProductoById(id);
        model.addAttribute("producto", producto);
        model.addAttribute("categoriaList", categoriaService.listarTodosCategoria());
        return "administrador/producto/edit";
    }

    // Actualizar producto
    @PostMapping("/administrador/producto/{id}")
    public String updateProducto(@PathVariable Integer id, Producto producto) {
        Producto existentProducto = productoService.buscarProductoById(id);
        existentProducto.setIdProd(id);
        existentProducto.setDescripcion(producto.getDescripcion());
        existentProducto.setPrecioVenta(producto.getPrecioVenta());
        existentProducto.setPrecioCompra(producto.getPrecioCompra());
        existentProducto.setStock(producto.getStock());
        existentProducto.setCategoria(producto.getCategoria());
        productoService.actualizarProducto(existentProducto);
        return "redirect:/administrador/producto";
    }

    // Eliminar producto
    @GetMapping("/administrador/producto/delete/{id}")
    public String deleteProducto(@PathVariable Integer id) {
        productoService.eliminarProductoById(id);
        return "redirect:/administrador/producto";
    }
}
