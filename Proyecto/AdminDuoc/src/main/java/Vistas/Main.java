package Vistas;

import Modelos.Usuario;
import java.awt.BorderLayout;
import java.awt.Color;
import javax.swing.*;


public class Main extends javax.swing.JFrame {

    private JPanel panelImagen; // Panel que contendrá el JLabel con la imagen
    private JLabel labelImagen; // JLabel donde se mostrará la imagen

    public Main() {
        this(null); // Llama al constructor principal con valores nulos
    }

    public Main(Usuario usuarioActual) {
        setTitle("Administración de DuocUC San Bernardo");
        setSize(600, 400); // Ajustar tamaño de la ventana
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        getContentPane().setLayout(new BorderLayout()); // Usar BorderLayout para mayor control

        // Crear barra de menú
        JMenuBar menuBar = new JMenuBar();
        menuBar.setBackground(Color.decode("#1A1A1A")); // Fondo oscuro de la barra de menú

        JMenu menuGestion = new JMenu("Gestión");
        menuGestion.setForeground(Color.decode("#FFFFFF")); // Texto blanco

        JMenu menuUsuario = new JMenu("Usuario");
        menuUsuario.setForeground(Color.decode("#FFFFFF")); // Texto blanco

        // Opciones de gestión
        JMenuItem menuEventos = new JMenuItem("Eventos");
        JMenuItem menuSalas = new JMenuItem("Salas");
        JMenuItem menuConsejeros = new JMenuItem("Consejeros");
        JMenuItem menuPreguntasFrecuentes = new JMenuItem("Preguntas Frecuentes");
        JMenuItem menuPuntosInteres = new JMenuItem("Puntos de Interés");

        configurarMenuItem(menuEventos);
        configurarMenuItem(menuSalas);
        configurarMenuItem(menuConsejeros);
        configurarMenuItem(menuPreguntasFrecuentes);
        configurarMenuItem(menuPuntosInteres);

        // Opciones de usuario
        JMenuItem menuPerfil = new JMenuItem("Perfil");
        JMenuItem menuLogout = new JMenuItem("Cerrar Sesión");

        configurarMenuItem(menuPerfil);
        configurarMenuItem(menuLogout);

        // Acciones de gestión
        menuEventos.addActionListener(e -> new Eventos().setVisible(true));
        menuSalas.addActionListener(e -> new Salas().setVisible(true));
        menuConsejeros.addActionListener(e -> new Consejeros().setVisible(true));
        menuPreguntasFrecuentes.addActionListener(e -> new Preguntas_FrecuentesView().setVisible(true));
        menuPuntosInteres.addActionListener(e -> new PuntoInteresView().setVisible(true));

        // Acciones de usuario
        menuPerfil.addActionListener(e -> new PerfilUsuarioView(usuarioActual).setVisible(true));
        menuLogout.addActionListener(e -> {
            int confirm = JOptionPane.showConfirmDialog(
                    this,
                    "¿Estás seguro de que deseas cerrar sesión?",
                    "Cerrar Sesión",
                    JOptionPane.YES_NO_OPTION
            );
            if (confirm == JOptionPane.YES_OPTION) {
                dispose(); // Cerrar la ventana principal
                new Login().setVisible(true); // Regresar al login
            }
        });

        // Añadir elementos al menú
        menuGestion.add(menuEventos);
        menuGestion.add(menuSalas);
        menuGestion.add(menuConsejeros);
        menuGestion.add(menuPreguntasFrecuentes);
        menuGestion.add(menuPuntosInteres);

        menuUsuario.add(menuPerfil);
        menuUsuario.add(menuLogout);

        menuBar.add(menuGestion);
        menuBar.add(menuUsuario);

        setJMenuBar(menuBar);

        // Fondo general de la ventana
        getContentPane().setBackground(Color.decode("#F1B634")); // Fondo amarillo

        // Configurar el panel con la imagen
        configurarPanelConImagen();
        getContentPane().add(panelImagen, BorderLayout.CENTER); // Añadir el panel al centro
    }

    private void configurarPanelConImagen() {
        // Crear el panel
        panelImagen = new JPanel();
        panelImagen.setBackground(Color.decode("#F1B634")); // Fondo amarillo (opcional)
        panelImagen.setLayout(new BorderLayout());

        // Crear el JLabel con la imagen configurada manualmente
        labelImagen = new JLabel();
        labelImagen.setHorizontalAlignment(SwingConstants.CENTER); // Centrar horizontalmente
        labelImagen.setVerticalAlignment(SwingConstants.CENTER);   // Centrar verticalmente

        // Configurar la imagen manualmente
        labelImagen.setIcon(new ImageIcon("C:\\Users\\nicol\\Downloads\\Proyecto-7\\Proyecto-7\\Proyecto\\AdminDuoc\\src\\main\\java\\Imagenes\\duocMaps (2).png")); // Ruta relativa de la imagen

        // Añadir el JLabel al panel
        panelImagen.add(labelImagen, BorderLayout.CENTER);
    }

    private void configurarMenuItem(JMenuItem menuItem) {
        menuItem.setForeground(Color.decode("#FFFFFF")); // Texto blanco
        menuItem.setBackground(Color.decode("#1A1A1A")); // Fondo oscuro
    }




    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/duocMaps (2).png"))); // NOI18N
        jLabel1.setLabelFor(jLabel1);
        jLabel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jLabel1.setName("Label1"); // NOI18N

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(49, 49, 49)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 283, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(56, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(49, 49, 49)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 195, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(56, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
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
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Main().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    // End of variables declaration//GEN-END:variables
}
