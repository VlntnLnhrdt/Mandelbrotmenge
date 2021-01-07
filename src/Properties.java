public class Properties {

    /**
     *
     * Enthält Daten (zur besseren Übersicht und einfachereren Veränderung)
     *
     * */

    final public static String WINDOW_TITLE = "Linhardt, Valentin 2020 - Seminararbeit: Die Mandelbrotmenge als mathematisches und künstlerisches Objekt";

    final public static int WINDOW_HEIGHT = 1000; // !! Nicht verändern !!
    final public static int WINDOW_WIDTH = WINDOW_HEIGHT;
    public static int WINDOW_CENTER_HOR = (WINDOW_WIDTH/5)*4; // Mehr Punkte in negativer Richtigung der reelen Zahlen
    public static int WINDOW_CENTER_VER = WINDOW_HEIGHT/2;    // Genau mittig (imaginäre Zahlen ausgeglichen)


    public static long ITERATIONS = 1000; // Anzahl der zu durchlaufenen Iterationen (je mehr desto "schärfer")
    public static double REAL_LENGTH = 2.5; // Definiert die insgesamte Länge der Koordiantenachse(n) (-2 bis 0,5 ist 2,5)
    public static double SCALE = Properties.REAL_LENGTH / Properties.WINDOW_WIDTH;

    public static int ZOOM_FACTOR = 2;

    public static boolean GROWING = false;

}
