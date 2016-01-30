package CrimeSceneInvestigator.Tuplets;

/**
 * Created by Funke on 23.01.2016.
 */
public class Zeitraum extends Tuplet {

    private int zeitraumID;
    private String von;
    private String bis;
    private int behoerdeID;
    private int personID;

    public Zeitraum(int zeitraumID, String von, int behoerdeID, int personID) {
        this.zeitraumID = zeitraumID;
        this.von = von;
        this.behoerdeID = behoerdeID;
        this.personID = personID;
    }

    public int getZeitraumID() {
        return zeitraumID;
    }

    public void setZeitraumID(int zeitraumID) {
        this.zeitraumID = zeitraumID;
    }

    public String getVon() {
        return von;
    }

    public void setVon(String von) {
        this.von = von;
    }

    public String getBis() {
        return bis;
    }

    public void setBis(String bis) {
        this.bis = bis;
    }

    public int getBehoerdeID() {
        return behoerdeID;
    }

    public void setBehoerdeID(int behoerdeID) {
        this.behoerdeID = behoerdeID;
    }

    public int getPersonID() {
        return personID;
    }

    public void setPersonID(int personID) {
        this.personID = personID;
    }
}
