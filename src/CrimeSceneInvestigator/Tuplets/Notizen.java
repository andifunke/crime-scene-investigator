package CrimeSceneInvestigator.Tuplets;

import CrimeSceneInvestigator.MainController;
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

    public static final String table = "Notizen";
    public static final String[] attr = {
          "NotizID",
          "angelegtam",
          "Text",
          "PersonID",
          "FallID",
    };
    public static ObservableList<Tuplet> getOL(ResultSet readTable) throws SQLException {
        ArrayList<Tuplet> al = new ArrayList<Tuplet>();
        while (readTable.next()) {
            String[] values = {
                  readTable.getString(attr[0]),
                  MainController.formatDateToDMY(readTable.getString(attr[1])),
                  readTable.getString(attr[2]),
                  readTable.getString(attr[3]),
                  readTable.getString(attr[4]),
            };
            al.add(new Notizen(values)); // TODO: CHANGE HERE
        }
        return FXCollections.observableArrayList(al);
    }

    public Notizen(String[] val) {
        super(val);
        setTable(table);
        setAttr(attr);
    }

    public String toString() {
        return
              "[" + getVal0() + "] " + getVal1();
    }

    public String getUpdateQuery(String[] key) {
        return
              "UPDATE " + table + "\n"+
                    " SET "+
                    attr[0] + "= " + getVal0() + ", " +
                    attr[1] + "='" + MainController.formatDateToYMD(getVal1()) + "', " +
                    attr[2] + "='" + getVal2() + "', " +
                    attr[3] + "='" + getVal3() + "', " +
                    attr[4] + "='" + getVal4() + "', " +
                    attr[5] + "='" + getVal5() + "' " +
                    "\n WHERE " + attr[0] + "=" + key[0] + ";";
    }

    public String getInsertQuery() {
        return
              "INSERT INTO " + table +
                    " VALUES (" +
                    "NULL" + ", " +
                    "'" + MainController.formatDateToYMD(getVal1()) + "', " +
                    getVal2() + ", " +
                    getVal3() + ", " +
                    getVal4() + ", " +
                    getVal5() + " " +
                    ");";
    }

}