package CrimeSceneInvestigator.Tuplets;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 CREATE TABLE betrifftO (
 VerbrechenID     INT           not null,
 PersonID         INT           not null,
 PRIMARY KEY      (VerbrechenID, PersonID),
 FOREIGN KEY      (VerbrechenID)
 REFERENCES      Verbrechen (VerbrechenID),
 FOREIGN KEY      (PersonID)
 REFERENCES      Opfer (PersonID)
 );
 */

public class betrifftO extends Tuplet {

    // Felder
    private final SimpleStringProperty VerbrechenID;
    public String getVerbrechenID() { return VerbrechenID.get(); }
    public void setVerbrechenID(String VerbrechenID) { this.VerbrechenID.set(VerbrechenID); }

    private final SimpleStringProperty PersonID;
    public String getPersonID() { return PersonID.get(); }
    public void setPersonID(String PersonID) { this.PersonID.set(PersonID); }


    // Konstruktoren
    public betrifftO(String VerbrechenID, String PersonID) {
        this.VerbrechenID = new SimpleStringProperty(VerbrechenID);
        this.PersonID = new SimpleStringProperty(PersonID);
    }

    // Methoden
    public String getValue(String attribute) {
        switch (attribute) {
            case ("VerbrechenID"):
                return VerbrechenID.get();
            case ("PersonID"):
                return PersonID.get();
        }
        return null;
    }

    public String toString() {
        return "["+VerbrechenID.get() + "] " + PersonID.get();
    }

    public static ObservableList<Tuplet> getOL(ResultSet readTable) throws SQLException {
        ArrayList<Tuplet> al = new ArrayList<Tuplet>();
        while (readTable.next()) {
            Tuplet tuplet = new betrifftO(
                  readTable.getString("VerbrechenID"),
                  readTable.getString("PersonID")
            );
            al.add(tuplet);
        }
        return FXCollections.observableArrayList(al);
    }
}