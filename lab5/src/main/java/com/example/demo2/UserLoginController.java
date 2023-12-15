package com.example.demo2;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import java.util.List;
import javafx.stage.Stage;
import javafx.scene.control.Label;
import javafx.scene.control.Alert;
import javafx.scene.layout.VBox;
import javafx.scene.Scene;
import javafx.geometry.Pos;
import java.util.concurrent.atomic.AtomicInteger;

public class UserLoginController {
    @FXML
    private TextField usernameField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private Button UserloginButton;

    @FXML
    private void login(ActionEvent event) {
        String username = usernameField.getText();
        String password = passwordField.getText();
        List<User> userList = UserDataManager.loadUsers();
        for (User user : userList) {
            if (user.getUsername().equals(username) && user.getPassword().equals(password)) {
                openNewWindow(user);

                Stage stage = (Stage) UserloginButton.getScene().getWindow();
                stage.close();
                return;
            }
        }
        // Логика для случая, когда учетные данные не совпали
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Ошибка");
        alert.setHeaderText(null);
        alert.setContentText("Неверный логин или пароль");
        alert.showAndWait();
    }

    private void openNewWindow(User user) {
        // Логика для открытия нового окна
        Stage stage = new Stage();
        Label label = new Label("Добро пожаловать в ваш личный кабинет");

        // Получение значения счетчика из объекта пользователя
        AtomicInteger counter = new AtomicInteger(user.getClickCount());
        Label counterLabel = new Label("Счетчик: " + counter.get());

        // Создание кнопки для увеличения счетчика
        Button incrementButton = new Button("Увеличить счетчик");
        incrementButton.setOnAction(e -> {
            counter.incrementAndGet();
            counterLabel.setText("Счетчик: " + counter.get());
            // Сохранение обновленного значения счетчика в объекте пользователя
            user.setClickCount(counter.get());
            UserDataManager.saveUser(user);
        });

        // Создание макета для размещения метки, счетчика и кнопки
        VBox layout = new VBox(10);
        layout.getChildren().addAll(label, counterLabel, incrementButton);
        layout.setAlignment(Pos.CENTER);

        // Создание сцены и установка макета
        Scene scene = new Scene(layout, 300, 200);

        // Установка сцены для окна
        stage.setScene(scene);

        stage.show();
    }


}
