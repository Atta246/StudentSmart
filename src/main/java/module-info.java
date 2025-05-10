module com.example.studentsmart {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;
    requires java.sql;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires net.synedra.validatorfx;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;
    requires eu.hansolo.tilesfx;
    requires com.almasb.fxgl.all;
    requires java.desktop;

    opens com.example.studentsmart to javafx.fxml;
//    exports com.example.studentsmart;
    exports com.example.studentsmart.Classes;
    opens com.example.studentsmart.Classes to javafx.fxml;
    exports com.example.studentsmart.Controllers;
    opens com.example.studentsmart.Controllers to javafx.fxml;
    exports com.example.studentsmart.Applications;
    opens com.example.studentsmart.Applications to javafx.fxml;
}