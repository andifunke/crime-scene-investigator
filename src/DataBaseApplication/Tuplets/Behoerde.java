package DataBaseApplication.Tuplets;

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

    public int getBehoerdeID() {
        return behoerdeID;
    }

    public void setBehoerdeID(int behoerdeID) {
        this.behoerdeID = behoerdeID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTyp() {
        return typ;
    }

    public void setTyp(String typ) {
        this.typ = typ;
    }

    public int getBezirkID() {
        return bezirkID;
    }

    public void setBezirkID(int bezirkID) {
        this.bezirkID = bezirkID;
    }
}
