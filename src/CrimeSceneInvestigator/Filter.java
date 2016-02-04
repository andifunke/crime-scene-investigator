package CrimeSceneInvestigator;

/**
 * Created by Funke on 01.02.2016.
 */
public class Filter {

    private String table;
    private String attribute;
    private String value;

    public Filter(String table, String attribute, String value) {
        this.table = table;
        this.attribute = attribute;
        this.value = value;
    }

    public String getTable() {
        return table;
    }
    public String getAttribute() { return attribute; }
    public String getValue() {
        return value;
    }
}
