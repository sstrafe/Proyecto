package com.cibertec.app.service;

import java.util.List;

import com.cibertec.app.entity.Venta;

public interface VentaService {

    public List<Venta> listarVentas();
    public Venta guardarVenta(Venta venta);
    public Venta buscarVentaPorId(Integer id);
    public Venta actualizarVenta(Venta venta);
    public void eliminarVenta(Integer id);
}
