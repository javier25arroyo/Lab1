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

    public PedidoController(ConsoleView viewConsole){
        this.viewConsole = viewConsole;
        Connection connection = Conexion.getConnection();
        this.pedidoDAO = new PedidoDAO(connection);
    }

    public void agregarPedido (int idCliente, Date fechaPedido, double total, String estado){
        PedidoModel datos = new PedidoModel(idCliente, fechaPedido, total, estado);

        try{
            pedidoDAO.agregarPedido(datos);
            viewConsole.showMessage("Inserccion de datos correcta\n");
        }catch (SQLException e){
            viewConsole.errorMessage("Error al insertar datos" + e.getMessage());
        }
    }
}
