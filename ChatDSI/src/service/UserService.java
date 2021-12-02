package service;

import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Base64;

import models.User;
import repository.UserRepository;

public class UserService {
	private User user;
	private UserRepository userRespository;

	public UserService() {
		userRespository = new UserRepository();
	}

	public User insert(User u) {
		MessageDigest algorithm;
		try {
			algorithm = MessageDigest.getInstance("SHA-256");
			byte[] hash = algorithm.digest(u.getPassword().getBytes(StandardCharsets.UTF_8));
			u.setPassword(Base64.getEncoder().encodeToString(hash));
			u = this.userRespository.insert(u);
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return u;
	}
	
	public ArrayList<User> getAll(){
		return this.userRespository.getAll();
	}

}
