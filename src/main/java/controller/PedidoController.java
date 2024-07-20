package controller;

import model.Cliente.ClienteModel;
import model.Conexion;
import model.Pedido.PedidoDAO;
import model.Pedido.PedidoModel;
import view.ConsoleView;

import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PedidoController {
    private ConsoleView viewConsole;

    private PedidoDAO pedidoDAO;

    public PedidoController(ConsoleView viewConsole) {
        this.viewConsole = viewConsole;
        Connection connection = Conexion.getConnection();
        this.pedidoDAO = new PedidoDAO(connection);
    }

    public void agregarPedido(int clienteId, Date fechaPedido, double total, String estado) {
        model.Pedido.PedidoModel datos = new model.Pedido.PedidoModel(clienteId, fechaPedido, total, estado);
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

    public void actualizarPedido(int pedido_id, int clienteId, Date fechaPedido, double total, String estado) {
        model.Pedido.PedidoModel pedido =new model.Pedido.PedidoModel(pedido_id,clienteId,fechaPedido,total,estado);
        try {
            pedidoDAO.actualizarPedido(pedido);
            viewConsole.showMessage("Actualización de pedido correcta\n");
        } catch (SQLException e) {
            viewConsole.errorMessage("Error al actualizar pedido: " + e.getMessage());
        }
    }

    public List<PedidoModel> obtenerTodosLosPedidos(){
        try {
            return pedidoDAO.obtenerTodosLosPedidos();
        } catch (SQLException e) {
            viewConsole.errorMessage("Error al recuperar los pedidos: " + e.getMessage());
            return new ArrayList<>();
        }
    }
    public PedidoModel getPedidoByID(int id) {
        try {
            return pedidoDAO.getPedidoByID(id);
        } catch (SQLException e) {
            viewConsole.errorMessage("Error al recuperar el cliente: " + e.getMessage());
            return null;
        }
    }

}
