public class Controller {

    // Vorbereitung für Zooming-Logik

    Map map;

    void makeMap(){

        map = new Map(Properties.WINDOW_HEIGHT, Properties.WINDOW_WIDTH);
        map.generateMap();

    }
}