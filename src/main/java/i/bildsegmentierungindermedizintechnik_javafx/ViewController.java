package i.bildsegmentierungindermedizintechnik_javafx;

import Graph_Building_and_dijkstra.Node;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.concurrent.atomic.AtomicInteger;

public class ViewController {
    private final View view;
    private final ArrayList<Rectangle> recArray = new ArrayList<>();
    private final ArrayList<Node> path;
    private final Image polarImage;

    public ViewController(View view) {
        this.view = view;
        this.path = new ArrayList<>();
        this.path.addAll(Arrays.asList(View.path));
        this.polarImage = View.polarImage;

        displayLayout();
    }

    /**
     * Handling of klicks on the Button to display the path created by the dijkstra-algorithm
     * @param event the ActionEvent needed for a handler
     */
    private void dykHandler(ActionEvent event) {
        displayLine();
    }

    /**
     * displays the polarimage as well as a button, with which the dijkstraPath gets displayed
     */
    private void displayLayout(){
        //Displaying the polarimage
        ImageView v = new ImageView(polarImage);
        v.setX(view.windowWidth/2 - polarImage.getWidth()/2);
        v.setY(view.windowHeight/6+25);
        v.setFitWidth(polarImage.getWidth()/2);
        v.setPreserveRatio(true);
        v.setX(view.windowWidth /2 - path.size()/2);


        //Displaying rectangles used for decoration
        recArray.add(view.createRectangle(view.windowWidth, view.windowHeight/6, 0, 0, "#c2f2ee"));
        recArray.add(view.createRectangle((int) polarImage.getWidth(), (int) v.getFitHeight(), (int) v.getX(),(int)v.getY(),"#c2f2ee"));
        view.view.getChildren().add(v);

        //Button
        Button dykButton = new Button();
        dykButton.setOnAction(this::dykHandler);
        dykButton.setText("Show Dijkstra");
        dykButton.setPrefWidth(125);
        dykButton.setPrefHeight(recArray.get(0).getHeight()/3);
        dykButton.setLayoutY(recArray.get(0).getHeight()/3);
        dykButton.setLayoutX(view.windowWidth -dykButton.getPrefWidth()-25);
        view.view.getChildren().add(dykButton);
    }

    /**
     * Displays the path contained in path on the displayed polarimage.
     */
    private void displayLine(){
        Timeline timeline;
        final int beginOfDisplayX = (int)(recArray.get(1).getX());
        final int beginOfDisplayY = (int)(recArray.get(1).getY());

        AtomicInteger i = new AtomicInteger(1);
        KeyFrame keyFrame = new KeyFrame(new Duration(10), event -> {
            int startx = path.get(i.get() -1).x + beginOfDisplayX;
            int starty = path.get(i.get() -1).y + beginOfDisplayY;
            int endx = path.get(i.get()).x + beginOfDisplayX;
            int endy = path.get(i.get()).y + beginOfDisplayY;
            view.createLine(startx, starty,  endx, endy);
            i.addAndGet(1);
        });
        timeline = new Timeline(keyFrame);
        timeline.setCycleCount(path.size()-1);
        timeline.play();
    }
}