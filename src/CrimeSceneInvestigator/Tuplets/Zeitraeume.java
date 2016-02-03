package CrimeSceneInvestigator.Tuplets;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 CREATE TABLE Zeitraeume (
 ZeitraumID       INTEGER PRIMARY KEY,
 von              DATE          not null,
 bis              DATE,
 BehoerdeID       INT           not null,
 PersonID         INT           not null,
 FOREIGN KEY      (BehoerdeID)
 REFERENCES      Behoerden (BehoerdeID),
 FOREIGN KEY      (PersonID)
 REFERENCES      Polizisten(PersonID)
 );
 */

public class Zeitraeume extends Tuplet {

    public void setAttr0(String attr0) {}
    public void setAttr1(String attr1) {}
    public void setAttr2(String attr2) {}
    public void setAttr3(String attr3) {}
    public void setAttr4(String attr4) {}
    public void setAttr5(String attr5) {}
    // Felder
    private final SimpleStringProperty ZeitraumID;
    public String getZeitraumID() { return ZeitraumID.get(); }
    public void setZeitraumID(String ZeitraumID) { this.ZeitraumID.set(ZeitraumID); }

    private final SimpleStringProperty von;
    public String getvon() { return von.get(); }
    public void setvon(String von) { this.von.set(von); }

    private final SimpleStringProperty bis;
    public String getbis() { return bis.get(); }
    public void setbis(String bis) { this.bis.set(bis); }

    private final SimpleStringProperty BehoerdeID;
    public String getBehoerdeID() { return BehoerdeID.get(); }
    public void setBehoerdeID(String BehoerdeID) { this.BehoerdeID.set(BehoerdeID); }

    private final SimpleStringProperty PersonID;
    public String getPersonID() { return PersonID.get(); }
    public void setPersonID(String PersonID) { this.PersonID.set(PersonID); }


    // Konstruktoren
    public Zeitraeume(String ZeitraumID, String von, String BehoerdeID, String PersonID) {
        this(ZeitraumID, von, "", BehoerdeID, PersonID);
    }

    public Zeitraeume(String ZeitraumID, String von, String bis, String BehoerdeID, String PersonID) {
        this.ZeitraumID = new SimpleStringProperty(ZeitraumID);
        this.von = new SimpleStringProperty(von);
        this.bis = new SimpleStringProperty(bis);
        this.BehoerdeID = new SimpleStringProperty(BehoerdeID);
        this.PersonID = new SimpleStringProperty(PersonID);
    }

    // Methoden
    public String getValue(String attribute) {
        switch (attribute) {
            case ("ZeitraumID"):
                return ZeitraumID.get();
            case ("von"):
                return von.get();
            case ("bis"):
                return bis.get();
            case ("BehoerdeID"):
                return BehoerdeID.get();
            case ("PersonID"):
                return PersonID.get();
        }
        return null;
    }

    public String toString() {
        return "["+ZeitraumID.get() + "] " + von.get();
    }

    public static ObservableList<Tuplet> getOL(ResultSet readTable) throws SQLException {
        ArrayList<Tuplet> al = new ArrayList<Tuplet>();
        while (readTable.next()) {
            Tuplet tuplet = new Zeitraeume(
                  readTable.getString("ZeitraumID"),
                  readTable.getString("von"),
                  readTable.getString("bis"),
                  readTable.getString("BehoerdeID"),
                  readTable.getString("PersonID")
            );
            al.add(tuplet);
        }
        return FXCollections.observableArrayList(al);
    }
}