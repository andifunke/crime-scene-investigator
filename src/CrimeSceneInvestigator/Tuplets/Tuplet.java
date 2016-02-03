package CrimeSceneInvestigator.Tuplets;

public abstract class Tuplet {

    public static final int VARCHAR = 256;

    public abstract String getValue(String attribute);

    public abstract String toString();

}
