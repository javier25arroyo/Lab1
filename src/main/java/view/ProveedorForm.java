package view;

import com.formdev.flatlaf.FlatLightLaf;
import controller.ProveedorController;
import model.Proveedor.ProveedorModel;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.util.List;
import java.util.regex.Pattern;

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
    private ProveedorController proveedorController;
    private DefaultTableModel tableModel;

    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(new FlatLightLaf());
        } catch (Exception ex) {
            System.err.println("Failed to initialize LaF");
        }
        
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Gestión de Proveedores");
            frame.setContentPane(new ProveedorForm().ProveedorForm);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(1200, 600);
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
        });
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

    }

    public JPanel getPanel() {
        return ProveedorForm;
    }

    public void agregarProveedor() {
        try {
            String nombre = textNombre.getText().trim();
            String direccion = textDireccion.getText().trim();
            String telefono = textTelefono.getText().trim();
            String email = textEmail.getText().trim();
            
            if (nombre.isEmpty() || direccion.isEmpty() || telefono.isEmpty() || email.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Todos los campos son obligatorios", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            
            if (!isValidEmail(email)) {
                JOptionPane.showMessageDialog(null, "El formato del email no es válido", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            
            if (!isValidPhone(telefono)) {
                JOptionPane.showMessageDialog(null, "El teléfono debe tener entre 8-15 dígitos", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            
            Date fechaRegistro = Date.valueOf(textFecha.getText().trim());

            proveedorController.agregarProveedor(nombre, direccion, telefono, email, fechaRegistro);
            JOptionPane.showMessageDialog(null, "El proveedor fue agregado con éxito", "Éxito", JOptionPane.INFORMATION_MESSAGE);
        } catch (IllegalArgumentException e) {
            JOptionPane.showMessageDialog(null, "Formato de fecha inválido. Use YYYY-MM-DD", "Error", JOptionPane.ERROR_MESSAGE);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al agregar proveedor: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void buscarProveedor() {
        try {
            String idText = textID.getText().trim();
            if (idText.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Ingrese un ID de proveedor", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            
            int id = Integer.parseInt(idText);
            if (id <= 0) {
                JOptionPane.showMessageDialog(null, "El ID debe ser un número positivo", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            
            ProveedorModel proveedor = proveedorController.getProveedorByID(id);

            if (proveedor == null) {
                JOptionPane.showMessageDialog(null, "Proveedor no encontrado", "Información", JOptionPane.INFORMATION_MESSAGE);
            } else {
                textNombre.setText(proveedor.getNombre());
                textDireccion.setText(proveedor.getDireccion());
                textTelefono.setText(proveedor.getTelefono());
                textEmail.setText(proveedor.getEmail());
                textFecha.setText(proveedor.getFecha_registro().toString());
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "El ID debe ser un número válido", "Error", JOptionPane.ERROR_MESSAGE);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al buscar proveedor: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
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
    
    private boolean isValidEmail(String email) {
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
        Pattern pattern = Pattern.compile(emailRegex);
        return pattern.matcher(email).matches();
    }
    
    private boolean isValidPhone(String phone) {
        String phoneRegex = "^[0-9]{8,15}$";
        Pattern pattern = Pattern.compile(phoneRegex);
        return pattern.matcher(phone).matches();
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
