package Main;

import Graph_Building_and_dijkstra.Dijkstra;
import Graph_Building_and_dijkstra.Graph;
import Graph_Building_and_dijkstra.Node;
import PicturePrep.PicturePreparation;
import i.bildsegmentierungindermedizintechnik_javafx.View;
import javafx.scene.image.Image;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        File f = new File("src/data/IMG_4.png");
        int [][] picture = PicturePreparation.imagePrep(f);
        Graph g = new Graph();
        g.build(picture);
        Node [] path = Dijkstra.dijkstra(g);

        View.polarImage = new Image(new FileInputStream(PicturePreparation.NAME_OF_POLAR_IMAGE));
        View.path = path;
        View.main();
    }
}
