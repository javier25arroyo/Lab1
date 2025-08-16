package controller;

import model.Cliente.ClienteModel;
import model.Conexion;
import model.Empleado.EmpleadoDAO;
import model.Empleado.EmpleadoModel;
import view.ConsoleView;

import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EmpleadoController {
    private ConsoleView viewConsole;

    private EmpleadoDAO empleadoDAO;

    public EmpleadoController(ConsoleView viewConsole){
        this.viewConsole = viewConsole;
        Connection connection = Conexion.getConnection();
        this.empleadoDAO = new EmpleadoDAO(connection);
    }

    public void agregarEmpleado(String nombre, String apellido, String cargo, double salario, Date fecha_contratacion){
        if (nombre == null || nombre.trim().isEmpty() || 
            apellido == null || apellido.trim().isEmpty() || 
            cargo == null || cargo.trim().isEmpty()) {
            throw new IllegalArgumentException("Nombre, apellido y cargo son obligatorios");
        }
        
        if (salario < 0) {
            throw new IllegalArgumentException("El salario no puede ser negativo");
        }
        
        EmpleadoModel datos = new EmpleadoModel(nombre.trim(), apellido.trim(), cargo.trim(), salario, fecha_contratacion);

        try{
            empleadoDAO.agregarEmpleado(datos);
            viewConsole.showMessage("Inserción de datos correcta\n");
        }catch (SQLException e){
            viewConsole.errorMessage("Error al insertar datos: " + e.getMessage());
            throw new RuntimeException("Error en la base de datos al agregar empleado", e);
        }
    }
    public void eliminarEmpleado(int empleado_id) {
        try {
            empleadoDAO.eliminarEmpleado(empleado_id);
            viewConsole.showMessage("Eliminación de empleado correcta\n");
        } catch (SQLException e) {
            viewConsole.errorMessage("Error al eliminar cliente: " + e.getMessage());
        }
    }

    public void actualizarEmpleado(int empleado_id, String nombre, String apellido, String cargo, double salario, Date fecha_contratacion) {
       EmpleadoModel empleado=new EmpleadoModel(empleado_id,nombre,apellido,cargo,salario,fecha_contratacion);
        try {
            empleadoDAO.actualizarEmpleado(empleado);
            viewConsole.showMessage("Actualización de empleado correcta\n");
        } catch (SQLException e) {
            viewConsole.errorMessage("Error al actualizar empleado: " + e.getMessage());
        }
    }
    public List<EmpleadoModel> obtenerTodosLosEmpleados(){
        try {
            return empleadoDAO.obtenerTodosLosEmpleados();
        } catch (SQLException e) {
            viewConsole.errorMessage("Error al recuperar los empleados: " + e.getMessage());
            return new ArrayList<>();
        }
    }

    public EmpleadoModel getEmpleadoByID(int empleado_id) {
        try {
            return empleadoDAO.getEmpleadoByID(empleado_id);
        } catch (SQLException e) {
            viewConsole.errorMessage("Error al recuperar el empleado: " + e.getMessage());
            return null;
        }
    }
}

