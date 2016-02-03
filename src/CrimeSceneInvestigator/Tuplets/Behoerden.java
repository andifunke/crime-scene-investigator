package CrimeSceneInvestigator.Tuplets;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 CREATE TABLE Behoerden (
 BehoerdeID       INTEGER PRIMARY KEY,
 Name             VARCHAR(256)  not null,
 Typ              VARCHAR(256)  not null,
 BezirkID         INT           not null,
 FOREIGN KEY      (BezirkID)
 REFERENCES      Bezirke (BezirkID)
 );
 */

public class Behoerden extends Tuplet {

    // Felder
    private final SimpleStringProperty BehoerdeID;
    public String getBehoerdeID() { return BehoerdeID.get(); }
    public void setBehoerdeID(String BehoerdeID) { this.BehoerdeID.set(BehoerdeID); }

    private final SimpleStringProperty Name;
    public String getName() { return Name.get(); }
    public void setName(String Name) { this.Name.set(Name); }

    private final SimpleStringProperty Typ;
    public String getTyp() { return Typ.get(); }
    public void setTyp(String Typ) { this.Typ.set(Typ); }

    private final SimpleStringProperty BezirkID;
    public String getBezirkID() { return BezirkID.get(); }
    public void setBezirkID(String BezirkID) { this.BezirkID.set(BezirkID); }


    // Konstruktoren
    public Behoerden(String BehoerdeID, String Name, String Typ, String BezirkID) {
        this.BehoerdeID = new SimpleStringProperty(BehoerdeID);
        this.Name = new SimpleStringProperty(Name);
        this.Typ = new SimpleStringProperty(Typ);
        this.BezirkID = new SimpleStringProperty(BezirkID);
    }

    // Methoden
    public String getValue(String attribute) {
        switch (attribute) {
            case ("BehoerdeID"):
                return BehoerdeID.get();
            case ("Name"):
                return Name.get();
            case ("Typ"):
                return Typ.get();
            case ("BezirkID"):
                return BezirkID.get();
        }
        return null;
    }

    public String toString() {
        return "["+BehoerdeID.get() + "] " + Name.get();
    }

    public static ObservableList<Tuplet> getOL(ResultSet readTable) throws SQLException {
        ArrayList<Tuplet> al = new ArrayList<Tuplet>();
        while (readTable.next()) {
            Tuplet tuplet = new Behoerden(
                  readTable.getString("BehoerdeID"),
                  readTable.getString("Name"),
                  readTable.getString("Typ"),
                  readTable.getString("BezirkID")
            );
            al.add(tuplet);
        }
        return FXCollections.observableArrayList(al);
    }
}