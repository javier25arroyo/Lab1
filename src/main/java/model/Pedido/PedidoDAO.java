package model.Pedido;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class PedidoDAO {
    private Connection connection;

    public PedidoDAO(Connection connection) {
        this.connection = connection;
    }

    public void agregarPedido(PedidoModel objeto) throws SQLException {
        String query = "INSERT INTO pedidos_JA_EM(`cliente_id`, `fecha_pedido`, `total`, `estado`) VALUES (?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, objeto.getClienteId());
            stmt.setDate(2, objeto.getFechaPedido());
            stmt.setDouble(3, objeto.getTotal());
            stmt.setString(4, objeto.getEstado());
            stmt.executeUpdate();
        }
    }
    public void eliminarPedido(int pedido_id) throws SQLException {
        String query = "DELETE FROM `pedidos_JA_EM` WHERE `pedido_id` = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, pedido_id);
            stmt.executeUpdate();
        }
    }

    public void actualizarPedido(PedidoModel pedido) throws SQLException {
        String query = "UPDATE `pedidos_JA_EM` SET `cliente_id` = ?, `fecha_pedido` = ?, `total` = ?, `estado` = ? WHERE `pedido_id` = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, pedido.getClienteId());
            stmt.setDate(2, pedido.getFechaPedido());
            stmt.setDouble(3, pedido.getTotal());
            stmt.setString(4, pedido.getEstado());
            stmt.setInt(5, pedido.getPedido_id());
            stmt.executeUpdate();
        }
    }
}

/*CREATE TABLE pedidos_JA_EM (
    cliente_id INT NOT NULL,
    fecha_pedido DATE NOT NULL,
    total DECIMAL(10, 2) NOT NULL,
    estado VARCHAR(50) NOT NULL
);
*/
