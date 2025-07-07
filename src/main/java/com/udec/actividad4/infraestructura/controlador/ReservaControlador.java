/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.udec.actividad4.infraestructura.controlador;

import com.udec.actividad4.aplicacion.puertos.entrada.GestionReservaCasoUso;
import com.udec.actividad4.dominio.modelo.Reserva;

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
    
    // consulta 8
    public void mostrarHistorialReservasCliente(String dniCliente) {
    List<Reserva> reservas = gestionReservaCasoUso.obtenerHistorialReservasCliente(dniCliente);

    if (reservas.isEmpty()) {
        System.out.println("El cliente no tiene reservas registradas.");
    } else {
        System.out.println("Historial de reservas para el cliente " + dniCliente + ":");
        for (Reserva r : reservas) {
            System.out.println("ID Reserva: " + r.getId());
            System.out.println("Hotel ID: " + r.getHotelId());
            System.out.println("Inicio: " + r.getFechaInicio());
            System.out.println("Fin: " + r.getFechaFin());
            System.out.println("Senal Pagada: " + r.getSenalPagada());
            System.out.println("Confirmada: " + r.isConfirmada());
            System.out.println("----------");
        }
    }
}

    public void mostrarReservasConCliente() {
    List<String> reservas = gestionReservaCasoUso.obtenerReservasConDetalleCliente();
    
    System.out.println("\n--- Reservas con detalle del cliente ---");
    if (reservas.isEmpty()) {
        System.out.println("No hay reservas registradas.");
    } else {
        reservas.forEach(System.out::println);
    }
}

}