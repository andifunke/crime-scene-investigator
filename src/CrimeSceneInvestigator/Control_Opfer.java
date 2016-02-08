package CrimeSceneInvestigator;

import CrimeSceneInvestigator.Tuplets.Opfer;
import CrimeSceneInvestigator.Tuplets.Tuplet;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.util.LinkedList;

public class Control_Opfer extends MainController {

	public static Control_Opfer controlMe;

	final String query =
			"SELECT Personen.PersonID,Name,Geschlecht,Nationalitaet,Geburtsdatum,Todesdatum\n" +
					"  FROM Opfer,Personen\n" +
					"  WHERE Opfer.PersonID = Personen.PersonID;";

	public Control_Opfer() {
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("FXML/Opfer.fxml"));
		fxmlLoader.setRoot(this);
		fxmlLoader.setController(this);
		try {
			fxmlLoader.load();
		} catch (IOException exception) {
			throw new RuntimeException(exception);
		}

		controlMe = this;

		table = Opfer.table;

		attr = new String[Opfer.attr.length];
		System.arraycopy(Opfer.attr, 0, attr, 0, attr.length);

		attrName = new String[attr.length];
		attrName[0] = "ID";
		attrName[1] = attr[1];
		attrName[2] = attr[2];
		attrName[3] = attr[3];
		attrName[4] = attr[4];
		attrName[5] = attr[5];

		listTable = new String[4];
		listTable[0] = "Verbrechen";
		listTable[1] = "";
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

		filterAttr = new TextField[7];
		filterAttr[0] = filterAttr0;
		filterAttr[1] = filterAttr1;
		filterAttr[2] = filterAttr2;
		filterAttr[3] = filterAttr3;
		filterAttr[4] = filterAttr4;
		filterAttr[5] = filterAttr5;
		filterAttr[6] = filterAttr6;

		for (int i = 0; i < attr.length; i++)
			filterAttr[i].setPromptText("nach '" + attrName[i] + "' filtern");

		labelAttr = new Label[7];
		labelAttr[0] = labelAttr0;
		labelAttr[1] = labelAttr1;
		labelAttr[2] = labelAttr2;
		labelAttr[3] = labelAttr3;
		labelAttr[4] = labelAttr4;
		labelAttr[5] = labelAttr5;
		labelAttr[6] = labelAttr6;

		for (int i = 0; i < attr.length; i++)
			labelAttr[i].setText(attrName[i]);

		columnAttr = new TableColumn[7];
		columnAttr[0] = columnAttr0;
		columnAttr[1] = columnAttr1;
		columnAttr[2] = columnAttr2;
		columnAttr[3] = columnAttr3;
		columnAttr[4] = columnAttr4;
		columnAttr[5] = columnAttr5;
		columnAttr[6] = columnAttr6;

		for (int i = 0; i < attr.length; i++)
			columnAttr[i].setText(attrName[i]);

		labelList = new Label[4];
		labelList[0] = labelList0;
		labelList[1] = labelList1;
		labelList[2] = labelList2;
		labelList[3] = labelList3;

		for (int i = 0; i < listTable.length; i++)
			if (labelList[i] != null)
				labelList[i].setText(listTable[i]);

		val = new String[attr.length];
		for (int i = 0; i < attr.length; i++)
			val[i] = "";

		olTable = SQLController.selectFromQuery(table, query);
		tableView.setItems(olTable);
		tableView.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
			if (newValue != null) {
				isNew = false;
				for (int i = 0; i < attr.length; i++)
					val[i] = newValue.getValue(i);
				setUpFields();
				setUpLists();
			}
			else {
				reset();
			}
		});
		tableView.getSelectionModel().selectFirst();
	}

	void setUpLists() {
		Filter filter = new Filter(table, attr[0], val[0], true);

		String query0 =
				"SELECT Verbrechen.VerbrechenID,Verbrechen.Name,Verbrechen.Datum,Verbrechen.FallID,Verbrechen.ArtName,Verbrechen.BezirkID\n" +
						"  FROM Verbrechen,betrifftO\n" +
						"  WHERE Verbrechen.VerbrechenID = betrifftO.VerbrechenID\n" +
						"  AND betrifftO.PersonID = '" + val[0] + "';";
		ol0 = SQLController.selectFromQuery(listTable[0], query0);
		list0.setItems(ol0);
	}


	void refreshTable() {
		olTable.clear();
		olTable.addAll(SQLController.selectFromQuery(table, query));
		tableView.refresh();
	}

	void refreshTable(LinkedList<Filter> filterList) {
		olTable.clear();
		olTable.addAll(SQLController.selectFromQuery(table, query, filterList));
		tableView.refresh();
	}

	@FXML
	void save(ActionEvent actionEvent) {
		String[] keys = new String[10];
		keys[0] = val[0];
		for (int i = 0; i < attr.length; i++)
			val[i] = textAttr[i].getText();
		try {
			int x = Integer.parseInt(val[0]);
		} catch (NumberFormatException e) {
			System.out.println(attr[0] + " ist kein Integer");
			try {
				int x = Integer.parseInt(keys[0]);
				val[0] = keys[0];
				textAttr0.setText(val[0]);
			} catch (NumberFormatException e2) {
				val[0] = "";
				textAttr0.setText(val[0]);
			}
		}
		if (val[1].equals("")) {
			System.out.println("kein " + attr[1] + " eingetragen");
			return;
		}
		if (isNew) {
			Tuplet tuplet = new Opfer(val);
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
			for (int i = 0; i < attr.length; i++)
				tuplet.setValue(i, val[i]);
			SQLController.update(tuplet, keys);
			filter(new ActionEvent());
			tableView.getSelectionModel().clearAndSelect(index);
		}
		Control_Personen.controlMe.filter(new ActionEvent());
	}

}