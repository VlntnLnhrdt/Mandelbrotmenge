import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;


public class Main  extends Application {


    /**
     *
     * Nur zur genrellen Darstellung eines Fensters und Starten des Programmes
     *
     * */

    private Parent createContent(){

        Pane root = new Pane();
        Controller con = new Controller();

        con.makeMap();

        root.getChildren().add(con.map.mapGroup);

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