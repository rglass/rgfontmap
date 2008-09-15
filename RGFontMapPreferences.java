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

class RGFontMapPreferences extends JFrame implements ActionListener {
    int defaultFontSize;
    String defaultSampleText;
    
    RGFontMapPreferences(int fontSize, String sampleText) {
        super("Preferences");
        setPreferredSize(new Dimension(444, 144));
        JPanel box = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JPanel fontSizeBox = new JPanel();
        fontSizeBox.add(new JLabel("Fontsize: "), BorderLayout.EAST);
        fontSizeBox.add(createSlider(fontSize), BorderLayout.WEST);
        fontSizeBox.add(new JLabel(new Integer(fontSize).toString()));
        JPanel sampleTextBox = new JPanel();
        sampleTextBox.add(new JLabel("Sampletext: "));
        sampleTextBox.add(createSampleTextField(sampleText));
        box.add(fontSizeBox, BorderLayout.NORTH);
        box.add(sampleTextBox, BorderLayout.CENTER);
        box.add(createNewFontMapButton(), BorderLayout.SOUTH);
        getContentPane().add(box);
        pack();
        setLocation(5, 10);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        
    }
    
    JButton createNewFontMapButton() {
        JButton b = new JButton("New Font-Map");
        b.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    new RGFontMapFrame(defaultFontSize,
                                       defaultSampleText);
                }
            });
        return b;
    }

    JTextField createSampleTextField(String sampleText) {
        JTextField field = new JTextField(sampleText);
        field.setPreferredSize(new Dimension(200, 20));
        return field;
    }
        
    JSlider createSlider(int fontSize) {
        JSlider slider = new JSlider(0, 72, fontSize);
        slider.setLabelTable(slider.createStandardLabels(4));
        slider.setPaintLabels(true);
        return slider;
    }
}
