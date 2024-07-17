package view;

import controller.ConexionController;
import model.Cliente.ClienteDAO;
import model.Cliente.ClienteModel;
import model.Conexion;
import javax.swing.*;
import java.sql.Connection;
import java.sql.SQLException;

public class LoginForm extends JDialog {
    private JTextField emailField1;
    private JPasswordField passwordField1;
    private JButton iniciarSesionButton;
    private JPanel loginPanel;

    public LoginForm() {
        setContentPane(loginPanel);
        setTitle("Login Form");
        setSize(600, 500);
        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        setVisible(true);

        iniciarSesionButton.addActionListener(e -> iniciarSesion());
    }

    private void iniciarSesion() {
        String email = emailField1.getText();
        String phone = new String(passwordField1.getPassword());

        try {
            if (validateLogin(email, phone)) {
                JOptionPane.showMessageDialog(null, "Login Successful");
                abrirConexion();
            } else {
                JOptionPane.showMessageDialog(null, "Invalid email or phone");
            }
        } catch (SQLException ex) {
            manejarErrorDeBaseDeDatos(ex);
        }
    }

    private boolean validateLogin(String email, String phone) throws SQLException {
        Connection connection = Conexion.getConnection();
        ClienteDAO clienteDAO = new ClienteDAO(connection);
        ClienteModel cliente = clienteDAO.getClienteByEmail(email);
        return cliente != null && cliente.getTelefono().equals(phone);
    }

    private void abrirConexion() {
        Connection connection = Conexion.getConnection();
        if (connection != null) {
            ConexionController conexionController = new ConexionController(new ConsoleView());
            conexionController.openConnection();
        } else {
            JOptionPane.showMessageDialog(null, "Error al conectar al servidor remoto");
        }
    }

    private void manejarErrorDeBaseDeDatos(SQLException ex) {
        ex.printStackTrace();
        JOptionPane.showMessageDialog(null, "Database error: " + ex.getMessage());
    }

    public static void main(String[] args) {
        new LoginForm();
    }
}