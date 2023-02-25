package i.bildsegmentierungindermedizintechnik_javafx;

import Graph_Building_and_dijkstra.Node;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
//import javafx.scene.image.Image;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;

import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicInteger;

public class ViewController {
    View view;
    ArrayList<Rectangle> recArray = new ArrayList<>(); // Array der zur Deko benötigten Dreiecke
    ArrayList<Node> nodes; // Nodes mit Koordinaten zum kürzesten Weg
    //Image polarCord;

    public ViewController(View view) { // Im Konstruktor des ViewCOntrollers wird einerseits ein View übergeben, über den man das Fenster bearbeiten kann
        this.view = view;
        //Anzeigen des Polarbildes

        //Provisorische Nodes zum Testen (ersetzen mit der "build" Methode, die ein modeArray zurückgibt)
        nodes = new ArrayList<>();
        nodes.add(new Node(0,100, 1));
        nodes.add(new Node(100,300,1));
        nodes.add(new Node(200,50,1));
        nodes.add(new Node(300, 300, 1));

        displayLayout(); // Methode zum einfachen Anzeigen der Buttons und Rechtecke

    }

    private void dykHandler(ActionEvent event) { // sobald der dykButton ("Create Dijkstra") gedrückt wird, soll der kürzeste Weg eingezeichnet werden
        displayLine(nodes);
    }
    public void displayLayout(){
        //Deko Rechtecke
        recArray.add(createRectangle(view.windowSizeX, view.windowSizeY/6, 0, 0, "#c2f2ee"));
        recArray.add(createRectangle(400,400,view.windowSizeX/2-200,view.windowSizeY/6+25,"#c2f2ee"));
        view.view.getChildren().addAll(recArray); // zeigt das gesamte recArray an

        //Button
        Button dykButton = new Button();
        dykButton.setOnAction(this::dykHandler); // Wenn der Button gedrückt wird, soll er zu angegebener Methode springen.
        dykButton.setText("Create Dijkstra");
        dykButton.setPrefWidth(125);
        dykButton.setPrefHeight(recArray.get(0).getHeight()/3);
        dykButton.setLayoutY(recArray.get(0).getHeight()/3);
        dykButton.setLayoutX(view.windowSizeX-dykButton.getPrefWidth()-25);
        view.view.getChildren().add(dykButton); // Zeigt den Button an

    }
    public void displayLine(ArrayList<Node> nodeArray/*, String url*/){
        Timeline timeline;
        // Um die start x und y Werte in das Rectangle zu verschieben (aus Schönheitsgründen)
        final int beginOfDisplayX = (int)(recArray.get(1).getX());
        System.out.println(recArray.get(1).getX());
        final int beginOfDisplayY = (int)(recArray.get(1).getY());
        System.out.println(beginOfDisplayY);

            AtomicInteger i = new AtomicInteger(1); // Counter zum Orientieren des nodeArrays
            KeyFrame keyFrame = new KeyFrame(new Duration(100), event -> { // Die Linienführung erfolgt mittels einer kleinen Animation bzw. mit einer Verzögerung der einzelnen Linien
                int startX = nodeArray.get(i.get() -1).x + beginOfDisplayX;
                int startY = nodeArray.get(i.get() -1).y + beginOfDisplayY;
                int endX = nodeArray.get(i.get()).x + beginOfDisplayX;
                int endY = nodeArray.get(i.get()).y + beginOfDisplayY;
                view.view.getChildren().add(createLine(startX, startY,  endX, endY)); //Zeigt die Linie final auf dem Fenster an
                System.out.println("startX:" + startX + "\t startY:" + startY+ "\tendX:" + endX + "\t endY:" + endY);
                i.addAndGet(1); // Der counter wird erhöht
            });
            timeline = new Timeline(keyFrame);
            timeline.setCycleCount(nodeArray.size()-1); // Die Timeline soll so oft wiederholt werden, bis das nodeArray vollends durchgegangen wurde
            timeline.play();
    }

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
}