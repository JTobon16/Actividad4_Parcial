package com.udec.actividad4.dominio.modelo;

import java.util.Objects;

public class Hotel {

    private int id; 
    private String nombre;
    private int categoriaEstrellas; 
    private String direccion;
    private String telefono;
    private String nombreDirector;

    // constructor
    
    public Hotel(int id, String nombre, int categoriaEstrellas, String direccion, String telefono,
            String nombreDirector) {
        this.id = id;
        this.nombre = nombre;
        this.categoriaEstrellas = categoriaEstrellas;
        this.direccion = direccion;
        this.telefono = telefono;
        this.nombreDirector = nombreDirector;
    }

    public Hotel() {
    }

    // Getters y Setters

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getCategoriaEstrellas() {
        return categoriaEstrellas;
    }

    public void setCategoriaEstrellas(int categoriaEstrellas) {
        this.categoriaEstrellas = categoriaEstrellas;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getNombreDirector() {
        return nombreDirector;
    }

    public void setNombreDirector(String nombreDirector) {
        this.nombreDirector = nombreDirector;
    }

    @Override
    public String toString() {
        return "Hotel{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", categoriaEstrellas=" + categoriaEstrellas +
                ", direccion='" + direccion + '\'' +
                ", telefono='" + telefono + '\'' +
                ", nombreDirector='" + nombreDirector + '\'' +
                '}';
    }

        // equals y hash
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Hotel)) return false;
        Hotel hotel = (Hotel) o;
        return id == hotel.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
