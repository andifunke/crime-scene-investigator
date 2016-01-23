package DataBaseApplication.Tuplets;

/**
 * Created by Funke on 23.01.2016.
 */
public class arbeitetAn extends Tuplet {

    private int personID;
    private int fallID;
    private String von;
    private String bis;

    public arbeitetAn (int personID, int fallID, String von) {
        this.personID = personID;
        this.fallID = fallID;
        this.von = von;
    }

    public int getPersonID() {
        return personID;
    }

    public void setPersonID(int personID) {
        this.personID = personID;
    }

    public int getFallID() {
        return fallID;
    }

    public void setFallID(int fallID) {
        this.fallID = fallID;
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

}
