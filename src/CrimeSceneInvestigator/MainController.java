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

public class MainController extends SplitPane {

    String table;
    String attr0;
    String attr1;
    String attr2;
    String attr3;
    String attr4;
    String attr5;
    String list0table;
    String list1table;
    String list2table;
    String list3table;
    String attr0name ;
    String attr1name ;
    String attr2name ;
    String attr3name ;
    String attr4name ;
    String attr5name ;
    String list0name ;
    String list1name ;
    String list2name ;
    String list3name ;
    String val0;
    String val1;
    String val2;
    String val3;
    String val4;
    String val5;
    final SimpleDateFormat formatter = new SimpleDateFormat("dd.MM.yyyy");
    boolean isNew = false;
    final String split = ",|;";

    @FXML
    TableView<Tuplet> tableView;
    ObservableList<Tuplet> olTable;

    @FXML TextField textAttr0;
    @FXML TextField filterAttr0;
    @FXML Label labelAttr0;
    @FXML TableColumn columnAttr0;

    @FXML TextField textAttr1;
    @FXML TextField filterAttr1;
    @FXML Label labelAttr1;
    @FXML TableColumn columnAttr1;

    @FXML TextField textAttr2;
    @FXML TextField filterAttr2;
    @FXML Label labelAttr2;
    @FXML TableColumn columnAttr2;

    @FXML TextField textAttr3;
    @FXML TextField filterAttr3;
    @FXML Label labelAttr3;
    @FXML TableColumn columnAttr3;

    @FXML ListView<Tuplet> list0;
    @FXML Label labelList0;
    ObservableList<Tuplet> ol0;

    @FXML ListView<Tuplet> list1;
    @FXML Label labelList1;
    ObservableList<Tuplet> ol1;

    @FXML ListView<Tuplet> list2;
    @FXML Label labelList2;
    ObservableList<Tuplet> ol2;

    @FXML ListView<Tuplet> list3;
    @FXML Label labelList3;
    ObservableList<Tuplet> ol3;

    @FXML Button filterButton;
    @FXML Button resetButton;

    public MainController() {
    }

    void refreshTable() {
        System.out.println("refreshTable");
        olTable.clear();
        olTable.addAll(SQLController.selectFromTable(table));
        tableView.refresh();
    }
    void refreshTable(LinkedList<Filter> filterList) {
        System.out.println("refreshTable filtered");
        olTable.clear();
        olTable.addAll(SQLController.selectFromTable(table, filterList));
        tableView.refresh();
    }

    @FXML
    void reset() {
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

    void setUpFields() {
        textAttr0.setText(val0);
        textAttr1.setText(val1);
        textAttr2.setText(val2);
        textAttr3.setText(val3);
    }

    void setUpLists() {
    }

    @FXML
    void neu(ActionEvent actionEvent) {
        System.out.println("neu");
        isNew = true;
        tableView.getSelectionModel().clearSelection();
        reset();
        val2 = formatter.format(new Date());
        textAttr2.setText(val2);
    }

    @FXML
    void save(ActionEvent actionEvent) {
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
    void delete(ActionEvent actionEvent) {
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
    void filter(ActionEvent actionEvent) {
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
    void resetFilter(ActionEvent actionEvent) {
        filterAttr0.clear();
        filterAttr1.clear();
        filterAttr2.clear();
        filterAttr3.clear();
        filter(new ActionEvent());
    }

    @FXML
    void addForeign(ActionEvent actionEvent) {
        System.out.println("addForeign");
    }

    @FXML
    void goToForeign(ActionEvent actionEvent) {
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

    public static String formatDateToDMY(String date) {
        if (date != null && date.matches("\\d{4}-\\d{2}-\\d{2}")) {
            String[] splitdate = date.split("-");
            return splitdate[2]+"."+splitdate[1]+"."+splitdate[0];
        }
        return date;
    }

    public static String formatDateToYMD(String date) {
        if (date != null && date.matches("\\d{2}\\.\\d{2}\\.\\d{4}")) {
            String[] splitdate = date.split("\\.");
            return splitdate[2]+"-"+splitdate[1]+"-"+splitdate[0];
        }
        return date;
    }}