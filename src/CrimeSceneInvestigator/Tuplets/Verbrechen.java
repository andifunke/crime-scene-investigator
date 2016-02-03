package CrimeSceneInvestigator.Tuplets;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 CREATE TABLE Verbrechen (
 VerbrechenID     INTEGER PRIMARY KEY,
 Name             VARCHAR(256)  not null,
 Datum            DATE,
 FallID           INT           not null,
 ArtName          VARCHAR(256)  not null,
 BezirkID         INT           not null,
 FOREIGN KEY      (FallID)
 REFERENCES      Faelle (FallID),
 FOREIGN KEY      (ArtName)
 REFERENCES      Arten (Name),
 FOREIGN KEY      (BezirkID)
 REFERENCES      Bezirke (BezirkID)
 );
 */

public class Verbrechen extends Tuplet {

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

    private final SimpleStringProperty Name;
    public String getName() { return Name.get(); }
    public void setName(String Name) { this.Name.set(Name); }

    private final SimpleStringProperty Datum;
    public String getDatum() { return Datum.get(); }
    public void setDatum(String Datum) { this.Datum.set(Datum); }

    private final SimpleStringProperty FallID;
    public String getFallID() { return FallID.get(); }
    public void setFallID(String FallID) { this.FallID.set(FallID); }

    private final SimpleStringProperty ArtName;
    public String getArtName() { return ArtName.get(); }
    public void setArtName(String ArtName) { this.ArtName.set(ArtName); }

    private final SimpleStringProperty BezirkID;
    public String getBezirkID() { return BezirkID.get(); }
    public void setBezirkID(String BezirkID) { this.BezirkID.set(BezirkID); }


    // Konstruktoren
    public Verbrechen(String VerbrechenID, String Name, String FallID, String ArtName, String BezirkID) {
        this(VerbrechenID, Name, "", FallID, ArtName, BezirkID);
    }

    public Verbrechen(String VerbrechenID, String Name, String Datum, String FallID, String ArtName, String BezirkID) {
        this.VerbrechenID = new SimpleStringProperty(VerbrechenID);
        this.Name = new SimpleStringProperty(Name);
        this.Datum = new SimpleStringProperty(Datum);
        this.FallID = new SimpleStringProperty(FallID);
        this.ArtName = new SimpleStringProperty(ArtName);
        this.BezirkID = new SimpleStringProperty(BezirkID);
    }

    // Methoden
    public String getValue(String attribute) {
        switch (attribute) {
            case ("VerbrechenID"):
                return VerbrechenID.get();
            case ("Name"):
                return Name.get();
            case ("Datum"):
                return Datum.get();
            case ("FallID"):
                return FallID.get();
            case ("ArtName"):
                return ArtName.get();
            case ("BezirkID"):
                return BezirkID.get();
        }
        return null;
    }

    public String toString() {
        return "["+VerbrechenID.get() + "] " + Name.get();
    }

    public static ObservableList<Tuplet> getOL(ResultSet readTable) throws SQLException {
        ArrayList<Tuplet> al = new ArrayList<Tuplet>();
        while (readTable.next()) {
            Tuplet tuplet = new Verbrechen(
                  readTable.getString("VerbrechenID"),
                  readTable.getString("Name"),
                  readTable.getString("Datum"),
                  readTable.getString("FallID"),
                  readTable.getString("ArtName"),
                  readTable.getString("BezirkID")
            );
            al.add(tuplet);
        }
        return FXCollections.observableArrayList(al);
    }
}