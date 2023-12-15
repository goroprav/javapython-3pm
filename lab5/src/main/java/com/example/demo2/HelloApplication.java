package com.example.demo2;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.control.Button;
import javafx.scene.layout.TilePane;
import javafx.geometry.Orientation;

import java.io.IOException;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage)  {
        Button first = new Button("Войти как администратор");
        Button second = new Button("Войти как пользователь");
        Button third = new Button("Зарегистрироваться");

        TilePane tilepane = new TilePane(Orientation.VERTICAL, first, second, third);

        Scene window = new Scene(tilepane, 300, 200);
        stage.setScene(window);

        stage.setTitle("Выберете режим входа");

        stage.show();

        first.setOnAction(e -> {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("Login.fxml"));
            try {
                Scene scene = new Scene(loader.load());
                Stage loginStage = new Stage();
                loginStage.setScene(scene);
                loginStage.show();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        });

        second.setOnAction(e -> {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("UserLogin.fxml"));
            try {
                Scene scene = new Scene(loader.load());
                Stage loginStage = new Stage();
                loginStage.setScene(scene);
                loginStage.show();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        });

        third.setOnAction(e -> {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("Registration.fxml"));
            try {
                Scene scene = new Scene(loader.load());
                Stage loginStage = new Stage();
                loginStage.setScene(scene);
                loginStage.show();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        });
    }

    public static void main(String[] args) {
        launch();
    }
}