import javafx.scene.Node;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Pixel {

    private final Node rectangle;

    Pixel(int x, int y, Color color) {

        rectangle = new Rectangle(x * Main.rectSize, y * Main.rectSize, Main.rectSize, Main.rectSize);

        setFill(color);

    }


   void setFill(Color color){

       ((Rectangle)rectangle).setFill(color);

   }

    public Node getRectangle() {
        return rectangle;
    }
}
