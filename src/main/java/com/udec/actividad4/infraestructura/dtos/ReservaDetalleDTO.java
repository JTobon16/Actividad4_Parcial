
package com.udec.actividad4.infraestructura.dtos;



public class ReservaDetalleDTO {
    private String nombreCliente;
    private int idReserva;
    private String fechaInicio;
    private String fechaFin;
    private String habitaciones;

    public ReservaDetalleDTO(String nombreCliente, int idReserva, String fechaInicio, String fechaFin, String habitaciones) {
        this.nombreCliente = nombreCliente;
        this.idReserva = idReserva;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
        this.habitaciones = habitaciones;
    }

    // Getters
    public String getNombreCliente() { return nombreCliente; }
    public int getIdReserva() { return idReserva; }
    public String getFechaInicio() { return fechaInicio; }
    public String getFechaFin() { return fechaFin; }
    public String getHabitaciones() { return habitaciones; }
}
