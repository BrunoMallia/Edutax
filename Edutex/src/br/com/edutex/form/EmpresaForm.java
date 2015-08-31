/**
 * 
 */
package br.com.edutex.form;


import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import br.com.edutex.DAO.EmpresaDao;
import br.com.edutex.DAO.RegimeTributarioDao;
import br.com.edutex.logic.RegimeTributarioEmpresa;

/**
 * Classe que mapeia a Action de Negocio
 * @author bruno
 *
 */
public class EmpresaForm extends Action {
	
	
	
	
	@Override 
	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
	
		if (!request.isUserInRole("Administrador")) {
			System.out.println("você não possui permissão de acesso");
			response.setStatus(HttpServletResponse.SC_FORBIDDEN);
			
		}
		
		if(request.getAttribute("regimeTributario") == null ) {
			ArrayList<RegimeTributarioEmpresa> regTrib =  (ArrayList<RegimeTributarioEmpresa>) 
					RegimeTributarioDao.getInstance().getListaRegimeTributario();
			request.setAttribute("regimeTibutario", regTrib);
			
			
		}
		
		
		return(mapping.findForward("sucess"));
		
		
	}

}
