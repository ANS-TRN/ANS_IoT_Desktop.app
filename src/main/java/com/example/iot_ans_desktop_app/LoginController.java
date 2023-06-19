package com.example.iot_ans_desktop_app;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class LoginController {
    @FXML
    private TextField usernameField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private Label errorLabel;

    private MainApp mainApp;

    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;
    }

    @FXML
    private void initialize() {
        errorLabel.setVisible(false);
    }

    @FXML
    private void handleLogin() {
        String username = usernameField.getText();
        String password = passwordField.getText();

        // TODO: Sprawdź poprawność danych logowania
        boolean loginSuccessful = performLogin(username, password);

        if (loginSuccessful) {
            mainApp.showHomeScreen();
        } else {
            errorLabel.setText("Błędne dane logowania.");
            errorLabel.setVisible(true);
        }
    }

    private boolean performLogin(String username, String password) {
        // TODO: Implementacja logiki logowania
        // Sprawdź poprawność danych logowania
        // Zwróć true, jeśli logowanie było udane, false w przeciwnym razie

        // Przykładowa implementacja:
        return username.equals("admin") && password.equals("admin");
    }
}
