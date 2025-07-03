package com.udec.actividad4.aplicacion.servicios;

import com.udec.actividad4.aplicacion.puertos.entrada.HabitacionServicio;
import com.udec.actividad4.aplicacion.puertos.salida.HabitacionRepositorio;
import com.udec.actividad4.dominio.excepciones.EntidadNoEncontradaException;
import com.udec.actividad4.dominio.modelo.Habitacion;

import java.util.List;

public class GestionHabitacionServicio implements HabitacionServicio {

    private final HabitacionRepositorio habitacionRepositorio;

    public GestionHabitacionServicio(HabitacionRepositorio habitacionRepositorio) {
        this.habitacionRepositorio = habitacionRepositorio;
    }

    @Override
    public void registrarHabitacion(Habitacion habitacion) {
        habitacionRepositorio.guardar(habitacion);
    }

    @Override
    public Habitacion buscarHabitacionPorId(int id) {
        return habitacionRepositorio.buscarPorId(id)
                .orElseThrow(() -> new EntidadNoEncontradaException("Habitación no encontrada con ID: " + id));
    }

    @Override
    public List<Habitacion> listarHabitacionesPorHotel(int hotelId) {
        return habitacionRepositorio.listarPorHotel(hotelId);
    }

    @Override
    public void eliminarHabitacion(int id) {
        Habitacion habitacion = habitacionRepositorio.buscarPorId(id)
                .orElseThrow(() -> new EntidadNoEncontradaException("Habitación no encontrada con ID: " + id));
        habitacionRepositorio.eliminar(habitacion.getId());
    }

    @Override
    public void actualizar(Habitacion habitacion) {
      }
    
    
}
