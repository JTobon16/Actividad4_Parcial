package com.udec.actividad4.aplicacion.puertos.entrada;

import com.udec.actividad4.dominio.modelo.Empleado;

import java.util.List;

public interface EmpleadoServicio {

    void registrarEmpleado(Empleado empleado);

    Empleado buscarEmpleadoPorDni(String dni);

    List<Empleado> listarEmpleados();

    void actualizarEmpleado(Empleado empleado);

    void eliminarEmpleado(String dni);
}
