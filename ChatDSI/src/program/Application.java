package program;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import models.UserModel;
import service.UserService;

public class Application {
	public static void main(String args[]) {
		UserService userService = new UserService();
		
		UserModel u = new UserModel("Luiz Carlos Costa Moitinho", "luiz.moitinho", "123456", true, false, "127.0.0.1", 3000);
		u = userService.insert(u);
	
		for(UserModel u1 : userService.getAll()) 
			System.out.println(u1.toString());		
	}
}
