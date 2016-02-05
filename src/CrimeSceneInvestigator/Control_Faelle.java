package CrimeSceneInvestigator;

import CrimeSceneInvestigator.Tuplets.Faelle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import java.io.IOException;
import java.util.Date;

public class Control_Faelle extends MainController {

    public Control_Faelle() {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("FXML/Faelle.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);
        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
        table = Faelle.table;
        attr0 = Faelle.attr[0];
        attr1 = Faelle.attr[1];
        attr2 = Faelle.attr[2];
        attr3 = Faelle.attr[3];
        attr4 = "";
        attr5 = "";
        list0table = "Verbrechen";
        list1table = "Notizen";
        list2table = "Indizien";
        list3table = "Polizisten";
        attr0name = "ID";
        attr1name = attr1;
        attr2name = "eröffnet";
        attr3name = "geschlossen";
        attr4name = "";
        attr5name = "";
        list0name = list0table;
        list1name = list1table;
        list2name = list2table;
        list3name = list3table;
        val0 = "";
        val1 = "";
        val2 = "";
        val3 = "";
        val4 = "";
        val5 = "";
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
//                System.out.println("newValue != null");
                isNew = false;
                val0 = newValue.getVal0();
                val1 = newValue.getVal1();
                val2 = newValue.getVal2();
                val3 = newValue.getVal3();
                setUpFields();
                setUpLists();
            }
            else {
//                System.out.println("newValue == null");
                reset();
            }
        });
        tableView.getSelectionModel().selectFirst();
    }

    void setUpLists() {
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
    void FallSchliessen(ActionEvent actionEvent) {
        val3 = formatter.format(new Date());
        textAttr3.setText(val3);
        save(new ActionEvent());
    }

}