package view;

//
//  RGFontMapPreferences.java
//  RGFontMap
//
//  Created by Roman Glass on 09.09.08.
//  Copyright 2008 Roman Glass. All rights reserved.
//

import javax.swing.JFrame;
import java.awt.Dimension;
import javax.swing.JPanel;
import java.awt.FlowLayout;
import javax.swing.JLabel;
import java.awt.BorderLayout;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JSlider;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Container;
import javax.swing.JList;
import javax.swing.JScrollPane;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.ListSelectionModel;
import javax.swing.SwingUtilities;

public class RGFontMapPreferences extends JFrame {
    int defaultFontSize;
    String defaultSampleText;
    JTextField textfield;
    JList list;
    
    public RGFontMapPreferences(int adefaultFontSize, String adefaultSampleText) {
        super("Preferences");
        defaultFontSize = adefaultFontSize;
        defaultSampleText = adefaultSampleText;
        list = createFontSizeList();
        textfield = createSampleTextField();

        Container cp = getContentPane();
        cp.add(createSampleTextBoxBox(textfield), BorderLayout.EAST);
        cp.add(createFontSizeBox(list), BorderLayout.WEST);
        cp.add(createFontMapBox(), BorderLayout.SOUTH);
        pack();
        setVisible(true);
        
        list.ensureIndexIsVisible(list.getSelectedIndex());
    }

    JPanel createFontSizeBox(JList list) {
        JPanel panel = new JPanel(new BorderLayout());

        panel.add(createScrollPane(list), BorderLayout.NORTH);
        panel.add(createFontSizeLabel(), BorderLayout.SOUTH);
        
        return panel;
    }
    
    JPanel createFontMapBox() {
        JPanel panel = new JPanel(new BorderLayout());

        panel.add(createNewFontMapButton(), BorderLayout.EAST);

        return panel;
    }
    
    JPanel createSampleTextBoxBox(JTextField textfield) {
        JPanel panel = new JPanel(new BorderLayout());

        panel.add(createSampleTextBox(textfield), BorderLayout.NORTH);
        
        return panel;
    }

    JPanel createSampleTextBox(JTextField textfield) {
        JPanel panel = new JPanel(new BorderLayout());

        panel.add(textfield, BorderLayout.NORTH);
        panel.add(createSampleTextLabel(), BorderLayout.SOUTH);

        return panel;
    }

    JList createFontSizeList() {
        Integer[] model = new Integer[73];
        JList list = new JList(model);

        for (int i = 1; i < model.length; i++)
            model[i] = i;
        list.setSelectedIndex(defaultFontSize);

        return list;
    }

    JScrollPane createScrollPane(JList list) {
        JScrollPane s = new JScrollPane(list);
        
        s.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        s.setPreferredSize(new Dimension(50, 200));

        return s;
    }

    JLabel createFontSizeLabel() {
        JLabel label = new JLabel("Fontsize");

        return label;
    }

    JButton createNewFontMapButton() {
        JButton button = new JButton("New Fontmap");

        button.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent actionEvent) {
                    try {
                        javax.swing.SwingUtilities.invokeLater(new Runnable() {
                                public void run() {
                                    new RGFontMapFrame(Integer
                                                       .parseInt(list.
                                                                 getSelectedValue()
                                                                 .toString()),
                                                       textfield.getText());
                                }
                            });
                    } catch (Exception e) { }
                }
            });

        return button;
    }

    JTextField createSampleTextField() {
        JTextField textField = new JTextField(defaultSampleText);

        return textField;
    }

    JLabel createSampleTextLabel() {
        JLabel label = new JLabel("Sampletext");

        return label;
    }
}
