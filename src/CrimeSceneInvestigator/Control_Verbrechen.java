package CrimeSceneInvestigator;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.SplitPane;
import javafx.scene.control.TextField;

public class Control_Verbrechen extends SplitPane {

    @FXML private TextField textField;

    public Control_Verbrechen() {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("FXML/Verbrechen.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);

        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
    }

    @FXML
    private void newTuplet(ActionEvent actionEvent) {
        System.out.println("neu");
    }

    @FXML
    private void saveTuplet(ActionEvent actionEvent) {
        System.out.println("speichern");
    }

    @FXML
    private void deleteTuplet(ActionEvent actionEvent) {
        System.out.println("löschen");
    }

    @FXML
    private void FallSchliessen(ActionEvent actionEvent) {
        System.out.println("schließen");
    }

    @FXML
    private void newVerbrechen(ActionEvent actionEvent) {
        System.out.println("neues Verbrechen");
    }

    @FXML
    private void goToVerbrechen(ActionEvent actionEvent) {
        System.out.println("Verbrechen anzeigen");
    }

    @FXML
    private void newPolizist(ActionEvent actionEvent) {
        System.out.println("neuer Polizist");
    }

    @FXML
    private void newIndiz(ActionEvent actionEvent) {
        System.out.println("neues Indiz");
    }

    @FXML
    private void newNotiz(ActionEvent actionEvent) {
        System.out.println("neue Notiz");
    }
}