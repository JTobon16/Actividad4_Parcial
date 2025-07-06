/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.udec.actividad4.infraestructura.controlador;

import com.udec.actividad4.aplicacion.puertos.entrada.GestionReservaCasoUso;

import com.udec.actividad4.infraestructura.dtos.ReservaDetalleDTO;

import java.util.List;

public class ReservaControlador {

    private final GestionReservaCasoUso gestionReservaCasoUso;

    public ReservaControlador(GestionReservaCasoUso gestionReservaCasoUso) {
        this.gestionReservaCasoUso = gestionReservaCasoUso;
    }

    //consulta 7
    public void mostrarReservasActivasConHabitaciones() {
        List<ReservaDetalleDTO> reservas = gestionReservaCasoUso.obtenerReservasActivasConDetalle();

        if (reservas.isEmpty()) {
            System.out.println("No hay reservas activas.");
        } else {
            System.out.println("Reservas activas con detalles de habitaciones:");
            for (ReservaDetalleDTO dto : reservas) {
                System.out.println("Cliente: " + dto.getNombreCliente());
                System.out.println("ID Reserva: " + dto.getIdReserva());
                System.out.println("Fecha Inicio: " + dto.getFechaInicio());
                System.out.println("Fecha Fin: " + dto.getFechaFin());
                System.out.println("Habitaciones Ocupadas: " + dto.getHabitaciones());
                System.out.println("----------");
            }
        }
    }
}