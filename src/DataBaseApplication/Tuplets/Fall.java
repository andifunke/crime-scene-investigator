package DataBaseApplication.Tuplets;

/**
 * Created by Funke on 23.01.2016.
 */
public class Fall extends Tuplet {

        private int fallID;
        private String name;
        private String eroeffnungsdatum;
        private String enddatum;

    public Fall(int fallID, String name, String eroeffnungsdatum) {
        this.fallID = fallID;
        this.name = name;
        this.eroeffnungsdatum = eroeffnungsdatum;
    }

    public int getFallID() {
        return fallID;
    }

    public void setFallID(int fallID) {
        this.fallID = fallID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEroeffnungsdatum() {
        return eroeffnungsdatum;
    }

    public void setEroeffnungsdatum(String eroeffnungsdatum) {
        this.eroeffnungsdatum = eroeffnungsdatum;
    }

    public String getEnddatum() {
        return enddatum;
    }

    public void setEnddatum(String enddatum) {
        this.enddatum = enddatum;
    }
}
