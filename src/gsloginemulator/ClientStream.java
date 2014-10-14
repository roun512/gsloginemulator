package gsloginemulator;

import java.io.*;
import java.net.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/*
 * @author roun512
 */

public class ClientStream {
	private static File LogFile = new File(System.getProperty("user.dir") + "stream.log");
	private Socket Client;
	private BufferedReader Reader;
	private InputStream Stream;
	private BufferedWriter Writer;
	private boolean Debugging;
	
	public ClientStream(Socket client) throws IOException {
		this.Client = client;
		this.Stream = this.Client.getInputStream();
		this.Debugging = true;
	}

	public boolean HasData() {
		if(this.Reader.lines().count() > 0) {
			return true;
		} else {
			return false;
		}
	}
	
	public String Read() throws IOException {
		int num = 0;
		int receiveBufferSize = this.Client.getSendBufferSize();
		char[] array = new char[receiveBufferSize];
		String str = "";
		do {
			num += this.Reader.read(array, 0, receiveBufferSize);
			int newSize = 0;
			char[] numArray = array;
			for (int index = 0; index < numArray.length && (int) numArray[index] != 0; ++index) {
				++newSize;
			}
			str = str + array.toString();
			ClientStream.Log(String.format("Port %s Recieves: %s", this.Client.getLocalPort(), str));
		} while(this.HasData());
		
		if(num == 0)
			return "";
		else
			return str.toString();
	}
	
	public void Write(String message, Object[] items) throws IOException {
		message = String.format(message, items);
		ClientStream.Log(String.format("Port %s Recieves: %s", this.Client.getLocalPort(), message));
		this.Write(message.getBytes());
	}
	
	public void Write(byte[] bytes) throws IOException {
		
		this.Writer.write(bytes.toString());
	}
	
	private static void Log(String message) {
		Date now = new Date();
		DateFormat dateFormat = new SimpleDateFormat("MM/dd HH:mm");
		OutputStreamWriter writer;
		try {
			FileOutputStream Logfile = new FileOutputStream(ClientStream.LogFile);
			writer = new OutputStreamWriter(Logfile);
			writer.write(dateFormat.format(now) + " > " + message);
			writer.flush();
			writer.close();
		} catch (FileNotFoundException e) {
			System.out.println("server.log not found! error: " + e.getMessage());
		} catch (IOException e) {
			System.out.println("Unable to write to server.log! error: " + e.getMessage());
		}
	}
	
	private static void Log(String message, Object[] items)
    {
      ClientStream.Log(String.format(message, items));
    }
}
