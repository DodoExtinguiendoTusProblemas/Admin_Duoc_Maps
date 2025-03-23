package Vistas;

import Conexiones.ConsejeroDAO;
import Modelos.Consejero;
import Modelos.TablaNoEditable;
import javax.swing.*;
import java.awt.*;
import Modelos.ValidaciónCorreo;
import java.awt.image.BufferedImage;
import javax.swing.table.DefaultTableModel;
import java.net.URL;
import javax.swing.SwingWorker;

public class Consejeros extends javax.swing.JFrame {

    private ConsejeroDAO consejeroDAO;
    private JTable table;
    private DefaultTableModel tableModel;
    private JLabel lblImagenPreview;

    public Consejeros() {
        consejeroDAO = new ConsejeroDAO();
        setTitle("Gestión de Consejeros");
        setSize(800, 500); // Ajuste de tamaño para incluir la vista previa de la imagen
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        // Configuración de la tabla
        tableModel = new TablaNoEditable(new Object[]{"ID", "Nombre Completo", "Email", "Carrera", "Imagen"}, 0);
        table = new JTable(tableModel);
        table.setBackground(Color.BLACK); // Fondo negro
        table.setForeground(Color.WHITE); // Texto blanco
        table.setGridColor(Color.GRAY); // Líneas de separación
        table.setSelectionBackground(Color.DARK_GRAY); // Fondo al seleccionar
        table.setSelectionForeground(Color.WHITE); // Texto al seleccionar

        JScrollPane scrollPane = new JScrollPane(table);
        add(scrollPane, BorderLayout.CENTER);

        // Panel de vista previa de la imagen
        lblImagenPreview = new JLabel("Selecciona un consejero");
        lblImagenPreview.setHorizontalAlignment(SwingConstants.CENTER);
        lblImagenPreview.setPreferredSize(new Dimension(200, 200));
        lblImagenPreview.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        lblImagenPreview.setBackground(Color.decode("#F1B634"));
        lblImagenPreview.setOpaque(true);
        add(lblImagenPreview, BorderLayout.EAST);

        // Panel de botones
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

        // Acción para agregar un consejero
        btnAgregar.addActionListener(e -> agregarConsejero());

        // Acción para modificar un consejero
        btnModificar.addActionListener(e -> modificarConsejero());

        // Acción para eliminar un consejero
        btnEliminar.addActionListener(e -> eliminarConsejero());

        // Detectar selección en la tabla
        table.getSelectionModel().addListSelectionListener(event -> mostrarImagenSeleccionada());

        // Inicializar la tabla
        actualizarTabla();
    }

    private void agregarConsejero() {
        String nombre = JOptionPane.showInputDialog("Nombre completo del consejero:");
        String email = JOptionPane.showInputDialog("Email del consejero:");
        String carrera = JOptionPane.showInputDialog("Carrera del consejero:");

        if (nombre != null && email != null && carrera != null) {
            // Validar formato del correo electrónico
            if (!ValidaciónCorreo.esCorreoValido(email)) {
                JOptionPane.showMessageDialog(null, "El correo electrónico ingresado no es válido. Inténtelo de nuevo.");
                return;
            }

            // Preguntar si desea agregar una imagen
            int opcion = JOptionPane.showConfirmDialog(this, "¿Desea agregar una imagen?", "Agregar Imagen", JOptionPane.YES_NO_OPTION);
            String rutaImagen = null;

            if (opcion == JOptionPane.YES_OPTION) {
                // Mostrar un campo de texto para ingresar la URL de la imagen
                String urlImagen = JOptionPane.showInputDialog("Ingrese la URL de la imagen:");

                if (urlImagen != null && !urlImagen.isEmpty()) {
                    // Validar que la URL comience con "http://" o "https://"
                    if (urlImagen.startsWith("http://") || urlImagen.startsWith("https://")) {
                        try {
                            // Crear un ImageIcon a partir de la URL
                            URL url = new URL(urlImagen);
                            ImageIcon imageIcon = new ImageIcon(url);
                            // Escalar la imagen para que se ajuste al tamaño del JLabel
                            Image image = imageIcon.getImage().getScaledInstance(200, 200, Image.SCALE_SMOOTH);
                            lblImagenPreview.setIcon(new ImageIcon(image));
                            rutaImagen = urlImagen; // Asignar la URL de la imagen
                        } catch (Exception e) {
                            JOptionPane.showMessageDialog(this, "Error al cargar la imagen desde la URL.");
                            return;
                        }
                    } else {
                        JOptionPane.showMessageDialog(this, "La URL debe comenzar con http:// o https://");
                        return;
                    }
                }
            }

            // Crear el consejero con los datos proporcionados, incluyendo la imagen si es que se ha dado
            Consejero consejero = new Consejero(0, nombre, email, carrera, rutaImagen);
            consejeroDAO.agregarConsejero(consejero);
            actualizarTabla(); // Actualizar la tabla con el nuevo consejero
        }
    }

    private void modificarConsejero() {
        int selectedRow = table.getSelectedRow();
        if (selectedRow != -1) {
            int id = (int) tableModel.getValueAt(selectedRow, 0);
            Consejero consejero = consejeroDAO.buscarConsejero(id);
            if (consejero != null) {
                // Obtener los datos actuales
                String nuevoNombre = JOptionPane.showInputDialog("Nuevo nombre completo:", consejero.getNombreCompleto());
                String nuevoEmail = JOptionPane.showInputDialog("Nuevo email:", consejero.getEmail());
                String nuevaCarrera = JOptionPane.showInputDialog("Nueva carrera:", consejero.getCarrera());
                String nuevaRutaImagen = consejero.getRutaImagen(); // Mantener imagen actual

                // Validar el correo electrónico
                if (!ValidaciónCorreo.esCorreoValido(nuevoEmail)) {
                    JOptionPane.showMessageDialog(null, "El correo electrónico ingresado no es válido. Inténtelo de nuevo.");
                    return;
                }

                // Preguntar si desea cambiar la imagen
                int opcion = JOptionPane.showConfirmDialog(this, "¿Desea cambiar la imagen del consejero?", "Cambiar Imagen", JOptionPane.YES_NO_OPTION);
                if (opcion == JOptionPane.YES_OPTION) {
                    // Mostrar un campo de texto para ingresar la URL de la nueva imagen
                    String urlImagen = JOptionPane.showInputDialog("Ingrese la URL de la nueva imagen:");

                    if (urlImagen != null && !urlImagen.isEmpty()) {
                        // Validar que la URL comience con "http://" o "https://"
                        if (urlImagen.startsWith("http://") || urlImagen.startsWith("https://")) {
                            try {
                                // Crear un ImageIcon a partir de la URL
                                URL url = new URL(urlImagen);
                                ImageIcon imageIcon = new ImageIcon(url);
                                // Escalar la imagen para que se ajuste al tamaño del JLabel
                                Image image = imageIcon.getImage().getScaledInstance(200, 200, Image.SCALE_SMOOTH);
                                lblImagenPreview.setIcon(new ImageIcon(image));
                                nuevaRutaImagen = urlImagen; // Asignar la nueva URL de la imagen
                            } catch (Exception e) {
                                JOptionPane.showMessageDialog(this, "Error al cargar la imagen desde la URL.");
                                return;
                            }
                        } else {
                            JOptionPane.showMessageDialog(this, "La URL debe comenzar con http:// o https://");
                            return;
                        }
                    }
                }

                // Actualizar el Consejero con los nuevos valores
                consejero.setNombreCompleto(nuevoNombre);
                consejero.setEmail(nuevoEmail);
                consejero.setCarrera(nuevaCarrera);
                consejero.setRutaImagen(nuevaRutaImagen); // Actualizar la ruta de la imagen

                // Guardar los cambios
                consejeroDAO.actualizarConsejero(consejero);
                actualizarTabla();
            }
        } else {
            JOptionPane.showMessageDialog(this, "Seleccione un consejero para modificar.");
        }
    }

    private void eliminarConsejero() {
        int selectedRow = table.getSelectedRow();
        if (selectedRow != -1) {
            int id = (int) tableModel.getValueAt(selectedRow, 0);

            // Confirmar eliminación
            int confirm = JOptionPane.showConfirmDialog(
                    this,
                    "¿Está seguro de que desea eliminar este consejero?",
                    "Confirmar Eliminación",
                    JOptionPane.YES_NO_OPTION
            );

            if (confirm == JOptionPane.YES_OPTION) {
                consejeroDAO.eliminarConsejero(id);
                actualizarTabla();
                lblImagenPreview.setIcon(null); // Limpiar la vista previa
                lblImagenPreview.setText("Selecciona un consejero");
            }
        } else {
            JOptionPane.showMessageDialog(null, "Seleccione un consejero para eliminar.");
        }
    }

    private void mostrarImagenSeleccionada() {
        int selectedRow = table.getSelectedRow();
        if (selectedRow != -1) {
            int id = (int) tableModel.getValueAt(selectedRow, 0);
            Consejero consejero = consejeroDAO.buscarConsejero(id);
            if (consejero != null && consejero.getRutaImagen() != null) {
                String urlImagen = consejero.getRutaImagen();
                try {
                    // Crear un ImageIcon a partir de la URL
                    URL url = new URL(urlImagen);
                    ImageIcon imageIcon = new ImageIcon(url);
                    // Escalar la imagen para que se ajuste al tamaño del JLabel
                    Image image = imageIcon.getImage().getScaledInstance(200, 200, Image.SCALE_SMOOTH);
                    lblImagenPreview.setIcon(new ImageIcon(image));
                    lblImagenPreview.setText(""); // Limpiar texto
                } catch (Exception e) {
                    lblImagenPreview.setIcon(null);
                    lblImagenPreview.setText("Imagen no disponible");
                }
            } else {
                lblImagenPreview.setIcon(null);
                lblImagenPreview.setText("Sin imagen");
            }
        } else {
            lblImagenPreview.setIcon(null);
            lblImagenPreview.setText("Selecciona un consejero");
        }
    }

    private void actualizarTabla() {
        // Limpiar la tabla
        tableModel.setRowCount(0);

        // Obtener los consejeros de la base de datos
        for (Consejero consejero : consejeroDAO.listarConsejeros()) { // Cambié obtenerConsejeros() por listarConsejeros()
            Object[] rowData = new Object[5];
            rowData[0] = consejero.getId();
            rowData[1] = consejero.getNombreCompleto();
            rowData[2] = consejero.getEmail();
            rowData[3] = consejero.getCarrera();
            rowData[4] = consejero.getRutaImagen(); // Guardar la URL de la imagen
            tableModel.addRow(rowData);
        }
    }

    private void configurarBoton(JButton boton) {
        boton.setBackground(Color.decode("#1A1A1A")); // Fondo negro
        boton.setForeground(Color.decode("#FFFFFF")); // Texto blanco
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
            java.util.logging.Logger.getLogger(Consejeros.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Consejeros.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Consejeros.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Consejeros.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Consejeros().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
