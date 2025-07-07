package com.udec.actividad4.aplicacion.servicios;

import com.udec.actividad4.aplicacion.puertos.entrada.GestionEstanciaCasoUso;
import com.udec.actividad4.aplicacion.puertos.salida.EstanciaRepositorio;
import com.udec.actividad4.dominio.excepciones.EntidadNoEncontradaException;
import com.udec.actividad4.dominio.modelo.Estancia;

import java.util.List;

public class GestionEstanciaServicio implements GestionEstanciaCasoUso {

    private final EstanciaRepositorio estanciaRepositorio;

    public GestionEstanciaServicio(EstanciaRepositorio estanciaRepositorio) {
        this.estanciaRepositorio = estanciaRepositorio;
    }

    @Override
    public void registrarEstancia(Estancia estancia) {
        estanciaRepositorio.guardar(estancia);
    }

    @Override
    public Estancia obtenerEstanciaPorId(int id) {
        return estanciaRepositorio.buscarPorId(id)
                .orElseThrow(() -> new EntidadNoEncontradaException("Estancia no encontrada con ID: " + id));
    }

    @Override
    public List<Estancia> listarEstanciasPorHotel(int hotelId) {
        return estanciaRepositorio.listarPorHotel(hotelId); // Debes tener este método en el repositorio
    }

    @Override
    public List<Estancia> listarEstanciasPorCliente(String clienteDni) {
        return estanciaRepositorio.listarPorCliente(clienteDni); // También debe estar en el repositorio
    }

    @Override
    public void finalizarEstancia(int id) {
        Estancia estancia = estanciaRepositorio.buscarPorId(id)
                .orElseThrow(() -> new EntidadNoEncontradaException("Estancia no encontrada con ID: " + id));
        estancia.setFinalizada(true);
        estanciaRepositorio.actualizar(estancia);
    }

    @Override
    public List<Estancia> listarEstanciasActivasPorHotel(int hotelId) {
        return estanciaRepositorio.obtenerEstanciasActivasPorHotel(hotelId);
    }
}
