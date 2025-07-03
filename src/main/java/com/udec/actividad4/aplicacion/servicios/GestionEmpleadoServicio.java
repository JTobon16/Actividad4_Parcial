package com.udec.actividad4.aplicacion.servicios;

import com.udec.actividad4.aplicacion.puertos.entrada.EmpleadoServicio;
import com.udec.actividad4.aplicacion.puertos.salida.EmpleadoRepositorio;
import com.udec.actividad4.dominio.excepciones.EntidadNoEncontradaException;
import com.udec.actividad4.dominio.modelo.Empleado;

import java.util.List;

public class GestionEmpleadoServicio implements EmpleadoServicio {

    private final EmpleadoRepositorio empleadoRepositorio;

    public GestionEmpleadoServicio(EmpleadoRepositorio empleadoRepositorio) {
        this.empleadoRepositorio = empleadoRepositorio;
    }

    @Override
    public void registrarEmpleado(Empleado empleado) {
        empleadoRepositorio.guardar(empleado);
    }

    @Override
    public Empleado buscarEmpleadoPorDni(String dni) {
        return empleadoRepositorio.buscarPorDni(dni)
                .orElseThrow(() -> new EntidadNoEncontradaException("Empleado no encontrado con DNI: " + dni));
    }

    @Override
    public List<Empleado> listarEmpleados() {
        return empleadoRepositorio.listarTodos();
    }

    @Override
    public void actualizarEmpleado(Empleado empleado) {
        empleadoRepositorio.actualizar(empleado);
    }

    @Override
    public void eliminarEmpleado(String dni) {
        Empleado empleado = empleadoRepositorio.buscarPorDni(dni)
                .orElseThrow(() -> new EntidadNoEncontradaException("Empleado no encontrado con DNI: " + dni));
        empleadoRepositorio.eliminar(dni);
    }
}
