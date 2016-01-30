package CrimeSceneInvestigator.Tuplets;

/**
 * Created by Funke on 23.01.2016.
 */
public class Bezirk extends Tuplet {

    private int bezirkID;

    public Bezirk(int bezirkID) {
        this.bezirkID = bezirkID;
    }

    public int getBezirkID() {
        return bezirkID;
    }

    public void setBezirkID(int bezirkID) {
        this.bezirkID = bezirkID;
    }
}
