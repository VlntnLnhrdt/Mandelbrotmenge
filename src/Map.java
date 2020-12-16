import javafx.scene.Group;
import javafx.scene.paint.Color;

import java.util.Date;

public class Map {

    /**
     * Erstellung der Oberfläche, bzw. Zuweisung der Pixel mit entsprechender Farbe
     * <p>
     * Genereller Ablauf:
     * 01. Erzeugen einer noch leeren "Map" der Oberfläche
     * 02. Berechnung des Maßstabs zwischen Fenstergröße und den Achsen
     * 03. Erzeugen der komplexen Zahl "c", welche bei jeder Rechnung addiert wird (konstant, jedoch abhängig vom Startpunkt)
     * 04. Erzeugen der komplexen Zahl "z", welche quadriert, dann mit "c" addiert und als nächstes "z" hergenommen wird
     * 05. Boolean "paint" dient nur dazu, den Code schneller zu machen, kann ignoriert werden, wird in anderem Dokument genuer erklärt
     * 06. Durchlaufen der Map in Richtung des Realen- sowie Imaginären-Teils der komplexen Zahl
     * 07. Berechnen der Koordinaten der Pixel, da bei der Oberfläche der Ursprung links oben im Fenster ist (wird hierdurch indirekt in die Mitte verlegt)
     * 08. Berechnen der mathematischen Zahl, basierend auf den soeben berechneten Koordinaten
     * 09. Setzen der Zahl für "c" und zurücksetzen von "z" auf 0+0i
     * 10. Durchlaufen der Rechnung so oft, wie in "Properties" unter "ITERATIONS" angegeben
     * 11. Quadrieren und Addieren der komplexen Zahl(en)
     * 12. Prüfen ob der Reale-Teil von "z" schon außerhalb der "magischen" Grenzen liegt (wird in Seminararbeit erklärt)
     * 13. Variable "GRADIENT" in der Klasse Properties entscheidet, dass bei "true" ein Farberlauf entsteht, bzw. bei "false" die Mandelbrotmenge nur
     *     in schwarz-weiß dargestellt wird
     *
     * TODO Geschwindigkeit optimieren
     */

    Pixel[][] mapGrid; // Eine Map (multiDimensionales Array) für zukünftige Pixel wird erzeugt
    Group mapGroup;

    private static int CUSTOM_ITERATIONS;

    /* (01) */
    Map(int mapHeight, int mapWidth) {

        CUSTOM_ITERATIONS = (int)(Properties.ITERATIONS);
        mapGrid = new Pixel[mapWidth][mapHeight]; // Festlegen der Größe
        mapGroup = new Group();

    }

    Map(int mapHeight, int mapWidth, int growingIterations) {

        CUSTOM_ITERATIONS = growingIterations;
        mapGrid = new Pixel[mapWidth][mapHeight]; // Festlegen der Größe
        mapGroup = new Group();

    }

    void generateMap() {
        // Infotext Anfang
        long maxCals = Properties.WINDOW_WIDTH*Properties.WINDOW_WIDTH*CUSTOM_ITERATIONS;
        System.out.println("Anzahl max. Berechnungen: "+maxCals);

        long startTime = new Date().getTime();
        int realCalcs = 0;
        // Infotext Ende

        // Hinzufügen der einzelnen Pixel zur Oberfläche

        /* (02) */
        double scale = Properties.REAL_LENGTH / Properties.WINDOW_WIDTH;

        double realCords;
        double realMath;
        double imagCords;
        double imagMath;

        /* (03) */
        final ComplexNumber c = new ComplexNumber();
        /* (04) */
        ComplexNumber z = new ComplexNumber();

        /* (05) */
        boolean paint;

        int iterCount = 0;

        /* (06) */
        for (int real = 0; real < mapGrid.length; real++) {
            /* (06) */
            for (int imag = 0; imag < mapGrid[real].length; imag++) {
                paint = true;

                /* (07) */
                realCords = real - Properties.WINDOW_CENTER_HOR;
                /* (08) */
                realMath = realCords * scale;

                /* (07) */
                imagCords = imag - Properties.WINDOW_CENTER_VER;
                /* (08) */
                imagMath = imagCords * scale;

                /* (09) */
                c.setRealImag(realMath, imagMath);
                z.setRealImag(0, 0);


                Color clr = Color.rgb(0,0,0);

                /* (10) */
                for (int iter = 0; iter < Properties.ITERATIONS; iter++) {
                    realCalcs++;
                    /* (11) */
                    z.square();
                    z.adding(c);

                    iterCount = iter;

                    /* (12) */
                    if (z.real < -2 || z.real > 0.5) {
                        clr = Color.rgb(255,255,255);
                        break;
                    }
                }

                /* (13) */
                if (Properties.GRADIENT) {
                    if (iterCount < 255) {
                        mapGrid[real][imag] = new Pixel(real, imag, Color.rgb(0, iterCount / 2, iterCount));
                    } else {
                        mapGrid[real][imag] = new Pixel(real, imag, Color.rgb(0, 0, 0));
                    }
                } else {
                    mapGrid[real][imag] = new Pixel(real, imag, clr);

                }
                mapGroup.getChildren().add(mapGrid[real][imag].getRectangle());

            }
        }

        /*  */
        // Infotext Anfang
        long endTime = new Date().getTime();
        System.out.println("Gesamtdauer: " + (endTime - startTime) + "ms");
            System.out.println("Durchgeführte Berechnungen: " + realCalcs);
            System.out.println(realCalcs / (endTime - startTime) + " Berechnungen pro ms");
            long savedCalcs = (long) ((1 - (double) realCalcs / maxCals) * 100);
            System.out.println("Gesparte Berechnungen: " + (maxCals - realCalcs) + " (" + savedCalcs + "%)");
        // Infotext Ende
    }
}