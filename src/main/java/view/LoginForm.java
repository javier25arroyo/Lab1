package view;

import controller.ConexionController;
import model.Cliente.ClienteDAO;
import model.Cliente.ClienteModel;
import model.Conexion;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.SQLException;

public class LoginForm extends JFrame {
    private JTextField emailField1;
    private JPasswordField passwordField1;
    private JButton iniciarSesionButton;
    private JPanel loginPanel;

    public LoginForm() {
        setContentPane(loginPanel);
        setTitle("Login Form");
        setSize(800, 600);
        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);

        iniciarSesionButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String email = emailField1.getText();
                String password = new String(passwordField1.getPassword());

                try {
                    if (validateLogin(email, password)) {
                        JOptionPane.showMessageDialog(null, "Login Successful");
                        abrirMenuForm();
                    } else {
                        JOptionPane.showMessageDialog(null, "Invalid email or password");
                    }
                } catch (SQLException ex) {
                    manejarErrorDeBaseDeDatos(ex);
                }
            }
        });
    }

    private boolean validateLogin(String email, String password) throws SQLException {
        Connection connection = Conexion.getConnection();
        ClienteDAO clienteDAO = new ClienteDAO(connection);
        ClienteModel cliente = clienteDAO.getClienteByEmail(email);
        return cliente != null && cliente.getContrasena().equals(password);
    }

    private void abrirMenuForm() {
        dispose(); // Cierra la ventana de login
        JFrame menuFrame = new JFrame("Menu Form");
        MenuForm menuForm = new MenuForm();
        menuFrame.setContentPane(menuForm.getPanel());
        menuFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        menuFrame.setSize(500,400);
        menuFrame.setLocationRelativeTo(null);
        menuFrame.setVisible(true);
    }

    private void manejarErrorDeBaseDeDatos(SQLException ex) {
        ex.printStackTrace();
        JOptionPane.showMessageDialog(null, "Database error: " + ex.getMessage());
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new LoginForm());
    }
}