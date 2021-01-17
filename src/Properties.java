/**
 *
 * Enthält Daten zur besseren Übersicht und einfachereren Veränderung
 * Kann wie eine Art Konfigurationsdatei für bestimmte Einstellungen angesehen werden
 *
 * */

public class Properties {

    // Bezeichnung des Fenstertitels
    final public static String WINDOW_TITLE = "Linhardt, Valentin 2021 - Seminararbeit: Die Mandelbrot-Menge als mathematisches und künstlerisches Objekt";

    // Einstellung der Fenstergröße
    final public static int WINDOW_HEIGHT = 1000;
    final public static int WINDOW_WIDTH = WINDOW_HEIGHT;

    // Dient zur Positionierung der Mandelbrot-Menge bei erster Anzeige
    public static int WINDOW_CENTER_HOR = (WINDOW_WIDTH/5)*4; // Mehr Punkte in negativer Richtigung der reelen Zahlen
    public static int WINDOW_CENTER_VER = WINDOW_HEIGHT/2;    // Genau mittig (imaginäre Zahlen ausgeglichen)

    // Einstellung der Genauigkeit / Schärfe, je mehr Iterationen desto genauer
    public static long ITERATIONS = 1000;

    // Verhältnis zwischen Pixel und Längeeinheit der Achsen
    public static double REAL_LENGTH = 2.5; // Definiert die insgesamte Länge der Koordiantenachse(n) (-2 bis 0,5 ist 2,5)
    public static double SCALE = Properties.REAL_LENGTH / Properties.WINDOW_WIDTH;

    // Zoomfaktor bei Vergrößerung
    public static int ZOOM_FACTOR = 5;

    // Aktuelles Status, ob die Mandelbrot-Menge gerade "wächst"
    public static boolean GROWING = false;

}
