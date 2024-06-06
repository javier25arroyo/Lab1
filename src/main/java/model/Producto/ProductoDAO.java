package model.Producto;

import java.sql.Connection;
import java.sql.SQLException;

public class ProductoDAO {
    private Connection connection;

    public ProductoDAO(Connection connection) {
        this.connection = connection;
    }
        public void agregarProducto(ProductoModel objeto) throws SQLException {
            String query = "INSERT INTO `productos_JA_EM`(`nombre`, `descripcion`, `precio`, `stock`, `fecha_creacion`) VALUES (? ,?, ?, ?, ?, ?)";
            try (var stmt = connection.prepareStatement(query)) {
                stmt.setString(1, objeto.getNombre());
                stmt.setString(2, objeto.getDescripcion());
                stmt.setDouble(3, objeto.getPrecio());
                stmt.setInt(4, objeto.getStock());
                stmt.setDate(5, objeto.getFecha_creacion());
                stmt.executeUpdate();
        }
    }
}

/*CREATE TABLE productos_JA_EM (
    producto_id INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL,
    descripcion TEXT,
    precio DECIMAL(10, 2) NOT NULL,
    stock INT NOT NULL,
    fecha_creacion DATE NOT NULL
);
*/
