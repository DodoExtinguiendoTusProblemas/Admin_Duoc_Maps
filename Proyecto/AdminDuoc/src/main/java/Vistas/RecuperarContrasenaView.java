package Vistas;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class RecuperarContrasenaView extends JFrame {
    private JTextField txtEmail;
    private JButton btnRecuperar;
    private JButton btnVolver;

    public RecuperarContrasenaView() {
        setTitle("Recuperar Contraseña");
        setSize(400, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Configuración del panel principal
        JPanel panel = new JPanel(new GridLayout(3, 1));
        panel.setBackground(Color.decode("#F1B634")); // Fondo amarillo

        // Etiqueta con estilo
        JLabel lblCorreo = new JLabel("Ingrese su correo electrónico:");
        lblCorreo.setForeground(Color.decode("#1A1A1A")); // Texto negro

        txtEmail = new JTextField(20);
        txtEmail.setBackground(Color.WHITE); // Fondo blanco
        txtEmail.setForeground(Color.BLACK); // Texto negro

        // Botones con estilo
        btnRecuperar = new JButton("Recuperar Contraseña");
        btnVolver = new JButton("Volver");

        configurarBoton(btnRecuperar);
        configurarBoton(btnVolver);

        // Añadir componentes al panel
        panel.add(lblCorreo);
        panel.add(txtEmail);
        panel.add(btnRecuperar);
        panel.add(btnVolver);

        // Acción para recuperar contraseña
        btnRecuperar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String email = txtEmail.getText().trim();
                if (email.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Por favor, ingrese su correo electrónico.");
                } else {
                    // Simular recuperación
                    JOptionPane.showMessageDialog(null, "Se ha enviado un enlace de recuperación al correo: " + email);
                }
            }
        });

        // Acción para volver al login
        btnVolver.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose(); // Cerrar ventana actual
                new Login().setVisible(true); // Volver a la ventana de login
            }
        });

        add(panel);
    }

    private void configurarBoton(JButton boton) {
        boton.setBackground(Color.BLACK); // Fondo negro
        boton.setForeground(Color.WHITE); // Texto blanco
        boton.setFocusPainted(false);
        boton.setBorderPainted(false);
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
            java.util.logging.Logger.getLogger(RecuperarContrasenaView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(RecuperarContrasenaView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(RecuperarContrasenaView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(RecuperarContrasenaView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new RecuperarContrasenaView().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
