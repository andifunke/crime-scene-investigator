package CrimeSceneInvestigator.Tuplets;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 CREATE TABLE arbeitetan (
 PersonID         INT           not null,
 FallID           INT           not null,
 von              DATE          not null,
 bis              DATE,
 PRIMARY KEY      (PersonID, FallID),
 FOREIGN KEY      (PersonID)
 REFERENCES      Polizisten(PersonID),
 FOREIGN KEY      (FallID)
 REFERENCES      Faelle (FallID)
 ); 
 */

public class arbeitetan extends Tuplet {

    // Felder
    private final SimpleStringProperty PersonID;
    public String getPersonID() { return PersonID.get(); }
    public void setPersonID(String PersonID) { this.PersonID.set(PersonID); }

    private final SimpleStringProperty FallID;
    public String getFallID() { return FallID.get(); }
    public void setFallID(String FallID) { this.FallID.set(FallID); }

    private final SimpleStringProperty von;
    public String getvon() { return von.get(); }
    public void setvon(String von) { this.von.set(von); }

    private final SimpleStringProperty bis;
    public String getbis() { return bis.get(); }
    public void setbis(String bis) { this.bis.set(bis); }

    // Konstruktoren
    public arbeitetan(String PersonID, String FallID, String von) {
        this(PersonID, FallID, von, "");
    }

    public arbeitetan(String PersonID, String FallID, String von, String bis) {
        this.PersonID = new SimpleStringProperty(PersonID);
        this.FallID = new SimpleStringProperty(FallID);
        this.von = new SimpleStringProperty(von);
        this.bis = new SimpleStringProperty(bis);
    }

    // Methoden
    public String getValue(String attribute) {
        switch (attribute) {
            case ("PersonID"):
                return PersonID.get();
            case ("FallID"):
                return FallID.get();
            case ("von"):
                return von.get();
            case ("bis"):
                return bis.get();
        }
        return null;
    }

    public String toString() {
        return "["+PersonID.get() + "] " + FallID.get();
    }

    public static ObservableList<Tuplet> getOL(ResultSet readTable) throws SQLException {
        ArrayList<Tuplet> al = new ArrayList<Tuplet>();
        while (readTable.next()) {
            Tuplet tuplet = new Faelle(
                  readTable.getString("PersonID"),
                  readTable.getString("FallID"),
                  readTable.getString("von"),
                  readTable.getString("bis")
            );
            al.add(tuplet);
        }
        return FXCollections.observableArrayList(al);
    }

    public void setAttr0(String attr0) {}
    public void setAttr1(String attr1) {}
    public void setAttr2(String attr2) {}
    public void setAttr3(String attr3) {}
    public void setAttr4(String attr4) {}
    public void setAttr5(String attr5) {}

}