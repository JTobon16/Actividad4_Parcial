package com.udec.actividad4.aplicacion.puertos.salida;

import com.udec.actividad4.dominio.modelo.Factura;

import java.util.List;
import java.util.Optional;

public interface FacturaRepositorio {

    void guardar(Factura factura);

    Optional<Factura> buscarPorId(int id);

    List<Factura> listarTodos();

    void eliminar(int id);
}
