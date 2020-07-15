package servlet;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.tomcat.util.codec.binary.Base64;

import beans.BeanCursoJsp;
import dao.DaoLogin;
import dao.DaoUsuario;

/**
 * Servlet implementation class CadastroUsuario
 */
@WebServlet("/CadastroUsuario")
@MultipartConfig
public class CadastroUsuario extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private DaoUsuario daoUsuario = new DaoUsuario();

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public CadastroUsuario() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {

			String acao = request.getParameter("acao");
			String user = request.getParameter("user");
			String tipo = request.getParameter("tipo");

			if (acao == null) {
				RequestDispatcher view = request.getRequestDispatcher("cadastroUsuario.jsp");
				request.setAttribute("usuarios", daoUsuario.listarTodos());
				view.forward(request, response);
			} else if (acao.equals("delete")) {
				daoUsuario.deletar(user);
				System.out.println("Ação delete ok!");
				RequestDispatcher view = request.getRequestDispatcher("cadastroUsuario.jsp");
				request.setAttribute("usuarios", daoUsuario.listarTodos());
				view.forward(request, response);
			} else if (acao.equals("editar")) {
				BeanCursoJsp beancursojsp = daoUsuario.consultar(user);
				RequestDispatcher view = request.getRequestDispatcher("cadastroUsuario.jsp");
				request.setAttribute("user", beancursojsp);
				view.forward(request, response);
			} else if (acao.equals("listarTodos")) {
				RequestDispatcher view = request.getRequestDispatcher("cadastroUsuario.jsp");
				request.setAttribute("usuarios", daoUsuario.listarTodos());
				view.forward(request, response);
			} else if (acao.equals("download")) {

				BeanCursoJsp usuario = daoUsuario.consultar(user);

				if (usuario != null) {

					String contentType = "";
					byte[] fileBytes = null;

					if (tipo.equals("imagem")) {
						contentType = usuario.getContentType();
						fileBytes = new Base64().decodeBase64(usuario.getFotoBase64());
						response.setHeader("Content-Disposition",
								"attachment; filename = arquivo." + contentType.split("\\/")[1]);
					}
					if (tipo.equals("pdf")) {
						contentType = usuario.getContentTypePdf();
						fileBytes = new Base64().decodeBase64(usuario.getPdfBase64());
						response.setHeader("Content-Disposition",
								"attachment; filename = pdf." + contentType.split("\\/")[1]);
					}

					InputStream is = new ByteArrayInputStream(fileBytes);

					int read = 0;

					byte[] bytes = new byte[1024];
					OutputStream os = response.getOutputStream();
					while ((read = is.read(bytes)) != -1) {
						os.write(bytes, 0, read);
					}

					os.flush();
					os.close();

				}

				RequestDispatcher view = request.getRequestDispatcher("cadastroUsuario.jsp");
				request.setAttribute("usuarios", daoUsuario.listarTodos());
				view.forward(request, response);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		BeanCursoJsp usuario = new BeanCursoJsp();
		String acao = request.getParameter("acao");
		boolean podeInserir = true;

		if (acao != null && acao.equalsIgnoreCase("resetar")) {
			try {
				RequestDispatcher view = request.getRequestDispatcher("cadastroUsuario.jsp");
				request.setAttribute("usuarios", daoUsuario.listarTodos());
				view.forward(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}

		} else {
			String id = request.getParameter("id");
			String nome = request.getParameter("nome");
			String login = request.getParameter("login");
			String senha = request.getParameter("senha");
			String telefoneString = request.getParameter("telefone");
			String cep = request.getParameter("cep");
			String rua = request.getParameter("rua");
			String bairro = request.getParameter("bairro");
			String cidade = request.getParameter("cidade");
			String uf = request.getParameter("uf");
			String ibge = request.getParameter("ibge");

			usuario.setId(!id.isEmpty() ? Long.parseLong(id) : null);
			usuario.setLogin(login);
			usuario.setPassword(senha);
			usuario.setNome(nome);
			usuario.setTelefone(!telefoneString.isEmpty() ? Long.parseLong(telefoneString) : null);
			usuario.setCep(cep);
			usuario.setRua(rua);
			usuario.setBairro(bairro);
			usuario.setCidade(cidade);
			usuario.setUf(uf);
			usuario.setIbge(ibge);

			List<String> msg = new ArrayList<String>();
			try {

				if (ServletFileUpload.isMultipartContent(request)) {
					Part imagemFoto = request.getPart("foto");

					if (imagemFoto != null) {
						String fotoBase64 = new Base64()
								.encodeBase64String(converteStremParaByte(imagemFoto.getInputStream()));
						usuario.setFotoBase64(fotoBase64);
						usuario.setContentType(imagemFoto.getContentType());
					}

					/**
					 * Processamento de pdf
					 */
					Part pdf = request.getPart("pdf");
					if (pdf != null) {
						String pdf64 = new Base64().encodeBase64String(converteStremParaByte(pdf.getInputStream()));
						usuario.setPdfBase64(pdf64);
						usuario.setContentTypePdf(pdf.getContentType());
					}

				}

				if (login == null || login.isEmpty()) {
					String msgLogin = "Campo obrigatório: LOGIN";
					msg.add(msgLogin);
					podeInserir = false;
				} else if (senha == null || senha.isEmpty()) {
					String msgSenha = "Campo obrigatório: SENHA";
					msg.add(msgSenha);
					podeInserir = false;
				} else if (telefoneString == null || telefoneString.isEmpty()) {
					String msgTelefone = "Campo obrigatório: TELEFONE";
					msg.add(msgTelefone);
					podeInserir = false;
				} else if (nome == null || nome.isEmpty()) {
					String msgNome = "Campo obrigatório: NOME";
					msg.add(msgNome);
					podeInserir = false;
				} else if (id == null || id.isEmpty() && !daoUsuario.validarLogin(login)) {
					String msgLogin = " Usuário já existe com o mesmo login. Cadastre outro login.";
					msg.add(msgLogin);
					podeInserir = false;
				} else if (id == null || id.isEmpty() && !daoUsuario.validarSenha(senha)) {
					String msgSenha = " Senha já existe para outro usuário.";
					msg.add(msgSenha);
					podeInserir = false;
				}

				if (msg != null && !podeInserir) {
					request.setAttribute("msg", msg);
					request.setAttribute("user", usuario);
				} else if (id == null || id.isEmpty() && daoUsuario.validarLogin(login) && podeInserir) {
					daoUsuario.salvar(usuario);
					String msgSalvo = "Salvo com sucesso";
					msg.add(msgSalvo);
					podeInserir = true;
					System.out.println("Usuario salvo");
				} else if (id != null && !id.isEmpty()) {
					if (!daoUsuario.validarLoginUpdate(login, id)) {
						String msgUpdate = " Login já existe em outra conta de usuário. Atualize outro login.";
						msg.add(msgUpdate);
						podeInserir = false;
					} else {
						daoUsuario.atualizar(usuario);
						System.out.println("Usuario Atualizado");
						String msgAtualizado = "Atualizado com sucesso";
						msg.add(msgAtualizado);
						podeInserir = true;
					}

				}

				if (msg != null && podeInserir) {
					request.setAttribute("msg", msg);
				}

				RequestDispatcher view = request.getRequestDispatcher("cadastroUsuario.jsp");
				request.setAttribute("usuarios", daoUsuario.listarTodos());
				request.setAttribute("podeInserir", podeInserir);
				view.forward(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}

		}

	}

	/**
	 * Converte para um array de Bytes
	 * 
	 * @param imagem
	 * @return
	 * @throws IOException
	 */
	private byte[] converteStremParaByte(InputStream imagem) throws IOException {
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		int reads = imagem.read();
		while (reads != -1) {
			baos.write(reads);
			reads = imagem.read();
		}

		return baos.toByteArray();
	}
}
