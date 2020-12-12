import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.CacheHint;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;



public class Main  extends Application {


    final static int rectSize = 10, mapSize = 80, rgbRange = 255;

    private Parent createContent(){

        Pane root = new Pane();

        Controller con = new Controller();

        con.makeMap();
        root.getChildren().add(con.map.mapGroup);



        root.setBackground(new Background(new BackgroundFill(Color.BLACK, CornerRadii.EMPTY, Insets.EMPTY)));

        return root;
    }

    @Override
    public void start(Stage primaryStage){

        primaryStage.setScene(new Scene(createContent()));

        primaryStage.setHeight(800);
        primaryStage.setWidth(800);



        primaryStage.show();

    }

}
