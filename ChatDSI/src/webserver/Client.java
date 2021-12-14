package webserver;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.SocketException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;


public class Client extends Thread{
	public Socket socket;
	public int userId;

	public Client(int userId) throws UnknownHostException, IOException {
		this.userId = userId;
		socket = new Socket("127.0.0.1", 9876);
		System.out.println("client socket created: "+this.socket.getLocalAddress()+":"+this.socket.getLocalPort());
	}
	
	public void sendMsg(String msg, int userId) throws IOException {
		PrintWriter pr = new PrintWriter(this.socket.getOutputStream());
		pr.println(msg+";"+String.valueOf(userId));
		pr.flush();
	}
	
	public void run() {
		InputStreamReader in;
		try {
			in = new InputStreamReader(this.socket.getInputStream());
			BufferedReader bf = new BufferedReader(in);
			String str = bf.readLine();
			if(str != null) {
				System.out.println("server: "+this.socket.getLocalAddress());
				System.out.println("server: "+str);				
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
}