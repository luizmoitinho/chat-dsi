package webserver;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class Server {
	static ArrayList<Socket> listClients = new ArrayList<Socket>();

	public static void main(String argv[]) throws Exception {
		ServerSocket serverSocket = new ServerSocket(9876);
		System.out.println("server on: "+serverSocket.getLocalSocketAddress());
		while (true) {
			Socket socket = serverSocket.accept();
			InputStreamReader in = new InputStreamReader(socket.getInputStream());
			BufferedReader bf = new BufferedReader(in);
			String str = bf.readLine();
			if(str != null) {
				System.out.println("client: "+socket.getLocalAddress());
				System.out.println("client: "+str);	
				
				PrintWriter pr = new PrintWriter(socket.getOutputStream());
				pr.println("msg captured");
				pr.flush();
			}
			
			listClients.add(socket);
		}
	}
}