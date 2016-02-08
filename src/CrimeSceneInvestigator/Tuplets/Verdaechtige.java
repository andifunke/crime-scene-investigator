package CrimeSceneInvestigator.Tuplets;

/**
 * CREATE TABLE Verdaechtige (
 * PersonID         INTEGER PRIMARY KEY,
 * FOREIGN KEY      (PersonID)
 * REFERENCES      Personen (PersonID)
 * );
 */

public class Verdaechtige extends Personen {

	public static final String table = "Verdaechtige";

	public Verdaechtige(String[] val) {
		super(val);
		setTable(table);
		setAttr(attr);
	}

	public String getUpdateQuery2(String[] key) {
		return
				"UPDATE " + table + "\n" +
						" SET " +
						attr[0] + "='" + getVal0() + "'" +
						"\n WHERE " + attr[0] + "=" + key[0] + ";";
	}

	public String getInsertQuery2() {
		return
				"INSERT INTO " + table +
						" VALUES (" +
						"'" + getVal0() + "'" +
						");";
	}

}