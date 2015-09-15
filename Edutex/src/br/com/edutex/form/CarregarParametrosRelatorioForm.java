package br.com.edutex.form;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import br.com.edutex.DAO.EmpresaDao;
import br.com.edutex.logic.Empresa;

public class CarregarParametrosRelatorioForm extends Action {

	
	@Override 
	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		List<Empresa> empresasList = EmpresaDao.getInstance().buscaListaEmpresa();
		
		request.setAttribute("empresas", empresasList);
		
		return mapping.findForward("sucess");
	}
}
