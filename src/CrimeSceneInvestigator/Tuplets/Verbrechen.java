package CrimeSceneInvestigator.Tuplets;

import CrimeSceneInvestigator.MainController;
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
 */

public class Verbrechen extends Tuplet {

    public static final String table = "Verbrechen";
    public static final String[] attr = {
          "VerbrechenID",
          "Name",
          "Datum",
          "FallID",
          "ArtName",
          "BezirkID",
    };
    public static ObservableList<Tuplet> getOL(ResultSet readTable) throws SQLException {
        ArrayList<Tuplet> al = new ArrayList<Tuplet>();
        while (readTable.next()) {
            String[] values = {
                  readTable.getString(attr[0]),
                  readTable.getString(attr[1]),
                  MainController.formatDateToDMY(readTable.getString(attr[2])),
                  readTable.getString(attr[3]),
                  readTable.getString(attr[4]),
                  readTable.getString(attr[5]),
            };
            al.add(new Verbrechen(values)); // TODO: CHANGE HERE
        }
        return FXCollections.observableArrayList(al);
    }

    public Verbrechen(String[] val) {
        super(attr, val);
        setTable(table);
    }

    public String toString() {
        return
              "[" + getVal0() + "] " + getVal1();
    }

    public String getUpdateQuery(String[] key) {
        return
              "UPDATE " + table + "\n"+
                    " SET "+
                    attr[0] + "='" + getVal0() + "'," +
                    attr[1] + "='" + getVal1() + "'," +
                    attr[2] + "='" + MainController.formatDateToYMD(getVal2()) + "'," +
                    attr[3] + "='" + getVal3() + "'," +
                    attr[4] + "='" + getVal4() + "'," +
                    attr[5] + "='" + getVal5() + "'" +
                    "\n WHERE " + attr[0] + "=" + key[0] + ";";
    }

    public String getInsertQuery() {
        return
              "INSERT INTO " + table +
                    " VALUES (" +
                    "NULL" + "," +
                    "'" + getVal1() + "'," +
                    "'" + MainController.formatDateToYMD(getVal2()) + "'," +
                    "'" + getVal3() + "'," +
                    "'" + getVal4() + "'," +
                    "'" + getVal5() + "'" +
                    ");";
    }

}