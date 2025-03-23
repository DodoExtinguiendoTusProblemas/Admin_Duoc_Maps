package Vistas;

import Controlador.PFrecuente_Controlador;
import Modelos.TablaNoEditable;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Preguntas_FrecuentesView extends javax.swing.JFrame {

    private PFrecuente_Controlador controlador;
    private JTable table;
    private DefaultTableModel tableModel;

    public Preguntas_FrecuentesView() {
        controlador = new PFrecuente_Controlador();
        setTitle("Gestión de Preguntas Frecuentes");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        // Configuración de la tabla
        tableModel = new TablaNoEditable(new Object[]{"ID", "Pregunta", "Respuesta"}, 0);
        table = new JTable(tableModel);
        table.setBackground(Color.BLACK); // Fondo negro
        table.setForeground(Color.WHITE); // Texto blanco
        table.setGridColor(Color.GRAY); // Color de las líneas de separación
        table.setSelectionBackground(Color.DARK_GRAY); // Fondo al seleccionar
        table.setSelectionForeground(Color.WHITE); // Texto al seleccionar

        JScrollPane scrollPane = new JScrollPane(table);
        add(scrollPane, BorderLayout.CENTER);

        // Panel para los botones
        JPanel panelBotones = new JPanel();
        panelBotones.setBackground(Color.decode("#F1B634")); // Fondo amarillo

        // Botones con estilo
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

        // Acción para agregar
        btnAgregar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String pregunta = JOptionPane.showInputDialog("Ingrese la pregunta:");
                String respuesta = JOptionPane.showInputDialog("Ingrese la respuesta:");
                if (pregunta != null && respuesta != null) {
                    controlador.agregarPreguntaFrecuente(pregunta, respuesta);
                    actualizarTabla();
                }
            }
        });

        // Acción para modificar
        btnModificar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRow = table.getSelectedRow();
                if (selectedRow != -1) {
                    int id = (int) tableModel.getValueAt(selectedRow, 0);
                    Modelos.Preguntas_Frecuentes preguntaFrecuente = controlador.buscarPreguntaFrecuente(id);
                    if (preguntaFrecuente != null) {
                        String nuevaPregunta = JOptionPane.showInputDialog("Nueva pregunta:", preguntaFrecuente.getPregunta());
                        String nuevaRespuesta = JOptionPane.showInputDialog("Nueva respuesta:", preguntaFrecuente.getRespuesta());
                        if (nuevaPregunta != null && nuevaRespuesta != null) {
                            controlador.actualizarPreguntaFrecuente(id, nuevaPregunta, nuevaRespuesta);
                            actualizarTabla();
                        }
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Seleccione una pregunta para modificar.");
                }
            }
        });

        // Acción para eliminar una pregunta frecuente
        btnEliminar.addActionListener(e -> {
            int selectedRow = table.getSelectedRow();
            if (selectedRow != -1) {
                int id = (int) tableModel.getValueAt(selectedRow, 0); // Obtener el ID de la pregunta frecuente
                int confirm = JOptionPane.showConfirmDialog(
                    this, 
                    "¿Está seguro de eliminar esta pregunta frecuente?", 
                    "Confirmar Eliminación", 
                    JOptionPane.YES_NO_OPTION
                );
                if (confirm == JOptionPane.YES_OPTION) {
                    controlador.eliminarPreguntaFrecuente(id); // Usar la instancia del controlador para eliminar
                    actualizarTabla(); // Actualizar la tabla después de la eliminación
                }
            } else {
                JOptionPane.showMessageDialog(this, "Seleccione una pregunta frecuente para eliminar.");
            }
        });
        actualizarTabla();
    }

    private void actualizarTabla() {
        tableModel.setRowCount(0);
        for (Modelos.Preguntas_Frecuentes preguntaFrecuente : controlador.listarPreguntasFrecuentes()) {
            tableModel.addRow(new Object[]{
                    preguntaFrecuente.getId(),
                    preguntaFrecuente.getPregunta(),
                    preguntaFrecuente.getRespuesta()
            });
        }
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
            java.util.logging.Logger.getLogger(Preguntas_FrecuentesView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Preguntas_FrecuentesView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Preguntas_FrecuentesView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Preguntas_FrecuentesView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        java.awt.EventQueue.invokeLater(() -> new Preguntas_FrecuentesView().setVisible(true));
   
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
