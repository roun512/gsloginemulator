package gsloginemulator;

import java.io.IOException;



/**
 * @author roun512
 *
 */

public class Program {
	
	private static String version = "1.0";
	
	public static void main(String[] args) {
		System.out.println("Gamespy Login Emulator that works in linux and windows");
		System.out.println("Battlefield Gamespy Login Emulator v" + Program.version);
		System.out.println("Created in Java by roun512 for http://battlelog.co");
		System.out.println("");
		try {
			Server server = new Server();
		} catch (IOException | InterruptedException e) {
			e.printStackTrace();
		}
	}

}
