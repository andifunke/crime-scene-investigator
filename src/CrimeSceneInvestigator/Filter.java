package CrimeSceneInvestigator;

/**
 * Created by Funke on 01.02.2016.
 */
public class Filter {

	private final String table;
	private final String attribute;
	private final String value;
	private boolean strict;

	public Filter(String table, String attribute, String value) {
		this.table = table;
		this.attribute = attribute;
		this.value = value;
		this.strict = false;
	}

	public Filter(String table, String attribute, String value, Boolean strict) {
		this.table = table;
		this.attribute = attribute;
		this.value = value;
		this.strict = strict;
	}

	public String getTable() {
		return table;
	}

	public String getAttribute() { return attribute; }

	public String getValue() {
		return value;
	}

	public Boolean isStrict() { return strict; }

	public void setStrict(boolean strict) {
		this.strict = strict;
	}
}
