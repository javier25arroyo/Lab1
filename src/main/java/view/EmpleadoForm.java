package view;

import controller.EmpleadoController;
import model.Empleado.EmpleadoModel;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.sql.Date;
import java.sql.SQLException;
import java.util.List;

public class EmpleadoForm {
    private JPanel empleadoPanel;
    private JTextField textNombre;
    private JTextField textApellido;
    private JTextField textCargo;
    private JTextField textSalario;
    private JTextField textFecha;
    private JTextField textID;
    private JButton eliminarButton;
    private JTable table1;
    private JButton buscarButton;
    private JButton actualizarButton;
    private JButton guardarButton;
    private EmpleadoController empleadoController;
    private DefaultTableModel tableModel;

    public static void main(String[] args) {
        JFrame frame = new JFrame("EmpleadoForm");
        EmpleadoForm empleadoForm = new EmpleadoForm();
        frame.setContentPane(empleadoForm.empleadoPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

    public EmpleadoForm() {
        empleadoController = new EmpleadoController(new ConsoleView());
        tableModel = new DefaultTableModel(new Object[]{"empleado_id", "nombre", "apellido", "cargo", "salario", "fecha_contratacion"}, 0);
        table1.setModel(tableModel);

        guardarButton.addActionListener(e -> {
            addEmpleado();
            loadEmpleados();
            clearFields();
        });

        actualizarButton.addActionListener(e -> {
            updateEmpleado();
            loadEmpleados();
            clearFields();
        });

        buscarButton.addActionListener(e -> {
            searchEmpleado();
        });

        eliminarButton.addActionListener(e -> {
            deleteEmpleado();
            loadEmpleados();
            clearFields();
        });


        loadEmpleados();
    }

    private void searchEmpleado() {
        int empleado_id = Integer.parseInt(textID.getText());
        EmpleadoModel empleado = empleadoController.getEmpleadoByID(empleado_id);
        if (empleado == null) {
            JOptionPane.showMessageDialog(null, "Empleado no encontrado");
        } else {
            textNombre.setText(empleado.getNombre());
            textApellido.setText(empleado.getApellido());
            textCargo.setText(empleado.getCargo());
            textSalario.setText(String.valueOf(empleado.getSalario()));
            textFecha.setText(empleado.getFecha_contratacion().toString());
        }
    }

    private void deleteEmpleado() {
        int empleado_id = Integer.parseInt(textID.getText());
        empleadoController.eliminarEmpleado(empleado_id);
        JOptionPane.showMessageDialog(null, "El empleado fue eliminado con éxito");
    }

    private void clearFields() {
        textNombre.setText("");
        textApellido.setText("");
        textCargo.setText("");
        textSalario.setText("");
        textFecha.setText("");
    }

    private void loadEmpleados() {
        tableModel.setRowCount(0);
        try {
            List<EmpleadoModel> empleados = empleadoController.obtenerTodosLosEmpleados();
            for (EmpleadoModel empleado : empleados) {
                tableModel.addRow(new Object[]{
                        empleado.getEmpleado_id(),
                        empleado.getNombre(),
                        empleado.getApellido(),
                        empleado.getCargo(),
                        empleado.getSalario(),
                        empleado.getFecha_contratacion()
                });
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al cargar empleados: " + e.getMessage());
        }
    }

    private void updateEmpleado() {
        int empleado_id = Integer.parseInt(textID.getText());
        String nombre = textNombre.getText();
        String apellido = textApellido.getText();
        String cargo = textCargo.getText();
        double salario = Double.parseDouble(textSalario.getText());
        Date fecha = Date.valueOf(textFecha.getText());

        empleadoController.actualizarEmpleado(empleado_id, nombre, apellido, cargo, salario, fecha);
        JOptionPane.showMessageDialog(null, "Empleado actualizado exitosamente");
    }

    public void addEmpleado() {
        String nombre = textNombre.getText();
        String apellido = textApellido.getText();
        String cargo = textCargo.getText();
        double salario = Double.parseDouble(textSalario.getText());
        Date fecha = Date.valueOf(textFecha.getText());

        empleadoController.agregarEmpleado(nombre, apellido, cargo, salario, fecha);
        JOptionPane.showMessageDialog(null, "El empleado fue agregado con éxito");
    }
}