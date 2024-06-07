package model.Pedido;

import java.sql.Connection;
import java.sql.SQLException;

public class PedidoDAO {
   private Connection connection;

   public PedidoDAO(Connection connection) {
      this.connection = connection;
   }

    public void agregarPedido(PedidoModel objeto) throws SQLException {
        String query = "INSERT INTO `pedidos_JA_EM`(`idCliente`, `fecha_pedido`, `total`, `estado`) VALUES (?, ?, ?, ?)";
        try (var stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, objeto.getIdCliente());
            stmt.setDate(2, objeto.getFechaPedido());
            stmt.setDouble(3, objeto.getTotal());
            stmt.setString(4, objeto.getEstado());
            stmt.executeUpdate();
        }
    }
}

/*CREATE TABLE pedidos_JA_EM (
    pedido_id INT AUTO_INCREMENT PRIMARY KEY,
    cliente_id INT NOT NULL,
    fecha_pedido DATE NOT NULL,
    total DECIMAL(10, 2) NOT NULL,
    estado VARCHAR(50) NOT NULL
);
*/
