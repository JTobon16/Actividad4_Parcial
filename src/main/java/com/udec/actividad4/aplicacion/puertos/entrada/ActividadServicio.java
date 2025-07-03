package com.udec.actividad4.aplicacion.puertos.entrada;

import com.udec.actividad4.dominio.modelo.Actividad;

import java.util.List;

public interface ActividadServicio {

    void registrarActividad(Actividad actividad);

    Actividad buscarActividadPorId(int id);

    List<Actividad> listarActividades();

    void actualizarActividad(Actividad actividad);

    void eliminarActividad(int id);
}
