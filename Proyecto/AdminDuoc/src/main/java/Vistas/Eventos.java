package Vistas;

import Controlador.Evento_Controlador;
import Modelos.Evento;
import Modelos.TablaNoEditable;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.imageio.ImageIO;

public class Eventos extends JFrame {
    private Evento_Controlador eventoController;
    private JTable table;
    private DefaultTableModel tableModel;

    public Eventos() {
        eventoController = new Evento_Controlador();
        setTitle("Gestión de Eventos");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        getContentPane().setBackground(Color.decode("#F1B634"));

        // Configuración de la tabla
        tableModel = new TablaNoEditable(new Object[]{"ID", "Título", "Descripción", "Fecha", "Imagen"}, 0);
        table = new JTable(tableModel);
        table.setBackground(Color.decode("#1A1A1A"));
        table.setForeground(Color.decode("#FFFFFF"));
        table.setGridColor(Color.GRAY);
        table.setSelectionBackground(Color.DARK_GRAY);
        table.setSelectionForeground(Color.WHITE);
        actualizarTabla();

        JScrollPane scrollPane = new JScrollPane(table);
        add(scrollPane, BorderLayout.CENTER);

        // Panel de botones
        JPanel panelBotones = new JPanel();
        panelBotones.setBackground(Color.decode("#F1B634"));

        JButton btnAgregar = new JButton("Agregar");
        JButton btnModificar = new JButton("Modificar");
        JButton btnEliminar = new JButton("Eliminar");
        JButton btnVerImagen = new JButton("Ver Imagen");

        configurarBoton(btnAgregar);
        configurarBoton(btnModificar);
        configurarBoton(btnEliminar);
        configurarBoton(btnVerImagen);

        panelBotones.add(btnAgregar);
        panelBotones.add(btnModificar);
        panelBotones.add(btnEliminar);
        panelBotones.add(btnVerImagen);
        add(panelBotones, BorderLayout.SOUTH);

        // Acción para agregar un evento
        btnAgregar.addActionListener(e -> {
            String titulo = JOptionPane.showInputDialog("Ingrese el título del evento:");
            String descripcion = JOptionPane.showInputDialog("Ingrese la descripción del evento:");
            String fechaStr = JOptionPane.showInputDialog("Ingrese la fecha del evento (yyyy-MM-dd):");
            String rutaImagen = JOptionPane.showInputDialog("Ingrese la URL de la imagen:");

            if (rutaImagen != null && !rutaImagen.isEmpty() && !validarImagenDesdeURL(rutaImagen)) {
                JOptionPane.showMessageDialog(this, "La URL de la imagen no es válida o no se puede cargar.");
                return;
            }

            try {
                Date fecha = new SimpleDateFormat("yyyy-MM-dd").parse(fechaStr);
                eventoController.agregarEvento(titulo, descripcion, fecha, rutaImagen);
                actualizarTabla();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, "Formato de fecha inválido.");
            }
        });

        // Acción para modificar un evento
        btnModificar.addActionListener(e -> {
            int selectedRow = table.getSelectedRow();
            if (selectedRow != -1) {
                int id = (int) tableModel.getValueAt(selectedRow, 0);
                Evento evento = eventoController.buscarEvento(id);
                if (evento != null) {
                    String nuevoTitulo = JOptionPane.showInputDialog("Nuevo título:", evento.getTitulo());
                    String nuevaDescripcion = JOptionPane.showInputDialog("Nueva descripción:", evento.getDescripcion());
                    String nuevaFechaStr = JOptionPane.showInputDialog("Nueva fecha (yyyy-MM-dd):",
                            new SimpleDateFormat("yyyy-MM-dd").format(evento.getFecha()));
                    String nuevaRutaImagen = JOptionPane.showInputDialog("Ingrese la nueva URL de la imagen:", evento.getRutaImagen());

                    if (nuevaRutaImagen != null && !nuevaRutaImagen.isEmpty() && !validarImagenDesdeURL(nuevaRutaImagen)) {
                        JOptionPane.showMessageDialog(this, "La URL de la imagen no es válida o no se puede cargar.");
                        return;
                    }

                    try {
                        Date nuevaFecha = new SimpleDateFormat("yyyy-MM-dd").parse(nuevaFechaStr);
                        eventoController.actualizarEvento(id, nuevoTitulo, nuevaDescripcion, nuevaFecha, nuevaRutaImagen);
                        actualizarTabla();
                    } catch (Exception ex) {
                        JOptionPane.showMessageDialog(null, "Formato de fecha inválido.");
                    }
                }
            } else {
                JOptionPane.showMessageDialog(this, "Seleccione un evento para modificar.");
            }
        });

        // Acción para eliminar un evento
        btnEliminar.addActionListener(e -> {
            int selectedRow = table.getSelectedRow();
            if (selectedRow != -1) {
                int id = (int) tableModel.getValueAt(selectedRow, 0);
                int confirm = JOptionPane.showConfirmDialog(this, "¿Está seguro de eliminar este evento?", "Confirmar Eliminación", JOptionPane.YES_NO_OPTION);
                if (confirm == JOptionPane.YES_OPTION) {
                    eventoController.eliminarEvento(id);
                    actualizarTabla();
                }
            } else {
                JOptionPane.showMessageDialog(this, "Seleccione un evento para eliminar.");
            }
        });

        // Acción para ver la imagen del evento
        btnVerImagen.addActionListener(e -> {
            int selectedRow = table.getSelectedRow();
            if (selectedRow != -1) {
                int id = (int) tableModel.getValueAt(selectedRow, 0);
                Evento evento = eventoController.buscarEvento(id);
                if (evento != null && evento.getRutaImagen() != null) {
                    try {
                        ImageIcon icon = new ImageIcon(new ImageIcon(new URL(evento.getRutaImagen()))
                                .getImage().getScaledInstance(400, 300, Image.SCALE_SMOOTH));
                        JLabel labelImagen = new JLabel(icon);
                        JOptionPane.showMessageDialog(this, labelImagen, "Imagen del Evento", JOptionPane.PLAIN_MESSAGE);
                    } catch (IOException ex) {
                        JOptionPane.showMessageDialog(this, "No se pudo cargar la imagen desde la URL.");
                    }
                } else {
                    JOptionPane.showMessageDialog(this, "Este evento no tiene una imagen asociada.");
                }
            } else {
                JOptionPane.showMessageDialog(this, "Seleccione un evento para ver la imagen.");
            }
        });
    }

    private void actualizarTabla() {
        tableModel.setRowCount(0);
        for (Evento evento : eventoController.listarEventos()) {
            tableModel.addRow(new Object[]{
                    evento.getId(),
                    evento.getTitulo(),
                    evento.getDescripcion(),
                    new SimpleDateFormat("yyyy-MM-dd").format(evento.getFecha()),
                    evento.getRutaImagen() != null ? "Sí" : "No"
            });
        }
    }

    private void configurarBoton(JButton boton) {
        boton.setBackground(Color.decode("#1A1A1A"));
        boton.setForeground(Color.decode("#FFFFFF"));
        boton.setFocusPainted(false);
        boton.setBorderPainted(false);
    }

    private boolean validarImagenDesdeURL(String urlStr) {
        try {
            URL url = new URL(urlStr);
            BufferedImage image = ImageIO.read(url);
            return image != null;
        } catch (Exception e) {
            return false;
        }
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
            java.util.logging.Logger.getLogger(Eventos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Eventos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Eventos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Eventos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Eventos().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
