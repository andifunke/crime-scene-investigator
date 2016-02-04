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

    public static final String[] attr = {"Name","Beschreibung","","","","","Arten"};

    public Arten(String val0, String val1) {
        super(val0,val1,"","","","");
        setAttr(Faelle.attr);
    }

    public String toString() {
        return getVal0();
    }

    public String getUpdateQuery(String[] key) {
        String query =
              "UPDATE "+getAttr(7)+"\n"+
                    "SET "+
                    getAttr(0)+"="+getVal0()+", "+
                    getAttr(1)+"="+getVal1()+", "+
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
            Tuplet tuplet = new Arten(
                  readTable.getString(attr[0]),
                  readTable.getString(attr[1])
            );
            al.add(tuplet);
        }
        return FXCollections.observableArrayList(al);
    }

}