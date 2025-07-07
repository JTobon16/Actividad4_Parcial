package com.udec.actividad4.aplicacion.servicios;

import com.udec.actividad4.aplicacion.puertos.entrada.GestionActividadCasoUso;
import com.udec.actividad4.aplicacion.puertos.salida.ActividadRepositorio;
import com.udec.actividad4.dominio.excepciones.EntidadNoEncontradaException;
import com.udec.actividad4.dominio.modelo.Actividad;

import java.util.List;
import java.util.Map;

public class GestionActividadServicio implements GestionActividadCasoUso {

    private final ActividadRepositorio actividadRepositorio;

    public GestionActividadServicio(ActividadRepositorio actividadRepositorio) {
        this.actividadRepositorio = actividadRepositorio;
    }

    @Override
    public void registrarActividad(Actividad actividad) {
        actividadRepositorio.guardar(actividad);
    }

    @Override
    public Actividad obtenerActividadPorId(int id) {
        return actividadRepositorio.buscarPorId(id)
                .orElseThrow(() -> new EntidadNoEncontradaException("Actividad no encontrada con ID: " + id));
    }

    @Override
    public List<Actividad> listarActividadesPorHotel(int hotelId) {
    return actividadRepositorio.obtenerActividadesPorHotel(hotelId);
    }

    @Override
    public List<Actividad> listarActividadesPorEmpleado(int empleadoId) {
    return actividadRepositorio.obtenerActividadesPorEmpleado(empleadoId);
}

    public void actualizarActividad(Actividad actividad) {
        actividadRepositorio.actualizar(actividad);
    }

    @Override
    public void eliminarActividad(int id) {
        Actividad actividad = actividadRepositorio.buscarPorId(id)
                .orElseThrow(() -> new EntidadNoEncontradaException("Actividad no encontrada con ID: " + id));
        actividadRepositorio.eliminar(id);
    }
    
    @Override
    public List<Actividad> obtenerActividadesContratadasPorCliente(String clienteDni) {
    return actividadRepositorio.obtenerActividadesContratadasPorCliente(clienteDni);
}
    
    @Override
    public List<Actividad> obtenerHistorialActividadesPagasPorHotel(int hotelId) {
    return actividadRepositorio.obtenerHistorialActividadesPagasPorHotel(hotelId);
}


    @Override
   public Map<Integer, Double> obtenerIngresosPorActividadesPagasPorHotel() {
    return actividadRepositorio.obtenerIngresosPorActividadesPagasPorHotel();
}
   
   // consulta 19
   @Override
    public Map<Integer, Integer> obtenerHotelesConMasActividades() {
    return actividadRepositorio.obtenerHotelesConMasActividades();
}
 // consulta 21
    @Override
    public List<Actividad> listarActividadesMasContratadasPorHotel() {
    return actividadRepositorio.obtenerActividadesMasContratadasPorHotel();
}


}
