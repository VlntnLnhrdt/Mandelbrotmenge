import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;

public class Controller {

    // Vorrausgedanke für Zooming-Logik

    Map map;

    void makeMap(){

        map = new Map(Properties.WINDOW_HEIGHT, Properties.WINDOW_WIDTH);
        map.generateMap();

    }

    // Erkennung eines Mausklicks
    Pane getEvents(Pane root) {

        root.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                System.out.println(event.getSceneX());
                System.out.println(event.getSceneY());

                // TODO Mit Zehnfachvergrößerung (WINDOW.SIZE/10
            }
        });

        return root;
    }
}