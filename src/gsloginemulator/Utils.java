package gsloginemulator;

/*
 * @author roun512
 */

public class Utils {

	public static String DecodeGamespyPassword(String password) {
		password = password.replace("_", "=");
		password = password.replace("[", "+");
		password = password.replace("]", "/");
		
		return password;
	}
	
}
