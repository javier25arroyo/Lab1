package view;

import controller.ConexionController;
import model.Empleado.*;
import javax.swing.*;
import java.sql.Connection;
import java.sql.SQLException;

public class EmpleadoForm {
    private JPanel empleadoPanel;
    private JTextField nombreField;
    private JTextField apellidoField;
    private JTextField cargoField;
    private JTextField salarioField;
    private JTextField fechaField;
    private JButton crearButton;
    private JTextField idField;
    private JButton actializarButton;
    private JButton eliminarButton;
    private JButton mostrarButton;
    private JTable table1;

    public EmpleadoForm() {
        crearButton.addActionListener(e -> agregarEmpleado());
        actializarButton.addActionListener(e -> actualizarEmpleado());
        eliminarButton.addActionListener(e -> eliminarEmpleado());
        mostrarButton.addActionListener(e -> obtenerTodosLosEmpleados());
    }

    private void createUIComponents() {
        // TODO: place custom component creation code here
    }
    private void agregarEmpleado() {
        String nombre = nombreField.getText();
        String apellido = apellidoField.getText();
        String cargo = cargoField.getText();
        String salario = salarioField.getText();
        String fecha = fechaField.getText();

        // Validación de campos y creación de objeto Empleado omitida por brevedad

        // Supongamos que existe una clase Empleado y EmpleadoDAO para manejar la lógica de negocio

        // Ejemplo de llamada a EmpleadoDAO para agregar el empleado
        // EmpleadoDAO.agregarEmpleado(new Empleado(nombre, apellido, cargo, salario, fecha));
        // JOptionPane.showMessageDialog(null, "Empleado agregado con éxito");
    }

    private void actualizarEmpleado() {
        String id = idField.getText();
        String nombre = nombreField.getText();
        String apellido = apellidoField.getText();
        String cargo = cargoField.getText();
        String salario = salarioField.getText();
        String fecha = fechaField.getText();

        // Validación de campos y actualización de objeto Empleado omitida por brevedad

        // Ejemplo de llamada a EmpleadoDAO para actualizar el empleado
        // EmpleadoDAO.actualizarEmpleado(new Empleado(id, nombre, apellido, cargo, salario, fecha));
        // JOptionPane.showMessageDialog(null, "Empleado actualizado con éxito");
    }

    private void eliminarEmpleado() {
        String id = idField.getText();

        // Validación de campo ID omitida por brevedad

        // Ejemplo de llamada a EmpleadoDAO para eliminar el empleado
        // EmpleadoDAO.eliminarEmpleado(id);
        // JOptionPane.showMessageDialog(null, "Empleado eliminado con éxito");
    }

    private void obtenerTodosLosEmpleados() {
        // Supongamos que EmpleadoDAO tiene un método obtenerTodosLosEmpleados que devuelve una lista de empleados
        // List<Empleado> empleados = EmpleadoDAO.obtenerTodosLosEmpleados();

        // Actualización de table1 con la lista de empleados omitida por brevedad
    }
}
