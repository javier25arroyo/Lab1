package controller;

import model.Cliente.ClienteModel;
import model.Rol.RolDAO;
import view.ConsoleView;
import model.Conexion;
import model.Rol.RolModel;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RolController {
    private ConsoleView viewConsole;

    public RolDAO rolDAO;

    public RolController(ConsoleView viewConsole){
        this.viewConsole=viewConsole;
        Connection connection = Conexion.getConnection();
        this.rolDAO = new RolDAO(connection);
    }

    public void agregarRol(String nombre, String descripcion){
        RolModel datos = new RolModel(nombre, descripcion);

        try{
            rolDAO.agregarRol(datos);
            viewConsole.showMessage("Inserccion de datos correcta\n");
        }catch (SQLException e){
            viewConsole.errorMessage("Error al insertar datos" + e.getMessage());
        }
    }

    public void eliminarRol(int rol_id) {
        try {
            rolDAO.eliminarRol(rol_id);
            viewConsole.showMessage("Eliminación de rol correcta\n");
        } catch (SQLException e) {
            viewConsole.errorMessage("Error al eliminar rol: " + e.getMessage());
        }
    }

    public void actualizarRol(int rol_id, String nombre, String descripcion) {
        RolModel datos = new RolModel(rol_id, nombre, descripcion);
        try {
            rolDAO.actualizarRol(datos);
            viewConsole.showMessage("Actualización de rol correcta\n");
        } catch (SQLException e) {
            viewConsole.errorMessage("Error al actualizar rol: " + e.getMessage());
        }
    }

    public List<RolModel> obtenerTodosLosRoles(){
        try {
            return rolDAO.obtenerTodosLosRoles();
        } catch (SQLException e) {
            viewConsole.errorMessage("Error al recuperar los clientes: " + e.getMessage());
            return new ArrayList<>();
        }
    }
    public RolModel getRolByID(int id) {
        try {
            return rolDAO.getRolByID(id);
        } catch (SQLException e) {
            viewConsole.errorMessage("Error al recuperar el rol: " + e.getMessage());
            return null;
        }
    }

}
