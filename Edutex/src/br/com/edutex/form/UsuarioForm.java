package br.com.edutex.form;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import br.com.edutex.DAO.EmpresaDao;
import br.com.edutex.DAO.TipoUsuarioDao;
import br.com.edutex.logic.Empresa;
import br.com.edutex.logic.TipoUsuario;
import br.com.edutex.logic.Usuario;
import br.com.edutex.DAO.UsuarioDao;

/**
 * validacao:
 * 2 = Email de acordo com o padrao
 * 3 = Email fora do padrao
 * 4 = Email desativado
 */


public class UsuarioForm extends Action {
	
	@Override 
	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		TipoUsuarioDao tpUsuario = TipoUsuarioDao.getInstance();
		ArrayList<TipoUsuario> tiposUsuario  = (ArrayList<TipoUsuario>) tpUsuario.buscaListaTipoUsuario();
		
		request.setAttribute("listaTpUsuario", tiposUsuario);
		 ArrayList<Empresa> empresas =   (ArrayList<Empresa>) EmpresaDao.getInstance().buscaListaEmpresa();
		 request.setAttribute("listaEmpresa", empresas);
		
		
		return(mapping.findForward("success"));
	}
	
	/**
	 * Logica para adicao de usuario
	 */
	public int add(HttpServletRequest request)
	{
		return 1;
	}
	
	/**
	 * Logica para habilitar e desabilitar usuario
	 */
	
	
	public int enableDisable(HttpServletRequest request)
	{
		if(request.getParameter("EmailEnableDisable") !=null && !request.getParameter("EmailEnableDisable").toString().isEmpty() && request.getParameter("EmailEnableDisable").toString().contains("@"))
		{
			if(request.getParameter("EmailEnableDisable").toString().substring(request.getParameter("EmailEnableDisable").toString().indexOf("@")).contains("."))
			{
				String email=request.getParameter("EmailEnableDisable").toString();
				ArrayList<Usuario> users;
				users=(ArrayList<Usuario>) UsuarioDao.getInstance().buscaUsuariosFull(email);
				if((users!=null)&&(!users.isEmpty()))
				{
					if(users.get(0).getTpStatus().getCdStatus()==1)
					{
						return 5; //Verificar 
					}
					else
					{
						return 6; // Verificar
					}
				}
			}
		}
		return 3;
	}
	
	
	

	/**
	 * Busca lista de Tipos de Usuario
	 */
	public static List<TipoUsuario> buscaTipoUsuario () {
		TipoUsuarioDao tpUsuario = TipoUsuarioDao.getInstance();
		ArrayList<TipoUsuario> tiposUsuario  = (ArrayList<TipoUsuario>) tpUsuario.buscaListaTipoUsuario();
		
		for (int i = 0; tiposUsuario != null && i < tiposUsuario.size(); i++) {
			if (tiposUsuario.get(i).getNmTipoUsuario()== null ||tiposUsuario.get(i).getNmTipoUsuario().isEmpty()) {
				tiposUsuario.remove(i);
				i--;
			}
		}
		
		return tiposUsuario;
	}
	
	
	/**
	 * Busca lista de empresas ativas
	 */
	public List<Empresa> buscaListaEmpresa () {
		 
		//codigo de status precisa ser 1(ativo)
		ArrayList<Empresa> empresas = (ArrayList<Empresa>) EmpresaDao.getInstance().buscaListaEmpresa();
		
		for (int i = 0; i < empresas.size(); i++) {
			if (empresas.get(i).getNmEmpresa() == null || empresas.get(i).getNmEmpresa().isEmpty()
				|| empresas.get(i).getTpStatus().getNmStatus() == null || empresas.get(i).getTpStatus().getNmStatus().isEmpty()
				|| empresas.get(i).getTpStatus().getCdStatus() == 2){
				empresas.remove(i);
				i--;
			}
		}
		return empresas;
	}
}
