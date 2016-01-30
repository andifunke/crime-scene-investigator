package CrimeSceneInvestigator;

import CrimeSceneInvestigator.CrimeSceneInvestigator;
import CrimeSceneInvestigator.Tuplets.Fall;
import CrimeSceneInvestigator.Tuplets.Tuplet;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;

import java.io.IOException;

public class Control_Faelle extends SplitPane {

    @FXML
    private TableView Table;
    private ObservableList<Tuplet> olFaelle;

    @FXML
    private TextField TextFallID;
    @FXML
    private TextField FilterFallID;
    @FXML
    private TableColumn ColumnFallID;

    @FXML
    private TextField TextName;
    @FXML
    private TextField FilterName;
    @FXML
    private TableColumn ColumnName;

    @FXML
    private DatePicker TextEroeffnungsdatum;
    @FXML
    private TextField FilterEroeffnungsdatum;
    @FXML
    private TableColumn ColumnEroeffnungsdatum;

    @FXML
    private DatePicker TextEnddatum;
    @FXML
    private TextField FilterEnddatum;
    @FXML
    private TableColumn ColumnEnddatum;

    @FXML
    private ListView<String> ListVerbrechen;
    @FXML
    private TextField FilterVerbrechen;
    private ObservableList<String> olVerbrechen;

    @FXML
    private ListView ListNotizen;
    @FXML
    private TextField FilterNotizen;
    private ObservableList<String> olNotizen;

    @FXML
    private ListView ListIndizien;
    @FXML
    private TextField FilterIndizien;
    private ObservableList<String> olIndizien;

    @FXML
    private ListView ListPolizisten;
    @FXML
    private TextField FilterPolizisten;
    private ObservableList<String> olPolizisten;

    public Control_Faelle() {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("FXML/Faelle.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);

        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
        olFaelle = SQLController.getTable("Faelle");
        Table.setItems(olFaelle);
    }

    @FXML
    private void neu(ActionEvent actionEvent) {
//        olVerbrechen = SQLController.getTable('Faelle');
//        olVerbrechen = FXCollections.observableArrayList (
//                "Single", "Double", "Suite", "Family App");
//        ListVerbrechen.setItems(olVerbrechen);
        System.out.println("neu");
        TextFallID.setText("text");
    }

    @FXML
    private void save(ActionEvent actionEvent) {
        System.out.println("speichern");
        try {
            if (!ListVerbrechen.getSelectionModel().isEmpty()) {
                String x = ListVerbrechen.getSelectionModel().getSelectedItem().toString();
                TextName.setText(x);
            }
        } catch (NullPointerException e) {}
    }

    @FXML
    private void delete(ActionEvent actionEvent) {
        System.out.println("löschen");
        TextFallID.setText("");
    }

    @FXML
    private void filter(ActionEvent actionEvent) {
        if (actionEvent.getSource()==FilterFallID) {
            String filter = FilterFallID.getText();
            TextFallID.setText(filter);
        }
        else if (actionEvent.getSource()==FilterName) {
            String filter = FilterName.getText();
            TextName.setText(filter);
        }
    }

    @FXML
    private void FallSchliessen(ActionEvent actionEvent) {
        System.out.println("schließen");
    }

    @FXML
    private void addForeign(ActionEvent actionEvent) {
        System.out.println("hinzufügen");
    }

    @FXML
    private void goToForeign(ActionEvent actionEvent) {
        System.out.println("anzeigen");
    }

}