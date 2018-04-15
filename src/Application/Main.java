package Application;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application
{
    @Override
    public void start(Stage stage) throws Exception
    {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../Views/Login.fxml"));
        Parent root = (Parent)loader.load();
        stage.setTitle("System zarządzania kancelarią");
        Scene scene = new Scene(root, 400, 350);
        scene.getStylesheets().add("Styles/Theme.css");

        stage.setScene(scene);
        stage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
