import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;


public class Main  extends Application {


    /**
     *
     * Nur zur genrellen Darstellung eines Fensters und Starten des Programmes
     *
     * */
    Pane root;

    private Parent createContent(){

        root = new Pane();
        Controller con = new Controller();

        con.makeMap();

        root.getChildren().add(con.map.mapGroup);

        root = con.getEvents(root);

        return root;
    }

    @Override
    public void start(Stage primaryStage){

        primaryStage.setScene(new Scene(createContent()));

        primaryStage.setHeight(Properties.WINDOW_HEIGHT+37);
        primaryStage.setWidth(Properties.WINDOW_WIDTH+13);
        primaryStage.setTitle(Properties.WINDOW_TITLE);

        primaryStage.show();

    }
}