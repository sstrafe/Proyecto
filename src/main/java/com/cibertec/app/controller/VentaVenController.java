package com.cibertec.app.controller;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.dom4j.DocumentException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.cibertec.app.entity.Cliente;
import com.cibertec.app.entity.Producto;
import com.cibertec.app.entity.Proveedor;
import com.cibertec.app.entity.Venta;
import com.cibertec.app.pdf.PdfGenerator;
import com.cibertec.app.service.ClienteService;
import com.cibertec.app.service.ProductoService;
import com.cibertec.app.service.ProveedorService;
import com.cibertec.app.service.VentaService;

@Controller
@RequestMapping("/vendedor/venta")
public class VentaVenController {


	 @Autowired
	    private VentaService ventaService;

	    @Autowired
	    private ClienteService clienteService;

	    @Autowired
	    private ProductoService productoService;

	    @Autowired
	    private ProveedorService proveedorService;

	    // Listado de ventas
	    @GetMapping
	    public String listarVentas(Model model) {
	        List<Venta> ventas = ventaService.listarVentas();
	        model.addAttribute("ventas", ventas);
	        return "vendedor/venta/index";
	    }



	    @PostMapping("/guardar")
	    public String guardarVenta(@ModelAttribute Venta venta, Model model) {
	        // Validar que el producto exista y esté seleccionado
	        if (venta.getProducto() == null || venta.getProducto().getIdProd() == null) {
	            model.addAttribute("venta", venta);
	            model.addAttribute("clientes", clienteService.listarTodosCliente());
	            model.addAttribute("productos", productoService.listarTodosProductos());
	            model.addAttribute("proveedores", proveedorService.listarTodosProveedor());
	            model.addAttribute("error", "Debe seleccionar un producto válido.");
	            return "vendedor/venta/create";
	        }

	        // Obtener producto completo desde la BD
	        Producto producto = productoService.buscarProductoById(venta.getProducto().getIdProd());

	        if (producto == null) {
	            model.addAttribute("venta", venta);
	            model.addAttribute("clientes", clienteService.listarTodosCliente());
	            model.addAttribute("productos", productoService.listarTodosProductos());
	            model.addAttribute("proveedores", proveedorService.listarTodosProveedor());
	            model.addAttribute("error", "El producto seleccionado no existe.");
	            return "vendedor/venta/create";
	        }

	        // Validar cantidad
	        if (venta.getCantidad() == null || venta.getCantidad() <= 0) {
	            model.addAttribute("venta", venta);
	            model.addAttribute("clientes", clienteService.listarTodosCliente());
	            model.addAttribute("productos", productoService.listarTodosProductos());
	            model.addAttribute("proveedores", proveedorService.listarTodosProveedor());
	            model.addAttribute("error", "La cantidad debe ser mayor a 0.");
	            return "vendedor/venta/create";
	        }

	        // Validar stock
	        if (producto.getStock() < venta.getCantidad()) {
	            model.addAttribute("venta", venta);
	            model.addAttribute("clientes", clienteService.listarTodosCliente());
	            model.addAttribute("productos", productoService.listarTodosProductos());
	            model.addAttribute("proveedores", proveedorService.listarTodosProveedor());
	            model.addAttribute("errorStock", "Stock insuficiente. Stock actual: " + producto.getStock());
	            return "vendedor/venta/create";
	        }

	        // Actualizar stock
	        producto.setStock(producto.getStock() - venta.getCantidad());
	        productoService.actualizarProducto(producto);

	        // Asignar valores calculados
	        venta.setFecha(new Date());

	        // Si quieres calcular total según precio del producto
	        venta.setTotal(producto.getPrecioVenta() * venta.getCantidad());

	        ventaService.guardarVenta(venta);
	        return "redirect:/vendedor/venta";
	    }


	    // Eliminar venta
	    @GetMapping("/delete/{id}")
	    public String eliminarVenta(@PathVariable Integer id) {
	        ventaService.eliminarVenta(id);
	        return "redirect:/vendedor/venta";
	    }

	    // Editar venta
	    @GetMapping("/edit/{id}")
	    public String editarVenta(@PathVariable Integer id, Model model) {
	        Venta venta = ventaService.buscarVentaPorId(id);

	        model.addAttribute("venta", venta);
	        model.addAttribute("clientes", clienteService.listarTodosCliente());
	        model.addAttribute("productos", productoService.listarTodosProductos());
	        model.addAttribute("proveedores", proveedorService.listarTodosProveedor());

	        return "vendedor/venta/edit";
	    }

	    // Actualizar venta
	    @PostMapping("/{id}")
	    public String actualizarVenta(@PathVariable Integer id, @ModelAttribute Venta ventaActualizada) {
	        Venta ventaExistente = ventaService.buscarVentaPorId(id);

	        ventaExistente.setCliente(ventaActualizada.getCliente());
	        ventaExistente.setProducto(ventaActualizada.getProducto());
	        ventaExistente.setProveedor(ventaActualizada.getProveedor());
	        ventaExistente.setCantidad(ventaActualizada.getCantidad());

	        ventaService.guardarVenta(ventaExistente);

	        return "redirect:/vendedor/venta";
	    }
	    
	    @GetMapping("/new")
	    public String mostrarFormularioNuevaVenta(Model model) {
	        Venta venta = new Venta();
	        venta.setCliente(new Cliente());
	        venta.setProducto(new Producto());
	        venta.setProveedor(new Proveedor());

	        model.addAttribute("venta", venta);
	        model.addAttribute("clientes", clienteService.listarTodosCliente());
	        model.addAttribute("productos", productoService.listarTodosProductos());
	        model.addAttribute("proveedores", proveedorService.listarTodosProveedor());

	        return "vendedor/venta/create";
	    }

	    @GetMapping("/generarPDF")
	    public void generaraPDF(HttpServletResponse response)throws DocumentException, IOException {	
			response.setContentType("application/pdf");
	        
			DateFormat dateFormatter = new SimpleDateFormat("dd/MM/yyyy");
			String currentDateTime = dateFormatter.format(new Date());
			String headerKey = "Content-Disposition";
			String headerValue = "attachment; filename=ventas_" + currentDateTime + ".pdf";
			response.setHeader(headerKey, headerValue);
			
			List<Venta> ventas = ventaService.listarVentas();
			PdfGenerator pdfGenerator = new PdfGenerator();
			pdfGenerator.generate(ventas, response);
		}
	
	
}

