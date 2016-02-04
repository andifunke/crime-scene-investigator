package CrimeSceneInvestigator;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;


public class CrimeSceneInvestigator extends Application {

    public final String dbSetupFile = "X:\\Dropbox\\BC\\Meine Dokumente\\Studium\\Informatik\\2015-10 (WS)\\Datenbanksysteme\\Abschlussprojekt 4\\dbs4_sql.txt";

    @Override
    public void start(Stage primaryStage) throws Exception{

        SQLController.connect();
        Parent root = FXMLLoader.load(getClass().getResource("CrimeSceneInvestigator.fxml"));
        primaryStage.setTitle("Crime Scene Investigator");
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
