package CrimeSceneInvestigator;

import CrimeSceneInvestigator.Tuplets.Faelle;
import CrimeSceneInvestigator.Tuplets.Tuplet;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;

public class Control_Faelle extends SplitPane {

    private final String table = Faelle.attr[6];
    private final String attr0 = Faelle.attr[0];
    private final String attr1 = Faelle.attr[1];
    private final String attr2 = Faelle.attr[2];
    private final String attr3 = Faelle.attr[3];
    private final String attr4 = Faelle.attr[4];
    private final String attr5 = Faelle.attr[5];
    private final String list0table = "Verbrechen";
    private final String list1table = "Notizen";
    private final String list2table = "Indizien";
    private final String list3table = "Polizisten";
    private final String attr0name = "ID";
    private final String attr1name = attr1;
    private final String attr2name = "eröffnet";
    private final String attr3name = "geschlossen";
    private final String attr4name = "";
    private final String attr5name = "";
    private final String list0name = list0table;
    private final String list1name = list1table;
    private final String list2name = list2table;
    private final String list3name = list3table;
    private String val0 = "";
    private String val1 = "";
    private String val2 = "";
    private String val3 = "";
    private String val4 = "";
    private String val5 = "";
    private SimpleDateFormat formatter = new SimpleDateFormat("dd.MM.yyyy");
    private boolean isNew = false;
    private final String split = ",|;";

    @FXML private TableView<Tuplet> tableView;
    private ObservableList<Tuplet> olTable;

    @FXML private TextField textAttr0;
    @FXML private TextField filterAttr0;
    @FXML private Label labelAttr0;
    @FXML private TableColumn columnAttr0;

    @FXML private TextField textAttr1;
    @FXML private TextField filterAttr1;
    @FXML private Label labelAttr1;
    @FXML private TableColumn columnAttr1;

    @FXML private TextField textAttr2;
    @FXML private TextField filterAttr2;
    @FXML private Label labelAttr2;
    @FXML private TableColumn columnAttr2;

    @FXML private TextField textAttr3;
    @FXML private TextField filterAttr3;
    @FXML private Label labelAttr3;
    @FXML private TableColumn columnAttr3;

    @FXML private ListView<Tuplet> list0;
//    @FXML private TextField filterList0;
    @FXML private Label labelList0;
    private ObservableList<Tuplet> ol0;

    @FXML private ListView<Tuplet> list1;
//    @FXML private TextField filterList1;
    @FXML private Label labelList1;
    private ObservableList<Tuplet> ol1;

    @FXML private ListView<Tuplet> list2;
//    @FXML private TextField filterList2;
    @FXML private Label labelList2;
    private ObservableList<Tuplet> ol2;

    @FXML private ListView<Tuplet> list3;
//    @FXML private TextField filterList3;
    @FXML private Label labelList3;
    private ObservableList<Tuplet> ol3;

    @FXML private Button filterButton;
    private int counter = 0;

    public Control_Faelle() {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("FXML/Faelle.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);
        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
        labelAttr0.setText(attr0name);
        labelAttr1.setText(attr1name);
        labelAttr2.setText(attr2name);
        labelAttr3.setText(attr3name);
        labelList0.setText(list0name);
        labelList1.setText(list1name);
        labelList2.setText(list2name);
        labelList3.setText(list3name);
        columnAttr0.setText(attr0name);
        columnAttr1.setText(attr1name);
        columnAttr2.setText(attr2name);
        columnAttr3.setText(attr3name);
        setUpDefaultTable();
        tableView.getSelectionModel().select(0);
    }

    private void setUpDefaultTable() {
        olTable = SQLController.getTable(table);
        setUpFilteredTable();
    }
    private void setUpFilteredTable() {
        tableView.setItems(olTable);
        tableView.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                isNew = false;
                val0 = newValue.getVal0();
                val1 = newValue.getVal1();
                val2 = newValue.getVal2();
                val3 = newValue.getVal3();
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
        val4 = "";
        val5 = "";
        setUpFields();
        setUpLists();
    }

    private void setUpLists() {
        Filter filter = new Filter(table, attr0, val0);
        ol0 = SQLController.getTable(list0table, filter);
        list0.setItems(ol0);
        ol1 = SQLController.getTable(list1table, filter);
        list1.setItems(ol1);
        ol2 = SQLController.getTable(list2table, filter);
        list2.setItems(ol2);
        //                    ol3 = SQLController.getTable(list3table, filter);
        //                    list3.setItems(ol3);
    }

    private void setUpFields() {
        textAttr0.setText(val0);
        textAttr1.setText(val1);
        textAttr2.setText(val2);
        textAttr3.setText(val3);
    }

    @FXML
    private void neu(ActionEvent actionEvent) {
        isNew = true;
        tableView.getSelectionModel().clearSelection();
        reset();
        setUpIDMessage();
        val2 = formatter.format(new Date());
        setUpFields();
    }

    @FXML
    private void save(ActionEvent actionEvent) {
        String[] keys = new String[6];
        keys[0] = val0;
        val0 = textAttr0.getText();
        val1 = textAttr1.getText();
        val2 = textAttr2.getText();
        val3 = textAttr3.getText();
        try {
            int x = Integer.parseInt(val0);
        }
        catch (NumberFormatException e) {
            System.out.println(attr0+" ist kein Integer");
            try {
                int x = Integer.parseInt(keys[0]);
                val0 = keys[0];
                textAttr0.setText(val0);
            }
            catch (NumberFormatException e2) {
                val0 = "";
                textAttr0.setText(val0);
            }
        }
        if (val1.equals("")) {
            System.out.println("kein "+attr1+" eingetragen");
            setUpIDMessage();
            return;
        }
        if (val2.equals("")) {
            System.out.println("kein "+attr2+" eingetragen");
            setUpIDMessage();
            return;
        }
        if (isNew) {
            Tuplet tuplet = new Faelle(val0, val1, val2, val3);
            SQLController.insert(tuplet);
            setUpDefaultTable();
//            olTable.add(tuplet);
            // TODO: sollte nicht gespeichert werden, wenn keine Integer-ID, bzw. ID aus Datenbank auslesen
            tableView.getSelectionModel().selectLast();
            isNew = false;
        }
        else {
            Tuplet tuplet = tableView.getSelectionModel().getSelectedItem();
            tuplet.setVal0(val0);
            tuplet.setVal1(val1);
            tuplet.setVal2(val2);
            tuplet.setVal3(val3);
            SQLController.update(tuplet, keys);
            setUpFilteredTable();
            tableView.getSelectionModel().select(0);
//            tableView.refresh();
        }
    }

    private void setUpIDMessage() {
        if (isNew) {
            val0 = "wird automatisch generiert";
            textAttr0.setText(val0);
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
                setUpFilteredTable();
                tableView.getSelectionModel().select(0);
            } catch (NullPointerException e) {
                System.out.println("keine Einträge vorhanden");
            }
        }
    }

    @FXML
    private void filter(ActionEvent actionEvent) {
        System.out.println(++counter);
        LinkedList<Filter> filterList = new LinkedList<>();
        String filter0[] = filterAttr0.getText().split(split);
        String filter1[] = filterAttr1.getText().split(split);
        String filter2[] = filterAttr2.getText().split(split);
        String filter3[] = filterAttr3.getText().split(split);
//        String listFilter0[] = filterList0.getText().split(split);
//        String listFilter1[] = filterList1.getText().split(split);
//        String listFilter2[] = filterList2.getText().split(split);
//        String listFilter3[] = filterList3.getText().split(split);
        for (String filter : filter0) {
            filter = filter.trim();
            if (!filter.equals("")) {
                filterList.add(new Filter(table, attr0, filter));
            }
        }
        for (String filter : filter1) {
            filter = filter.trim();
            if (!filter.equals("")) {
                filterList.add(new Filter(table, attr1, filter));
            }
        }
        for (String filter : filter2) {
            filter = filter.trim();
            if (!filter.equals("")) {
                filterList.add(new Filter(table, attr2, filter));
            }
        }
        for (String filter : filter3) {
            filter = filter.trim();
            if (!filter.equals("")) {
                filterList.add(new Filter(table, attr3, filter));
            }
        }
/*        for (String filter : listFilter0) {
            filter = filter.trim();
            if (!filter.equals("")) {
                filterList.add(new Filter(list0table, "Name", filter.trim()));
            }
        }
        for (String filter : listFilter1) {
            filter = filter.trim();
            if (!filter.equals("")) {
                filterList.add(new Filter(list1table, "Text", filter.trim()));
            }
        }
        for (String filter : listFilter2) {
            filter = filter.trim();
            if (!filter.equals("")) {
                filterList.add(new Filter(list2table, "Text", filter.trim()));
            }
        }
        for (String filter : listFilter3) {
            filter = filter.trim();
            if (!filter.equals("")) {
                filterList.add(new Filter(list3table, "Name", filter.trim()));
            }
        }
*/
        for (Filter filter : filterList) {
            System.out.println(filter.getTable()+", "+filter.getAttribute()+", "+filter.getValue());
        }
        if (!filterList.isEmpty()) {
            olTable = SQLController.getTable(table, filterList);
            setUpFilteredTable();
            tableView.getSelectionModel().select(0);
        }
        else {
            setUpDefaultTable();
            tableView.getSelectionModel().select(0);
        }
    }

    @FXML
    private void FallSchliessen(ActionEvent actionEvent) {
        val3 = formatter.format(new Date());
        textAttr3.setText(val3);
        save(new ActionEvent());
    }

    @FXML
    private void addForeign(ActionEvent actionEvent) {
        System.out.println("hinzufügen");
    }

    @FXML
    private void goToForeign(ActionEvent actionEvent) {
        System.out.println("anzeigen");
        if (actionEvent.getSource() == filterAttr0) {
            String filter = filterAttr0.getText();
            textAttr0.setText(filter);
        }
        else if (actionEvent.getSource() == filterAttr1) {
            String filter = filterAttr1.getText();
            textAttr1.setText(filter);
        }
    }

}