package model.Rol;

import model.Cliente.ClienteModel;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RolDAO {
    private Connection connection;

    public RolDAO(Connection connection){
        this.connection = connection;
    }

    public void agregarRol(RolModel objeto) throws SQLException {
        String query = "INSERT INTO `Rol_JA_EM`(`nombre`, `descripcion`) VALUES (?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, objeto.getNombre());
            stmt.setString(2, objeto.getDescripcion());
            stmt.executeUpdate();
        }
    }

    public void eliminarRol(int rol_id) throws SQLException {
        String query = "DELETE FROM `Rol_JA_EM` WHERE `id` = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, rol_id);
            stmt.executeUpdate();
        }
    }

    public void actualizarRol(RolModel objeto) throws SQLException {
        String query = "UPDATE `Rol_JA_EM` SET `nombre` = ?, `descripcion` = ? WHERE `id` = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, objeto.getNombre());
            stmt.setString(2, objeto.getDescripcion());
            stmt.setInt(3, objeto.getId());
            stmt.executeUpdate();
        }
    }

    public List<RolModel> obtenerTodosLosRoles()throws SQLException{
        List<RolModel> roles= new ArrayList<>();
        String query="SELECT `id`, `nombre`, `descripcion` from `Rol_JA_EM`";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            ResultSet rs = stmt.executeQuery();
            while (rs.next()){
                RolModel rol =new RolModel(
                    rs.getInt("id"),
                    rs.getString("nombre"),
                    rs.getString("descripcion")
                );
                roles.add(rol);
            }
        }
        return roles;
    }
    public RolModel getRolByID(int id) throws SQLException {
        RolModel rol = null;
        String query = "SELECT * FROM `Rol_JA_EM` WHERE `id` = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                rol = new RolModel(
                        rs.getInt("id"),
                        rs.getString("nombre"),
                        rs.getString("descripcion")
                );
            }
        }
        return rol;
    }
}


/*
Rol_JA_EM (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(50) NOT NULL,
    descripcion VARCHAR(200) NULL
 */
