package view;

import controller.UsuarioController;
import model.Usuario.UsuarioModel;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

public class UsuarioForm {
    private JPanel UsuarioForm;
    private JTextField textID;
    private JTextField textNombre;
    private JTextField textApellido;
    private JTextField textCorreo;
    private JPasswordField textContrasena;
    private JTextField textTelefono;
    private JComboBox<String> comboEstado;
    private JTextField textFecha;
    private JButton guardarButton;
    private JButton actualizarButton;
    private JButton eliminarButton;
    private JButton buscarButton;
    private JButton limpiarButton;
    private JTable table1;

    private UsuarioController usuarioController;
    private DefaultTableModel tableModel;
    private SimpleDateFormat dateFormat;

    public static void main(String[] args) {
        JFrame frame = new JFrame("Gestión de Usuarios");
        frame.setContentPane(new UsuarioForm().UsuarioForm);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1000, 600);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    public UsuarioForm() {
        usuarioController = new UsuarioController(new ConsoleView());
        dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        
        setupTable();
        setupEventHandlers();
        cargarUsuarios();
        
        // Establecer fecha actual por defecto
        textFecha.setText(dateFormat.format(new java.util.Date()));
    }

    private void setupTable() {
        tableModel = new DefaultTableModel(
            new Object[]{"ID", "Nombre", "Apellido", "Email", "Teléfono", "Estado", "Fecha Registro"}, 
            0
        ) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        table1.setModel(tableModel);
        table1.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        
        // Agregar listener para selección de filas
        table1.getSelectionModel().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                cargarDatosSeleccionados();
            }
        });
    }

    private void setupEventHandlers() {
        guardarButton.addActionListener(e -> {
            if (agregarUsuario()) {
                cargarUsuarios();
                limpiarCampos();
            }
        });

        actualizarButton.addActionListener(e -> {
            if (actualizarUsuario()) {
                cargarUsuarios();
                limpiarCampos();
            }
        });

        eliminarButton.addActionListener(e -> {
            if (eliminarUsuario()) {
                cargarUsuarios();
                limpiarCampos();
            }
        });

        buscarButton.addActionListener(e -> buscarUsuario());

        limpiarButton.addActionListener(e -> limpiarCampos());
    }

    private boolean agregarUsuario() {
        try {
            String nombre = textNombre.getText().trim();
            String apellido = textApellido.getText().trim();
            String email = textCorreo.getText().trim();
            String contrasena = new String(textContrasena.getPassword());
            String telefono = textTelefono.getText().trim();
            String estado = (String) comboEstado.getSelectedItem();
            Date fechaRegistro = parseDate(textFecha.getText());

            if (fechaRegistro == null) {
                JOptionPane.showMessageDialog(UsuarioForm, "Error: Formato de fecha inválido. Use yyyy-MM-dd", 
                    "Error de Validación", JOptionPane.ERROR_MESSAGE);
                return false;
            }

            return usuarioController.agregarUsuario(nombre, apellido, email, contrasena, telefono, estado, fechaRegistro);

        } catch (Exception e) {
            JOptionPane.showMessageDialog(UsuarioForm, "Error al agregar usuario: " + e.getMessage(), 
                "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
    }

    private boolean actualizarUsuario() {
        try {
            String idText = textID.getText().trim();
            if (idText.isEmpty()) {
                JOptionPane.showMessageDialog(UsuarioForm, "Seleccione un usuario de la tabla para actualizar", 
                    "Error de Validación", JOptionPane.WARNING_MESSAGE);
                return false;
            }

            int usuarioId = Integer.parseInt(idText);
            String nombre = textNombre.getText().trim();
            String apellido = textApellido.getText().trim();
            String email = textCorreo.getText().trim();
            String contrasena = new String(textContrasena.getPassword());
            String telefono = textTelefono.getText().trim();
            String estado = (String) comboEstado.getSelectedItem();
            Date fechaRegistro = parseDate(textFecha.getText());

            if (fechaRegistro == null) {
                JOptionPane.showMessageDialog(UsuarioForm, "Error: Formato de fecha inválido. Use yyyy-MM-dd", 
                    "Error de Validación", JOptionPane.ERROR_MESSAGE);
                return false;
            }

            return usuarioController.actualizarUsuario(usuarioId, nombre, apellido, email, contrasena, telefono, estado, fechaRegistro);

        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(UsuarioForm, "Error: ID de usuario inválido", 
                "Error de Validación", JOptionPane.ERROR_MESSAGE);
            return false;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(UsuarioForm, "Error al actualizar usuario: " + e.getMessage(), 
                "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
    }

    private boolean eliminarUsuario() {
        try {
            String idText = textID.getText().trim();
            if (idText.isEmpty()) {
                JOptionPane.showMessageDialog(UsuarioForm, "Seleccione un usuario de la tabla para eliminar", 
                    "Error de Validación", JOptionPane.WARNING_MESSAGE);
                return false;
            }

            int usuarioId = Integer.parseInt(idText);
            
            int confirmacion = JOptionPane.showConfirmDialog(UsuarioForm, 
                "¿Está seguro de que desea eliminar este usuario?", 
                "Confirmar Eliminación", 
                JOptionPane.YES_NO_OPTION, 
                JOptionPane.QUESTION_MESSAGE);

            if (confirmacion == JOptionPane.YES_OPTION) {
                return usuarioController.eliminarUsuario(usuarioId);
            }
            return false;

        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(UsuarioForm, "Error: ID de usuario inválido", 
                "Error de Validación", JOptionPane.ERROR_MESSAGE);
            return false;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(UsuarioForm, "Error al eliminar usuario: " + e.getMessage(), 
                "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
    }

    private void buscarUsuario() {
        String email = JOptionPane.showInputDialog(UsuarioForm, "Ingrese el email del usuario a buscar:");
        if (email != null && !email.trim().isEmpty()) {
            UsuarioModel usuario = usuarioController.getUsuarioByEmail(email.trim());
            if (usuario != null) {
                cargarDatosEnFormulario(usuario);
                JOptionPane.showMessageDialog(UsuarioForm, "Usuario encontrado", 
                    "Búsqueda Exitosa", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(UsuarioForm, "Usuario no encontrado", 
                    "Búsqueda Sin Resultados", JOptionPane.INFORMATION_MESSAGE);
            }
        }
    }

    private void cargarUsuarios() {
        tableModel.setRowCount(0);
        List<UsuarioModel> usuarios = usuarioController.obtenerTodosLosUsuarios();
        
        for (UsuarioModel usuario : usuarios) {
            Object[] fila = {
                usuario.getUsuario_id(),
                usuario.getNombre(),
                usuario.getApellido(),
                usuario.getEmail(),
                usuario.getTelefono(),
                usuario.getEstado(),
                usuario.getFechaRegistro()
            };
            tableModel.addRow(fila);
        }
    }

    private void cargarDatosSeleccionados() {
        int selectedRow = table1.getSelectedRow();
        if (selectedRow >= 0) {
            textID.setText(tableModel.getValueAt(selectedRow, 0).toString());
            textNombre.setText(tableModel.getValueAt(selectedRow, 1).toString());
            textApellido.setText(tableModel.getValueAt(selectedRow, 2).toString());
            textCorreo.setText(tableModel.getValueAt(selectedRow, 3).toString());
            textTelefono.setText(tableModel.getValueAt(selectedRow, 4) != null ? 
                tableModel.getValueAt(selectedRow, 4).toString() : "");
            comboEstado.setSelectedItem(tableModel.getValueAt(selectedRow, 5).toString());
            textFecha.setText(tableModel.getValueAt(selectedRow, 6).toString());
            textContrasena.setText(""); // No mostrar contraseña por seguridad
        }
    }

    private void cargarDatosEnFormulario(UsuarioModel usuario) {
        textID.setText(String.valueOf(usuario.getUsuario_id()));
        textNombre.setText(usuario.getNombre());
        textApellido.setText(usuario.getApellido());
        textCorreo.setText(usuario.getEmail());
        textTelefono.setText(usuario.getTelefono());
        comboEstado.setSelectedItem(usuario.getEstado());
        textFecha.setText(usuario.getFechaRegistro().toString());
        textContrasena.setText(""); // No mostrar contraseña por seguridad
    }

    private void limpiarCampos() {
        textID.setText("");
        textNombre.setText("");
        textApellido.setText("");
        textCorreo.setText("");
        textContrasena.setText("");
        textTelefono.setText("");
        comboEstado.setSelectedIndex(0);
        textFecha.setText(dateFormat.format(new java.util.Date()));
        table1.clearSelection();
    }

    private Date parseDate(String dateString) {
        try {
            java.util.Date utilDate = dateFormat.parse(dateString);
            return new Date(utilDate.getTime());
        } catch (ParseException e) {
            return null;
        }
    }

    public JPanel getPanel() {
        return UsuarioForm;
    }
}