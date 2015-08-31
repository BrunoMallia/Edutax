package br.com.edutex.form;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.security.auth.login.LoginException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import br.com.edutax.security.principal.User;
import br.com.edutax.security.principal.Role;
import br.com.edutex.DAO.UsuarioDao;
import br.com.edutex.logic.Empresa;
import br.com.edutex.logic.Usuario;
import br.com.edutex.util.LogUtil;
import br.com.edutex.util.MensagemProp;


public class LoginEmpresaAut extends Action {
	
	  
	
	@Override 
	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		

		User usuario = null;
		 
	
		
		//request.login(user, password);
		usuario = (User)request.getUserPrincipal();
		
				HttpSession session = request.getSession();
				 
				Usuario usuarioSessao = UsuarioDao.getInstance().getUsuario(usuario.getId());
				
				if (usuarioSessao != null)
					session.setAttribute("user", usuarioSessao);
				
				
				List<Empresa> empresasLista = new ArrayList<Empresa>();
				for (int i = 0 ; i < usuarioSessao.getUsuarioEmpresas().size(); i++) {
					     empresasLista.add(usuarioSessao.getUsuarioEmpresas().get(i).getEmpresa());
				}
				
				 
				if (empresasLista.isEmpty()) {
					request.setAttribute("falha", MensagemProp.getPropriedades().getProperty("aut.login.erro.empresa").toString());
					return(mapping.findForward("failed"));
				} 
				
				request.setAttribute("listaEmpresas", empresasLista);
			
		 return (mapping.findForward("sucess"));	
		
	}

	

}
