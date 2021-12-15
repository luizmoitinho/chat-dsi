package repository;


import java.sql.*;
import java.util.ArrayList;

import models.MessageModel;

public class MessageRepository {
	private JdbcConnection db;
	private ResultSet resultSet = null;

	public MessageRepository() {
		this.db = new JdbcConnection();
	}

	public MessageModel insert(MessageModel newMessage) {
		String query = "insert into tb_message (from_user, to_user, content)"
				+ " values (?, ?, ?)";

		try {
			PreparedStatement stmt;
			stmt = this.db.getConnection().prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
			stmt.setInt(1, newMessage.getFrom_user());
			stmt.setInt(2, newMessage.getTo_user());
			stmt.setString(3, newMessage.getContent());


			stmt.executeUpdate();
			this.resultSet = stmt.getGeneratedKeys();
			if (this.resultSet.next())
				newMessage.setId(this.resultSet.getInt(1));

			this.db.getConnection().close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return newMessage;
	}


	public ArrayList<MessageModel> getAll(int userId) {
		ArrayList<MessageModel> messages = new ArrayList<MessageModel>();
		try {
			String sql = "select id, from_user, to_user, created_at from tb_messages" + 
							"where from_user = ? OR to_user = ?;";
			PreparedStatement stmt;
			stmt = this.db.getConnection().prepareStatement(sql);
			stmt.setInt(1, userId);
			stmt.setInt(2, userId);
			
			this.resultSet = stmt.executeQuery();
			while (this.resultSet.next()) {
				MessageModel m = new MessageModel(Integer.parseInt(this.resultSet.getString("id")), 
												  Integer.parseInt(this.resultSet.getString("from_user")),
												  Integer.parseInt(this.resultSet.getString("to_user")),
												  this.resultSet.getString("content"),
												 this.resultSet.getString("created_at"));
				messages.add(m);
			}
			this.db.getConnection().close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return messages;
	}

}
