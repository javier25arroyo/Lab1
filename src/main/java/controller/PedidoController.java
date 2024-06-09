package controller;

import model.Conexion;
import model.Pedido.PedidoDAO;
import model.Pedido.PedidoModel;
import view.ConsoleView;

import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;

public class PedidoController {
    private ConsoleView viewConsole;

    private PedidoDAO pedidoDAO;

    public PedidoController(ConsoleView viewConsole) {
        this.viewConsole = viewConsole;
        Connection connection = Conexion.getConnection();
        this.pedidoDAO = new PedidoDAO(connection);
    }

    public void agregarPedido(int clienteId, Date fechaPedido, double total, String estado) {
        PedidoModel datos = new PedidoModel(clienteId, fechaPedido, total, estado);
        try {
            pedidoDAO.agregarPedido(datos);
            viewConsole.showMessage("Pedido agregado correctamente\n");
        } catch (SQLException e) {
            viewConsole.errorMessage("Error al agregar el pedido" + e.getMessage());
        }
    }
    public void eliminarPedido(int pedido_id) {
        try {
            pedidoDAO.eliminarPedido(pedido_id);
            viewConsole.showMessage("Eliminación de pedido correcta\n");
        } catch (SQLException e) {
            viewConsole.errorMessage("Error al eliminar cliente: " + e.getMessage());
        }
    }
}
