package com.udec.actividad4.dominio.modelo;

import java.util.Objects;

public class DetalleActividad {

    private int id;
    private int estanciaId;
    private int actividadId;
    private int cantidadPersonas;

    public DetalleActividad() {
    }

    public DetalleActividad(int id, int estanciaId, int actividadId, int cantidadPersonas) {
        this.id = id;
        this.estanciaId = estanciaId;
        this.actividadId = actividadId;
        this.cantidadPersonas = cantidadPersonas;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getEstanciaId() {
        return estanciaId;
    }

    public void setEstanciaId(int estanciaId) {
        this.estanciaId = estanciaId;
    }

    public int getActividadId() {
        return actividadId;
    }

    public void setActividadId(int actividadId) {
        this.actividadId = actividadId;
    }

    public int getCantidadPersonas() {
        return cantidadPersonas;
    }

    public void setCantidadPersonas(int cantidadPersonas) {
        this.cantidadPersonas = cantidadPersonas;
    }

    @Override
    public String toString() {
        return "DetalleActividad{" +
                "id=" + id +
                ", estanciaId=" + estanciaId +
                ", actividadId=" + actividadId +
                ", cantidadPersonas=" + cantidadPersonas +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof DetalleActividad)) return false;
        DetalleActividad that = (DetalleActividad) o;
        return id == that.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
