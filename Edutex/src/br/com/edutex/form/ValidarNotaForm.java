/**
 * 
 */
package br.com.edutex.form;


import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import br.com.edutex.DAO.FinalidadeNfeDao;
import br.com.edutex.logic.FinalidadeNfe;

/**
 * Classe que mapeia a Action de Negocio
 * @author bruno
 *
 */
public class ValidarNotaForm extends Action {
	
	
	
	
	@Override 
	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
	
		List<FinalidadeNfe> listaNfe = FinalidadeNfeDao.getInstance().buscaFinalidadesNfe();
		
		request.getSession().setAttribute("finalidadeNota", listaNfe);
		
		return(mapping.findForward("sucesso"));
		
		
	}

}
