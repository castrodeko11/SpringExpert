module com.example.projectsales {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.projectsales to javafx.fxml;
    exports com.example.projectsales;
}