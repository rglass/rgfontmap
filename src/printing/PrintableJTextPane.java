package printing;

import view.PrintView;
import java.awt.print.Printable;
import javax.swing.JTextPane;
import java.awt.Graphics;
import java.awt.print.PageFormat;
import java.awt.print.PrinterException;
import javax.swing.plaf.basic.BasicTextUI;
import javax.swing.text.Element;
import javax.swing.text.View;


public class PrintableJTextPane implements Printable {
    JTextPane textPane;
    PrintView printView;
        
    public PrintableJTextPane(JTextPane atextPane) {
        textPane = atextPane;
    }
        
    public int print(Graphics graphics,
                     PageFormat pageFormat,
                     int pageIndex) throws PrinterException {
        graphics.translate((int)pageFormat.getImageableX(),
                           (int)pageFormat.getImageableY());
        int wPage = (int)pageFormat.getImageableWidth();
        int hPage = (int)pageFormat.getImageableHeight();
        graphics.setClip(0, 0, wPage, hPage);
                                 
        if (printView == null) {
            BasicTextUI btui = (BasicTextUI)textPane.getUI();
            View root = btui.getRootView(textPane);
            Element element = textPane.getDocument().getDefaultRootElement();
                                         
            printView = new PrintView(element, root, wPage, hPage);
        }
                                 
        boolean bContinue = printView.paintPage(graphics, hPage, pageIndex);
        System.gc();
                                 
        if (bContinue) {
            return PAGE_EXISTS;                
        }
        else {
            printView = null;
            return NO_SUCH_PAGE;
        }
    }
}
