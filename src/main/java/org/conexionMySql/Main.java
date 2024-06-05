package org.conexionMySql;

import controller.ClienteController;
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
    }
}