
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class Server extends Thread {
	static ArrayList<Socket> listClients = new ArrayList<Socket>();
	
	public void run() {
		ServerSocket serverSocket;
		try {
			serverSocket = new ServerSocket(9876);
			System.out.println("server on: "+serverSocket.getLocalSocketAddress());
			while (true) {
				Socket socket = serverSocket.accept();
				InputStreamReader in = new InputStreamReader(socket.getInputStream());
				BufferedReader bf = new BufferedReader(in);
				String str = bf.readLine();
				System.out.println(str);
				if(str != null) {
					System.out.println("client: "+socket.getLocalAddress());
					System.out.println("client: "+str);	
					
					PrintWriter pr = new PrintWriter(socket.getOutputStream());
					pr.println("msg captured");
					pr.flush();
				}
				
				listClients.add(socket);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}