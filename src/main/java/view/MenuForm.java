package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MenuForm {
    private JPanel panel1;
    private JButton ClienteForm;
    private JButton EmpleadoForm;
    private JButton PedidoForm;
    private JButton ProductoForm;
    private JButton ProveedorForm;
    private JButton RolForm;
    private JButton CRForm;

    public MenuForm() {
        ClienteForm.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                abrirClienteForm();
            }
        });
    }

    public Container getPanel() {
        return panel1;
    }

    private void abrirClienteForm() {
        JFrame frame = new JFrame("ClienteForm");
        ClienteForm clienteForm = new ClienteForm();
        frame.setContentPane(clienteForm.getPanel());
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}
