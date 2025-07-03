package com.udec.actividad4.dominio.modelo;

import java.util.Objects;

public class TarifaHabitacion {

    private int id;
    private int hotelId;
    private String tipoHabitacion; // (ej. Individual, Doble, Suite...)
    private double precioPorNoche;

    public TarifaHabitacion() {
    }

    public TarifaHabitacion(int id, int hotelId, String tipoHabitacion, double precioPorNoche) {
        this.id = id;
        this.hotelId = hotelId;
        this.tipoHabitacion = tipoHabitacion;
        this.precioPorNoche = precioPorNoche;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getHotelId() {
        return hotelId;
    }

    public void setHotelId(int hotelId) {
        this.hotelId = hotelId;
    }

    public String getTipoHabitacion() {
        return tipoHabitacion;
    }

    public void setTipoHabitacion(String tipoHabitacion) {
        this.tipoHabitacion = tipoHabitacion;
    }

    public double getPrecioPorNoche() {
        return precioPorNoche;
    }

    public void setPrecioPorNoche(double precioPorNoche) {
        this.precioPorNoche = precioPorNoche;
    }

    @Override
    public String toString() {
        return "TarifaHabitacion{" +
                "id=" + id +
                ", hotelId=" + hotelId +
                ", tipoHabitacion='" + tipoHabitacion + '\'' +
                ", precioPorNoche=" + precioPorNoche +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof TarifaHabitacion)) return false;
        TarifaHabitacion that = (TarifaHabitacion) o;
        return id == that.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
