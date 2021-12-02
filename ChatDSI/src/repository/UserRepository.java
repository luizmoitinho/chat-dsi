package repository;


import models.User;
import java.sql.*;
import java.util.ArrayList;

public class UserRepository {
	private JdbcConnection db;
	private ResultSet resultSet = null;
	
	public UserRepository() {
		this.db = new JdbcConnection();
	}
	
	public User insert(User newUser) {
		String query = "insert into tb_user (name, login, password, is_online, is_point_focal, current_ip, current_port)"
			        	+ " values (?, ?, ?, ?, ?, ?, ?)";
		
		try {
			PreparedStatement stmt;
			stmt = this.db.getConnection().prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
			stmt.setString(1, newUser.getName());
			stmt.setString(2, newUser.getLogin());
			stmt.setString(3, newUser.getPassword());
			stmt.setBoolean(4, newUser.getIsOnline());
			stmt.setBoolean(5, newUser.getIsPointFocal());
			stmt.setString(6, newUser.getCurentIp());
			stmt.setInt(7, newUser.getCurrentPort());
			
			stmt.executeUpdate();
			this.resultSet = stmt.getGeneratedKeys();
			if(this.resultSet.next())
				newUser.setId(this.resultSet.getInt(1));
			
			this.db.getConnection().close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return newUser;
	}
	
	public User updateOnline(User updateUser) {
		return updateUser;
	}
	
	public User getById(int id) {
		return new User("teste", "teste", "123", false, false, "",1);
	}
	
	public ArrayList<User> getAll(){
		ArrayList<User> users = new ArrayList<User>();
		try {
			String sql = "select id, name, login, is_online, is_point_focal, current_ip, current_port from tb_user";		
			PreparedStatement stmt;
			stmt = this.db.getConnection().prepareStatement(sql);
			this.resultSet = stmt.executeQuery();
			while(this.resultSet.next()) {
				User u = new User(
							this.resultSet.getString("id"),
							this.resultSet.getString("name"),
							this.resultSet.getString("login"),
							this.resultSet.getString("is_online").equals("1")? true : false ,
							this.resultSet.getString("is_point_focal").equals("1")? true : false,
							this.resultSet.getString("current_ip"),
							Integer.parseInt(this.resultSet.getString("current_port"))
						);
				users.add(u);
			}
			this.db.getConnection().close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return users;
	}
	
}
