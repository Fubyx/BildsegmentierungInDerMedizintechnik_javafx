package i.bildsegmentierungindermedizintechnik_javafx;

import Graph_Building_and_dijkstra.Node;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;

import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicInteger;

public class ViewController {
    View view;
    ArrayList<Rectangle> recArray = new ArrayList<>();
    ArrayList<Node> nodes;
    Image polarCord;

    public ViewController(View view) {
        this.view = view;
        //Anzeigen des Polarbildes

        //Provisorische Nodes zum Testen (Ersetzen mit der "build" methode, die ein Nodearray zurückgibt)
        nodes = new ArrayList<>();
        nodes.add(new Node(0,100, 1));
        nodes.add(new Node(100,300,1));
        nodes.add(new Node(200,50,1));
        nodes.add(new Node(300, 300, 1));

        displayLayout();

    }

    private void dykHandler(ActionEvent event) {
        displayLine(nodes);
    }
    public void displayLayout(){
        //Deko Rechtecke
        recArray.add(view.createRectangle(view.windowSizeX, view.windowSizeY/6, 0, 0, "#c2f2ee"));
        recArray.add(view.createRectangle(400,400,view.windowSizeX/2-200,view.windowSizeY/6+25,"#c2f2ee"));
        view.view.getChildren().addAll(recArray);

        //Buttons
        Button dykButton = new Button();
        dykButton.setOnAction(this::dykHandler);
        dykButton.setText("Create Dijkstra");
        dykButton.setPrefWidth(125);
        dykButton.setPrefHeight(recArray.get(0).getHeight()/3);
        dykButton.setLayoutY(recArray.get(0).getHeight()/3);
        dykButton.setLayoutX(view.windowSizeX-dykButton.getPrefWidth()-25);
        view.view.getChildren().add(dykButton);

    }
    public void displayLine(ArrayList<Node> nodeArray/*, String url*/){
        Timeline timeline;
        // Um die start x und y Werte in das Rectangle zu verschieben (aus Schönheitsgründen)
        final int beginOfDisplayX = (int)(recArray.get(1).getX());
        System.out.println(recArray.get(1).getX());
        final int beginOfDisplayY = (int)(recArray.get(1).getY());
        System.out.println(beginOfDisplayY);

            AtomicInteger i = new AtomicInteger(1);
            KeyFrame keyFrame = new KeyFrame(new Duration(100), event -> {
                int startx = nodeArray.get(i.get() -1).x + beginOfDisplayX;
                int starty = nodeArray.get(i.get() -1).y + beginOfDisplayY;
                int endx = nodeArray.get(i.get()).x + beginOfDisplayX;
                int endy = nodeArray.get(i.get()).y + beginOfDisplayY;
                view.view.getChildren().add(view.createLine(startx, starty,  endx, endy));
                System.out.println("startx:" + startx + "\t starty:" + starty+ "\tendX:" + endx + "\t Endy:" + endy);
                i.addAndGet(1);
            });
            timeline = new Timeline(keyFrame);
            timeline.setCycleCount(nodeArray.size()-1);
            timeline.play();
    }
}