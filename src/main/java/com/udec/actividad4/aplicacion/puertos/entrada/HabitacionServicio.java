package com.udec.actividad4.aplicacion.puertos.entrada;

import com.udec.actividad4.dominio.modelo.Habitacion;
import java.util.List;

public interface HabitacionServicio {
    void registrarHabitacion(Habitacion habitacion);
    Habitacion buscarHabitacionPorId(int id);
    List<Habitacion> listarHabitacionesPorHotel(int hotelId);
    void eliminarHabitacion(int id);
    void actualizarHabitacion(Habitacion habitacion);
}
