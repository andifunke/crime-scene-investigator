package CrimeSceneInvestigator.Tuplets;

/**
 * Created by Funke on 23.01.2016.
 */
public class Verbrechen extends Tuplet {

    private String name;
    private String datum;
    private int fallID;
    private int verbrechenID;
    private String artName;
    private int BezirkID;

    public Verbrechen(String name, int fallID, int verbrechenID, String artName, int bezirkID) {
        this.name = name;
        this.fallID = fallID;
        this.verbrechenID = verbrechenID;
        this.artName = artName;
        BezirkID = bezirkID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDatum() {
        return datum;
    }

    public void setDatum(String datum) {
        this.datum = datum;
    }

    public int getFallID() {
        return fallID;
    }

    public void setFallID(int fallID) {
        this.fallID = fallID;
    }

    public int getVerbrechenID() {
        return verbrechenID;
    }

    public void setVerbrechenID(int verbrechenID) {
        this.verbrechenID = verbrechenID;
    }

    public String getArtName() {
        return artName;
    }

    public void setArtName(String artName) {
        this.artName = artName;
    }

    public int getBezirkID() {
        return BezirkID;
    }

    public void setBezirkID(int bezirkID) {
        BezirkID = bezirkID;
    }
}
