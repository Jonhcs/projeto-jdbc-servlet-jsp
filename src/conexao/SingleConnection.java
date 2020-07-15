package conexao;

import java.sql.Connection;
import java.sql.DriverManager;

public class SingleConnection {
	
	private static String url = "jdbc:postgresql://localhost:5432/projetojsp";
	private static String password = "root";
	private static String user ="postgres";
	private static Connection conn = null;
	
	static {
		conexao();
	}
	
	public SingleConnection() {
		conexao();
	}
	
	private static void conexao() {
		try {
			if (conn == null ) {
				Class.forName("org.postgresql.Driver");
				conn = DriverManager.getConnection(url, user, password);
				conn.setAutoCommit(false);
			}
			System.out.println("Conectado");
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Deu ruim");
		}
	}
	
	public static Connection getConnetion() {
		try {
			return conn;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	
}
