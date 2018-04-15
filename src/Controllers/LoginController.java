package Controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.awt.event.ActionEvent;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

import DBConnection.DBHandler;
import javafx.stage.Stage;

/**
 * TODO:
 * [ ] hash password (encrypt)
 */

public class LoginController implements Initializable{

    @FXML
    private PasswordField password;

    @FXML
    private TextField login;

    @FXML
    private Button loginButton;

    private  DBHandler handler;
    private PreparedStatement pst;
    private Connection connection;

    @Override
    public void initialize(URL arg0, ResourceBundle arg1){

        handler = new DBHandler();
        loginButton.setOnAction((event) -> {
            loginAction();
        });
    }


    @FXML
    private void loginAction() {

        connection = handler.getConnection();

        String queryGetUser = "SELECT * FROM users WHERE login=? AND password=?";
        try {
            pst = connection.prepareStatement(queryGetUser);
            pst.setString(1,login.getText());
            pst.setString(2,password.getText());
            ResultSet rs = pst.executeQuery();

            int count = 0;
            while (rs.next()){
                count++;
            }
            if(count==1){
                login.getScene().getWindow().hide();

                Stage panel = new Stage();
                try {
                    Parent root = FXMLLoader.load(getClass().getResource("/Views/Panel.fxml"));
                    Scene scene = new Scene(root);
                    panel.setScene(scene);
                    panel.show();
                } catch (IOException e) {
                    e.printStackTrace();
                }

            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setHeaderText("Błąd logowania");
                alert.setContentText("Nie poprawny login lub hasło");
                alert.show();
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        finally {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }


}
