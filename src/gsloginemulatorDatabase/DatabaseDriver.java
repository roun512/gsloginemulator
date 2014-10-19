package gsloginemulatorDatabase;

import java.sql.*;
import java.util.Properties;

/*
 * @author roun512
 */

public class DatabaseDriver {
	
	public Connection connection;

	public DatabaseDriver(String hostname, int port, String username, String password, String database) {
		try {
		    Class.forName("com.mysql.jdbc.Driver");
		} 
		catch (ClassNotFoundException e) {
		    e.printStackTrace();
		} 
		if(this.connection == null) {
			Properties connectionProps = new Properties();
			connectionProps.put("user", username);
			connectionProps.put("password", password);
			try {
				this.connection = DriverManager.getConnection("jdbc:mysql://" + hostname + ":" + port + "/" + database, connectionProps);
			} catch(Exception e) {
				System.out.println("Couldn\'t connect to database! error: " + e.getMessage());
			}
		}
	}

	public void Connect(String host, int port, String username, String password, String database) {
		
		
	}
	
	public void Close() {
		if(this.connection != null) {
			try {
				this.connection.close();
			} catch(Exception e) {
				System.out.println("Unable to close connection to Database! error: " + e.getMessage());
			}
		} else {
			System.out.println("You are not connected to any database!");
		}
	}
	
	public ResultSet Execute(String sql) throws SQLException {
		Statement st = null;
		ResultSet result = null;
		st = this.connection.createStatement();
		ResultSet rs = st.executeQuery(sql);
		result = rs;
	
		return result;
	}
	
	public boolean Update(String sql) throws SQLException {
		Statement st = null;
		boolean executed = false;
		try {
			st = this.connection.createStatement();
			st.executeUpdate(sql);
			executed = true;
		} catch(SQLException e) {
			System.out.println("SQL Error: " + e.getMessage());
		
		}
		
		return executed;
	}

	public boolean Query(String sql) throws SQLException {
		Statement st = null;
		boolean executed = false;
		try {
			st = this.connection.createStatement();
			ResultSet rs = st.executeQuery(sql);
			if(rs.next())
				executed = true;
			else
				executed = false;
		} catch(SQLException e) {
			System.out.println("SQL Error: " + e.getMessage());
		} finally {
			if(st != null) {
				st.close();
			}
		}
		return executed;
	}
}
