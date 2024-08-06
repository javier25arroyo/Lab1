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

    public static void main(String[] args) {
        JFrame frame = new JFrame("MenuForm");
        frame.setContentPane(new MenuForm().panel1);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

    public MenuForm() {
        ClienteForm.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                abrirClienteForm();
            }
        });

        EmpleadoForm.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                abrirEmpleadoForm();
            }
        });

        PedidoForm.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                abrirPedidoForm();
            }
        });

        ProductoForm.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                abrirProductoForm();
            }
        });

        ProveedorForm.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                abrirProveedorForm();
            }
        });

        RolForm.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                abrirRolForm();
            }
        });

        CRForm.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CRForm();
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
        frame.setSize(1200,600);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    private void abrirEmpleadoForm() {
        JFrame frame = new JFrame("EmpleadoForm");
        EmpleadoForm empleadoForm = new EmpleadoForm();
        frame.setContentPane(empleadoForm.getPanel());
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(1400,600);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    private void abrirPedidoForm() {
        JFrame frame = new JFrame("PedidoForm");
        PedidoForm pedidoForm = new PedidoForm();
        frame.setContentPane(pedidoForm.getPanel());
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    private void abrirProductoForm() {
        JFrame frame = new JFrame("ProductoForm");
        ProductoForm productoForm = new ProductoForm();
        frame.setContentPane(productoForm.getProductoForm());
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(1200,600);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    private void abrirProveedorForm() {
        JFrame frame = new JFrame("ProveedorForm");
        ProveedorForm proveedorForm = new ProveedorForm();
        frame.setContentPane(proveedorForm.getPanel());
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(1200,400);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    private void abrirRolForm() {
        JFrame frame = new JFrame("RolForm");
        RolForm rolForm = new RolForm();
        frame.setContentPane(rolForm.getPanel());
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    private void CRForm() {
        JFrame frame = new JFrame("panel1");
        ClienteRolForm clienteRolForm = new ClienteRolForm();
        frame.setContentPane(clienteRolForm.getPanel());
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}