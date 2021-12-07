import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;
import org.json.JSONObject;
import org.json.JSONException;


public class HttpServerChat {

  public static void main(String[] args) throws Exception {
    HttpServer server = HttpServer.create(new InetSocketAddress(8000), 0);
    System.out.println("web server on: "+server.getAddress());
    server.createContext("/user", new User());
    server.setExecutor(null); // creates a default executor
    server.start();
  }

  static class User implements HttpHandler {
    public void handle(HttpExchange t) throws IOException {
      String s = new String(t.getRequestBody().readAllBytes(), "UTF-8");
 
      JSONObject jsonObject = new JSONObject(s);
      System.out.println("OBJECT : "+jsonObject.toString());
      byte [] response = "Usuários".getBytes();
      t.sendResponseHeaders(200, response.length);
      OutputStream os = t.getResponseBody();
      os.write(response);
      os.close();
    }
  }
}