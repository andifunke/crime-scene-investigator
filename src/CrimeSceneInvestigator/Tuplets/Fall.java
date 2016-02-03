package CrimeSceneInvestigator.Tuplets;

import javafx.beans.property.SimpleIntegerProperty;
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
 *
 * Created by Funke on 23.01.2016.
 */
public class Fall extends Tuplet {

    private final SimpleIntegerProperty fallID;
    private final SimpleStringProperty name;
    private final SimpleStringProperty eroeffnungsdatum;
    private final SimpleStringProperty enddatum;

    public Fall(int fallID, String name, String eroeffnungsdatum) {
        this(fallID, name, eroeffnungsdatum, "");
    }

    public Fall(int fallID, String name, String eroeffnungsdatum, String enddatum) {
        this.fallID = new SimpleIntegerProperty(fallID);
        this.name = new SimpleStringProperty(name);
        this.eroeffnungsdatum = new SimpleStringProperty(eroeffnungsdatum);
        this.enddatum = new SimpleStringProperty(enddatum);
    }

    public void setFallID(int fallID) {
        this.fallID.set(fallID);
    }
    public void setName(String name) {
        this.name.set(name);
    }
    public void setEroeffnungsdatum(String eroeffnungsdatum) {
        this.eroeffnungsdatum.set(eroeffnungsdatum);
    }
    public void setEnddatum(String enddatum) {
        this.enddatum.set(enddatum);
    }
    public int getFallID() { return fallID.get(); }
    public String getName() { return name.get(); }
    public String getEroeffnungsdatum() { return eroeffnungsdatum.get(); }
    public String getEnddatum() { return enddatum.get(); }


    public String getValue(String attribute) {
        switch (attribute) {
            case ("fallid"):
                return Integer.toString(fallID.get());
            case ("name"):
                return name.get();
            case ("eroeffnungsdatum"):
                return eroeffnungsdatum.get();
            case ("enddatum"):
                return enddatum.get();
        }
        return null;
    }

    public String toString() {
        return "["+Integer.toString(fallID.get()) + "] " + name.get();
    }

    public static ObservableList<Tuplet> getOL(ResultSet readTable) throws SQLException {
        ArrayList<Tuplet> al = new ArrayList<Tuplet>();
        while (readTable.next()) {
            Tuplet tuplet = new Fall(
                readTable.getInt("fallid"),
                readTable.getString("name"),
                readTable.getString("eroeffnungsdatum"),
                readTable.getString("enddatum")
            );
            al.add(tuplet);
        }
        return FXCollections.observableArrayList(al);
    }
}