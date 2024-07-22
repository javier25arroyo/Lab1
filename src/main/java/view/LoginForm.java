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
        setLocationRelativeTo(null);
        setVisible(true);

        iniciarSesionButton.addActionListener(e -> iniciarSesion());
    }

    private void iniciarSesion() {
        String email = emailField1.getText();
        String password = new String(passwordField1.getPassword());

        try {
            if (validateLogin(email, password)) {
                JOptionPane.showMessageDialog(null, "Login Successful");
                abrirClienteForm();
            } else {
                JOptionPane.showMessageDialog(null, "Invalid email or password");
            }
        } catch (SQLException ex) {
            manejarErrorDeBaseDeDatos(ex);
        }
    }

    private boolean validateLogin(String email, String password) throws SQLException {
        Connection connection = Conexion.getConnection();
        ClienteDAO clienteDAO = new ClienteDAO(connection);
        ClienteModel cliente = clienteDAO.getClienteByEmail(email);
        return cliente != null && cliente.getContrasena().equals(password);
    }

    private void abrirClienteForm() {
        dispose(); // Cierra la ventana de login
        JFrame clienteFrame = new JFrame("Cliente Form");
        ClienteForm clienteForm = new ClienteForm();
        clienteFrame.setContentPane(clienteForm.getPanel());
        clienteFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        clienteFrame.pack();
        clienteFrame.setLocationRelativeTo(null);
        clienteFrame.setVisible(true);
    }

    private void manejarErrorDeBaseDeDatos(SQLException ex) {
        ex.printStackTrace();
        JOptionPane.showMessageDialog(null, "Database error: " + ex.getMessage());
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new LoginForm());
    }
}
