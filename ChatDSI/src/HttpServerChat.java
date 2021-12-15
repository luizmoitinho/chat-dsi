
import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.util.ArrayList;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;

import models.MessageModel;
import models.UserModel;
import server.MessageServer;
import server.ServerGeneric;
import server.UserServer;

import org.json.JSONObject;



public class HttpServerChat {
	public static ArrayList<Client> clients = new ArrayList<Client>();
	public static void main(String[] args) throws Exception {
		Server serverSocket = new Server();
		serverSocket.start();
		
		InetSocketAddress address = new InetSocketAddress(8000);
		HttpServer api = HttpServer.create(address, 0);

		System.out.println("web server on: " + api.getAddress());
		api.createContext("/authenticate/", new Authenticate());
		api.createContext("/exist_login/", new ExistLogin());
		api.createContext("/create_user/", new CreateUser());
		api.createContext("/logout/", new LogOut());
		api.createContext("/contacts/", new getContacts());
		api.createContext("/messages/", new Messages());
		api.setExecutor(null); // creates a default executor
		api.start();

	}
	
	public static class Messages implements HttpHandler {
		public void handle(HttpExchange request) throws IOException {
			request.getResponseHeaders().add("Access-Control-Allow-Origin", "*");

			String response;
			MessageServer messageServer = new MessageServer();
			JSONObject jsonObject = ServerGeneric.getJson(request);			
			ArrayList<MessageModel> messages = messageServer.getAll(jsonObject);
			String arrayMessages = "[";
			for(MessageModel m: messages) {
				arrayMessages += m.toJSON()+","; 
			}
			arrayMessages = arrayMessages.substring(0, arrayMessages.length()-1);
			arrayMessages += "]";
			if (messages.size()>0) {
				response = "{\"message\":\"ok\", \"messages\":"+arrayMessages+"}";
				request.sendResponseHeaders(200, response.length());
			} else {
				response = "{\"message\": \"not founded\"}";
				request.sendResponseHeaders(404, response.length());
			}
			
			OutputStream responseStream = request.getResponseBody();
			try {
				responseStream.write(response.getBytes());
			} finally {
				responseStream.close();
			}
		}
	}

	public static class getContacts implements HttpHandler {
		public void handle(HttpExchange request) throws IOException {
			request.getResponseHeaders().add("Access-Control-Allow-Origin", "*");
			String response;
			UserServer userServer = new UserServer();
			JSONObject jsonObject = ServerGeneric.getJson(request);
			ArrayList<UserModel> contacts = userServer.getContacts(jsonObject);
			String arrayUser = "[";
			for(UserModel u: contacts) {
				arrayUser += u.toJSON()+","; 
			}
			arrayUser = arrayUser.substring(0, arrayUser.length()-1);
			arrayUser += "]";
			if (contacts.size()>0) {
				response = "{\"message\":\"ok\", \"contacts\":"+arrayUser+"}";
				request.sendResponseHeaders(200, response.length());
			} else {
				response = "{\"message\": \"not founded\"}";
				request.sendResponseHeaders(404, response.length());
			}
			
			OutputStream responseStream = request.getResponseBody();
			try {
				responseStream.write(response.getBytes());
			} finally {
				responseStream.close();
			}
		}
	}
	
	public static class LogOut implements HttpHandler {
		public void handle(HttpExchange request) throws IOException {
			request.getResponseHeaders().add("Access-Control-Allow-Origin", "*");

			boolean logout = true;
			String response;
			
			UserServer userServer = new UserServer();
			JSONObject jsonObject = ServerGeneric.getJson(request);
			System.out.println("request:"+request.getRemoteAddress()+ "| data: "+jsonObject.toString());
			//update is online
			logout &= userServer.logOut(jsonObject);
			logout &= removeClient(Integer.parseInt((String) jsonObject.get("user_id")));
			if (logout) {
				response = "{\"message\":\"ok\"}";
				request.sendResponseHeaders(200, response.length());
			} else {
				response = "{\"message\": \"login not exist\"}";
				request.sendResponseHeaders(204, response.length());
			}
			OutputStream responseStream = request.getResponseBody();
			try {
				responseStream.write(response.getBytes());
			} finally {
				responseStream.close();
			}
		}
	}
	
	public static class Authenticate implements HttpHandler {
		public void handle(HttpExchange request) throws IOException {
			request.getResponseHeaders().add("Access-Control-Allow-Origin", "*");

			UserServer userServer = new UserServer();
			JSONObject jsonObject = ServerGeneric.getJson(request);
			System.out.println("request:"+request.getRemoteAddress()+ "| data: "+jsonObject.toString());

			String response;
			int id = userServer.authenticate(jsonObject);
			if (id != 0) {
				//create socket		
				Client client = new Client(id);
				//client.start();
				
				clients.add(client);
				
				UserModel u = new UserModel();
				u.setId(id);
				u.setIsOnline(true);
				u.setCurrentIp(client.socket.getLocalAddress().toString().replace("/", ""));
				u.setCurrentPort(client.socket.getPort());
				userServer.signinUser(u);
				response = "{\"message\":\"ok\""+
							",\"user_id\":\""+String.valueOf(u.getId())+"\""+
							",\"ip\":\"" + u.getCurrentIp()+"\""+
							",\"port\":\""+u.getCurrentPort()+"\""+
							"}";
				request.sendResponseHeaders(200, response.length());
				
			} else {
				response = "{\"message\": \"login or password are wrong\"}";
				request.sendResponseHeaders(401, response.length());
			}
			OutputStream responseStream = request.getResponseBody();
			try {
				responseStream.write(response.getBytes());
			} finally {
				responseStream.close();
			}

		}
	}
	
	public static class ExistLogin implements HttpHandler {
		public void handle(HttpExchange request) throws IOException {
			request.getResponseHeaders().add("Access-Control-Allow-Origin", "*");

			UserServer userServer = new UserServer();
			JSONObject jsonObject = ServerGeneric.getJson(request);
			System.out.println("request:"+request.getRemoteAddress()+ "| data: "+jsonObject.toString());

			String response;
			if (userServer.existLogin(jsonObject)) {
				response = "{\"message\":\"ok\"}";
				request.sendResponseHeaders(200, response.length());
			} else {
				response = "{\"message\": \"login not exist\"}";
				request.sendResponseHeaders(204, response.length());
			}
			OutputStream responseStream = request.getResponseBody();
			try {
				responseStream.write(response.getBytes());
			} finally {
				responseStream.close();
			}

		}
	}

	public static class CreateUser implements HttpHandler {
		public void handle(HttpExchange request) throws IOException {
			request.getResponseHeaders().add("Access-Control-Allow-Origin", "*");
			UserServer userServer = new UserServer();
			JSONObject jsonObject = ServerGeneric.getJson(request);
			System.out.println("request:"+request.getRemoteAddress()+ "| data: "+jsonObject.toString());
			String response;
			if (userServer.create(jsonObject)) {
				response = "{\"message\":\"Created\"}";
				request.sendResponseHeaders(201, response.length());
			} else {
				response = "{\"message\": \"user can not be created\"}";
				request.sendResponseHeaders(401, response.length());
			}
			OutputStream responseStream = request.getResponseBody();
			try {
				responseStream.write(response.getBytes());
			} finally {
				responseStream.close();
			}
		}
	}
	
	public static boolean removeClient(int userId) throws IOException {
		for (Client c: clients) {
			if(c.getId() == userId) {
				System.out.println("client deleted id: "+String.valueOf(c.getId()));
				c.socket.close();
				c.stop();
				clients.remove(c);
				return true;
			}
		}
		return false;
	}

}