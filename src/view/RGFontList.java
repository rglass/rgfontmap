package view;

import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.JTable;
import javax.swing.table.TableModel;
import java.awt.Container;
import java.awt.BorderLayout;
import javax.swing.JPanel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.Vector;
import model.RGFontTableModel;

public class RGFontList extends JFrame {
    JTextField searchField;
    JTable fonts;

    RGFontList(String[] fontNames) {
        TableModel model = new RGFontTableModel(fontNames);
        JPanel searchBox = createSearchBox(searchField);
        searchField = new JTextField("Tags");
        fonts = new JTable(model);
        Container cp = getContentPane();

        cp.add(searchField, BorderLayout.NORTH);
        cp.add(fonts, BorderLayout.CENTER);
        pack();
        setVisible(true);
    }

    JPanel createSearchBox(JTextField searchField) {
        JPanel panel = new JPanel();
        panel.add(searchField);
        panel.add(createSearchButton());
        return panel;
    }

    JButton createSearchButton() {
        JButton button = new JButton("Search");
        button.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    searchTags(searchField.getText());
                }
            });
        return button;
    }

    void searchTags(String searchText) {
        RGFontTableModel tmp = new RGFontTableModel();

        for (int i = 0; i < fonts.getRowCount(); i++) ;
            //if (fonts.getName().equals(searchText))
                //tmp.add(fonts.get(i));
        fonts.setModel(tmp);
    }

    public static void main(String[] args) {
        String[] fs = {"Helvetica", "Sans", "Serif", "Tahoma"};
        new RGFontList(fs);
    }
}