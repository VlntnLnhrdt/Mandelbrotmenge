public class Controller {

    // Vorrausgedanke für Zooming-Logik


    Map map;

    void makeMap(){

        map = new Map(Properties.WINDOW_HEIGHT, Properties.WINDOW_WIDTH);
        try {
            map.generateMap();
        } catch (NullPointerException e) {
            System.err.println("Folgende Fehlermeldung erschien aus bislang ungeklärter Ursache: " + e);
        }


    }
}