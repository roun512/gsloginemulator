package gsloginemulatorDatabase;

import java.sql.ResultSet;
import java.sql.SQLException;

import gsloginemulator.*;


/*
 *@author roun512
 *
 */

public class GamespyDatabase {
	
	private DatabaseDriver Driver;
	
	public GamespyDatabase() {
		this.Connect();
		this.Driver.Close();
	}

	public boolean UserExists(String Nick) {
		this.Connect();
		ResultSet result = null;
		try {
			result = this.Driver.Execute("SELECT id FROM users WHERE name = " + Nick);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		this.Driver.Close();
		return result != null;
	}
	
	public void UpdateUser(String Nick, String CC) {
		this.Connect();
		boolean Updated = false;
		try {
			 this.Driver.Update("UPDATE users SET country='" + CC + "' WHERE name='" + Nick + "'");
		} catch (SQLException e) {
			System.out.println("SQL Error: " + e.getMessage());
		}
		this.Driver.Close();
	}
	
	public void Connect() {
		String username = Config.getLine("Username");
		String password = Config.getLine("Password");
		String hostname = Config.getLine("Hostname");
		int port = Integer.parseInt(Config.getLine("Port"));
		String database = Config.getLine("Database");
		this.Driver = new DatabaseDriver(hostname, port, username, password, database);
	}

}
