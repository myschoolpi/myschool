package app.services;

import java.sql.*;

public class BD {
	private final String BANCO = "myschoolpi";
	private final String URL = "jdbc:mysql://den1.mysql1.gear.host:3306/"+BANCO;
	private final String LOGIN = "myschoolpi";
	private final String SENHA = "Ne664J~BqI!f";
	
	public Connection con = null;
	public PreparedStatement st = null;
	public ResultSet rs = null;
	
	public static void main(String[] args) {
		BD bd = new BD();
		bd.getConnection();
		bd.close();
	}
	
	/**
	 * Irá se conectar com o banco de dados
	 * @return - true se a conexão deu certo, false se deu errado
	 */
	public boolean getConnection() {
		try {
			con = DriverManager.getConnection(URL, LOGIN, SENHA);
			System.out.println("Conectado");
			return true;
		} catch(SQLException e){
			System.out.println("Falha na conexão" + e.toString());
			return false;
		}
	}
	
	/**
	 * Irá fechar a conexão com o banco de dados
	 */
	public void close() {
		try { if(rs!=null) rs.close(); }
		catch(SQLException e) {}

		try { if(st!=null) st.close(); }
		catch(SQLException e) {}
		
		try { 
			if(con!=null) {
				con.close();
				System.out.println("Desconectado");
			}
		}
		catch(SQLException e) {
			
		}
	}
}
