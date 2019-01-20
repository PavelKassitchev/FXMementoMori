package sample;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class Questionnaire extends Application {



    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception{
        FXMLLoader loader = new FXMLLoader(getClass().getResource("questionnaire_fx.fxml"));
        Parent root = loader.load();
        Controller controller = loader.getController();

        controller.getQuestion().setText(Questions.Q1.getQuestion());

        primaryStage.setTitle("Memento Mori");
        Image image = new Image(Main.class.getResource("memento_icon.png").toExternalForm());
        primaryStage.getIcons().add(image);
        primaryStage.setScene(new Scene(root, 640, 400));
        primaryStage.sizeToScene();
        primaryStage.setResizable(false);
        primaryStage.show();



    }



}
