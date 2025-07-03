package com.udec.actividad4.dominio.modelo;

import java.util.Objects;

public class DetalleFactura {

    private int id;
    private int facturaId;
    private String concepto;      // Ej: "Habitación Suite", "Suplemento Temporada Alta", "Actividad Masaje"
    private double valorUnitario;
    private int cantidad;
    private double subtotal;

    public DetalleFactura() {
    }

    public DetalleFactura(int id, int facturaId, String concepto, double valorUnitario, int cantidad, double subtotal) {
        this.id = id;
        this.facturaId = facturaId;
        this.concepto = concepto;
        this.valorUnitario = valorUnitario;
        this.cantidad = cantidad;
        this.subtotal = subtotal;
    }

    // Getters y Setters

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getFacturaId() {
        return facturaId;
    }

    public void setFacturaId(int facturaId) {
        this.facturaId = facturaId;
    }

    public String getConcepto() {
        return concepto;
    }

    public void setConcepto(String concepto) {
        this.concepto = concepto;
    }

    public double getValorUnitario() {
        return valorUnitario;
    }

    public void setValorUnitario(double valorUnitario) {
        this.valorUnitario = valorUnitario;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public double getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(double subtotal) {
        this.subtotal = subtotal;
    }

    @Override
    public String toString() {
        return "DetalleFactura{" +
                "id=" + id +
                ", facturaId=" + facturaId +
                ", concepto='" + concepto + '\'' +
                ", valorUnitario=" + valorUnitario +
                ", cantidad=" + cantidad +
                ", subtotal=" + subtotal +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof DetalleFactura)) return false;
        DetalleFactura that = (DetalleFactura) o;
        return id == that.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
