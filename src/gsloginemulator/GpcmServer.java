package gsloginemulator;

import java.net.*;
import java.util.ArrayList;
import java.util.List;
import java.io.*;

/*
 * @author roun512 
 * 
 */

public class GpcmServer {

	private static ServerSocket Listener = null;
	private static List<GpcmClient> Clients = new ArrayList<GpcmClient>();
	private Thread ListenThread;
	
	public GpcmServer() throws IOException, InterruptedException {
		GpcmServer.Listener = new ServerSocket(29900);
		this.ListenThread = new Thread(new Runnable() {
			public void run(){
			    try {
				   this.ListenForClients();
			    } catch (IOException e) {
			    	e.printStackTrace();
				}
			}
			
			private void ListenForClients() throws IOException {
				while(true)
				{
					GpcmServer.Clients.add(new GpcmClient(GpcmServer.Listener.accept()));
					for (int index = GpcmServer.Clients.size() - 1; index >= 0; --index) {
						if(GpcmServer.Clients.get(index).Disposed) {
							GpcmServer.Clients.remove(index);
						}
					}
					try {
						Thread.sleep(100);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		});
		this.ListenThread.start();
	}
	
	@SuppressWarnings("deprecation")
	public void Shutdown() throws IOException {
		this.ListenThread.stop();
		for (GpcmClient client : GpcmServer.Clients) {
			client.Dispose();
		}
		GpcmServer.Listener.close();
	}
	
	public int NumClients() {
		return GpcmServer.Clients.size();
	}
}
