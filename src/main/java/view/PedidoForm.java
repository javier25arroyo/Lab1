package view;

import controller.PedidoController;
import model.Pedido.PedidoModel;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.util.List;

public class PedidoForm {

    private JPanel PedidoForm;
    private JTextField textClienteID;
    private JTextField textFecha;
    private JTextField textTotal;
    private JTextField textEstado;
    private JButton guardarButton;
    private JTable table1;
    private JButton eliminarButton;
    private JButton actualizarButton;
    private JButton buscarButton;
    private JTextField textID;
    private JButton irAClientesButton;
    private JButton atrasButton;

    private PedidoController pedidoController;
    private DefaultTableModel tableModel;

    public static void main(String[] args) {
        JFrame frame = new JFrame("PedidoForm");
        frame.setContentPane(new PedidoForm().PedidoForm);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

    public PedidoForm() {
        pedidoController = new PedidoController(new ConsoleView());
        tableModel = new DefaultTableModel(new Object[]{"pedido_id", "cliente_id", "fecha_pedido", "total", "estado"}, 0);
        table1.setModel(tableModel);

        guardarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                agregarPedido();
                cargarPedidos();
                limpiarCampos();
            }
        });

        eliminarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                eliminarPedido();
                cargarPedidos();
                limpiarCampos();
            }
        });

        buscarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                buscarPedido();
            }
        });

        actualizarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                actualizarPedido();
                cargarPedidos();
                limpiarCampos();
            }
        });

        cargarPedidos();
    }

    public void agregarPedido() {
        int clienteId = Integer.parseInt(textClienteID.getText());
        Date fechaPedido = Date.valueOf(textFecha.getText());
        double total = Double.parseDouble(textTotal.getText());
        String estado = textEstado.getText();

        pedidoController.agregarPedido(clienteId, fechaPedido, total, estado);
        JOptionPane.showMessageDialog(null, "El pedido fue agregado con éxito");
    }
    public JPanel getPanel() {
        return PedidoForm;
    }

    public void buscarPedido() {
        int id = Integer.parseInt(textID.getText());
        PedidoModel pedido = pedidoController.getPedidoByID(id);

        if (pedido == null) {
            JOptionPane.showMessageDialog(null, "Pedido no encontrado");
        } else {
            textClienteID.setText(String.valueOf(pedido.getClienteId()));
            textFecha.setText(pedido.getFechaPedido().toString());
            textTotal.setText(String.valueOf(pedido.getTotal()));
            textEstado.setText(pedido.getEstado());
        }
    }

    public void actualizarPedido() {
        int id = Integer.parseInt(textID.getText());
        int clienteId = Integer.parseInt(textClienteID.getText());
        Date fechaPedido = Date.valueOf(textFecha.getText());
        double total = Double.parseDouble(textTotal.getText());
        String estado = textEstado.getText();

        pedidoController.actualizarPedido(id, clienteId, fechaPedido, total, estado);
        JOptionPane.showMessageDialog(null, "El pedido fue actualizado con éxito");
    }

    public void cargarPedidos() {
        tableModel.setRowCount(0);
        try {
            List<PedidoModel> pedidos = pedidoController.obtenerTodosLosPedidos();
            for (PedidoModel pedido : pedidos) {
                tableModel.addRow(new Object[]{
                        pedido.getPedido_id(),
                        pedido.getClienteId(),
                        pedido.getFechaPedido(),
                        pedido.getTotal(),
                        pedido.getEstado()
                });
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al cargar pedidos: " + e.getMessage());
        }
    }

    public void eliminarPedido() {
        int id = Integer.parseInt(textID.getText());
        pedidoController.eliminarPedido(id);
        JOptionPane.showMessageDialog(null, "El pedido fue eliminado con éxito");
    }

    public void limpiarCampos() {
        textClienteID.setText("");
        textFecha.setText("");
        textTotal.setText("");
        textEstado.setText("");
        textID.setText("");
    }
}
