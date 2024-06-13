package controller;

import model.Conexion;
import model.Proveedor.ProveedorDAO;
import model.Proveedor.ProveedorModel;
import view.ConsoleView;

import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;
import java.util.List;

public class ProveedorController {
    private ConsoleView viewConsole;

    public ProveedorDAO proveedorDAO;

    public ProveedorController(ConsoleView viewConsole){
        this.viewConsole=viewConsole;
        Connection connection = Conexion.getConnection();
        this.proveedorDAO = new ProveedorDAO(connection);
    }

    public void agregarProveedor(String nombre, String direccion, String telefono, String email, Date fecha_registro){
        ProveedorModel datos = new ProveedorModel(nombre, direccion, telefono, email, fecha_registro);

        try{
            proveedorDAO.agregarProveedor(datos);
            viewConsole.showMessage("Inserccion de datos correcta\n");
        }catch (SQLException e){
            viewConsole.errorMessage("Error al insertar datos" + e.getMessage());
        }
    }

    public void eliminarProveedor(int proveedor_id) {
        try {
            proveedorDAO.eliminarProveedor(proveedor_id);
            viewConsole.showMessage("Eliminación de proveedor correcta\n");
        } catch (SQLException e) {
            viewConsole.errorMessage("Error al eliminar cliente: " + e.getMessage());
        }
    }

    public void actualizarProveedor(int proveedor_id, String nombre, String direccion, String telefono, String email, Date fecha_registro) {
        ProveedorModel datos = new ProveedorModel(proveedor_id,nombre,direccion,telefono,email,fecha_registro);
        try {
            proveedorDAO.actualizarProveedor(datos);
            viewConsole.showMessage("Actualización de proveedor correcta\n");
        } catch (SQLException e) {
            viewConsole.errorMessage("Error al actualizar proveedor: " + e.getMessage());
        }
    }
    public void obtenerTodosLosProveedores(){
        try {
            List<ProveedorModel> proveedores=proveedorDAO.obtenerTodosLosProveedores();
            for (ProveedorModel proveedor: proveedores){
                viewConsole.showMessage(proveedor.toString()+"\n");
            }
        }catch (SQLException e){
            viewConsole.errorMessage("Error al recuperar los empleados"+e.getMessage());
        }
    }
}
