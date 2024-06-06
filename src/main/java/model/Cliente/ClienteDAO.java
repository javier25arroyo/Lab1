package model.Cliente;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ClienteDAO {
    private Connection connection;

    public ClienteDAO(Connection connection) {
        this.connection = connection;
    }

    public void agregarClientes(ClienteModel objeto) throws SQLException {
        String query = "INSERT INTO `clientes_JA_EM`(`nombre`, `apellido`, `email`, `telefono`, `fecha_registro`) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, objeto.getNombre());
            stmt.setString(2, objeto.getApellido());
            stmt.setString(3, objeto.getEmail());
            stmt.setString(4, objeto.getTelefono());
            stmt.setDate(5, objeto.getFechaRegistro());
            stmt.executeUpdate();
        }
    }
}

/*
CREATE TABLE clientes_JA_EM (
    cliente_id INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL,
    apellido VARCHAR(100) NOT NULL,
    email VARCHAR(150) NOT NULL,
    telefono VARCHAR(20),
    fecha_registro DATE NOT NULL
);
 */

