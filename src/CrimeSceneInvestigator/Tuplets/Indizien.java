package CrimeSceneInvestigator.Tuplets;

import CrimeSceneInvestigator.MainController;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 CREATE TABLE Indizien (
 IndizID          INTEGER PRIMARY KEY,
 angelegtam       DATE          not null,
 Bild             BLOB,
 Text             CLOB,
 PersonID         INT           not null,
 FallID           INT           not null,
 FOREIGN KEY      (PersonID)
 REFERENCES      Polizisten(PersonID),
 FOREIGN KEY      (FallID)
 REFERENCES      Faelle (FallID)
 );
 */

public class Indizien extends Tuplet {

    public static final String[] attr = {"IndizID","angelegtam","Bild","Text","PersonID","FallID","Indizien"};

    public Indizien(String val0, String val1, String val2, String val3, String val4, String val5) {
        super(val0,val1,val2,val3,val4,val5);
        setAttr(Faelle.attr);
    }

    public String toString() {
        int endindex = (getVal3().length() < Tuplet.VARCHAR) ? getVal3().length() : Tuplet.VARCHAR;
        return "["+ getVal0() + "] " + getVal3().substring(0,endindex);
    }

    public String getUpdateQuery(String[] key) {
        String query =
              "UPDATE "+getAttr(7)+"\n"+
                    "SET "+
                    getAttr(0)+"="+getVal0()+", "+
                    getAttr(1)+"="+getVal1()+", "+
                    getAttr(2)+"="+getVal2()+", "+
                    getAttr(3)+"="+getVal3()+", "+
                    getAttr(4)+"="+getVal4()+", "+
                    getAttr(5)+"="+getVal5()+"\n"+
                    "WHERE "+getAttr(0)+"="+key[0]+";";
        return query;
    }

    public String getInsertQuery() {
        String query =
              "INSERT INTO "+getAttr(6)+" "+
                    "VALUES (NULL, '"+getVal1()+"', '"+getVal2()+"', '"+getVal3()+"'"+");";
        return query;
    }

    public static ObservableList<Tuplet> getOL(ResultSet readTable) throws SQLException {
        ArrayList<Tuplet> al = new ArrayList<Tuplet>();
        while (readTable.next()) {
            Tuplet tuplet = new Indizien(
                  readTable.getString(attr[0]),
                  MainController.formatDateToDMY(readTable.getString(attr[1])),
                  readTable.getString(attr[2]),
                  readTable.getString(attr[3]),
                  readTable.getString(attr[4]),
                  readTable.getString(attr[5])
            );
            al.add(tuplet);
        }
        return FXCollections.observableArrayList(al);
    }

}