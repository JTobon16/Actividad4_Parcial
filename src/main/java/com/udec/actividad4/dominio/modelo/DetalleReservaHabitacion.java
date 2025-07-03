package com.udec.actividad4.dominio.modelo;

import java.util.Objects;

public class DetalleReservaHabitacion {

    private int habitacionId;
    private int cantidad; // Número de habitaciones de ese tipo

    public DetalleReservaHabitacion() {
    }

    public DetalleReservaHabitacion(int habitacionId, int cantidad) {
        this.habitacionId = habitacionId;
        this.cantidad = cantidad;
    }

    public int getHabitacionId() {
        return habitacionId;
    }

    public void setHabitacionId(int habitacionId) {
        this.habitacionId = habitacionId;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    @Override
    public String toString() {
        return "DetalleReservaHabitacion{" +
                "habitacionId=" + habitacionId +
                ", cantidad=" + cantidad +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof DetalleReservaHabitacion)) return false;
        DetalleReservaHabitacion that = (DetalleReservaHabitacion) o;
        return habitacionId == that.habitacionId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(habitacionId);
    }
}
