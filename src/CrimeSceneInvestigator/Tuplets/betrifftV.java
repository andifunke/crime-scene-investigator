package CrimeSceneInvestigator.Tuplets;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 CREATE TABLE betrifftV (
 VerbrechenID     INT           not null,
 PersonID         INT           not null,
 ueberfuehrt      BOOLEAN       not null,
 PRIMARY KEY      (VerbrechenID, PersonID),
 FOREIGN KEY      (VerbrechenID)
 REFERENCES      Verbrechen (VerbrechenID),
 FOREIGN KEY      (PersonID)
 REFERENCES      Verdaechtige (PersonID)
 );
 */

public class betrifftV extends Tuplet {

    public static final String table = "betrifftV";
    public static final String[] attr = {
          "VerbrechenID",
          "PersonID",
          "ueberfuehrt",
    };
    public static ObservableList<Tuplet> getOL(ResultSet readTable) throws SQLException {
        ArrayList<Tuplet> al = new ArrayList<Tuplet>();
        while (readTable.next()) {
            String[] values = {
                  readTable.getString(attr[0]),
                  readTable.getString(attr[1]),
                  readTable.getString(attr[2])
            };
            al.add(new betrifftV(values)); // TODO: CHANGE HERE
        }
        return FXCollections.observableArrayList(al);
    }

    public betrifftV(String[] val) {
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
                    attr[2] + "='" + getVal2() + "'" +
                    "\n WHERE " + attr[0] + "='" + key[0] + "'" +
                    "\n AND   " + attr[1] + "='" + key[1] + "';";
    }

    public String getInsertQuery() {
        return
              "INSERT INTO " + table +
                    " VALUES (" +
                    "'" + getVal0() + "'," +
                    "'" + getVal1() + "'," +
                    "'" + getVal2() + "'" +
                    ");";
    }

}