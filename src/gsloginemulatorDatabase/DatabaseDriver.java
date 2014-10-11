package gsloginemulatorDatabase;

import gsloginemulator.*;
import java.sql.*;
import java.util.Properties;

/*
 * @author roun512
 */

public class DatabaseDriver {
	
	
	private Boolean IsNewDatabase;
	public Connection connection;
	

	public void main(String[] args) {
		Properties connectionProps = new Properties();
		connectionProps.put("user", args[2]);
		connectionProps.put("password", args[3]);
		try {
			this.connection = DriverManager.getConnection("jdbc:mysql://" + args[0] + ":" + args[1] + "/" + args[4], connectionProps);
		} catch(Exception e) {
			System.out.println("Couldn\'t connect to database! error: " + e.getMessage());
		}
	}

//	public void Connect() {
//		this.connection
//	}
	
	public void Close() {
		try {
			this.connection.close();
		} catch(Exception e) {
			System.out.println("Unable to close connection to Database! error: " + e.getMessage());
		}
	}
	
	public void Execute(String sql) throws SQLException {
		Statement st = null;
		try {
			st = this.connection.createStatement();
			ResultSet rs = st.executeQuery(sql);
			
		} catch(SQLException e) {
			System.out.println("SQL Error: " + e.getMessage());
		} finally {
			if(st != null) {
				st.close();
			}
		}
		
	}

	
}
