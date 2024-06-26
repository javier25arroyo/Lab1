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
        String nombre;
        String apellido;
        String email;
        String telefono;
        Date fecha_registro = null;

        System.out.println("Digite el nombre del cliente:");
       nombre = scanner.nextLine();
        System.out.println("Digite el apellido del cliente:");
        apellido = scanner.nextLine();
        System.out.println("Digite el email del cliente:");
        email = scanner.nextLine();
        System.out.println("Digite el teléfono del cliente:");
        telefono = scanner.nextLine();
        System.out.println("Digite la fecha de registro del cliente (Formato: yyyy-MM-dd):");
        fecha_registro= Date.valueOf(scanner.nextLine());


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
        id_cliente = Integer.parseInt(scanner.nextLine());
        String nombre;
        String apellido;
        String email;
        String telefono;
        Date fecha_registro = null;

        System.out.println("Digite el nombre del cliente:");
        nombre = scanner.nextLine();
        System.out.println("Digite el apellido del cliente:");
        apellido = scanner.nextLine();
        System.out.println("Digite el email del cliente:");
        email = scanner.nextLine();
        System.out.println("Digite el teléfono del cliente:");
        telefono = scanner.nextLine();
        System.out.println("Digite la fecha de registro del cliente (Formato: yyyy-MM-dd):");
        fecha_registro= Date.valueOf(scanner.nextLine());
        clienteController.actualizarCliente(id_cliente, nombre, apellido, email, telefono, fecha_registro);
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
        String nombre;
        String apellido;
        String cargo;
        double salario;
        Date fecha_contratacion = null;

        System.out.println("Digite el nombre del empleado:");
        nombre = scanner.nextLine();
        System.out.println("Digite el apellido del empleado:");
        apellido = scanner.nextLine();
        System.out.println("Digite el cargo del empleado:");
        cargo = scanner.nextLine();
        System.out.println("Digite el salario del empleado:");
        salario = Double.parseDouble(scanner.nextLine());
        System.out.println("Digite la fecha de contratacion del empleado(Formato: yyyy-MM-dd):");
        fecha_contratacion=Date.valueOf(scanner.nextLine());
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
        String nombre;
        String apellido;
        String cargo;
        double salario;
        Date fecha_contratacion = null;

        System.out.println("Digite el nombre del empleado:");
        nombre = scanner.nextLine();
        System.out.println("Digite el apellido del empleado:");
        apellido = scanner.nextLine();
        System.out.println("Digite el cargo del empleado:");
        cargo = scanner.nextLine();
        System.out.println("Digite el salario del empleado:");
        salario = Double.parseDouble(scanner.nextLine());
        System.out.println("Digite la fecha de contratacion del empleado(Formato: yyyy-MM-dd):");
        fecha_contratacion=Date.valueOf(scanner.nextLine());
        empleadoController.actualizarEmpleado(id_empleado, nombre, apellido, cargo, salario, fecha_contratacion);
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
        int clienteId;
        Date fechaPedido = null;
        double total;
        String estado;


        System.out.println("Digite el ID del cliente:");
        clienteId = Integer.parseInt(scanner.nextLine());
        System.out.println("Digite la fecha del pedido (Formato: yyyy-MM-dd):");
        fechaPedido=Date.valueOf(scanner.nextLine());
        System.out.println("Digite el total del pedido:");
        total = Double.parseDouble(scanner.nextLine());
        System.out.println("Digite el estado del pedido:");
        estado = scanner.nextLine();

        pedidoController.agregarPedido(clienteId, fechaPedido, total, estado);
    }

    public static void retornarPedido() {
        PedidoController pedidoController = new PedidoController(consoleView);
        pedidoController.obtenerTodosLosPedidos();
    }

    public static void actualizarPedido() {
        PedidoController pedidoController = new PedidoController(consoleView);
        int id_pedido = 0;
        System.out.println("Digite el id del pedido a actualizar:");
        id_pedido = Integer.parseInt(scanner.nextLine());
        int clienteId;
        Date fechaPedido = null;
        double total;
        String estado;


        System.out.println("Digite el ID del cliente:");
        clienteId = Integer.parseInt(scanner.nextLine());
        System.out.println("Digite la fecha del pedido (Formato: yyyy-MM-dd):");
        fechaPedido=Date.valueOf(scanner.nextLine());
        System.out.println("Digite el total del pedido:");
        total = Double.parseDouble(scanner.nextLine());
        System.out.println("Digite el estado del pedido:");
        estado = scanner.nextLine();
        pedidoController.actualizarPedido(id_pedido, clienteId, fechaPedido, total, estado);
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
        String nombreProducto;
        String descripcion;
        double precio;
        int stock;
        Date fecha_creacion=null;
        System.out.println("Digite el nombre del producto a registrar:");
        nombreProducto=scanner.nextLine();
        System.out.println("Digite la descripcion del producto a registrar:");
        descripcion=scanner.nextLine();
        System.out.println("Digite el precio del producto:");
        precio=Double.parseDouble(scanner.nextLine());
        System.out.println("Digite el stock del producto:");
        stock=Integer.parseInt(scanner.nextLine());
        System.out.println("Digite la fecha de creacion del producto (Formato: yyyy-MM-dd):");
        fecha_creacion= Date.valueOf(scanner.nextLine());

        productoController.agregarProducto(nombreProducto, descripcion, precio, stock, fecha_creacion);
    }

    public static void retornarProducto() {
        ProductoController productoController = new ProductoController(consoleView);
        productoController.obtenerTodosLosProdutos();
    }

    public static void actualizarProducto() {
        ProductoController productoController = new ProductoController(consoleView);
        int id_producto = 0;
        String nombreProducto;
        String descripcion;
        double precio;
        int stock;
        Date fecha_creacion=null;
        System.out.println("Digite el id del producto a actualizar:");
        id_producto = Integer.parseInt(scanner.nextLine());
        System.out.println("Digite el nombre del producto a registrar:");
        nombreProducto=scanner.nextLine();
        System.out.println("Digite la descripcion del producto a registrar:");
        descripcion=scanner.nextLine();
        System.out.println("Digite el precio del producto:");
        precio=Double.parseDouble(scanner.nextLine());
        System.out.println("Digite el stock del producto:");
        stock=Integer.parseInt(scanner.nextLine());
        System.out.println("Digite la fecha de creacion del producto (Formato: yyyy-MM-dd):");
        fecha_creacion= Date.valueOf(scanner.nextLine());

        productoController.actualizarProducto(id_producto, nombreProducto, descripcion, precio, stock, fecha_creacion);
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
        proveedorController.actualizarProveedor(id_proveedor, nombre, direccion, telefono, email, fecha_registro);
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
        int id_rol = 0;
        System.out.println("Digite el id del rol a actualizar:");
        id_rol = Integer.parseInt(scanner.nextLine());
        String nombre = "Nada";
        String descripcion = "Administrador de la empresa";
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