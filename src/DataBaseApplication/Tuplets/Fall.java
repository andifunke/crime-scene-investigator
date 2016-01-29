package DataBaseApplication.Tuplets;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

/**
 * Created by Funke on 23.01.2016.
 */
public class Fall extends Tuplet {

    private final SimpleIntegerProperty fallID;
    private final SimpleStringProperty name;
    private final SimpleStringProperty eroeffnungsdatum;
    private final SimpleStringProperty enddatum;

    public Fall(int fallID, String name, String eroeffnungsdatum) {
        this(fallID, name, eroeffnungsdatum, "");
    }

    public Fall(int fallID, String name, String eroeffnungsdatum, String enddatum) {
        this.fallID = new SimpleIntegerProperty(fallID);
        this.name = new SimpleStringProperty(name);
        this.eroeffnungsdatum = new SimpleStringProperty(eroeffnungsdatum);
        this.enddatum = new SimpleStringProperty(enddatum);
    }

    public int getFallID() {
        return fallID.get();
    }

    public void setFallID(int fallID) {
        this.fallID.set(fallID);
    }

    public String getName() {
        return name.get();
    }

    public void setName(String name) {
        this.name.set(name);
    }

    public String getEroeffnungsdatum() {
        return eroeffnungsdatum.get();
    }

    public void setEroeffnungsdatum(String eroeffnungsdatum) {
        this.eroeffnungsdatum.set(eroeffnungsdatum);
    }

    public String getEnddatum() {
        return enddatum.get();
    }

    public void setEnddatum(String enddatum) {
        this.enddatum.set(enddatum);
    }
}