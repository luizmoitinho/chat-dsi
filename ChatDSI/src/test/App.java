package test;

import java.beans.Statement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import repository.JdbcConnection;


public class App {
	public static void main(String args[]) throws SQLException {
		Connection con = new JdbcConnection().getConnection();
		String sql = "create table teste( col1 varchar(255) not null );";		

        PreparedStatement stmt = con.prepareStatement(sql);

        if(stmt.execute())
        	System.out.println("teste realizado com sucesso, tabela teste criada no banco de dados");
        else
        	System.out.println("falha ao realizar o teste, tabela teste não foi criada");

        stmt.close();
        
	}
	
}
