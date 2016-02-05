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

    private final String table = Faelle.table;
    private final String attr0 = Faelle.attr[0];
    private final String attr1 = Faelle.attr[1];
    private final String attr2 = Faelle.attr[2];
    private final String attr3 = Faelle.attr[3];
    private final String attr4 = "";
    private final String attr5 = "";
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
    @FXML private Button resetButton;
    private int counter = 0;

    public Control_Faelle() {
        System.out.println("constructor");
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
        filterAttr0.setPromptText("nach '"+attr0name+"' filtern");
        filterAttr1.setPromptText("nach '"+attr1name+"' filtern");
        filterAttr2.setPromptText("nach '"+attr2name+"' filtern");
        filterAttr3.setPromptText("nach '"+attr3name+"' filtern");
        textAttr0.setPromptText("* Pflichtfeld - wird automatisch generiert");
        textAttr1.setPromptText("* Pflichtfeld");
        textAttr2.setPromptText("* Pflichtfeld");
        System.out.println("setUpDefaultTable");
        olTable = SQLController.selectFromTable(table);
        System.out.println("setUpFilteredTable");
        tableView.setItems(olTable);
        tableView.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                System.out.println("newValue != null");
                isNew = false;
                val0 = newValue.getVal0();
                val1 = newValue.getVal1();
                val2 = newValue.getVal2();
                val3 = newValue.getVal3();
                setUpFields();
                setUpLists();
            }
            else {
                System.out.println("newValue == null");
                reset();
            }
        });
        tableView.getSelectionModel().selectFirst();
    }

    private void refreshTable() {
        System.out.println("refreshTable");
        olTable.clear();
        olTable.addAll(SQLController.selectFromTable(table));
        tableView.refresh();
    }
    private void refreshTable(LinkedList<Filter> filterList) {
        System.out.println("refreshTable filtered");
        olTable.clear();
        olTable.addAll(SQLController.selectFromTable(table, filterList));
        tableView.refresh();
    }

    @FXML
    private void reset() {
        System.out.println("reset");
        val0 = "";
        val1 = "";
        val2 = "";
        val3 = "";
        val4 = "";
        val5 = "";
        setUpFields();
        setUpLists();
    }

    private void setUpFields() {
        System.out.println("setUpFields");
        textAttr0.setText(val0);
        textAttr1.setText(val1);
        textAttr2.setText(val2);
        textAttr3.setText(val3);
    }

    private void setUpLists() {
        System.out.println("setUpLists");
        if (!val0.equals("")) {
            Filter filter = new Filter(table, attr0, val0, true);
            ol0 = SQLController.selectFromTable(list0table, filter);
            list0.setItems(ol0);
            ol1 = SQLController.selectFromTable(list1table, filter);
            list1.setItems(ol1);
            ol2 = SQLController.selectFromTable(list2table, filter);
            list2.setItems(ol2);
            String query =
                        "SELECT Polizisten.PersonID,Dienstgrad,Name,Geschlecht,Nationalitaet,Geburtsdatum,Todesdatum\n" +
                        "  FROM Polizisten,Personen,arbeitetan\n" +
                        "  WHERE Polizisten.PersonID = Personen.PersonID\n" +
                        "  AND Polizisten.PersonID = arbeitetan.PersonID\n" +
                              "  AND arbeitetan.FallID = " + val0 + ";";
            //ol3 = SQLController.selectFromQuery(list3table, query);
            //list3.setItems(ol3);
        }
    }

    @FXML
    private void neu(ActionEvent actionEvent) {
        System.out.println("neu");
        isNew = true;
        tableView.getSelectionModel().clearSelection();
        reset();
        val2 = formatter.format(new Date());
        textAttr2.setText(val2);
    }

    @FXML
    private void save(ActionEvent actionEvent) {
        System.out.println("save");
        String[] keys = new String[10];
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
            return;
        }
        if (val2.equals("")) {
            System.out.println("kein "+attr2+" eingetragen");
            return;
        }
        if (isNew) {
            String[] val = { val0, val1, val2, val3 };
            Tuplet tuplet = new Faelle(val);
            SQLController.insert(tuplet);
            resetFilter(new ActionEvent());
            tableView.getSelectionModel().clearSelection();
            tableView.getSelectionModel().selectLast();
            int last = tableView.getSelectionModel().getSelectedIndex();
            tableView.scrollTo(last);
            isNew = false;
        }
        else {
            Tuplet tuplet = tableView.getSelectionModel().getSelectedItem();
            int index = tableView.getSelectionModel().getSelectedIndex();
            tuplet.setVal0(val0);
            tuplet.setVal1(val1);
            tuplet.setVal2(val2);
            tuplet.setVal3(val3);
            SQLController.update(tuplet, keys);
            filter(new ActionEvent());
            tableView.getSelectionModel().clearAndSelect(index);
        }
    }

    @FXML
    private void delete(ActionEvent actionEvent) {
        System.out.println("delete");
        int index = tableView.getSelectionModel().getSelectedIndex();
        System.out.println("index: "+index);
        if (index > -1) {
            try {
                SQLController.delete(table, attr0, val0);
                refreshTable();
                tableView.getSelectionModel().clearAndSelect(index-1);
            } catch (NullPointerException e) {
                System.out.println("keine Einträge vorhanden");
            }
        }
    }

    @FXML
    private void filter(ActionEvent actionEvent) {
        System.out.println("filter "+counter++);
        LinkedList<Filter> filterList = new LinkedList<>();

        String filter0 = filterAttr0.getText().trim();
        String filter1 = filterAttr1.getText().trim();
        String filter2 = filterAttr2.getText().trim();
        String filter3 = filterAttr3.getText().trim();
        if (!filter0.equals("")) filterList.add(new Filter(table, attr0, filter0, true));
        if (!filter1.equals("")) filterList.add(new Filter(table, attr1, filter1));
        if (!filter2.equals("")) filterList.add(new Filter(table, attr2, filter2));
        if (!filter3.equals("")) filterList.add(new Filter(table, attr3, filter3));
        for (Filter filter : filterList) {
            System.out.println(filter.getTable()+", "+filter.getAttribute()+", "+filter.getValue());
        }
        if (!filterList.isEmpty()) {
            System.out.println("filterlist not empty");
            refreshTable(filterList);
            tableView.getSelectionModel().clearSelection();
            tableView.getSelectionModel().selectFirst();
        }
        else {
            System.out.println("filterlist empty");
            refreshTable();
            tableView.getSelectionModel().clearSelection();
            tableView.getSelectionModel().selectFirst();
        }
    }
    @FXML
    private void resetFilter(ActionEvent actionEvent) {
        filterAttr0.clear();
        filterAttr1.clear();
        filterAttr2.clear();
        filterAttr3.clear();
        filter(new ActionEvent());
    }

    @FXML
    private void FallSchliessen(ActionEvent actionEvent) {
        System.out.println("FallSchliessen");
        val3 = formatter.format(new Date());
        textAttr3.setText(val3);
        save(new ActionEvent());
    }

    @FXML
    private void addForeign(ActionEvent actionEvent) {
        System.out.println("addForeign");
    }

    @FXML
    private void goToForeign(ActionEvent actionEvent) {
        System.out.println("goToForeign");
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