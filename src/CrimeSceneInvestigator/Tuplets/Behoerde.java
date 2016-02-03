package CrimeSceneInvestigator.Tuplets;

/**
 * Created by Funke on 23.01.2016.
 */
public class Behoerde extends Tuplet {

    private int behoerdeID;
    private String name;
    private String typ;
    private int bezirkID;

    public Behoerde(int behoerdeID, String name, String typ, int bezirkID) {
        this.behoerdeID = behoerdeID;
        this.name = name;
        this.typ = typ;
        this.bezirkID = bezirkID;
    }

    public String getValue(String attribute) {
        return null;
    }

    public String toString() {
        return "";
    }


}
