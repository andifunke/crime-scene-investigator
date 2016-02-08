package CrimeSceneInvestigator.Tuplets;

import javafx.beans.property.SimpleStringProperty;

public abstract class Tuplet {

	public static final int VARCHAR = 256;

	private String table;
	private String[] attr;
	private final SimpleStringProperty[] val = new SimpleStringProperty[9];

	public Tuplet(String[] attr, String[] val) {
		this.attr = attr;
		for (String s : val)
			if (s == null) s = "";
		int i;
		for (i = 0; i < attr.length; i++)
			this.val[i] = new SimpleStringProperty(val[i]);
		for (; i < this.val.length; i++)
			this.val[i] = new SimpleStringProperty("");
	}

	public String getTable() {
		return table;
	}

	public void setTable(String table) { this.table = table; }

	public String getAttr(int index) {
		return attr[index];
	}

	public void setAttr(String[] attr) { this.attr = attr; }

	public String getVal0() { return val[0].get(); }

	public void setVal0(String val0) { this.val[0].set(val0); }

	public String getVal1() { return val[1].get(); }

	public void setVal1(String val1) { this.val[1].set(val1); }

	public String getVal2() { return val[2].get(); }

	public void setVal2(String val2) { this.val[2].set(val2); }

	public String getVal3() { return val[3].get(); }

	public void setVal3(String val3) { this.val[3].set(val3); }

	public String getVal4() { return val[4].get(); }

	public void setVal4(String val4) { this.val[4].set(val4); }

	public String getVal5() { return val[5].get(); }

	public void setVal5(String val5) { this.val[5].set(val5); }

	public String getVal6() { return val[6].get(); }

	public void setVal6(String val6) { this.val[6].set(val6); }

	public String getVal7() { return val[7].get(); }

	public void setVal7(String val7) { this.val[7].set(val7); }

	public String getVal8() { return val[8].get(); }

	public void setVal8(String val8) { this.val[8].set(val8); }

	public String getValue(String param) {
		for (int i = 0; i < attr.length; i++)
			if (param.equals(attr[i])) return val[i].get();
		return null;
	}

	public String getValue(int index) {
		return val[index].get();
	}

	public void setValue(String param, String value) {
		for (int i = 0; i < attr.length; i++)
			if (param.equals(attr[i])) val[i].set(value);
	}

	public void setValue(int index, String value) {
		val[index].set(value);
	}


	public abstract String toString();

	public abstract String getUpdateQuery(String[] key);

	public String getUpdateQuery2(String[] key) { return ""; }

	public abstract String getInsertQuery();

	public String getInsertQuery2() { return ""; }

}
