package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import model.Reserva;

public class ReservaDAO {
	
	private Connection connection;
	
	public ReservaDAO(Connection connection) {
		this.connection = connection; 
	}
	
	public void salvar(Reserva reserva) {
		try {
			String sql = "INSERT INTO reservas (data_entrada, data_saida, valor, forma_pagamento) VALUES(?, ?, ?, ?)";
			
			try (PreparedStatement pstm = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
				pstm.setDate(1, reserva.getDataEntrada());
				pstm.setDate(2, reserva.getDataSaida());
				pstm.setString(3, reserva.getValor());
				pstm.setString(4, reserva.getFormaPagamento());
				
				pstm.executeUpdate();
				
				try (ResultSet rst = pstm.getGeneratedKeys()){
					while (rst.next()) {
						reserva.setId(rst.getInt(1));
					}
				}
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	public List<Reserva> listar() {
		try {
			List<Reserva> reservas = new ArrayList<>();
			String sql = "SELECT * FROM reservas";

			try (PreparedStatement pstm = connection.prepareStatement(sql)) {
				pstm.execute();

				try (ResultSet rst = pstm.getResultSet()) {
					while (rst.next()) {
						Reserva reserva = new Reserva(rst.getInt(1), rst.getDate(2), rst.getDate(3), rst.getString(4), rst.getString(5));

						reservas.add(reserva);
					}
				}
			}
			return reservas;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}

	}
	
	public List<Reserva> buscarId(Integer id) {
		try {
			List<Reserva> reservas = new ArrayList<Reserva>();
			String sql = "SELECT * FROM reservas WHERE id = ?";

			try (PreparedStatement pstm = connection.prepareStatement(sql)) {
				pstm.setInt(1, id);
				pstm.execute();

				try (ResultSet rst = pstm.getResultSet()) {
					while (rst.next()) {
						Reserva reserva = new Reserva(rst.getInt(1), rst.getDate(2), rst.getDate(3), rst.getString(4), rst.getString(5));

						reservas.add(reserva);
					}
				}
			}
			return reservas;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	public void editar(Date dataE, Date dataS, String valor, String formPagamento, Integer id) {
		try (PreparedStatement stm = connection
				.prepareStatement("UPDATE reservas R SET R.data_entrada = ?, R.data_saida = ?, valor = ?, forma_pagamento = ? WHERE id = ?")) {
			stm.setDate(1, dataE);
            stm.setDate(2, dataS);
            stm.setString(3, valor);
            stm.setString(4, formPagamento);
            stm.setInt(5, id);
			stm.executeUpdate();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
    }
	
	public void deletar(Integer id) {
		try (PreparedStatement stm = connection.prepareStatement("DELETE FROM reservas WHERE ID = ?")) {
			stm.setInt(1, id);
			stm.executeUpdate();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
}
