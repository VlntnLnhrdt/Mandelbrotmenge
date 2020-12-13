import javafx.scene.Group;
import javafx.scene.paint.Color;

public class Map {

    Pixel[][] mapGrid; // Eine Map (multiDimensionales Array) für zukünftige Pixel wird erzeugt
    Group mapGroup;

    Map(int mapHeight, int mapWidth) {

        mapGrid = new Pixel[mapWidth][mapHeight]; // Festlegen der Größe
        mapGroup = new Group();

    }

    void generateMap() {

        // Hier werden die Pixel mit zugehörigen Farben hinzugefügt (aktuell nur einfarbig)

        for (int i = 0; i < mapGrid.length - 1; i++) {

            for (int j = 0; j < mapGrid[i].length; j++) {

                mapGrid[i][j] = new Pixel(i, j, Color.rgb(27,213,213));

                mapGroup.getChildren().add(mapGrid[i][j].getRectangle());

            }
        }
    }
}