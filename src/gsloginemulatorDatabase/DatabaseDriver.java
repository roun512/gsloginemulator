package gsloginemulatorDatabase;

import gsloginemulator.*;
import java.sql.*;
import java.util.Properties;

/*
 * @author roun512
 */

public class DatabaseDriver {

	static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
	static final String DB_URL = "jdbc:mysql://localhost/";
	
	private Boolean IsNewDatabase;

	public static void main(String[] args) {
		

	}

	public Connection connect(String host, int port, String username, String password, String Database) throws SQLException {
		Connection conn = null;
		Properties connectionProps = new Properties();
		connectionProps.put("user", username);
		connectionProps.put("password", password);
		try {
			conn = DriverManager.getConnection("jdbc:mysql://" + host + ":" + port + "/", connectionProps);
		} catch(Exception e) {
			System.out.println("Couldn\'t connect to database! error: " + e.getMessage());
		}
		return conn;
	}
	
}
