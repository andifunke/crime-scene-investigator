package CrimeSceneInvestigator.Tuplets;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 CREATE TABLE liegtin (
 SubBezirkID      INT           not null,
 SupBezirkID      INT           not null,
 PRIMARY KEY      (SubBezirkID),
 FOREIGN KEY      (SubBezirkID)
 REFERENCES      Bezirke (BezirkID),
 FOREIGN KEY      (SupBezirkID)
 REFERENCES      Bezirke (BezirkID)
 );
 */

public class liegtin extends Tuplet {

    public void setAttr0(String attr0) {}
    public void setAttr1(String attr1) {}
    public void setAttr2(String attr2) {}
    public void setAttr3(String attr3) {}
    public void setAttr4(String attr4) {}
    public void setAttr5(String attr5) {}
    // Felder
    private final SimpleStringProperty SubBezirkID;
    public String getSubBezirkID() { return SubBezirkID.get(); }
    public void setSubBezirkID(String SubBezirkID) { this.SubBezirkID.set(SubBezirkID); }

    private final SimpleStringProperty SupBezirkID;
    public String getSupBezirkID() { return SupBezirkID.get(); }
    public void setSupBezirkID(String SupBezirkID) { this.SupBezirkID.set(SupBezirkID); }


    // Konstruktoren
    public liegtin(String SubBezirkID, String SupBezirkID) {
        this.SubBezirkID = new SimpleStringProperty(SubBezirkID);
        this.SupBezirkID = new SimpleStringProperty(SupBezirkID);
    }

    // Methoden
    public String getValue(String attribute) {
        switch (attribute) {
            case ("SubBezirkID"):
                return SubBezirkID.get();
            case ("SupBezirkID"):
                return SupBezirkID.get();
        }
        return null;
    }

    public String toString() {
        return "["+SubBezirkID.get() + "] " + SupBezirkID.get();
    }

    public static ObservableList<Tuplet> getOL(ResultSet readTable) throws SQLException {
        ArrayList<Tuplet> al = new ArrayList<Tuplet>();
        while (readTable.next()) {
            Tuplet tuplet = new liegtin(
                  readTable.getString("SubBezirkID"),
                  readTable.getString("SupBezirkID")
            );
            al.add(tuplet);
        }
        return FXCollections.observableArrayList(al);
    }
}