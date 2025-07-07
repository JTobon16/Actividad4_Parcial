package com.udec.actividad4.dominio.modelo;

import com.udec.actividad4.dominio.enums.TipoHabitacion;

import java.util.Objects;

public class Habitacion {

    private int id; // ID único (puede ser único dentro del hotel)
    private int hotelId; // Relación con el hotel al que pertenece
    private TipoHabitacion tipo; // Enum: INDIVIDUAL, DOBLE, TRIPLE, SUITE
    private double precioBasePorNoche;
    private boolean tieneVistaMar;
    private boolean tieneJacuzzi;
    private boolean tieneBalcon;

    public Habitacion() {
    }

    public Habitacion(int id, int hotelId, TipoHabitacion tipo, double precioBasePorNoche,
                      boolean tieneVistaMar, boolean tieneJacuzzi, boolean tieneBalcon) {
        this.id = id;
        this.hotelId = hotelId;
        this.tipo = tipo;
        this.precioBasePorNoche = precioBasePorNoche;
        this.tieneVistaMar = tieneVistaMar;
        this.tieneJacuzzi = tieneJacuzzi;
        this.tieneBalcon = tieneBalcon;
    }

    // Getters y Setters

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

    public TipoHabitacion getTipo() {
        return tipo;
    }

    public void setTipo(TipoHabitacion tipo) {
        this.tipo = tipo;
    }

    public double getPrecioBasePorNoche() {
        return precioBasePorNoche;
    }

    public void setPrecioBasePorNoche(double precioBasePorNoche) {
        this.precioBasePorNoche = precioBasePorNoche;
    }

    public boolean isTieneVistaMar() {
        return tieneVistaMar;
    }

    public void setTieneVistaMar(boolean tieneVistaMar) {
        this.tieneVistaMar = tieneVistaMar;
    }

    public boolean isTieneJacuzzi() {
        return tieneJacuzzi;
    }

    public void setTieneJacuzzi(boolean tieneJacuzzi) {
        this.tieneJacuzzi = tieneJacuzzi;
    }

    public boolean isTieneBalcon() {
        return tieneBalcon;
    }

    public void setTieneBalcon(boolean tieneBalcon) {
        this.tieneBalcon = tieneBalcon;
    }

    @Override
    public String toString() {
        return "Habitacion{" +
                "id=" + id +
                ", hotelId=" + hotelId +
                ", tipo=" + tipo +
                ", precioBasePorNoche=" + precioBasePorNoche +
                ", tieneVistaMar=" + tieneVistaMar +
                ", tieneJacuzzi=" + tieneJacuzzi +
                ", tieneBalcon=" + tieneBalcon +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Habitacion)) return false;
        Habitacion that = (Habitacion) o;
        return id == that.id && hotelId == that.hotelId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, hotelId);
    }
}
