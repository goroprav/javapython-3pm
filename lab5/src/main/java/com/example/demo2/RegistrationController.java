package com.example.demo2;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class RegistrationController {

    @FXML
    private TextField nameField;


    @FXML
    private TextField usernameField;

    @FXML
    private PasswordField passwordField;



    @FXML
    private Button registerButton;



    @FXML
    public void register(ActionEvent event) {
        String username = usernameField.getText();
        String password = passwordField.getText();

        // Check if username or password is empty
        if (username.isEmpty() || password.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Ошибка");
            alert.setHeaderText(null);
            alert.setContentText("Пожалуйста, заполните все поля");
            alert.showAndWait();
            return;
        }


        if (UserDataManager.isUserExists(username)) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Ошибка");
            alert.setHeaderText(null);
            alert.setContentText("Пользователь с таким логином уже существует");
            alert.showAndWait();
            return;
        }

        User newUser = new User(username, password);
        UserDataManager.saveUser(newUser);

        Stage stage = (Stage) registerButton.getScene().getWindow();
        stage.close();
    }
}
