package i.bildsegmentierungindermedizintechnik_javafx;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

public class View extends Application {
    final Group view = new Group();
    Scene scene;
    ViewController viewControler;
    final int windowSizeX = 500;  // Einfache Änderung der Größe des Fensters (nur am Start möglich)
    final int windowSizeY = 550;

    // Eine Methode um die Rechtecke einfacher zu erstellen, die für die Dekoration des Fensters benötigt werden
    public Rectangle createRectangle(int sizex, int sizey, int x, int y, String color){
        Rectangle rectangle = new Rectangle();
        rectangle.setWidth(sizex);
        rectangle.setHeight(sizey);
        rectangle.setX(x);
        rectangle.setY(y);
        rectangle.setFill(Paint.valueOf(color));
        return rectangle;
    }
    //Da im ViewController öfters eine Linie gezeichnet werden muss, wurde dies hier als methode geschrieben,
    // um den späteren Prozess zu vereinfachen
    public Line createLine(int startx, int starty, int endx, int endy) {
        Line line = new Line();
        line.setStartX(startx);
        line.setStartY(starty);
        line.setEndX(endx);
        line.setEndY(endy);
        line.setStroke(Paint.valueOf("#ff0000")); // Farbe der Linie
        return line;
    }

    @Override
    public void start(Stage stage){
        scene = new Scene(view, windowSizeX, windowSizeY);

        viewControler = new ViewController(this); // übergibt sich selbst (view) an den Konstruktor des ViewControllers
        stage.setTitle("Bildsegmentierung in der Medizintechnik"); // Titel des Fensters
        stage.setScene(scene);
        stage.show();

    }
    public static void main(String[] args) {
        launch();
    }
}
