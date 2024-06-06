package model.Proveedor;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ProveedorDAO {
    private Connection connection;

    public ProveedorDAO(Connection connection) {
        this.connection = connection;
    }

    public void agregarProveedor(ProveedorModel objeto) throws SQLException {
        String query = "INSERT INTO `proveedores_JA_EM` (`nombre`, `direccion`, `telefono`, `email`, `fecha_registro`) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, objeto.getNombre());
            stmt.setString(2, objeto.getDireccion());
            stmt.setString(3, objeto.getTelefono());
            stmt.setString(4, objeto.getEmail());
            stmt.setDate(5, objeto.getFecha_registro());
            stmt.executeUpdate();
        }
    }
}


/*CREATE TABLE proveedores_JA_EM (
    proveedor_id INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL,
    direccion VARCHAR(255),
    telefono VARCHAR(20),
    email VARCHAR(150),
    fecha_registro DATE NOT NULL
);
*/
