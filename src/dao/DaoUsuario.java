package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import beans.BeanCursoJsp;
import conexao.SingleConnection;

public class DaoUsuario {
	private Connection conn;

	public DaoUsuario() {
		conn = SingleConnection.getConnetion();
	}

	public void salvar(BeanCursoJsp beancursojsp) {

		try {
			String sql = "insert into usuario(login, senha, nome, telefone, cep, rua, bairro, cidade, uf, ibge, fotoBase64, contentType, pdfBase64, contentTypePdf) values (?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
			PreparedStatement salvar = conn.prepareStatement(sql);
			salvar.setString(1, beancursojsp.getLogin());
			salvar.setString(2, beancursojsp.getPassword());
			salvar.setString(3, beancursojsp.getNome());
			salvar.setLong(4, beancursojsp.getTelefone());
			salvar.setString(5, beancursojsp.getCep());
			salvar.setString(6, beancursojsp.getRua());
			salvar.setString(7, beancursojsp.getBairro());
			salvar.setString(8, beancursojsp.getCidade());
			salvar.setString(9, beancursojsp.getUf());
			salvar.setString(10, beancursojsp.getIbge());
			salvar.setString(11, beancursojsp.getFotoBase64());
			salvar.setString(12, beancursojsp.getContentType());
			salvar.setString(13, beancursojsp.getPdfBase64());
			salvar.setString(14, beancursojsp.getContentTypePdf());
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

	public List<BeanCursoJsp> listarTodos() {
		List<BeanCursoJsp> usuarios = new ArrayList<BeanCursoJsp>();

		try {
			String sql = "select * from usuario";
			PreparedStatement listar = conn.prepareStatement(sql);
			ResultSet res = listar.executeQuery();

			while (res.next()) {
				BeanCursoJsp usuario = new BeanCursoJsp();
				usuario.setId(res.getLong("id"));
				usuario.setLogin(res.getString("login"));
				usuario.setPassword(res.getString("senha"));
				usuario.setNome(res.getString("nome"));
				usuario.setTelefone(res.getLong("telefone"));
				usuario.setCep(res.getString("cep"));
				usuario.setRua(res.getString("rua"));
				usuario.setBairro(res.getString("bairro"));
				usuario.setCidade(res.getString("cidade"));
				usuario.setUf(res.getString("uf"));
				usuario.setIbge(res.getString("ibge"));
				usuario.setFotoBase64(res.getString("fotoBase64"));
				usuario.setContentType(res.getString("contentType"));
				usuario.setPdfBase64(res.getString("pdfBase64"));
				usuario.setContentTypePdf(res.getString("contentTypePdf"));
				usuarios.add(usuario);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return usuarios;

	}

	public void deletar(String id) {
		try {
			String sql = "delete from usuario where id = '" + id + "'";
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

	public BeanCursoJsp consultar(String id) {

		try {
			String sql = "select * from usuario where id = '" + id + "'";
			PreparedStatement consulta = conn.prepareStatement(sql);
			ResultSet res = consulta.executeQuery();

			if (res.next()) {
				BeanCursoJsp usuario = new BeanCursoJsp();
				usuario.setId(res.getLong("id"));
				usuario.setLogin(res.getString("login"));
				usuario.setPassword(res.getString("senha"));
				usuario.setNome(res.getString("nome"));
				usuario.setTelefone(res.getLong("telefone"));
				usuario.setCep(res.getString("cep"));
				usuario.setRua(res.getString("rua"));
				usuario.setBairro(res.getString("bairro"));
				usuario.setCidade(res.getString("cidade"));
				usuario.setUf(res.getString("uf"));
				usuario.setIbge(res.getString("ibge"));
				usuario.setFotoBase64(res.getString("fotoBase64"));
				usuario.setContentType(res.getString("contentType"));
				usuario.setPdfBase64(res.getString("pdfBase64"));
				usuario.setContentTypePdf(res.getString("contentTypePdf"));
				return usuario;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;

	}

	public void atualizar(BeanCursoJsp usuario) {

		try {
			String sql = "update usuario set login = ?, senha = ?, nome = ?, telefone = ?,cep = ?,rua = ?,bairro = ?, cidade = ?, uf = ?, ibge = ?,  fotoBase64 = ?, contentType = ?, pdfBase64 = ? , contentTypePdf = ? where id =  " + usuario.getId();
			PreparedStatement atualizar = conn.prepareStatement(sql);
			atualizar.setString(1, usuario.getLogin());
			atualizar.setString(2, usuario.getPassword());
			atualizar.setString(3, usuario.getNome());
			atualizar.setLong(4, usuario.getTelefone());
			atualizar.setString(5, usuario.getCep());
			atualizar.setString(6, usuario.getRua());
			atualizar.setString(7, usuario.getBairro());
			atualizar.setString(8, usuario.getCidade());
			atualizar.setString(9, usuario.getUf());
			atualizar.setString(10, usuario.getIbge());
			atualizar.setString(11, usuario.getFotoBase64());
			atualizar.setString(12, usuario.getContentType());
			atualizar.setString(13, usuario.getPdfBase64());
			atualizar.setString(14, usuario.getContentTypePdf());
			atualizar.executeUpdate();
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

	public boolean validarLogin(String login) throws SQLException {

		String sql = "select count(1) as qtd from usuario where login = '" + login + "'";
		PreparedStatement validar = conn.prepareStatement(sql);
		ResultSet res = validar.executeQuery();

		if (res.next()) {

			return res.getInt("qtd") <= 0;
		}

		return false;

	}
	
	public boolean validarLoginUpdate(String login, String id) throws SQLException {

		String sql = "select count(1) as qtd from usuario where login = '" + login + "' and id <> " + id;
		PreparedStatement validar = conn.prepareStatement(sql);
		ResultSet res = validar.executeQuery();

		if (res.next()) {

			return res.getInt("qtd") <= 0;
		}

		return false;

	}
	public boolean validarSenha(String Senha) throws SQLException {

		String sql = "select count(1) as qtd from usuario where senha = '" + Senha + "'";
		PreparedStatement validar = conn.prepareStatement(sql);
		ResultSet res = validar.executeQuery();

		if (res.next()) {

			return res.getInt("qtd") <= 0;
		}

		return false;

	}

}
