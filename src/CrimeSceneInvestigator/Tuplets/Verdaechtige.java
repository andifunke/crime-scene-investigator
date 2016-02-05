package CrimeSceneInvestigator.Tuplets;

import CrimeSceneInvestigator.MainController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 CREATE TABLE Verdaechtige (
 PersonID         INTEGER PRIMARY KEY,
 FOREIGN KEY      (PersonID)
 REFERENCES      Personen (PersonID)
 );
 */

public class Verdaechtige extends Personen {

	public static final String table = "Verdaechtige";

	public Verdaechtige(String[] val) {
		super(val);
		setTable(table);
		setAttr(attr);
	}

}