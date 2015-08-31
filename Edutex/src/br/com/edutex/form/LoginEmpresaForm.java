package br.com.edutex.form;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import br.com.edutex.DAO.EmpresaDao;
import br.com.edutex.logic.Empresa;

public class LoginEmpresaForm extends Action {
	
	@Override 
	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		int opcoesEmpresa = Integer.parseInt(request.getParameter("opcoesEmpresa"));
		
		Empresa emp = EmpresaDao.getInstance().getEmpresa(opcoesEmpresa);
		request.getSession().setAttribute("empresa", emp);
		
		//todo 
		//recuperar lista de empresas do login JAAS
		
		return mapping.findForward("sucess");
	
	}
}
