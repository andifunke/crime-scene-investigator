package CrimeSceneInvestigator.Tuplets;

/**
 * Created by Funke on 23.01.2016.
 */
public class betrifftOpfer extends Tuplet {

    private int verbrechenID;
    private int personID;

    public betrifftOpfer(int personID, int verbrechenID) {
        this.personID = personID;
        this.verbrechenID = verbrechenID;
    }

    public int getPersonID() {
        return personID;
    }

    public void setPersonID(int personID) {
        this.personID = personID;
    }

    public int getVerbrechenID() {
        return verbrechenID;
    }

    public void setVerbrechenID(int verbrechenID) {
        this.verbrechenID = verbrechenID;
    }
}
