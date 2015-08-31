/**
 * 
 */
package br.com.edutex.form;


import java.io.IOException;
import java.io.PrintWriter;
import java.text.MessageFormat;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import br.com.edutex.DAO.EmpresaDao;
import br.com.edutex.DAO.TipoStatusDao;
import br.com.edutex.logic.Empresa;
import br.com.edutex.logic.TipoStatus;
import br.com.edutex.util.LogUtil;
import br.com.edutex.util.MensagemProp;

import org.json.simple.JSONObject;

/**
 * Classe que mapeia a Action de Negocio Empresa
 * @author bruno
 *
 */
public class EmpresaFormAjax extends Action {
	
	
	@Override 
	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response)  {
			
		String resp= "", param = "";
		
	response.setHeader("cache-control", "no-cache");
		 
	 param = request.getParameter("acao");
	 
	 switch (param) {
	case "inserir":
		response.setContentType("text/text;charset=utf-8");
		resp = inserirEmpresa(request, response);
		break;
	case "pesquisar":
		response.addHeader("Content-Type", "application/jso;charset=utf-8");
		resp = pesquisar(request, response);
		break;
	case "atualizar":
		response.setContentType("text/text;charset=utf-8");
		resp = atualizarEmpresa(request, response);
		break;
	case "pesquisarStatus":
		response.addHeader("Content-Type", "application/json;charset=utf-8");
		resp = pesquisarStatus(request, response);
		break;
	case "atualizarStatus":
		response.addHeader("Content-Type", "application/json;charset=utf-8");
		resp = atualizarStatus(request, response);
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
	 * Metodo que trata requisicao ajax para inserir empresa
	 * @param request
	 * @param response
	 * @return
	 */
	private String inserirEmpresa(HttpServletRequest request,
			HttpServletResponse response){
			
		String cnpj = request.getParameter("cnpj");
		long cnpjLong = 0l;
		cnpj = cnpj.replaceAll("-", "").replaceAll("/", "").replaceAll("\\.", "");
		
		int idRegTrib = 0; 

		    
		   ArrayList<Empresa> empresas =  (ArrayList<Empresa>) EmpresaDao.getInstance().listarEmpresaPorCnpj(cnpj);
		   
		   if(empresas.size() >= 1){
			   return MensagemProp.getPropriedades().get("empresa.cnpj.existente").toString();
		   }
		   
			    Empresa emp = new Empresa();
			    emp.setCnpj(cnpj);
			    emp.setNmEmpresa(request.getParameter("nmEmpresa"));
			    
			    try {
			    idRegTrib = Integer.parseInt(request.getParameter("regimeTributario"));
			    }catch (NumberFormatException e){
			    	e.printStackTrace();
			    	LogUtil.getLog().error("Erro ao converter o numero do regime Tributario: "+ e.getMessage(), e);
			    }
			    
			    String resposta = (String) EmpresaDao.getInstance().inseriEmpresa(emp, idRegTrib); 
			    if(resposta == null || resposta.equals("")){
					   return "<OK>" + MensagemProp.getPropriedades().get("empresa.inserido.sucesso").toString();
				   } else {
					   return resposta;
				   }
			 
	
	}
	
	/** Trata requisicao ajax para pesquisa de Empresa
	 * @param request
	 * @param response
	 * @return
	 */
	@SuppressWarnings("unused")
	private String pesquisar(HttpServletRequest request,
			HttpServletResponse response){
		String cnpj = request.getParameter("cnpj");
		cnpj = cnpj.replaceAll("-", "").replaceAll("/", "").replaceAll("\\.", "");
		
		ArrayList<Empresa> empresas =  (ArrayList<Empresa>) EmpresaDao.getInstance().listarEmpresaPorCnpj(cnpj);
		JSONObject obj= new JSONObject();
		  
		   if(empresas.size() == 0){
			   obj.put("erro", MensagemProp.getPropriedades().get("empresa.pesquisar.cnpj.inexistente").toString());
		   }else {
			   obj.put("cdEmpresa", new Integer(empresas.get(0).getCdcnpj()));
			   obj.put("nmEmpresa", empresas.get(0).getNmEmpresa());
			   obj.put("regimeTributario", new Integer(empresas.get(0).getRegimeTibutario().getCodTipoImposto()));
		   }
		   
		
       return obj.toJSONString();
		
		
	}
	
	/**
	 * Metodo que atualiza empresa
	 * @param request
	 * @param response
	 * @return
	 */
	private String atualizarEmpresa(HttpServletRequest request,
			HttpServletResponse response){
		
		int regCod = 0,idEmpresa = 0;
		
		try {
		   regCod = Integer.parseInt(request.getParameter("regimeTributario"));
		   
		} catch(NumberFormatException e){
			e.printStackTrace();
	    	LogUtil.getLog().error("Erro ao converter o numero do regime Tributario: "+ e.getMessage(), e);
		}
		
		try{
			idEmpresa = Integer.parseInt(request.getParameter("idEmpresa"));
		}catch(NumberFormatException e){
			e.printStackTrace();
	    	LogUtil.getLog().error("Erro ao converter o id da Empresa: "+ e.getMessage(), e);
		}
		
		
		Empresa emp = EmpresaDao.getInstance().getEmpresa(idEmpresa);
		emp.setNmEmpresa(request.getParameter("nmEmpresa"));
		
		if(EmpresaDao.getInstance().atualizarEmpresa(emp, regCod,0)){
			LogUtil.getLog().info("Empresa " + emp.getCnpj() + "atualizada com sucesso");
			return MensagemProp.getPropriedades().get("empresa.atualiza.sucesso").toString();
		} else { 
			return MensagemProp.getPropriedades().get("empresa.atualiza.erro").toString();
		}
		
		
	}
	
	/**
	 * Verifica se o a empresa consultada esta ativa ou nao
	 * @param request
	 * @param response 
	 * @return Mensagem para solicitar a decisao de ativar ou nao
	 * para o usuario
	 */
	private String pesquisarStatus(HttpServletRequest request, 
			HttpServletResponse response){
		String cnpjTrim = "";
		String cnpj = request.getParameter("cnpj");
		cnpjTrim = cnpj.replaceAll("-", "").replaceAll("/", "").replaceAll("\\.", "");
		
		ArrayList<Empresa> emps =	(ArrayList<Empresa>) EmpresaDao.getInstance().listarEmpresaPorCnpj(cnpjTrim);
		JSONObject obj= new JSONObject();
		
		if(emps.isEmpty()){
			String msg = MensagemProp.getPropriedades().get("empresa.pesquisar.cnpj.inexistente").toString();
			obj.put("erro", msg + ":" + cnpj);
			LogUtil.getLog().info(msg + cnpj);
		}else {
			
			if(emps.get(0).getTpStatus().getNmStatus().equalsIgnoreCase("Ativo")){
				String mensagemFormatada = MessageFormat.format(MensagemProp.getPropriedades().get("empresa.desativar.pergunta").toString(),  emps.get(0).getNmEmpresa()); 
				obj.put("sucess", mensagemFormatada);
				obj.put("ativo", TipoStatusDao.getInstance().buscarTipoStatusDesativado().getCdStatus());
			}else{
				String mensagemFormatada = MessageFormat.format(MensagemProp.getPropriedades().get("empresa.ativar.pergunta").toString(),  emps.get(0).getNmEmpresa()); 
				obj.put("sucess",mensagemFormatada);
				obj.put("ativo", TipoStatusDao.getInstance().buscarTipoStatusAtivo().getCdStatus());
			}
			
			obj.put("idEmpresa", emps.get(0).getCdcnpj());
		}
		
		   return obj.toJSONString();
	}
	
	/**
	 * Atualiza o status da empresa 
	 * @param request
	 * @param response
	 * @return Mensagem a ser mostrada ao usuario
	 */
	public String atualizarStatus(HttpServletRequest request, 
			HttpServletResponse response) {
		int codigoEmpresa = 0, codTipoStatus = 0;
		
		JSONObject obj= new JSONObject();
		
		try {
		codigoEmpresa = Integer.parseInt(request.getParameter("idEmpresa"));
		codTipoStatus = Integer.parseInt(request.getParameter("ativar"));
		} catch(NumberFormatException e){
			e.printStackTrace();
	    	LogUtil.getLog().error("Erro ao converter :"+ e.getMessage(), e);
		}
		
		if(codigoEmpresa == 0){
			obj.put("erro", MensagemProp.getPropriedades().get("empresa.status.erro").toString());
			return obj.toJSONString();
		}
		
		Empresa emp = EmpresaDao.getInstance().getEmpresa(codigoEmpresa);
		
		
		if(EmpresaDao.getInstance().atualizarEmpresa(emp,0,codTipoStatus)){
			obj.put("sucess",MensagemProp.getPropriedades().get("empresa.ativa.sucesso").toString());
			obj.put("erro", null);
		} else {
			obj.put("sucess", MensagemProp.getPropriedades().get("empresa.ativa.erro").toString());
			obj.put("erro", null);
		}
		return obj.toJSONString();
	}

}
