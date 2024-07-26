package view;

import controller.ProveedorController;
import model.Proveedor.ProveedorModel;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.util.List;

public class ProveedorForm {
    private JPanel ProveedorForm;
    private JTextField textNombre;
    private JTextField textDireccion;
    private JTextField textCorreo;
    private JTextField textTelefono;
    private JTextField textFecha;
    private JTextField textEmail;
    private JButton guardarButton;
    private JTable table1;
    private JButton eliminarButton;
    private JButton actualizarButton;
    private JButton buscarButton;
    private JTextField textID;
    private JButton irAPedidoButton;
    private JButton atrasButton;
    private ProveedorController proveedorController;
    private DefaultTableModel tableModel;

    public static void main(String[] args) {
        JFrame frame = new JFrame("ProveedorForm");
        frame.setContentPane(new ProveedorForm().ProveedorForm);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

    public ProveedorForm() {
        proveedorController = new ProveedorController(new ConsoleView());
        tableModel = new DefaultTableModel(new Object[]{"proveedor_id", "nombre", "direccon", "email", "telefono", "fecha_registro"}, 0);
        table1.setModel(tableModel);

        guardarButton.addActionListener(e -> {
            agregarProveedor();
            cargarProveedores();
            limpiarCampos();
        });

        eliminarButton.addActionListener(e -> {
            eliminarProveedores();
            cargarProveedores();
            limpiarCampos();
        });

        buscarButton.addActionListener(e -> buscarProveedor());

        actualizarButton.addActionListener(e -> {
            actualizarProveedor();
            cargarProveedores();
            limpiarCampos();
        });

        cargarProveedores();
        irAPedidoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                abrirPedidoForm();
            }
        });
    }

    public JPanel getPanel() {
        return ProveedorForm;
    }

    public void agregarProveedor() {
        String nombre = textNombre.getText();
        String direccion = textDireccion.getText();
        String telefono = textTelefono.getText();
        String email = textEmail.getText();
        Date fechaRegistro = Date.valueOf(textFecha.getText());


        proveedorController.agregarProveedor(nombre, direccion,telefono,email, fechaRegistro);
        JOptionPane.showMessageDialog(null, "El proveedor fue agregado con éxito");
    }

    public void buscarProveedor() {
        int id = Integer.parseInt(textID.getText());
        ProveedorModel proveedor = proveedorController.getProveedorByID(id);

        if (proveedor == null) {
            JOptionPane.showMessageDialog(null, "Proveedor no encontrado");
        } else {
            textNombre.setText(proveedor.getNombre());
            textDireccion.setText(proveedor.getDireccion());
            textTelefono.setText(proveedor.getTelefono());
            textEmail.setText(proveedor.getEmail());
            textFecha.setText(proveedor.getFecha_registro().toString());

        }
    }

    public void actualizarProveedor() {
        int id = Integer.parseInt(textID.getText());
        String nombre = textNombre.getText();
        String direccion = textDireccion.getText();
        String telefono = textTelefono.getText();
        String email = textEmail.getText();
        Date fechaRegistro = Date.valueOf(textFecha.getText());

        proveedorController.actualizarProveedor(id, nombre, direccion, telefono,email, fechaRegistro);
        JOptionPane.showMessageDialog(null, "El proveedor fue actualizado con éxito");
    }

    public void cargarProveedores() {
        tableModel.setRowCount(0);
        try {
            List<ProveedorModel> proveedores = proveedorController.obtenerTodosLosProveedores();
            for (ProveedorModel proveedor : proveedores) {
                tableModel.addRow(new Object[]{
                        proveedor.getProveedor_id(),
                        proveedor.getNombre(),
                        proveedor.getDireccion(),
                        proveedor.getTelefono(),
                        proveedor.getEmail(),
                        proveedor.getFecha_registro()

                });
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al cargar proveedores: " + e.getMessage());
        }
    }

    public void eliminarProveedores() {
        int id = Integer.parseInt(textID.getText());
        proveedorController.eliminarProveedor(id);
        JOptionPane.showMessageDialog(null, "El proveedor fue eliminado con éxito");
    }

    public void limpiarCampos() {
        textNombre.setText("");
        textDireccion.setText("");
        textTelefono.setText("");
        textEmail.setText("");
        textFecha.setText("");

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
