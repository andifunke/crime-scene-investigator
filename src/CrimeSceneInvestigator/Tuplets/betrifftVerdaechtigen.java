package CrimeSceneInvestigator.Tuplets;

/**
 * Created by Funke on 23.01.2016.
 */
public class betrifftVerdaechtigen extends Tuplet {

    private int verbrechenID;
    private int personID;
    private boolean ueberfuehrt;

    public betrifftVerdaechtigen(int personID, boolean ueberfuehrt, int verbrechenID) {
        this.personID = personID;
        this.ueberfuehrt = ueberfuehrt;
        this.verbrechenID = verbrechenID;
    }

    public String getValue(String attribute) {
        return null;
    }

    public String toString() {
        return "";
    }


}
