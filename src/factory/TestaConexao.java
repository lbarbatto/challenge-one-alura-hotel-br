package factory;

import java.sql.Connection;
import java.sql.SQLException;

public class TestaConexao {

	public static void main(String[] args) throws SQLException {
		// TODO Auto-generated method stub
		ConnectionFactory con = new ConnectionFactory();
		Connection conn = con.fazerConexao();
		
		System.out.println("FEchada conexão");
		
		conn.close();

	}

}
