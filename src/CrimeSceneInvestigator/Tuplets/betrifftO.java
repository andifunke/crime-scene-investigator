package CrimeSceneInvestigator.Tuplets;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 CREATE TABLE betrifftO (
 VerbrechenID     INT           not null,
 PersonID         INT           not null,
 PRIMARY KEY      (VerbrechenID, PersonID),
 FOREIGN KEY      (VerbrechenID)
 REFERENCES      Verbrechen (VerbrechenID),
 FOREIGN KEY      (PersonID)
 REFERENCES      Opfer (PersonID)
 );
 */

public class betrifftO extends Tuplet {

    public static final String table = "betrifftO";
    public static final String[] attr = {
          "VerbrechenID",
          "PersonID",
    };
    public static ObservableList<Tuplet> getOL(ResultSet readTable) throws SQLException {
        ArrayList<Tuplet> al = new ArrayList<Tuplet>();
        while (readTable.next()) {
            String[] values = {
                  readTable.getString(attr[0]),
                  readTable.getString(attr[1]),
            };
            al.add(new betrifftO(values)); // TODO: CHANGE HERE
        }
        return FXCollections.observableArrayList(al);
    }

    public betrifftO(String[] val) {
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
                    attr[1] + "='" + getVal1() + "', " +
                    " WHERE " + attr[0] + "=" + key[0] + ";";
    }

    public String getInsertQuery() {
        return
              "INSERT INTO " + table +
                    " VALUES (" +
                    getVal0() + ", " +
                    getVal1() + " " +
                    ");";
    }

}