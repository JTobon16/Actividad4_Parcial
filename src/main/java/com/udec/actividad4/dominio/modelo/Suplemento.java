package com.udec.actividad4.dominio.modelo;

import com.udec.actividad4.dominio.enums.TipoSuplemento;

import java.time.LocalDate;
import java.util.Objects;

public class Suplemento {

    private int id;
    private int hotelId;
    private String descripcion;
    private TipoSuplemento tipo;
    private double valorPorNoche;
    private LocalDate fechaInicio;
    private LocalDate fechaFin;

    public Suplemento() {
    }

    public Suplemento(int id, int hotelId, String descripcion, TipoSuplemento tipo,
                      double valorPorNoche, LocalDate fechaInicio, LocalDate fechaFin) {
        this.id = id;
        this.hotelId = hotelId;
        this.descripcion = descripcion;
        this.tipo = tipo;
        this.valorPorNoche = valorPorNoche;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
    }

    // Getters y setters

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

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public TipoSuplemento getTipo() {
        return tipo;
    }

    public void setTipo(TipoSuplemento tipo) {
        this.tipo = tipo;
    }

    public double getValorPorNoche() {
        return valorPorNoche;
    }

    public void setValorPorNoche(double valorPorNoche) {
        this.valorPorNoche = valorPorNoche;
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

    @Override
    public String toString() {
        return "Suplemento{" +
                "id=" + id +
                ", hotelId=" + hotelId +
                ", descripcion='" + descripcion + '\'' +
                ", tipo=" + tipo +
                ", valorPorNoche=" + valorPorNoche +
                ", fechaInicio=" + fechaInicio +
                ", fechaFin=" + fechaFin +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Suplemento)) return false;
        Suplemento that = (Suplemento) o;
        return id == that.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
