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
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

import DBConnection.DBHandler;

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
    public void loginAction() {

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
                System.out.println("Succes");
            } else {
                System.out.println("Failure");
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
