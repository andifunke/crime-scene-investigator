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

        final Parameters params = getParameters();
        final List<String> parameters = params.getRaw();

        if (!parameters.isEmpty() && parameters.get(0).toLowerCase().equals("setupdb")) {
            FileReader fr = null;
            try {
                fr = new FileReader(dbSetupFile);
            } catch (FileNotFoundException e) {
                System.out.println("Datei nicht vorhanden!");
                System.exit(0);
            }
            BufferedReader br = null;
            String zeile;
            StringBuilder stringBuilder = new StringBuilder();
            LinkedList<String> statementList = new LinkedList<>();
            try {
                br = new BufferedReader(fr);
                boolean newItem = false;
                while( (zeile = br.readLine()) != null ) {
                    if (zeile.toLowerCase().trim().startsWith("create") ||
                          zeile.toLowerCase().trim().startsWith("insert")) {
                        if (newItem) {
                            statementList.add(stringBuilder.toString());
                            stringBuilder.setLength(0);
                        }
                        newItem = true;
                    }
                    if (zeile.toLowerCase().trim().startsWith("select")) {
                        if (newItem) {
                            statementList.add(stringBuilder.toString());
                            stringBuilder.setLength(0);
                        }
                        break;
                    }
                    stringBuilder.append(zeile);
                    stringBuilder.append('\n');
                }
                br.close();
                SQLController.setUpDB(statementList);
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                try {
                    fr.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            //System.exit(0);
        }
        else {
            Parent root = FXMLLoader.load(getClass().getResource("CrimeSceneInvestigator.fxml"));
            primaryStage.setTitle("Crime Scene Investigator");
            Scene scene = new Scene(root);
            primaryStage.setScene(scene);
            primaryStage.show();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
