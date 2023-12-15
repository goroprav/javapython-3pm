// LoginController.java
package com.example.demo2;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.io.ObjectInputStream;
import java.io.FileInputStream;
import java.io.ObjectOutputStream;
import java.io.FileOutputStream;



public class LoginController {
    @FXML
    private TextField usernameField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private Button loginButton;

    @FXML
    private void login(ActionEvent event) {
        String username = usernameField.getText();
        String password = passwordField.getText();


        if ("111".equals(username) && "111".equals(password)) {
            openNewWindow();

            Stage stage = (Stage) loginButton.getScene().getWindow();
            stage.close();
            return;
        }

        // Логика для случая, когда учетные данные не совпали
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Ошибка");
        alert.setHeaderText(null);
        alert.setContentText("Неверный логин или пароль");
        alert.showAndWait();


    }


    private void openNewWindow() {
        // Логика для открытия нового окна
        Stage stage = new Stage();
        Label label = new Label("Добро пожаловать в ваш личный кабинет");

        // Чтение файла userdata.dat и извлечение информации о входе
        List<String> loginsAndPasswords = readUserData();

        // Создание TextArea для отображения логинов и паролей
        TextArea loginInfoTextArea = new TextArea();
        loginInfoTextArea.setEditable(false);
        loginInfoTextArea.setText(String.join("\n", loginsAndPasswords));

        // Создание поля ввода для логина пользователя, которого нужно удалить
        TextField deleteUserField = new TextField();
        deleteUserField.setPromptText("Введите логин пользователя для удаления");

        // Создание кнопки для удаления всех логинов и паролей
        Button deleteButton = new Button("Удалить все логины и пароли");
        deleteButton.setOnAction(e -> {
            deleteAllUsers();
            stage.close();
            openNewWindow();
        });

        // Создание кнопки для удаления определенного пользователя
        Button deleteUserButton = new Button("Удалить пользователя");
        deleteUserButton.setOnAction(e -> {
            String username = deleteUserField.getText();
            deleteSpecificUser(username);
            stage.close();
            openNewWindow();
        });

        // Создание VBox для размещения метки, информации о входе, поля ввода и кнопок удаления
        VBox layout = new VBox(10);
        layout.getChildren().addAll(label, loginInfoTextArea, deleteUserField, deleteUserButton, deleteButton);
        layout.setAlignment(Pos.CENTER);

        // Создание сцены и установка макета
        Scene scene = new Scene(layout, 300, 200);

        // Установка сцены для окна
        stage.setScene(scene);

        stage.show();
    }


    private List<String> readUserData() {
        List<String> loginsAndPasswords = new ArrayList<>();

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("userdata.dat"))) {
            List<User> userList = (List<User>) ois.readObject();
            for (User user : userList) {
                String login = user.getUsername();
                String password = user.getPassword();
                int clickCount = user.getClickCount();
                loginsAndPasswords.add("Login: " + login + ", Password: " + password + ", Click: " + clickCount );
            }
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        return loginsAndPasswords;
    }

    private void deleteAllUsers() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("userdata.dat"))) {
            oos.writeObject(new ArrayList<User>());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private void deleteSpecificUser(String username) {
        List<User> userList = new ArrayList<>();

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("userdata.dat"))) {
            userList = (List<User>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        // Находим пользователя по имени и удаляем его из списка
        userList.removeIf(user -> user.getUsername().equals(username));

        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("userdata.dat"))) {
            oos.writeObject(userList);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}