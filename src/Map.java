import javafx.scene.Group;
import javafx.scene.paint.Color;

public class Map {

    Pixel[][] mapGrid;// Die map besteht aus Pixel-Elementen
    Group mapGroup;

    Map(int mapSize) {

        mapGrid = new Pixel[mapSize][mapSize];
        mapGroup = new Group();

    }

    void generateMap() {

        Pixel[] grid;

        for (int i = 0; i < mapGrid.length - 1; i++) {// das gesammte Array wird durchgegangen

            grid = mapGrid[i];

            for (int j = 0; j < grid.length; j++) {

                mapGrid[i][j] = new Pixel(i, j, Color.rgb(calcColor(i),calcColor(j), 0));


                mapGroup.getChildren().add(mapGrid[i][j].getRectangle());// nachdem der Pxel deklariert wurde, wird die Node (getRectangle gibt die Node vom Pixel zurück)
                                                                         // der mapGroup hinzugefügt.(eine Group enthält Nodes)

            }


        }


    }


    int calcColor(int maxNumber) {//die rgb Werte können maximal den Wert 255 haben, damit das auch geht, wenn die map > 255 Pixel breit ist, gibt es diese Methode

        return (int) (maxNumber / ((double) Main.mapSize / Main.rgbRange));// auch wenn die Map 1000 Pixel-Einheiten breit ist wird maximal 255 returned
    }

}
