package DataBaseApplication;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

class DBController {

    private static final DBController dbcontroller = new DBController();
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

    private DBController(){
    }

    public static DBController getInstance(){
        return dbcontroller;
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

    private void handleDB() {
        try {
            Statement stmt = connection.createStatement();
//            stmt.executeUpdate("DROP TABLE IF EXISTS books;");
//            stmt.executeUpdate("CREATE TABLE books (author, title, publication, pages, price);");
//            stmt.execute("INSERT INTO books (author, title, publication, pages, price) VALUES ('Paulchen Paule', 'Paul der Penner', '2001-05-06', '1234', '5.67')");

            PreparedStatement insertFaelle = connection.prepareStatement("INSERT INTO faelle VALUES (?, ?, ?, ?);");

//            insertFaelle.setInt(1, 1000);
            insertFaelle.setString(2, "Mörder Fall");
            insertFaelle.setString(3, "2011-05-16");
            insertFaelle.setString(4, "2011-05-17");
            insertFaelle.addBatch();

            connection.setAutoCommit(false);
            insertFaelle.executeBatch();
            connection.setAutoCommit(true);

            ResultSet readFaelle = stmt.executeQuery("SELECT * FROM faelle;");
            while (readFaelle.next()) {
                System.out.println("ID = " + readFaelle.getInt("fallid"));
                System.out.println("Name = " + readFaelle.getString("name"));
                System.out.println("Eröffnungsdatum = " + readFaelle.getString("eroeffnungsdatum"));
                System.out.println("Enddatum = " + readFaelle.getString("enddatum"));
            }
            readFaelle.close();
            connection.close();
        } catch (SQLException e) {
            System.err.println("Couldn't handle DB-Query");
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        DBController dbc = DBController.getInstance();
        dbc.initDBConnection();
        dbc.handleDB();
    }
}