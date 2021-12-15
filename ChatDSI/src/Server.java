
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

import org.json.JSONObject;

import com.sun.net.httpserver.HttpExchange;

import models.MessageModel;
import server.ServerGeneric;
import service.MessageService;

public class Server extends Thread {
	static ArrayList<Socket> listClients = new ArrayList<Socket>();
	
	public void run() {
		ServerSocket serverSocket;
		try {
			serverSocket = new ServerSocket(9876);
			System.out.println("server on: "+serverSocket.getLocalSocketAddress());
			while (true) {
				System.out.println("new connection");
				Socket socket = serverSocket.accept();
				InputStream is = socket.getInputStream();
				InputStreamReader isReader = new InputStreamReader(is);
				BufferedReader br = new BufferedReader(isReader);
				
				String headerLine = null;
			    while((headerLine = br.readLine()).length() != 0){}

			    
				StringBuilder payload = new StringBuilder();
		        while(br.ready())
		            payload.append((char) br.read());
		            
				System.out.println("Payload data is: "+getJson(payload.toString()));
				JSONObject jsonObject = new JSONObject(payload.toString());
				MessageService ms = new MessageService();
				MessageModel m = new MessageModel(Integer.parseInt(jsonObject.get("from_user").toString()), 
												  Integer.parseInt(jsonObject.get("to_user").toString()),
												  jsonObject.get("content").toString());
				ms.insert(m);
				
				PrintWriter writer = new PrintWriter(socket.getOutputStream ());
				writer.print ("HTTP/1.1 200 OK \r\n"); 
				writer.print ("Access-Control-Allow-Origin: * \r\n"); 
				writer.print ("Access-Control-Allow-Credentials: true \r\n"); 
				
				System.out.println("ok");
				writer.print ("\r\n"); 
		        writer.flush ();
		        isReader.close ();
		        writer.close ();
				socket.close();
				
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	static public JSONObject getJson(String str) {
		JSONObject jsonObject = new JSONObject(str);
		return jsonObject;
	}
	

}