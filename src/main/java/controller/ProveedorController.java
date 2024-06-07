package controller;

import model.Conexion;
import model.Proveedor.ProveedorDAO;
import model.Proveedor.ProveedorModel;
import view.ConsoleView;

import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;

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
}
