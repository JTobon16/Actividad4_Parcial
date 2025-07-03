package com.udec.actividad4.dominio.modelo;


import com.udec.actividad4.dominio.enums.TipoActividad;

import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.Objects;

public class Actividad {

    private int id; // Código único
    private int hotelId;
    private String nombre;
    private DayOfWeek diaSemana;
    private LocalTime hora;
    private String descripcion;
    private int empleadoId; // empleado de animación a cargo
    private TipoActividad tipoActividad;
    private double precioPorPersona;

    public Actividad() {
    }

    public Actividad(int id, int hotelId, String nombre, DayOfWeek diaSemana, LocalTime hora,
                     String descripcion, int empleadoId, TipoActividad tipoActividad, double precioPorPersona) {
        this.id = id;
        this.hotelId = hotelId;
        this.nombre = nombre;
        this.diaSemana = diaSemana;
        this.hora = hora;
        this.descripcion = descripcion;
        this.empleadoId = empleadoId;
        this.tipoActividad = tipoActividad;
        this.precioPorPersona = precioPorPersona;
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

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public DayOfWeek getDiaSemana() {
        return diaSemana;
    }

    public void setDiaSemana(DayOfWeek diaSemana) {
        this.diaSemana = diaSemana;
    }

    public LocalTime getHora() {
        return hora;
    }

    public void setHora(LocalTime hora) {
        this.hora = hora;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getEmpleadoId() {
        return empleadoId;
    }

    public void setEmpleadoId(int empleadoId) {
        this.empleadoId = empleadoId;
    }

    public TipoActividad getTipoActividad() {
        return tipoActividad;
    }

    public void setTipoActividad(TipoActividad tipoActividad) {
        this.tipoActividad = tipoActividad;
    }

    public double getPrecioPorPersona() {
        return precioPorPersona;
    }

    public void setPrecioPorPersona(double precioPorPersona) {
        this.precioPorPersona = precioPorPersona;
    }

    @Override
    public String toString() {
        return "Actividad{" +
                "id=" + id +
                ", hotelId=" + hotelId +
                ", nombre='" + nombre + '\'' +
                ", diaSemana=" + diaSemana +
                ", hora=" + hora +
                ", descripcion='" + descripcion + '\'' +
                ", empleadoId=" + empleadoId +
                ", tipoActividad=" + tipoActividad +
                ", precioPorPersona=" + precioPorPersona +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Actividad)) return false;
        Actividad that = (Actividad) o;
        return id == that.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
