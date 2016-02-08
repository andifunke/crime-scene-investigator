package CrimeSceneInvestigator.Tuplets;

import CrimeSceneInvestigator.MainController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * CREATE TABLE Indizien (
 * IndizID          INTEGER PRIMARY KEY,
 * angelegtam       DATE          not null,
 * Bild             BLOB,
 * Text             CLOB,
 * PersonID         INT           not null,
 * FallID           INT           not null,
 * FOREIGN KEY      (PersonID)
 * REFERENCES      Polizisten(PersonID),
 * FOREIGN KEY      (FallID)
 * REFERENCES      Faelle (FallID)
 * );
 */

public class Indizien extends Tuplet {

	public static final String table = "Indizien";
	public static final String[] attr = {
			"IndizID",
			"angelegtam",
			"Bild",
			"Text",
			"PersonID",
			"FallID",
	};

	public Indizien(String[] val) {
		super(attr, val);
		setTable(table);
	}

	public static ObservableList<Tuplet> getOL(ResultSet readTable) throws SQLException {
		ArrayList<Tuplet> al = new ArrayList<Tuplet>();
		while (readTable.next()) {
			String[] values = {
					readTable.getString(attr[0]),
					MainController.formatDateToDMY(readTable.getString(attr[1])),
					readTable.getString(attr[2]),
					readTable.getString(attr[3]),
					readTable.getString(attr[4]),
					readTable.getString(attr[5]),
			};
			al.add(new Indizien(values)); // TODO: CHANGE HERE
		}
		return FXCollections.observableArrayList(al);
	}

	public String toString() {
		return
				"[" + getVal0() + "] " + getVal3();
	}

	public String getUpdateQuery(String[] key) {
		return
				"UPDATE " + table + "\n" +
						" SET " +
						attr[0] + "='" + getVal0() + "'," +
						attr[1] + "='" + MainController.formatDateToYMD(getVal1()) + "'," +
						attr[2] + "='" + getVal2() + "'," +
						attr[3] + "='" + getVal3() + "'," +
						attr[4] + "='" + getVal4() + "'," +
						attr[5] + "='" + getVal5() + "'" +
						"\n WHERE " + attr[0] + "=" + key[0] + ";";
	}

	public String getInsertQuery() {
		return
				"INSERT INTO " + table +
						" VALUES (" +
						"NULL" + "," +
						"'" + MainController.formatDateToYMD(getVal1()) + "'," +
						"'" + getVal2() + "'," +
						"'" + getVal3() + "'," +
						"'" + getVal4() + "'," +
						"'" + getVal5() + "'" +
						");";
	}

}