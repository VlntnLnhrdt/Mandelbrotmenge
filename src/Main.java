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

import java.util.Timer;
import java.util.TimerTask;


public class Main  extends Application {

    /**
     *
     * Nur zur genrellen Darstellung eines Fensters und Starten des Programmes
     *
     * */

    Pane root;
    Controller con;


    int growingIteration = 0;
    Timer t;
    boolean iterationGrowing = false;

    // Statische Mandelbrotmenge
    private Parent createContent(){

        root = new Pane();
        con = new Controller();

        con.makeMap();
        root.getChildren().add(con.map.mapGroup);

        //root = con.getEvents(root);

        return root;
    }

    @Override
    public void start(Stage primaryStage){

        primaryStage.setHeight(Properties.WINDOW_HEIGHT+37);
        primaryStage.setWidth(Properties.WINDOW_WIDTH+13);
        primaryStage.setTitle(Properties.WINDOW_TITLE);

        /* XX */ // Schließen des Fensters
        primaryStage.setOnCloseRequest(event -> {
            System.out.println("\n------------------------------------------------\n| Calculations stopped, Window is now closing! |\n------------------------------------------------");
            System.exit(0);
        });




        primaryStage.setScene(new Scene(createContent()));

        root.setOnMouseClicked(mouseEvent -> {
            System.out.println(mouseEvent.getX());
            System.out.println(mouseEvent.getY());
            System.out.println(mouseEvent.getButton()); // PRIMARY(links) - SECONDARY(rechts)
            con.map.generateZoomMap(mouseEvent.getX(), mouseEvent.getY());
        });

        /* XX */ // Wenn eine Taste gedrückt wird
        primaryStage.addEventHandler(KeyEvent.KEY_PRESSED, e -> {
            if (e.getCode() == KeyCode.ENTER) { // Bei Enter wird das Folgende ausgeführt
                if (!con.map.zoomed) { // Wenn gezoomt ist werden Tasten ignoriert (growingIteration)
                    if (!iterationGrowing) {
                        TimerTask tt = new TimerTask() {
                            @Override
                            public void run() {
                                /* XX */
                                growingIteration = (growingIteration < 30) ? growingIteration + 1 : growingIteration * 2;
                                Properties.ITERATIONS = growingIteration;

                                System.out.println("Generating Map with " + growingIteration + " Iterations ...");

                                con.map.generateMap();

                            }
                        };

                        /* XX */
                        t = new Timer();
                        t.schedule(tt, 0, 1000);
                    } else {
                        t.cancel();
                    }

                    iterationGrowing = !iterationGrowing;
                }
            }

            /* XX */
            if (e.getCode() == KeyCode.UP && !iterationGrowing) { // Wenn Pfeiltaste + Iteration wächst aktuell nicht
                Properties.ITERATIONS = ++growingIteration;

                System.out.println("Generating Map with " + growingIteration + " Iterations ...");

                con.map.generateMap();
            }

            if (e.getCode() == KeyCode.DOWN && !iterationGrowing && growingIteration>1) { // Wenn Pfeiltaste + Iteration wächst aktuell nicht
                Properties.ITERATIONS = --growingIteration;

                System.out.println("Generating Map with " + growingIteration + " Iterations ...");

                con.map.generateMap();
            }
        });

        primaryStage.show();





    }
}