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
		
		
		String param = request.getParameter("opcoesEmpresa");
		
		if (param != null) {
		int opcoesEmpresa = Integer.parseInt(param);
		
		Empresa emp = EmpresaDao.getInstance().getEmpresa(opcoesEmpresa);
		request.getSession().setAttribute("empresa", emp);
		}
		//todo 
		//recuperar lista de empresas do login JAAS
		
		if(!request.getSession().getAttribute("urlnavegation").toString().equals(request.getContextPath() + "/principal/carregaEmpresa.do"))
		{
			//request.getServletContext().getRequestDispatcher(request.getSession().getAttribute("urlnavegation").toString()).forward(request, response);
			response.sendRedirect(request.getSession().getAttribute("urlnavegation").toString());
			return null;
			
		}
		
		return mapping.findForward("sucess");
	
	}
}
