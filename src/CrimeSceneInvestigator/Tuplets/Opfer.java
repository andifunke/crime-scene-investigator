package CrimeSceneInvestigator.Tuplets;

import CrimeSceneInvestigator.MainController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 CREATE TABLE Opfer (
 PersonID         INTEGER PRIMARY KEY,
 FOREIGN KEY      (PersonID)
 REFERENCES      Personen (PersonID)
 );
 */

public class Opfer extends Personen {

	public static final String table = "Opfer";

	public Opfer(String[] val) {
		super(val);
		setTable(table);
		setAttr(attr);
	}

	public String getUpdateQuery2(String[] key) {
		return
				"UPDATE " + table + "\n"+
						" SET "+
						attr[0] + "='" + getVal0() + "'" +
						"\n WHERE " + attr[0] + "=" + key[0] + ";";
	}

	public String getInsertQuery2() {
		return
				"INSERT INTO " + table +
						" VALUES (" +
						"'" + getVal0() + "'" +
						");";
	}

}