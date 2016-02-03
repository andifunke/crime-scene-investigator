package CrimeSceneInvestigator.Tuplets;

import javafx.beans.property.SimpleIntegerProperty;
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
 *
 * Created by Funke on 23.01.2016.
 */
public class Verbrechen extends Tuplet {

    private final SimpleIntegerProperty verbrechenID;
    public void setVerbrechenID(int verbrechenID) {
        this.verbrechenID.set(verbrechenID);
    }

    private final SimpleStringProperty name;
    public void setName(String name) {
        this.name.set(name);
    }

    private final SimpleStringProperty datum;
    public void setDatum(String datum) {
        this.datum.set(datum);
    }

    private final SimpleIntegerProperty fallID;
    public void setFallID(int fallID) {
        this.fallID.set(fallID);
    }

    private final SimpleStringProperty artName;
    public void setArtName(String artName) {
        this.artName.set(artName);
    }

    private final SimpleIntegerProperty bezirkID;
    public void setBezirkID(int bezirkID) {
        this.bezirkID.set(bezirkID);
    }

    public String getValue(String attribute) {
        switch (attribute) {
            case ("verbrechenid"):
                return Integer.toString(verbrechenID.get());
            case ("name"):
                return name.get();
            case ("datum"):
                return datum.get();
            case ("fallid"):
                return Integer.toString(fallID.get());
            case ("artname"):
                return artName.get();
            case ("bezirkid"):
                return Integer.toString(bezirkID.get());
        }
        return null;
    }

    public String toString() {
        return "["+Integer.toString(verbrechenID.get())+"] " + artName.get() + ": " + name.get();
    }

    public Verbrechen(int verbrechenID, String name, int fallID, String artName, int bezirkID) {
        this(verbrechenID, name, "", fallID, artName, bezirkID);
    }

    public Verbrechen(int verbrechenID, String name, String datum, int fallID, String artName, int bezirkID) {
        this.name = new SimpleStringProperty(name);
        this.datum = new SimpleStringProperty(datum);
        this.fallID = new SimpleIntegerProperty(fallID);
        this.verbrechenID = new SimpleIntegerProperty(verbrechenID);
        this.artName = new SimpleStringProperty(artName);
        this.bezirkID = new SimpleIntegerProperty(bezirkID);
    }

    public static ObservableList<Tuplet> getOL(ResultSet readTable) throws SQLException {
        ArrayList<Tuplet> al = new ArrayList<Tuplet>();
        while (readTable.next()) {
            Tuplet tuplet = new Verbrechen(
                    readTable.getInt("verbrechenid"),
                    readTable.getString("name"),
                    readTable.getString("datum"),
                    readTable.getInt("fallid"),
                    readTable.getString("artname"),
                    readTable.getInt("bezirkid")
            );
            al.add(tuplet);
        }
        return FXCollections.observableArrayList(al);
    }
}
