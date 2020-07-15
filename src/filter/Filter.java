package filter;

import java.io.IOException;
import java.sql.Connection;

import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;

import conexao.SingleConnection;

/**
 * Servlet Filter implementation class Filter
 */
@WebFilter(urlPatterns = {"/*"})
public class Filter implements javax.servlet.Filter {

    private static Connection conn;
    

	public void destroy() {
	
	}


	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		try {
			chain.doFilter(request, response);
			conn.commit();
		} catch (Exception e) {
			
			e.printStackTrace();
			try {
				conn.rollback();
			} catch (Exception e2) {
				e.printStackTrace();
			}
		}
		
	}

	public void init(FilterConfig fConfig) throws ServletException {
		conn = SingleConnection.getConnetion();
	}

}
