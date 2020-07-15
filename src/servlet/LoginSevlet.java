package servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import beans.BeanCursoJsp;
import dao.DaoLogin;

/**
 * Servlet implementation class LoginSevlet
 */
@WebServlet("/LoginSevlet")
public class LoginSevlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private DaoLogin daologin = new DaoLogin();
       
    public LoginSevlet() {
        super();
       
    }
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException
	{
		doPost(request, response);
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			BeanCursoJsp acesso = new BeanCursoJsp();
			
			String login = request.getParameter("login");
			String senha = request.getParameter("senha");
			
			if (daologin.validarLogin(login, senha)) {
				RequestDispatcher disp = request.getRequestDispatcher("acessopermitido.jsp");
				disp.forward(request, response);
			}else {
				RequestDispatcher disp = request.getRequestDispatcher("acessonegado.jsp");
				disp.forward(request, response);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

}
