package controller;

import model.Conexion;
import model.Empleado.EmpleadoDAO;
import model.Empleado.EmpleadoModel;
import model.Pedido.PedidoModel;
import view.ConsoleView;

import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;
import java.util.List;

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
    public void obtenerTodosLosEmpleados(){
        try {
            List<EmpleadoModel> empleados=empleadoDAO.obtenerTodosLosEmpleados();
            for (EmpleadoModel empleado: empleados){
                viewConsole.showMessage(empleado.toString()+"\n");
            }
        }catch (SQLException e){
            viewConsole.errorMessage("Error al recuperar los empleados"+e.getMessage());
        }
    }
}
