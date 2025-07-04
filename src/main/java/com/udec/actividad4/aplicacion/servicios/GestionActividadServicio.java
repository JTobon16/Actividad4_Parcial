package com.udec.actividad4.aplicacion.servicios;

import com.udec.actividad4.aplicacion.puertos.entrada.ActividadServicio;
import com.udec.actividad4.aplicacion.puertos.salida.ActividadRepositorio;
import com.udec.actividad4.dominio.excepciones.EntidadNoEncontradaException;
import com.udec.actividad4.dominio.modelo.Actividad;

import java.util.List;

public class GestionActividadServicio implements ActividadServicio {

    private final ActividadRepositorio actividadRepositorio;

    public GestionActividadServicio(ActividadRepositorio actividadRepositorio) {
        this.actividadRepositorio = actividadRepositorio;
    }

    @Override
    public void registrarActividad(Actividad actividad) {
        actividadRepositorio.guardar(actividad);
    }

    @Override
    public Actividad buscarActividadPorId(int id) {
        return actividadRepositorio.buscarPorId(id)
                .orElseThrow(() -> new EntidadNoEncontradaException("Actividad no encontrada con ID: " + id));
    }

    @Override
    public List<Actividad> listarActividades() {
        return actividadRepositorio.listarTodos();
    }

    @Override
    public void actualizarActividad(Actividad actividad) {
        actividadRepositorio.actualizar(actividad);
    }

    @Override
    public void eliminarActividad(int id) {
        Actividad actividad = actividadRepositorio.buscarPorId(id)
                .orElseThrow(() -> new EntidadNoEncontradaException("Actividad no encontrada con ID: " + id));
        actividadRepositorio.eliminar(id);
    }
}
