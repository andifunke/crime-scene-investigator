package CrimeSceneInvestigator.Tuplets;

import javafx.beans.property.SimpleStringProperty;
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

public class Polizisten extends Tuplet {

	// Felder
	private final SimpleStringProperty PersonID;
	public String getPersonID() { return PersonID.get(); }
	public void setPersonID(String PersonID) { this.PersonID.set(PersonID); }

	private final SimpleStringProperty Dienstgrad;
	public String getDienstgrad() { return Dienstgrad.get(); }
	public void setDienstgrad(String Dienstgrad) { this.Dienstgrad.set(Dienstgrad); }


	// Konstruktoren
	public Polizisten(String PersonID, String Dienstgrad) {
		this.PersonID = new SimpleStringProperty(PersonID);
		this.Dienstgrad = new SimpleStringProperty(Dienstgrad);
	}

	// Methoden
	public String getValue(String attribute) {
		switch (attribute) {
			case ("PersonID"):
				return PersonID.get();
			case ("Dienstgrad"):
				return Dienstgrad.get();
		}
		return null;
	}

	public String toString() {
		return "["+PersonID.get() + "] " + Dienstgrad.get();
	}

	public static ObservableList<Tuplet> getOL(ResultSet readTable) throws SQLException {
		ArrayList<Tuplet> al = new ArrayList<Tuplet>();
		while (readTable.next()) {
			Tuplet tuplet = new Polizisten(
					readTable.getString("PersonID"),
					readTable.getString("Dienstgrad")
			);
			al.add(tuplet);
		}
		return FXCollections.observableArrayList(al);
	}
}