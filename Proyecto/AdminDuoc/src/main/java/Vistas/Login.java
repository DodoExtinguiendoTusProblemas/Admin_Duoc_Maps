package Vistas;

import javax.swing.*;
import Conexiones.UsuarioDAO;
import Modelos.Usuario;
import java.awt.*;


public class Login extends javax.swing.JFrame {
    private JTextField txtEmail;
    private JPasswordField txtPassword;
    private JButton btnLogin;
    private JLabel lblForgotPassword;
    private UsuarioDAO usuarioDAO;

    public Login() {
        setTitle("Inicio de Sesión");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        usuarioDAO = new UsuarioDAO();

        // Panel principal con color de fondo
        JPanel panel = new JPanel();
        panel.setBackground(Color.decode("#F1b634")); // Fondo amarillo
        panel.setLayout(new GridLayout(5, 1, 10, 10));

        // Campos de entrada
        txtEmail = new JTextField(20);
        txtPassword = new JPasswordField(20);
        btnLogin = new JButton("Iniciar Sesión");

        // Personalización de estilos
        txtEmail.setBackground(Color.decode("#FFFFFF"));
        txtEmail.setForeground(Color.decode("#1A1A1A"));
        txtPassword.setBackground(Color.decode("#FFFFFF"));
        txtPassword.setForeground(Color.decode("#1A1A1A"));

        btnLogin.setBackground(Color.decode("#1A1A1A"));
        btnLogin.setForeground(Color.decode("#FFFFFF"));
        btnLogin.setFocusPainted(false);

        lblForgotPassword = new JLabel("<html><a href='#'>¿Olvidaste tu contraseña?</a></html>");
        lblForgotPassword.setHorizontalAlignment(SwingConstants.CENTER);
        lblForgotPassword.setForeground(Color.decode("#F1B634"));

        // Añadir elementos al panel
        panel.add(crearEtiqueta("Correo:"));
        panel.add(txtEmail);
        panel.add(crearEtiqueta("Contraseña:"));
        panel.add(txtPassword);
        panel.add(btnLogin);
        panel.add(lblForgotPassword);

        lblForgotPassword.setCursor(new Cursor(Cursor.HAND_CURSOR));
        lblForgotPassword.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent e) {
                dispose(); // Cierra la ventana de login
                new RecuperarContrasenaView().setVisible(true); // Abre la ventana de recuperación de contraseña
            }
        });

        btnLogin.addActionListener(e -> {
            String email = txtEmail.getText().trim();
            String password = new String(txtPassword.getPassword()).trim();

            Usuario usuarioLogueado = usuarioDAO.login(email, password);

            if (usuarioLogueado != null) {
                JOptionPane.showMessageDialog(null, "Inicio de sesión exitoso");

                new Main(usuarioLogueado).setVisible(true);
                dispose();
            } else {
                JOptionPane.showMessageDialog(null, "Correo o contraseña incorrectos");
            }
        });

        add(panel);
    }

    private JLabel crearEtiqueta(String texto) {
        JLabel etiqueta = new JLabel(texto);
        etiqueta.setForeground(Color.decode("#1A1A1A")); // Texto negro
        return etiqueta;
    }
    

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Login().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
