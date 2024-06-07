package org.conexionMySql;

import controller.*;
import view.ConsoleView;

import java.sql.Date;

public class Main {
    public static void main(String[] args) {
        System.out.printf("Hello MySQL!\n");
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

        PedidoController pedidoController = new PedidoController(consoleView);
        int idCliente= 4;
        Date fechaPedido=Date.valueOf("2024-06-07");
        double total=1000;
        String estado="EnProceso";
        pedidoController.agregarPedido(idCliente,fechaPedido,total,estado);

        ProductoController productoController = new ProductoController(consoleView);
        String nombreProducto="Laptop";
        String descripcion="Laptop HP";
        double precio=500;
        int stock=10;
        Date fecha_creacion=Date.valueOf("2024-06-07");
        productoController.agregarProducto(nombreProducto,descripcion,precio,stock,fecha_creacion);

        ProveedorController proveedorController = new ProveedorController(consoleView);
        String nombreP="Javier";
        String direccion="Cartago";
        String telefonoP="88888888";
        String emailP="javier@gmail.com";
        Date fecha_registroP=Date.valueOf("2025-12-25");
        proveedorController.agregarProveedor(nombreP,direccion,telefonoP,emailP,fecha_registroP);
    }
}