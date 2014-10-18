package gsloginemulator;

import java.io.IOException;
import java.net.*;
import java.util.ArrayList;
import java.util.List;

/*
 * @author roun512
 */

public class GpspServer {

	private List<GpspClient> Clients = new ArrayList<GpspClient>();
	private ServerSocket Listener;
	private Thread ListenThread;
	
	public GpspServer() {
		try {
			this.Listener = new ServerSocket(29901);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		this.ListenThread = new Thread();// {
//			public void run(){
//		    	try {
//		    		this.ListenForClients();
//		    	} catch (InterruptedException ex) {
//		    		System.out.println(ex.getMessage());
//		    	} catch (IOException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				}
//			}
//		};
		this.ListenThread.start();
		//try {
		//} catch (IOException e) {
		//	// TODO Auto-generated catch block
		//	e.printStackTrace();
		//} catch (InterruptedException e) {
		//	// TODO Auto-generated catch block
		//	e.printStackTrace();
		//}
	}
		
	
	public void Shutdown() {
		this.ListenThread.stop();
		for (GpspClient client : this.Clients) {
			client.Dispose();
		}
		try {
			this.Listener.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private void ListenForClients() throws IOException {
		while (true) {
			this.Clients.add(new GpspClient(this.Listener.accept()));
			for (int index = this.Clients.size() - 1; index >= 0; --index) {
				if(this.Clients.get(index).Disposed) {
					this.Clients.remove(index);
				}
			}
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}
