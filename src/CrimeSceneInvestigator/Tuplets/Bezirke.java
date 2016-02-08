package CrimeSceneInvestigator.Tuplets;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * CREATE TABLE Bezirke (
 * BezirkID         INTEGER PRIMARY KEY,
 * Name             VARCHAR(256)  not null,
 * Typ              VARCHAR(256)  not null
 * );
 */

public class Bezirke extends Tuplet {

	public static final String table = "Bezirke";
	public static final String[] attr = {
			"BezirkID",
			"Name",
			"Typ",
	};

	public Bezirke(String[] val) {
		super(attr, val);
		setTable(table);
	}

	public static ObservableList<Tuplet> getOL(ResultSet readTable) throws SQLException {
		ArrayList<Tuplet> al = new ArrayList<Tuplet>();
		while (readTable.next()) {
			String[] values = {
					readTable.getString(attr[0]),
					readTable.getString(attr[1]),
					readTable.getString(attr[2]),
			};
			al.add(new Bezirke(values)); // TODO: CHANGE HERE
		}
		return FXCollections.observableArrayList(al);
	}

	public String toString() {
		return
				"[" + getVal0() + "] " + getVal1();
	}

	public String getUpdateQuery(String[] key) {
		return
				"UPDATE " + table + "\n" +
						" SET " +
						attr[0] + "='" + getVal0() + "'," +
						attr[1] + "='" + getVal1() + "'," +
						attr[2] + "='" + getVal2() + "'" +
						"\n WHERE " + attr[0] + "=" + key[0] + ";";
	}

	public String getInsertQuery() {
		return
				"INSERT INTO " + table +
						" VALUES (" +
						"NULL" + "," +
						"'" + getVal1() + "'," +
						"'" + getVal2() + "'" +
						");";
	}

}