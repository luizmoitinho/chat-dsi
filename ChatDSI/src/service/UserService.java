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
	
	public UserModel getById(int id) {
		UserModel u = this.userRespository.getById(id);
		return u;
	}
	
	public int authenticate(UserModel u) {
		return this.userRespository.authenticate(u);
	}
	
	public boolean signinUser(UserModel u) {
		return this.userRespository.signinUser(u);
	}
	
	public boolean logOut(UserModel u) {
		return this.userRespository.signinUser(u);
	}
	
	public boolean existLogin(UserModel u) {
		return this.userRespository.existLogin(u);
	}
	
	public ArrayList<UserModel> getAll(int id){
		return this.userRespository.getAll(id);
	}
	
	public ArrayList<UserModel> getAll2(int id){
		return this.userRespository.getAll2(id);
	}

}
