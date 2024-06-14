package model.Proveedor;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

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

    public void eliminarProveedor(int proveedor_id) throws SQLException {
        String query = "DELETE FROM `proveedores_JA_EM` WHERE `proveedor_id` = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, proveedor_id);
            stmt.executeUpdate();
        }
    }

    public void actualizarProveedor(ProveedorModel objeto) throws SQLException {
        String query = "UPDATE `proveedores_JA_EM` SET `nombre` = ?, `direccion` = ?, `telefono` = ?, `email` = ?, `fecha_registro` = ? WHERE `proveedor_id` = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, objeto.getNombre());
            stmt.setString(2, objeto.getDireccion());
            stmt.setString(3, objeto.getTelefono());
            stmt.setString(4, objeto.getEmail());
            stmt.setDate(5, objeto.getFecha_registro());
            stmt.setInt(6, objeto.getProveedor_id());
            stmt.executeUpdate();
        }
    }
    public List<ProveedorModel> obtenerTodosLosProveedores()throws SQLException{

        List<ProveedorModel> proveedores= new ArrayList<>();

        String query="SELECT `proveedor_id`, `nombre`, `direccion`, `telefono`, `email`, `fecha_registro` from `proveedores_JA_EM`";

        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            ResultSet rs =stmt.executeQuery();
            while (rs.next()){
                ProveedorModel proveedor =new ProveedorModel(
                        rs.getInt("proveedor_id"),
                        rs.getString("nombre"),
                        rs.getString("direccion"),
                        rs.getString("telefono"),
                        rs.getString("email"),
                        rs.getDate("fecha_registro")
                );
                proveedores.add(proveedor);
            }
        }
        return proveedores;
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
