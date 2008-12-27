package view;

import javax.swing.JFrame;
import javax.swing.JTextPane;
import java.awt.BorderLayout;
import javax.swing.JPanel;
import java.awt.FlowLayout;
import java.awt.Dimension;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.print.PrinterJob;
import java.awt.print.Paper;
import java.awt.print.PageFormat;
import java.awt.print.PrinterException;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import java.awt.Font;
import javax.swing.text.StyledDocument;
import javax.swing.text.BadLocationException;
import javax.swing.text.Style;
import javax.swing.text.StyleContext;
import javax.swing.text.StyleConstants;
import java.awt.Color;
import java.awt.Toolkit;
import java.awt.GraphicsEnvironment;
import printing.PrintableJTextPane;


public class RGFontMapFrame extends JFrame {
    public RGFontMapFrame(int defaultFontSize,
                   String defaultSampleText) {
        new RGFontMapFrame(GraphicsEnvironment
                       .getLocalGraphicsEnvironment()
                       .getAvailableFontFamilyNames(),
                       defaultFontSize,
                       defaultSampleText);
    }
    
    public RGFontMapFrame(String[] fonts,
                   int defaultFontSize,
                   String defaultSampleText) {
        super("Font-Map");
        JTextPane textPane = createTextPane(defaultSampleText,
                                            fonts,
                                            defaultFontSize);
        getContentPane().add(BorderLayout.CENTER, createScrollPane(textPane));
        JPanel buttons = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        buttons.add(createButton(textPane), BorderLayout.EAST);
        getContentPane().add(BorderLayout.SOUTH, buttons);
        setPreferredSize(new Dimension(640, 480));
        pack();
        Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
        Dimension e = getSize();

        setLocation((d.width - e.width) / 2,
                    ((d.height - e.height) / 2) + 20);
        setVisible(true);
    }
        
    private ActionListener createPrintAction(final JTextPane textPane) {
        return new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                final int INCH = 72;
                final double A4_WIDTH = 8.27 * INCH;
                final double A4_HEIGHT = 5 * INCH;
                PrinterJob printJob = PrinterJob.getPrinterJob();
                Paper paper = new Paper();
                int margin = INCH/2;
                double pageWidth = A4_WIDTH - 2 * margin;
                double pageHeigth = A4_HEIGHT - 2 * margin;
                paper.setImageableArea(margin,
                                       margin,
                                       pageWidth,
                                       pageHeigth) ;
                PageFormat pformat = printJob.defaultPage();
                pformat.setPaper(paper);
                pformat.setOrientation(PageFormat.PORTRAIT);
                printJob.pageDialog(pformat);
                printJob.setPrintable(new PrintableJTextPane(textPane));
                if (printJob.printDialog()) {
                    try {
                        printJob.print();
                    } catch (PrinterException pe) {
                        pe.printStackTrace();
                    }
                }
            }
        };
    }
        
    private JButton createButton(JTextPane textPane) {
        JButton b = new JButton("Print \u2026");
        b.addActionListener(createPrintAction(textPane));
        return b;
    }
        
    private String createPrefix(String s) {
        StringBuffer b = new StringBuffer();
        b.append(s);
        b.append(": ");
        return b.toString();
    }
        
    private JScrollPane createScrollPane(JTextPane t) {
        JScrollPane s = new JScrollPane(t);
        s.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        return s;
    }
        
    private String prepareSampleText(String sampleText,
                                     String s,
                                     int fontSize) {
        StringBuffer b = new StringBuffer();
        for (int i = 0; i < sampleText.length(); i++) {
            char c = sampleText.charAt(i);
            b.append(new Font(s, 
                              Font.PLAIN,
                              fontSize).canDisplay(c)
                     ? c 
                     : ' ');
        }
        b.append('\n');
        return b.toString();
    }
        
    private JTextPane createTextPane(String sampleText,
                                     String[] fonts,
                                     int fontSize) {
        JTextPane textPane = new JTextPane();
        StyledDocument document = textPane.getStyledDocument();
        textPane.setEditable(false);
        stylizeDocument(document, fonts, fontSize);
        try {
            for (int i = 0; i < fonts.length; i++) {
                String s = fonts[i];
                document.insertString(document.getLength(),
                                      createPrefix(s),
                                      document.getStyle("fontstyle"));
                document.insertString(document.getLength(),
                                      prepareSampleText(sampleText,
                                                        s,
                                                        fontSize),
                                      document.getStyle(s));
            }
        } catch (BadLocationException e) {
            System.err.println("Couldn't insert initial text into text pane.");
        }
        return textPane;
    }
        
    private void stylizeDocument(StyledDocument document,
                                 String[] fonts,
                                 int fontSize) {
        Style defaultStyle = StyleContext
            .getDefaultStyleContext()
            .getStyle(StyleContext.DEFAULT_STYLE);
        for (int i = 0; i < fonts.length; i++) {
            String s = fonts[i];
            Style style = document.addStyle(s, defaultStyle);
            StyleConstants.setFontFamily(style, s);
            StyleConstants.setFontSize(style, fontSize);
        }
        Style style = document.addStyle("fontstyle", defaultStyle);
        StyleConstants.setFontFamily(style, "Helvetica");
        StyleConstants.setFontSize(style, 9);
        StyleConstants.setForeground(style, Color.gray);
    }
}
