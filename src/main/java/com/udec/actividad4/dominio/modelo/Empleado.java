package com.udec.actividad4.dominio.modelo;

import com.udec.actividad4.dominio.enums.TipoEmpleado;

import java.util.Objects;

public class Empleado {

    private int id; // Código único de empleado
    private String nombreCompleto;
    private String direccion;
    private String dni;
    private String nivelEducativo; // Licenciado, diplomado, etc.
    private TipoEmpleado tipo; // Enum: GESTION, CAMARERO, LIMPIEZA, etc.
    private int hotelId; // ID del hotel donde trabaja (relación con Hotel)

    public Empleado() {
    }

    public Empleado(int id, String nombreCompleto, String direccion, String dni, String nivelEducativo, TipoEmpleado tipo, int hotelId) {
        this.id = id;
        this.nombreCompleto = nombreCompleto;
        this.direccion = direccion;
        this.dni = dni;
        this.nivelEducativo = nivelEducativo;
        this.tipo = tipo;
        this.hotelId = hotelId;
    }

    // Getters y Setters

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombreCompleto() {
        return nombreCompleto;
    }

    public void setNombreCompleto(String nombreCompleto) {
        this.nombreCompleto = nombreCompleto;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getNivelEducativo() {
        return nivelEducativo;
    }

    public void setNivelEducativo(String nivelEducativo) {
        this.nivelEducativo = nivelEducativo;
    }

    public TipoEmpleado getTipo() {
        return tipo;
    }

    public void setTipo(TipoEmpleado tipo) {
        this.tipo = tipo;
    }

    public int getHotelId() {
        return hotelId;
    }

    public void setHotelId(int hotelId) {
        this.hotelId = hotelId;
    }

    @Override
    public String toString() {
        return "Empleado{" +
                "id=" + id +
                ", nombreCompleto='" + nombreCompleto + '\'' +
                ", direccion='" + direccion + '\'' +
                ", dni='" + dni + '\'' +
                ", nivelEducativo='" + nivelEducativo + '\'' +
                ", tipo=" + tipo +
                ", hotelId=" + hotelId +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Empleado)) return false;
        Empleado empleado = (Empleado) o;
        return id == empleado.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
