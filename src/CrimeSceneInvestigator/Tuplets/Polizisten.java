package CrimeSceneInvestigator.Tuplets;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 CREATE TABLE Polizisten(
 PersonID         INTEGER PRIMARY KEY,
 Dienstgrad       VARCHAR(256)  not null,
 FOREIGN KEY      (PersonID)
 REFERENCES      Personen (PersonID)
 );
 */

public class Polizisten extends Personen {

	public static final String[] attr = {"BehoerdeID","Name","Typ","BezirkID","","","Polizisten"};

	public Polizisten(String val0, String val1, String val2, String val3) {
		super(val0,val1,val2,val3);
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
			Tuplet tuplet = new Polizisten(
					readTable.getString(attr[0]),
					readTable.getString(attr[1]),
					readTable.getString(attr[2]),
					readTable.getString(attr[3])
			);
			al.add(tuplet);
		}
		return FXCollections.observableArrayList(al);
	}

}