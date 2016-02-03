package CrimeSceneInvestigator.Tuplets;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 CREATE TABLE Notizen (
 NotizID          INTEGER PRIMARY KEY,
 angelegtam       DATE           not null,
 Text             CLOB           not null,
 PersonID         INT            not null,
 FallID           INT            not null,
 FOREIGN KEY      (PersonID)
 REFERENCES      Polizisten(PersonID),
 FOREIGN KEY      (FallID)
 REFERENCES      Faelle (FallID)
 );
 */

public class Notizen extends Tuplet {

    // Felder
    private final SimpleStringProperty NotizID;
    public String getNotizID() { return NotizID.get(); }
    public void setNotizID(String NotizID) { this.NotizID.set(NotizID); }

    private final SimpleStringProperty angelegtam;
    public String getangelegtam() { return angelegtam.get(); }
    public void setangelegtam(String angelegtam) { this.angelegtam.set(angelegtam); }

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
    public Notizen(String NotizID, String angelegtam, String Text, String PersonID, String FallID) {
        this.NotizID = new SimpleStringProperty(NotizID);
        this.angelegtam = new SimpleStringProperty(angelegtam);
        this.Text = new SimpleStringProperty(Text);
        this.PersonID = new SimpleStringProperty(PersonID);
        this.FallID = new SimpleStringProperty(FallID);
    }

    // Methoden
    public String getValue(String attribute) {
        switch (attribute) {
            case ("NotizID"):
                return NotizID.get();
            case ("angelegtam"):
                return angelegtam.get();
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
        return "["+NotizID.get() + "] " + angelegtam.get();
    }

    public static ObservableList<Tuplet> getOL(ResultSet readTable) throws SQLException {
        ArrayList<Tuplet> al = new ArrayList<Tuplet>();
        while (readTable.next()) {
            Tuplet tuplet = new Notizen(
                  readTable.getString("NotizID"),
                  readTable.getString("angelegtam"),
                  readTable.getString("Text"),
                  readTable.getString("PersonID"),
                  readTable.getString("FallID")
            );
            al.add(tuplet);
        }
        return FXCollections.observableArrayList(al);
    }
}