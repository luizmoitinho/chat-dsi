package repository;

import models.UserModel;
import java.sql.*;
import java.util.ArrayList;

public class UserRepository {
	private JdbcConnection db;
	private ResultSet resultSet = null;

	public UserRepository() {
		this.db = new JdbcConnection();
	}

	public int authenticate(UserModel u) {
		try {
			String sql = "select id from tb_user where login=? and password=?";
			PreparedStatement stmt = this.db.getConnection().prepareStatement(sql);
			stmt.setString(1, u.getLogin());
			stmt.setString(2, u.getPassword());

			this.resultSet = stmt.executeQuery();
			if (!this.resultSet.next())
				return 0;

			int id = Integer.parseInt(this.resultSet.getString("id"));
			//this.db.getConnection().close();

			return id;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;
	}

	public boolean signinUser(UserModel u) {

		String query = "update tb_user set is_online=?, current_ip=?, current_port=? where id=?";

		try {
			System.out.println(u.getId());
			PreparedStatement stmt;
			stmt = this.db.getConnection().prepareStatement(query);
			stmt.setBoolean(1, u.getIsOnline());
			stmt.setString(2, u.getCurrentIp());
			stmt.setInt(3, u.getCurrentPort());
			stmt.setInt(4, u.getId());
			
			return stmt.executeUpdate() == 0? false:true;
			//this.db.getConnection().close();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	public boolean existLogin(UserModel u) {
		try {
			String sql = "select id from tb_user where login=?";
			PreparedStatement stmt = this.db.getConnection().prepareStatement(sql);
			stmt.setString(1, u.getLogin());

			this.resultSet = stmt.executeQuery();
			if (!this.resultSet.next())
				return false;
			this.db.getConnection().close();
			return true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}

	public UserModel insert(UserModel newUser) {
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
			stmt.setString(6, newUser.getCurrentIp());
			stmt.setInt(7, newUser.getCurrentPort());

			stmt.executeUpdate();
			this.resultSet = stmt.getGeneratedKeys();
			if (this.resultSet.next())
				newUser.setId(this.resultSet.getInt(1));

			this.db.getConnection().close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return newUser;
	}

	public UserModel updateOnline(UserModel updateUser) {
		return updateUser;
	}

	public UserModel getById(int userId) {
		UserModel u = new UserModel();
		try {		
			String sql = "select id, name, login, is_online, is_point_focal, current_ip, current_port from tb_user where id=?";
			PreparedStatement stmt;
			stmt = this.db.getConnection().prepareStatement(sql);
			stmt.setInt(1, userId);
			this.resultSet = stmt.executeQuery();
			this.resultSet.next();
			
			u = new UserModel(this.resultSet.getString("id"), this.resultSet.getString("name"),
					this.resultSet.getString("login"),
					this.resultSet.getString("is_online").equals("1") ? true : false,
					this.resultSet.getString("is_point_focal").equals("1") ? true : false,
					this.resultSet.getString("current_ip"),
					Integer.parseInt(this.resultSet.getString("current_port")));
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return u;
	}

	public ArrayList<UserModel> getAll(int userId) {
		ArrayList<UserModel> users = new ArrayList<UserModel>();
		try {
			String sql = "select id, name, login, is_online, is_point_focal, current_ip, current_port from tb_user where is_point_focal=0 and id!=? ";
			PreparedStatement stmt;
			stmt = this.db.getConnection().prepareStatement(sql);
			stmt.setInt(1, userId);
			this.resultSet = stmt.executeQuery();
			while (this.resultSet.next()) {
				UserModel u = new UserModel(Integer.parseInt(this.resultSet.getString("id")), this.resultSet.getString("name"),
						this.resultSet.getString("login"),
						this.resultSet.getString("is_online").equals("1") ? true : false,
						this.resultSet.getString("is_point_focal").equals("1") ? true : false,
						this.resultSet.getString("current_ip"),
						Integer.parseInt(this.resultSet.getString("current_port")));
				users.add(u);
			}
			this.db.getConnection().close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return users;
	}
	
	public ArrayList<UserModel> getAll2(int userId) {
		ArrayList<UserModel> users = new ArrayList<UserModel>();
		try {
			String sql = "select id, name, login, is_online, is_point_focal, current_ip, current_port from tb_user where is_point_focal=1 and id!=?";
			PreparedStatement stmt;
			stmt = this.db.getConnection().prepareStatement(sql);
			stmt.setInt(1, userId);
			this.resultSet = stmt.executeQuery();
			while (this.resultSet.next()) {
				UserModel u = new UserModel(Integer.parseInt(this.resultSet.getString("id")), this.resultSet.getString("name"),
						this.resultSet.getString("login"),
						this.resultSet.getString("is_online").equals("1") ? true : false,
						this.resultSet.getString("is_point_focal").equals("1") ? true : false,
						this.resultSet.getString("current_ip"),
						Integer.parseInt(this.resultSet.getString("current_port")));
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
