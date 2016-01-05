/**
 * 
 */
package br.com.edutex.util;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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

import br.com.edutax.security.principal.User;
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
		
		   HttpServletRequest request = (HttpServletRequest) req;
		   HttpSession session = request.getSession();
		   String urlLogin;
		   
		   
	
		   
		   try {
		   	 urlLogin =  request.getRequestURI().substring(request.getRequestURI().lastIndexOf('/'), request.getRequestURI().length());
		   } catch (NullPointerException ponter) {
			   urlLogin = null;
		   }
		   
		   
		   Pattern pat = Pattern.compile("^.+\\.(js|css|jpg|gif|png)$");
		   Matcher mat = pat.matcher(urlLogin);
		   
		   if (mat.matches()) {
			   chain.doFilter(req, resp);
		   }
		   
		  User usuario = (User)request.getUserPrincipal();
		  if (!request.getRequestURI().toString().equals(request.getContextPath() + "/principal/home.do")) {
			  session.setAttribute("urlnavegation", request.getRequestURI());
		  }		  
		  
		  if (usuario == null) {
			  chain.doFilter(req, resp);
			  return;
		  }
		  
		  
	    

		  if (session.getAttribute("empresa") == null && !urlLogin.equals("/home.do") && !urlLogin.equals("/carregaEmpresa.do")) {
			  request.getServletContext().getRequestDispatcher("/principal/carregaEmpresa.do").forward(req, resp);
			   return;
		   }
		   

		
		
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
