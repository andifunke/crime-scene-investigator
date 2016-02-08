package CrimeSceneInvestigator.Tuplets;

import CrimeSceneInvestigator.MainController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 CREATE TABLE Polizisten(
 PersonID         INTEGER PRIMARY KEY,
 Dienstgrad       VARCHAR(256)  not null,
 FOREIGN KEY      (PersonID)
 REFERENCES      Personen (PersonID)
 );
 */

public class Polizisten extends Personen {

	public static final String table = "Polizisten";
	public static final String[] attr = {
			"PersonID",
			"Name",
			"Geschlecht",
			"Nationalitaet",
			"Geburtsdatum",
			"Todesdatum",
			"Dienstgrad"
	};
	public static ObservableList<Tuplet> getOL(ResultSet readTable) throws SQLException {
		ArrayList<Tuplet> al = new ArrayList<Tuplet>();
		while (readTable.next()) {
			String[] values = {
					readTable.getString(attr[0]),
					readTable.getString(attr[1]),
					readTable.getString(attr[2]),
					readTable.getString(attr[3]),
					MainController.formatDateToDMY(readTable.getString(attr[4])),
					MainController.formatDateToDMY(readTable.getString(attr[5])),
					readTable.getString(attr[6]),
			};
			al.add(new Polizisten(values)); // TODO: CHANGE HERE
		}
		return FXCollections.observableArrayList(al);
	}

	public Polizisten(String[] val) {
		super(attr, val);
		setTable(table);
		setAttr(attr);
	}

	public String getUpdateQuery2(String[] key) {
		return
				"UPDATE " + table + "\n"+
						" SET "+
						attr[0] + "='" + getVal0() + "'," +
						attr[6] + "='" + getVal6() + "'" +
						"\n WHERE " + attr[0] + "=" + key[0] + ";";
	}

	public String getInsertQuery2() {
		return
				"INSERT INTO " + table +
						" VALUES (" +
						"'" + getVal0() + "'," +
						"'" + getVal6() + "'" +
						");";
	}

}