package CrimeSceneInvestigator.Tuplets;

import CrimeSceneInvestigator.MainController;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 CREATE TABLE Personen (
 PersonID         INTEGER PRIMARY KEY,
 Name             VARCHAR(256),
 Geschlecht       CHAR(1)       check(Geschlecht in ('w', 'm', 'u')),
 Nationalitaet    VARCHAR(256),
 Geburtsdatum     DATE,
 Todesdatum       DATE
 );
 */

public class Personen extends Tuplet {

    public static final String table = "Personen";
    public static final String[] attr = {
          "PersonID",
          "Name",
          "Geschlecht",
          "Nationalitaet",
          "Geburtsdatum",
          "Todesdatum",
    };
    public static ObservableList<Tuplet> getOL(ResultSet readTable) throws SQLException {
        ArrayList<Tuplet> al = new ArrayList<Tuplet>();
        while (readTable.next()) {
            String[] values = {
                  readTable.getString(attr[0]),
                  readTable.getString(attr[1]),
                  readTable.getString(attr[2]),
                  readTable.getString(attr[3]),
                  MainController.formatDateToDMY(readTable.getString(attr[4])),
                  MainController.formatDateToDMY(readTable.getString(attr[5]))
            };
            al.add(new Personen(values)); // TODO: CHANGE HERE
        }
        return FXCollections.observableArrayList(al);
    }

    public Personen(String[] val) {
        super(attr, val);
        setTable(table);
    }

    public Personen(String[] attr, String[] val) {
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
                    attr[2] + "='" + getVal2() + "'," +
                    attr[3] + "='" + getVal3() + "'," +
                    attr[4] + "='" + MainController.formatDateToYMD(getVal4()) + "'," +
                    attr[5] + "='" + MainController.formatDateToYMD(getVal5()) + "'" +
                    "\n WHERE " + attr[0] + "=" + key[0] + ";";
    }

    public String getInsertQuery() {
        return
              "INSERT INTO " + table +
                    " VALUES (" +
                    "NULL" + "," +
                    "'" + getVal1() + "'," +
                    "'" + getVal2() + "'," +
                    "'" + getVal3() + "'," +
                    "'" + MainController.formatDateToYMD(getVal4()) + "'," +
                    "'" + MainController.formatDateToYMD(getVal5()) + "'" +
                    ");";
    }

}