package com.udec.actividad4.aplicacion.servicios;

import com.udec.actividad4.aplicacion.puertos.entrada.EstanciaServicio;
import com.udec.actividad4.aplicacion.puertos.salida.EstanciaRepositorio;
import com.udec.actividad4.dominio.excepciones.EntidadNoEncontradaException;
import com.udec.actividad4.dominio.modelo.Estancia;

import java.util.List;

public class GestionEstanciaServicio implements EstanciaServicio {

    private final EstanciaRepositorio estanciaRepositorio;

    public GestionEstanciaServicio(EstanciaRepositorio estanciaRepositorio) {
        this.estanciaRepositorio = estanciaRepositorio;
    }

    @Override
    public void registrarEstancia(Estancia estancia) {
        estanciaRepositorio.guardar(estancia);
    }

    @Override
    public Estancia buscarEstanciaPorId(int id) {
        return estanciaRepositorio.buscarPorId(id)
                .orElseThrow(() -> new EntidadNoEncontradaException("Estancia no encontrada con ID: " + id));
    }

    @Override
    public List<Estancia> listarEstancias() {
        return estanciaRepositorio.listarTodas();
    }

    public void eliminarEstancia(int id) {
        estanciaRepositorio.eliminar(id);
    }

    public void actualizarEstancia(Estancia estancia) {
        estanciaRepositorio.actualizar(estancia);
    }

    @Override
public void finalizarEstancia(int id) {
    Estancia estancia = estanciaRepositorio.buscarPorId(id)
        .orElseThrow(() -> new EntidadNoEncontradaException("Estancia no encontrada con ID: " + id));

    estancia.setFinalizada(true); // o lo que necesite tu lógica
    estanciaRepositorio.actualizar(estancia);
}
}
