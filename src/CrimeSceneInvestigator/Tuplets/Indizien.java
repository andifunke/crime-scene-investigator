package CrimeSceneInvestigator.Tuplets;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 CREATE TABLE Indizien (
 IndizID          INTEGER PRIMARY KEY,
 angelegtam       DATE          not null,
 Bild             BLOB,
 Text             CLOB,
 PersonID         INT           not null,
 FallID           INT           not null,
 FOREIGN KEY      (PersonID)
 REFERENCES      Polizisten(PersonID),
 FOREIGN KEY      (FallID)
 REFERENCES      Faelle (FallID)
 );
 */

public class Indizien extends Tuplet {

    public void setAttr0(String attr0) {}
    public void setAttr1(String attr1) {}
    public void setAttr2(String attr2) {}
    public void setAttr3(String attr3) {}
    public void setAttr4(String attr4) {}
    public void setAttr5(String attr5) {}
    // Felder
    private final SimpleStringProperty IndizID;
    public String getIndizID() { return IndizID.get(); }
    public void setIndizID(String IndizID) { this.IndizID.set(IndizID); }

    private final SimpleStringProperty angelegtam;
    public String getangelegtam() { return angelegtam.get(); }
    public void setangelegtam(String angelegtam) { this.angelegtam.set(angelegtam); }

    private final SimpleStringProperty Bild;
    public String getBild() { return Bild.get(); }
    public void setBild(String Bild) { this.Bild.set(Bild); }

    private final SimpleStringProperty Text;
    public String getText() { return Text.get(); }
    public void setText(String Text) { this.Text.set(Text); }

    private final SimpleStringProperty PersonID;
    public String getPersonID() { return PersonID.get(); }
    public void setPersonID(String PersonID) { this.PersonID.set(PersonID); }

    private final SimpleStringProperty FallID;
    public String getFallID() { return FallID.get(); }
    public void setFallID(String FallID) { this.FallID.set(FallID); }


    // Konstruktoren
    public Indizien(String IndizID, String angelegtam, String PersonID, String FallID) {
        this(IndizID, angelegtam, "", "", PersonID, FallID);
    }

    public Indizien(String IndizID, String angelegtam, String Bild, String Text, String PersonID, String FallID) {
        this.IndizID = new SimpleStringProperty(IndizID);
        this.angelegtam = new SimpleStringProperty(angelegtam);
        this.Bild = new SimpleStringProperty(Bild);
        this.Text = new SimpleStringProperty(Text);
        this.PersonID = new SimpleStringProperty(PersonID);
        this.FallID = new SimpleStringProperty(FallID);
    }

    // Methoden
    public String getValue(String attribute) {
        switch (attribute) {
            case ("IndizID"):
                return IndizID.get();
            case ("angelegtam"):
                return angelegtam.get();
            case ("Bild"):
                return Bild.get();
            case ("Text"):
                return Text.get();
            case ("PersonID"):
                return PersonID.get();
            case ("FallID"):
                return FallID.get();
        }
        return null;
    }

    public String toString() {
        return "["+IndizID.get() + "] " + angelegtam.get();
    }

    public static ObservableList<Tuplet> getOL(ResultSet readTable) throws SQLException {
        ArrayList<Tuplet> al = new ArrayList<Tuplet>();
        while (readTable.next()) {
            Tuplet tuplet = new Indizien(
                  readTable.getString("IndizID"),
                  readTable.getString("angelegtam"),
                  readTable.getString("Bild"),
                  readTable.getString("Text"),
                  readTable.getString("PersonID"),
                  readTable.getString("FallID")
            );
            al.add(tuplet);
        }
        return FXCollections.observableArrayList(al);
    }
}