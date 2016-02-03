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

    public String getValue(String attribute) {
        return null;
    }

    public String toString() {
        return "";
    }


}
