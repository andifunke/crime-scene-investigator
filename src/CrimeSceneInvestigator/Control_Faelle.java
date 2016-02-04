package CrimeSceneInvestigator;

import CrimeSceneInvestigator.Tuplets.Faelle;
import CrimeSceneInvestigator.Tuplets.Tuplet;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.jar.Attributes;

public class Control_Faelle extends SplitPane {

    private final String table = "Faelle";
    private final String attr0 = "FallID";
    private final String attr1 = "Name";
    private final String attr2 = "Eroeffnungsdatum";
    private final String attr3 = "Enddatum";
    private final String list0 = "Verbrechen";
    private final String list1 = "Notizen";
    private final String list2 = "Indizien";
    private final String list3 = "Polizisten";
    private String val0 = "";
    private String val1 = "";
    private String val2 = "";
    private String val3 = "";
    private String val4 = "";
    private String val5 = "";
    private SimpleDateFormat formatter = new SimpleDateFormat("dd.MM.yyyy");
    boolean isNew = false;

    @FXML private TableView<Tuplet> tableView;
    private ObservableList<Tuplet> olTable;

    @FXML private TextField TextFallID;
    @FXML private TextField FilterFallID;
    @FXML private TableColumn ColumnFallID;

    @FXML private TextField TextName;
    @FXML private TextField FilterName;
    @FXML private TableColumn ColumnName;

    @FXML private TextField TextEroeffnungsdatum;
    @FXML private TextField FilterEroeffnungsdatum;
    @FXML private TableColumn ColumnEroeffnungsdatum;

    @FXML private TextField TextEnddatum;
    @FXML private TextField FilterEnddatum;
    @FXML private TableColumn ColumnEnddatum;

    @FXML private ListView<Tuplet> ListVerbrechen;
    @FXML private TextField FilterVerbrechen;
    private ObservableList<Tuplet> ol0;

    @FXML private ListView<Tuplet> ListNotizen;
    @FXML private TextField FilterNotizen;
    private ObservableList<Tuplet> ol1;

    @FXML private ListView<Tuplet> ListIndizien;
    @FXML private TextField FilterIndizien;
    private ObservableList<Tuplet> ol2;

    @FXML private ListView<Tuplet> ListPolizisten;
    @FXML private TextField FilterPolizisten;
    private ObservableList<Tuplet> ol3;

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
        setUpTable();
        tableView.getSelectionModel().select(0);
    }

    private void setUpTable() {
        olTable = SQLController.getTable(table);
        tableView.setItems(olTable);
        tableView.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
				if (newValue != null) {
                isNew = false;
					 val0 = newValue.getValue(attr0);
					 val1 = newValue.getValue(attr1);
					 val2 = newValue.getValue(attr2);
					 val3 = newValue.getValue(attr3);
					 setUpFields();
					 setUpLists();
				}
				else {
					 reset();
				}
		  });
    }

    private void reset() {
        val0 = "";
        val1 = "";
        val2 = "";
        val3 = "";
        setUpFields();
        setUpLists();
    }

    private void setUpLists() {
        Filter filter = new Filter(attr0, val0);
        ol0 = SQLController.getTable(list0, filter);
        ListVerbrechen.setItems(ol0);
        ol1 = SQLController.getTable(list1, filter);
        ListNotizen.setItems(ol1);
        ol2 = SQLController.getTable(list2, filter);
        ListIndizien.setItems(ol2);
        //                    ol3 = SQLController.getTable(list3, filter);
        //                    ListPolizisten.setItems(ol3);
    }

    private void setUpFields() {
        TextFallID.setText(val0);
        TextName.setText(val1);
        TextEroeffnungsdatum.setText(val2);
        TextEnddatum.setText(val3);
    }

    @FXML
    private void tableSelect(EventHandler event) {
        System.out.println("click on table faelle");
    }

    @FXML
    private void neu(ActionEvent actionEvent) {
        isNew = true;
        tableView.getSelectionModel().clearSelection();
        reset();
        val0 = "wird automatisch generiert";
        val2 = formatter.format(new Date());
        setUpFields();
    }

    @FXML
    private void save(ActionEvent actionEvent) {
        String val0old = val0;
        val0 = TextFallID.getText();
        val1 = TextName.getText();
        val2 = TextEroeffnungsdatum.getText();
        val3 = TextEnddatum.getText();
        try {
            int x = Integer.parseInt(val0);
        }
        catch (NumberFormatException e) {
            System.out.println("kein Integer");
            try {
                int x = Integer.parseInt(val0old);
                val0 = val0old;
                TextFallID.setText(val0);
            }
            catch (NumberFormatException e2) {
                System.out.println("kein Integer");
                val0 = "";
                TextFallID.setText(val0);
            }
        }
        if (val1.equals("")) {
            System.out.println("kein "+attr1+" eingetragen");
            return;
        }
        if (val2.equals("")) {
            System.out.println("kein "+attr2+" eingetragen");
            return;
        }
        if (isNew) {
            Tuplet tuplet = new Faelle(val0, val1, val2, val3);
            olTable.add(tuplet);
            // TODO: sollte nicht gespeichert werden, wenn keine Integer-ID, bzw. ID aus Datenbank auslesen
            tableView.getSelectionModel().selectLast();
            isNew = false;
        }
        else {
            Tuplet tuplet = tableView.getSelectionModel().getSelectedItem();
            tuplet.setAttr0(val0);
            tuplet.setAttr1(val1);
            tuplet.setAttr2(val2);
            tuplet.setAttr3(val3);
            //SQLController.update(table, tuplet);
            tableView.refresh();
        }
    }

    @FXML
    private void delete(ActionEvent actionEvent) {
        int index = tableView.getSelectionModel().getSelectedIndex();
        if (index > -1) {
            try {
                Tuplet deletedTuplet = (Tuplet) tableView.getItems().get(index);
                String attr = attr0;
                String val = val0;
                olTable.remove(deletedTuplet);
                SQLController.delete(table, attr, val);
                setUpTable();
            } catch (NullPointerException e) {
                System.out.println("keine Einträge vorhanden");
            }
        }
    }

    @FXML
    private void filter(ActionEvent actionEvent) {
            if (actionEvent.getSource() == FilterFallID) {
                String filter = FilterFallID.getText();
                TextFallID.setText(filter);
            }
            else if (actionEvent.getSource() == FilterName) {
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