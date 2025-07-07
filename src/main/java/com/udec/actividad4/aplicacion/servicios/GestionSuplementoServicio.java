package com.udec.actividad4.aplicacion.servicios;

import com.udec.actividad4.aplicacion.puertos.entrada.GestionSuplementoCasoUso;
import com.udec.actividad4.aplicacion.puertos.salida.SuplementoRepositorio;
import com.udec.actividad4.dominio.excepciones.EntidadNoEncontradaException;
import com.udec.actividad4.dominio.modelo.Suplemento;
import java.time.LocalDate;

import java.util.List;

public class GestionSuplementoServicio implements GestionSuplementoCasoUso {

    private final SuplementoRepositorio suplementoRepositorio;

    public GestionSuplementoServicio(SuplementoRepositorio suplementoRepositorio) {
        this.suplementoRepositorio = suplementoRepositorio;
    }

    @Override
    public void registrarSuplemento(Suplemento suplemento) {
        suplementoRepositorio.guardar(suplemento);
    }

    @Override
    public Suplemento obtenerSuplementoPorId(int id) {
        return suplementoRepositorio.buscarPorId(id)
                .orElseThrow(() -> new EntidadNoEncontradaException("Suplemento no encontrado con ID: " + id));
    }

    @Override
    public List<Suplemento> listarSuplementosPorTipo(String tipo) {
    return suplementoRepositorio.listarPorTipo(tipo); // este método debe existir en el repositorio

    }
    
    @Override
    public List<Suplemento> listarTodos() {
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
    
    //consulta 9
    @Override
    public List<Suplemento> obtenerSuplementosTemporadaPorFecha(LocalDate fecha) {
    return suplementoRepositorio.obtenerSuplementosTemporadaPorFecha(fecha); 
}
    //consulta 15
    public List<Suplemento> listarSuplementosPorCaracteristicas() {
    return suplementoRepositorio.obtenerSuplementosPorCaracteristicas();
}


}

