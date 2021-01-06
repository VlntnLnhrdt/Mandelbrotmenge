import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import java.util.Arrays;

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
     * in schwarz-weiß dargestellt wird
     */

    Pixel[][] mapGrid; // Eine Map (multiDimensionales Array) für zukünftige Pixel wird erzeugt
    Group mapGroup;
    boolean zoomed = false; // Wenn gezoomt ist werden Tasten ignoriert (growingIteration)
    boolean firstZoom = true; // Beim ersten Klicken werden die Koordinatenachsen angezeigt
    int mapHeight, mapWidth;

    int realStart = 0;
    int imagStart = 0;

    /* (01) */
    Map(int mapHeight, int mapWidth) {
        this.mapHeight = mapHeight;
        this.mapWidth = mapWidth;

        mapGrid = new Pixel[mapWidth][mapHeight]; // Festlegen der Größe
        mapGroup = new Group();

    }

    void generateMap() {
        // Hinzufügen der einzelnen Pixel zur Oberfläche

        double realCords;
        double realMath;
        double imagCords;
        double imagMath;

        /* (03) */
        final ComplexNumber c = new ComplexNumber();
        /* (04) */
        ComplexNumber z = new ComplexNumber();

        int iterCount = 0;

        /* (06) */
        for (int real = 0; real < mapGrid.length; real++) {
            /* (06) */
            for (int imag = 0; imag < mapGrid[real].length; imag++) {

                /* (07) */
                realCords = real - Properties.WINDOW_CENTER_HOR +realStart;
                /* (08) */
                realMath = realCords * Properties.SCALE;

                /* (07) */
                imagCords = Properties.WINDOW_CENTER_VER - imag - imagStart;
                /* (08) */
                imagMath = imagCords * Properties.SCALE;

                /* (09) */
                c.setRealImag(realMath, imagMath);
                z.setRealImag(0, 0);


                Color clr = Color.rgb(0, 0, 0);

                /* (10) */
                for (int iter = 0; iter < Properties.ITERATIONS; iter++) {
                    /* (11) */
                    z.square();
                    z.adding(c);

                    iterCount = iter;

                    /* (12) */
                    if (z.real < -2 || z.real > 0.5) {
                        clr = Color.rgb(255, 255, 255);
                        break;
                    }
                }

                if (mapGrid[real][imag]==null) { // Wenn die Map leer ist, werden neue Pixel erstellt
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
                } else if (zoomed){
                    if (iterCount < 255) {
                        ((Rectangle)mapGrid[real][imag].getRectangle()).setFill(Color.rgb(0, iterCount / 2, iterCount));
                    } else {
                        ((Rectangle)mapGrid[real][imag].getRectangle()).setFill(Color.rgb(0,0,0));
                    }

                    if (firstZoom && (real == Properties.WINDOW_WIDTH/2 || imag == Properties.WINDOW_HEIGHT/2)) {
                        ((Rectangle) mapGrid[real][imag].getRectangle()).setFill(Color.rgb(255, 255, 255));
                    }
                } else { // Wenn bereits etwas in Map drin ist, wird nur die Farbe geändert (Leistungsersparnis)
                    // Hier schwarz weiß, da man mehr sehen will
                    ((Rectangle)mapGrid[real][imag].getRectangle()).setFill(clr);
                }

            }
        }

        System.out.println("Speichergröße: " +mapGroup.getChildren().size());
    }

    void generateMap(int xClick, int yClick) {

        if (!zoomed) {
            zoomed = true;

            // Versetzen der Mandelbrotmenge in die Mitte
            Properties.WINDOW_CENTER_HOR = Properties.WINDOW_WIDTH/2;
            Properties.WINDOW_CENTER_VER = Properties.WINDOW_HEIGHT/2;

            // Scaleanpassung
            Properties.REAL_LENGTH = 4;
            Properties.SCALE = Properties.REAL_LENGTH / Properties.WINDOW_WIDTH;

            generateMap();

            firstZoom = false;
        } else {
            System.out.println("Ende");
        }

    }
}