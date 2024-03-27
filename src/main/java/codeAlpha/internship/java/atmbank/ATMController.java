package codeAlpha.internship.java.atmbank;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TextInputDialog;

import java.util.Optional;

public class ATMController {

    @FXML
    private Label balanceLabel;

    private BankAccount bankAccount;

    public void initialize(BankAccount account) {
        this.bankAccount = account;
        updateBalanceLabel();
    }

    @FXML
    private void handleDeposit() {
        TextInputDialog dialog = new TextInputDialog();
        dialog.setHeaderText(null);
        dialog.setContentText("Enter deposit amount:");
        dialog.showAndWait().ifPresent(amount -> {
            try {
                double depositAmount = Double.parseDouble(amount);
                bankAccount.deposit(depositAmount);
                updateBalanceLabel();
            } catch (NumberFormatException e) {
                showAlert("Invalid input", "Please enter a valid number.");
            }
        });
    }

    @FXML
    private void handleWithdraw() {
        TextInputDialog dialog = new TextInputDialog();
        dialog.setHeaderText(null);
        dialog.setContentText("Enter withdrawal amount:");
        dialog.showAndWait().ifPresent(amount -> {
            try {
                double withdrawalAmount = Double.parseDouble(amount);
                if (bankAccount.withdraw(withdrawalAmount)) {
                    updateBalanceLabel();
                } else {
                    showAlert("Error", "Invalid withdrawal amount or insufficient funds.");
                }
            } catch (NumberFormatException e) {
                showAlert("Invalid input", "Please enter a valid number.");
            }
        });
    }

    @FXML
    void handleExit() {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Exit");
        alert.setHeaderText(null);
        alert.setContentText("Are you sure you want to exit?");
        Optional<ButtonType> result = alert.showAndWait();
        if (result.orElse(ButtonType.CANCEL) == ButtonType.OK) {
            System.exit(0);
        }
    }

    private void updateBalanceLabel() {
        balanceLabel.setText("Current Balance: " + bankAccount.getBalance());
    }

    private void showAlert(String title, String content) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }
}
