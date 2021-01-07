import java.util.Date;

public class Util {

    // Zusätzliche Funktionen

    private static long startingTime;

    public static void startTimer() {
        startingTime = new Date().getTime();
        System.out.print("Ladezeit der Darstellung: ");
    }

    public static void endTimer() {
        System.out.println((new Date().getTime()-startingTime) + "ms");
        System.out.println("---------------------------------------------------");
    }

    public static String getRounding(double real, double imag) {
        return Math.round(real * 10000.0) / 10000.0 + " " + Math.round(imag * 10000.0) / 10000.0+"i";
    }

}
