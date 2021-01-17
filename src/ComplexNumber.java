import javafx.scene.Node;
import javafx.scene.paint.Color;

/**
 *
 * Dient zur Darstellung einer kompelxen Zahl dar, beinhaltet den Pixel, welcher für diese dargestellt wird
 *
 * */

public class ComplexNumber {

    // Der eigentliche Pixel welcher sichtbar dargestellt wird
    private Pixel pixel;

    // Real- und Imaginärteil der komplexen Zahl
    private double real, realTemp;
    private double imag;

    // Erzeugung der komplexen Zahl sowie des zugehörigen Pixels
    ComplexNumber(double real, double imag, int x, int y) {
        pixel = new Pixel(x,y);
        this.real = real;
        this.imag = imag;
    }

    // Änderung der Zahlenwerte sowie der Farbe des Pixels
    public void upadateContent(double real, double imag, Color color) {
        this.real = real;
        this.imag = imag;
        pixel.setColor(color);
    }

    // Änderung der Farbe des Pixels
    public void setColor(Color color) {
        pixel.setColor(color);
    }

    // Getter-Funktion des Realteiles der komplexen Zahl
    public double getReal() {
        return real;
    }

    // Getter-Funktion des Imaginärteiles der komplexen Zahl
    public double getImag() {
        return imag;
    }

    // Getter-Funktion des Pixels
    public Node getRectangle() {
        return pixel.getRectangle();
    }

    /**
     * Kurze Umrechnung zu ggf. besseren Verständlichkeit (dient zur als Stütze und wird nicht im Code ausgeführt)
     *
     * z = z² + c
     * (1+1i)*(1+1i) = 1² + 1i + 1i + 1i²
     * (a+bi)*(c+di)  = a*c + a*di + bi*c + bi*di    mit i²=-1
     *            -> = a*c -b*d +a*di + bi*c
     *             Daraus folgt:
     *             Für Real-Wert: a*c - b*d bzw. a+a -b*b -> a²-b²
     *             Für Imag-Wert: a*d+b*c   bzw. a*b+b*a  -> 2*a*b
     *
     * */

    // Quadriert nach obriger Rechnung die komplexe Zahl
    public void square() {
        realTemp = real;
        real = (real*real)-(imag*imag);
        imag = 2*realTemp*imag;
    }

    // Addiert eine weitere komplexe Zahl zu dieser komplexen Zahl
    public void adding(double real, double imag) {
        this.real += real;
        this.imag += imag;
    }
}