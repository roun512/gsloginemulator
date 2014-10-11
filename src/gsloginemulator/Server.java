package gsloginemulator;

import java.io.*;
//import java.io.FileNotFoundException;
//import java.io.IOException;
//import java.io.InputStreamReader;
//import java.io.FileOutputStream;
//import java.io.File;
//import java.io.OutputStreamWriter;
import java.sql.ResultSet;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import gsloginemulatorDatabase.*;

/**
 * @author roun512
 *
*/

public class Server {

	protected static boolean isRunning = true;
	public static File LogFile = new File(System.getProperty("user.dir") + "server.log");
    //private GpcmServer CmServer;
    //private GpspServer SpServer;
    public static GamespyDatabase Database;
	
	public static void main(String[] args) throws IOException {
		System.out.println("Initializing...");
		try
	    {
	      //Server.Database = new GamespyDatabase();
	    } catch (Exception ex)
	    {
	      return;
	    }
		try
		{
			System.out.println("<GPCM> Binding to port 29900");
		//	this.CmServer = new GpcmServer();
		}
		catch (Exception ex) {
			System.out.println("Error binding to port 29900! " + ex.getMessage());
			System.out.println("Closing server");
			
			return;
		}
		try {
			System.out.println("<GPSP> Binding to port 29901");
		//	this.SpServer = new GpspServer();
		} catch(Exception ex) {
			System.out.println("Error binding to port 29901! " + ex.getMessage());
			System.out.println("Closing Server");
			
			return;
		}
		System.out.println("Ready for connections! Type \"help\" for a list of commands");
		while(Server.isRunning)
			checkInput();
		//Server.CmServer.Shutdown();
		//Server.SpServer.Shutdown();
		System.out.println("Server shutdown Successfully");
		
	}

	public static void checkInput() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		System.out.print("command > ");
		String command = br.readLine();
		String[] cmd = command.trim().split(" ");
		System.out.println("");
		try {
			switch(cmd[0]) {
				case "quit":
				case "exit":
				case "stop":
					Server.isRunning = false;
					break;
					
				case "connections":
					System.out.println(" - Total Connections: "/* + Server.CmServer.NumClients()*/);
					break;
					
				case "accounts":
					System.out.println(" - Total Accounts: "/* + Server.Database.GetNumAccounts()*/);
					break;
					
				case "create":
					if (command.length() < 4) {
						System.out.println(" - Incorrect command format. Please type 'help' to see list of available commands.");
						System.out.println("");
						return;
					} else if(cmd[1] == null || cmd[2] == null || cmd[3] == null || cmd[1].isEmpty() || cmd[2].isEmpty() || cmd[3].isEmpty()) {
						System.out.println(" - Account name, password, or email was not provided. Please try again with the correct format.");
						System.out.println("");
						return;
					} else if(Server.Database.UserExists(cmd[1])){
						System.out.println(" - Account " + cmd[1] + " already exists in the gamespy database.");
					} else {
						System.out.println((Server.Database.CreateUser(cmd[1], cmd[2], cmd[3], "00") ? " - Account created successfully" : " - Error creating account!"));
						break;
					}
				
				case "delete":
					if (command.length() < 2) {
						System.out.println(" - Incorrect command format. Please type 'help' to see list of available commands.");
						System.out.println();
					} else if(cmd[1] == null || cmd[1].isEmpty()) {
						System.out.println(" - Account name was not provided. Please try again with the correct format.");
						System.out.println("");
						return;
					} else if(!Server.Database.UserExists(cmd[1])) {
						System.out.println(" - Account " + cmd[1] + " doesnt exist in the gamespy database.");
						System.out.println("");
						return;
					} else {
						System.out.print(" - Are you sure you want to delete account " + cmd[1] + "? <y/n>: ");
						String yn = br.readLine();
						yn = yn.toLowerCase();
						if(yn == "y" || yn == "yes") {
							System.out.println((Server.Database.DeleteUser(cmd[1]) != 1 ? " - Failed to remove account from database." : " - Account deleted successfully"));
							break;
						} else if(yn == "n" || yn == "no") {
							System.out.println(" - Command cancelled.");
							return;
						}
					}
					
				case "setpid":
					if (command.Length < 3)
		            {
						System.out.println(" - Incorrect command format. Please type 'help' to see list of available commands.");
						System.out.println("");
						return;
		            }
		            else if (cmd[1] == null || cmd[1].isEmpty() || cmd[2] == null || cmd[2].isEmpty())
		            {
		            	System.out.println(" - Account name or PID not provided. Please try again with the correct format.");
		            	System.out.println("");
		                return;
		            }
		            else if (Server.Database.GetUser(cmd[1]) == null)
		            {
		            	System.out.println(" - Account " + cmd[1] + " does not exist in the gamespy database.");
		            	return;
		            }
		            else
		            {
		              int result = Integer.parseInt(cmd[2]);
		              //Integer.parseInt(cmd[2], result);
		              if (result != (int)result)
		              {
		            	  System.out.println(" - Player ID must be an numeric only!");
		            	  System.out.println("");
		                  return;
		              }
		              else
		              {
		                int num = Server.Database.SetPID(cmd[1], result);
		                String str2 = "";
		                switch (num)
		                {
		                  case -2:
		                      str2 = String.format("PID {0} is already in use.", (object) result);
		                      break;
		                  case -1:
		                      str2 = String.format("Account " + cmd[1] + " does not exist in the gamespy database.");
		                      break;
		                  case 0:
		                	  str2 = "Error setting PID";
		                      break;
		                  case 1:
		                	  str2 = "New PID is set!";
		                	  break;
		                }
		                System.out.println(" - " + str2);
		               	System.out.println("");
		               	break;
		             	}
		            }
					
				case "fetch":
					if(command.length() < 2) {
						System.out.println(" - Incorrect command format. Please type 'help' to see list of available commands.");
						System.out.println("");
						return;
					} else if (command == null || command.isEmpty()) {
						System.out.println(" - No account named provided. Please make sure you are providing an account name, and not a space");
			            System.out.println("");
			            return;
					} else {
						ResultSet user = Server.Database.getUser(cmd[1]);
						if(user == null) {
							System.out.println(" - Account " + user.getString("username") + " does not exist in the database.");
						} else {
							System.out.println(" - Account ID: " + user.getString("id") + " - Email: " + user.getString("email") + " - Country: " + user.getString("country"));
							break;
						}
					}
					
				case "help":
					System.out.println("quit/exit/stop          - Stops the server");
					System.out.println("connections             - Displays the current number of connected clients");
					System.out.println("accounts                - Displays the current number accounts in the DB");
					System.out.println("create {nick} {password} {email}       - Creates a new login account");
					System.out.println("delete {nick}           - Deletes a user from DB");
					System.out.println("fetch {nick}            - Displays the account information");
					System.out.println("setpid {nick} {newpid}  - Sets the BF2 Player ID of the givin account name");
					break;
				default:
					System.out.println(" - Unrecognized input:" + command);
			}
		} catch(Exception ex) {
			System.out.println(ex.getMessage());
		}
		Thread.sleep(100);
	}
	
	public static void Shutdown()
    {
		Server.isRunning = false;
    }
	
	public static void Log(String message) {
		Date now = new Date();
		DateFormat dateFormat = new SimpleDateFormat("MM/dd HH:mm");
		try {
			FileOutputStream Logfile = new FileOutputStream(Server.LogFile);
			OutputStreamWriter writer = new OutputStreamWriter(Logfile);
			writer.write(dateFormat.format(now) + " > " + message);
			writer.flush();
		} catch (FileNotFoundException e) {
			System.out.println("server.log not found! error: " + e.getMessage());
		} catch (IOException e) {
			System.out.println("Unable to write to server.log! error: " + e.getMessage());
		}
		
	}
	
	public static void Log(String message, String[] items) {
		Server.Log(String.format(message, items));	
	}
}
