package DataBaseApplication.Tuplets;

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

    public int getPersonID() {
        return personID;
    }

    public void setPersonID(int personID) {
        this.personID = personID;
    }

    public boolean isUeberfuehrt() {
        return ueberfuehrt;
    }

    public void setUeberfuehrt(boolean ueberfuehrt) {
        this.ueberfuehrt = ueberfuehrt;
    }

    public int getVerbrechenID() {
        return verbrechenID;
    }

    public void setVerbrechenID(int verbrechenID) {
        this.verbrechenID = verbrechenID;
    }
}
