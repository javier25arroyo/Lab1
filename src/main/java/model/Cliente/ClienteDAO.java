package model.Cliente;

import java.sql.Connection;
import java.sql.SQLException;

public class ClienteDAO {
    private Connection connection;

    public ClienteDAO(Connection connection) {
        this.connection = connection;
    }

    public void agregarClientes(ClienteModel objeto) throws SQLException {
        String query = "INSERT INTO `clientes_JA_EM`(`nombre`, `apellido`, `email`, `telefono`, `fecha_registro`) VALUES (?, ?, ?, ?, ?)";
        try (var stmt = connection.prepareStatement(query)) {
            stmt.setString(1, objeto.getNombre());
            stmt.setString(2, objeto.getApellido());
            stmt.setString(3, objeto.getEmail());
            stmt.setString(4, objeto.getTelefono());
            stmt.setDate(5, objeto.getFechaRegistro());
            stmt.executeUpdate();
        }
    }
}

