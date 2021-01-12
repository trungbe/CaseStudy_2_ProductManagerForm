package controller;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;

public class LoginController {

    @FXML
    private Button btnCancel;

    @FXML
    private TextField txtUsername;

    @FXML
    private Button btnLogin;

    @FXML
    private PasswordField txtPassword;


    public void login() throws IOException {
        String userName = txtUsername.getText();
        String passWord = txtPassword.getText();
        txtUsername.setText("");
        txtPassword.setText("");
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        Alert alert1 = new Alert(Alert.AlertType.WARNING);
        if (userName.equals("admin") && passWord.equals("123456")) {
//            alert.setContentText("Welcome " + userName + " to Login Management");
//            alert.setTitle("Login successfully <3");
//            alert.show();

            //Hide form login
            btnLogin.getScene().getWindow().hide();
            //Hien thi form product
            Pane root = (Pane) FXMLLoader.load(getClass().getResource("../view/product.fxml"));
            Scene scene = new Scene(root, 667, 492);
            Stage primaryStage = new Stage();
            primaryStage.setTitle("WELCOME TO MANAGEMENT PRODUCT");
            primaryStage.setScene(scene);
            primaryStage.show();
        } else if (userName.equals("") && passWord.equals("")) {
            alert1.setContentText("You haven't entered Username and Password ! Please try again !");
            alert1.setTitle("Login Error ");
            alert1.show();
        } else if (userName.equals("")) {
            alert1.setContentText("You haven't entered Username ! Please try again !");
            alert1.setTitle("Login Error ");
            alert1.show();
        } else if (passWord.equals("")) {
            alert1.setContentText("You haven't entered Password  ! Please try again !");
            alert1.setTitle("Login Error ");
            alert1.show();
        }
    }

    public void cancelLogin() {
        Platform.exit();
    }

}

