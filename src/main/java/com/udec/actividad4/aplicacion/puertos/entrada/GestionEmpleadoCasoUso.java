
package com.udec.actividad4.aplicacion.puertos.entrada;

import com.udec.actividad4.dominio.modelo.Empleado;

import java.util.List;

public interface GestionEmpleadoCasoUso {

    void registrarEmpleado(Empleado empleado);

    Empleado obtenerEmpleadoPorId(int id);

    List<Empleado> listarEmpleadosPorHotel(int hotelId);

    void eliminarEmpleado(int id);
}
