package gsloginemulator;

import java.io.IOException;
import java.net.Socket;
import java.sql.ResultSet;
import java.sql.SQLException;

/*
 * @author roun512
 */
public class GpspClient {

	private ClientStream Stream;
	private Socket Client;
	private Thread ClientThread;
	private ResultSet ClientData;
	public boolean Disposed;
	
	
	public GpspClient(Socket client) {
		this.Disposed = false;
		this.Client = client;
		this.Stream = new ClientStream(client);
		this.ClientThread = new Thread() {
			public void run(){
		    	try {
		    		GpspClient.this.Start();
		    		Thread.sleep(2000);
		    	} catch (InterruptedException ex) {
		    		System.out.println(ex.getMessage());
		    	}
			}
		};
		this.ClientThread.start();
	}

	public void Dispose() {
		try {
			if(this.Client.getKeepAlive()) {
				this.Client.close();
			}
			this.Disposed = true;
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void Start() throws InterruptedException {
		Server.Log("[GPSP] Client Connected: " + this.Client.getLocalSocketAddress());
		while(this.Client.isConnected()) {
			try {
				this.Update();
			} catch (IOException e) {
				e.printStackTrace();
			}
			Thread.sleep(200);
		}
		Server.Log("[GPSP] Client Disconnected: " + this.Client.getLocalSocketAddress());
		this.Dispose();
	}
	
	public void Update() throws IOException {
		if(!this.Stream.HasData()) {
			return;
		}
		String[] recv = this.Stream.Read().split("\\");
		switch(recv[1]) {
			case "nicks":
			try {
				this.SendGPSP(recv);
			} catch (SQLException e) {
				e.printStackTrace();
			}
				break;
			case "check":
				this.SendCheck(recv);
				break;
		}
	}
	
	private void SendGPSP(String[] recv) throws SQLException {
		try {
			this.ClientData = Server.Database.GetUser(this.GetParamValue(recv, "email"), this.GetParamValue(recv, "pass"));
		} catch(Exception e) {
			this.Dispose();
			return;
		}
		
		if(this.ClientData == null) {
			this.Stream.Write("\\nr\\{0}\\ndone\\\\final\\");
		} else {
			this.Stream.Write(String.format("\\nr\\1\\nick\\%s\\uniquenick\\%s\\ndone\\\\final\\", this.ClientData.getString("name").toString(), this.ClientData.getString("name").toString()));
		}
	}
	
	private void SendCheck(String[] recv)
    {
      int pid;
      try
      {
        pid = Server.Database.GetPID(this.GetParamValue(recv, "nick"));
      }
      catch (Exception e)
      {
        this.Dispose();
        return;
      }
      if (pid == 0)
        this.Stream.Write("\\cur\\0\\pid\\0\\final\\");
      else
        this.Stream.Write(String.format("\\cur\\0\\pid\\%s\\final\\", (Object) pid));
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

}
