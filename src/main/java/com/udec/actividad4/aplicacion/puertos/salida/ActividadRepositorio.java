package com.udec.actividad4.aplicacion.puertos.salida;

import com.udec.actividad4.dominio.modelo.Actividad;

import java.util.List;
import java.util.Optional;

public interface ActividadRepositorio {

    void guardar(Actividad actividad);

    Optional<Actividad> buscarPorId(int id);

    List<Actividad> listarTodos();

    void actualizar(Actividad actividad);

    void eliminar(int id);
    
    List<Actividad> obtenerActividadesPorEmpleado(int empleadoId);

     List<Actividad> obtenerActividadesPorHotel(int hotelId);
     
    List<Actividad> obtenerActividadesContratadasPorCliente(String clienteDni);

}
