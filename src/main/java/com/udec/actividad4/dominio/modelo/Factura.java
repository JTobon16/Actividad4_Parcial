package com.udec.actividad4.dominio.modelo;

import java.time.LocalDate;
import java.util.Objects;

public class Factura {

    private int id; // Número de factura
    private int reservaId; // Reserva asociada
    private LocalDate fechaEmision;
    private int diasEstancia;
    private double totalHabitaciones;
    private double totalSuplementos;
    private double totalActividades;
    private double senalDescontada;
    private double totalFinal;

    public Factura() {
    }

    public Factura(int id, int reservaId, LocalDate fechaEmision, int diasEstancia,
                   double totalHabitaciones, double totalSuplementos, double totalActividades,
                   double senalDescontada, double totalFinal) {
        this.id = id;
        this.reservaId = reservaId;
        this.fechaEmision = fechaEmision;
        this.diasEstancia = diasEstancia;
        this.totalHabitaciones = totalHabitaciones;
        this.totalSuplementos = totalSuplementos;
        this.totalActividades = totalActividades;
        this.senalDescontada = senalDescontada;
        this.totalFinal = totalFinal;
    }

    // Getters y Setters

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getReservaId() {
        return reservaId;
    }

    public void setReservaId(int reservaId) {
        this.reservaId = reservaId;
    }

    public LocalDate getFechaEmision() {
        return fechaEmision;
    }

    public void setFechaEmision(LocalDate fechaEmision) {
        this.fechaEmision = fechaEmision;
    }

    public int getDiasEstancia() {
        return diasEstancia;
    }

    public void setDiasEstancia(int diasEstancia) {
        this.diasEstancia = diasEstancia;
    }

    public double getTotalHabitaciones() {
        return totalHabitaciones;
    }

    public void setTotalHabitaciones(double totalHabitaciones) {
        this.totalHabitaciones = totalHabitaciones;
    }

    public double getTotalSuplementos() {
        return totalSuplementos;
    }

    public void setTotalSuplementos(double totalSuplementos) {
        this.totalSuplementos = totalSuplementos;
    }

    public double getTotalActividades() {
        return totalActividades;
    }

    public void setTotalActividades(double totalActividades) {
        this.totalActividades = totalActividades;
    }

    public double getSenalDescontada() {
        return senalDescontada;
    }

    public void setSenalDescontada(double senalDescontada) {
        this.senalDescontada = senalDescontada;
    }

    public double getTotalFinal() {
        return totalFinal;
    }

    public void setTotalFinal(double totalFinal) {
        this.totalFinal = totalFinal;
    }

    @Override
    public String toString() {
        return "Factura{" +
                "id=" + id +
                ", reservaId=" + reservaId +
                ", fechaEmision=" + fechaEmision +
                ", diasEstancia=" + diasEstancia +
                ", totalHabitaciones=" + totalHabitaciones +
                ", totalSuplementos=" + totalSuplementos +
                ", totalActividades=" + totalActividades +
                ", senalDescontada=" + senalDescontada +
                ", totalFinal=" + totalFinal +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Factura)) return false;
        Factura factura = (Factura) o;
        return id == factura.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
