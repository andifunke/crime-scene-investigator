package CrimeSceneInvestigator.Tuplets;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 CREATE TABLE Faelle (
 FallID           INTEGER PRIMARY KEY,
 Name             VARCHAR(256) not null,
 Eroeffnungsdatum DATE         not null,
 Enddatum         DATE
 );
 */

public class Faelle extends Tuplet {

    // Felder
    private final SimpleStringProperty FallID;
    public String getFallID() { return FallID.get(); }
    public void setAttr0(String FallID) { this.FallID.set(FallID); }

    private final SimpleStringProperty Name;
    public String getName() { return Name.get(); }
    public void setAttr1(String Name) { this.Name.set(Name); }

    private final SimpleStringProperty Eroeffnungsdatum;
    public String getEroeffnungsdatum() { return Eroeffnungsdatum.get(); }
    public void setAttr2(String Eroeffnungsdatum) { this.Eroeffnungsdatum.set(Eroeffnungsdatum); }

    private final SimpleStringProperty Enddatum;
    public String getEnddatum() { return Enddatum.get(); }
    public void setAttr3(String Enddatum) { this.Enddatum.set(Enddatum); }

    public void setAttr4(String Enddatum) {  }
    public void setAttr5(String Enddatum) {  }

    // Konstruktoren
    public Faelle(String FallID, String Name, String Eroeffnungsdatum) {
        this(FallID, Name, Eroeffnungsdatum, "");
    }

    public Faelle(String FallID, String Name, String Eroeffnungsdatum, String Enddatum) {
        this.FallID = new SimpleStringProperty(FallID);
        this.Name = new SimpleStringProperty(Name);
        this.Eroeffnungsdatum = new SimpleStringProperty(Eroeffnungsdatum);
        this.Enddatum = new SimpleStringProperty(Enddatum);
    }

    // Methoden
    public String getValue(String attribute) {
        switch (attribute) {
            case ("FallID"):
                return FallID.get();
            case ("Name"):
                return Name.get();
            case ("Eroeffnungsdatum"):
                return Eroeffnungsdatum.get();
            case ("Enddatum"):
                return Enddatum.get();
        }
        return null;
    }

    public String toString() {
        return "["+FallID.get() + "] " + Name.get();
    }

    public static ObservableList<Tuplet> getOL(ResultSet readTable) throws SQLException {
        ArrayList<Tuplet> al = new ArrayList<Tuplet>();
        while (readTable.next()) {
            Tuplet tuplet = new Faelle(
                readTable.getString("FallID"),
                readTable.getString("Name"),
                readTable.getString("Eroeffnungsdatum"),
                readTable.getString("Enddatum")
            );
            al.add(tuplet);
        }
        return FXCollections.observableArrayList(al);
    }
}