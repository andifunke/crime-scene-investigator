package CrimeSceneInvestigator;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class CrimeSceneInvestigator extends Application {


    static Stage openStage;

    @Override
    public void start(final Stage primaryStage) throws Exception{

        SQLController.connect();
        Parent root = FXMLLoader.load(getClass().getResource("CrimeSceneInvestigator.fxml"));
        primaryStage.setTitle("Crime Scene Investigator");
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        openStage = primaryStage;
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
