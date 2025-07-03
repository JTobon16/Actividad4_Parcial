package com.udec.actividad4.dominio.modelo;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

public class Estancia {

    private int id; // ID único de estancia
    private int reservaId; // Referencia a la reserva
    private LocalDate fechaInicio;
    private LocalDate fechaFin;
    private List<Integer> habitacionesOcupadas; // IDs de habitaciones efectivamente usadas

    public Estancia() {
    }

    public Estancia(int id, int reservaId, LocalDate fechaInicio, LocalDate fechaFin, List<Integer> habitacionesOcupadas) {
        this.id = id;
        this.reservaId = reservaId;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
        this.habitacionesOcupadas = habitacionesOcupadas;
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

    public List<Integer> getHabitacionesOcupadas() {
        return habitacionesOcupadas;
    }

    public void setHabitacionesOcupadas(List<Integer> habitacionesOcupadas) {
        this.habitacionesOcupadas = habitacionesOcupadas;
    }

    @Override
    public String toString() {
        return "Estancia{" +
                "id=" + id +
                ", reservaId=" + reservaId +
                ", fechaInicio=" + fechaInicio +
                ", fechaFin=" + fechaFin +
                ", habitacionesOcupadas=" + habitacionesOcupadas +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Estancia)) return false;
        Estancia estancia = (Estancia) o;
        return id == estancia.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
