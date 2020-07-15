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

import beans.BeanCadastroTelefone;
import beans.BeanCursoJsp;
import dao.DaoTelefone;
import dao.DaoUsuario;

/**
 * Servlet implementation class CadastroTelefones
 */
@WebServlet("/CadastroTelefones")
public class CadastroTelefones extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private DaoUsuario daoUsuario = new DaoUsuario();
    private DaoTelefone daoTelefone = new DaoTelefone();

    public CadastroTelefones() {
        super();
        
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		try {
			String acao = request.getParameter("acao");
			List<String> msg = new ArrayList<String>();
			boolean podeInserir;
			
			if(acao.equalsIgnoreCase("addfones")) {
				String idUser = request.getParameter("user");
				BeanCursoJsp usuario = daoUsuario.consultar(idUser);
				request.getSession().setAttribute("userEscolhido", usuario);
				request.setAttribute("userEscolhido", usuario);

				RequestDispatcher view = request.getRequestDispatcher("telefones.jsp");
				request.setAttribute("telefones", daoTelefone.listarTodos(usuario.getId()));
				view.forward(request, response);
				
			}else if(acao.equalsIgnoreCase("delete")) {
				BeanCursoJsp beanCursoJsp = (BeanCursoJsp) request.getSession().getAttribute("userEscolhido");
				String fone = request.getParameter("fone");
				daoTelefone.deletar(fone);
				String msgDelete = "Telefone deletado com sucesso!";
				msg.add(msgDelete);
				podeInserir = true;
				
				RequestDispatcher view = request.getRequestDispatcher("telefones.jsp");
				request.setAttribute("telefones", daoTelefone.listarTodos(beanCursoJsp.getId()));
				request.setAttribute("msg", msg);
				view.forward(request, response);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}	
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		try {
			BeanCursoJsp beanCursoJsp = (BeanCursoJsp) request.getSession().getAttribute("userEscolhido");
			
			
			String numero = request.getParameter("numero");
			String tipo = request.getParameter("tipo");
			
			BeanCadastroTelefone telefone = new BeanCadastroTelefone();
			telefone.setNumero(numero);
			telefone.setTipo(tipo);
			telefone.setUser(beanCursoJsp.getId());
			
			daoTelefone.salvar(telefone);
			
			request.getSession().setAttribute("userEscolhido", beanCursoJsp);
			request.setAttribute("userEscolhido", beanCursoJsp);
			
			RequestDispatcher view = request.getRequestDispatcher("telefones.jsp");
			request.setAttribute("telefones", daoTelefone.listarTodos(beanCursoJsp.getId()));
			view.forward(request, response);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
	}

}
