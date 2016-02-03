package CrimeSceneInvestigator.Tuplets;

/**
 * Created by Funke on 23.01.2016.
 */
public class Notiz extends Tuplet {

    private int notizID;
    private String angelegtam;
    private String text;
    private int personID;
    private int fallID;

    public Notiz(int notizID, String angelegtam, String text, int personID, int fallID) {
        this.notizID = notizID;
        this.angelegtam = angelegtam;
        this.text = text;
        this.personID = personID;
        this.fallID = fallID;
    }

    public int getNotizID() {
        return notizID;
    }

    public void setNotizID(int notizID) {
        this.notizID = notizID;
    }

    public String getAngelegtam() {
        return angelegtam;
    }

    public void setAngelegtam(String angelegtam) {
        this.angelegtam = angelegtam;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
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

    public String getValue(String attribute) {
        return null;
    }

    public String toString() {
        return "";
    }

}
