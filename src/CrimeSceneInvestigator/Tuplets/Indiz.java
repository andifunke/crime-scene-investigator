package CrimeSceneInvestigator.Tuplets;

import javafx.scene.image.Image;

/**
 * Created by Funke on 23.01.2016.
 */
public class Indiz extends Tuplet {

    private int indizID;
    private String angelegtAm;
    private Image bild;
    private String text;
    private int personID;
    private int fallID;

    public Indiz(String angelegtAm, int indizID, int personID, int fallID) {
        this.angelegtAm = angelegtAm;
        this.indizID = indizID;
        this.personID = personID;
        this.fallID = fallID;
    }

    public int getIndizID() {
        return indizID;
    }

    public void setIndizID(int indizID) {
        this.indizID = indizID;
    }

    public String getAngelegtAm() {
        return angelegtAm;
    }

    public void setAngelegtAm(String angelegtAm) {
        this.angelegtAm = angelegtAm;
    }

    public Image getBild() {
        return bild;
    }

    public void setBild(Image bild) {
        this.bild = bild;
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
