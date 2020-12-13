import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;


public class Main  extends Application {

    /**
     *
     * windowSizeHeight = Höhe des App-Fensters
     * windowSizeWidth = Breite des App-Fensters
     *
     */

    //final static int windowSizeHeight = 1080, windowSizeWidth = 1920;

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

        primaryStage.setHeight(Properties.WINDOW_HEIGHT);
        primaryStage.setWidth(Properties.WINDOW_WIDTH);
        primaryStage.setTitle(Properties.WINDOW_TITLE);

        primaryStage.show();

    }
}