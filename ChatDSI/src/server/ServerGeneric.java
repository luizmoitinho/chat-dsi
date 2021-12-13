package server;
import java.io.IOException;
import java.io.OutputStream;

import org.json.JSONObject;

import com.sun.net.httpserver.HttpExchange;

public class ServerGeneric {
	
	static public JSONObject getJson(HttpExchange t) {
		JSONObject jsonObject = new JSONObject();
		try {
			String s;
			s = new String(t.getRequestBody().readAllBytes(), "UTF-8");
			jsonObject = new JSONObject(s);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.printf("error to receive json object: %s\n",e.getMessage());
		}
		return jsonObject;
	}
	
	static public void closeRequest(HttpExchange t, byte [] response) {
	      try {
	    	OutputStream os = t.getResponseBody();
			os.write(response);
			os.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.printf("error to close request: %s\n",e.getMessage());
		}
	}
	
	
	
}
