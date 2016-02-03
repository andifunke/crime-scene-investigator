package CrimeSceneInvestigator.Tuplets;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 CREATE TABLE Personen (
 PersonID         INTEGER PRIMARY KEY,
 Name             VARCHAR(256),
 Geschlecht       CHAR(1)       check(Geschlecht in ('w', 'm', 'u')),
 Nationalitaet    VARCHAR(256),
 Geburtsdatum     DATE,
 Todesdatum       DATE
 );
 */

public class Personen extends Tuplet {

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

    private final SimpleStringProperty Name;
    public String getName() { return Name.get(); }
    public void setName(String Name) { this.Name.set(Name); }

    private final SimpleStringProperty Geschlecht;
    public String getGeschlecht() { return Geschlecht.get(); }
    public void setGeschlecht(String Geschlecht) { this.Geschlecht.set(Geschlecht); }

    private final SimpleStringProperty Nationalitaet;
    public String getNationalitaet() { return Nationalitaet.get(); }
    public void setNationalitaet(String Nationalitaet) { this.Nationalitaet.set(Nationalitaet); }

    private final SimpleStringProperty Geburtsdatum;
    public String getGeburtsdatum() { return Geburtsdatum.get(); }
    public void setGeburtsdatum(String Geburtsdatum) { this.Geburtsdatum.set(Geburtsdatum); }

    private final SimpleStringProperty Todesdatum;
    public String getTodesdatum() { return Todesdatum.get(); }
    public void setTodesdatum(String Todesdatum) { this.Todesdatum.set(Todesdatum); }


    // Konstruktoren
    public Personen(String PersonID) {
        this(PersonID,"","","","","");
    }

    public Personen(String PersonID, String Name, String Geschlecht, String Nationalitaet, String Geburtsdatum, String Todesdatum) {
        this.PersonID = new SimpleStringProperty(PersonID);
        this.Name = new SimpleStringProperty(Name);
        this.Geschlecht = new SimpleStringProperty(Geschlecht);
        this.Nationalitaet = new SimpleStringProperty(Nationalitaet);
        this.Geburtsdatum = new SimpleStringProperty(Geburtsdatum);
        this.Todesdatum = new SimpleStringProperty(Todesdatum);
    }

    // Methoden
    public String getValue(String attribute) {
        switch (attribute) {
            case ("PersonID"):
                return PersonID.get();
            case ("Name"):
                return Name.get();
            case ("Geschlecht"):
                return Geschlecht.get();
            case ("Nationalitaet"):
                return Nationalitaet.get();
            case ("Geburtsdatum"):
                return Geburtsdatum.get();
            case ("Todesdatum"):
                return Todesdatum.get();
        }
        return null;
    }

    public String toString() {
        return "["+PersonID.get() + "] " + Name.get();
    }

    public static ObservableList<Tuplet> getOL(ResultSet readTable) throws SQLException {
        ArrayList<Tuplet> al = new ArrayList<Tuplet>();
        while (readTable.next()) {
            Tuplet tuplet = new Personen(
                  readTable.getString("PersonID"),
                  readTable.getString("Name"),
                  readTable.getString("Geschlecht"),
                  readTable.getString("Nationalitaet"),
                  readTable.getString("Geburtsdatum"),
                  readTable.getString("Todesdatum")
            );
            al.add(tuplet);
        }
        return FXCollections.observableArrayList(al);
    }
}