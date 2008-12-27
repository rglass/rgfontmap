package view;

import javax.swing.text.BoxView;
import java.awt.Graphics;
import java.awt.Rectangle;
import javax.swing.text.Element;
import javax.swing.text.View;

public class PrintView extends BoxView {
    protected int firstOnPage = 0;
    protected int lastOnPage = 0;
    protected int pageIndex = 0;
        
    public PrintView(Element elem, View root, int w, int h) {
        super(elem, Y_AXIS);
        setParent(root);
        setSize(w, h);
        layout(w, h);
    }
        
    public boolean paintPage(Graphics g, int hPage, int pIndex) {
        if (pIndex > this.pageIndex) {
            firstOnPage = lastOnPage + 1;
            if (firstOnPage >= getViewCount()) {
                                return false;
                        }
            pageIndex = pIndex;
        }
        int yMin = getOffset(Y_AXIS, firstOnPage);
        int yMax = yMin + hPage;
        Rectangle rc = new Rectangle();
                
        for (int k = firstOnPage; k < getViewCount(); k++) {
            rc.x = getOffset(X_AXIS, k);
            rc.y = getOffset(Y_AXIS, k);
            rc.width = getSpan(X_AXIS, k);
            rc.height = getSpan(Y_AXIS, k);
            if (rc.y+rc.height > yMax) {
                                break;
                        }
            lastOnPage = k;
            rc.y -= yMin;
            paintChild(g, rc, k);
        }
        return true;
    }
}
