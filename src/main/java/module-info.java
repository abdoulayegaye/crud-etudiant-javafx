module sn.toto.crudetudiantjavafx {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires TrayNotification;


    opens sn.toto.crudetudiantjavafx to javafx.fxml;
    exports sn.toto.crudetudiantjavafx;
}