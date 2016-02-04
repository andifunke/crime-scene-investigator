package CrimeSceneInvestigator.Tuplets;

import CrimeSceneInvestigator.MainController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 CREATE TABLE Faelle (
 FallID           INTEGER PRIMARY KEY,
 Name             VARCHAR(256) not null,
 Eroeffnungsdatum DATE         not null,
 Enddatum         DATE
 );
 */

public class Faelle extends Tuplet {

    public static final String[] attr = {"FallID","Name","Eroeffnungsdatum","Enddatum","","","Faelle"};

    public Faelle(String val0, String val1, String val2, String val3) {
        super(val0,val1,val2,val3,"","");
        setAttr(Faelle.attr);
    }

    public String toString() {
        return "["+ getVal0() + "] " + getVal1();
    }

    public String getUpdateQuery(String[] key) {
        String query =
                "UPDATE "+getAttr(6)+"\n"+
                "SET "+
                getAttr(0)+"="+getVal0()+", "+
                getAttr(1)+"='"+getVal1()+"', "+
                getAttr(2)+"='"+MainController.formatDateToYMD(getVal2())+"', "+
                getAttr(3)+"='"+MainController.formatDateToYMD(getVal3())+"'\n"+
                "WHERE "+getAttr(0)+"="+key[0]+";";
        return query;
    }

    public String getInsertQuery() {
        String query =
                "INSERT INTO "+getAttr(6)+" "+
                "VALUES (NULL, '"+getVal1()+"', '"+MainController.formatDateToYMD(getVal2())+"', '"+MainController.formatDateToYMD(getVal3())+"'"+");";
        return query;
    }

    public static ObservableList<Tuplet> getOL(ResultSet readTable) throws SQLException {
        ArrayList<Tuplet> al = new ArrayList<Tuplet>();
        while (readTable.next()) {
            Tuplet tuplet = new Faelle(
                  readTable.getString(attr[0]),
                  readTable.getString(attr[1]),
                  MainController.formatDateToDMY(readTable.getString(attr[2])),
                  MainController.formatDateToDMY(readTable.getString(attr[3]))
            );
            al.add(tuplet);
        }
        return FXCollections.observableArrayList(al);
    }

}