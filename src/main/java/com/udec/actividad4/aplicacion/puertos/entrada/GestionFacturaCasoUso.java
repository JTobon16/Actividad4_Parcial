package com.udec.actividad4.aplicacion.puertos.entrada;

import com.udec.actividad4.dominio.modelo.Factura;

import java.util.List;

public interface GestionFacturaCasoUso {

    void generarFactura(Factura factura);

    Factura obtenerFacturaPorId(int id);

    List<Factura> listarFacturasPorCliente(String clienteDni);

    List<Factura> listarTodasFacturas();
}