package controller;

import model.Cliente.ClienteDAO;
import model.Cliente.ClienteModel;
import model.Conexion;
import model.Empleado.EmpleadoModel;
import view.ConsoleView;

import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ClienteController {
    private ConsoleView viewConsole;

    private ClienteDAO clienteDAO;

    public ClienteController(ConsoleView viewConsole){
        this.viewConsole=viewConsole;
        Connection connection = Conexion.getConnection();
        this.clienteDAO = new ClienteDAO(connection);
    }

    public void agregarClientes(String nombre, String apellido, String email, String telefono, Date fecha_registro, String contrasena){
        ClienteModel datos = new ClienteModel(nombre, apellido,email,telefono,fecha_registro,contrasena);

        try{
            clienteDAO.agregarClientes(datos);
            viewConsole.showMessage("Inserccion de datos correcta\n");
        }catch (SQLException e){
            viewConsole.errorMessage("Error al insertar datos" + e.getMessage());
        }
    }

    public void actualizarCliente(int id_cliente, String nombre, String apellido, String email, String telefono, Date fecha_registro, String contrasena) {
        ClienteModel cliente = new ClienteModel(id_cliente, nombre, apellido,email,telefono,fecha_registro,contrasena);
        try {
            clienteDAO.actualizarCliente(cliente);
            viewConsole.showMessage("Actualización de cliente correcta\n");
        } catch (SQLException e) {
            viewConsole.errorMessage("Error al actualizar cliente: " + e.getMessage());
        }
    }

    public void eliminarCliente(int cliente_id) {
        try {
            clienteDAO.eliminarCliente(cliente_id);
            viewConsole.showMessage("Eliminación de cliente correcta\n");
        } catch (SQLException e) {
            viewConsole.errorMessage("Error al eliminar cliente: " + e.getMessage());
        }
    }
    public List<ClienteModel> obtenerTodosLosClientes(){
        try {
            return clienteDAO.obtenerTodosLosClientes();
        } catch (SQLException e) {
            viewConsole.errorMessage("Error al recuperar los clientes: " + e.getMessage());
            return new ArrayList<>();
        }
    }
    public ClienteModel getClienteByID(int id) {
        try {
            return clienteDAO.getClienteByID(id);
        } catch (SQLException e) {
            viewConsole.errorMessage("Error al recuperar el cliente: " + e.getMessage());
            return null;
        }
    }
}
