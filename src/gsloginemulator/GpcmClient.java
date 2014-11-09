/**
 * 
 */
package gsloginemulator;

import java.net.*;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Random;
import java.io.*;

/**
 * @author roun512
 *
 */
public class GpcmClient {

	private char backslash = '\\';
	
	private String[] Btoh = new String[] {
			"00",
		      "01",
		      "02",
		      "03",
		      "04",
		      "05",
		      "06",
		      "07",
		      "08",
		      "09",
		      "0a",
		      "0b",
		      "0c",
		      "0d",
		      "0e",
		      "0f",
		      "10",
		      "11",
		      "12",
		      "13",
		      "14",
		      "15",
		      "16",
		      "17",
		      "18",
		      "19",
		      "1a",
		      "1b",
		      "1c",
		      "1d",
		      "1e",
		      "1f",
		      "20",
		      "21",
		      "22",
		      "23",
		      "24",
		      "25",
		      "26",
		      "27",
		      "28",
		      "29",
		      "2a",
		      "2b",
		      "2c",
		      "2d",
		      "2e",
		      "2f",
		      "30",
		      "31",
		      "32",
		      "33",
		      "34",
		      "35",
		      "36",
		      "37",
		      "38",
		      "39",
		      "3a",
		      "3b",
		      "3c",
		      "3d",
		      "3e",
		      "3f",
		      "40",
		      "41",
		      "42",
		      "43",
		      "44",
		      "45",
		      "46",
		      "47",
		      "48",
		      "49",
		      "4a",
		      "4b",
		      "4c",
		      "4d",
		      "4e",
		      "4f",
		      "50",
		      "51",
		      "52",
		      "53",
		      "54",
		      "55",
		      "56",
		      "57",
		      "58",
		      "59",
		      "5a",
		      "5b",
		      "5c",
		      "5d",
		      "5e",
		      "5f",
		      "60",
		      "61",
		      "62",
		      "63",
		      "64",
		      "65",
		      "66",
		      "67",
		      "68",
		      "69",
		      "6a",
		      "6b",
		      "6c",
		      "6d",
		      "6e",
		      "6f",
		      "70",
		      "71",
		      "72",
		      "73",
		      "74",
		      "75",
		      "76",
		      "77",
		      "78",
		      "79",
		      "7a",
		      "7b",
		      "7c",
		      "7d",
		      "7e",
		      "7f",
		      "80",
		      "81",
		      "82",
		      "83",
		      "84",
		      "85",
		      "86",
		      "87",
		      "88",
		      "89",
		      "8a",
		      "8b",
		      "8c",
		      "8d",
		      "8e",
		      "8f",
		      "90",
		      "91",
		      "92",
		      "93",
		      "94",
		      "95",
		      "96",
		      "97",
		      "98",
		      "99",
		      "9a",
		      "9b",
		      "9c",
		      "9d",
		      "9e",
		      "9f",
		      "a0",
		      "a1",
		      "a2",
		      "a3",
		      "a4",
		      "a5",
		      "a6",
		      "a7",
		      "a8",
		      "a9",
		      "aa",
		      "ab",
		      "ac",
		      "ad",
		      "ae",
		      "af",
		      "b0",
		      "b1",
		      "b2",
		      "b3",
		      "b4",
		      "b5",
		      "b6",
		      "b7",
		      "b8",
		      "b9",
		      "ba",
		      "bb",
		      "bc",
		      "bd",
		      "be",
		      "bf",
		      "c0",
		      "c1",
		      "c2",
		      "c3",
		      "c4",
		      "c5",
		      "c6",
		      "c7",
		      "c8",
		      "c9",
		      "ca",
		      "cb",
		      "cc",
		      "cd",
		      "ce",
		      "cf",
		      "d0",
		      "d1",
		      "d2",
		      "d3",
		      "d4",
		      "d5",
		      "d6",
		      "d7",
		      "d8",
		      "d9",
		      "da",
		      "db",
		      "dc",
		      "dd",
		      "de",
		      "df",
		      "e0",
		      "e1",
		      "e2",
		      "e3",
		      "e4",
		      "e5",
		      "e6",
		      "e7",
		      "e8",
		      "e9",
		      "ea",
		      "eb",
		      "ec",
		      "ed",
		      "ee",
		      "ef",
		      "f0",
		      "f1",
		      "f2",
		      "f3",
		      "f4",
		      "f5",
		      "f6",
		      "f7",
		      "f8",
		      "f9",
		      "fa",
		      "fb",
		      "fc",
		      "fd",
		      "fe",
		      "ff"
	};
	
	private char[] alphanumeric = new char[] {
			'0',
		      '1',
		      '2',
		      '3',
		      '4',
		      '5',
		      '6',
		      '7',
		      '8',
		      '9',
		      'a',
		      'b',
		      'c',
		      'd',
		      'e',
		      'f',
		      'g',
		      'h',
		      'i',
		      'j',
		      'k',
		      'l',
		      'm',
		      'n',
		      'o',
		      'p',
		      'q',
		      'r',
		      's',
		      't',
		      'u',
		      'v',
		      'w',
		      'x',
		      'y',
		      'z',
		      'A',
		      'B',
		      'C',
		      'D',
		      'E',
		      'F',
		      'G',
		      'H',
		      'I',
		      'J',
		      'K',
		      'L',
		      'M',
		      'N',
		      'O',
		      'P',
		      'Q',
		      'R',
		      'S',
		      'T',
		      'U',
		      'V',
		      'W',
		      'X',
		      'Y',
		      'Z'
	};
	
	private int[] CrcTable = new int[] {
			  (int) 0,
		      (int) 49345,
		      (int) 49537,
		      (int) 320,
		      (int) 49921,
		      (int) 960,
		      (int) 640,
		      (int) 49729,
		      (int) 50689,
		      (int) 1728,
		      (int) 1920,
		      (int) 51009,
		      (int) 1280,
		      (int) 50625,
		      (int) 50305,
		      (int) 1088,
		      (int) 52225,
		      (int) 3264,
		      (int) 3456,
		      (int) 52545,
		      (int) 3840,
		      (int) 53185,
		      (int) 52865,
		      (int) 3648,
		      (int) 2560,
		      (int) 51905,
		      (int) 52097,
		      (int) 2880,
		      (int) 51457,
		      (int) 2496,
		      (int) 2176,
		      (int) 51265,
		      (int) 55297,
		      (int) 6336,
		      (int) 6528,
		      (int) 55617,
		      (int) 6912,
		      (int) 56257,
		      (int) 55937,
		      (int) 6720,
		      (int) 7680,
		      (int) 57025,
		      (int) 57217,
		      (int) 8000,
		      (int) 56577,
		      (int) 7616,
		      (int) 7296,
		      (int) 56385,
		      (int) 5120,
		      (int) 54465,
		      (int) 54657,
		      (int) 5440,
		      (int) 55041,
		      (int) 6080,
		      (int) 5760,
		      (int) 54849,
		      (int) 53761,
		      (int) 4800,
		      (int) 4992,
		      (int) 54081,
		      (int) 4352,
		      (int) 53697,
		      (int) 53377,
		      (int) 4160,
		      (int) 61441,
		      (int) 12480,
		      (int) 12672,
		      (int) 61761,
		      (int) 13056,
		      (int) 62401,
		      (int) 62081,
		      (int) 12864,
		      (int) 13824,
		      (int) 63169,
		      (int) 63361,
		      (int) 14144,
		      (int) 62721,
		      (int) 13760,
		      (int) 13440,
		      (int) 62529,
		      (int) 15360,
		      (int) 64705,
		      (int) 64897,
		      (int) 15680,
		      (int) 65281,
		      (int) 16320,
		      (int) 16000,
		      (int) 65089,
		      (int) 64001,
		      (int) 15040,
		      (int) 15232,
		      (int) 64321,
		      (int) 14592,
		      (int) 63937,
		      (int) 63617,
		      (int) 14400,
		      (int) 10240,
		      (int) 59585,
		      (int) 59777,
		      (int) 10560,
		      (int) 60161,
		      (int) 11200,
		      (int) 10880,
		      (int) 59969,
		      (int) 60929,
		      (int) 11968,
		      (int) 12160,
		      (int) 61249,
		      (int) 11520,
		      (int) 60865,
		      (int) 60545,
		      (int) 11328,
		      (int) 58369,
		      (int) 9408,
		      (int) 9600,
		      (int) 58689,
		      (int) 9984,
		      (int) 59329,
		      (int) 59009,
		      (int) 9792,
		      (int) 8704,
		      (int) 58049,
		      (int) 58241,
		      (int) 9024,
		      (int) 57601,
		      (int) 8640,
		      (int) 8320,
		      (int) 57409,
		      (int) 40961,
		      (int) 24768,
		      (int) 24960,
		      (int) 41281,
		      (int) 25344,
		      (int) 41921,
		      (int) 41601,
		      (int) 25152,
		      (int) 26112,
		      (int) 42689,
		      (int) 42881,
		      (int) 26432,
		      (int) 42241,
		      (int) 26048,
		      (int) 25728,
		      (int) 42049,
		      (int) 27648,
		      (int) 44225,
		      (int) 44417,
		      (int) 27968,
		      (int) 44801,
		      (int) 28608,
		      (int) 28288,
		      (int) 44609,
		      (int) 43521,
		      (int) 27328,
		      (int) 27520,
		      (int) 43841,
		      (int) 26880,
		      (int) 43457,
		      (int) 43137,
		      (int) 26688,
		      (int) 30720,
		      (int) 47297,
		      (int) 47489,
		      (int) 31040,
		      (int) 47873,
		      (int) 31680,
		      (int) 31360,
		      (int) 47681,
		      (int) 48641,
		      (int) 32448,
		      (int) 32640,
		      (int) 48961,
		      (int) 32000,
		      (int) 48577,
		      (int) 48257,
		      (int) 31808,
		      (int) 46081,
		      (int) 29888,
		      (int) 30080,
		      (int) 46401,
		      (int) 30464,
		      (int) 47041,
		      (int) 46721,
		      (int) 30272,
		      (int) 29184,
		      (int) 45761,
		      (int) 45953,
		      (int) 29504,
		      (int) 45313,
		      (int) 29120,
		      (int) 28800,
		      (int) 45121,
		      (int) 20480,
		      (int) 37057,
		      (int) 37249,
		      (int) 20800,
		      (int) 37633,
		      (int) 21440,
		      (int) 21120,
		      (int) 37441,
		      (int) 38401,
		      (int) 22208,
		      (int) 22400,
		      (int) 38721,
		      (int) 21760,
		      (int) 38337,
		      (int) 38017,
		      (int) 21568,
		      (int) 39937,
		      (int) 23744,
		      (int) 23936,
		      (int) 40257,
		      (int) 24320,
		      (int) 40897,
		      (int) 40577,
		      (int) 24128,
		      (int) 23040,
		      (int) 39617,
		      (int) 39809,
		      (int) 23360,
		      (int) 39169,
		      (int) 22976,
		      (int) 22656,
		      (int) 38977,
		      (int) 34817,
		      (int) 18624,
		      (int) 18816,
		      (int) 35137,
		      (int) 19200,
		      (int) 35777,
		      (int) 35457,
		      (int) 19008,
		      (int) 19968,
		      (int) 36545,
		      (int) 36737,
		      (int) 20288,
		      (int) 36097,
		      (int) 19904,
		      (int) 19584,
		      (int) 35905,
		      (int) 17408,
		      (int) 33985,
		      (int) 34177,
		      (int) 17728,
		      (int) 34561,
		      (int) 18368,
		      (int) 18048,
		      (int) 34369,
		      (int) 33281,
		      (int) 17088,
		      (int) 17280,
		      (int) 33601,
		      (int) 16640,
		      (int) 33217,
		      (int) 32897,
		      (int) 16448
	};
	
	private String chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
	private String chars2 = "123456789abcdef";
	private Socket Client;
	private Thread ClientThread;
	private ClientStream Stream;
	private Random rand;
	private int Step;
	private String clientNick;
	private String clientLt;
	private String clientChallengeKey;
	private String serverChallengeKey;
    private String clientResponse;
    private ResultSet User;
    
    public boolean Disposed;
    
	public GpcmClient(Socket Client) {
		this.Client = Client;
		this.Stream = new ClientStream(Client);
		this.Disposed = false;
		this.ClientThread = new Thread() {
			public void run(){
		    	try {
		    		GpcmClient.this.HandleClient();
		    		Thread.sleep(2000);
		    	} catch (InterruptedException ex) {
		    		System.out.println(ex.getMessage());
		    	}
			}
		};
		this.ClientThread.start();
	}
	
	public void Dispose()
    {
      if (this.Client.isConnected())
		try {
			this.Client.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
      this.Disposed = true;
    }
	
	private void Logout() throws IOException {
		this.Dispose();
	}
	
	private void Update() {
		if(!this.Stream.HasData())
			return;
		String str = "";
		try {
			str = this.Stream.Read();
		} catch (IOException e) {
			e.printStackTrace();
		}
		String[] recv = str.split("\\");
		switch(recv[1]) {
			case "newuser":
			try {
				this.HandleNewUser(recv);
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
				++this.Step;
				break;
			case "login":
				this.ProcessLogin(recv);
				++this.Step;
				break;
			case "getprofile":
				if(this.Step < 2) {
					try {
						this.SendProfile(false);
					} catch (SQLException e) {
						e.printStackTrace();
					}
					++this.Step;
					break;
				} else {
					try {
						this.SendProfile(true);
					} catch (SQLException e) {
						e.printStackTrace();
					}
					break;
				}
			case "updatepro":
				this.UpdateUser(recv);
				break;
			case "logout":
				try {
					this.Logout();
				} catch (IOException e) {
					e.printStackTrace();
				}
				break;
			default:
				Server.Log(String.format("Unknown Message Passed: %s", str));
				break;
		}
	}
	
	private void ProcessLogin(String[] recv) {
		this.clientNick = this.GetParamValue(recv, "uniquenick");
		this.clientChallengeKey = this.GetParamValue(recv, "challenge");
		this.clientResponse = this.GetParamValue(recv, "response");
		try {
			this.SendProof();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	private void SendProof() throws SQLException {
		try {
			this.User = Server.Database.GetUser(this.clientNick);
		} catch(Exception e) {
			this.Dispose();
			return;
		}
		if(this.User == null) {
			this.Stream.Write("\\error\\\\err\\265\\fatal\\\\errmsg\\The uniquenick provided is incorrect!\\id\\1\\final\\");
	        this.Dispose();
		} else {
			int result = 0;
			try {
				result = Integer.parseInt(this.User.getString("id").toString());
			} catch (NumberFormatException | SQLException e) {
				e.printStackTrace();
			}
			if (this.clientResponse == this.GenerateResponseValue(this.clientNick, (String) this.User.getString("password"), this.clientChallengeKey, this.serverChallengeKey))
	        {
				this.clientLt = this.GenerateRandomString(22);
				this.Stream.Write(String.format("\\lc\\2\\sesskey\\%s\\proof\\%s\\userid\\%s\\profileid\\%s\\uniquenick\\%s\\lt\\%s__\\id\\1\\final\\", (Object) this.GenerateSession(), (Object) this.GenerateResponseValue(this.clientNick, (String) this.User.getString("password"), this.serverChallengeKey, this.clientChallengeKey), (Object) result, (Object) result, (Object) this.clientNick, (Object) this.clientLt));
	        } else {
	        	this.Stream.Write("\\error\\\\err\\260\\fatal\\\\errmsg\\The password provided is incorrect.\\id\\1\\final\\");
	            this.Dispose();
	        }
		}
	}
	
	private void SendProfile(boolean retrieve) throws SQLException
    {
      this.Stream.Write(String.format("\\pi\\\\profileid\\%s\\nick\\%s\\userid\\%s\\email\\%s\\sig\\%s\\uniquenick\\%s\\pid\\0\\firstname\\\\lastname\\\\countrycode\\%s\\birthday\\16844722\\lon\\0.000000\\lat\\0.000000\\loc\\\\id\\%s\\final\\", (Object) (String) this.User.getString("id"), (Object) this.clientNick, (Object) (String) this.User.getString("id"), (Object) (String) this.User.getString("email"), (Object) this.GenerateSig(), (Object) this.clientNick, (Object) (String) this.User.getString("country"), retrieve ? (Object) "5" : (Object) "2"));
    }
	
	private void HandleClient() {
		if (this.Stream.HasData())
			try {
				this.Stream.Read();
			} catch (IOException e) {
				e.printStackTrace();
			}
		 Server.Log("[GPCM] Client Connected: " + this.Client.getLocalSocketAddress());
	}
	
	private void HandleNewUser(String[] recv) throws SQLException {
		String parameterValue1 = this.GetParamValue(recv, "nick");
	    String parameterValue2 = this.GetParamValue(recv, "email");
	    boolean flag;
	    try {
	    	flag = Server.Database.UserExists(parameterValue1);
	    } catch(Exception e) {
	    	this.Dispose();
	    	return;
	    }
	    if(flag) {
	    	this.Stream.Write("\\error\\\\err\\516\\fatal\\\\errmsg\\This account name is already in use!\\id\\1\\final\\");
	    	this.Dispose();
	    } else {
	    	String Pass = Utils.DecodeGamespyPassword(this.GetParamValue(recv, "passwordenc"));
	    	boolean user1 = Server.Database.CreateUser(parameterValue1, Pass, parameterValue2, "US");
	    	ResultSet user2 = Server.Database.GetUser(parameterValue1);
	    	if (!user1 || user2 == null) {
	    		this.Stream.Write("\\error\\\\err\\516\\fatal\\\\errmsg\\Error creating account!\\id\\1\\final\\");
	            this.Dispose();
	    	} else {
	    		this.Stream.Write(String.format("\\nur\\\\userid\\%s\\profileid\\%s\\id\\1\\final\\", user2.getString("id"), user2.getString("id")));
	    	}
	    }
	}
	
	private void UpdateUser(String[] recv) {
		try {
			Server.Database.UpdateUser(this.GetParamValue(recv, "nick"), this.GetParamValue(recv, "countrycode"));
		} catch(Exception e) {
			this.Dispose();
		}
	}
	
	private String GetParamValue(String[] parts, String param) {
		boolean flag = false;
		for(String str : parts) {
			if(flag)
				return str;
			if (str == param)
				flag = true;
		}
		return "";
	}
	
	private String GenerateResponseValue(String nickname, String password, String challenge1, String challenge2) {
		MessageDigest md5 = null;
		try {
			md5 = MessageDigest.getInstance("MD5");
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		md5.update(password.getBytes());
		byte[] hash1 = md5.digest(password.getBytes());
		String str1 = "";
		for(byte num : hash1)
			str1 = str1 + this.Btoh[(int) num];
		String str2 = str1;
		for (int index = 0; index < 48; ++index)
			str2 = str2 + " ";
		String s = str2 + nickname + challenge1 + challenge2 + str1;
		md5.update(s.getBytes());
		byte[] hash2 = md5.digest(s.getBytes());
		StringBuilder stringBuilder = new StringBuilder();
		for(byte num : hash2)
			stringBuilder.append(String.format("{0}", (Object) this.Btoh[(int) num]));
		return ((Object) stringBuilder).toString();
	}
	
	public short GenerateSession() {
		int length = this.clientNick.length();
		int num = 0;
		for(int index=0; index<length;++index) {
//			num = (int) this.CrcTable[((int) Character.toString(this.clientNick.charAt(index)) ^ num) & (int) Byte.MAX_VALUE] ^ num >> 8;
		}
		return (short) num;
	}
	
	private String GenerateRandomString(int length) {
		String str = "";
		while(length > 0)
		{
			--length;
			str = str + this.rand;
		}
		return str;
	}
	
	private String GenerateSig() {
		String str = "";
		int num = 32;
		while (num > 0) {
			--num;
			str = str + this.rand;
		}
		return str;
	}
	
}
