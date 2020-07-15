package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import beans.BeanCadastroProduto;
import beans.BeanCursoJsp;
import conexao.SingleConnection;

public class DaoProduto {
	private Connection conn;
	public DaoProduto() {
		conn = SingleConnection.getConnetion();
	}
	
	public void salvar(BeanCadastroProduto produto) {
		
		try {
			String sql = "insert into produtos(nome, valor, quantidade) values (?,?,?);";
			PreparedStatement salvar = conn.prepareStatement(sql);
			salvar.setString(1, produto.getNome());
			salvar.setDouble(2, produto.getValor());
			salvar.setLong(3, produto.getQuantidade());
			salvar.executeUpdate();
			conn.commit();
		} catch (SQLException e) {
			
			e.printStackTrace();
			try {
				conn.rollback();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}

	}
	
	public List<BeanCadastroProduto> listarTodos(){
		try {
			
			List<BeanCadastroProduto> listarProdutos = new ArrayList<BeanCadastroProduto>();
			String sql = "select * from produtos";
			PreparedStatement listar = conn.prepareStatement(sql);
			ResultSet res = listar.executeQuery();
			
			while (res.next()) {
				BeanCadastroProduto produto = new BeanCadastroProduto();
				produto.setPid(res.getLong("pid"));
				produto.setNome(res.getString("nome"));
				produto.setQuantidade(res.getLong("quantidade"));
				produto.setValor(res.getDouble("valor"));
				
				listarProdutos.add(produto);
			}
			
			return listarProdutos;
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			try {
				conn.rollback();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		
		return null;
	}
	
	public void deletar(String id) {
		try {
			String sql = "delete from produtos where pid = '" + id + "'";
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
	
	public BeanCadastroProduto consultar(String id) {
		BeanCadastroProduto produto = new BeanCadastroProduto();
		try {
			String sql = "select * from produtos where pid = '" + id + "'";
			PreparedStatement consultar = conn.prepareStatement(sql);
			ResultSet res = consultar.executeQuery();
			
			if (res.next()) {
				
				produto.setPid(res.getLong("pid"));
				produto.setNome(res.getString("nome"));
				produto.setValor(res.getDouble("valor"));
				produto.setQuantidade(res.getLong("quantidade"));
				return produto;
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			try {
				conn.rollback();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		
		return produto;
	}
	
	public void atualizar(BeanCadastroProduto produto) {
		try {
			String sql = "update produtos set nome = ?, quantidade = ?, valor = ? where pid =  " + produto.getPid(); ;
			PreparedStatement atualizar = conn.prepareStatement(sql);
			atualizar.setString(1, produto.getNome());
			atualizar.setLong(2, produto.getQuantidade());
			atualizar.setDouble(3, produto.getValor());
			atualizar.executeUpdate();
			conn.commit();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			try {
				conn.rollback();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
	}
	
	public boolean validarProduto(String nome) throws SQLException {

		String sql = "select count(1) as qtd from produtos where nome = '" + nome + "'";
		PreparedStatement validar = conn.prepareStatement(sql);
		ResultSet res = validar.executeQuery();

		if (res.next()) {

			return res.getInt("qtd") <= 0;
		}

		return false;

	}
	
}
