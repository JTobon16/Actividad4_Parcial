package com.udec.actividad4.aplicacion.puertos.entrada;

import com.udec.actividad4.dominio.modelo.Actividad;

import java.util.List;
import java.util.Map;

public interface GestionActividadCasoUso {

    void registrarActividad(Actividad actividad);

    Actividad obtenerActividadPorId(int id);

    List<Actividad> listarActividadesPorHotel(int hotelId);

    List<Actividad> listarActividadesPorEmpleado(int empleadoId);

    void eliminarActividad(int id);
    
    List<Actividad> obtenerActividadesContratadasPorCliente(String clienteDni);
    
    List<Actividad> obtenerHistorialActividadesPagasPorHotel(int hotelId);
    
    Map<Integer, Double> obtenerIngresosPorActividadesPagasPorHotel();
    
    Map<Integer, Integer> obtenerHotelesConMasActividades();

    List<Actividad> listarActividadesMasContratadasPorHotel();
    


}
