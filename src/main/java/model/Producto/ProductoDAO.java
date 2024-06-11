package model.Producto;

import model.Empleado.EmpleadoModel;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProductoDAO {
    private Connection connection;

    public ProductoDAO(Connection connection) {
        this.connection = connection;
    }
        public void agregarProducto(ProductoModel objeto) throws SQLException {
            String query = "INSERT INTO `productos_JA_EM`(`nombre`, `descripcion`, `precio`, `stock`, `fecha_creacion`) VALUES (? ,?, ?, ?, ?)";
            try (var stmt = connection.prepareStatement(query)) {
                stmt.setString(1, objeto.getNombre());
                stmt.setString(2, objeto.getDescripcion());
                stmt.setDouble(3, objeto.getPrecio());
                stmt.setInt(4, objeto.getStock());
                stmt.setDate(5, objeto.getFecha_creacion());
                stmt.executeUpdate();
        }
    }

    public void eliminarProducto(int producto_id) throws SQLException {
        String query = "DELETE FROM `productos_JA_EM` WHERE `producto_id` = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, producto_id);
            stmt.executeUpdate();
        }
    }

    public void actualizarProducto(ProductoModel objeto) throws SQLException {
        String query = "UPDATE `productos_JA_EM` SET `nombre` = ?, `descripcion` = ?, `precio` = ?, `stock` = ?, `fecha_creacion` = ? WHERE `producto_id` = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, objeto.getNombre());
            stmt.setString(2, objeto.getDescripcion());
            stmt.setDouble(3, objeto.getPrecio());
            stmt.setInt(4, objeto.getStock());
            stmt.setDate(5, objeto.getFecha_creacion());
            stmt.setInt(6, objeto.getProducto_id());
            stmt.executeUpdate();
        }
    }
    public List<ProductoModel> obtenerTodosLosProductos()throws SQLException{

        List<ProductoModel> productos= new ArrayList<>();

        String query="SELECT `producto_id`, `nombre`, `descripcion`, `precio`, `stock`, `fecha_creacion` from `productos_JA_EM`";

        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            ResultSet rs =stmt.executeQuery();
            while (rs.next()){
                ProductoModel producto =new ProductoModel(
                        rs.getInt("producto_id"),
                        rs.getString("nombre"),
                        rs.getString("descripcion"),
                        rs.getDouble("precio"),
                        rs.getInt("stock"),
                        rs.getDate("fecha_creacion")
                );
                productos.add(producto);
            }
        }
        return productos;
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
