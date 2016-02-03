package CrimeSceneInvestigator;

/**
 * Created by Funke on 01.02.2016.
 */
public class Filter {

    private String attribute;
    private String value;

    public Filter(String attribute, String value) {
        this.attribute = attribute;
        this.value = value;
    }

    public String getAttribute() {
        return attribute;
    }

    public String getValue() {
        return value;
    }
}
