
package com.udec.actividad4.aplicacion.puertos.entrada;

import com.udec.actividad4.dominio.modelo.Habitacion;

import java.util.List;

public interface GestionHabitacionCasoUso {

    void registrarHabitacion(Habitacion habitacion);

    Habitacion obtenerHabitacionPorId(int id);

    List<Habitacion> listarHabitacionesPorHotel(int hotelId);

    List<Habitacion> listarDisponiblesPorTipo(int hotelId, String tipo);

    void eliminarHabitacion(int id);
    
     List<String> consultarTarifasHabitaciones(); // Para consulta 16
}
