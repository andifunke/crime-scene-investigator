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

    public static final String[] attr = {"VerbrechenID","PersonID","","","","","betrifftO"};

    public betrifftO(String val0, String val1) {
        super(val0,val1,"","","","");
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
                    getAttr(1)+"="+getVal1()+"\n"+
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
            Tuplet tuplet = new betrifftO(
                  readTable.getString(attr[0]),
                  readTable.getString(attr[1])
            );
            al.add(tuplet);
        }
        return FXCollections.observableArrayList(al);
    }

}