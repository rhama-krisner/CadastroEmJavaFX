module app.cadastro {
    requires javafx.controls;
    requires javafx.fxml;
    requires org.json;
    requires java.desktop;


    opens app.cadastro to javafx.fxml;
    exports app.cadastro;
}

