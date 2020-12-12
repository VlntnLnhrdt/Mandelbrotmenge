public class Controller {


    // Hier könntest  du z.B. die Logik zum heranzoomen einfügen (Wenn Mausklick -> erstelle neue, herangezoomte map)

    Map map;

    void makeMap(){

        map = new Map(Main.mapSize);
        map.generateMap();

    }



}
