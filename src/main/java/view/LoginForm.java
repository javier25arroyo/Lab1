package view;

import com.formdev.flatlaf.FlatLightLaf;
import controller.ConexionController;
import model.Cliente.ClienteDAO;
import model.Cliente.ClienteModel;
import model.Conexion;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.regex.Pattern;

public class LoginForm extends JFrame {
    private JTextField emailField1;
    private JPasswordField passwordField1;
    private JButton iniciarSesionButton;
    private JPanel loginPanel;

    public LoginForm() {
        try {
            UIManager.setLookAndFeel(new FlatLightLaf());
        } catch (Exception ex) {
            System.err.println("Failed to initialize LaF");
        }
        
        setContentPane(loginPanel);
        setTitle("Inicio de Sesión");
        setSize(800, 600);
        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);

        iniciarSesionButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String email = emailField1.getText().trim();
                String password = new String(passwordField1.getPassword());

                if (email.isEmpty() || password.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Email y contraseña son obligatorios", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                
                if (!isValidEmail(email)) {
                    JOptionPane.showMessageDialog(null, "Formato de email inválido", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                try {
                    if (validateLogin(email, password)) {
                        JOptionPane.showMessageDialog(null, "Inicio de sesión exitoso", "Éxito", JOptionPane.INFORMATION_MESSAGE);
                        abrirMenuForm();
                    } else {
                        JOptionPane.showMessageDialog(null, "Email o contraseña inválidos", "Error", JOptionPane.ERROR_MESSAGE);
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

    private boolean isValidEmail(String email) {
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
        Pattern pattern = Pattern.compile(emailRegex);
        return pattern.matcher(email).matches();
    }
    
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new LoginForm());
    }
}