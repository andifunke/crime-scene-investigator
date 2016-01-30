package CrimeSceneInvestigator;

import CrimeSceneInvestigator.Tuplets.Fall;
import CrimeSceneInvestigator.Tuplets.Tuplet;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.*;
import java.util.ArrayList;

class SQLController {

    private static final SQLController sqlController = new SQLController();
    private static Connection connection;
    private static final String DB_PATH = "res/crime.db";

    static {
        try {
            Class.forName("org.sqlite.JDBC");
        } catch (ClassNotFoundException e) {
            System.err.println("Fehler beim Laden des JDBC-Treibers");
            e.printStackTrace();
        }
    }

    private SQLController(){
    }

    public static void connect() {
        SQLController dbc = SQLController.getInstance();
        dbc.initDBConnection();
    }

    public static SQLController getInstance(){
        return sqlController;
    }

    private void initDBConnection() {
        try {
            if (connection != null)
                return;
            System.out.println("Creating Connection to Database...");
            connection = DriverManager.getConnection("jdbc:sqlite:" + DB_PATH);
            if (!connection.isClosed())
                System.out.println("...Connection established");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        Runtime.getRuntime().addShutdownHook(new Thread() {
            public void run() {
                try {
                    if (!connection.isClosed() && connection != null) {
                        connection.close();
                        if (connection.isClosed())
                            System.out.println("Connection to Database closed");
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public static ObservableList<Tuplet> getTable(String table) {
        SQLController dbc = SQLController.getInstance();
        return dbc.handleTableRequest(table);
    }

    private ObservableList<Tuplet> handleTableRequest(String table) {
        ObservableList<Tuplet> ol = null;
        try {
            Statement stmt = connection.createStatement();
            ResultSet readTable = stmt.executeQuery("SELECT * FROM "+table+";");
            switch (table) {
                case "Faelle":
                    ol = Fall.getOL(readTable);
                    break;
            }
            readTable.close();
            connection.close();
        } catch (SQLException e) {
            System.err.println("Couldn't handle DB-Query");
            e.printStackTrace();
        }
        return ol;
    }

/*    private void handleRequest() {
        try {
            Statement stmt = connection.createStatement();
//            stmt.executeUpdate("DROP TABLE IF EXISTS books;");
//            stmt.executeUpdate("CREATE TABLE books (author, title, publication, pages, price);");
//            stmt.execute("INSERT INTO books (author, title, publication, pages, price) VALUES ('Paulchen Paule', 'Paul der Penner', '2001-05-06', '1234', '5.67')");

            PreparedStatement insertFaelle = connection.prepareStatement("INSERT INTO faelle VALUES (?, ?, ?, ?);");

//            insertFaelle.setInt(1, 1000);
//            insertFaelle.setString(2, "Mörder Fall");
//            insertFaelle.setString(3, "2011-05-16");
//            insertFaelle.setString(4, "2011-05-17");
//            insertFaelle.addBatch();

//            connection.setAutoCommit(false);
//            insertFaelle.executeBatch();
//            connection.setAutoCommit(true);

            ResultSet readFaelle = stmt.executeQuery("SELECT * FROM faelle;");
            readFaelle.close();
            connection.close();
        } catch (SQLException e) {
            System.err.println("Couldn't handle DB-Query");
            e.printStackTrace();
        }
    }
*/
}