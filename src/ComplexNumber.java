import javafx.scene.Node;
import javafx.scene.paint.Color;

public class ComplexNumber {

    /**
     *
     * Stellt eine komplexe Zahl mit den benötigten Rechnungen dar
     *
     */

    Pixel pixel;
    double real, realTemp;
    double imag;

    ComplexNumber(double real, double imag, int x, int y) {
        pixel = new Pixel(x,y);
        this.real = real;
        this.imag = imag;
    }

    public void upadateContent(double real, double imag, Color color) {
        this.real = real;
        this.imag = imag;
        pixel.setColor(color);
    }

    public void setColor(Color color) {
        pixel.setColor(color);
    }

    public double getReal() {
        return real;
    }

    public Node getRectangle() {
        return pixel.getRectangle();
    }

    /**
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

    // Quadriert nach obriger Rechnung die Zahl
    public void square() {
        realTemp = real;
        real = (real*real)-(imag*imag);
        imag = 2*realTemp*imag;
    }

    // Addiert eine weitere komplexe Zahl zu dieser Zahl
    public void adding(double real, double imag) {
        this.real += real;
        this.imag += imag;
    }
}