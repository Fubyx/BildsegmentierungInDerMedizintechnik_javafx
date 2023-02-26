package i.bildsegmentierungindermedizintechnik_javafx;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

public class View extends Application {
    /**
     *  The root container, containing every graphical item
     */
    protected final Group view = new Group();
    protected int windowWidth;
    protected int windowHeight;
    public static Node[] path;
    public static Image polarImage;

    /**
     *  Creates a rectangle with the given parameters
     * @param width the width of the rectangle
     * @param height the height of the rectangle
     * @param x the x coordinate of the top left corner
     * @param y the y coordinate of the top left corner
     * @param color the color of the rectangle with the format #RRGGBB
     * @return  the created rectangle
     */
    public Rectangle createRectangle(int width, int height, int x, int y, String color){
        Rectangle rectangle = new Rectangle();
        rectangle.setWidth(width);
        rectangle.setHeight(height);
        rectangle.setX(x);
        rectangle.setY(y);
        rectangle.setFill(Paint.valueOf(color));
        return rectangle;
    }

    /**
     *  Creates a line between the given points
     * @param startX   the x coordinate of the starting point
     * @param startY   the y coordinate of the starting point
     * @param endX  the x coordinate of the endpoint
     * @param endY  the y coordinate of the endpoint
     * @return  the created line
     */
    public Line createLine(int startX, int startY, int endX, int endY) {
        Line line = new Line();
        line.setStartX(startX);
        line.setStartY(startY);
        line.setEndX(endX);
        line.setEndY(endY);
        line.setStroke(Paint.valueOf("#ff0000"));
        return line;
    }

    @Override
    public void start(Stage stage){
        windowHeight = (int) (150 + polarImage.getHeight()/2);
        windowWidth = (int) (polarImage.getWidth()/2 + 40);
        Scene scene = new Scene(view, windowWidth, windowHeight);

        new ViewController(this);
        stage.setTitle("Bildsegmentierung in der Medizintechnik");
        stage.setScene(scene);
        stage.show();

    }
    public static void main() {
        launch();
    }
}
