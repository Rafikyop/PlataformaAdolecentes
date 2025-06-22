package com.plataforma.proyecto;

import com.plataforma.proyecto.dao.UsuarioDAO;
import com.plataforma.proyecto.dto.UsuarioDTO;
import com.plataforma.proyecto.dao.IntervencionDAO;
import com.plataforma.proyecto.dto.IntervencionDTO;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class AppGUI extends JFrame {

    // Atributos de clase
    private JTextField txtNombre, txtTipo, txtEmail, txtContraseña;
    private JTextField txtIdModificar, txtNuevoTipo, txtIdEliminar;
    private JTextArea areaUsuarios;
    private JButton btnActualizar, btnEliminar, btnCrear, btnListar;

    private UsuarioDAO usuarioDAO = new UsuarioDAO();
    private IntervencionDAO intervencionDAO = new IntervencionDAO();

    public AppGUI() {
        setTitle("Plataforma Apoyo - Gestión de Usuarios");
        setSize(600, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        initUI();
    }

    private void initUI() {
        //JPanel panel = new JPanel(new GridLayout(6, 2, 5, 5));

        txtNombre = new JTextField();
        txtTipo = new JTextField();
        txtEmail = new JTextField();
        txtContraseña = new JTextField();
        txtIdModificar = new JTextField();
        txtNuevoTipo = new JTextField();
        txtIdEliminar = new JTextField();

    // Crear contenedor de pestañas
        JTabbedPane tabs = new JTabbedPane();

    // Panel principal de usuarios
        JPanel panelUsuarios = new JPanel();
        panelUsuarios.setLayout(new BorderLayout());

    // Subpanel del formulario (datos)
        JPanel formPanel = new JPanel(new GridLayout(7, 2, 5, 5));
        formPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        formPanel.add(new JLabel("Nombre:"));          formPanel.add(txtNombre);
        formPanel.add(new JLabel("Tipo:"));            formPanel.add(txtTipo);
        formPanel.add(new JLabel("Email:"));           formPanel.add(txtEmail);
        formPanel.add(new JLabel("Contraseña:"));      formPanel.add(txtContraseña);
        formPanel.add(new JLabel("ID a Modificar:"));  formPanel.add(txtIdModificar);
        formPanel.add(new JLabel("Nuevo Tipo:"));      formPanel.add(txtNuevoTipo);
        formPanel.add(new JLabel("ID a Eliminar:"));   formPanel.add(txtIdEliminar);

    // Subpanel de botones en línea
        btnActualizar = new JButton("Actualizar Tipo");
        btnEliminar = new JButton("Eliminar Usuario");
        btnCrear = new JButton("Crear Usuario");
        btnListar = new JButton("Listar Usuarios");
    
        JPanel botonesPanel = new JPanel();
        botonesPanel.setLayout(new BoxLayout(botonesPanel, BoxLayout.X_AXIS));
        botonesPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
    
    // Opcional: Establecer tamaño uniforme
        Dimension btnSize = new Dimension(160, 30);
        btnActualizar.setPreferredSize(btnSize);
        btnEliminar.setPreferredSize(btnSize);
        btnCrear.setPreferredSize(btnSize);
        btnListar.setPreferredSize(btnSize);
    
    // Agregar botones con espaciado
        botonesPanel.add(Box.createHorizontalGlue());
        botonesPanel.add(btnActualizar);
        botonesPanel.add(Box.createRigidArea(new Dimension(10, 0)));
        botonesPanel.add(btnEliminar);
        botonesPanel.add(Box.createRigidArea(new Dimension(10, 0)));
        botonesPanel.add(btnCrear);
        botonesPanel.add(Box.createRigidArea(new Dimension(10, 0)));
        botonesPanel.add(btnListar);
        botonesPanel.add(Box.createHorizontalGlue());
    // Área de texto y scroll
        areaUsuarios = new JTextArea();
        areaUsuarios.setEditable(false);
        JScrollPane scroll = new JScrollPane(areaUsuarios);

    // Agregar partes al panel de usuarios
        panelUsuarios.add(formPanel, BorderLayout.NORTH);
        panelUsuarios.add(botonesPanel, BorderLayout.CENTER);
        panelUsuarios.add(scroll, BorderLayout.SOUTH);

    // Agregar pestaña
        tabs.addTab("Usuarios", panelUsuarios);
        tabs.addTab("Intervenciones", crearPanelIntervenciones());

    // Mostrar pestañas en la ventana
        add(tabs);

    /* Esta parte del codigo fue el inicio del panel, lo dejo para que se pueda ver en el archivo java y que lo otro no salio de la nada, realmente esto hacia
     que los botones se alargaran cuando la pestana se alargaba a la par, entonces cambie mejor de un panel, para un form panel y que no se deformara la estructura
     tambien se pusieron los paneles con un BoxLayout para que se quedaran estaticos. */

        /*JTextField txtIdModificar = new JTextField();
        JTextField txtNuevoTipo = new JTextField();
        JTextField txtIdEliminar = new JTextField();
        JButton btnActualizar = new JButton("Actualizar Tipo");
        JButton btnEliminar = new JButton("Eliminar Usuario");

        JButton btnCrear = new JButton("Crear Usuario");
        JButton btnListar = new JButton("Listar Usuarios");

        areaUsuarios = new JTextArea();
        areaUsuarios.setEditable(false);
        JScrollPane scroll = new JScrollPane(areaUsuarios);

        panel.add(new JLabel("Nombre:"));
        panel.add(txtNombre);
        panel.add(new JLabel("Tipo:"));
        panel.add(txtTipo);
        panel.add(new JLabel("Email:"));
        panel.add(txtEmail);
        panel.add(new JLabel("Contraseña:"));
        panel.add(txtContraseña);

        // Campos para actualizar y eliminar
        panel.add(new JLabel("ID a Modificar:"));
        panel.add(txtIdModificar);
        panel.add(new JLabel("Nuevo Tipo:"));
        panel.add(txtNuevoTipo);
        panel.add(btnActualizar);

        panel.add(new JLabel("ID a Eliminar:"));
        panel.add(txtIdEliminar);
        panel.add(btnEliminar);

        panel.add(btnCrear);
        panel.add(btnListar);

        add(panel, BorderLayout.NORTH);
        add(scroll, BorderLayout.CENTER);*/

        btnCrear.addActionListener(e -> {
            UsuarioDTO u = new UsuarioDTO(
                txtNombre.getText(),
                txtTipo.getText(),
                txtEmail.getText(),
                txtContraseña.getText()
            );
            usuarioDAO.insertarUsuario(u);
            limpiarCampos();
            JOptionPane.showMessageDialog(this, "Usuario creado correctamente.");
        });

        btnListar.addActionListener(e -> {
            List<UsuarioDTO> lista = usuarioDAO.obtenerUsuarios();
            areaUsuarios.setText("");
            for (UsuarioDTO u : lista) {
                areaUsuarios.append("ID: " + u.getId() + " | Nombre: " + u.getNombre() + " | Tipo: " + u.getTipo() + "\n");
            }
        });

        btnActualizar.addActionListener(e -> {
            try {
                int id = Integer.parseInt(txtIdModificar.getText());
                String nuevoTipo = txtNuevoTipo.getText();
                usuarioDAO.actualizarTipoUsuario(id, nuevoTipo);
                JOptionPane.showMessageDialog(this, "Tipo actualizado correctamente.");
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Error al actualizar: " + ex.getMessage());
            }
        });

        btnEliminar.addActionListener(e -> {
            try {
                int id = Integer.parseInt(txtIdEliminar.getText());
                usuarioDAO.eliminarUsuario(id);
                JOptionPane.showMessageDialog(this, "Usuario eliminado.");
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Error al eliminar: " + ex.getMessage());
            }
        });
    }

     // Crear panel de intervenciones
     private JPanel crearPanelIntervenciones() {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBorder(BorderFactory.createTitledBorder("Registrar Intervención"));
    
        JPanel campos = new JPanel(new GridLayout(5, 2, 5, 5));
    
        JTextField txtReporteId = new JTextField();
        JTextField txtModeradorId = new JTextField();
        JTextField txtPsicologoId = new JTextField();
        JTextArea txtDescripcion = new JTextArea(3, 20);
        JButton btnRegistrar = new JButton("Registrar Intervención");
    
        campos.add(new JLabel("ID Reporte:"));
        campos.add(txtReporteId);
        campos.add(new JLabel("ID Moderador:"));
        campos.add(txtModeradorId);
        campos.add(new JLabel("ID Psicólogo:"));
        campos.add(txtPsicologoId);
        campos.add(new JLabel("Descripción de Acción:"));
        campos.add(new JScrollPane(txtDescripcion));
    
        panel.add(campos);
        panel.add(btnRegistrar);
    
        btnRegistrar.addActionListener(e -> {
            try {
                int reporteId = Integer.parseInt(txtReporteId.getText());
                int moderadorId = Integer.parseInt(txtModeradorId.getText());
                int psicologoId = Integer.parseInt(txtPsicologoId.getText());
                String descripcion = txtDescripcion.getText();
    
                IntervencionDTO dto = new IntervencionDTO(reporteId, moderadorId, psicologoId, descripcion);
                intervencionDAO.registrarIntervencion(dto);
    
                JOptionPane.showMessageDialog(panel, "Intervención registrada correctamente.");
                txtReporteId.setText("");
                txtModeradorId.setText("");
                txtPsicologoId.setText("");
                txtDescripcion.setText("");
    
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(panel, "Verifica que los IDs sean números válidos.");
            }
        });
    
        return panel;
    }

    private void limpiarCampos() {
        txtNombre.setText("");
        txtTipo.setText("");
        txtEmail.setText("");
        txtContraseña.setText("");
    }

    public static void main(String[] args) {
        // Activa tema moderno
        try {
            UIManager.setLookAndFeel(new com.formdev.flatlaf.FlatDarkLaf());
        } catch (Exception e) {
            e.printStackTrace();
        }

        SwingUtilities.invokeLater(() -> {
            new AppGUI().setVisible(true);
        });
    }
}