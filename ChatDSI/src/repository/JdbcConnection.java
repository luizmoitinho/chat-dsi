package repository;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class JdbcConnection {
	/**
	 * link util: https://www.youtube.com/watch?v=80tZQFicXn4 
	 */
	
	private Connection con = null;
	private String hostName = null;
	private String userName = null;
	private String password = null;
	private String url = null;
	private String jdbcDriver = null;
	private String dataBaseName = null;
	private String dataBasePrefix = null;
	private String dabaBasePort = null;
	
	public JdbcConnection() {
		super();
		// jdbc:'mysql:/localhost:3306/meu_bd'
		this.hostName = "localhost";
		this.userName = "root";
		this.password = "123456";
		this.jdbcDriver = "com.mysql.cj.jdbc.Driver";
		this.dataBaseName = "chat_dsi";
		this.dataBasePrefix = "jdbc:mysql://";
		this.dabaBasePort = "3306";
		this.url = dataBasePrefix + hostName + ":" + dabaBasePort + "/" + dataBaseName;
	}

	public Connection getConnection() {
		try {
			if (con == null) {
				Class.forName(jdbcDriver);
				con = DriverManager.getConnection(url, userName, password);
			} else if (con.isClosed()) {
				con = null;
				return getConnection();
			}
		} catch (ClassNotFoundException e) {

			// TODO: use um sistema de log apropriado.

			e.printStackTrace();
		} catch (SQLException e) {

			// TODO: use um sistema de log apropriado.

			e.printStackTrace();
		}
		return con;

	}

	public Connection createConnection() throws SQLException {
		Connection conexao = null;

		conexao = DriverManager.getConnection(this.url, this.userName, this.password);
		return conexao;
	}

	public void closeConnection() throws IOException, SQLException {
		if (con != null) {
			con.close();
		}
	}

}
