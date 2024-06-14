package model.ClienteRol;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ClienteRolDAO {
    private Connection connection;

    public ClienteRolDAO(Connection connection){
        this.connection = connection;
    }

    public void agregarClienteRol(ClienteRolModel objeto)throws SQLException{
        String query = "INSERT INTO `Cliente_Rol_JA_EM` (`FK_idCliente`, `FK_idRol`) VALUES (?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, objeto.getFK_idCliente());
            stmt.setInt(2, objeto.getFK_idRol());
            stmt.executeUpdate();
        }
    }

    public void eliminarClienteRol(int id) throws SQLException {
        String query = "DELETE FROM `Cliente_Rol_JA_EM` WHERE `id` = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        }
    }

    public void actualizarClienteRol(ClienteRolModel objeto) throws SQLException {
        String query = "UPDATE `Cliente_Rol_JA_EM` SET `FK_idCliente` = ?, `FK_idRol` = ? WHERE `id` = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, objeto.getFK_idCliente());
            stmt.setInt(2, objeto.getFK_idRol());
            stmt.setInt(3, objeto.getId());
            stmt.executeUpdate();
        }
    }

    public List<ClienteRolModel> obtenerTodosLosClienteRoles()throws SQLException{
        List<ClienteRolModel> clienteRoles= new ArrayList<>();
        String query="SELECT `id`, `FK_idCliente`, `FK_idRol` from `Cliente_Rol_JA_EM`";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            ResultSet rs = stmt.executeQuery();
            while (rs.next()){
                ClienteRolModel clienteRol =new ClienteRolModel(
                    rs.getInt("id"),
                    rs.getInt("FK_idCliente"),
                    rs.getInt("FK_idRol")
                );
                clienteRoles.add(clienteRol);
            }
        }
        return clienteRoles;
    }

}

/*
CREATE TABLE Cliente_Rol_JA_EM (
    id INT AUTO_INCREMENT PRIMARY KEY,
    FK_idCliente INT NOT NULL,
    FK_idRol INT NOT NULL,
);
 */