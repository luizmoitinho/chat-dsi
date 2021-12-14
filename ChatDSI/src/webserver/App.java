package webserver;
import java.io.IOException;

public class App {
	public static void main(String args[]) {
		try {
			Client c1 = new Client(1);
			c1.start();
			//Client c2 = new Client(2);
			
			c1.sendMsg("Ola c2", 2);
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
