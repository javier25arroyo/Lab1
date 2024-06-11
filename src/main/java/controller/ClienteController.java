package controller;

import model.Cliente.ClienteDAO;
import model.Cliente.ClienteModel;
import model.Conexion;
import model.Empleado.EmpleadoModel;
import view.ConsoleView;

import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;
import java.util.List;

public class ClienteController {
    private ConsoleView viewConsole;

    private ClienteDAO clienteDAO;

    public ClienteController(ConsoleView viewConsole){
        this.viewConsole=viewConsole;
        Connection connection = Conexion.getConnection();
        this.clienteDAO = new ClienteDAO(connection);
    }

    public void agregarClientes(String nombre, String apellido, String email, String telefono, Date fecha_registro){
        ClienteModel datos = new ClienteModel(nombre, apellido,email,telefono,fecha_registro);

        try{
            clienteDAO.agregarClientes(datos);
            viewConsole.showMessage("Inserccion de datos correcta\n");
        }catch (SQLException e){
            viewConsole.errorMessage("Error al insertar datos" + e.getMessage());
        }
    }

    public void actualizarCliente(int id_cliente, String nombre, String apellido, String email, String telefono, Date fecha_registro) {
        ClienteModel cliente = new ClienteModel(id_cliente, nombre, apellido, email, telefono, fecha_registro);
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
    public void obtenerTodosLosClientes(){
        try {
            List<ClienteModel> clientes=clienteDAO.obtenerTodosLosClientes();
            for (ClienteModel cliente: clientes){
                viewConsole.showMessage(cliente.toString()+"\n");
            }
        }catch (SQLException e){
            viewConsole.errorMessage("Error al recuperar los empleados"+e.getMessage());
        }
    }
}
