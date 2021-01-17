import javafx.scene.Node;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

/**
 *
 * Dient zur Darstellung eines eingefärbten Pixels
 * Diesem werden Höhe, Breite, x- sowie y-Position und die Farbe zugewiesen
 *
 * */

public class Pixel {

    // Der wirkliche Pixel
    private final Node rectangle;

    // Konstruktur des Pixels / Erzeugung und Positionierung des Pixels
    Pixel(int x, int y) {
        rectangle = new Rectangle(x, y, 1, 1); // Node mit den Eigenschaften eines Rectangles wird erstellt
    }

    // Farbfestlegung des Pixels
    public void setColor(Color color) {
        ((Rectangle)rectangle).setFill(color);
    }

    // Getter-Funktion für die Abrufung des Pixels
    public Node getRectangle() {
        try {
            return rectangle;
        } catch (Exception e) {
            System.err.println("Eine Exception (vermutlich NullPointerException) ist aus bislang ungeklärter Ursache aufgetreten.");
            System.err.println("Bitte starten Sie das Programm neu.");
            System.exit(2020);
        }
        return null;
    }
}
