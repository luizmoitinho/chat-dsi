package program;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import models.User;
import service.UserService;

public class Application {
	public static void main(String args[]) {
		UserService userService = new UserService();
		
		User u = new User("Luiz Carlos Costa Moitinho", "luiz.moitinho", "123456", true, false, "127.0.0.1", 3000);
		u = userService.insert(u);
	
		for(User u1 : userService.getAll()) 
			System.out.println(u1.toString());		
		
	}
}
