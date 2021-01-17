import java.util.Date;

/**
 *
 * Zusätzliche Funktionen, welche für den allgemeinen Betrieb des Software nicht nötig wären
 * Aktuelle Funktionen:
 * - dient als eine Art Zeit-Logger
 * - Rundung von Zahlen auf 4-Nachkommestellen
 *
 * */

public class Util {

    // Speichert die Zeit zu Beginn des Timers
    private static long startingTime;

    // Starten des Timers und Speicherung der aktuellen Zeit
    public static void startTimer() {
        startingTime = new Date().getTime();
        System.out.print("Ladezeit der Darstellung: ");
    }

    // Stoppen des Timers und Ausgabe der benötigten Zeit
    public static void endTimer() {
        System.out.println((new Date().getTime()-startingTime) + "ms");
        System.out.println("---------------------------------------------------");
    }

    // Rundung von Zahlen auf 4-Nachkommestellen
    public static String getRounding(double real, double imag) {
        return Math.round(real * 10000.0) / 10000.0 + " " + Math.round(imag * 10000.0) / 10000.0+"i";
    }

}
