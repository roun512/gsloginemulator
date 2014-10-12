/**
 * 
 */
package gsloginemulator;

import java.io.File;
import java.io.FileNotFoundException;
//import gsloginemulatorDatabase.*;
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
	
	public static String getLine(String Key) throws FileNotFoundException {
		File config = new File(Config.IniLocation);
		Scanner in = null;
		String value = "";
		String result;
		
		in = new Scanner(new FileReader(config));
		while(in.hasNextLine()) {
			result = in.nextLine();
			String[] word = result.trim().split("");
			System.out.println(result);
			if(word[0] == Key) {
				value = word[1];
				break;
			} else {
				in.next();
			}
		}
		in.close();
		return value;
		
	}
	
	//public static DatabaseEngine GetDatabaseEngine() {
		//return "test";
	//}
}
