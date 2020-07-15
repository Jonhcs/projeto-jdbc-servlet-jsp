package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import beans.BeanCadastroTelefone;
import beans.BeanCursoJsp;
import conexao.SingleConnection;

public class DaoTelefone {
	private Connection conn;

	public DaoTelefone() {
		conn = SingleConnection.getConnetion();
	}

	public void salvar(BeanCadastroTelefone telefone) {

		try {
			String sql = "insert into telefones(numero, tipo, usuario) values (?,?,?)";
			PreparedStatement salvar = conn.prepareStatement(sql);
			salvar.setString(1, telefone.getNumero());
			salvar.setString(2, telefone.getTipo());
			salvar.setLong(3, telefone.getUser());
			salvar.execute();
			conn.commit();
		} catch (Exception e) {
			e.printStackTrace();
			try {
				conn.rollback();
				System.out.println("Foi para o rollback");
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}

	}

	public List<BeanCadastroTelefone> listarTodos(Long user) {
		List<BeanCadastroTelefone> telefones = new ArrayList<BeanCadastroTelefone>();

		try {
			String sql = "select * from telefones where usuario = " + user;
			PreparedStatement listar = conn.prepareStatement(sql);
			ResultSet res = listar.executeQuery();

			while (res.next()) {
				BeanCadastroTelefone telefone = new BeanCadastroTelefone();
				telefone.setId(res.getLong("id"));
				telefone.setNumero(res.getString("numero"));
				telefone.setTipo(res.getString("tipo"));
				telefone.setUser(res.getLong("usuario"));
				
				telefones.add(telefone);
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return telefones;

	}

	public void deletar(String id) {
		try {
			String sql = "delete from telefones where id = '" + id + "'";
			PreparedStatement deletar = conn.prepareStatement(sql);
			deletar.executeUpdate();
			conn.commit();

		} catch (Exception e) {
			e.printStackTrace();
			try {
				conn.rollback();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
	}

	
}
