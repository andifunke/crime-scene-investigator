package CrimeSceneInvestigator.Tuplets;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 CREATE TABLE Arten (
 Name             VARCHAR(256)  not null,
 Beschreibung     CLOB          not null,
 PRIMARY KEY      (Name)
 );
 */

public class Arten extends Tuplet {

    public static final String table = "Arten";
    public static final String[] attr = {
          "Name",
          "Beschreibung",
    };
    public static ObservableList<Tuplet> getOL(ResultSet readTable) throws SQLException {
        ArrayList<Tuplet> al = new ArrayList<Tuplet>();
        while (readTable.next()) {
            String[] values = {
                  readTable.getString(attr[0]),
                  readTable.getString(attr[1]),
            };
            al.add(new Arten(values)); // TODO: CHANGE HERE
        }
        return FXCollections.observableArrayList(al);
    }

    public Arten(String[] val) {
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
                    attr[1] + "='" + getVal1() + "'" +
                    "\n WHERE " + attr[0] + "='" + key[0] + "';";
    }

    public String getInsertQuery() {
        return
              "INSERT INTO " + table +
                    " VALUES (" +
                    "'" + getVal0() + "'," +
                    "'" + getVal1() + "'" +
                    ");";
    }

}