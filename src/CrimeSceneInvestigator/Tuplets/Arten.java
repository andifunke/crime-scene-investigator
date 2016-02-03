package CrimeSceneInvestigator.Tuplets;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 CREATE TABLE Arten (
 Name             VARCHAR(256)  not null,
 Beschreibung     CLOB          not null,
 PRIMARY KEY      (Name)
 );
 */

public class Arten extends Tuplet {

    public void setAttr0(String attr0) {}
    public void setAttr1(String attr1) {}
    public void setAttr2(String attr2) {}
    public void setAttr3(String attr3) {}
    public void setAttr4(String attr4) {}
    public void setAttr5(String attr5) {}
    // Felder
    private final SimpleStringProperty Name;
    public String getName() { return Name.get(); }
    public void setName(String Name) { this.Name.set(Name); }

    private final SimpleStringProperty Beschreibung;
    public String getBeschreibung() { return Beschreibung.get(); }
    public void setBeschreibung(String Beschreibung) { this.Beschreibung.set(Beschreibung); }

    // Konstruktoren
    public Arten(String Name, String Beschreibung) {
        this.Name = new SimpleStringProperty(Name);
        this.Beschreibung = new SimpleStringProperty(Beschreibung);
    }

    // Methoden
    public String getValue(String attribute) {
        switch (attribute) {
            case ("Name"):
                return Name.get();
            case ("Beschreibung"):
                return Beschreibung.get();
        }
        return null;
    }

    public String toString() {
        return "["+Name.get() + "] " + Beschreibung.get();
    }

    public static ObservableList<Tuplet> getOL(ResultSet readTable) throws SQLException {
        ArrayList<Tuplet> al = new ArrayList<Tuplet>();
        while (readTable.next()) {
            Tuplet tuplet = new Arten(
                  readTable.getString("Name"),
                  readTable.getString("Beschreibung")
            );
            al.add(tuplet);
        }
        return FXCollections.observableArrayList(al);
    }
}