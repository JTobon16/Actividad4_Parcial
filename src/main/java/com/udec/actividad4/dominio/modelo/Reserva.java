package com.udec.actividad4.dominio.modelo;

import java.time.LocalDate;
import java.util.List;


public class Reserva {

    private int id; // ID único de la reserva
    private String clienteDni; // DNI del cliente que reserva
    private int hotelId; // Hotel donde se realiza la reserva
    private LocalDate fechaInicio;
    private LocalDate fechaFin;
    private double senalPagada; // Anticipo
    private boolean confirmada; // Si se convierte en estancia o no
    private List<DetalleReservaHabitacion> habitacionesReservadas;

    public Reserva() {
    }

    public Reserva(int id, String clienteDni, int hotelId, LocalDate fechaInicio, LocalDate fechaFin,
                   double senalPagada, boolean confirmada, List<DetalleReservaHabitacion> habitacionesReservadas) {
        this.id = id;
        this.clienteDni = clienteDni;
        this.hotelId = hotelId;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
        this.senalPagada = senalPagada;
        this.confirmada = confirmada;
        this.habitacionesReservadas = habitacionesReservadas;
    }

    // Getters y Setters

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getClienteDni() {
        return clienteDni;
    }

    public void setClienteDni(String clienteDni) {
        this.clienteDni = clienteDni;
    }

    public int getHotelId() {
        return hotelId;
    }

    public void setHotelId(int hotelId) {
        this.hotelId = hotelId;
    }

    public LocalDate getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(LocalDate fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public LocalDate getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(LocalDate fechaFin) {
        this.fechaFin = fechaFin;
    }

    public double getSenalPagada() {
        return senalPagada;
    }

    public void setSenalPagada(double senalPagada) {
        this.senalPagada = senalPagada;
    }

    public boolean isConfirmada() {
        return confirmada;
    }

    public void setConfirmada(boolean confirmada) {
        this.confirmada = confirmada;
    }

    public List<DetalleReservaHabitacion> getHabitacionesReservadas() {
        return habitacionesReservadas;
    }

    public void setHabitacionesReservadas(List<DetalleReservaHabitacion> habitacionesReservadas) {
        this.habitacionesReservadas = habitacionesReservadas;
    }

     @Override
    public String toString() {
        return "Reserva{" +
                "id=" + id +
                ", clienteDni='" + clienteDni + '\'' +
                ", hotelId=" + hotelId +
                ", fechaInicio=" + fechaInicio +
                ", fechaFin=" + fechaFin +
                ", senalPagada=" + senalPagada +
                ", confirmada=" + confirmada +
                ", habitacionesReservadas=" + habitacionesReservadas +
                '}';
    }
}
