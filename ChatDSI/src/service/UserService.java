package service;

import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Base64;

import models.UserModel;
import repository.UserRepository;

public class UserService {
	private UserModel user;
	private UserRepository userRespository;

	public UserService() {
		userRespository = new UserRepository();
	}

	public UserModel insert(UserModel u) {
		u = this.userRespository.insert(u);
		return u;
	}
	
	public boolean authenticate(UserModel u) {
		return this.userRespository.authenticate(u);
	}
	
	public ArrayList<UserModel> getAll(){
		return this.userRespository.getAll();
	}

}
