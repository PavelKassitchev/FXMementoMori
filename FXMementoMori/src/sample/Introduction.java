package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class Introduction extends Application {

    private static Controller controller;


    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception{
        FXMLLoader loader = new FXMLLoader(getClass().getResource("introduction_fx.fxml"));
        Parent root = loader.load();
        primaryStage.setTitle("Memento Mori");
        Image image = new Image(Main.class.getResource("memento_icon.png").toExternalForm());
        primaryStage.getIcons().add(image);
        primaryStage.setScene(new Scene(root, 640, 400));
        primaryStage.setResizable(false);
        primaryStage.show();

        controller = loader.getController();
        controller.setPrevStage(primaryStage);
    }
}
