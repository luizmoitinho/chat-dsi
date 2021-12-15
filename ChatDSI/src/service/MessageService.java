package service;

import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Base64;

import models.MessageModel;
import models.UserModel;
import repository.MessageRepository;
import repository.UserRepository;

public class MessageService {
	private MessageRepository respository;

	public MessageService() {
		respository = new MessageRepository();
	}

	public MessageModel insert(MessageModel m) {
		m = this.respository.insert(m);
		return m;
	}
	
	public ArrayList<MessageModel> getAll(int from_user, int to_user){
		return this.respository.getAll(from_user, to_user);
	}

}
