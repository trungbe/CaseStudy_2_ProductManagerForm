package controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

public class ProductController {

    @FXML
    private Button btnLogout;

    public void logout() throws IOException {
        btnLogout.getScene().getWindow().hide();
        Parent root = (Parent) FXMLLoader.load(getClass().getResource("../view/login.fxml"));
        Scene scene = new Scene(root, 667, 492);
        Stage primaryStage = new Stage();
        primaryStage.setTitle("WELCOME TO MANAGEMENT LOGIN");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
