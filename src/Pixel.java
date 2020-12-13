import javafx.scene.Node;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Pixel {

    /**
    *
    * Stellt einen Pixel dar
    *
    */

    private final Node rectangle;

    Pixel(int x, int y, Color color) {
        rectangle = new Rectangle(x, y, 1, 1); // Node mit den Eigenschaften eines Rectangles wird erstellt
        ((Rectangle)rectangle).setFill(color); // Farbgebung
    }

    public Node getRectangle() {
        return rectangle;
    }
}
