package model.Cliente;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ClienteDAO {
    private Connection connection;

    public ClienteDAO(Connection connection) {
        this.connection = connection;
    }

    public void agregarClientes(ClienteModel objeto) throws SQLException {
        String query = "INSERT INTO `clientes_JA_EM`(`nombre`, `apellido`, `email`, `telefono`, `fecha_registro`, `contrasena`) VALUES (?, ?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, objeto.getNombre());
            stmt.setString(2, objeto.getApellido());
            stmt.setString(3, objeto.getEmail());
            stmt.setString(4, objeto.getTelefono());
            stmt.setDate(5, objeto.getFechaRegistro());
            stmt.setString(6, objeto.getContrasena());
            stmt.executeUpdate();
        }
    }

    public void actualizarCliente(ClienteModel cliente) throws SQLException {
        String query = "UPDATE `clientes_JA_EM` SET `nombre` = ?, `apellido` = ?, `email` = ?,`telefono` = ?, `fecha_registro` = ?, `contrasena` = ? WHERE `cliente_id` = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, cliente.getNombre());
            stmt.setString(2, cliente.getApellido());
            stmt.setString(3, cliente.getEmail());
            stmt.setString(4, cliente.getTelefono());
            stmt.setDate(5, cliente.getFechaRegistro());
            stmt.setString(6, cliente.getContrasena());
            stmt.setInt(7, cliente.getCliente_id());
            stmt.executeUpdate();
        }
    }

    public void eliminarCliente(int cliente_id) throws SQLException {
        String query = "DELETE FROM `clientes_JA_EM` WHERE `cliente_id` = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, cliente_id);
            stmt.executeUpdate();
        }
    }
    public List<ClienteModel> obtenerTodosLosClientes()throws SQLException{

        List<ClienteModel> clientes= new ArrayList<>();

        String query="SELECT `cliente_id`, `nombre`, `apellido`, `email`, `contrasena`,`telefono`,`fecha_registro`, `contrasena` from `clientes_JA_EM`";

        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            ResultSet rs =stmt.executeQuery();
            while (rs.next()){
                ClienteModel cliente =new ClienteModel(
                        rs.getInt("cliente_id"),
                        rs.getString("nombre"),
                        rs.getString("apellido"),
                        rs.getString("email"),
                        rs.getString("telefono"),
                        rs.getDate("fecha_registro"),
                        rs.getString("contrasena")

                );
                clientes.add(cliente);
            }
        }
        return clientes;
    }

    public ClienteModel getClienteByEmail(String email) throws SQLException {
        //Necesito hacer una funcion para ingresar un email y contrasena y me permita entrar a la aplicacion
        String query = "SELECT `cliente_id`, `nombre`, `apellido`, `email`, `contrasena`,`telefono`,`fecha_registro` FROM `clientes_JA_EM` WHERE `email` = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, email);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new ClienteModel(
                        rs.getInt("cliente_id"),
                        rs.getString("nombre"),
                        rs.getString("apellido"),
                        rs.getString("email"),
                        rs.getString("telefono"),
                        rs.getDate("fecha_registro"),
                        rs.getString("contrasena")

                );
            }
        }
        return null;
    }
    public ClienteModel getClienteByID(int id) throws SQLException {
        ClienteModel cliente = null;
        String query = "SELECT * FROM `clientes_JA_EM` WHERE `cliente_id` = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                cliente = new ClienteModel(
                        rs.getInt("cliente_id"),
                        rs.getString("nombre"),
                        rs.getString("apellido"),
                        rs.getString("email"),
                        rs.getString("telefono"),
                        rs.getDate("fecha_registro"),
                        rs.getString("contrasena")

                );
            }
        }
        return cliente;
    }

}
/*
CREATE TABLE clientes_JA_EM (
    nombre VARCHAR(100) NOT NULL,
    apellido VARCHAR(100) NOT NULL,
    email VARCHAR(150) NOT NULL,
    telefono VARCHAR(20),
    fecha_registro DATE NOT NULL
);
 */

