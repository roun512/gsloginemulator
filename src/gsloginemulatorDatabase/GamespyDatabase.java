package gsloginemulatorDatabase;

import java.io.FileNotFoundException;
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
		boolean result = false;
		try {
			result = this.Driver.Query("SELECT id FROM users WHERE name = '" + Nick + "'");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		this.Driver.Close();
		return result;
	}
	
	public void UpdateUser(String Nick, String CC) {
		this.Connect();
		try {
			 this.Driver.Update("UPDATE users SET country='" + CC + "' WHERE name='" + Nick + "'");
		} catch (SQLException e) {
			System.out.println("SQL Error: " + e.getMessage());
		}
		this.Driver.Close();
	}
	
	public boolean CreateUser(String Nick, String password, String email, String country) throws SQLException {
		this.Connect();
		ResultSet rs = this.Driver.Execute("SELECT MAX(id) FROM users");
		int num1;
		try {
			int result = Integer.getInteger(rs.getString("max").toString());
			num1 = result + 1;
			if (num1 < 500000000)
				num1 = 500000000;
		} catch(Exception e) {
			num1 = 500000000;
		}
		boolean num2 = this.Driver.Update("INSERT INTO users (id, name, password, email, country) VALUES (" + num1 + ", '" + Nick + "', '" + password + "', '" + email + "', '" + country + "')");
		this.Driver.Close();
		return num2;
	}
	
	public ResultSet GetUser(String Nick) throws SQLException {
		this.Connect();
		ResultSet rs = this.Driver.Execute("SELECT id, name, password, country, session FROM users WHERE name = '" + Nick + "'");
		this.Driver.Close();
		ResultSet result;
		if(rs.next()) {
			result = rs;
			return result;
		} else {
			return null;
		}
	}
	
	public ResultSet GetUser(String email, String password) throws SQLException {
		this.Connect();
		ResultSet rs = this.Driver.Execute("SELECT id, name, password, country, session FROM users WHERE email = '" + email + "' AND password = '" + password + "'");
		this.Driver.Close();
		if (rs != null)
			return rs;
		else
			return null;
	}
	
	public boolean DeleteUser(String Nick) throws SQLException {
		this.Connect();
		boolean result = this.Driver.Update("DELETE FROM users WHERE name = '" + Nick + "'");
		this.Driver.Close();
		return result;
	}
	
	public int GetPID(String Nick) throws SQLException {
		this.Connect();
		ResultSet rs = this.Driver.Execute("SELECT id FROM users WHERE name = '" + Nick + "'");
		
		if(rs == null) {
			this.Driver.Close();
			return 0;
		} else {
			int result = rs.getInt("id");
			if(result == (int)result) {
				this.Driver.Close();
				return result;
			} else {
				return 0;
			}
		}
	}
	
	public boolean SetPID(String Nick, int PID) throws SQLException {
		this.Connect();
		boolean rs1 = this.Driver.Update("UPDATE users SET id=" + PID + " WHERE name = '" + Nick + "'");
		this.Driver.Close();
		return rs1;
		
	}
	
	public void Connect() {
		//String username = Config.getLine("Username");
		//String password = Config.getLine("Password");
		//String hostname = Config.getLine("Hostname");
		//int port = Integer.parseInt(Config.getLine("Port"));
		//String database = Config.getLine("Database");
		this.Driver = new DatabaseDriver("localhost", 3306, "root", "", "bf2gs");
	}

	public int GetNumAccounts() throws SQLException {
		this.Connect();
		int result = 0;
		ResultSet rs = this.Driver.Execute("SELECT COUNT(id) AS count FROM users");
		if(rs.next()){
			result = rs.getInt("count");
			return result;
		} else {
			return (Integer) null;
		}
	}

}
