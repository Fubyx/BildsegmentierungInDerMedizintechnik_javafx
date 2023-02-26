module i.bildsegmentierungindermedizintechnik_javafx {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;


    opens i.bildsegmentierungindermedizintechnik_javafx to javafx.fxml;
    exports i.bildsegmentierungindermedizintechnik_javafx;
    exports Graph_Building_and_dijkstra;
}