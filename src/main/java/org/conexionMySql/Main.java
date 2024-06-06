package org.conexionMySql;

import controller.ClienteController;
import controller.EmpleadoController;
import controller.ProveedorController;
import view.ConsoleView;

import java.sql.Date;

public class Main {
    public static void main(String[] args) {
        System.out.printf("Hello MySQL!");
        ConsoleView viewConsole = new ConsoleView();
        ConsoleView consoleView = new ConsoleView();
        ClienteController clienteController = new ClienteController(consoleView);
        String nombre = "Alberto";
        String apellido="Calvo";
        String email="celestecalvo@gmail.com";
        String telefono="77777777";
        Date fecha_registro= Date.valueOf("2024-06-07");
        clienteController.agregarClientes(nombre, apellido, email, telefono, fecha_registro);

        EmpleadoController empleadoController = new EmpleadoController(consoleView);
        String nombreE="Juan";
        String apellidoE="Rodriguez";
        String cargo="Jefe";
        double salario=22500;
        Date fecha_contratacion= Date.valueOf("2024-08-22");
        empleadoController.agregarEmpleado(nombreE,apellidoE,cargo,salario,fecha_contratacion);

        ProveedorController proveedorController = new ProveedorController(consoleView);
        String nombreP="Javier";
        String direccion="Cartago";
        String telefonoP="88888888";
        String emailP="javier@gmail.com";
        Date fecha_registroP=Date.valueOf("2025-12-25");
        proveedorController.agregarProveedor(nombreP,direccion,telefonoP,emailP,fecha_registroP);
    }
}