package com.udec.actividad4.aplicacion.servicios;

import com.udec.actividad4.aplicacion.puertos.entrada.FacturaServicio;
import com.udec.actividad4.aplicacion.puertos.salida.FacturaRepositorio;
import com.udec.actividad4.dominio.excepciones.EntidadNoEncontradaException;
import com.udec.actividad4.dominio.modelo.Factura;

import java.util.List;

public class GestionFacturaServicio implements FacturaServicio {

    private final FacturaRepositorio facturaRepositorio;

    public GestionFacturaServicio(FacturaRepositorio facturaRepositorio) {
        this.facturaRepositorio = facturaRepositorio;
    }

    @Override
    public void generarFactura(Factura factura) {
        facturaRepositorio.guardar(factura);
    }

    @Override
    public Factura buscarFacturaPorId(int id) {
        return facturaRepositorio.buscarPorId(id)
                .orElseThrow(() -> new EntidadNoEncontradaException("Factura no encontrada con ID: " + id));
    }

    @Override
    public List<Factura> listarFacturas() {
        return facturaRepositorio.listarTodos();
    }

    @Override
    public void eliminarFactura(int id) {
        Factura factura = facturaRepositorio.buscarPorId(id)
                .orElseThrow(() -> new EntidadNoEncontradaException("Factura no encontrada con ID: " + id));
        facturaRepositorio.eliminar(id);
    }
}

