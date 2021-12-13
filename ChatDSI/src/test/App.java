package test;

import java.beans.Statement;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import repository.JdbcConnection;


public class App {
	public static void main(String args[]) throws SQLException, NoSuchAlgorithmException {
	   String s="Texto de Exemplo";
	   MessageDigest m=MessageDigest.getInstance("MD5");
       m.update(s.getBytes(),0,s.length());
       System.out.println("MD5: "+new BigInteger(1,m.digest()).toString(16).getClass());
       
//		Connection con = new JdbcConnection().getConnection();
//		String sql = "create table teste( col1 varchar(255) not null );";		
//
//        PreparedStatement stmt = con.prepareStatement(sql);
//
//        if(stmt.execute())
//        	System.out.println("teste realizado com sucesso, tabela teste criada no banco de dados");
//        else
//        	System.out.println("falha ao realizar o teste, tabela teste não foi criada");
//
//        stmt.close();
        
	}
	
}
