package CrimeSceneInvestigator.Tuplets;

import javafx.beans.property.SimpleStringProperty;
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

public class Opfer extends Tuplet {

	public void setAttr0(String attr0) {}
	public void setAttr1(String attr1) {}
	public void setAttr2(String attr2) {}
	public void setAttr3(String attr3) {}
	public void setAttr4(String attr4) {}
	public void setAttr5(String attr5) {}
	// Felder
	private final SimpleStringProperty PersonID;
	public String getPersonID() { return PersonID.get(); }
	public void setPersonID(String PersonID) { this.PersonID.set(PersonID); }


	// Konstruktoren
	public Opfer(String PersonID) {
		this.PersonID = new SimpleStringProperty(PersonID);
	}

	// Methoden
	public String getValue(String attribute) {
		switch (attribute) {
			case ("PersonID"):
				return PersonID.get();
		}
		return null;
	}

	public String toString() {
		return "["+PersonID.get() + "] ";
	}

	public static ObservableList<Tuplet> getOL(ResultSet readTable) throws SQLException {
		ArrayList<Tuplet> al = new ArrayList<Tuplet>();
		while (readTable.next()) {
			Tuplet tuplet = new Opfer(
					readTable.getString("PersonID")
			);
			al.add(tuplet);
		}
		return FXCollections.observableArrayList(al);
	}
}