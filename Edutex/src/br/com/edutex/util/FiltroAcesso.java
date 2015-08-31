/**
 * 
 */
package br.com.edutex.util;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import br.com.edutex.logic.Usuario;

/**
 * @author bruno
 *
 */
@WebFilter("/*")
public class FiltroAcesso implements Filter {

	/* (non-Javadoc)
	 * @see javax.servlet.Filter#destroy()
	 */
	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

	/* (non-Javadoc)
	 * @see javax.servlet.Filter#doFilter(javax.servlet.ServletRequest, javax.servlet.ServletResponse, javax.servlet.FilterChain)
	 */
	
	@Override
	public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws IOException,
			ServletException {
		
			Usuario user = null;	
		   HttpServletRequest request = (HttpServletRequest) req;
		  
		   HttpSession session = request.getSession();
		   String urlLogin;
		   
		   
	
		   
		   try {
		   	 urlLogin =  request.getRequestURI().substring(request.getRequestURI().lastIndexOf('/'), request.getRequestURI().length());
		   } catch (NullPointerException ponter) {
			   urlLogin = null;
		   }
	    
	/*	   if(session.getAttribute("user") == null && (!urlLogin.equals("/login.do") && !urlLogin.equals("/autentica.do"))) { 
		     user = (Usuario)session.getAttribute("user");
			   request.getServletContext().getRequestDispatcher("/login.do").forward(req, resp);
			   //request.getServletContext().getRequestDispatcher("/WEB-INF/sessao.jsp").forward(req, resp);
			   //req.getRequestDispatcher("action.login.do")
			   	//	.forward(req, resp);
			   return;
			  // response.sendRedirect("autentica");
			   		//return;
			   
		  } */
		    
		
		
		chain.doFilter(req, resp);
	}

	/* (non-Javadoc)
	 * @see javax.servlet.Filter#init(javax.servlet.FilterConfig)
	 */
	@Override
	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub
		
	}

}
