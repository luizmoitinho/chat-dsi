import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerWeb {

	public static void main(String argv[]) throws Exception {
		String clientSentence;
		String capitalizedSentence;
		ServerSocket welcomeSocket = new ServerSocket(6789);
		System.out.println("server web online...\n");
		System.out.printf("%s:%d",welcomeSocket.getInetAddress(),welcomeSocket.getLocalPort());
		while (true) {
			Socket connectionSocket = welcomeSocket.accept();
			BufferedReader inFromClient = new BufferedReader(new InputStreamReader(connectionSocket.getInputStream()));
			DataOutputStream outToClient = new DataOutputStream(connectionSocket.getOutputStream());

	        String s;
	        while ((s = inFromClient.readLine()) != null) {
	            System.out.println(s);
	            if (s.isEmpty()) {
	                break;
	            }
	        }

			outToClient.writeBytes("HTTP/1.0 200 OK\r\n");

			outToClient.close();
			inFromClient.close();
	        connectionSocket.close();
			
		}
	}

}
