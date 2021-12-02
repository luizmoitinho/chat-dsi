package models;

public class User {
	private int id;
	private String name, login, password;
	private String currentIp;
	private int currentPort;

	private Boolean isOnline, isPointFocal;
	
    public User(String name, String login, String password, Boolean isOnline, Boolean isPointFocal, String currentIp, int currentPort) {
    	this.name = name;
    	this.login = login;
    	this.password = password;
    	this.isOnline = isOnline;
    	this.isPointFocal = isPointFocal;
    	this.currentIp = currentIp;
    	this.currentPort = currentPort;	
    }
    
    
	@Override
	public String toString() {
		return "User [id=" + id + ", name=" + name + ", login=" + login + ", password=" + password + ", currentIp="
				+ currentIp + ", currentPort=" + currentPort + ", isOnline=" + isOnline + ", isPointFocal="
				+ isPointFocal + "]";
	}


	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Boolean getIsOnline() {
		return isOnline;
	}

	public void setIsOnline(Boolean isOnline) {
		this.isOnline = isOnline;
	}

	public Boolean getIsPointFocal() {
		return isPointFocal;
	}

	public void setIsPointFocal(Boolean isPointFocal) {
		this.isPointFocal = isPointFocal;
	}
	
	public int getCurrentPort() {
		return currentPort;
	}

	public void setCurrentPort(int currentPort) {
		this.currentPort = currentPort;
	}
	
	public String getCurentIp() {
		return currentIp;
	}
	
	public void setCurentIp(String currentIp) {
		this.currentIp = currentIp;
	}
    
    
   
    
    
	
}
