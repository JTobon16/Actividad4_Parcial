package com.udec.actividad4.aplicacion.puertos.entrada;

import com.udec.actividad4.dominio.modelo.Actividad;

import java.util.List;

public interface GestionActividadCasoUso {

    void registrarActividad(Actividad actividad);

    Actividad obtenerActividadPorId(int id);

    List<Actividad> listarActividadesPorHotel(int hotelId);

    List<Actividad> listarActividadesPorEmpleado(int empleadoId);

    void eliminarActividad(int id);
    
    
}
