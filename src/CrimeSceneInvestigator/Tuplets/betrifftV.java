package CrimeSceneInvestigator.Tuplets;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 CREATE TABLE betrifftV (
 VerbrechenID     INT           not null,
 PersonID         INT           not null,
 ueberfuehrt      BOOLEAN       not null,
 PRIMARY KEY      (VerbrechenID, PersonID),
 FOREIGN KEY      (VerbrechenID)
 REFERENCES      Verbrechen (VerbrechenID),
 FOREIGN KEY      (PersonID)
 REFERENCES      Verdaechtige (PersonID)
 );
 */

public class betrifftV extends Tuplet {

    public void setAttr0(String attr0) {}
    public void setAttr1(String attr1) {}
    public void setAttr2(String attr2) {}
    public void setAttr3(String attr3) {}
    public void setAttr4(String attr4) {}
    public void setAttr5(String attr5) {}
    // Felder
    private final SimpleStringProperty VerbrechenID;
    public String getVerbrechenID() { return VerbrechenID.get(); }
    public void setVerbrechenID(String VerbrechenID) { this.VerbrechenID.set(VerbrechenID); }

    private final SimpleStringProperty PersonID;
    public String getPersonID() { return PersonID.get(); }
    public void setPersonID(String PersonID) { this.PersonID.set(PersonID); }

    private final SimpleStringProperty ueberfuehrt;
    public String getueberfuehrt() { return ueberfuehrt.get(); }
    public void setueberfuehrt(String ueberfuehrt) { this.ueberfuehrt.set(ueberfuehrt); }


    // Konstruktoren
    public betrifftV(String VerbrechenID, String PersonID, String ueberfuehrt) {
        this.VerbrechenID = new SimpleStringProperty(VerbrechenID);
        this.PersonID = new SimpleStringProperty(PersonID);
        this.ueberfuehrt = new SimpleStringProperty(ueberfuehrt);
    }

    // Methoden
    public String getValue(String attribute) {
        switch (attribute) {
            case ("VerbrechenID"):
                return VerbrechenID.get();
            case ("PersonID"):
                return PersonID.get();
            case ("ueberfuehrt"):
                return ueberfuehrt.get();
        }
        return null;
    }

    public String toString() {
        return "["+VerbrechenID.get() + "] " + PersonID.get();
    }

    public static ObservableList<Tuplet> getOL(ResultSet readTable) throws SQLException {
        ArrayList<Tuplet> al = new ArrayList<Tuplet>();
        while (readTable.next()) {
            Tuplet tuplet = new betrifftV(
                  readTable.getString("VerbrechenID"),
                  readTable.getString("PersonID"),
                  readTable.getString("ueberfuehrt")
            );
            al.add(tuplet);
        }
        return FXCollections.observableArrayList(al);
    }
}