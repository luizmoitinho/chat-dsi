package server;

import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;

import org.json.JSONObject;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import models.MessageModel;
import models.UserModel;
import service.MessageService;
import service.UserService;
import webserver.Client;

public class MessageServer {
	MessageService service;

	public MessageServer() {
		this.service = new MessageService();

	}


	public ArrayList<MessageModel> getAll(JSONObject json){
		if (!json.get("from_user").equals("") && !json.get("to_user").equals("")) {
			int from_user = Integer.parseInt(json.get("from_user").toString());
			int to_user = Integer.parseInt(json.get("to_user").toString());
			return this.service.getAll(from_user, to_user);	
		}
		return new ArrayList<MessageModel>();
	}
	



}
