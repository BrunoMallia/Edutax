package br.com.edutex.form;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

import javax.json.Json;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;






import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;







import br.com.edutex.DAO.EstadoDao;
import br.com.edutex.DAO.FinalidadeNfeDao;
import br.com.edutex.DAO.ImpostoNcmDao;
import br.com.edutex.DAO.ImpostoNcmEstadoDao;
import br.com.edutex.DAO.NcmDao;
import br.com.edutex.logic.CST;
import br.com.edutex.logic.Empresa;
import br.com.edutex.logic.Estado;
import br.com.edutex.logic.ImpostoNcm;
import br.com.edutex.logic.ImpostoNcmEstado;
import br.com.edutex.logic.NCM;
import br.com.edutex.logic.TipoImposto;
import br.com.edutex.util.LogUtil;
import br.com.edutex.util.MensagemProp;

public class NcmFormAjax extends Action {
	
	@Override 
	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String param = "", resp= "";
		
		
		 //ESSE PARÂMETRO E ENVIADO PELO MÉTODO AJAX DE REQUISIÇÃO
		 param = request.getParameter("acao");
		 
		 switch (param) {
			case "pesquisar":
				response.addHeader("Content-Type", "application/json;charset=utf-8");
				resp = pesquisar(request, response);
				break;
				
			case "inserir":	
				response.addHeader("Content-Type", "application/json;charset=utf-8");
				resp = inserir(request, response);
				break;
				
			case "atualizar":
				response.addHeader("Content-Type", "application/json;charset=utf-8");
				resp = atualizar(request, response);
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
					e.printStackTrace();
					LogUtil.getLog().error("Erro ao fazer IO PrintWriter: " + e.getMessage(), e);
				}
			
				 
		   return null;
	}
	
	/**
	 * Pesquisa por NCM, cadastra ou altera NCM
	 * @param request
	 * @param response
	 * @return Resposta do servidor ao cliente
	 */
	private String pesquisar(HttpServletRequest request,
			HttpServletResponse response) throws NullPointerException {
		
		String parametroNCM = new String(); 
		parametroNCM = request.getParameter("ncm");
		int finalidade = Integer.parseInt(request.getParameter("finalidade"));
		List<NCM> listaNCM;
		JSONObject obj = new JSONObject();

		try {

			if (!parametroNCM.isEmpty() && !parametroNCM.equals(" ")) {
				listaNCM = NcmDao.getInstance().buscarNcms(parametroNCM,finalidade);
			} else {
				return MensagemProp.getPropriedades().getProperty("erro.geral")
						.toString();
			}

			if (listaNCM.isEmpty()) {
				String mensagemFormatada = MessageFormat.format(MensagemProp
						.getPropriedades().get("ncm.novo").toString(),
						parametroNCM);
				obj.put("novoNCM", true);

				String tabelaImpostoNCM = new String();

				for (Estado estado : EstadoDao.getInstance().listaEstados()) {

					tabelaImpostoNCM += "<tr><td style='display:none' >"
							+ estado.getCdEstado() + "</td><td>"
							+ estado.getNomeEstado() + "</td>"
							+ "<td>0</td><td>0</td></tr>";

				}

				obj.put("tabelaImpostoNCMEstado", tabelaImpostoNCM);

				obj.put("sucess", mensagemFormatada);
			} else {
				obj.put("novoNCM", false);
				obj.put("ncmCod", listaNCM.get(0).getCdNcm());
				obj.put("NCM", listaNCM.get(0).getNmNCM());
				//todo: salvar o CST também e o percentual de redução
				obj.put("cst", listaNCM.get(0).getCsts().get(0).getNmCST());
				obj.put("percentualReducao", listaNCM.get(0).getCsts().get(0).getNuPercentualReducao());
				obj.put("finalidade", listaNCM.get(0).getFinalidadeNfe()
						.getCdFinalidadeNfe());

				StringBuilder tabelaImpostoNCM = new StringBuilder();
				List<ImpostoNcm> listaImpostoNcm = (List<ImpostoNcm>)  listaNCM.get(0)
						.getImpNcm();

				for (ImpostoNcm imposto : listaImpostoNcm) {
					
					switch (imposto.getTipoImposto().getCdTipoImposto()) {
					case 1:
						obj.put("icms", imposto.getNuPercentualImposto());
					 List<ImpostoNcmEstado> impostosEstado =
							 ImpostoNcmEstadoDao.getInstance().listarImpostoNcmICMS(imposto.getNcm().getCdNcm());
						
						for (ImpostoNcmEstado impostoEstado : impostosEstado) {
							tabelaImpostoNCM.append("<tr><td style='display:none' >" +
									 impostoEstado.getEstado().getCdEstado()	 + "</td><td>" +  impostoEstado.getEstado().getNomeEstado()  + "</td>" +  	
										"<td>" + impostoEstado.getMva()  + "</td><td>" +  impostoEstado.getMvaAjustado() + "</td></tr>");
								
						}

						obj.put("tabelaImpostoNCMEstado", tabelaImpostoNCM);
						break;

					case 2:
						obj.put("pis", imposto.getNuPercentualImposto());
						break;

					case 3:
						obj.put("cofins", imposto.getNuPercentualImposto());
						break;

					case 4:
						obj.put("ipi", imposto.getNuPercentualImposto());
						break;

					case 5:
						obj.put("icmsInterestadual",
								imposto.getNuPercentualImposto());
						break;

					default:
						break;
					}

				}

			}

		} catch (JSONException e) {
			e.printStackTrace();
		} catch (NumberFormatException ne) {
			try {
				obj.put("erro","Código de NCM inválido, contecte com o administrador do sistema");
				return obj.toString();
			} catch (JSONException e) {
				LogUtil.getLog().error("Erro JSON na classe NcmFormAjax.java", e);
				throw new IllegalArgumentException("Erro JSON na classe NcmFormAjaxa.java", e);
			}
		} catch (Exception ex) {
			try {
				obj.put("erro","Erro no sistema, por favor contacte com o Administrador do sistema");
				LogUtil.getLog().error(ex.getMessage(), ex);
				return obj.toString();
			} catch (JSONException e) {
				LogUtil.getLog().error("Erro JSON na classe NcmFormAjax.java", e);
				throw new IllegalArgumentException("Erro JSON na classe NcmFormAjaxa.java", e);
			}
		} 

		return obj.toString();
		
	}
	
	
	
	/**
	 * Inseri um novo NCM e suas dependências
	 * @param request
	 * @param response
	 * @return resposta da inserção
	 */
	private String inserir(HttpServletRequest request,
			HttpServletResponse response) {
	
		JSONObject obj= new JSONObject();

		NCM ncm = new NCM();
		
		if (request.getParameter("ncm") == null) {
			throw new IllegalArgumentException("Parâmetro NCM inválido na inserção");
		}
		
		
		ncm.setNmNCM(request.getParameter("ncm"));
		Empresa empSession = (Empresa)request.getSession().getAttribute("empresa");
		
		if (empSession == null) {
			try {
				obj.put("erro", "Você não está logado no sistema ou a sessão expeirou. Logue novamente no sistema");
				return obj.toString();
			} catch (JSONException e) {
				LogUtil.getLog().error("Erro JSON na classe NcmFormAjax.java", e);
				throw new IllegalArgumentException("Erro JSON na classe NcmFormAjaxa.java", e);
				
			}
		}
		
	

		
		//Cadastra sempre o NCM da empresa que está logada
		ncm.setEmpresa(empSession);
		
		List<ImpostoNcm> listaImpostoNcm = new ArrayList<ImpostoNcm>();
		ImpostoNcm impostoNcmICMS = new ImpostoNcm();
		ImpostoNcm impostoPIS = new ImpostoNcm();
		ImpostoNcm impostoCOFINS = new ImpostoNcm();
		ImpostoNcm impostoIPI = new ImpostoNcm(); 
		ImpostoNcm impostoICMSInterestadual = new ImpostoNcm();
		
		int finalidadeCodigo = 0;
		double valorICMS = 0,valorPIS = 0, valorCOFINS = 0, valorIPI = 0, valorICMSInterestadual = 0, percentualReducao = 0;
		
		try {
			
			finalidadeCodigo = Integer.parseInt(request.getParameter("finalidade"));
			valorPIS = Double.parseDouble(request.getParameter("pis"));
			valorCOFINS = Double.parseDouble(request.getParameter("cofins"));
			valorICMS = Double.parseDouble(request.getParameter("icms"));
			valorIPI = Double.parseDouble(request.getParameter("ipi"));
			valorICMSInterestadual = Double.parseDouble(request.getParameter("icmsInterestadual"));
			percentualReducao = Double.parseDouble(request.getParameter("percReducao"));
			
		} catch (NumberFormatException e) {
			try {
				obj.put("erro", "Falha na conversão dos valores dos impostos do NCM. Altere para valores válidos");
				return obj.toString();
			} catch (JSONException e1) {
				LogUtil.getLog().error("Erro JSON na classe NcmFormAjax.java", e);
				throw new IllegalArgumentException("Erro JSON na classe NcmFormAjaxa.java", e);
			}
			
		}
		


		
		if (request.getParameter("impostosEstado") == null) {
			try {
				obj.put("erro", "lista de impostos Estado da página NCM está nula");
				return obj.toString();
			} catch (JSONException e) {
				LogUtil.getLog().error("lista de impostos Estado da página NCM está nula", e);
				throw new IllegalArgumentException("lista de impostos Estado da página NCM está nula", e);
			}
		}
		
         
         TipoImposto tpImposto = new TipoImposto();
         //Tipo imposto para ICMS
         tpImposto.setCdTipoImposto(1);
         impostoNcmICMS.setTipoImposto(tpImposto);
         impostoNcmICMS.setNuPercentualImposto(valorICMS);
		
         
         //Tipo imposto PIS
         tpImposto = new TipoImposto();
        tpImposto.setCdTipoImposto(2);
        impostoPIS.setTipoImposto(tpImposto);
        impostoPIS.setNuPercentualImposto(valorPIS);
        
        
        //Tipo imposto COFINS
        tpImposto = new TipoImposto();
        tpImposto.setCdTipoImposto(3);
        impostoCOFINS.setTipoImposto(tpImposto);
        impostoCOFINS.setNuPercentualImposto(valorCOFINS);
        
        
        //Tipo imposto iPI
        tpImposto = new TipoImposto();
		tpImposto.setCdTipoImposto(4);
		impostoIPI.setTipoImposto(tpImposto);
        impostoIPI.setNuPercentualImposto(valorIPI);
		 
        
        //Tipo IMPOSTO ICMS Interestadual
        tpImposto = new TipoImposto();
        tpImposto.setCdTipoImposto(5);
        impostoICMSInterestadual.setTipoImposto(tpImposto);
        impostoICMSInterestadual.setNuPercentualImposto(valorICMSInterestadual);
        
		 
         listaImpostoNcm.add(impostoNcmICMS);
		 listaImpostoNcm.add(impostoPIS);
		 listaImpostoNcm.add(impostoCOFINS);
		 listaImpostoNcm.add(impostoIPI);
		 listaImpostoNcm.add(impostoICMSInterestadual); 
		 
		 ncm.setImpNcm(listaImpostoNcm);
			List<CST> listaCst = new ArrayList<CST>();
			
			CST cst = new CST();
			cst.setNmCST(request.getParameter("cst"));
			cst.setDtCriacao(Calendar.getInstance());
			cst.setNuPercentualReducao(percentualReducao);
			listaCst.add(cst);
			
		ncm.setCsts(listaCst);
		 
		 try {
			 String resposta = NcmDao.getInstance().insereNcm(ncm, finalidadeCodigo);
			 ImpostoNcm impostoNcmResp = ImpostoNcmDao.getInstance().findImpostoNcmICMS(ncm.getCdNcm());
	    	 	
			 
			 
			 
				//jSONArray = objClient.getJSONArray("listaImpostoEstado");
				JSONObject jsobject;
				
				/*Esse iimposto NCM estado serve apenas para ICMS, futuramente talvez seja alterado
				 * para contemplar todos os tipos de impostos
				*/
				
				
				   final JSONObject jsonObjeto = new JSONObject(request.getParameter("impostosEstado").toString());
				    final JSONArray impEstadodata = jsonObjeto.getJSONArray("listaImpostoEstado");
				    final int n = impEstadodata.length();
			
				
				for (int i = 0 ; i < n; i++) {
					ImpostoNcmEstado impostoNCMEstado = new ImpostoNcmEstado();
						
					jsobject = impEstadodata.getJSONObject(i);
					
					Estado estado = EstadoDao.getInstance().getEstado(Integer.parseInt(jsobject.get("idEstado").toString()));
					impostoNCMEstado.setEstado(estado);
					impostoNCMEstado.setMva(Float.parseFloat(jsobject.getString("mva").toString()));
					impostoNCMEstado.setMvaAjustado(Float.parseFloat(jsobject.getString("mvaAjustado")));
					impostoNCMEstado.setImpostoNCM(impostoNcmResp);
					ImpostoNcmEstadoDao.getInstance().inserir(impostoNCMEstado);
				}
			 
			obj.put("sucesso", resposta);
		} catch (JSONException e) {
			LogUtil.getLog().error("Erro JSON na classe NcmFormAjaxa.java", e);
			throw new IllegalArgumentException("Erro JSON na classe NcmFormAjaxa.java", e);
		} catch (Exception ex) {
			try {
				obj.put("erro", "Erro ao tentar inserir NCM, por favor contacte com o Admnistrador do sistema");
				LogUtil.getLog().error("Erro ao tentar inserir NCM, por favor contacte com o Admnistrador do sistema", ex);
			} catch (JSONException e) {
				LogUtil.getLog().error("Erro JSON na classe NcmFormAjax.java", e);
				throw new IllegalArgumentException("Erro JSON na classe NcmFormAjaxa.java", e);
			}
		}
		
	return obj.toString();
	}
	
	
	/**
	 * Este método atualiza NCM e suas classes filhas
	 * @param request
	 * @param response
	 * @return
	 */
	private String atualizar(HttpServletRequest request,
			HttpServletResponse response) {
		
		JSONObject obj= new JSONObject();
		String parametroNCM = new String(); 
		parametroNCM = request.getParameter("ncm");
		List<NCM> listaNCM = null;
		
		if (!parametroNCM.isEmpty() && !parametroNCM.equals(" ")) {
			listaNCM = NcmDao.getInstance().buscarNcms(parametroNCM);
		} else {
			return MensagemProp.getPropriedades().getProperty("erro.geral")
					.toString();
		}
		
		NCM ncm = listaNCM.get(0);
		
		ncm.setDtAtualizacao(Calendar.getInstance());
		
		
		CST cst = ncm.getCsts().get(0);
		
		
		
		if (cst == null) {
			LogUtil.getLog().error("Valor do CST para este NCM está nulo no banco de dados");
			throw new NullPointerException("Valor do CST para este NCM está nulo no banco de dados");
		}
		

		
		
		int finalidadeCodigo = 0;
		double valorICMS = 0,valorPIS = 0, valorCOFINS = 0, valorIPI = 0, valorICMSInterestadual = 0, percentualReducao = 0;
		
		try {
			
			finalidadeCodigo = Integer.parseInt(request.getParameter("finalidade"));
			valorPIS = Double.parseDouble(request.getParameter("pis"));
			valorCOFINS = Double.parseDouble(request.getParameter("cofins"));
			valorICMS = Double.parseDouble(request.getParameter("icms"));
			valorIPI = Double.parseDouble(request.getParameter("ipi"));
			valorICMSInterestadual = Double.parseDouble(request.getParameter("icmsInterestadual"));
			percentualReducao = Double.parseDouble(request.getParameter("percReducao"));
			
		} catch (NumberFormatException e) {
			try {
				obj.put("erro", "Falha na conversão dos valores dos impostos do NCM. Altere para valores válidos");
				return obj.toString();
			} catch (JSONException e1) {
				LogUtil.getLog().error("Erro JSON na classe NcmFormAjax.java", e);
				throw new IllegalArgumentException("Erro JSON na classe NcmFormAjaxa.java", e);
			}
			
		}
		
		
		cst.setNmCST(request.getParameter("cst"));
		cst.setNuPercentualReducao(percentualReducao);
		
		for (ImpostoNcm impostoNCM  :ncm.getImpNcm()) {
			
			switch (impostoNCM.getTipoImposto().getCdTipoImposto()) {
				
			case 1:
				//ICMS
				impostoNCM.setNuPercentualImposto(valorICMS);
				break;
			
			case 2:
				//PIS
				impostoNCM.setNuPercentualImposto(valorPIS);
				break;
				
			case 3:
				//COFINS
				impostoNCM.setNuPercentualImposto(valorCOFINS);
				break;
				
			case 4:
				//IPI
				impostoNCM.setNuPercentualImposto(valorIPI);
				break;
				
			case 5:
				//ICMS Interestadual
				impostoNCM.setNuPercentualImposto(valorICMSInterestadual);
				break;
			}
			
		}
		
		
		
		if (request.getParameter("impostosEstado") == null) {
			try {
				obj.put("erro", "lista de impostos Estado da página NCM está nula");
				return obj.toString();
			} catch (JSONException e) {
				LogUtil.getLog().error("lista de impostos Estado da página NCM está nula", e);
				throw new IllegalArgumentException("lista de impostos Estado da página NCM está nula", e);
			}
		}
		
		
		try {
			JSONObject jsobject;
			NcmDao.getInstance().atualizar(ncm, finalidadeCodigo);
			
			
			   final JSONObject jsonObjeto = new JSONObject(request.getParameter("impostosEstado").toString());
			    final JSONArray impEstadodata = jsonObjeto.getJSONArray("listaImpostoEstado");
			    final int n = impEstadodata.length();
		
			
			for (int i = 0 ; i < n; i++) {
				ImpostoNcmEstado impostoNCMEstado = new ImpostoNcmEstado();
					
				jsobject = impEstadodata.getJSONObject(i);
				
				Estado estado = EstadoDao.getInstance().getEstado(Integer.parseInt(jsobject.get("idEstado").toString()));
				impostoNCMEstado.setEstado(estado);
				impostoNCMEstado.setMva(Float.parseFloat(jsobject.getString("mva").toString()));
				impostoNCMEstado.setMvaAjustado(Float.parseFloat(jsobject.getString("mvaAjustado")));
				ImpostoNcm impostoNcmResp = ImpostoNcmDao.getInstance().findImpostoNcmICMS(ncm.getCdNcm());
				//ImpostoNcmDao.getInstance().atualizar(impostoNcmResp, finalidadeCodigo);
				impostoNCMEstado.setImpostoNCM(impostoNcmResp);
				ImpostoNcmEstadoDao.getInstance().atualizar(impostoNCMEstado);
			}
			
			
			
			obj.put("sucesso", "NCM atualizado com sucesso");
		} catch (Exception e) {
			try {
				obj.put("erro", "Erro na atualização do NCM, contacte o administrador do sistema");
			} catch (JSONException e1) {
				LogUtil.getLog().error("Erro JSON na classe NcmFormAjax.java", e);
				throw new IllegalArgumentException("Erro JSON na classe NcmFormAjaxa.java", e);
			}
			LogUtil.getLog().error("Erro na atualização do NCM", e);
		}
		
		
		return obj.toString();
	}
}
