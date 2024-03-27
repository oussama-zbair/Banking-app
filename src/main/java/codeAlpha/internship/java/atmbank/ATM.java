package codeAlpha.internship.java.atmbank;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class ATM extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("ATM.fxml"));
        Parent root = loader.load();

        ATMController controller = loader.getController();

        BankAccount bankAccount = new BankAccount(1000.0);
        controller.initialize(bankAccount);

        primaryStage.setTitle("ATM Machine");
        primaryStage.setScene(new Scene(root, 300, 200));
        primaryStage.setOnCloseRequest(e -> controller.handleExit());
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
