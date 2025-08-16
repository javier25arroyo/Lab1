package model.Usuario;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UsuarioDAO {
    private Connection connection;

    public UsuarioDAO(Connection connection) {
        this.connection = connection;
    }

    public void agregarUsuario(UsuarioModel usuario) throws SQLException {
        String query = "INSERT INTO usuarios (nombre, apellido, email, contrasena, telefono, estado, fecha_registro) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, usuario.getNombre());
            stmt.setString(2, usuario.getApellido());
            stmt.setString(3, usuario.getEmail());
            stmt.setString(4, usuario.getContrasena());
            stmt.setString(5, usuario.getTelefono());
            stmt.setString(6, usuario.getEstado());
            stmt.setDate(7, usuario.getFechaRegistro());
            stmt.executeUpdate();
        }
    }

    public void actualizarUsuario(UsuarioModel usuario) throws SQLException {
        String query = "UPDATE usuarios SET nombre = ?, apellido = ?, email = ?, contrasena = ?, telefono = ?, estado = ?, fecha_registro = ? WHERE usuario_id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, usuario.getNombre());
            stmt.setString(2, usuario.getApellido());
            stmt.setString(3, usuario.getEmail());
            stmt.setString(4, usuario.getContrasena());
            stmt.setString(5, usuario.getTelefono());
            stmt.setString(6, usuario.getEstado());
            stmt.setDate(7, usuario.getFechaRegistro());
            stmt.setInt(8, usuario.getUsuario_id());
            stmt.executeUpdate();
        }
    }

    public void eliminarUsuario(int usuario_id) throws SQLException {
        String query = "DELETE FROM usuarios WHERE usuario_id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, usuario_id);
            stmt.executeUpdate();
        }
    }

    public List<UsuarioModel> obtenerTodosLosUsuarios() throws SQLException {
        List<UsuarioModel> usuarios = new ArrayList<>();
        String query = "SELECT usuario_id, nombre, apellido, email, contrasena, telefono, estado, fecha_registro, created_at, updated_at FROM usuarios ORDER BY usuario_id";

        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                UsuarioModel usuario = new UsuarioModel(
                        rs.getInt("usuario_id"),
                        rs.getString("nombre"),
                        rs.getString("apellido"),
                        rs.getString("email"),
                        rs.getString("contrasena"),
                        rs.getString("telefono"),
                        rs.getString("estado"),
                        rs.getDate("fecha_registro"),
                        rs.getTimestamp("created_at"),
                        rs.getTimestamp("updated_at")
                );
                usuarios.add(usuario);
            }
        }
        return usuarios;
    }

    public UsuarioModel getUsuarioByEmail(String email) throws SQLException {
        String query = "SELECT usuario_id, nombre, apellido, email, contrasena, telefono, estado, fecha_registro, created_at, updated_at FROM usuarios WHERE email = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, email);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new UsuarioModel(
                        rs.getInt("usuario_id"),
                        rs.getString("nombre"),
                        rs.getString("apellido"),
                        rs.getString("email"),
                        rs.getString("contrasena"),
                        rs.getString("telefono"),
                        rs.getString("estado"),
                        rs.getDate("fecha_registro"),
                        rs.getTimestamp("created_at"),
                        rs.getTimestamp("updated_at")
                );
            }
        }
        return null;
    }

    public UsuarioModel getUsuarioByID(int id) throws SQLException {
        String query = "SELECT usuario_id, nombre, apellido, email, contrasena, telefono, estado, fecha_registro, created_at, updated_at FROM usuarios WHERE usuario_id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new UsuarioModel(
                        rs.getInt("usuario_id"),
                        rs.getString("nombre"),
                        rs.getString("apellido"),
                        rs.getString("email"),
                        rs.getString("contrasena"),
                        rs.getString("telefono"),
                        rs.getString("estado"),
                        rs.getDate("fecha_registro"),
                        rs.getTimestamp("created_at"),
                        rs.getTimestamp("updated_at")
                );
            }
        }
        return null;
    }

    public boolean existeEmail(String email) throws SQLException {
        String query = "SELECT COUNT(*) FROM usuarios WHERE email = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, email);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getInt(1) > 0;
            }
        }
        return false;
    }

    public UsuarioModel validarLogin(String email, String contrasena) throws SQLException {
        String query = "SELECT usuario_id, nombre, apellido, email, contrasena, telefono, estado, fecha_registro, created_at, updated_at FROM usuarios WHERE email = ? AND contrasena = ? AND estado = 'ACTIVO'";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, email);
            stmt.setString(2, contrasena);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new UsuarioModel(
                        rs.getInt("usuario_id"),
                        rs.getString("nombre"),
                        rs.getString("apellido"),
                        rs.getString("email"),
                        rs.getString("contrasena"),
                        rs.getString("telefono"),
                        rs.getString("estado"),
                        rs.getDate("fecha_registro"),
                        rs.getTimestamp("created_at"),
                        rs.getTimestamp("updated_at")
                );
            }
        }
        return null;
    }
}