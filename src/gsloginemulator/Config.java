/**
 * 
 */
package gsloginemulator;

import java.io.File;
import gsloginemulatorDatabase.*;
import java.io.*;
import java.util.Scanner;


/**
 * @author roun512
 *
 */
public class Config {
	private static String IniLocation = System.getProperty("user.dir") + "/config.ini";
	public static String path = System.getProperty("user.dir");
	
	public Config() {
		//Check File IniLocation Exists
		File config_file = new File(Config.IniLocation);
		if(config_file.exists() && !config_file.isDirectory())
			return;
		System.out.println("config.ini File doesn\'t exist");
		
	}
	
	public String getLine(String Key) {
		File config = new File(Config.IniLocation);
		Scanner in = null;
		String value = null;
		try {
			in = new Scanner(new FileReader(config));
			while(in.hasNextLine()) {
				String result = in.nextLine();
				String[] word = result.trim().split(" ");
				if(word[0] == Key) {
					value = word[2];
				} else {
					System.out.println(Key + " variable is not found in config.ini file!");
					in.next();
					break;
				}
				
			}
		} catch(Exception e) {
			System.out.println("Wasn\'t able to read config.ini! error: " + e.getMessage());
		}
		in.close();
		return value;
		
	}
	
	//public static DatabaseEngine GetDatabaseEngine() {
		//return "test";
	//}
}
