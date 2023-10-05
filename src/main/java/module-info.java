module app.cadastro {
    requires javafx.controls;
    requires javafx.fxml;


    opens app.cadastro to javafx.fxml;
    exports app.cadastro;
}