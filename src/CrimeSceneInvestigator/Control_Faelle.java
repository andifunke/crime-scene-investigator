package CrimeSceneInvestigator;

import CrimeSceneInvestigator.Tuplets.Tuplet;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;

import java.io.IOException;
import java.util.LinkedList;

public class Control_Faelle extends SplitPane {

    @FXML
    private TableView<Tuplet> Table;
    private ObservableList<Tuplet> olTable;

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
    private TextField TextEroeffnungsdatum;
    @FXML
    private TextField FilterEroeffnungsdatum;
    @FXML
    private TableColumn ColumnEroeffnungsdatum;

    @FXML
    private TextField TextEnddatum;
    @FXML
    private TextField FilterEnddatum;
    @FXML
    private TableColumn ColumnEnddatum;

    @FXML
    private ListView<Tuplet> ListVerbrechen;
    @FXML
    private TextField FilterVerbrechen;
    private ObservableList<Tuplet> olVerbrechen;

    @FXML
    private ListView ListNotizen;
    @FXML
    private TextField FilterNotizen;
    private ObservableList<Tuplet> olNotizen;

    @FXML
    private ListView ListIndizien;
    @FXML
    private TextField FilterIndizien;
    private ObservableList<Tuplet> olIndizien;

    @FXML
    private ListView ListPolizisten;
    @FXML
    private TextField FilterPolizisten;
    private ObservableList<Tuplet> olPolizisten;

    private LinkedList<Filter> filterVerbrechen;

    public Control_Faelle() {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("FXML/Faelle.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);

        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
        olTable = SQLController.getTable("faelle");
        Table.setItems(olTable);
        Table.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Tuplet>() {
            @Override
            public void changed(ObservableValue<? extends Tuplet> observable,
                                Tuplet oldValue, Tuplet newValue) {

                TextFallID.setText(newValue.getValue("fallid"));
                TextName.setText(newValue.getValue("name"));
                TextEroeffnungsdatum.setText(newValue.getValue("eroeffnungsdatum"));
                TextEnddatum.setText(newValue.getValue("enddatum"));
                Filter filterVerbrechen = new Filter("fallid", newValue.getValue("fallid"));
                olVerbrechen = SQLController.getTable("verbrechen", filterVerbrechen);
                ListVerbrechen.setItems(olVerbrechen);

            }
        });
    }

    @FXML
    private void tableSelect(EventHandler event) {
        System.out.println("click on table faelle");
    }

    @FXML
    private void neu(ActionEvent actionEvent) {
        System.out.println("neu");
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