package baseContruction;

public class Controller {

    // Vorbereitung für Zooming-Logik

    Map map;

    void makeMap(){

        map = new Map(Main.windowSizeHeight, Main.windowSizeWidth);
        map.generateMap();

    }
}