package CrimeSceneInvestigator;

import CrimeSceneInvestigator.Tuplets.Indizien;
import CrimeSceneInvestigator.Tuplets.Tuplet;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Date;

public class Control_Indizien extends MainController {

    public static Control_Indizien controlMe;

    //ImageView imageView;
    //Image image;
    @FXML
    VBox imgBox;
    Image img;
    ImageView imgView;
    InputStream IS;
    String imgURL;
    Button addImg;
    String imgPath="img/";
    Label fehlerLabel;

    public Control_Indizien() {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("FXML/Indizien.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);
        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }

        controlMe = this;

        table = Indizien.table;

        attr = new String[Indizien.attr.length];
        System.arraycopy(Indizien.attr, 0, attr, 0, attr.length);

        attrName = new String[attr.length];
        attrName[0] = "ID";
        attrName[1] = "angelegt am";
        attrName[2] = attr[2];
        attrName[3] = attr[3];
        attrName[4] = "Polizist ID";
        attrName[5] = "Fall ID";

        listTable = new String[4];
        listTable[0] = "Polizisten";
        listTable[1] = "Faelle";
        listTable[2] = "";
        listTable[3] = "";

        textAttr = new TextField[7];
        textAttr[0] = textAttr0;
        textAttr[1] = textAttr1;
        textAttr[2] = textAttr2;
        textAttr[3] = textAttr3;
        textAttr[4] = textAttr4;
        textAttr[5] = textAttr5;
        textAttr[6] = textAttr6;

        textAttr[0].setPromptText("* Pflichtfeld - wird automatisch generiert");
        textAttr[1].setPromptText("* Pflichtfeld");
        textAttr[4].setPromptText("* Pflichtfeld");
        textAttr[5].setPromptText("* Pflichtfeld");

        filterAttr = new TextField[7];
        filterAttr[0] = filterAttr0;
        filterAttr[1] = filterAttr1;
        filterAttr[2] = filterAttr2;
        filterAttr[3] = filterAttr3;
        filterAttr[4] = filterAttr4;
        filterAttr[5] = filterAttr5;
        filterAttr[6] = filterAttr6;

        for (int i=0; i<attr.length; i++)
            if (filterAttr[i] != null)
                filterAttr[i].setPromptText("nach '" + attrName[i] + "' filtern");

        labelAttr = new Label[7];
        labelAttr[0] = labelAttr0;
        labelAttr[1] = labelAttr1;
        labelAttr[2] = labelAttr2;
        labelAttr[3] = labelAttr3;
        labelAttr[4] = labelAttr4;
        labelAttr[5] = labelAttr5;
        labelAttr[6] = labelAttr6;

        for (int i=0; i<attr.length; i++)
            labelAttr[i].setText(attrName[i]);

        columnAttr = new TableColumn[7];
        columnAttr[0] = columnAttr0;
        columnAttr[1] = columnAttr1;
        columnAttr[2] = columnAttr2;
        columnAttr[3] = columnAttr3;
        columnAttr[4] = columnAttr4;
        columnAttr[5] = columnAttr5;
        columnAttr[6] = columnAttr6;

        for (int i=0; i<attr.length; i++)
            columnAttr[i].setText(attrName[i]);

        labelList = new Label[4];
        labelList[0] = labelList0;
        labelList[1] = labelList1;
        labelList[2] = labelList2;
        labelList[3] = labelList3;

        for (int i=0; i<listTable.length; i++)
            if (labelList[i] != null)
                labelList[i].setText(listTable[i]);
        labelList[0].setText("Polizist");
        labelList[1].setText("Fall");

        val = new String[attr.length];
        for (int i=0; i<attr.length; i++)
            val[i] = "";

        loadPicture();

        olTable = SQLController.selectFromTable(table);
        tableView.setItems(olTable);
        tableView.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                isNew = false;
                for (int i=0; i<attr.length; i++)
                    val[i] = newValue.getValue(i);
                setUpFields();
                setUpLists();
                setUpPicture();
            }
            else {
                reset();
            }
        });
        tableView.getSelectionModel().selectFirst();


    }

    void loadPicture() {
        imgURL = "kein-bild.gif";
/*        if (textAttr[2].getText().equals("")) {
            val[2] = imgURL;
            System.out.println("kein Bild");
        }
        else {
            val[2] = textAttr[2].getText();
        }*/
        try {
            img = new Image(getClass().getResourceAsStream(imgPath+imgURL));
            imgView = new ImageView(img);
            imgView.setFitHeight(400);
            imgView.setFitWidth(400);
            imgView.setPreserveRatio(true);
            imgView.setSmooth(true);
            imgBox.getChildren().add(imgView);
        }
    catch (Exception e){
        System.out.println("kann Bild nicht laden");
    }
        addImg = new Button("hinzufügen");
        addImg.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
            }
        });
//        imgBox.getChildren().add(addImg);
//        imgBox.getChildren().add(textAttr[2]);
    }

    void setUpPicture() {
        try {
            if (textAttr[2].getText().equals("")) {
                val[2] = imgURL;
                System.out.println("kein Bild");
            }
            else {
                val[2] = textAttr[2].getText();
            }
            val[2] = textAttr[2].getText();
            img = new Image(getClass().getResourceAsStream(imgPath+val[2]));
            imgView.setImage(img);
        }
        catch (Exception e){
            System.out.println("kann Bild nicht laden");
            val[2] = imgURL;
            try {
                img = new Image(getClass().getResourceAsStream(imgPath+imgURL));
                imgView.setImage(img);
            }
            catch (Exception e2) {
                System.out.println("kann Default-Bild nicht laden");
            }
        }

    }



    void setUpLists() {
        Filter filter = new Filter(table, attr[0], val[0], true);

        String query0 =
              "SELECT Personen.PersonID,Personen.Name,Geschlecht,Nationalitaet,Geburtsdatum,Todesdatum,Dienstgrad\n" +
                    " FROM Personen,Polizisten\n" +
                    " WHERE Polizisten.PersonID = Personen.PersonID\n" +
                    " AND Personen.PersonID = '" + val[4] + "';";
        ol0 = SQLController.selectFromQuery(listTable[0], query0);
        list0.setItems(ol0);

        String query1 =
              "SELECT * FROM Faelle WHERE FallID = '" + val[5] + "';";
        ol1 = SQLController.selectFromQuery(listTable[1], query1);
        list1.setItems(ol1);
    }

    @FXML
    void neu(ActionEvent actionEvent) {
        neuDefault();
        val[1] = formatter.format(new Date());
        textAttr1.setText(val[1]);
    }

    @FXML
    void save(ActionEvent actionEvent) {
        String[] keys = new String[10];
        keys[0] = val[0];
        for (int i=0; i<attr.length; i++)
            val[i] = textAttr[i].getText();
        try {
            int x = Integer.parseInt(val[0]);
        }
        catch (NumberFormatException e) {
            System.out.println(attr[0] + " ist kein Integer");
            try {
                int x = Integer.parseInt(keys[0]);
                val[0] = keys[0];
                textAttr0.setText(val[0]);
            }
            catch (NumberFormatException e2) {
                val[0] = "";
                textAttr0.setText(val[0]);
            }
        }
        if (val[1].equals("")) {
            System.out.println("kein " + attr[1] + " eingetragen");
            return;
        }
        if (val[4].equals("")) {
            System.out.println("kein " + attr[4] + " eingetragen");
            return;
        }
        if (val[5].equals("")) {
            System.out.println("kein " + attr[5] + " eingetragen");
            return;
        }
        if (isNew) {
            Tuplet tuplet = new Indizien(val);
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
            for (int i=0; i<attr.length; i++)
                tuplet.setValue(i, val[i]);
            SQLController.update(tuplet, keys);
            filter(new ActionEvent());
            tableView.getSelectionModel().clearAndSelect(index);
        }
        Control_Faelle.controlMe.filter(new ActionEvent());
        Control_Polizisten.controlMe.filter(new ActionEvent());
    }

    @FXML
    void openImage() {

    }

}