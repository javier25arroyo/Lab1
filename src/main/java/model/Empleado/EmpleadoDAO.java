package model.Empleado;

import model.Pedido.PedidoModel;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EmpleadoDAO {
    private Connection connection;

    public EmpleadoDAO(Connection connection){
        this.connection = connection;
    }

    public void agregarEmpleado(EmpleadoModel objeto) throws SQLException {
        String query = "INSERT INTO `empleados_JA_EM`(`nombre`, `apellido`, `cargo`, `salario`, `fecha_contratacion`) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(query)){
            stmt.setString(1, objeto.getNombre());
            stmt.setString(2, objeto.getApellido());
            stmt.setString(3, objeto.getCargo());
            stmt.setDouble(4, objeto.getSalario());
            stmt.setDate(5, objeto.getFecha_contratacion());
            stmt.executeUpdate();
        }
    }
    public void eliminarEmpleado(int empleado_id) throws SQLException {
        String query = "DELETE FROM `empleados_JA_EM` WHERE `empleado_id` = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, empleado_id);
            stmt.executeUpdate();
        }
    }

    public void actualizarEmpleado(EmpleadoModel empleado) throws SQLException {
        String query = "UPDATE `empleados_JA_EM` SET `nombre` = ?, `apellido` = ?, `cargo` = ?, `salario` = ?, `fecha_contratacion` = ? WHERE `empleado_id` = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, empleado.getNombre());
            stmt.setString(2, empleado.getApellido());
            stmt.setString(3, empleado.getCargo());
            stmt.setDouble(4, empleado.getSalario());
            stmt.setDate(5, empleado.getFecha_contratacion());
            stmt.setInt(6, empleado.getEmpleado_id());
            stmt.executeUpdate();
        }
    }
    public List<EmpleadoModel> obtenerTodosLosEmpleados()throws SQLException{

        List<EmpleadoModel> empleados= new ArrayList<>();

        String query="SELECT `empleado_id`, `nombre`, `apellido`, `cargo`, `salario`, `fecha_contratacion` from `empleados_JA_EM`";

        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            ResultSet rs =stmt.executeQuery();
            while (rs.next()){
                EmpleadoModel empleado =new EmpleadoModel(
                        rs.getInt("empleado_id"),
                        rs.getString("nombre"),
                        rs.getString("apellido"),
                        rs.getString("cargo"),
                        rs.getDouble("salario"),
                        rs.getDate("fecha_contratacion")
                );
                empleados.add(empleado);
            }
        }
        return empleados;
    }
}

/*
CREATE TABLE empleados_JA_EM (
    empleado_id INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL,
    apellido VARCHAR(100) NOT NULL,
    cargo VARCHAR(50) NOT NULL,
    salario DECIMAL(10, 2) NOT NULL,
    fecha_contratacion DATE NOT NULL
*/