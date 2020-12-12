import javafx.scene.Group;
import javafx.scene.paint.Color;

public class Map {

    Pixel[][] mapGrid;
    Group mapGroup;

    Map(int mapSize) {

        mapGrid = new Pixel[mapSize][mapSize];
        mapGroup = new Group();

    }

    void generateMap() {

        Pixel[] grid;

        for (int i = 0; i < mapGrid.length - 1; i++) {

            grid = mapGrid[i];

            for (int j = 0; j < grid.length; j++) {

                mapGrid[i][j] = new Pixel(i, j, Color.rgb(calcColor(i), calcColor(j), 0));


                mapGroup.getChildren().add(mapGrid[i][j].getRectangle());

            }


        }


    }


    int calcColor(int maxNumber) {

        return (int) (maxNumber / ((double) Main.mapSize / Main.rgbRange));
    }

}
