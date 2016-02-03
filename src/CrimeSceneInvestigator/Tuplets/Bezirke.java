package CrimeSceneInvestigator.Tuplets;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 CREATE TABLE Bezirke (
 BezirkID         INTEGER PRIMARY KEY,
 Name             VARCHAR(256)  not null,
 Typ              VARCHAR(256)  not null
 );
 */

public class Bezirke extends Tuplet {

    public void setAttr0(String attr0) {}
    public void setAttr1(String attr1) {}
    public void setAttr2(String attr2) {}
    public void setAttr3(String attr3) {}
    public void setAttr4(String attr4) {}
    public void setAttr5(String attr5) {}
    // Felder
    private final SimpleStringProperty BezirkID;
    public String getBezirkID() { return BezirkID.get(); }
    public void setBezirkID(String BezirkID) { this.BezirkID.set(BezirkID); }


    // Konstruktoren
    public Bezirke(String BezirkID) {
        this.BezirkID = new SimpleStringProperty(BezirkID);
    }

    // Methoden
    public String getValue(String attribute) {
        switch (attribute) {
            case ("BezirkID"):
                return BezirkID.get();
        }
        return null;
    }

    public String toString() {
        return "["+BezirkID.get() + "] ";
    }

    public static ObservableList<Tuplet> getOL(ResultSet readTable) throws SQLException {
        ArrayList<Tuplet> al = new ArrayList<Tuplet>();
        while (readTable.next()) {
            Tuplet tuplet = new Bezirke(
                  readTable.getString("BezirkID")
            );
            al.add(tuplet);
        }
        return FXCollections.observableArrayList(al);
    }
}