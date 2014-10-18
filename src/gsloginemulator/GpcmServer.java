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

	private ServerSocket Listener = null;
	private List<GpcmClient> Clients = new ArrayList<GpcmClient>();
	private Thread ListenThread;
	
	public GpcmServer() throws IOException, InterruptedException {
		this.Listener = new ServerSocket(29900);
		this.ListenThread = new Thread();// {
//			public void run(){
//			    	try {
//			    		GpcmServer server = new GpcmServer();
//			    		server.ListenForClients();
//			    	} catch (InterruptedException ex) {
//			    		System.out.println(ex.getMessage());
//			    	} catch (IOException e) {
//						// TODO Auto-generated catch block
//						e.printStackTrace();
//					}
//			}
//		};
		this.ListenThread.start();
		this.ListenForClients();
	}
	
	@SuppressWarnings("deprecation")
	public void Shutdown() throws IOException {
		this.ListenThread.stop();
		for (GpcmClient client : this.Clients) {
			client.Dispose();
		}
		this.Listener.close();
	}
	
	public int NumClients() {
		return this.Clients.size();
	}
	
	private void ListenForClients() throws IOException, InterruptedException {
		//while(true)
		//{
			this.Clients.add(new GpcmClient(this.Listener.accept()));
			for (int index = this.Clients.size() - 1; index >= 0; --index) {
				if(this.Clients.get(index).Disposed) {
					this.Clients.remove(index);
				}
			}
			Thread.sleep(100);
		//}
	}

}
