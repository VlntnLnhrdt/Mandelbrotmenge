import javafx.scene.Node;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Pixel {

    private final Node rectangle;

    Pixel(int x, int y, Color color) {

        rectangle = new Rectangle(x * Main.pixelSize, y * Main.pixelSize, Main.pixelSize, Main.pixelSize); // eine Node mit den Eigenschaften eines Rectangles wird erstellt

        setFill(color);

    }


   void setFill(Color color){

       ((Rectangle)rectangle).setFill(color);//das würde  nicht gehen, wenn Zeile 11 nicht so wäre, wie sie jetzt ist

   }

    public Node getRectangle() {
        return rectangle;
    }
}
