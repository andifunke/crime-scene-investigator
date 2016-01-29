package DataBaseApplication;

import javafx.beans.InvalidationListener;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.util.*;

public class FXMLController {

    @FXML
    private TableView TableFaelle;
    @FXML
    private TableColumn ColumnFaelleFallID;
    @FXML
    private TableColumn ColumnFaelleName;
    @FXML
    private TableColumn ColumnFaelleEroeffnungsdatum;
    @FXML
    private TableColumn ColumnFaelleEnddatum;
    private ObservableList<Fall> olFaelle;

    @FXML
    private TextField FaelleFallID;
    @FXML
    private TextField FilterFaelleFallID;

    @FXML
    private TextField FaelleName;
    @FXML
    private TextField FilterFaelleName;

    @FXML
    private DatePicker FaelleEroeffnungsdatum;
    @FXML
    private TextField FilterFaelleEroeffnungsdatum;

    @FXML
    private DatePicker FaelleEnddatum;
    @FXML
    private TextField FilterFaelleEnddatum;

    @FXML
    private ListView<String> FaelleVerbrechen;
    private ObservableList<String> olFaelleVerbrechen;
    @FXML
    private TextField FilterFaelleVerbrechen;

    @FXML
    private ListView FaelleNotizen;
    private ObservableList<String> olFaelleNotizen;
    @FXML
    private TextField FilterFaelleNotizen;

    @FXML
    private ListView FaelleIndizien;
    private ObservableList<String> olFaelleIndizien;
    @FXML
    private TextField FilterFaelleIndizien;

    @FXML
    private ListView FaellePolizisten;
    private ObservableList<String> olFaellePolizisten;
    @FXML
    private TextField FilterFaellePolizisten;

    @FXML
    private void onActionFilterFallID(ActionEvent actionEvent) {
        String filterText = FilterFaelleFallID.getText();
        FaelleFallID.setText(filterText);
    }

    @FXML
    private void newTuplet(ActionEvent actionEvent) {
        System.out.println("neu");
        FaelleFallID.setText("text");
        olFaelleVerbrechen = FXCollections.observableArrayList (
                "Single", "Double", "Suite", "Family App");
        FaelleVerbrechen.setItems(olFaelleVerbrechen);
        //TableFaelle.setPlaceholder(new Node());
        ColumnFaelleFallID.setCellValueFactory(new PropertyValueFactory<Fall,Integer>("fallID"));
        ColumnFaelleName.setCellValueFactory(new PropertyValueFactory<Fall,String>("name"));
        ColumnFaelleEroeffnungsdatum.setCellValueFactory(new PropertyValueFactory<Fall,String>("eroeffnungsdatum"));
        ColumnFaelleEnddatum.setCellValueFactory(new PropertyValueFactory<Fall,String>("enddatum"));
        olFaelle = FXCollections.observableArrayList(
                new Fall(123,"Blubb3","2016-12-01","2016-12-12"),
                new Fall(124,"Blubb4","2016-12-01","2016-12-12"),
                new Fall(125,"Blubb5","2016-12-01","2016-12-12"),
                new Fall(126,"Blubb6","2016-12-01","2016-12-12"),
                new Fall(127,"Blubb7","2016-12-01","2016-12-12"),
                new Fall(128,"Blubb8","2016-12-01","2016-12-12"),
                new Fall(129,"Blubb9","2016-12-01","2016-12-12"),
                new Fall(123,"Blubb","2016-12-01","2016-12-12"),
                new Fall(123,"Blubb","2016-12-01","2016-12-12"),
                new Fall(123,"Blubb","2016-12-01","2016-12-12"),
                new Fall(123,"Blubb","2016-12-01","2016-12-12"),
                new Fall(123,"Blubb","2016-12-01","2016-12-12")
        );
        TableFaelle.setItems(olFaelle);
//        TableFaelle.getColumns().addAll(ColumnFaelleFallID, ColumnFaelleName, ColumnFaelleEroeffnungsdatum, ColumnFaelleEnddatum);
    }

    @FXML
    private void saveTuplet(ActionEvent actionEvent) {
        System.out.println("speichern");
        try {
            if (!FaelleVerbrechen.getSelectionModel().isEmpty()) {
                String x = FaelleVerbrechen.getSelectionModel().getSelectedItem().toString();
                FaelleName.setText(x);
            }
        } catch (NullPointerException e) {}
    }

    @FXML
    private void deleteTuplet(ActionEvent actionEvent) {
        System.out.println("löschen");
        FaelleFallID.setText("");
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

    public static class Fall {

        private final SimpleIntegerProperty fallID;
        private final SimpleStringProperty name;
        private final SimpleStringProperty eroeffnungsdatum;
        private final SimpleStringProperty enddatum;

        private Fall(int fallID, String name, String eroeffnungsdatum, String enddatum) {
            this.fallID = new SimpleIntegerProperty(fallID);
            this.name = new SimpleStringProperty(name);
            this.eroeffnungsdatum = new SimpleStringProperty(eroeffnungsdatum);
            this.enddatum = new SimpleStringProperty(enddatum);
        }

        public int getFallID() {
            return fallID.get();
        }

        public void setFallID(int fallID) {
            this.fallID.set(fallID);
        }

        public String getName() {
            return name.get();
        }

        public void setName(String name) {
            this.name.set(name);
        }

        public String getEroeffnungsdatum() {
            return eroeffnungsdatum.get();
        }

        public void setEroeffnungsdatum(String eroeffnungsdatum) {
            this.eroeffnungsdatum.set(eroeffnungsdatum);
        }

        public String getEnddatum() {
            return enddatum.get();
        }

        public void setEnddatum(String enddatum) {
            this.enddatum.set(enddatum);
        }
    }
}

