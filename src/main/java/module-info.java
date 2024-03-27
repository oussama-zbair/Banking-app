module codAlpha.internship.java.atmbank {
    requires javafx.controls;
    requires javafx.fxml;


    opens codeAlpha.internship.java.atmbank to javafx.fxml;
    exports codeAlpha.internship.java.atmbank;
}