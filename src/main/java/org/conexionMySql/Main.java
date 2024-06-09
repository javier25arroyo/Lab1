package org.conexionMySql;

import controller.*;
import view.ConsoleView;

import java.io.IOException;
import java.util.Scanner;
import java.sql.Date;

public class Main {
    static Scanner scanner = new Scanner(System.in);
    ConsoleView viewConsole = new ConsoleView();
    static ConsoleView consoleView = new ConsoleView();
    public static void main(String[] args)throws Exception, IOException {

        String opcion = "";
        while (!opcion.equals("s")) {
            mostrarMenu();
            System.out.println("Ingrese la opción deseada: ");
            opcion = scanner.nextLine();
            ejecutarOpcion(opcion);
        }

    }
    public static void mostrarMenu(){
        System.out.println("\u001b[33mMenú del programa:\u001b[0m");
        System.out.println("1. Registrar Cliente");
        System.out.println("2. Registrar Empleado");
        System.out.println("3. Registrar pedido");
        System.out.println("4. Registrar producto");
        System.out.println("5. Registrar proveedor");
        System.out.println("6. Eliminar cliente");
        System.out.println("7. Eliminar empleado");
        System.out.println("8. Eliminar pedido");
        System.out.println("9. Eliminar producto");
        System.out.println("10. Eliminar proveedor");
        System.out.println("s. Salir");
    }
    public static void ejecutarOpcion(String opcion)throws Exception{
        switch(opcion){
            case "1":
                registroCliente();
                break;
            case "2":
                registroEmpleado();
                break;
            case "3":
                registroPedido();
            case "4":
                registroProducto();
                break;
            case "5":
                registroProveedor();
                break;
            case "6":
                EliminarCliente();
                break;
            case "7":
                EliminarEmpleado();
                break;
            case "8":
                EliminarPedido();
                break;
            case "9":
                EliminarProducto();
                break;
            case "10":
                EliminarProveedor();
                break;
            case "s":
                System.out.println("Saliendo del Programa");
                break;
        }
    }
    public static void registroCliente(){
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
    public static void registroEmpleado() {
        ConsoleView viewConsole = new ConsoleView();
        ConsoleView consoleView = new ConsoleView();
        EmpleadoController empleadoController = new EmpleadoController(consoleView);
        String nombreE="Juan";
        String apellidoE="Rodriguez";
        String cargo="Jefe";
        double salario=22500;
        Date fecha_contratacion= Date.valueOf("2024-08-22");
        empleadoController.agregarEmpleado(nombreE,apellidoE,cargo,salario,fecha_contratacion);
    }
    public static void registroPedido() {
        ConsoleView viewConsole = new ConsoleView();
        ConsoleView consoleView = new ConsoleView();
        PedidoController pedidoController = new PedidoController(consoleView);
        int clienteId= 4;
        Date fechaPedido=Date.valueOf("2024-06-07");
        double total=1000;
        String estado="En Proceso";
        pedidoController.agregarPedido(clienteId,fechaPedido,total,estado);
    }
    public static void registroProducto() {
        ConsoleView viewConsole = new ConsoleView();
        ConsoleView consoleView = new ConsoleView();
        ProductoController productoController = new ProductoController(consoleView);
        String nombreProducto="Laptop";
        String descripcion="Laptop HP";
        double precio=500;
        int stock=10;
        Date fecha_creacion=Date.valueOf("2024-06-07");
        productoController.agregarProducto(nombreProducto,descripcion,precio,stock,fecha_creacion);
    }
    public static void registroProveedor() {
        ConsoleView viewConsole = new ConsoleView();
        ConsoleView consoleView = new ConsoleView();
        ProveedorController proveedorController = new ProveedorController(consoleView);
        String nombreP="Javier";
        String direccion="Cartago";
        String telefonoP="88888888";
        String emailP="javier@gmail.com";
        Date fecha_registroP=Date.valueOf("2025-12-25");
        proveedorController.agregarProveedor(nombreP,direccion,telefonoP,emailP,fecha_registroP);
    }
    public static void EliminarCliente(){
        ClienteController clienteController = new ClienteController(consoleView);
        int id_cliente=0;
        System.out.println("Digite el id del cliente a eliminar:");
        id_cliente=Integer.parseInt(scanner.nextLine());
        clienteController.eliminarCliente(id_cliente);
    }
    public static void EliminarEmpleado(){
        EmpleadoController empleadoController=new EmpleadoController(consoleView);
        int id_empleado=0;
        System.out.println("Digite el id del empleado a eliminar:");
        id_empleado=Integer.parseInt(scanner.nextLine());
        empleadoController.eliminarEmpleado(id_empleado);
    }
    public static void EliminarPedido(){
        PedidoController pedidoController=new PedidoController(consoleView);
        int id_pedido=0;
        System.out.println("Digite el id del pedido a eliminar:");
        id_pedido=Integer.parseInt(scanner.nextLine());
        pedidoController.eliminarPedido(id_pedido);
    }
    public static void EliminarProducto(){
        ProductoController productoController=new ProductoController(consoleView);
        int id_producto=0;
        System.out.println("Digite el id del producto a eliminar:");
        id_producto=Integer.parseInt(scanner.nextLine());
        productoController.eliminarProducto(id_producto);
    }
    public static void EliminarProveedor(){
        ProveedorController proveedorController=new ProveedorController(consoleView);
        int id_proveedor=0;
        System.out.println("Digite el id del proveedor a eliminar:");
        id_proveedor=Integer.parseInt(scanner.nextLine());
        proveedorController.eliminarProveedor(id_proveedor);
    }
}