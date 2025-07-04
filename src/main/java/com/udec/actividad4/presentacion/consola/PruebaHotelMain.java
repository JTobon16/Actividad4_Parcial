/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package com.udec.actividad4.presentacion.consola;

import com.udec.actividad4.dominio.modelo.Hotel;
import com.udec.actividad4.infraestructura.config.ConexionBD;
import com.udec.actividad4.infraestructura.repositorio.HotelRepositorioImpl;

import java.sql.Connection;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class PruebaHotelMain {

    public static void main(String[] args) {
        try (Connection conexion = ConexionBD.obtenerConexion()) {
            if (conexion == null) {
                System.out.println("No se pudo establecer conexion.");
                return;
            }

            HotelRepositorioImpl repo = new HotelRepositorioImpl(conexion);
            Scanner scanner = new Scanner(System.in);

            while (true) {
                System.out.println("\n===== MENU HOTEL =====");
                System.out.println("1. Insertar hotel");
                System.out.println("2. Listar hoteles");
                System.out.println("3. Buscar hotel por ID");
                System.out.println("4. Actualizar hotel");
                System.out.println("5. Eliminar hotel");
                System.out.println("6. Buscar por nombre o categoria");
                System.out.println("0. Salir");
                System.out.print("Seleccione una opcion: ");

                int opcion = scanner.nextInt();
                scanner.nextLine(); // limpiar buffer

                switch (opcion) {
                    case 1 -> {
                        Hotel h = new Hotel();
                        System.out.print("ID: ");
                        h.setId(scanner.nextInt());
                        scanner.nextLine();
                        System.out.print("Nombre: ");
                        h.setNombre(scanner.nextLine());
                        System.out.print("Categoria estrellas: ");
                        h.setCategoriaEstrellas(scanner.nextInt());
                        scanner.nextLine();
                        System.out.print("Direccion: ");
                        h.setDireccion(scanner.nextLine());
                        System.out.print("Telefono: ");
                        h.setTelefono(scanner.nextLine());
                        System.out.print("Nombre del director: ");
                        h.setNombreDirector(scanner.nextLine());

                        repo.guardar(h);
                        System.out.println("Hotel insertado.");
                    }

                    case 2 -> {
                        List<Hotel> hoteles = repo.listarTodos();
                        hoteles.forEach(System.out::println);
                    }

                    case 3 -> {
                        System.out.print("Ingrese ID del hotel: ");
                        int id = scanner.nextInt();
                        Optional<Hotel> encontrado = repo.buscarPorId(id);
                        encontrado.ifPresentOrElse(
                            System.out::println,
                            () -> System.out.println("Hotel no encontrado.")
                        );
                    }

                    case 4 -> {
                        System.out.print("ID del hotel a actualizar: ");
                        int id = scanner.nextInt();
                        scanner.nextLine();

                        Optional<Hotel> hotelActual = repo.buscarPorId(id);
                        if (hotelActual.isPresent()) {
                            Hotel h = hotelActual.get();
                            System.out.print("Nuevo nombre (" + h.getNombre() + "): ");
                            h.setNombre(scanner.nextLine());
                            System.out.print("Nueva categoria (" + h.getCategoriaEstrellas() + "): ");
                            h.setCategoriaEstrellas(scanner.nextInt());
                            scanner.nextLine();
                            System.out.print("Nueva direccion (" + h.getDireccion() + "): ");
                            h.setDireccion(scanner.nextLine());
                            System.out.print("Nuevo telefono (" + h.getTelefono() + "): ");
                            h.setTelefono(scanner.nextLine());
                            System.out.print("Nuevo director (" + h.getNombreDirector() + "): ");
                            h.setNombreDirector(scanner.nextLine());

                            repo.actualizar(h);
                            System.out.println("Hotel actualizado.");
                        } else {
                            System.out.println("Hotel no encontrado.");
                        }
                    }

                    case 5 -> {
                        System.out.print("ID del hotel a eliminar: ");
                        int id = scanner.nextInt();
                        repo.eliminar(id);
                        System.out.println("Hotel eliminado.");
                    }

                    case 6 -> {
                        System.out.print("Buscar nombre que contenga: ");
                        String nombre = scanner.nextLine();
                        System.out.print("O categoria estrella (numero): ");
                        int cat = scanner.nextInt();
                        List<Hotel> resultado = repo.buscarPorNombreOCategoria(nombre, cat);
                        resultado.forEach(System.out::println);
                    }

                    case 0 -> {
                        System.out.println("Saliendo...");
                        return;
                    }

                    default -> System.out.println("Opcion invalida.");
                }
            }

        } catch (Exception e) {
            System.err.println("Error general: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
