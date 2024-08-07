package view;

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
    private JButton atrasButton;
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
        JFrame frame = new JFrame("RolForm");
        frame.setContentPane(new RolForm().RolForm);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1000,500);
        frame.setVisible(true);
    }

    public JPanel getPanel() {
        return RolForm;
    }

    public void agregarRol() {
        String nombre = textNombre.getText();
        String descripcion = textDescripcion.getText();

        rolController.agregarRol(nombre, descripcion);
        JOptionPane.showMessageDialog(null, "El rol fue agregado con éxito");
    }

    public void buscarRol() {
        int id = Integer.parseInt(textID.getText());
        RolModel rol = rolController.getRolByID(id);

        if (rol == null) {
            JOptionPane.showMessageDialog(null, "Rol no encontrado");
        } else {
            textNombre.setText(rol.getNombre());
            textDescripcion.setText(rol.getDescripcion());
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
