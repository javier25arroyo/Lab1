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
    private JButton guardarButton;
    private JTable table1;
    private JButton eliminarButton;
    private JButton actualizarButton;
    private JButton buscarButton;
    private JTextField IDField1;
    private JTextField textContrasena;
    private JTextField textID;
    private JButton irAPedidoButton;
    private JButton button2;
    private JButton button3;

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
        tableModel = new DefaultTableModel(new Object[]{"cliente_id", "nombre", "apellido", "email", "telefono", "fecha_registro","contrasena"}, 0);
        table1.setModel(tableModel);

        guardarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                agregarCliente();
                cargarClientes();
                limpiarCampos();
            }
        });

        eliminarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            eliminarClientes();
            cargarClientes();
            limpiarCampos();
            }
        });
        cargarClientes();
        buscarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                buscarCliente();
            }
        });
        actualizarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                actualizarCliente();
                cargarClientes();
                limpiarCampos();
            }
        });
    }
        public void agregarCliente () {

            String nombre = textNombre.getText();
            String apellido = textApellido.getText();
            String email = textCorreo.getText();
            String telefono = textTelefono.getText();
            Date fechaRegistro = Date.valueOf(textFecha.getText());
            String contrasena=textContrasena.getText();

            clienteController.agregarClientes(nombre, apellido, email, telefono, fechaRegistro,contrasena);
            JOptionPane.showMessageDialog(null,"El cliente fue agregado con exito");

        }
    public void buscarCliente(){
        int id= Integer.parseInt(textID.getText());
        ClienteModel cliente =clienteController.getClienteByID(id);

    if (cliente==null){
        JOptionPane.showMessageDialog(null, "Cliente no encontrado");
    }else {
        textNombre.setText(cliente.getNombre());
        textApellido.setText(cliente.getApellido());
        textCorreo.setText(cliente.getEmail());
        textTelefono.setText(cliente.getTelefono());
        textFecha.setText(cliente.getFechaRegistro().toString());
        textContrasena.setText(cliente.getContrasena());
    }
    }

        public void actualizarCliente () {
            int id= Integer.parseInt(textID.getText());
            String nombre = textNombre.getText();
            String apellido = textApellido.getText();
            String email = textCorreo.getText();
            String telefono = textTelefono.getText();
            Date fechaRegistro = Date.valueOf(textFecha.getText());
            String contrasena=textContrasena.getText();

            clienteController.actualizarCliente(id,nombre,apellido,email,telefono,fechaRegistro,contrasena);
            JOptionPane.showMessageDialog(null,"El cliente fue actualizado con exito");
    }
        public void cargarClientes () {
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

        public void eliminarClientes(){
            int id= Integer.parseInt(textID.getText());
            clienteController.eliminarCliente(id);
            JOptionPane.showMessageDialog(null,"El cliente fue eliminado con exito");
        }
        public void limpiarCampos () {
            textNombre.setText("");
            textApellido.setText("");
            textCorreo.setText("");
            textTelefono.setText("");
            textFecha.setText("");
            textContrasena.setText("");
        }


    }

