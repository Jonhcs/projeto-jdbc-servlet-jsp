package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import conexao.SingleConnection;

public class DaoLogin {
	private Connection conn;
	
	 public DaoLogin() {
		conn = SingleConnection.getConnetion();
	}
	 
	 public boolean validarLogin(String login, String senha) throws Exception {
		 String sql = "SELECT * FROM usuario where login = '" + login + "' and senha = '" + senha + "'";
		 PreparedStatement validar = conn.prepareStatement(sql);
		 ResultSet res = validar.executeQuery();
		 if (res.next()) {
			return true;
		}else {
			return false;
		}
	 }
	 
	
}
