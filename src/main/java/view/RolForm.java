package view;

import com.formdev.flatlaf.FlatLightLaf;
import controller.RolController;
import model.Rol.RolModel;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class RolForm {
    private JPanel RolForm;
    private JTextField textNombre;
    private JTextField textDescripcion;
    private JButton guardarButton;
    private JTable table1;
    private JButton eliminarButton;
    private JButton actualizarButton;
    private JButton buscarButton;
    private JTextField textID;
    private RolController rolController;
    private DefaultTableModel tableModel;


    public RolForm() {
        rolController = new RolController(new ConsoleView());
        tableModel = new DefaultTableModel(new Object[]{"id", "nombre", "descripcion"}, 0);
        table1.setModel(tableModel);

        guardarButton.addActionListener(e -> {
            agregarRol();
            cargarRoles();
            limpiarCampos();
        });

        eliminarButton.addActionListener(e -> {
            eliminarRol();
            cargarRoles();
            limpiarCampos();
        });

        buscarButton.addActionListener(e -> buscarRol());

        actualizarButton.addActionListener(e -> {
            actualizarRol();
            cargarRoles();
            limpiarCampos();
        });

        cargarRoles();
    }

    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(new FlatLightLaf());
        } catch (Exception ex) {
            System.err.println("Failed to initialize LaF");
        }
        
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Gestión de Roles");
            frame.setContentPane(new RolForm().RolForm);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(1000, 500);
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
        });
    }

    public JPanel getPanel() {
        return RolForm;
    }

    public void agregarRol() {
        try {
            String nombre = textNombre.getText().trim();
            String descripcion = textDescripcion.getText().trim();
            
            if (nombre.isEmpty() || descripcion.isEmpty()) {
                JOptionPane.showMessageDialog(null, "El nombre y descripción son obligatorios", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            rolController.agregarRol(nombre, descripcion);
            JOptionPane.showMessageDialog(null, "El rol fue agregado con éxito", "Éxito", JOptionPane.INFORMATION_MESSAGE);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al agregar rol: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void buscarRol() {
        try {
            String idText = textID.getText().trim();
            if (idText.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Ingrese un ID de rol", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            
            int id = Integer.parseInt(idText);
            if (id <= 0) {
                JOptionPane.showMessageDialog(null, "El ID debe ser un número positivo", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            
            RolModel rol = rolController.getRolByID(id);

            if (rol == null) {
                JOptionPane.showMessageDialog(null, "Rol no encontrado", "Información", JOptionPane.INFORMATION_MESSAGE);
            } else {
                textNombre.setText(rol.getNombre());
                textDescripcion.setText(rol.getDescripcion());
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "El ID debe ser un número válido", "Error", JOptionPane.ERROR_MESSAGE);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al buscar rol: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void actualizarRol() {
        int id = Integer.parseInt(textID.getText());
        String nombre = textNombre.getText();
        String descripcion = textDescripcion.getText();

        rolController.actualizarRol(id, nombre, descripcion);
        JOptionPane.showMessageDialog(null, "El rol fue actualizado con éxito");
    }

    public void cargarRoles() {
        tableModel.setRowCount(0);
        try {
            List<RolModel> roles = rolController.obtenerTodosLosRoles();
            for (RolModel rol : roles) {
                tableModel.addRow(new Object[]{
                        rol.getId(),
                        rol.getNombre(),
                        rol.getDescripcion()
                });
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al cargar roles: " + e.getMessage());
        }
    }

    public void eliminarRol() {
        int id = Integer.parseInt(textID.getText());
        rolController.eliminarRol(id);
        JOptionPane.showMessageDialog(null, "El rol fue eliminado con éxito");
    }

    public void limpiarCampos() {
        textNombre.setText("");
        textDescripcion.setText("");
        textID.setText("");
    }
}
