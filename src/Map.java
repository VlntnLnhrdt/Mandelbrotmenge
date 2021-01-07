import com.sun.scenario.effect.impl.sw.sse.SSEBlend_SRC_OUTPeer;
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

    ComplexNumber[][] mapGrid; // Eine Map (multiDimensionales Array) für zukünftige Pixel wird erzeugt
    Group mapGroup;
    boolean zoomed = false; // Wenn gezoomt ist werden Tasten ignoriert
    boolean growing = false; // Wenn sie entsteht, wird die Maus ignoriert
    int mapHeight, mapWidth;

    /* (01) */
    Map(int mapHeight, int mapWidth) {
        this.mapHeight = mapHeight;
        this.mapWidth = mapWidth;

        mapGrid = new ComplexNumber[mapWidth][mapHeight]; // Festlegen der Größe
        mapGroup = new Group();
    }


    void generateMap() {

        Util.startTimer();

        ComplexNumber z;
        double realMath, imagMath;
        int iterations = 0;

        for (int real = 0; real < mapGrid.length; real++) {

            for (int imag = 0; imag < mapGrid[real].length; imag++) {

                realMath = (real - Properties.WINDOW_CENTER_HOR) * Properties.SCALE;
                imagMath = (Properties.WINDOW_CENTER_VER - imag) * Properties.SCALE;

                z = new ComplexNumber(realMath,imagMath,real,imag);

                for (int i = 0; i<Properties.ITERATIONS;i++) {
                    z.square();
                    z.adding(realMath, imagMath);

                    iterations = i;

                    if (z.getReal()<-2 || z.getReal()>0.5)
                        break;

                }

                // FIXME Entstehung fehlt

                if (mapGrid[real][imag]==null) {
                    mapGrid[real][imag] = new ComplexNumber(realMath, imagMath, real, imag);

                    mapGrid[real][imag].setColor(Color.rgb(0, iterations < 256 ? iterations / 2 : 0, iterations < 256 ? iterations : 0));


                    mapGroup.getChildren().add(mapGrid[real][imag].getRectangle());
                } else {

                    mapGrid[real][imag].upadateContent(realMath, imagMath, Color.rgb(0, iterations < 256 ? iterations / 2 : 0, iterations < 256 ? iterations : 0));
                }

                if (zoomed && (real==Properties.WINDOW_CENTER_HOR || imag==Properties.WINDOW_CENTER_VER))
                    mapGrid[real][imag].setColor(Color.WHITE);

            }
        }

        Util.endTimer();
    }

    void generateMap(int xClick, int yClick) {

        if (!zoomed) {// Mandelbrotmenge wird in die Mitte verschoben
            System.out.println("Mittelung der Mandelbrotmenge mit Koordinatenachsen");
//            System.out.println(mapGrid[xClick][yClick].getReal()+" "+mapGrid[xClick][yClick].getImag()+"i");
            zoomed = true;

            // Versetzen der Mandelbrotmenge in die Mitte
            Properties.WINDOW_CENTER_HOR = Properties.WINDOW_WIDTH / 2;
            Properties.WINDOW_CENTER_VER = Properties.WINDOW_HEIGHT / 2;

            // Scaleanpassung
            Properties.REAL_LENGTH = 4;
            Properties.SCALE = Properties.REAL_LENGTH / Properties.WINDOW_WIDTH;

            generateMap();

        } else {// Zoom

            System.out.println("Zoom bei "+Util.getRounding(mapGrid[xClick][yClick].getReal(), mapGrid[xClick][yClick].getImag()));
            Util.startTimer();

            Properties.SCALE /= Properties.ZOOM_FACTOR;

            double realAddMove = Properties.WINDOW_CENTER_HOR*Properties.SCALE;
            double imagAddMove = Properties.WINDOW_CENTER_VER*Properties.SCALE;

            double realStart = mapGrid[xClick][yClick].getReal() - realAddMove ;
            double imagStart = mapGrid[xClick][yClick].getImag() + imagAddMove;


            ComplexNumber z;
            int iterations = 0;

            for (int real = 0; real<mapGrid.length; real++) {
                for (int imag = 0; imag<mapGrid[real].length; imag++) {

                    z = new ComplexNumber(realStart+(real*Properties.SCALE),imagStart-(imag*Properties.SCALE),real,imag);

                    for (int i=0; i<Properties.ITERATIONS;i++){
                        z.square();
                        z.adding(realStart+(real*Properties.SCALE), imagStart-(imag*Properties.SCALE));

                        iterations = i;

                        if (z.getReal()<-2 || z.getReal()>0.5)
                            break;
                    }

                    mapGrid[real][imag].upadateContent(realStart+(real*Properties.SCALE), imagStart-(imag*Properties.SCALE), Color.rgb(0, iterations < 256 ? iterations / 2 : 0, iterations < 256 ? iterations : 0));

                }
            }
            
            Util.endTimer();

        }
    }
}