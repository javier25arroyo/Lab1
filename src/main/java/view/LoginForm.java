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

public class LoginForm extends JDialog {
    private JTextField emailField1;
    private JPasswordField passwordField1;
    private JButton iniciarSesionButton;
    private JPanel loginPanel;

    public LoginForm() {
        setContentPane(loginPanel);
        setTitle("Login Form");
        setSize(400, 200);
        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        setVisible(true);

        iniciarSesionButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String email = emailField1.getText();
                String phone = passwordField1.getText();

                try {
                    boolean loginValido = validateLogin(email, phone);

                    if (loginValido) {
                        JOptionPane.showMessageDialog(null, "Login Successful");
                        // Aquí puedes redirigir a la siguiente pantalla o acción
                        Connection connection = Conexion.getConnection();
                        if (connection != null) {
                            // Continuar con la lógica que requiere la conexión al servidor
                            ConexionController conexionController = new ConexionController(new ConsoleView());
                            conexionController.openConnection();
                        } else {
                            JOptionPane.showMessageDialog(null, "Error al conectar al servidor remoto");
                        }
                    } else {
                        JOptionPane.showMessageDialog(null, "Invalid email or phone");
                    }
                } catch (SQLException ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(null, "Database error: " + ex.getMessage());
                }
            }
        });
    }

    private boolean validateLogin(String email, String phone) throws SQLException {
        Connection connection = Conexion.getConnection();
        ClienteDAO clienteDAO = new ClienteDAO(connection);
        ClienteModel cliente = clienteDAO.getClienteByEmail(email);
        return cliente != null && cliente.getTelefono().equals(phone);
    }

    public static void main(String[] args) {
        new LoginForm();
    }
}
