package com.udec.actividad4.infraestructura.controlador;

import com.udec.actividad4.aplicacion.puertos.entrada.GestionSuplementoCasoUso;
import com.udec.actividad4.dominio.modelo.Suplemento;

import java.time.LocalDate;
import java.util.List;

public class SuplementoControlador {

    private final GestionSuplementoCasoUso gestionSuplementoCasoUso;

    public SuplementoControlador(GestionSuplementoCasoUso gestionSuplementoCasoUso) {
        this.gestionSuplementoCasoUso = gestionSuplementoCasoUso;
    }

    // Consulta 9
    public void mostrarSuplementosTemporada(LocalDate fechaConsulta) {
        List<Suplemento> suplementos = gestionSuplementoCasoUso.obtenerSuplementosTemporadaPorFecha(fechaConsulta);
        if (suplementos.isEmpty()) {
            System.out.println("No hay suplementos de temporada para la fecha: " + fechaConsulta);
        } else {
            System.out.println("Suplementos de temporada para la fecha: " + fechaConsulta);
            for (Suplemento suplemento : suplementos) {
                System.out.println(suplemento);
            }
        }
    }
    
    // consulta 15
    public void mostrarSuplementosPorCaracteristicas() {
    List<Suplemento> suplementos = gestionSuplementoCasoUso.listarSuplementosPorCaracteristicas();
    System.out.println("\n--- Suplementos aplicados por caracteristicas especiales ---");

    for (Suplemento s : suplementos) {
        System.out.println("Hotel ID: " + s.getHotelId() +
                           " | Descripcion: " + s.getDescripcion() +
                           " | Valor/noche: $" + s.getValorPorNoche());
    }
}

}
