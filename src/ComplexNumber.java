public class ComplexNumber {

    /**
     *
     * Stellt eine komplexe Zahl mit den benötigten Rechnungen dar
     *
     */

    double real, realTemp;
    double imag;

    public void setRealImag(double real, double imag) {
        this.real = real;
        this.imag = imag;
    }


    /**
     *
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
    public void adding(ComplexNumber c) {
        real += c.real;
        imag += c.imag;
    }

    // Gibt die komplexe Zahl aus (meist nur für Testzwecke)
    public void print(){
        System.out.println("Real: "+real+"\nImag: "+imag+"i");
    }
}