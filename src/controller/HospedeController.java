package controller;

import java.sql.Connection;
import java.sql.Date;
import java.util.List;

import dao.HospedeDAO;
import factory.ConnectionFactory;
import model.Hospede;

public class HospedeController {

    private HospedeDAO hospedeDAO;

    public HospedeController() {
        Connection connection = new ConnectionFactory().fazerConexao();
        this.hospedeDAO = new HospedeDAO(connection);
    }

    public void salvar(Hospede hospede) {
        this.hospedeDAO.salvar(hospede);
    }
    
    public List<Hospede> listar() {
    	return this.hospedeDAO.listar();
    }
    
    public void buscarSobrenome(String sobrenome) {
    	this.hospedeDAO.buscarSobrenome(sobrenome);
    }
    
    public void editar(String nome, String sobrenome, Date dataNascimento, String nacionalidade, String telefone) {
    	this.hospedeDAO.editar(nome, sobrenome, dataNascimento, nacionalidade, telefone);
    }
    
    public void deletar(Integer id) {
    	this.hospedeDAO.deletar(id);
    }
    
    public void deletarIdReserva(Integer idReserva) {
    	this.hospedeDAO.deletarIdReserva(idReserva);
    }
}
