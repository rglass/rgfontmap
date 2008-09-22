import javax.swing.JFrame;

public class RGFontList extends JFrame {
    String[] fonts;
    
    RGFontList(String[] afonts) {
        super("Fontlist");
        fonts = afonts;
    }
    
    public static void main(String[] args) {
        String[] fonts = {"Helvetica", "Zapfino"};
        new RGFontList(fonts);
    }
}