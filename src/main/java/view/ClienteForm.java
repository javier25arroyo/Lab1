package view;

import controller.ClienteController;
import model.Cliente.ClienteModel;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.util.List;

public class ClienteForm {
    private JPanel ClienteForm;
    private JTextField textNombre;
    private JTextField textApellido;
    private JTextField textCorreo;
    private JTextField textTelefono;
    private JTextField textFecha;
    private JTextField textContrasena;
    private JButton guardarButton;
    private JTable table1;
    private JButton eliminarButton;
    private JButton actualizarButton;
    private JButton buscarButton;
    private JTextField textID;
    private JButton irAPedidoButton;
    private JButton atrasButton;
    private ClienteController clienteController;
    private DefaultTableModel tableModel;

    public static void main(String[] args) {
        JFrame frame = new JFrame("ClienteForm");
        frame.setContentPane(new ClienteForm().ClienteForm);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

    public ClienteForm() {
        clienteController = new ClienteController(new ConsoleView());
        tableModel = new DefaultTableModel(new Object[]{"cliente_id", "nombre", "apellido", "email", "telefono", "fecha_registro", "contrasena"}, 0);
        table1.setModel(tableModel);

        guardarButton.addActionListener(e -> {
            agregarCliente();
            cargarClientes();
            limpiarCampos();
        });

        eliminarButton.addActionListener(e -> {
            eliminarClientes();
            cargarClientes();
            limpiarCampos();
        });

        buscarButton.addActionListener(e -> buscarCliente());

        actualizarButton.addActionListener(e -> {
            actualizarCliente();
            cargarClientes();
            limpiarCampos();
        });

        cargarClientes();
        irAPedidoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                abrirPedidoForm();
            }
        });
    }

    public JPanel getPanel() {
        return ClienteForm;
    }

    public void agregarCliente() {
        String nombre = textNombre.getText();
        String apellido = textApellido.getText();
        String email = textCorreo.getText();
        String telefono = textTelefono.getText();
        Date fechaRegistro = Date.valueOf(textFecha.getText());
        String contrasena = textContrasena.getText();

        clienteController.agregarClientes(nombre, apellido, email, telefono, fechaRegistro, contrasena);
        JOptionPane.showMessageDialog(null, "El cliente fue agregado con éxito");
    }

    public void buscarCliente() {
        int id = Integer.parseInt(textID.getText());
        ClienteModel cliente = clienteController.getClienteByID(id);

        if (cliente == null) {
            JOptionPane.showMessageDialog(null, "Cliente no encontrado");
        } else {
            textNombre.setText(cliente.getNombre());
            textApellido.setText(cliente.getApellido());
            textCorreo.setText(cliente.getEmail());
            textTelefono.setText(cliente.getTelefono());
            textFecha.setText(cliente.getFechaRegistro().toString());
            textContrasena.setText(cliente.getContrasena());
        }
    }

    public void actualizarCliente() {
        int id = Integer.parseInt(textID.getText());
        String nombre = textNombre.getText();
        String apellido = textApellido.getText();
        String email = textCorreo.getText();
        String telefono = textTelefono.getText();
        Date fechaRegistro = Date.valueOf(textFecha.getText());
        String contrasena = textContrasena.getText();

        clienteController.actualizarCliente(id, nombre, apellido, email, telefono, fechaRegistro, contrasena);
        JOptionPane.showMessageDialog(null, "El cliente fue actualizado con éxito");
    }

    public void cargarClientes() {
        tableModel.setRowCount(0);
        try {
            List<ClienteModel> clientes = clienteController.obtenerTodosLosClientes();
            for (ClienteModel cliente : clientes) {
                tableModel.addRow(new Object[]{
                        cliente.getCliente_id(),
                        cliente.getNombre(),
                        cliente.getApellido(),
                        cliente.getEmail(),
                        cliente.getTelefono(),
                        cliente.getFechaRegistro(),
                        cliente.getContrasena()
                });
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al cargar clientes: " + e.getMessage());
        }
    }

    public void eliminarClientes() {
        int id = Integer.parseInt(textID.getText());
        clienteController.eliminarCliente(id);
        JOptionPane.showMessageDialog(null, "El cliente fue eliminado con éxito");
    }

    public void limpiarCampos() {
        textNombre.setText("");
        textApellido.setText("");
        textCorreo.setText("");
        textTelefono.setText("");
        textFecha.setText("");
        textContrasena.setText("");
    }
    public void abrirPedidoForm(){
        JFrame pedidoFrame = new JFrame("Pedido Form");
        PedidoForm pedidoForm = new PedidoForm();
        pedidoFrame.setContentPane(pedidoForm.getPanel());
        pedidoFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // Usar DISPOSE_ON_CLOSE para cerrar solo el formulario actual
        pedidoFrame.pack();
        pedidoFrame.setLocationRelativeTo(null);
        pedidoFrame.setVisible(true);
    }
}
