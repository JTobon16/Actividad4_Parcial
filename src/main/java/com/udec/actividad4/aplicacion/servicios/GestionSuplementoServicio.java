package com.udec.actividad4.aplicacion.servicios;

import com.udec.actividad4.aplicacion.puertos.entrada.SuplementoServicio;
import com.udec.actividad4.aplicacion.puertos.salida.SuplementoRepositorio;
import com.udec.actividad4.dominio.excepciones.EntidadNoEncontradaException;
import com.udec.actividad4.dominio.modelo.Suplemento;

import java.util.List;

public class GestionSuplementoServicio implements SuplementoServicio {

    private final SuplementoRepositorio suplementoRepositorio;

    public GestionSuplementoServicio(SuplementoRepositorio suplementoRepositorio) {
        this.suplementoRepositorio = suplementoRepositorio;
    }

    @Override
    public void registrarSuplemento(Suplemento suplemento) {
        suplementoRepositorio.guardar(suplemento);
    }

    @Override
    public Suplemento buscarSuplementoPorId(int id) {
        return suplementoRepositorio.buscarPorId(id)
                .orElseThrow(() -> new EntidadNoEncontradaException("Suplemento no encontrado con ID: " + id));
    }

    @Override
    public List<Suplemento> listarSuplementos() {
        return suplementoRepositorio.listarTodos();
    }

    @Override
    public void actualizarSuplemento(Suplemento suplemento) {
        suplementoRepositorio.actualizar(suplemento);
    }

    @Override
    public void eliminarSuplemento(int id) {
        Suplemento suplemento = suplementoRepositorio.buscarPorId(id)
                .orElseThrow(() -> new EntidadNoEncontradaException("Suplemento no encontrado con ID: " + id));
        suplementoRepositorio.eliminar(suplemento.getId());
    }
}

