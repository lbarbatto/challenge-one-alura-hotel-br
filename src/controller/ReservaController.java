package controller;

import java.sql.Connection;
import java.sql.Date;
import java.util.List;

import dao.ReservaDAO;
import factory.ConnectionFactory;
import model.Reserva;

public class ReservaController {
	
	private ReservaDAO reservaDAO;
	
	public ReservaController() {
		Connection connection = new ConnectionFactory().fazerConexao();
		this.reservaDAO = new ReservaDAO(connection);
	}
	
	public void salvar(Reserva reserva) {
		this.reservaDAO.salvar(reserva); 
	}
	
	public List<Reserva> listar() {
		return this.reservaDAO.listar();
	}
	
	public void buscarId(Integer id) {
		this.reservaDAO.buscarId(id);
	}
	
	public void editar(Date dataE, Date dataS, String valor, String formPagamento, Integer id) {
		this.reservaDAO.editar(dataE, dataS, valor, formPagamento, id);
	}
	
	public void deletar(Integer id) {
		this.reservaDAO.deletar(id);
	}

}
