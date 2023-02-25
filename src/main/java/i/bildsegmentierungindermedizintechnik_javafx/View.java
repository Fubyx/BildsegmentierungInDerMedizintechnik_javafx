package i.bildsegmentierungindermedizintechnik_javafx;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class View extends Application {
    final Group view = new Group();
    Scene scene;
    ViewController viewControler;
    final int windowSizeX = 500;  // Einfache Änderung der Größe des Fensters (nur am Start möglich)
    final int windowSizeY = 550;

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
