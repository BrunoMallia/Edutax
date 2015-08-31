package br.com.edutex.form;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.json.simple.JSONObject;


import br.com.edutex.DAO.EmpresaDao;
import br.com.edutex.DAO.TipoStatusDao;
import br.com.edutex.DAO.TipoUsuarioDao;
import br.com.edutex.logic.Empresa;
import br.com.edutex.logic.TipoStatus;
import br.com.edutex.logic.TipoUsuario;
import br.com.edutex.logic.Usuario;
import br.com.edutex.logic.UsuarioEmpresa;
import br.com.edutex.logic.UsuarioEmpresaPK;
import br.com.edutex.util.LogUtil;
import br.com.edutex.util.MensagemProp;
import br.com.edutex.DAO.UsuarioDao;



/**
 * Métodos Ajax para adicionar, alterar e habilitar/desabilitar
 * usuário
 * @author TCC
 *
 */
public class UsuarioFormAjax extends Action {
	
	
	
	@Override 
	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String param = "", resp= "";
		
		
		 //ESSE PARÂMETRO E ENVIADO PELO MÉTODO AJAX DE REQUISIÇÃO
		 param = request.getParameter("acao");
		 
		 switch (param) {
			case "inserir":
				response.setContentType("text/text;charset=utf-8");
				resp = inserirUsuario(request, response);
				
				break;
			case "pesquisar":
				response.addHeader("Content-Type", "application/json;charset=utf-8");
				resp = pesquisarUsuario(request, response); 
				break;
			case "atualizar":
				response.setContentType("text/text;charset=utf-8");
				resp = atualizarUsuario(request, response);
				break;
			case "pesquisarStatus":
				response.addHeader("Content-Type", "application/json;charset=utf-8");
				resp = pesquisarStatus(request, response);
				break;
			case "atualizarStatus":
				response.addHeader("Content-Type", "application/json;charset=utf-8");
				 JSONObject obj= new JSONObject();
				obj = atualizarStatus(request, response);
				resp = obj.toJSONString();
				break;
			default:
				break;
		}
			
				 PrintWriter out;
				try {
					
					if(response.getContentType().equals("application/json;charset=utf-8")){
						  response.getOutputStream().write(resp.toString().getBytes("UTF-8"));
					      response.getOutputStream().flush();
							   
					}else {
						out = response.getWriter();
						out.println(resp);
						out.flush();
					}
					
					
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					LogUtil.getLog().error("Erro ao fazer IO PrintWriter: " + e.getMessage(), e);
				}
			
				 
		   return null;
	}
	
	/**
	 * Método que inclui um novo usuário
	 * @param request
	 * @param response
	 * @return mensagem que será mostrada ao usuário
	 */
	private String inserirUsuario(HttpServletRequest request,
			HttpServletResponse response) {
		
			Usuario usu = new Usuario();
			Object respInsercao = 0;
			int tipoUsuarioId = 0;
			String resp = "", opcEmps = "";
			
			try {
			tipoUsuarioId = Integer.parseInt(request.getParameter("tipoUsuarioId"));
			
			} catch (NumberFormatException e) {
				LogUtil.getLog().error("Campo TipoUsuarioId da requisicao de insercao de usuario deve ser do tipo inteiro", e);
			} 
			
			usu.setCdSenha(request.getParameter("senha"));
			usu.setDtCriacao(Calendar.getInstance());
			usu.setNmLogin(request.getParameter("email"));
			usu.setNmUsuario(request.getParameter("nomeUsuario"));
			usu.setTpStatus(TipoStatusDao.getInstance().buscarTipoStatusAtivo());
			usu.setTpUsuario(TipoUsuarioDao.getInstance().buscaTipoUsuario(tipoUsuarioId));
			
			List<UsuarioEmpresa> usuEmps = new ArrayList<UsuarioEmpresa>();
			
			opcEmps = request.getParameter("opcoesEmpresa").toString();
			opcEmps = opcEmps.substring(0, opcEmps.lastIndexOf(","));   
			
			String[] empsId = opcEmps.split(",");
			Integer[] empsIDInt = new Integer[empsId.length];
			
			for (int i= 0; i < empsId.length; i++) {
				empsIDInt[i] = Integer.parseInt(empsId[i]);
			}
			ArrayList<Empresa> emps =  (ArrayList<Empresa>)EmpresaDao.getInstance().listaEmpresaPorIds(empsIDInt);
			
			
			
			for(Empresa emp: emps) {
				UsuarioEmpresa usuEmp = new UsuarioEmpresa();	
				UsuarioEmpresaPK usuEmpPK = new UsuarioEmpresaPK();

				usuEmpPK.setUsuario(usu);
				usuEmpPK.setEmpresa(emp);
				usuEmp.setPk(usuEmpPK);
				usuEmps.add(usuEmp);

			}
				
			usu.setUsuarioEmpresas(usuEmps);
			
			
			
			respInsercao = UsuarioDao.getInstance().salvarUsuario(usu);
		
			if (respInsercao instanceof String) {
					resp = respInsercao.toString();
			}
			
			if(respInsercao instanceof Integer) {
				if((Integer)respInsercao != 0) {
					resp = "<OK>" + MensagemProp.getPropriedades().getProperty("usuario.cadastro.sucesso");
				} else {
					resp = MensagemProp.getPropriedades().getProperty("usuario.cadastro.erro");
							
				}
				
			}
			 
		return resp;
	}
	
	/**
	 * Metodo que pesquisa Usuário recebendo email(chave única)
	 * @param request
	 * @param response
	 * @return mensagem que será mostrada ao usuário
	 */
	private String pesquisarUsuario(HttpServletRequest request,
			HttpServletResponse response) {
		
		List<Usuario> usuarios = UsuarioDao.getInstance().buscaUsuariosFull(request.getParameter("email"));
		
		
		JSONObject obj= new JSONObject();
		  
	
		
		String usuariosEmpresa = "";
		
		
		   if(usuarios.size() == 0) {
			   obj.put("erro", MensagemProp.getPropriedades().get("usuario.pesquisar.inexistente").toString());
		   } else {
			   obj.put("cdUsuario", new Integer(usuarios.get(0).getCdUsuario()));
			   obj.put("nmLogin", usuarios.get(0).getNmLogin());
			   obj.put("nmUsuario", new String(usuarios.get(0).getNmUsuario()));
			   obj.put("tpStatus", usuarios.get(0).getTpStatus()!=null?usuarios.get(0).getTpStatus().getCdStatus():0);
			   obj.put("tpUsuario", usuarios.get(0).getTpUsuario()!=null?usuarios.get(0).getTpUsuario().getCdTipoUsuario():0);
			   
			   
			   for (int i=0;i < usuarios.get(0).getUsuarioEmpresas().size(); i++) {
				   usuariosEmpresa +=  usuarios.get(0).getUsuarioEmpresas().get(i).getEmpresa().getCdcnpj() + ";";
			   }
			   
			   
			   obj.put("usuariosEmpresas",usuariosEmpresa);
			   
			   
		   }
		   
		   return obj.toJSONString();
	}
	
	/** Metodo que atualiza os o usuario
	 * @param request
	 * @param response
	 * @return menssagem de erro ou de sucesso
	 */
	private String atualizarUsuario(HttpServletRequest request,
			HttpServletResponse response) {
		

	 int cdUsuario = Integer.parseInt(request.getParameter("cdUsuario"));	
	 String[] usuariosEmpresa;	
	 String resp = "";
	 
	 

	 
	 Usuario usu = UsuarioDao.getInstance().getUsuario(cdUsuario);
	 
	 
	 usu.setDtAtualizacao(Calendar.getInstance());
	 
	 
	 TipoUsuario tpUsuario = TipoUsuarioDao.getInstance().buscaTipoUsuario(Integer.parseInt(request.getParameter("tipoUsuario")));
	 List<UsuarioEmpresa> usuEmpList = new ArrayList<UsuarioEmpresa>();
			 
	 if (tpUsuario != null) 
		 usu.setTpUsuario(tpUsuario);
	  
	 TipoStatus tpStatus = TipoStatusDao.getInstance().getStatus(Integer.parseInt(request.getParameter("tpStatus")));
	 
	 if (tpStatus != null)
		 usu.setTpStatus(tpStatus);
	 
	 if (request.getParameter("cdSenha") != null) 
		 usu.setCdSenha(request.getParameter("cdSenha")); 
		 
	 
		usu.setNmUsuario(request.getParameter("nomeUsuario"));
		usu.setNmLogin(request.getParameter("email"));	
		usuariosEmpresa = request.getParameter("usuariosEmpresas").toString().split(",");

	   
	   
	   
	   for (int i = 0 ; i < usuariosEmpresa.length; i++) {
		   
		 if (usuariosEmpresa[i] != null && !usuariosEmpresa[i].equals("")) {
			 UsuarioEmpresa usuEmp1 = new UsuarioEmpresa();
			   UsuarioEmpresaPK usuEmpPK = new UsuarioEmpresaPK();
			   usuEmpPK.setEmpresa(EmpresaDao.getInstance().getEmpresa(Integer.parseInt(usuariosEmpresa[i])));
			   usuEmpPK.setUsuario(usu);
			   usuEmp1.setPk(usuEmpPK);
			   usuEmpList.add(usuEmp1);
			 
		 }
		      
	   }
	   
	   usu.setUsuarioEmpresas(usuEmpList);
	   
	    resp = (String) UsuarioDao.getInstance().atualizarUsuario(usu);
		
		return resp;
	}
	
	private String pesquisarStatus(HttpServletRequest request, 
			HttpServletResponse response){
		String email = request.getParameter("email").trim();
		
		List<Usuario> usuarios =	(ArrayList<Usuario>) UsuarioDao.getInstance().buscaUsuariosFull(email);
		
		UsuarioDao.getInstance().buscaUsuariosFull(email);
		JSONObject obj= new JSONObject();
		
		if(usuarios.isEmpty()){
			String msg = MensagemProp.getPropriedades().get("usuario.pesquisar.inexistente").toString();
			obj.put("erro", msg + ":" + email);
			LogUtil.getLog().info(msg + email);
		}else {
			
			if(usuarios.get(0).getTpStatus().getNmStatus().equalsIgnoreCase("Ativo")){
				obj.put("sucess", MensagemProp.getPropriedades().get("usuario.desativar.pergunta").toString());
				obj.put("ativo", TipoStatusDao.getInstance().buscarTipoStatusDesativado().getCdStatus());
			}else{
				obj.put("sucess", MensagemProp.getPropriedades().get("usuario.ativar.pergunta"));
				obj.put("ativo", TipoStatusDao.getInstance().buscarTipoStatusAtivo().getCdStatus());
			}
			
			obj.put("idUsuario", usuarios.get(0).getCdUsuario());
		}
		
		   return obj.toJSONString();
	}
	
	/**Metodo para ativar ou dasetivar usuario
	 * @param request
	 * @param response
	 * @return
	 */
	public JSONObject atualizarStatus(HttpServletRequest request, 
			HttpServletResponse response) {
		int codigoUsuario = 0, codTipoStatus = 0;
		
		JSONObject obj= new JSONObject();
		
		try {
		codigoUsuario = Integer.parseInt(request.getParameter("idUsuario"));
		codTipoStatus = Integer.parseInt(request.getParameter("ativar"));
		} catch(NumberFormatException e){
			e.printStackTrace();
	    	LogUtil.getLog().error("Erro ao converter :"+ e.getMessage(), e);
		}
		
		if(codigoUsuario == 0){
			obj.put("erro", MensagemProp.getPropriedades().get("usuario.status.erro").toString());
			return obj;
		}
		
		
		Usuario usu = UsuarioDao.getInstance().getUsuario(codigoUsuario);
		usu.setTpStatus(TipoStatusDao.getInstance().getStatus(codTipoStatus));
		obj = (JSONObject) UsuarioDao.getInstance().atualizarStatusUsuario(usu);		
				
				
	
		return obj;
	}
	
}
