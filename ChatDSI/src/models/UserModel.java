package models;

public class UserModel {
	private int id;
	private String name, login, password;
	private String currentIp;
	private int currentPort;

	private Boolean isOnline, isPointFocal;
	
    public UserModel() {
    	
    }
	
    public UserModel(String login, String password) {
    	this.login = login;
    	this.password = password;
    }
    
    public UserModel(String name, String login, String password) {
    	this.name = name;
    	this.login = login;
    	this.password = password;
    	this.isOnline = false;
    	this.isPointFocal = false;
    	this.currentIp = "";
    	this.currentPort = 0;	
    }
	
    public UserModel(String name, String login, String password, Boolean isOnline, Boolean isPointFocal, String currentIp, int currentPort) {
    	this.name = name;
    	this.login = login;
    	this.password = password;
    	this.isOnline = isOnline;
    	this.isPointFocal = isPointFocal;
    	this.currentIp = currentIp;
    	this.currentPort = currentPort;	
    }
    
    public UserModel(int id, String name, String login,  Boolean isOnline, Boolean isPointFocal, String currentIp, int currentPort) {
    	this.id = id;
    	this.name = name;
    	this.login = login;
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

	public String toJSON() {
		return "{\"id\":\""+String.valueOf(this.getId())+"\""+
				",\"name\":\""+this.getName()+"\""+
				",\"login\":\""+this.getLogin()+"\""+
				",\"is_online\":\""+String.valueOf(this.getIsOnline())+"\""+
				",\"current_ip\":\""+this.getCurrentIp()+"\""+
				",\"current_port\":\""+String.valueOf(this.getCurrentPort())+"\"}";
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
	
	public String getCurrentIp() {
		return currentIp;
	}
	
	public void setCurrentIp(String currentIp) {
		this.currentIp = currentIp;
	}
    
    
   
    
    
	
}
