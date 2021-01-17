import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.util.Timer;
import java.util.TimerTask;

/**
 *
 * Start- und Hauptklasse des Programmes und dient zur Anzeige des konkreten Fensters
 *
 * */

public class Main  extends Application {

    // Inhalt des Fensters
    Pane root;

    // Objekt, in welchem die Karte / das Gitter mit den Pixeln berechnet wird
    Map map;

    // Als "Hochzähler" für die wachsende Mandelbrot-Menge
    int growingIteration = 0;

    // Timeline, Code wird basierend auf einer vorgegebenen Zeit regelmäßig durchgeführt (Für wachsende Mandelbrot-Menge)
    Timeline growingTimeline;

    // Statische Mandelbrotmenge
    private Parent createContent() {

        growingTimeline = new Timeline(new KeyFrame(Duration.millis(2000), e -> {
            growingIteration = (growingIteration < 30) ? growingIteration + 1 : growingIteration * 2;
            Properties.ITERATIONS = growingIteration;

            System.out.println("Generating Map with " + growingIteration + " Iterations ...");

            map.generateMap();
        }));

        growingTimeline.setCycleCount(Animation.INDEFINITE);
        growingTimeline.stop();

        root = new Pane();
        map = new Map(Properties.WINDOW_HEIGHT, Properties.WINDOW_WIDTH);
        map.generateMap();

        root.getChildren().add(map.mapGroup);

        return root;
    }

    // Erste Funktion, welche ausgeführt wird
    @Override
    public void start(Stage primaryStage) {

        // Festlegung der Fenstergröße (die beiden Zahlen, welche addiert werden, beheben einen Anzeigefehler)
        primaryStage.setHeight(Properties.WINDOW_HEIGHT + 37);
        primaryStage.setWidth(Properties.WINDOW_WIDTH + 13);

        // Festlegung des Fenstertitels
        primaryStage.setTitle(Properties.WINDOW_TITLE);

        // Sobald am Rechten-Oberen-Eck des Fensters des X angeklickt wird, erscheint folgende Benachrichtigung
        primaryStage.setOnCloseRequest(event -> {
            System.out.println("Berechnungen werden abgebrochen.");
            System.out.println("Fenster wird geschlossen.");
            System.out.println("---------------------------------------------------");
            System.exit(0);
        });

        // Setzt inhalt in Fenster und ruft obrige Funktion auf
        primaryStage.setScene(new Scene(createContent()));

        // Dient der Vergrößerung der Mandelbrot-Menge und erfasst bestimmte Mauseingaben
        root.setOnMouseClicked(mouseEvent -> {
            // Wenn die Linke-Maustaste gedrückt wird, werden die Positionsdaten an das Objekt "Map" übergeben
            if (mouseEvent.getButton() == MouseButton.PRIMARY) {
                // Wird nur durchgeführt, wenn die Mandelbrot-Menge aktuell nicht wächst (überschneidet sich sonst und es führt zu Anzeigeproblemen)
                if (!map.growing)
                    map.generateMap((int) mouseEvent.getX(), (int) mouseEvent.getY());
            }

            // Wenn die Rechte-Maustaste gedrückt wird, wird die Mandelbrot-Menge wieder zur Normalgröße zurückgesetzt
            if (mouseEvent.getButton() == MouseButton.SECONDARY) {
                map.zoomed = false;
                map.generateMap(0,0);
            }
        });

        // Dient des "Wachsens" der Mandelbrot-Menge und erfasst bestimmte Tasteneingaben
        primaryStage.addEventHandler(KeyEvent.KEY_PRESSED, e -> {
            // Wenn Enter gedrückt wurde, startet oder stoppt das Wachstum der Mandelbrot-Menge
            if (e.getCode() == KeyCode.ENTER) {
                // Wenn zuvor bereits vergrößert wurde, wird die Tasteneingabe ignoriert (überschneidet sich sonst und es führt zu Anzeigeproblemen)
                if (!map.zoomed) {
                    // Wachstumsstatus wird umgedreht
                    Properties.GROWING = !Properties.GROWING;

                    // Wenn das Wachstum bereits läuft, wird es gestoppt, ansonsten anderes herum
                    if (growingTimeline.getStatus() == Animation.Status.RUNNING)
                        growingTimeline.stop();
                    else
                        growingTimeline.play();
                }
            }
        });
        // Zeigt das Fenster dann wirklich an (hierdurch erscheint es erst)
        primaryStage.show();
    }
}