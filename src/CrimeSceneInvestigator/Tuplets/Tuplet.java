package CrimeSceneInvestigator.Tuplets;

import javafx.collections.ObservableList;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by Funke on 23.01.2016.
 */
public abstract class Tuplet {

    public static final int VARCHAR = 256;

    public abstract String getValue(String attribute);

    public abstract String toString();

}
