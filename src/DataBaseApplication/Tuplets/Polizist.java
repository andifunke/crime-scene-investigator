package DataBaseApplication.Tuplets;

/**
 * Created by Funke on 23.01.2016.
 */
public class Polizist extends Person {

    private String dienstgrad;

    public Polizist(int personID, String dienstgrad) {
        super(personID);
        this.dienstgrad = dienstgrad;
    }

    public String getDienstgrad() {
        return dienstgrad;
    }

    public void setDienstgrad(String dienstgrad) {
        this.dienstgrad = dienstgrad;
    }
}
