package view;

import com.formdev.flatlaf.FlatLightLaf;
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
    private PedidoController pedidoController;
    private DefaultTableModel tableModel;

    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(new FlatLightLaf());
        } catch (Exception ex) {
            System.err.println("Failed to initialize LaF");
        }
        
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Gestión de Pedidos");
            frame.setContentPane(new PedidoForm().PedidoForm);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(1200, 600);
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
        });
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
        try {
            String clienteIdText = textClienteID.getText().trim();
            String totalText = textTotal.getText().trim();
            String estado = textEstado.getText().trim();
            
            if (clienteIdText.isEmpty() || totalText.isEmpty() || estado.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Todos los campos son obligatorios", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            
            int clienteId = Integer.parseInt(clienteIdText);
            if (clienteId <= 0) {
                JOptionPane.showMessageDialog(null, "El ID del cliente debe ser positivo", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            
            double total = Double.parseDouble(totalText);
            if (total < 0) {
                JOptionPane.showMessageDialog(null, "El total debe ser un valor positivo", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            
            Date fechaPedido = Date.valueOf(textFecha.getText().trim());

            pedidoController.agregarPedido(clienteId, fechaPedido, total, estado);
            JOptionPane.showMessageDialog(null, "El pedido fue agregado con éxito", "Éxito", JOptionPane.INFORMATION_MESSAGE);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Cliente ID y total deben ser números válidos", "Error", JOptionPane.ERROR_MESSAGE);
        } catch (IllegalArgumentException e) {
            JOptionPane.showMessageDialog(null, "Formato de fecha inválido. Use YYYY-MM-DD", "Error", JOptionPane.ERROR_MESSAGE);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al agregar pedido: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    public JPanel getPanel() {
        return PedidoForm;
    }

    public void buscarPedido() {
        try {
            String idText = textID.getText().trim();
            if (idText.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Ingrese un ID de pedido", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            
            int id = Integer.parseInt(idText);
            if (id <= 0) {
                JOptionPane.showMessageDialog(null, "El ID debe ser un número positivo", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            
            PedidoModel pedido = pedidoController.getPedidoByID(id);

            if (pedido == null) {
                JOptionPane.showMessageDialog(null, "Pedido no encontrado", "Información", JOptionPane.INFORMATION_MESSAGE);
            } else {
                textClienteID.setText(String.valueOf(pedido.getClienteId()));
                textFecha.setText(pedido.getFechaPedido().toString());
                textTotal.setText(String.valueOf(pedido.getTotal()));
                textEstado.setText(pedido.getEstado());
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "El ID debe ser un número válido", "Error", JOptionPane.ERROR_MESSAGE);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al buscar pedido: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
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
