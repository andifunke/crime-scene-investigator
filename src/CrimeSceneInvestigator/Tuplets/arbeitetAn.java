package CrimeSceneInvestigator.Tuplets;

import javafx.beans.property.SimpleIntegerProperty;
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
 *
 * Created by Funke on 23.01.2016.
 */
public class arbeitetAn extends Tuplet {

    private final SimpleIntegerProperty fallID;
    private final SimpleIntegerProperty personID;
    private final SimpleStringProperty von;
    private final SimpleStringProperty bis;

    public arbeitetAn(int fallID, int personID, String von) {
        this(fallID, personID, von, "");
    }

    public arbeitetAn(int fallID, int personID, String von, String bis) {
        this.fallID = new SimpleIntegerProperty(fallID);
        this.personID = new SimpleIntegerProperty(personID);
        this.von = new SimpleStringProperty(von);
        this.bis = new SimpleStringProperty(bis);
    }

    public void setFallID(int fallID) {
        this.fallID.set(fallID);
    }
    public void setPersonID(int personID) {
        this.personID.set(personID);
    }
    public void setVon(String von) {
        this.von.set(von);
    }
    public void setBis(String bis) { this.bis.set(bis); }

    public String getValue(String attribute) {
        return null;
    }

    public String toString() {
        return "";
    }

    public static ObservableList<Tuplet> getOL(ResultSet readTable) throws SQLException {
        ArrayList<Tuplet> al = new ArrayList<Tuplet>();
        while (readTable.next()) {
            Tuplet tuplet = new arbeitetAn(
                readTable.getInt("fallid"),
                readTable.getInt("personid"),
                readTable.getString("von"),
                readTable.getString("bis")
            );
            al.add(tuplet);
        }
        return FXCollections.observableArrayList(al);
    }
}
