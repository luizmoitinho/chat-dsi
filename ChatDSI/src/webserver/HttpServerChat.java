package webserver;
import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.util.ArrayList;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;

import models.UserModel;
import server.ServerGeneric;
import server.UserServer;

import org.json.JSONObject;



public class HttpServerChat {
	public static ArrayList<Client> clients = new ArrayList<Client>();
;	public static void main(String[] args) throws Exception {
		InetSocketAddress address = new InetSocketAddress(8000);
		HttpServer server = HttpServer.create(address, 0);

		System.out.println("web server on: " + server.getAddress());
		server.createContext("/authenticate/", new Authenticate());
		server.createContext("/exist_login/", new ExistLogin());
		server.createContext("/create_user/", new CreateUser());
		server.createContext("/logout/", new LogOut());
		server.setExecutor(null); // creates a default executor
		server.start();

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
			logout &= removeClient(Integer.parseInt((String) jsonObject.get("userId")));
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
				client.start();
				
				clients.add(client);
				
				UserModel u = new UserModel();
				u.setId(id);
				u.setIsOnline(true);
				u.setCurrentIp(client.socket.getLocalAddress().toString());
				u.setCurrentPort(client.socket.getPort());
				
				
				response = "{\"message\":\"ok\","+
							"\"user_id\":"+String.valueOf(u.getId())+
							"\"ip\":" + u.getCurrentIp()+
							"\"port\":"+u.getCurrentIp()+
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
				c.socket.close();
				c.stop();
				clients.remove(c);
				return true;
			}
		}
		return false;
	}

}