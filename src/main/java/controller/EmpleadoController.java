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

    public void agregarEmpleado(String nombre, String apellido, String cargo,double salario, Date fecha_contratacion){
        EmpleadoModel datos = new EmpleadoModel(nombre, apellido, cargo, salario, fecha_contratacion);

        try{
            empleadoDAO.agregarEmpleado(datos);
            viewConsole.showMessage("Inserccion de datos correcta\n");
        }catch (SQLException e){
            viewConsole.errorMessage("Error al insertar datos" + e.getMessage());
        }
    }
}
