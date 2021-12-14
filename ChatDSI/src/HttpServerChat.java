import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;

import server.ServerGeneric;
import server.UserServer;

import org.json.JSONObject;

public class HttpServerChat {
	public static void main(String[] args) throws Exception {
		InetSocketAddress address = new InetSocketAddress(8000);
		HttpServer server = HttpServer.create(address, 0);

		System.out.println("web server on: " + server.getAddress());
		server.createContext("/authenticate/", new Authenticate());
		server.createContext("/exist_login/", new ExistLogin());
		server.createContext("/create_user/", new CreateUser());
		server.setExecutor(null); // creates a default executor
		server.start();

	}

	public static class Authenticate implements HttpHandler {
		public void handle(HttpExchange request) throws IOException {
			request.getResponseHeaders().add("Access-Control-Allow-Origin", "*");

			UserServer userServer = new UserServer();
			JSONObject jsonObject = ServerGeneric.getJson(request);
			System.out.println("request:"+request.getRemoteAddress()+ "| data: "+jsonObject.toString());

			String response;
			if (userServer.authenticate(jsonObject)) {
				response = "{\"message\":\"ok\"}";
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

}