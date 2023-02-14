module i.bildsegmentierungindermedizintechnik_javafx {
    requires javafx.controls;
    requires javafx.fxml;


    opens i.bildsegmentierungindermedizintechnik_javafx to javafx.fxml;
    exports i.bildsegmentierungindermedizintechnik_javafx;
}