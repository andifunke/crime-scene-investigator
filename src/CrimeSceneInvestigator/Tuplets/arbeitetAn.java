package CrimeSceneInvestigator.Tuplets;

import CrimeSceneInvestigator.MainController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 CREATE TABLE arbeitetan (
 PersonID         INT           not null,
 FallID           INT           not null,
 von              DATE          not null,
 bis              DATE,
 PRIMARY KEY      (PersonID, FallID),
 FOREIGN KEY      (PersonID)
 REFERENCES      Polizisten(PersonID),
 FOREIGN KEY      (FallID)
 REFERENCES      Faelle (FallID)
 ); 
 */

public class arbeitetan extends Tuplet {

    public static final String table = "arbeitetan";
    public static final String[] attr = {
          "PersonID",
          "FallID",
          "von",
          "bis",
    };
    public static ObservableList<Tuplet> getOL(ResultSet readTable) throws SQLException {
        ArrayList<Tuplet> al = new ArrayList<Tuplet>();
        while (readTable.next()) {
            String[] values = {
                  readTable.getString(attr[0]),
                  readTable.getString(attr[1]),
                  MainController.formatDateToDMY(readTable.getString(attr[2])),
                  MainController.formatDateToDMY(readTable.getString(attr[3]))
            };
            al.add(new arbeitetan(values)); // TODO: CHANGE HERE
        }
        return FXCollections.observableArrayList(al);
    }

    public arbeitetan(String[] val) {
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
                    attr[3] + "='" + MainController.formatDateToYMD(getVal3()) + "'" +
                    "\n WHERE " + attr[0] + "='" + key[0] + "'" +
                    "\n AND "   + attr[1] + "='" + key[1] + "';";
    }

    public String getInsertQuery() {
        return
              "INSERT INTO " + table +
                    " VALUES (" +
                    "'" + getVal0() + "'," +
                    "'" + getVal1() + "'," +
                    "'" + MainController.formatDateToYMD(getVal2()) + "'," +
                    "'" + MainController.formatDateToYMD(getVal3()) + "'" +
                    ");";
    }

}