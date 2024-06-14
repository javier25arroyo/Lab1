package org.conexionMySql;

import controller.*;
import view.ConsoleView;
import java.util.Scanner;
import java.sql.Date;

public class Main {
    static Scanner scanner = new Scanner(System.in);
    static ConsoleView consoleView = new ConsoleView();

    public static void main(String[] args) throws Exception {
        String opcion = "";
        while (!opcion.equals("s")) {
            mostrarMenuPrincipal();
            System.out.println("Ingrese la opción deseada: ");
            opcion = scanner.nextLine();
            ejecutarOpcionMenuPrincipal(opcion);
        }
    }

    public static void mostrarMenuPrincipal() {
        System.out.println("\u001b[33mMenú Principal:\u001b[0m");
        System.out.println("1. Cliente");
        System.out.println("2. Empleado");
        System.out.println("3. Pedido");
        System.out.println("4. Producto");
        System.out.println("5. Proveedor");
        System.out.println("6. Rol");
        System.out.println("7. Cliente_Rol");
        System.out.println("0. Salir");
    }

    public static void ejecutarOpcionMenuPrincipal(String opcion) throws Exception {
        switch (opcion) {
            case "1":
                mostrarSubmenu("Cliente");
                break;
            case "2":
                mostrarSubmenu("Empleado");
                break;
            case "3":
                mostrarSubmenu("Pedido");
                break;
            case "4":
                mostrarSubmenu("Producto");
                break;
            case "5":
                mostrarSubmenu("Proveedor");
                break;
            case "6":
                mostrarSubmenu("Rol");
                break;
            case "7":
                mostrarSubmenu("Cliente_Rol");
                break;
            case "0":
                System.out.println("Saliendo del Programa");
                break;
            default:
                System.out.println("Opción no válida.");
                break;
        }
    }

    public static void mostrarSubmenu(String entidad) throws Exception {
        String opcion = "";
        while (!opcion.equals("v")) {
            System.out.println("\u001b[33mMenú de " + entidad + ":\u001b[0m");
            System.out.println("1. Registrar " + entidad);
            System.out.println("2. Retornar " + entidad);
            System.out.println("3. Actualizar " + entidad);
            System.out.println("4. Eliminar " + entidad);
            System.out.println("v. Volver");
            System.out.println("Ingrese la opción deseada: ");
            opcion = scanner.nextLine();
            ejecutarOpcionSubmenu(entidad, opcion);
        }
    }

    public static void ejecutarOpcionSubmenu(String entidad, String opcion) throws Exception {
        switch (entidad) {
            case "Cliente":
                switch (opcion) {
                    case "1":
                        registroCliente();
                        break;
                    case "2":
                        retornarCliente();
                        break;
                    case "3":
                        actualizarCliente();
                        break;
                    case "4":
                        eliminarCliente();
                        break;
                    case "v":
                        break;
                    default:
                        System.out.println("Opción no válida.");
                        break;
                }
                break;
            case "Empleado":
                switch (opcion) {
                    case "1":
                        registroEmpleado();
                        break;
                    case "2":
                        retornarEmpleado();
                        break;
                    case "3":
                        actualizarEmpleado();
                        break;
                    case "4":
                        eliminarEmpleado();
                        break;
                    case "v":
                        break;
                    default:
                        System.out.println("Opción no válida.");
                        break;
                }
                break;
            case "Pedido":
                switch (opcion) {
                    case "1":
                        registroPedido();
                        break;
                    case "2":
                        retornarPedido();
                        break;
                    case "3":
                        actualizarPedido();
                        break;
                    case "4":
                        eliminarPedido();
                        break;
                    case "v":
                        break;
                    default:
                        System.out.println("Opción no válida.");
                        break;
                }
                break;
            case "Producto":
                switch (opcion) {
                    case "1":
                        registroProducto();
                        break;
                    case "2":
                        retornarProducto();
                        break;
                    case "3":
                        actualizarProducto();
                        break;
                    case "4":
                        eliminarProducto();
                        break;
                    case "v":
                        break;
                    default:
                        System.out.println("Opción no válida.");
                        break;
                }
                break;
            case "Proveedor":
                switch (opcion) {
                    case "1":
                        registroProveedor();
                        break;
                    case "2":
                        retornarProveedor();
                        break;
                    case "3":
                        actualizarProveedor();
                        break;
                    case "4":
                        eliminarProveedor();
                        break;
                    case "v":
                        break;
                    default:
                        System.out.println("Opción no válida.");
                        break;
                }
                break;
            case "Rol":
                switch (opcion) {
                    case "1":
                        registroRol();
                        break;
                    case "2":
                        retornarRol();
                        break;
                    case "3":
                        actualizarRol();
                        break;
                    case "4":
                        eliminarRol();
                        break;
                    case "v":
                        break;
                    default:
                        System.out.println("Opción no válida.");
                        break;
                }
                break;
            case "Cliente_Rol":
                switch (opcion) {
                    case "1":
                        registroClienteRol();
                        break;
                    case "2":
                        retornarClienteRol();
                        break;
                    case "3":
                        actualizarClienteRol();
                        break;
                    case "4":
                        eliminarClienteRol();
                        break;
                    case "v":
                        break;
                    default:
                        System.out.println("Opción no válida.");
                        break;
                    }
                break;
        }
    }


    public static void registroCliente() {
        ClienteController clienteController = new ClienteController(consoleView);
        String nombre = "Alberto";
        String apellido = "Calvo";
        String email = "celestecalvo@gmail.com";
        String telefono = "77777777";
        Date fecha_registro = Date.valueOf("2024-06-07");
        clienteController.agregarClientes(nombre, apellido, email, telefono, fecha_registro);
    }

    public static void retornarCliente() {
        ClienteController clienteController = new ClienteController(consoleView);
        clienteController.obtenerTodosLosClientes();
    }

    public static void actualizarCliente() {
        ClienteController clienteController = new ClienteController(consoleView);
        int id_cliente = 0;
        System.out.println("Digite el id del cliente a actualizar:");
        id_cliente=Integer.parseInt(scanner.nextLine());
        String nombre = "Alberto";
        String apellido="Calvooooooo";
        String email= "celestecalvo@gmail.com";
        String telefono="22222222";
        Date fecha_registro=Date.valueOf("2024-12-25");
        clienteController.actualizarCliente(id_cliente,nombre,apellido,email,telefono,fecha_registro);
    }

    public static void eliminarCliente() {
        ClienteController clienteController = new ClienteController(consoleView);
        int id_cliente = 0;
        System.out.println("Digite el id del cliente a eliminar:");
        id_cliente = Integer.parseInt(scanner.nextLine());
        clienteController.eliminarCliente(id_cliente);
    }

    public static void registroEmpleado() {
        EmpleadoController empleadoController = new EmpleadoController(consoleView);
        String nombre = "Juan";
        String apellido = "Rodriguez";
        String cargo = "Jefe";
        double salario = 22500;
        Date fecha_contratacion = Date.valueOf("2024-08-22");
        empleadoController.agregarEmpleado(nombre, apellido, cargo, salario, fecha_contratacion);
    }

    public static void retornarEmpleado() {
        EmpleadoController empleadoController = new EmpleadoController(consoleView);
        empleadoController.obtenerTodosLosEmpleados();
    }

    public static void actualizarEmpleado() {
        EmpleadoController empleadoController = new EmpleadoController(consoleView);
        int id_empleado = 0;
        System.out.println("Digite el id del empleado a actualizar:");
        id_empleado = Integer.parseInt(scanner.nextLine());
        String nombre = "Pedro";
        String apellido = "Rodriguezzzzzzzzz";
        String cargo = "Jefe";
        double salario = 25000;
        Date fecha_contratacion = Date.valueOf("2024-08-22");
        empleadoController.actualizarEmpleado(id_empleado,nombre,apellido,cargo,salario,fecha_contratacion);
    }

    public static void eliminarEmpleado() {
        EmpleadoController empleadoController = new EmpleadoController(consoleView);
        int id_empleado = 0;
        System.out.println("Digite el id del empleado a eliminar:");
        id_empleado = Integer.parseInt(scanner.nextLine());
        empleadoController.eliminarEmpleado(id_empleado);
    }

    public static void registroPedido() {
        PedidoController pedidoController = new PedidoController(consoleView);
        int clienteId = 4;
        Date fechaPedido = Date.valueOf("2024-06-07");
        double total = 1000;
        String estado = "En Proceso";
        pedidoController.agregarPedido(clienteId, fechaPedido, total, estado);
    }

    public static void retornarPedido() {
PedidoController pedidoController=new PedidoController(consoleView);
pedidoController.obtenerTodosLosPedidos();
    }

    public static void actualizarPedido() {
        PedidoController pedidoController = new PedidoController(consoleView);
        int id_pedido = 0;
        System.out.println("Digite el id del pedido a actualizar:");
        id_pedido = Integer.parseInt(scanner.nextLine());
        int clienteId = 4;
        Date fechaPedido = Date.valueOf("2024-07-07");
        double total = 30000;
        String estado = "Realizado";
        pedidoController.actualizarPedido(id_pedido,clienteId,fechaPedido,total,estado);
    }

    public static void eliminarPedido() {
        PedidoController pedidoController = new PedidoController(consoleView);
        int id_pedido = 0;
        System.out.println("Digite el id del pedido a eliminar:");
        id_pedido = Integer.parseInt(scanner.nextLine());
        pedidoController.eliminarPedido(id_pedido);
    }

    public static void registroProducto() {
        ProductoController productoController = new ProductoController(consoleView);
        String nombreProducto = "Laptop";
        String descripcion = "Laptop HP";
        double precio = 500;
        int stock = 10;
        Date fecha_creacion = Date.valueOf("2024-06-07");
        productoController.agregarProducto(nombreProducto, descripcion, precio, stock, fecha_creacion);
    }

    public static void retornarProducto() {
        ProductoController productoController = new ProductoController(consoleView);
        productoController.obtenerTodosLosProdutos();
    }

    public static void actualizarProducto() {
        ProductoController productoController = new ProductoController(consoleView);
        int id_producto = 0;
        System.out.println("Digite el id del producto a actualizar:");
        id_producto = Integer.parseInt(scanner.nextLine());
        String nombreProducto = "LaptopP";
        String descripcion = "Laptop HPP";
        double precio = 50000;
        int stock = 200;
        Date fecha_creacion = Date.valueOf("2028-06-07");
        productoController.actualizarProducto(id_producto,nombreProducto,descripcion,precio,stock,fecha_creacion);
    }

    public static void eliminarProducto() {
        ProductoController productoController = new ProductoController(consoleView);
        int id_producto = 0;
        System.out.println("Digite el id del producto a eliminar:");
        id_producto = Integer.parseInt(scanner.nextLine());
        productoController.eliminarProducto(id_producto);
    }

    public static void registroProveedor() {
        ProveedorController proveedorController = new ProveedorController(consoleView);
        String nombre = "Javier";
        String direccion = "Cartago";
        String telefono = "88888888";
        String email = "javier@gmail.com";
        Date fecha_registro = Date.valueOf("2025-12-25");
        proveedorController.agregarProveedor(nombre, direccion, telefono, email, fecha_registro);
    }

    public static void retornarProveedor() {
        ProveedorController proveedorController = new ProveedorController(consoleView);
        proveedorController.obtenerTodosLosProveedores();
    }

    public static void actualizarProveedor() {
        ProveedorController proveedorController = new ProveedorController(consoleView);
        int id_proveedor = 0;
        System.out.println("Digite el id del proveedor a actualizar:");
        id_proveedor = Integer.parseInt(scanner.nextLine());
        String nombre = "Javierrrr";
        String direccion = "Limon";
        String telefono = "12121212";
        String email = "javier123@gmail.com";
        Date fecha_registro = Date.valueOf("2027-12-25");
proveedorController.actualizarProveedor(id_proveedor,nombre,direccion,telefono,email,fecha_registro);
    }

    public static void eliminarProveedor() {
        ProveedorController proveedorController = new ProveedorController(consoleView);
        int id_proveedor = 0;
        System.out.println("Digite el id del proveedor a eliminar:");
        id_proveedor = Integer.parseInt(scanner.nextLine());
        proveedorController.eliminarProveedor(id_proveedor);
    }

    public static void registroRol() {
        RolController rolController = new RolController(consoleView);
        String nombre = "Administrador";
        String descripcion = "Administrador de la empresa";
        rolController.agregarRol(nombre, descripcion);
    }

    public static void retornarRol() {
        RolController rolController = new RolController(consoleView);
        rolController.obtenerTodosLosRoles();
    }

    public static void actualizarRol() {
        RolController rolController = new RolController(consoleView);
        int id_rol = 1;
        System.out.println("Digite el id del rol a actualizar:");
        id_rol = Integer.parseInt(scanner.nextLine());
        String nombre = "Nada";
        String descripcion = "Nada de la empresa";
        rolController.actualizarRol(id_rol, nombre, descripcion);
    }

    public static void eliminarRol() {
        RolController rolController = new RolController(consoleView);
        int id_rol = 0;
        System.out.println("Digite el id del rol a eliminar:");
        id_rol = Integer.parseInt(scanner.nextLine());
        rolController.eliminarRol(id_rol);
    }

    public static void registroClienteRol() {
        ClienteRolController clienteRolController = new ClienteRolController(consoleView);
        int cliente_id = 1;
        int rol_id = 1;
        clienteRolController.agregarClienteRol(cliente_id, rol_id);
    }

    public static void retornarClienteRol() {
        ClienteRolController clienteRolController = new ClienteRolController(consoleView);
        clienteRolController.obtenerClienteRol();
    }

    public static void actualizarClienteRol() {
        ClienteRolController clienteRolController = new ClienteRolController(consoleView);
        int id = 4;
        System.out.println("Digite el id del cliente_rol a actualizar:");
        id = Integer.parseInt(scanner.nextLine());
        int FK_idCliente = 99;
        int FK_idRol = 69;
        clienteRolController.actualizarClienteRol(id, FK_idCliente, FK_idRol);
    }

    public static void eliminarClienteRol() {
        ClienteRolController clienteRolController = new ClienteRolController(consoleView);
        int id = 0;
        System.out.println("Digite el id del cliente_rol a eliminar:");
        id = Integer.parseInt(scanner.nextLine());
        clienteRolController.eliminarClienteRol(id);
    }
}