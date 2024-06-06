package controller;

import model.Conexion;
import model.Empleado.EmpleadoDAO;
import model.Empleado.EmpleadoModel;
import view.ConsoleView;

import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;

public class EmpleadoController {
    private ConsoleView viewConsole;

    private EmpleadoDAO empleadoDAO;

    public EmpleadoController(ConsoleView viewConsole){
        this.viewConsole = viewConsole;
        Connection connection = Conexion.getConnection();
        this.empleadoDAO = new EmpleadoDAO(connection);
    }

    public void agregarEmpleado(int empleado_id ,String nombre, String apellido, String telefono, String direccion, String cargo, Date fecha_ingreso){
        EmpleadoModel datos = new EmpleadoModel(empleado_id ,nombre, apellido, telefono, direccion, cargo, fecha_ingreso);

        try{
            empleadoDAO.agregarEmpleado(datos);
            viewConsole.showMessage("Inserccion de datos correcta");
        }catch (SQLException e){
            viewConsole.errorMessage("Error al insertar datos" + e.getMessage());
        }
    }
}
