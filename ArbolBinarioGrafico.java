
import java.awt.*;
import javax.swing.*;

public class ArbolBinarioGrafico extends javax.swing.JFrame {

    private ArbolBinario arbol = new ArbolBinario();
    private int DIAMETRO = 40;
    private int RADIO = DIAMETRO / 2;
    private int ANCHO = 60;

    public ArbolBinarioGrafico(ArbolBinario arbol) {
        super("Arbol binario grafico");
        this.arbol = arbol;
        this.setSize(1000, 1000);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    public void DibujarTextoCentrado(Graphics g, String text, Rectangle rect, Font fuente) {
        FontMetrics letra = g.getFontMetrics(fuente);
        int x = rect.x + (rect.width - letra.stringWidth(text)) / 2;
        int y = rect.y + ((rect.height - letra.getHeight()) / 2) + letra.getAscent();
        g.setFont(fuente);
        g.drawString(text, x, y);
    }

    private void DibujarArbol(Graphics g, NodoArbol nodo, int x, int y) {
        if (nodo != null) {
            int EXTRA = nodo.getNodosCompletos(nodo) * (ANCHO / 2);
            if (nodo.getIzq() != null) {g.drawLine(x + RADIO, y + RADIO + 20, x - ANCHO - EXTRA + RADIO, y + ANCHO + RADIO - 20);}
            if (nodo.getDer() != null) {g.drawLine(x + RADIO, y + RADIO + 20, x + ANCHO + EXTRA + RADIO, y + ANCHO + RADIO - 20);}
            if(nodo.getDer() != null || nodo.getIzq() != null)g.setColor(Color.CYAN);
            if(nodo.getDer() == null && nodo.getIzq() == null)g.setColor(Color.GREEN);
            if(nodo == arbol.getRaiz())g.setColor(Color.YELLOW);
            g.fillOval(x, y, DIAMETRO, DIAMETRO);
            g.setColor(Color.black);
            g.drawOval(x, y, DIAMETRO, DIAMETRO);
            Rectangle rect = new Rectangle(x, y, DIAMETRO, DIAMETRO);
            Font letra = new Font("Arial", Font.BOLD, 17);
            String t = String.valueOf(nodo.getDato());
            DibujarTextoCentrado(g, t, rect, letra);
            DibujarArbol(g, nodo.getIzq(), x - ANCHO - EXTRA, y + ANCHO);
            DibujarArbol(g, nodo.getDer(), x + ANCHO + EXTRA, y + ANCHO);
        }
    }

    @Override
    public void paint(Graphics g) {
        if (!arbol.ArbolVacio()) {
            Graphics2D Margenes = (Graphics2D) g;
            Margenes.setStroke(new BasicStroke(2));
            DibujarArbol(Margenes, arbol.getRaiz(), (this.getWidth() / 2) - RADIO, 50);
        } else {
            this.setVisible(false);
            JOptionPane.showMessageDialog(this, "ARBOL VACIO!");
        }
    }
}
