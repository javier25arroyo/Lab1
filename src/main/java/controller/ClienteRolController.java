package controller;

import model.ClienteRol.ClienteRolDAO;
import model.ClienteRol.ClienteRolModel;
import model.Conexion;
import view.ConsoleView;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ClienteRolController {
    private ConsoleView viewConsole;

    public ClienteRolDAO clienteRolDAO;

    public ClienteRolController(ConsoleView viewConsole){
        this.viewConsole = viewConsole;
        Connection connection = Conexion.getConnection();
        this.clienteRolDAO = new ClienteRolDAO(connection);
    }

    public void agregarClienteRol(int FK_idCliente, int FK_idRol){
        ClienteRolModel datos = new ClienteRolModel(FK_idCliente, FK_idRol);

        try{
            clienteRolDAO.agregarClienteRol(datos);
            viewConsole.showMessage("Inserccion de datos correcta\n");
        }catch (SQLException e){
            viewConsole.errorMessage("Error al insertar datos" + e.getMessage());
        }
    }

    public void eliminarClienteRol(int id) {
        try {
            clienteRolDAO.eliminarClienteRol(id);
            viewConsole.showMessage("Eliminación de clienteRol correcta\n");
        } catch (SQLException e) {
            viewConsole.errorMessage("Error al eliminar clienteRol: " + e.getMessage());
        }
    }

    public void actualizarClienteRol(int id, int FK_idCliente, int FK_idRol) {
        ClienteRolModel datos = new ClienteRolModel(id, FK_idCliente, FK_idRol);
        try {
            clienteRolDAO.actualizarClienteRol(datos);
            viewConsole.showMessage("Actualización de clienteRol correcta\n");
        } catch (SQLException e) {
            viewConsole.errorMessage("Error al actualizar clienteRol: " + e.getMessage());
        }
    }

    public List<ClienteRolModel> obtenerClienteRol(){
        try {
            return clienteRolDAO.obtenerClienteRol();
        } catch (SQLException e) {
            viewConsole.errorMessage("Error al recuperar los clienteRol: " + e.getMessage());
            return new ArrayList<>();
        }

    }

    public ClienteRolModel getClienteRolByID(int id) {
        try{
            return clienteRolDAO.getClienteRolByID(id);
        }catch (SQLException e){
            viewConsole.errorMessage("Error al recuperar el clienteRol: " + e.getMessage());
            return null;
        }
    }
}
