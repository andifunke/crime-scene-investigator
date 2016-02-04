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

    public static final String[] attr = {"PersonID","FallID","von","bis","","","arbeitetan"};

    public arbeitetan(String val0, String val1, String val2, String val3) {
        super(val0,val1,val2,val3,"","");
        setAttr(Faelle.attr);
    }

    public String toString() {
        return "["+ getVal0() + "] " + getVal1();
    }

    public String getUpdateQuery(String[] key) {
        String query =
              "UPDATE "+getAttr(7)+"\n"+
                    "SET "+
                    getAttr(0)+"="+getVal0()+", "+
                    getAttr(1)+"="+getVal1()+", "+
                    getAttr(2)+"="+getVal2()+", "+
                    getAttr(3)+"="+getVal3()+"\n"+
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
            Tuplet tuplet = new arbeitetan(
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