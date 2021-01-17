import javafx.scene.Group;
import javafx.scene.paint.Color;

/**
 *
 * Führt die Berechnung der Formel durch und speichert alle anzuzeigenden Pixel inklusive deren Attributen sowie alle komplexe Zahlen
 *
 * */

public class Map {

    // Ein Multidimensionales Array, vorzustellen wir eine Art Gitter für die einzelnen Pixel der Oberfläche
    ComplexNumber[][] mapGrid;
    // Speichert das Grip, muss zur korrekten Anzeige verwendet werden (Java-Bedingt)
    Group mapGroup;

    // Vergrößerungs bzw. Wachstumsstatus
    boolean zoomed = false;
    boolean growing = false;

    // Fenstergröße
    int mapHeight, mapWidth;

    // Konstruktur der Map, speichert Größe dieser ab
    Map(int mapHeight, int mapWidth) {
        this.mapHeight = mapHeight;
        this.mapWidth = mapWidth;

        // Initialisiert das "Gitter"
        mapGrid = new ComplexNumber[mapWidth][mapHeight];
        mapGroup = new Group();
    }

    // Füllt "mapGrid" mit Pixelwerten, bzw. ändert diese ab
    void generateMap() {

        // Starten des Timer
        Util.startTimer();

        // Erzeugung einer komplexen Zahl
        ComplexNumber z;
        // Speichert Real- und Imaginärteil der komplexen Zahl ab
        double realMath, imagMath;

        // Zählt die durchlaufenen Iterationen ab (für spätere Farbberechnung)
        int iterations = 0;

        // Zwei Schleifen durchlaufen jede Spalte (Realteil) und jede Zeile (Imaginärteil)
        for (int real = 0; real < mapGrid.length; real++) {
            for (int imag = 0; imag < mapGrid[real].length; imag++) {

                // Berechnung der mathematischen Zahl basierend auf Position des zu erzeugenden Pixels
                // Realteil
                realMath = (real - Properties.WINDOW_CENTER_HOR) * Properties.SCALE;
                // Imaginärteil
                imagMath = (Properties.WINDOW_CENTER_VER - imag) * Properties.SCALE;

                // Initialisierung der komplexe Zahl (Realteil, Imaginärteil, x- und y-Position)
                z = new ComplexNumber(realMath,imagMath,real,imag);

                // Durchlaufen der Berechnungen basierend auf den eingestellten Iterationsanzahl der Properties-Datei
                for (int i = 0; i<Properties.ITERATIONS;i++) {
                    // Quadrierung der komplexen Zahl
                    z.square();

                    // Addierung der komplexen Zahl nach X-Durchläufen mit Startzahl der Position (genaueres siehe Seminararbeit unter "Erläuterung von Code und Klassen)
                    z.adding(realMath, imagMath);

                    // Zählt Iterationen für spätere Farbberechnung mit
                    iterations = i;

                    // Dient zur Leistungsoptimierung
                    // Bricht Berechnungsprozess ab, wenn der Realteil der komplexen Zahl außerhalb des angegebenen Bereiches liegt (führt ins unendliche-> außerhalb der Mandelbrotmenge)
                    if (z.getReal()<-10 || z.getReal()>5) // Zahlen wurden basierend auf eigenen Erfahrungswerten und Testergebnissen gewählt
                        break;
                }

                // Prüft ob bereits Pixel vorhanden sind
                if (mapGrid[real][imag]==null) {
                    // Erzeugung der komplexen Zahl mit dazugehörigem Pixel
                    mapGrid[real][imag] = new ComplexNumber(realMath, imagMath, real, imag);

                    // Berechnung und Zuweisung der Farbe (genaueres siehe Seminararbeit unter "Erläuterung von Code und Klassen)
                    mapGrid[real][imag].setColor(Color.rgb(0, iterations < 256 ? iterations / 2 : 0, iterations < 256 ? iterations : 0));

                    // Hinzufügen zur Oberfläche
                    mapGroup.getChildren().add(mapGrid[real][imag].getRectangle());
                } else {
                    // Prüft ob die Mandelbrot-Menge gerade "wächst"
                    if (Properties.GROWING) {
                        // Wenn die maximale Iterationszahl erreicht ist, wird der Pixel schwarz gefärbt
                        if (iterations==Properties.ITERATIONS-1)
                            mapGrid[real][imag].upadateContent(realMath, imagMath, Color.rgb(0,0,0));
                        else // Ansonsten in weiß, da er außerhalb der Mandelbrot-Menge liegt
                            mapGrid[real][imag].upadateContent(realMath, imagMath, Color.rgb(255,255,255));
                    } else {
                        // Wächst sie nicht, so wird die Farbe berechnet und zugewiesen
                        mapGrid[real][imag].upadateContent(realMath, imagMath, Color.rgb(0, iterations < 256 ? iterations / 2 : 0, iterations < 256 ? iterations : 0));
                    }
                }

                // Beim ersten Mausklick wird das Koordinatensystem zentriert und dementsprechend die Koordiantenachsen hinzugefügt (Pixel werden unabhängig von vorherigen Berechnungen weiß gefärbt)
                if (zoomed && (real==Properties.WINDOW_CENTER_HOR || imag==Properties.WINDOW_CENTER_VER))
                    mapGrid[real][imag].setColor(Color.WHITE);

            }
        }

        // Stoppen des Timers
        Util.endTimer();
    }

    // Füllt "mapGrid" mit Pixelwerten, bzw. ändert diese ab (dient nur der Vergrößerungsfunktion)
    void generateMap(int xClick, int yClick) {

        // Die Mandelbrot-Menge wird zentriert und Koordinatenachsen hinzugefügt
        if (!zoomed) {
            System.out.println("Mittelung der Mandelbrotmenge mit Koordinatenachsen");
            // Änderung des Vergrößerungsstatus
            zoomed = true;

            // Versetzen der Mandelbrotmenge in die Mitte
            Properties.WINDOW_CENTER_HOR = Properties.WINDOW_WIDTH / 2;
            Properties.WINDOW_CENTER_VER = Properties.WINDOW_HEIGHT / 2;

            // Skalenanpasung (Verhältnis Pixel / Längeneinheit der Achsen)
            Properties.REAL_LENGTH = 4;
            Properties.SCALE = Properties.REAL_LENGTH / Properties.WINDOW_WIDTH;

            // Erzeugung einer neuen Oberfläche, obige Funktion wird aufgerufen
            generateMap();

        } else { // Hier findet die aktive Vergrößerung statt

            // Anzeigen der Position, wo vergrößert wird
            System.out.println("Zoom bei "+Util.getRounding(mapGrid[xClick][yClick].getReal(), mapGrid[xClick][yClick].getImag()));
            // Starten des Timers
            Util.startTimer();

            // Anpassung des Verhältnisses Pixel / Längeneinheit der Achsen
            Properties.SCALE /= Properties.ZOOM_FACTOR;

            // Neuzentrierung der Mandelbrot-Menge
            double realAddMove = Properties.WINDOW_CENTER_HOR*Properties.SCALE;
            double imagAddMove = Properties.WINDOW_CENTER_VER*Properties.SCALE;

            // Linke-Obere-Ecke wird ausgerechnet (genaueres siehe Seminararbeit unter "Erläuterung von Code und Klassen)
            double realStart = mapGrid[xClick][yClick].getReal() - realAddMove ;
            double imagStart = mapGrid[xClick][yClick].getImag() + imagAddMove;

            // Erzeugung einer komplexen Zahl
            ComplexNumber z;

            // Zählt die durchlaufenen Iterationen ab (für spätere Farbberechnung)
            int iterations = 0;

            // Zwei Schleifen durchlaufen jede Spalte (Realteil) und jede Zeile (Imaginärteil)
            for (int real = 0; real<mapGrid.length; real++) {
                for (int imag = 0; imag<mapGrid[real].length; imag++) {

                    // Initialisierung der komplexe Zahl (Realteil, Imaginärteil, x- und y-Position)
                    z = new ComplexNumber(realStart+(real*Properties.SCALE),imagStart-(imag*Properties.SCALE),real,imag);

                    // Durchlaufen der Berechnungen basierend auf den eingestellten Iterationsanzahl der Properties-Datei
                    for (int i=0; i<Properties.ITERATIONS;i++){
                        // Quadrierung der komplexen Zahl
                        z.square();

                        // Addierung der komplexen Zahl nach X-Durchläufen mit Startzahl der Position (genaueres siehe Seminararbeit unter "Erläuterung von Code und Klassen)
                        z.adding(realStart+(real*Properties.SCALE), imagStart-(imag*Properties.SCALE));

                        // Zählt Iterationen für spätere Farbberechnung mit
                        iterations = i;

                        // Dient zur Leistungsoptimierung
                        // Bricht Berechnungsprozess ab, wenn der Realteil der komplexen Zahl außerhalb des angegebenen Bereiches liegt (führt ins unendliche-> außerhalb der Mandelbrotmenge)
                        if (z.getReal()<-10 || z.getReal()>5) // Zahlen wurden basierend auf eigenen Erfahrungswerten und Testergebnissen gewählt
                            break;
                    }

                    // Aktualisierung der Zahlenwerte des bereits hinzugefügten Pixels sowie die Farbänderung davon
                    mapGrid[real][imag].upadateContent(realStart+(real*Properties.SCALE), imagStart-(imag*Properties.SCALE), Color.rgb(0, iterations < 256 ? iterations / 2 : 0, iterations < 256 ? iterations : 0));
                }
            }

            // Stoppen des Timers
            Util.endTimer();
        }
    }
}