package view;

import controller.ClienteRolController;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.List;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import model.ClienteRol.ClienteRolModel;
import static org.conexionMySql.Main.eliminarClienteRol;

public class ClienteRolForm {
    private JPanel ClienteRolForm;
    private JPanel panel1;
    private JTextField textIdClienteTextField;
    private JTextField textIdRol;
    private JButton guardarButton;
    private JButton eliminarButton;
    private JButton actualizarButton;
    private JButton buscarButton;
    private JTable table1;
    private JTextField textID;
    private JButton atrasButton;
    private ClienteRolController clienteRolController;
    private DefaultTableModel tableModel;

    public static void main(String[] args) {
        JFrame frame = new JFrame("ClienteRolForm");
        frame.setContentPane(new ClienteRolForm().panel1);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1000,500);
        frame.setVisible(true);
    }

    public ClienteRolForm(){
        clienteRolController = new ClienteRolController(new ConsoleView());
        tableModel = new DefaultTableModel(new Object[]{"id","cliente_id", "rol_id"}, 0);
        table1.setModel(tableModel);

        guardarButton.addActionListener(e -> {
            agregarClienteRol();
            cargarClienteRoles();
            limpiarCampos();
        });

        eliminarButton.addActionListener(e -> {
            eliminarClienteRol();
            cargarClienteRoles();
            limpiarCampos();
        });

        buscarButton.addActionListener(e -> buscarClienteRol());

        actualizarButton.addActionListener(e -> {
            updateClienteRol();
            cargarClienteRoles();
            limpiarCampos();
        });

        cargarClienteRoles();
        atrasButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) { abrirMenu();}
        });
    }

    private void updateClienteRol() {
        int id = Integer.parseInt(textID.getText());
        int cliente_id = Integer.parseInt(textIdClienteTextField.getText());
        int rol_id = Integer.parseInt(textIdRol.getText());

       clienteRolController.actualizarClienteRol(id, cliente_id, rol_id);
        JOptionPane.showMessageDialog(null, "ClienteRol actualizado correctamente");
    }

    public JPanel getPanel() {
        return panel1;
    }

    private void agregarClienteRol() {
        int cliente_id = Integer.parseInt(textIdClienteTextField.getText());
        int rol_id = Integer.parseInt(textIdRol.getText());

        clienteRolController.agregarClienteRol(cliente_id, rol_id);
        JOptionPane.showMessageDialog(null, "ClienteRol agregado correctamente");
    }
    public void eliminarClienteRol() {
        int id = Integer.parseInt(textID.getText());
        clienteRolController.eliminarClienteRol(id);
        JOptionPane.showMessageDialog(null, "El clienteRol fue eliminado con éxito");
    }

    public void buscarClienteRol() {
        int id = Integer.parseInt(textID.getText());
        ClienteRolModel clienteRol = clienteRolController.getClienteRolByID(id);

        if (clienteRol == null) {
            JOptionPane.showMessageDialog(null, "ClienteRol no encontrado");
        } else {
            textIdClienteTextField.setText(String.valueOf(clienteRol.getFK_idCliente()));
            textIdRol.setText(String.valueOf(clienteRol.getFK_idRol()));
        }
    }

    private void limpiarCampos() {
        textIdClienteTextField.setText("");
        textIdRol.setText("");
    }

    public void cargarClienteRoles() {
        tableModel.setRowCount(0);
        try{
            List<ClienteRolModel> clienteRoles = clienteRolController.obtenerClienteRol();
            for (ClienteRolModel clienteRol : clienteRoles)
                tableModel.addRow(new Object[]{clienteRol.getId(),clienteRol.getFK_idCliente(), clienteRol.getFK_idRol()});
        }catch (Exception e){
            JOptionPane.showMessageDialog(null, "Error al cargar los clienteRoles");
        }
    }

    public void abrirMenu() {
        JFrame frame = new JFrame("Menu");
        MenuForm menuForm = new MenuForm();
        frame.setContentPane(menuForm.getPanel());
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}

