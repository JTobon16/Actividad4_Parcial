package com.udec.actividad4.aplicacion.puertos.entrada;

import com.udec.actividad4.dominio.modelo.Factura;

import java.util.List;

public interface FacturaServicio {

    void generarFactura(Factura factura);

    Factura buscarFacturaPorId(int id);

    List<Factura> listarFacturas();

    void eliminarFactura(int id);
}
