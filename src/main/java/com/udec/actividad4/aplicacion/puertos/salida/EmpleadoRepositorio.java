package com.udec.actividad4.aplicacion.puertos.salida;

import com.udec.actividad4.dominio.modelo.Empleado;

import java.util.List;
import java.util.Optional;

public interface EmpleadoRepositorio {

    void guardar(Empleado empleado);

    Optional<Empleado> buscarPorDni(String dni);

    List<Empleado> listarTodos();

    void actualizar(Empleado empleado);

    void eliminar(String dni);
}
