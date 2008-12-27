package model;

import model.Font;
import java.util.Vector;
import javax.swing.table.AbstractTableModel;

public class RGFontTableModel extends AbstractTableModel {
        float serialVersionUID = 0F;
    Vector<Font> fonts;

    public RGFontTableModel(String[] fs) {
        fonts = new Vector<Font>();
        for (String s : fs) {
                        fonts.add(new Font(s, "TestCase"));
                }
    }

    public RGFontTableModel() {
        fonts = new Vector<Font>();
    }
    
    public Class getColumnClass(int columnIndex) {
        return new String().getClass();
    }
    
    public int getColumnCount() {
        return 2;
    }

    public int getRowCount() {
        return fonts.size();
    }
    
    public Object getValueAt(int row, int column) {
        return ((column == 0)
                ? fonts.get(row).name
                : fonts.get(row).tag);
    }

    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
        fonts.set(rowIndex, new Font(fonts.get(rowIndex).name,
                                     aValue.toString()));
    }

    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return (columnIndex == 1);
    }
}