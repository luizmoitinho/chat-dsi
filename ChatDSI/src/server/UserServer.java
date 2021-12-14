package server;

import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.json.JSONObject;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import models.UserModel;
import service.UserService;
import webserver.Client;

public class UserServer {
	UserService service;

	public UserServer() {
		this.service = new UserService();

	}
	
	public boolean logOut(JSONObject json) throws IOException {
		UserModel u = new UserModel();
		u.setId(Integer.parseInt(json.get("user_id").toString()));
		u.setIsOnline(false);
		u.setCurrentIp("");
		u.setCurrentPort(0);
		
		return this.service.signinUser(u);
	}

	public int authenticate(JSONObject json) throws IOException {
		if (!json.get("login").equals("") && !json.get("password").equals("")) {
			String password = this.getHashMd5(json.get("password").toString());
			if (password.equals(""))
				return 0;
			UserModel user = new UserModel(json.get("login").toString(), password);
			int id = this.service.authenticate(user);
			if (id != 0) 
				return id;
		}
		return 0;
	}
	
	public boolean signinUser(UserModel u) {
		return this.service.signinUser(u);
	}
	
	public boolean existLogin(JSONObject json) throws IOException {
		if (!json.get("login").equals("") ) {
			UserModel user = new UserModel();
			user.setLogin(json.get("login").toString());
			if (this.service.existLogin(user))
				return true;
		}
		return false;
	}

	public boolean create(JSONObject json) {
		//TODO: validar se usuário já existe.
		String password = this.getHashMd5(json.get("password").toString());
		if (password.equals(""))
			return false;
		UserModel user = new UserModel(json.get("name").toString(), json.get("login").toString(),
				password);
		user =  this.service.insert(user);
		return user.getId() != 0 ? true : false;
	}

	private String getHashMd5(String password) {
		try {
			MessageDigest m;
			m = MessageDigest.getInstance("MD5");
			m.update(password.getBytes(), 0, password.length());
			return new BigInteger(1, m.digest()).toString(16);
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return "";
	}

}
