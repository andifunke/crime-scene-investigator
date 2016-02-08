package CrimeSceneInvestigator;

import CrimeSceneInvestigator.Tuplets.Tuplet;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.text.SimpleDateFormat;
import java.util.LinkedList;

public class MainController extends SplitPane {

    String table;
    String attr[];
    String listTable[];
    String attrName[];
    String val[];
    final SimpleDateFormat formatter = new SimpleDateFormat("dd.MM.yyyy");
    boolean isNew = false;
    final String split = ",|;";

    @FXML TableView<Tuplet> tableView;
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

    @FXML TextField textAttr4;
    @FXML TextField filterAttr4;
    @FXML Label labelAttr4;
    @FXML TableColumn columnAttr4;

    @FXML TextField textAttr5;
    @FXML TextField filterAttr5;
    @FXML Label labelAttr5;
    @FXML TableColumn columnAttr5;

    @FXML TextField textAttr6;
    @FXML TextField filterAttr6;
    @FXML Label labelAttr6;
    @FXML TableColumn columnAttr6;

    TextField[] textAttr;
    TextField[] filterAttr;
    Label[] labelAttr;
    TableColumn[] columnAttr;

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

    ListView[] listView;
    Label[] labelList;
    ObservableList[] ol;


    @FXML Button filterButton;
    @FXML Button resetButton;

    public MainController() {
    }

    void refreshTable() {
        olTable.clear();
        olTable.addAll(SQLController.selectFromTable(table));
        tableView.refresh();
        setUpPicture();
    }
    void refreshTable(LinkedList<Filter> filterList) {
        olTable.clear();
        olTable.addAll(SQLController.selectFromTable(table, filterList));
        tableView.refresh();
        setUpPicture();
    }

    @FXML
    void reset() {
        for (int i=0; i<attr.length; i++)
            val[i] = "";
        setUpFields();
        setUpLists();
        setUpPicture();
    }

    void setUpPicture() {

    }

    void setUpFields() {
        for (int i = 0; i < attr.length; i++)
            if (textAttr[i] != null)
                textAttr[i].setText(val[i]);
    }

    void setUpLists() {
    }

    @FXML
    void neu(ActionEvent actionEvent) {
        neuDefault();
    }
    void neuDefault() {
        isNew = true;
        tableView.getSelectionModel().clearSelection();
        reset();
    }

    @FXML
    void save(ActionEvent actionEvent) {
    }

    @FXML
    void delete(ActionEvent actionEvent) {
        int index = tableView.getSelectionModel().getSelectedIndex();
        if (index > -1) {
            try {
                SQLController.delete(table, attr[0], val[0]);
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

        String[] filters = new String[attr.length];
        for (int i=0; i<attr.length; i++) {
            filters[i] = filterAttr[i].getText().trim();
        }
        for (int i=0; i<attr.length; i++) {
            if (!filters[i].equals("")) {
                boolean strict = false;
                if (attr[i].matches("ID")) {
                    strict = strictIdFilterPolicy;
                }
                filterList.add(new Filter(table, attr[i], filters[i], strict));
            }
        }
        if (!filterList.isEmpty()) {
            refreshTable(filterList);
        }
        else {
            refreshTable();
        }
        tableView.getSelectionModel().clearSelection();
        tableView.getSelectionModel().selectFirst();
    }
    boolean strictIdFilterPolicy = true;

    @FXML
    void resetFilter(ActionEvent actionEvent) {
        for (int i=0; i<attr.length; i++)
            filterAttr[i].clear();
        filter(new ActionEvent());
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
    }

}