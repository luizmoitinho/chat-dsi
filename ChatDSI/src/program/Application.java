package program;

import models.User;
import repository.UserRepository;

public class Application {
	public static void main(String args[]) {
		UserRepository ur = new UserRepository();
		
		User u = new User("teste", "teste", "123", false, false, "127.0.0.1",1);
		//u = ur.insert(u);
	
		for(User u1 : ur.getAll()) 
			System.out.println(u1.toString());
		
		
		
	}
}
