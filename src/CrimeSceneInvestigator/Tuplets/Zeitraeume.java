package CrimeSceneInvestigator.Tuplets;

import CrimeSceneInvestigator.MainController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 CREATE TABLE Zeitraeume (
 ZeitraumID       INTEGER PRIMARY KEY,
 von              DATE          not null,
 bis              DATE,
 BehoerdeID       INT           not null,
 PersonID         INT           not null,
 FOREIGN KEY      (BehoerdeID)
 REFERENCES      Behoerden (BehoerdeID),
 FOREIGN KEY      (PersonID)
 REFERENCES      Polizisten(PersonID)
 );
 */

public class Zeitraeume extends Tuplet {

    public static final String table = "Zeitraeume";
    public static final String[] attr = {
          "ZeitraumID",
          "von",
          "bis",
          "BehoerdeID",
          "PersonID",
    };
    public static ObservableList<Tuplet> getOL(ResultSet readTable) throws SQLException {
        ArrayList<Tuplet> al = new ArrayList<Tuplet>();
        while (readTable.next()) {
            String[] values = {
                  readTable.getString(attr[0]),
                  MainController.formatDateToDMY(readTable.getString(attr[1])),
                  MainController.formatDateToDMY(readTable.getString(attr[2])),
                  readTable.getString(attr[3]),
                  readTable.getString(attr[4]),
            };
            al.add(new Zeitraeume(values)); // TODO: CHANGE HERE
        }
        return FXCollections.observableArrayList(al);
    }

    public Zeitraeume(String[] val) {
        super(attr, val);
        setTable(table);
    }

    public String toString() {
        return
              "Behörde: [" + getVal3() + "] von " + getVal1() + " bis " + getVal2();
    }

    public String getUpdateQuery(String[] key) {
        return
              "UPDATE " + table + "\n"+
                    " SET "+
                    attr[0] + "='" + getVal0() + "'," +
                    attr[1] + "='" + MainController.formatDateToYMD(getVal1()) + "'," +
                    attr[2] + "='" + MainController.formatDateToYMD(getVal2()) + "'," +
                    attr[3] + "='" + getVal3() + "'," +
                    attr[4] + "='" + getVal4() + "'" +
                    "\n WHERE " + attr[0] + "=" + key[0] + ";";
    }

    public String getInsertQuery() {
        return
              "INSERT INTO " + table +
                    " VALUES (" +
                    "NULL" + "," +
                    "'" + MainController.formatDateToYMD(getVal1()) + "'," +
                    "'" + MainController.formatDateToYMD(getVal2()) + "'," +
                    "'" + getVal3() + "'," +
                    "'" + getVal4() + "'" +
                    ");";
    }

}