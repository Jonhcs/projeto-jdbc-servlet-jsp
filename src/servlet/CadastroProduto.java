package servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import beans.BeanCadastroProduto;
import beans.BeanCursoJsp;
import dao.DaoProduto;

@WebServlet("/CadastroProduto")
public class CadastroProduto extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static DaoProduto daoProduto = new DaoProduto();

	public CadastroProduto() {
		super();

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {

			String acao = request.getParameter("acao");
			String produtoParam = request.getParameter("produto");

			if (acao.equalsIgnoreCase("delete")) {
				daoProduto.deletar(produtoParam);
				System.out.println("DELETADO");
				RequestDispatcher view = request.getRequestDispatcher("cadastroProduto.jsp");
				request.setAttribute("produtos", daoProduto.listarTodos());
				view.forward(request, response);
			} else if (acao.equals("editar")) {
				BeanCadastroProduto beanCadastroProduto = daoProduto.consultar(produtoParam);
				RequestDispatcher view = request.getRequestDispatcher("cadastroProduto.jsp");
				request.setAttribute("produto", beanCadastroProduto);
				view.forward(request, response);
			} else if (acao.equals("listarTodos")) {
				RequestDispatcher view = request.getRequestDispatcher("cadastroProduto.jsp");
				request.setAttribute("produtos", daoProduto.listarTodos());
				view.forward(request, response);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		BeanCadastroProduto produto = new BeanCadastroProduto();
		String acao = request.getParameter("acao");
		boolean podeInserir = true;

		if (acao != null && acao.equalsIgnoreCase("reset")) {
			try {
				RequestDispatcher view = request.getRequestDispatcher("cadastroProduto.jsp");
				request.setAttribute("produtos", daoProduto.listarTodos());
				view.forward(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}

		} else {
			String pid = request.getParameter("pid");
			String nome = request.getParameter("nome");
			String quantidade = request.getParameter("quantidade");
			String valor = request.getParameter("valor");

			produto.setPid(!pid.isEmpty() ? Long.parseLong(pid) : null);
			produto.setNome(nome);
			produto.setQuantidade(!quantidade.isEmpty() ? Long.parseLong(quantidade) : null);
			produto.setValor(!valor.isEmpty() ? Double.parseDouble(valor) : null);

			List<String> msg = new ArrayList<String>();
			try {
				if (nome == null || nome.isEmpty()) {
					String msgnome = "Campo obrigatório: Nome";
					msg.add(msgnome);
					podeInserir = false;
				} else if (quantidade == null || quantidade.isEmpty()) {
					String msgquantidade = "Campo obrigatório: Quantidade";
					msg.add(msgquantidade);
					podeInserir = false;
				} else if (valor == null || valor.isEmpty()) {
					String msgvalor = "Campo obrigatório: Valor";
					msg.add(msgvalor);
					podeInserir = false;
				} else if (pid == null || pid.isEmpty() && !daoProduto.validarProduto(nome)) {
					String msgLogin = "Nome Produto já existe.";
					msg.add(msgLogin);
					podeInserir = false;
				}

				if (msg != null && !podeInserir) {
					request.setAttribute("msg", msg);
					request.setAttribute("produto", produto);
				} else if (pid == null || pid.isEmpty() && daoProduto.validarProduto(nome)) {
					daoProduto.salvar(produto);
					System.out.println("Produto salvo");

				} else if (pid != null && !pid.isEmpty()) {
					if (!daoProduto.validarProduto(nome)) {
						String msgUpdate = "Nome do Produto já existe.";
						msg.add(msgUpdate);
						podeInserir = false;
					} else {
						daoProduto.atualizar(produto);
						System.out.println("Usuario Atualizado");
					}

				}


				RequestDispatcher view = request.getRequestDispatcher("cadastroProduto.jsp");
				request.setAttribute("produtos", daoProduto.listarTodos());
				view.forward(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}

		}

	}

}
