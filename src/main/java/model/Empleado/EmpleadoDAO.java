package model.Empleado;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

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