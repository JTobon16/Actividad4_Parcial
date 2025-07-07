package com.udec.actividad4.infraestructura.controlador;

import com.udec.actividad4.aplicacion.puertos.entrada.GestionEstanciaCasoUso;
import com.udec.actividad4.dominio.modelo.Estancia;

import java.util.List;

public class EstanciaControlador {

    private final GestionEstanciaCasoUso gestionEstanciaCasoUso;

    public EstanciaControlador(GestionEstanciaCasoUso gestionEstanciaCasoUso) {
        this.gestionEstanciaCasoUso = gestionEstanciaCasoUso;
    }

    public void mostrarEstanciasActivasPorHotel(int hotelId) {
        List<Estancia> estancias = gestionEstanciaCasoUso.listarEstanciasActivasPorHotel(hotelId);

        if (estancias.isEmpty()) {
            System.out.println("No hay estancias activas para el hotel con ID: " + hotelId);
            return;
        }

        System.out.println("----- Estancias activas para el hotel ID: " + hotelId + " -----");
        for (Estancia e : estancias) {
            System.out.println("Estancia ID: " + e.getId());
            System.out.println("Reserva ID: " + e.getReservaId());
            System.out.println("Fecha de Inicio: " + e.getFechaInicio());
            System.out.println("Fecha de Fin: " + e.getFechaFin());
            System.out.println("Habitaciones Ocupadas: " + e.getHabitacionesOcupadas());
            System.out.println("Finalizada: " + e.isFinalizada());
            System.out.println("-------------------------------");
        }
    }
}
