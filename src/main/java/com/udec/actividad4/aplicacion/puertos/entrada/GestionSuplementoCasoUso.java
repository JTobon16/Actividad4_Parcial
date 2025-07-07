package com.udec.actividad4.aplicacion.puertos.entrada;

import com.udec.actividad4.dominio.modelo.Suplemento;
import java.time.LocalDate;

import java.util.List;

public interface GestionSuplementoCasoUso {

    void registrarSuplemento(Suplemento suplemento);

    Suplemento obtenerSuplementoPorId(int id);

    List<Suplemento> listarSuplementosPorTipo(String tipo);

    List<Suplemento> listarTodos();

    void eliminarSuplemento(int id);
    
    List<Suplemento> obtenerSuplementosTemporadaPorFecha(LocalDate fecha); //consulta 9
   
    void actualizarSuplemento(Suplemento suplemento);

}
