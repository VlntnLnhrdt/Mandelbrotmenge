import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;


public class Main  extends Application {

    /**
     *
     * pixelSize = Größe der einzelnen Rechtecke (Höhe und Breite)
     * mapSize = Anzahl der Pixel-Spalten / -Zeilen
     * rgbRange = verhinderrung von Magic-Numbers
     * windowSize = größe des App-Fensters (Höhe und Breite)
     *
     */
    final static int pixelSize = 10, mapSize = 80, rgbRange = 255, windowSize = 800;

    private Parent createContent(){

        Pane root = new Pane();


        Controller con = new Controller();

        con.makeMap();

        root.getChildren().add(con.map.mapGroup); // wenn Sachen angezeigt werden  sollen, müssen diese root hinzugefügt werden
        //root nimmt Nodes und auch Group-Elemente, die Nodes enthalten



        //root.setBackground(new Background(new BackgroundFill(Color.BLACK, CornerRadii.EMPTY, Insets.EMPTY))); damit könnte man theorethisch den Hintergrund schwarz einfärben

        return root;
    }

    @Override
    public void start(Stage primaryStage){

        primaryStage.setScene(new Scene(createContent()));

        primaryStage.setHeight(windowSize);
        primaryStage.setWidth(windowSize);



        primaryStage.show();

    }

}
