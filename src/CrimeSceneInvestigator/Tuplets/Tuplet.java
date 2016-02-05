package CrimeSceneInvestigator.Tuplets;

import javafx.beans.property.SimpleStringProperty;

public abstract class Tuplet {

    public static final int VARCHAR = 256;

    private String[] attr = new String[10];
    public String getAttr(int index) {
        return attr[index];
    }
    public void setAttr(String[] attr) { this.attr = attr; }

    private final SimpleStringProperty val0;
    public String getVal0() { return val0.get(); }
    public void setVal0(String val0) { this.val0.set(val0); }

    private final SimpleStringProperty val1;
    public String getVal1() { return val1.get(); }
    public void setVal1(String val1) { this.val1.set(val1); }

    private final SimpleStringProperty val2;
    public String getVal2() { return val2.get(); }
    public void setVal2(String val2) { this.val2.set(val2); }

    private final SimpleStringProperty val3;
    public String getVal3() { return val3.get(); }
    public void setVal3(String val3) { this.val3.set(val3); }

    private final SimpleStringProperty val4;
    public String getVal4() { return val4.get(); }
    public void setVal4(String val4) { this.val4.set(val4); }

    private final SimpleStringProperty val5;
    public String getVal5() { return val5.get(); }
    public void setVal5(String val5) { this.val5.set(val5); }

    private final SimpleStringProperty val6;
    public String getVal6() { return val6.get(); }
    public void setVal6(String val6) { this.val6.set(val6); }

    private final SimpleStringProperty val7;
    public String getVal7() { return val7.get(); }
    public void setVal7(String val7) { this.val7.set(val7); }

    private final SimpleStringProperty val8;
    public String getVal8() { return val8.get(); }
    public void setVal8(String val8) { this.val8.set(val8); }

    public Tuplet (String val0, String val1, String val2, String val3, String val4, String val5, String val6, String val7, String val8) {
        if (val0 == null) val0 = "";
        if (val1 == null) val1 = "";
        if (val2 == null) val2 = "";
        if (val3 == null) val3 = "";
        if (val4 == null) val4 = "";
        if (val5 == null) val5 = "";
        if (val6 == null) val6 = "";
        if (val7 == null) val7 = "";
        if (val8 == null) val8 = "";
        this.val0 = new SimpleStringProperty(val0);
        this.val1 = new SimpleStringProperty(val1);
        this.val2 = new SimpleStringProperty(val2);
        this.val3 = new SimpleStringProperty(val3);
        this.val4 = new SimpleStringProperty(val4);
        this.val5 = new SimpleStringProperty(val5);
        this.val6 = new SimpleStringProperty(val6);
        this.val7 = new SimpleStringProperty(val7);
        this.val8 = new SimpleStringProperty(val8);
    }

    public String getValue(String param) {
        if (param.equals(attr[0]))
            return val0.get();
        if (param.equals(attr[1]))
            return val1.get();
        if (param.equals(attr[2]))
            return val2.get();
        if (param.equals(attr[3]))
            return val3.get();
        if (param.equals(attr[4]))
            return val4.get();
        if (param.equals(attr[5]))
            return val5.get();
        if (param.equals(attr[6]))
            return val6.get();
        if (param.equals(attr[7]))
            return val7.get();
        if (param.equals(attr[8]))
            return val8.get();
        return null;
    }

    public abstract String toString();
    public abstract String getUpdateQuery(String[] key);
    public abstract String getInsertQuery();

}
