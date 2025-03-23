package Modelos;

import javax.swing.table.DefaultTableModel;


public class TablaNoEditable extends DefaultTableModel{
    public TablaNoEditable(Object[] columnNames, int rowCount) {
        super(columnNames, rowCount);
    }

    @Override
    public boolean isCellEditable(int row, int column) {
        return false; // Deshabilita la edición en todas las celdas
    }
}

