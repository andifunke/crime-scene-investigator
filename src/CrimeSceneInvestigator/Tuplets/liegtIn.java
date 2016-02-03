package CrimeSceneInvestigator.Tuplets;

/**
 * Created by Funke on 23.01.2016.
 */
public class liegtIn extends Tuplet {

    private int subBezirkID;
    private int supBezirkID;

    public liegtIn(int subBezirkID, int supBezirkID) {
        this.subBezirkID = subBezirkID;
        this.supBezirkID = supBezirkID;
    }

    public int getSubBezirkID() {
        return subBezirkID;
    }

    public void setSubBezirkID(int subBezirkID) {
        this.subBezirkID = subBezirkID;
    }

    public int getSupBezirkID() {
        return supBezirkID;
    }

    public void setSupBezirkID(int supBezirkID) {
        this.supBezirkID = supBezirkID;
    }

    public String getValue(String attribute) {
        return null;
    }

    public String toString() {
        return "";
    }

}
