package Vistas;

import Controlador.PuntoInteresControlador;
import Modelos.PuntoInteres;
import Modelos.TablaNoEditable;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.net.URL;
import javax.imageio.ImageIO;
import javax.swing.SwingWorker;


public class PuntoInteresView extends javax.swing.JFrame {

    private PuntoInteresControlador puntoInteresController;
    private JTable table;
    private DefaultTableModel tableModel;
    private JLabel lblImagenPreview;

    public PuntoInteresView() {
        puntoInteresController = new PuntoInteresControlador();
        setTitle("Gestión de Puntos de Interés");
        setSize(600, 500); // Ajuste para incluir la vista previa de la imagen
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        // Configuración de la tabla
        tableModel = new TablaNoEditable(new Object[]{"ID", "Título", "Descripción", "Imagen"}, 0);
        table = new JTable(tableModel);
        table.setBackground(Color.BLACK);
        table.setForeground(Color.WHITE);
        table.setGridColor(Color.GRAY);
        table.setSelectionBackground(Color.DARK_GRAY);
        table.setSelectionForeground(Color.WHITE);

        JScrollPane scrollPane = new JScrollPane(table);
        add(scrollPane, BorderLayout.CENTER);

        // Vista previa de la imagen
        lblImagenPreview = new JLabel();
        lblImagenPreview.setHorizontalAlignment(SwingConstants.CENTER);
        lblImagenPreview.setPreferredSize(new Dimension(600, 150));
        lblImagenPreview.setBackground(Color.decode("#F1B634"));
        lblImagenPreview.setOpaque(true);
        add(lblImagenPreview, BorderLayout.NORTH);

        // Panel para los botones
        JPanel panelBotones = new JPanel();
        panelBotones.setBackground(Color.decode("#F1B634"));

        JButton btnAgregar = new JButton("Agregar");
        JButton btnModificar = new JButton("Modificar");
        JButton btnEliminar = new JButton("Eliminar");

        configurarBoton(btnAgregar);
        configurarBoton(btnModificar);
        configurarBoton(btnEliminar);

        panelBotones.add(btnAgregar);
        panelBotones.add(btnModificar);
        panelBotones.add(btnEliminar);
        add(panelBotones, BorderLayout.SOUTH);

        // Acción para agregar un punto de interés
        btnAgregar.addActionListener(e -> agregarPuntoInteres());

        // Acción para modificar un punto de interés
        btnModificar.addActionListener(e -> modificarPuntoInteres());

        // Acción para eliminar un punto de interés con confirmación
        btnEliminar.addActionListener(e -> eliminarPuntoInteres());

        // Detectar selección de fila en la tabla
        table.getSelectionModel().addListSelectionListener(event -> mostrarImagenSeleccionada());

        // Actualizar la tabla inicialmente
        actualizarTabla();
    }

    
    private void agregarPuntoInteres() {
    String titulo = JOptionPane.showInputDialog("Ingrese el título:");
    String descripcion = JOptionPane.showInputDialog("Ingrese la descripción:");

    if (titulo != null && descripcion != null) {
        // Preguntar si desea agregar una imagen
        int opcion = JOptionPane.showConfirmDialog(
                this,
                "¿Desea agregar una imagen?",
                "Agregar Imagen",
                JOptionPane.YES_NO_OPTION
        );

        String rutaImagen = null;

        if (opcion == JOptionPane.YES_OPTION) {
            // Mostrar un campo de texto para ingresar la ruta de la imagen
            String ruta = JOptionPane.showInputDialog("Ingrese la ruta de la imagen:");

            if (ruta != null && !ruta.isEmpty()) {
                // Verificar si la ruta es una URL
                if (ruta.startsWith("http://") || ruta.startsWith("https://")) {
                    try {
                        // Intentar cargar la imagen desde la URL
                        URL url = new URL(ruta);
                        BufferedImage image = ImageIO.read(url);
                        rutaImagen = ruta; // Usamos la URL directamente
                        lblImagenPreview.setIcon(new ImageIcon(image.getScaledInstance(600, 150, Image.SCALE_SMOOTH)));
                    } catch (Exception e) {
                        JOptionPane.showMessageDialog(this, "La URL no es válida o no se puede cargar la imagen.");
                    }
                } else {
                    // Validar si es una ruta local
                    File archivo = new File(ruta);
                    if (archivo.exists() && archivo.isFile()) {
                        rutaImagen = ruta;
                        lblImagenPreview.setIcon(new ImageIcon(new ImageIcon(rutaImagen)
                                .getImage().getScaledInstance(600, 150, Image.SCALE_SMOOTH)));
                    } else {
                        JOptionPane.showMessageDialog(this, "La ruta no es válida o el archivo no existe.");
                    }
                }
            }
        }

        puntoInteresController.agregarPuntoInteres(titulo, descripcion, rutaImagen);
        actualizarTabla();

        // Limpiar vista previa
        lblImagenPreview.setIcon(null);
    }
}
    
    
    
    private void modificarPuntoInteres() {
    int selectedRow = table.getSelectedRow();
    if (selectedRow != -1) {
        int id = (int) tableModel.getValueAt(selectedRow, 0);
        PuntoInteres puntoInteres = puntoInteresController.buscarPuntoInteres(id);
        if (puntoInteres != null) {
            String nuevoTitulo = JOptionPane.showInputDialog("Nuevo título:", puntoInteres.getTitulo());
            String nuevaDescripcion = JOptionPane.showInputDialog("Nueva descripción:", puntoInteres.getDescripcion());
            String nuevaRutaImagen = puntoInteres.getRutaImagen();

            // Preguntar si se desea cambiar la imagen
            int opcion = JOptionPane.showConfirmDialog(
                    this,
                    "¿Desea cambiar la imagen?",
                    "Cambiar Imagen",
                    JOptionPane.YES_NO_OPTION
            );

            if (opcion == JOptionPane.YES_OPTION) {
                // Mostrar un campo de texto para ingresar la nueva ruta de la imagen
                String nuevaRuta = JOptionPane.showInputDialog("Ingrese la nueva ruta de la imagen:", nuevaRutaImagen);

                if (nuevaRuta != null && !nuevaRuta.isEmpty()) {
                    // Verificar si la ruta es una URL
                    if (nuevaRuta.startsWith("http://") || nuevaRuta.startsWith("https://")) {
                        try {
                            // Intentar cargar la imagen desde la URL
                            URL url = new URL(nuevaRuta);
                            BufferedImage image = ImageIO.read(url);
                            nuevaRutaImagen = nuevaRuta; // Usamos la URL directamente
                            lblImagenPreview.setIcon(new ImageIcon(image.getScaledInstance(600, 150, Image.SCALE_SMOOTH)));
                        } catch (Exception e) {
                            JOptionPane.showMessageDialog(this, "La URL no es válida o no se puede cargar la imagen.");
                        }
                    } else {
                        // Validar si es una ruta local
                        File archivo = new File(nuevaRuta);
                        if (archivo.exists() && archivo.isFile()) {
                            nuevaRutaImagen = nuevaRuta;
                            lblImagenPreview.setIcon(new ImageIcon(new ImageIcon(nuevaRutaImagen)
                                    .getImage().getScaledInstance(600, 150, Image.SCALE_SMOOTH)));
                        } else {
                            JOptionPane.showMessageDialog(this, "La ruta no es válida o el archivo no existe.");
                        }
                    }
                }
            }

            if (nuevoTitulo != null && nuevaDescripcion != null) {
                puntoInteresController.actualizarPuntoInteres(id, nuevoTitulo, nuevaDescripcion, nuevaRutaImagen);
                actualizarTabla();
            }
        }
    } else {
        JOptionPane.showMessageDialog(this, "Seleccione un punto de interés para modificar.");
    }
}

    private void eliminarPuntoInteres() {
        int selectedRow = table.getSelectedRow();
        if (selectedRow != -1) {
            int id = (int) tableModel.getValueAt(selectedRow, 0);

            int confirm = JOptionPane.showConfirmDialog(
                this,
                "¿Está seguro de eliminar este punto de interés?",
                "Confirmar Eliminación",
                JOptionPane.YES_NO_OPTION
            );

            if (confirm == JOptionPane.YES_OPTION) {
                puntoInteresController.eliminarPuntoInteres(id);
                actualizarTabla();
                lblImagenPreview.setIcon(null); // Limpiar la vista previa
            }
        } else {
            JOptionPane.showMessageDialog(this, "Seleccione un punto de interés para eliminar.");
        }
    }

    /**
    private void mostrarImagenSeleccionada() {
        int selectedRow = table.getSelectedRow();
        if (selectedRow != -1) {
            int id = (int) tableModel.getValueAt(selectedRow, 0);
            PuntoInteres puntoInteres = puntoInteresController.buscarPuntoInteres(id);
            if (puntoInteres != null && puntoInteres.getRutaImagen() != null) {
                lblImagenPreview.setIcon(new ImageIcon(new ImageIcon(puntoInteres.getRutaImagen())
                        .getImage().getScaledInstance(600, 150, Image.SCALE_SMOOTH)));
            } else {
                lblImagenPreview.setIcon(null);
            }
        }
    }
    **/ 
    
    /**
    private void mostrarImagenSeleccionada() {
    int selectedRow = table.getSelectedRow();
    if (selectedRow != -1) {
        int id = (int) tableModel.getValueAt(selectedRow, 0);
        PuntoInteres puntoInteres = puntoInteresController.buscarPuntoInteres(id);

        if (puntoInteres != null && puntoInteres.getRutaImagen() != null && !puntoInteres.getRutaImagen().isEmpty()) {
            try {
                // Cargar la imagen desde la URL
                URL url = new URL(puntoInteres.getRutaImagen());
                BufferedImage image = ImageIO.read(url);

                // Escalar la imagen
                Image scaledImage = image.getScaledInstance(600, 150, Image.SCALE_SMOOTH);
                lblImagenPreview.setIcon(new ImageIcon(scaledImage));

            } catch (Exception e) {
                e.printStackTrace();
                lblImagenPreview.setIcon(null); // Si hay error, no muestra imagen
            }
        } else {
            lblImagenPreview.setIcon(null); // Si no hay imagen, limpia el label
        }
    }
}    
    **/

    private void mostrarImagenSeleccionada() {
        int selectedRow = table.getSelectedRow();
        if (selectedRow != -1) {
            int id = (int) tableModel.getValueAt(selectedRow, 0);
            PuntoInteres puntoInteres = puntoInteresController.buscarPuntoInteres(id);

            if (puntoInteres != null && puntoInteres.getRutaImagen() != null && !puntoInteres.getRutaImagen().isEmpty()) {
                // Mostrar imagen de carga mientras se descarga
                lblImagenPreview.setIcon(new ImageIcon("loading.png"));

                // Cargar la imagen en segundo plano
                new SwingWorker<ImageIcon, Void>() {
                    @Override
                    protected ImageIcon doInBackground() throws Exception {
                        try {
                            BufferedImage image = ImageIO.read(new URL(puntoInteres.getRutaImagen()));
                            // Escalar la imagen
                            return new ImageIcon(image.getScaledInstance(600, 150, Image.SCALE_SMOOTH));
                        } catch (Exception e) {
                            e.printStackTrace();
                            return null;
                        }
                    }

                    @Override
                    protected void done() {
                        try {
                            ImageIcon imageIcon = get();
                            lblImagenPreview.setIcon(imageIcon != null ? imageIcon : new ImageIcon("error.png"));
                        } catch (Exception e) {
                            lblImagenPreview.setIcon(new ImageIcon("error.png"));
                        }
                    }
                }.execute();
            } else {
                lblImagenPreview.setIcon(null);
            }
        }
    }
    
    
    private void actualizarTabla() {
        tableModel.setRowCount(0);
        for (PuntoInteres puntoInteres : puntoInteresController.listarPuntosInteres()) {
            tableModel.addRow(new Object[]{
                    puntoInteres.getId(),
                    puntoInteres.getTitulo(),
                    puntoInteres.getDescripcion(),
                    puntoInteres.getRutaImagen() != null ? "Sí" : "No"
            });
        }
    }

    private void configurarBoton(JButton boton) {
        boton.setBackground(Color.BLACK);
        boton.setForeground(Color.WHITE);
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
            java.util.logging.Logger.getLogger(PuntoInteresView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(PuntoInteresView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(PuntoInteresView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(PuntoInteresView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new PuntoInteresView().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
