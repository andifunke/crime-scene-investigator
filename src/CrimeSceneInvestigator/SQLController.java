package CrimeSceneInvestigator;

import CrimeSceneInvestigator.Tuplets.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.*;
import java.util.ArrayList;
import java.util.LinkedList;

class SQLController {

	private static final SQLController sqlController = new SQLController();
	private static final String DB_PATH = "./crime.db";
	private static Connection connection;

	static {
		try {
			Class.forName("org.sqlite.JDBC");
		} catch (ClassNotFoundException e) {
			System.err.println("Fehler beim Laden des JDBC-Treibers");
			e.printStackTrace();
		}
	}

	private SQLController() {
	}

	public static void connect() {
		SQLController dbc = SQLController.getInstance();
		dbc.initDBConnection();
	}

	public static SQLController getInstance() {
		return sqlController;
	}

	public static ObservableList<Tuplet> selectFromTable(String table) {
		LinkedList<Filter> filterList = null;
		return selectFromTable(table, filterList);
	}

	public static ObservableList<Tuplet> selectFromTable(String table, Filter filter) {
		LinkedList<Filter> filterList = new LinkedList<>();
		filterList.addFirst(filter);
		return selectFromTable(table, filterList);
	}

	public static ObservableList<Tuplet> selectFromTable(String table, LinkedList<Filter> filterList) {
		String where = "";
		if (filterList != null) {
			StringBuilder sb = new StringBuilder();
			sb.append(" WHERE ");
			boolean hasMultipleFilter = false;
			for (Filter filter : filterList) {
				if (hasMultipleFilter) {
					sb.append(" AND ");
				}
				if (filter.isStrict()) {
					sb.append(filter.getAttribute()).append(" = '").append(filter.getValue()).append("'");
					//                    System.out.println("strict");
				}
				else {
					sb.append(filter.getAttribute()).append(" LIKE '%").append(filter.getValue()).append("%'");
					//                    System.out.println("!strict");
				}
				hasMultipleFilter = true;
			}
			where += sb.toString();
		}
		String query = "SELECT * FROM " + table + where + ";";

		SQLController dbc = SQLController.getInstance();
		return dbc.handleSelectRequest(table, query);
	}

	public static ObservableList<Tuplet> selectFromQuery(String table, String query) {
		SQLController dbc = SQLController.getInstance();
		return dbc.handleSelectRequest(table, query);
	}

	public static ObservableList<Tuplet> selectFromQuery(String table, String query, LinkedList<Filter> filterList) {
		if (filterList != null && !filterList.isEmpty()) {
			query = query.replace(";", "\n");
			StringBuilder sb = new StringBuilder();
			for (Filter filter : filterList) {
				sb.append(" AND ");
				if (filter.isStrict()) {
					sb.append(filter.getAttribute()).append(" = '").append(filter.getValue()).append("'");
				}
				else {
					sb.append(filter.getAttribute()).append(" LIKE '%").append(filter.getValue()).append("%'");
				}
			}
			query += sb.append(";").toString();
		}

		SQLController dbc = SQLController.getInstance();
		return dbc.handleSelectRequest(table, query);
	}

	public static void delete(String table, String key, String value) {
		SQLController dbc = SQLController.getInstance();
		dbc.handleDeleteRequest(table, key, value);
	}

	public static void delete(String table, String key0, String value0, String key1, String value1) {
		SQLController dbc = SQLController.getInstance();
		dbc.handleDeleteRequest(table, key0, value0, key1, value1);
	}

	public static void update(Tuplet tuplet, String[] keys) {
		SQLController dbc = SQLController.getInstance();
		dbc.handleUpdateRequest(tuplet, keys);
	}

	public static void insert(Tuplet tuplet) {
		SQLController dbc = SQLController.getInstance();
		dbc.handleInsertRequest(tuplet);
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

	private ObservableList<Tuplet> handleSelectRequest(String table, String query) {
		System.out.println(query);
		ObservableList<Tuplet> ol = null;
		try {
			Statement stmt = connection.createStatement();
			ResultSet readTable = stmt.executeQuery(query);
			if (!readTable.isClosed()) {
				switch (table) {
					case "arbeitetan":
						ol = arbeitetan.getOL(readTable);
						break;
					case "Arten":
						ol = Arten.getOL(readTable);
						break;
					case "Behoerden":
						ol = Behoerden.getOL(readTable);
						break;
					case "betrifftO":
						ol = betrifftO.getOL(readTable);
						break;
					case "betrifftV":
						ol = betrifftV.getOL(readTable);
						break;
					case "Bezirke":
						ol = Bezirke.getOL(readTable);
						break;
					case "Faelle":
						ol = Faelle.getOL(readTable);
						break;
					case "Indizien":
						ol = Indizien.getOL(readTable);
						break;
					case "liegtin":
						ol = liegtin.getOL(readTable);
						break;
					case "Notizen":
						ol = Notizen.getOL(readTable);
						break;
					case "Opfer":
						ol = Opfer.getOL(readTable);
						break;
					case "Personen":
						ol = Personen.getOL(readTable);
						break;
					case "Polizisten":
						ol = Polizisten.getOL(readTable);
						break;
					case "Verbrechen":
						ol = Verbrechen.getOL(readTable);
						break;
					case "Verdaechtige":
						ol = Verdaechtige.getOL(readTable);
						break;
					case "Zeitraeume":
						ol = Zeitraeume.getOL(readTable);
						break;
				}
				readTable.close();
			}
		} catch (SQLException e) {
			System.err.println("Couldn't handle DB-Query");
			e.printStackTrace();
		}
		if (ol == null) {
			ArrayList<Tuplet> al = new ArrayList<Tuplet>();
			ol = FXCollections.observableArrayList(al);
		}
		return ol;
	}

	private void handleDeleteRequest(String table, String key, String value) {
		try {
			String query = "DELETE FROM " + table + " WHERE " + key + " = '" + value + "';";
			System.out.println(query);
			Statement stmt = connection.createStatement();
			stmt.execute(query);
		} catch (SQLException e) {
			System.err.println("Couldn't handle DB-Query");
			e.printStackTrace();
		}
	}

	private void handleDeleteRequest(String table, String key0, String value0, String key1, String value1) {
		try {
			String query = "DELETE FROM " + table + " WHERE " + key0 + " = '" + value0 + "' AND " + key1 + " = '" + value1 + "';";
			System.out.println(query);
			Statement stmt = connection.createStatement();
			stmt.execute(query);
		} catch (SQLException e) {
			System.err.println("Couldn't handle DB-Query");
			e.printStackTrace();
		}
	}

	private void handleUpdateRequest(Tuplet tuplet, String[] keys) {
		try {
			String query = tuplet.getUpdateQuery(keys);
			System.out.println(query);
			Statement stmt = connection.createStatement();
			stmt.execute(query);
			switch (tuplet.getTable()) {
				case "Polizisten":
				case "Verdaechtige":
				case "Opfer":
					query = tuplet.getUpdateQuery2(keys);
					System.out.println(query);
					stmt = connection.createStatement();
					stmt.execute(query);
					break;
			}
		} catch (SQLException e) {
			System.err.println("Couldn't handle DB-Query");
			e.printStackTrace();
		}
	}

	private void handleInsertRequest(Tuplet tuplet) {
		try {
			String query = tuplet.getInsertQuery();
			switch (tuplet.getTable()) {
				case "Polizisten":
				case "Verdaechtige":
				case "Opfer":
					System.out.println(query);
					PreparedStatement pstmt = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
					pstmt.executeUpdate();
					ResultSet res = pstmt.getGeneratedKeys();
					String id = res.getString(1);
					tuplet.setVal0(id);
					query = tuplet.getInsertQuery2();
				default:
					System.out.println(query);
					Statement stmt = connection.createStatement();
					stmt.execute(query);
			}
		} catch (SQLException e) {
			System.err.println("Couldn't handle DB-Query");
			e.getCause();// printStackTrace();
		}
	}

}