package models;

public class MessageModel {
	private int id;
	private int from_user, to_user;
	private String content;
	private String created_at;
	
	public MessageModel(int id, int from_user, int to_user, String content, String created_at) {
		this.id = id;
		this.from_user = from_user;
		this.to_user = to_user;
		this.content = content;
		this.created_at =  created_at;
	}
	
	public MessageModel(int from_user, int to_user, String content) {
		this.from_user = from_user;
		this.to_user = to_user;
		this.content = content;
	}
	
	public String toJSON() {
		return "{\"id\":\""+String.valueOf(this.getId())+"\""+
				",\"from_user\":\""+String.valueOf(this.getFrom_user())+"\""+
				",\"to_user\":\""+String.valueOf(this.getTo_user())+"\""+
				",\"content\":\""+this.getContent()+"\"}";

	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getFrom_user() {
		return from_user;
	}
	public void setFrom_user(int from_user) {
		this.from_user = from_user;
	}
	public int getTo_user() {
		return to_user;
	}
	public void setTo_user(int to_user) {
		this.to_user = to_user;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getCreated_at() {
		return created_at;
	}
	public void setCreated_at(String created_at) {
		this.created_at = created_at;
	}
}
