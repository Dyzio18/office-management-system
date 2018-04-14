package Controllers;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.awt.event.ActionEvent;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.ResourceBundle;

import DBConnection.DBHandler;

public class LoginController implements Initializable{

    @FXML
    private PasswordField password;

    @FXML
    private TextField login;

    @FXML
    private Button loginButton;

    private Connection connection;
    private  DBHandler dbHandler;
    private PreparedStatement pst;

    @Override
    public void initialize(URL arg0, ResourceBundle arg1){

        dbHandler - new DBHandler();
    }

    @FXML
    public void loginAction(ActionEvent event) {

    }
}
