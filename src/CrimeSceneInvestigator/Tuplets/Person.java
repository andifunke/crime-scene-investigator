package CrimeSceneInvestigator.Tuplets;

/**
 * Created by Funke on 23.01.2016.
 */
public class Person extends Tuplet {

    private int personID;
    private String Name;
    private char Geschlecht;
    private String Nationalitaet;
    private String Geburtsdatum;
    private String Todesdatum;

    public Person(int personID) {
        this.personID = personID;
    }

    public int getPersonID() {
        return personID;
    }

    public void setPersonID(int personID) {
        this.personID = personID;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public char getGeschlecht() {
        return Geschlecht;
    }

    public void setGeschlecht(char geschlecht) {
        Geschlecht = geschlecht;
    }

    public String getNationalitaet() {
        return Nationalitaet;
    }

    public void setNationalitaet(String nationalitaet) {
        Nationalitaet = nationalitaet;
    }

    public String getGeburtsdatum() {
        return Geburtsdatum;
    }

    public void setGeburtsdatum(String geburtsdatum) {
        Geburtsdatum = geburtsdatum;
    }

    public String getTodesdatum() {
        return Todesdatum;
    }

    public void setTodesdatum(String todesdatum) {
        Todesdatum = todesdatum;
    }

    public String getValue(String attribute) {
        return null;
    }

    public String toString() {
        return "";
    }

}
