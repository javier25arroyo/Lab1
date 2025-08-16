package view;

import com.formdev.flatlaf.FlatLightLaf;
import controller.EmpleadoController;
import model.Empleado.EmpleadoModel;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.util.List;

public class EmpleadoForm {
    private JPanel EmpleadoForm;
    private JTextField textNombre;
    private JTextField textApellido;
    private JTextField textCargo;
    private JTextField textFecha;
    private JTextField textSalario;
    private JButton guardarButton;
    private JTable table1;
    private JButton eliminarButton;
    private JButton actualizarButton;
    private JButton buscarButton;
    private JTextField textID;
    private EmpleadoController empleadoController;
    private DefaultTableModel tableModel;

    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(new FlatLightLaf());
        } catch (Exception ex) {
            System.err.println("Failed to initialize LaF");
        }
        
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Gestión de Empleados");
            frame.setContentPane(new EmpleadoForm().EmpleadoForm);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(1200, 600);
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
        });
    }

    public EmpleadoForm() {
        empleadoController = new EmpleadoController(new ConsoleView());
        tableModel = new DefaultTableModel(new Object[]{"empleado_id", "nombre", "apellido", "cargo", "salario", "fecha_contratacion"}, 0);
        table1.setModel(tableModel);

        guardarButton.addActionListener(e -> {
            agregarEmpleado();
            cargarEmpleados();
            limpiarCampos();
        });

        eliminarButton.addActionListener(e -> {
            eliminarEmpleados();
            cargarEmpleados();
            limpiarCampos();
        });

        buscarButton.addActionListener(e -> buscarEmpleado());

        actualizarButton.addActionListener(e -> {
            actualizarEmpleado();
            cargarEmpleados();
            limpiarCampos();
        });

        cargarEmpleados();

    }

    public JPanel getPanel() {
        return EmpleadoForm;
    }

    public void agregarEmpleado() {
        try {
            String nombre = textNombre.getText().trim();
            String apellido = textApellido.getText().trim();
            String cargo = textCargo.getText().trim();
            
            if (nombre.isEmpty() || apellido.isEmpty() || cargo.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Todos los campos son obligatorios", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            
            Double salario = Double.parseDouble(textSalario.getText().trim());
            if (salario < 0) {
                JOptionPane.showMessageDialog(null, "El salario debe ser un valor positivo", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            
            Date fechaContratacion = Date.valueOf(textFecha.getText().trim());
            
            empleadoController.agregarEmpleado(nombre, apellido, cargo, salario, fechaContratacion);
            JOptionPane.showMessageDialog(null, "El empleado fue agregado con éxito", "Éxito", JOptionPane.INFORMATION_MESSAGE);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "El salario debe ser un número válido", "Error", JOptionPane.ERROR_MESSAGE);
        } catch (IllegalArgumentException e) {
            JOptionPane.showMessageDialog(null, "Formato de fecha inválido. Use YYYY-MM-DD", "Error", JOptionPane.ERROR_MESSAGE);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al agregar empleado: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void buscarEmpleado() {
        try {
            String idText = textID.getText().trim();
            if (idText.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Ingrese un ID de empleado", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            
            int id = Integer.parseInt(idText);
            if (id <= 0) {
                JOptionPane.showMessageDialog(null, "El ID debe ser un número positivo", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            
            EmpleadoModel empleado = empleadoController.getEmpleadoByID(id);

            if (empleado == null) {
                JOptionPane.showMessageDialog(null, "Empleado no encontrado", "Información", JOptionPane.INFORMATION_MESSAGE);
            } else {
                textNombre.setText(empleado.getNombre());
                textApellido.setText(empleado.getApellido());
                textCargo.setText(empleado.getCargo());
                textSalario.setText(String.valueOf(empleado.getSalario()));
                textFecha.setText(empleado.getFecha_contratacion().toString());
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "El ID debe ser un número válido", "Error", JOptionPane.ERROR_MESSAGE);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al buscar empleado: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void actualizarEmpleado() {
        int id = Integer.parseInt(textID.getText());
        String nombre = textNombre.getText();
        String apellido = textApellido.getText();
        String cargo = textCargo.getText();
        Double salario =Double.parseDouble( textSalario.getText());
        Date fechaContratacion = Date.valueOf(textFecha.getText());

        empleadoController.actualizarEmpleado(id, nombre, apellido, cargo, salario, fechaContratacion);
        JOptionPane.showMessageDialog(null, "El empleado fue actualizado con éxito");
    }

    public void cargarEmpleados() {
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

    public void eliminarEmpleados() {
        int id = Integer.parseInt(textID.getText());
        empleadoController.eliminarEmpleado(id);
        JOptionPane.showMessageDialog(null, "El empleado fue eliminado con éxito");
    }

    public void limpiarCampos() {
        textNombre.setText("");
        textApellido.setText("");
        textCargo.setText("");
        textSalario.setText("");
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