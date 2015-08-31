package br.com.edutex.form;



import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import br.com.edutex.DAO.ImpostoNcmDao;
import br.com.edutex.DAO.ImpostoNcmEstadoDao;
import br.com.edutex.logic.CST;
import br.com.edutex.logic.Empresa;
import br.com.edutex.logic.ImpostoNcm;
import br.com.edutex.logic.ImpostoNcmEstado;
import br.com.edutex.logic.NFE;
import br.com.edutex.logic.NotaValidadaAliquota;
import br.com.edutex.logic.NotaValidadaItem;
import br.com.edutex.logic.Validacao;
import br.com.edutex.notafiscal.NotaFiscal;
import br.com.edutex.util.LogUtil;
import br.com.edutex.util.NumeroFormato;



public class ValidarRegraForm extends Action {

	
	private static final float ALIQUOTA_SP_DESTINO = 18;
	
	
	public ValidarRegraForm() {
		
		 
		 
	}
	
	
	private Validacao validacao;
	
	private StringBuilder mensagem;
	
	/* (non-Javadoc)
	 * Action para tratar os uplods das notas fiscais
	 * @see org.apache.struts.action.Action#execute(org.apache.struts.action.ActionMapping, org.apache.struts.action.ActionForm, javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	@Override 
	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		setValidacao((Validacao) request.getSession().getAttribute("validacaoNotaFiscal")) ;
     
		/*Se a finalidade da nota for complemento
		 campo finNFE corresponde  a finalidade da nota
			1 - NFe normal 
			2 - NFe complementar
			3 - NFe de ajuste
			4 - Devolução/Retorno
		*/
		
		//para o caso da finalidade da nota for consumo final
		if (validacao != null && validacao.getFinalidadeNfe().getCdFinalidadeNfe() == 2) {
			
			//cNF corresponde ao Código Fiscal da Nota
			//lê cnf da nota.
			//Este método irá bater os valores das alíquotas com o que está cadastrado no EDUTAX
			
		if (!validarImpostos()) {
				//recusa nota
				request.setAttribute("erro", getMensagem());
				return mapping.findForward("erro");
			}
				 
		}
		
		//gera a saída da nota fiscal
		NotaFiscal notaFiscal = new NotaFiscal();
		
		NFE nfe = validacao.getNfeInicial();
		
		validacao.setNfeGerada((NFE) nfe.clone());
		
		
		
		for (NotaValidadaItem notaItem: validacao.getNfeGerada().getNotaValidada().getNotasValidadaItem()) {
				
				for(NotaValidadaAliquota notaAliquota:notaItem.getNotasValidadasAliquotas()) {
					
								switch (notaAliquota.getOrigem()) {
											
											case 1:
												notaAliquota.setOrigem(2);
												break;
											
											case 6:
												notaAliquota.setOrigem(7);
												break;
												
											default:
												break;
										
										}
					
				}
			
						
		}
		
		//para o caso da finalidade da nota fiscal ser industrialização
		if (validacao != null && validacao.getFinalidadeNfe().getCdFinalidadeNfe() == 2) {
			
			for (NotaValidadaItem notaItem: validacao.getNfeGerada().getNotaValidada().getNotasValidadaItem()) {
				
					for(NotaValidadaAliquota notaAliquota:notaItem.getNotasValidadasAliquotas()) {
						
						//verifica se é ICMS
						if (notaAliquota.getCst().getNmCST().equals("60") && notaAliquota.getTipoImpostoAliquota().getCdTipoImposto() == 1) {
								
							 List<ImpostoNcm> listaImpostoNcmCadastrado = ImpostoNcmDao.getInstance().buscarImpostoNcm(notaItem.getNcm().getNmNCM(),
							  			2, notaItem.getNcm().getEmpresa().getCdcnpj());
							
								//VALOR DO ICMS CADASTRADO NO SISTEMA
							 if (listaImpostoNcmCadastrado.get(0) != null) {
								 notaAliquota.setPercentualAliquota(NumeroFormato.getNumero2digitos((float)listaImpostoNcmCadastrado.get(0).getNuPercentualImposto()) );
								 notaAliquota.setValorBCSTRetidoAnteriormente(NumeroFormato.getNumero2digitos(notaAliquota.getValorBCImposto() * notaAliquota.getPercentualAliquota()));
							 }
								
						}	
					
				}
			
			
		}
		
	 
	} else {
		
		for (NotaValidadaItem notaItem: validacao.getNfeGerada().getNotaValidada().getNotasValidadaItem()) {
			
			for(NotaValidadaAliquota notaAliquota:notaItem.getNotasValidadasAliquotas()) {
					
				if (!notaAliquota.getCst().getNmCST().equals("60")){
						
					 List<ImpostoNcm> listaImpostoNcmCadastrado = ImpostoNcmDao.getInstance().buscarImpostoNcm(notaItem.getNcm().getNmNCM(),
					  			validacao.getFinalidadeNfe().getCdFinalidadeNfe(), notaItem.getNcm().getEmpresa().getCdcnpj());
					
					 //o CST IRÁ MUDAR PARA TODOS OS IMPOSTOS?
					 if (listaImpostoNcmCadastrado.get(0) != null) {
						 	CST cstCadastrado = listaImpostoNcmCadastrado.get(0).getNcm().getCsts().get(0);
						 	if (cstCadastrado != null) {
						 		 notaAliquota.getCst().setNmCST(listaImpostoNcmCadastrado.get(0).getNcm().getCsts().get(0).getNmCST());
						 	} else {
						 		request.setAttribute("erro", "CST não Cadastrado para o NCM " + notaItem.getNcm().getNmNCM() + ",finalidade " 
						 				+ validacao.getFinalidadeNfe().getNmFinalidade());
								return mapping.findForward("erro");
						 	}
						
					 } else {
						 request.setAttribute("erro", "NCM " + notaItem.getNcm().getNmNCM() + "não cadastrado -para a finalidade " 
					 				+ validacao.getFinalidadeNfe().getNmFinalidade());
							return mapping.findForward("erro");
					 }
					
				}
				
				
			}
		}
		
	}
		
		
		
		//PEGA o tipo de empresa da sessão
		Empresa empSessao = (Empresa) request.getSession().getAttribute("empresa");
		
		//SE A EMPRESA FOR SIMPLES NACIONAL ALTERA BC e aliquota dependendo do tipo de imposto
		if (empSessao.getRegimeTibutario().getCodTipoImposto() == 3) {
			
				for (NotaValidadaItem notaItem: validacao.getNfeGerada().getNotaValidada().getNotasValidadaItem()) {
						//to do: é só para ICMS?
					
						for (NotaValidadaAliquota aliquotaNota:notaItem.getNotasValidadasAliquotas()) {
							aliquotaNota.setPercentualAliquota(0);
							aliquotaNota.setValorAliquota(0);
							aliquotaNota.setValorBCImposto(0);
							aliquotaNota.setValorBCSTRetidoAnteriormente(0);
							aliquotaNota.setValorBCSTDest(0);
							
							switch (aliquotaNota.getTipoImpostoAliquota().getCdTipoImposto()) {
								case 1:
									//ICMS
									aliquotaNota.setCsosn(900);
									break;
								case 2:	
									//PIS
									aliquotaNota.getCst().setNmCST("99");
									break;
								case 3:	
									//COFINS
									aliquotaNota.getCst().setNmCST("99");
									break;
								case 4:
									//IPI
									aliquotaNota.getCst().setNmCST("49");
									break;
									
								default:
									break;
							}
						
							
						}
						
					
				}
		} else {
			for (NotaValidadaItem notaItem: validacao.getNfeGerada().getNotaValidada().getNotasValidadaItem()) {
			
				for (NotaValidadaAliquota aliquotaNota:notaItem.getNotasValidadasAliquotas()) {
							CST cst = new CST();
							cst.setNmCST("90");
							aliquotaNota.setCst(cst);;
							aliquotaNota.setPercentualAliquota(0);
							aliquotaNota.setValorAliquota(0);
				}
				
			
		}
			
			if (validacao.getFinalidadeNfe().getCdFinalidadeNfe() == 1 || validacao.getFinalidadeNfe().getCdFinalidadeNfe() == 3) {
						//pCredSN = valor permitido para crédito da nota
			}
	}
		  
		
		/**
		 * Se CST do XML igual CST do Edutax
	Se o CST do XML = 10 
		Se o fornecedor for simples nacional
			Se a finalidade da nota for revenda
				BC ST = PROD + IPI + outras despesas + frete * (1+ mva original)
				assumir BC = 0, alíquotas = 0
			senão
				rejeita a nota
			fimse	
		senão
			BC ST = PROD + IPI + outras despesas + frete * (1+ mva ajustado)	
		Fimse
	senão
		Se finalidade for diferente de revenda
			rejeita a nota
		fimse
	fimse
Fimse*/
		
		notaFiscal.escreverXML(validacao.getNfeGerada());
		
		
		request.setAttribute("sucesso", "A nota fiscal " + validacao.getNfeGerada().getNmNfe() +  "foi gerada com sucesso no diretório " + validacao.getNfeGerada().getNmFilePath());
		  
		return mapping.findForward("sucesso");
	}
	
	
	/**
	 * 
	 * @param notaValidadaItem
	 * @return
	 */
	private boolean validarImpostos() {
		
		mensagem = new StringBuilder();
		 
		
		 
		 for (NotaValidadaItem notaItemNota :validacao.getNfeInicial().getNotaValidada().getNotasValidadaItem()) {
			 
			 
			 	
				//impostos cadastrados
			 List<ImpostoNcm> listaImpostoNcmCadastrado = ImpostoNcmDao.getInstance().buscarImpostoNcm(notaItemNota.getNcm().getNmNCM(),
		  			2, notaItemNota.getNcm().getEmpresa().getCdcnpj());
			 
			 
			 
			 
			 
		 	 //IPI
		 	 float valorIPINota = notaItemNota.getNotasValidadasAliquotas().get(3).getValorAliquota();
		 	 float valorIPICadastrado  = 0;
		 	 
		 	 if (valorIPINota != 0) {
		 		  valorIPICadastrado = NumeroFormato.getNumero2digitos((float) (notaItemNota.getNotasValidadasAliquotas().get(3).getValorBCImposto() * (listaImpostoNcmCadastrado.get(3).getNuPercentualImposto()/100)));
		 		 
		 		 if (valorIPINota > valorIPICadastrado || (valorIPINota > valorIPICadastrado + 0.01) ||
		 				(valorIPINota < valorIPICadastrado - 0.01)) {
		 			 mensagem.append("<b>Nota recusada.Valor do IPI inconsistente.</b><br/> NCM do item:" + notaItemNota.getNcm().getNmNCM() + " valor da alíquota da nota: " + valorIPINota +
		 			  " valor alíquota cadastrada: " + valorIPICadastrado + "<br/>");
		 		 }
		 	 }
		 	
			 	if (validacao.getNfeInicial().getNotaValidada().getIndFinal() != 1) {
			 		valorIPICadastrado = 0;
			 	}
			 	//comparando ICMS
			 	    
			 float valorIcmsNota = notaItemNota.getNotasValidadasAliquotas().get(0).getValorAliquota();
		 	  
		 	 float percentualReducao = (float)listaImpostoNcmCadastrado.get(0).getNcm().getCsts().get(0).getNuPercentualReducao();
		 	  
		 		 if (listaImpostoNcmCadastrado.get(0).getNcm().getCsts().get(0).getNmCST().equals("10") ||
		 				 listaImpostoNcmCadastrado.get(0).getNcm().getCsts().get(0).getNmCST().equals("70")) {
		 			 
		 			 ImpostoNcmEstado impostoNcmEstado = null;  
		 			  // PEGAR O MVA AJUSTADO:  CÓDIGO DO ESTADO DE SÃO PAULO É 35
		 			   // o valor da base de cálculo - valor do produto + mva ajustado
				try {
					impostoNcmEstado = ImpostoNcmEstadoDao
							.getInstance().getMVAjustadoImposto(
									notaItemNota.getNcm().getNmNCM(), 35);
				} catch (javax.persistence.NoResultException result) {
					LogUtil.getLog()
							.error("Não existe nenhum imposto de MVA/ MVAjustado cadastrado para o NCM " + notaItemNota.getNcm().getNmNCM() +
									"e para o Estado SP", result);
					mensagem = new StringBuilder();
					mensagem.append("Não existe nenhum imposto de MVA/ MVAjustado cadastrado para o NCM " + notaItemNota.getNcm().getNmNCM() +
							" e para o Estado SP"); 
					return false;	
				}	
		 	  
		 			  	float icmsSTCadastrado = 0;
		 			  	
		 			  	
		 			  	 
		 			  	
		 			  	if (listaImpostoNcmCadastrado.get(0).getNcm().getCsts().get(0).getNmCST().equals("10")) {
		 			  		float BCST = NumeroFormato.getNumero2digitos((notaItemNota.getValorBrutoProduto() + valorIPICadastrado));
		 			  			
		 			  			if (validacao.getNfeInicial().getNotaValidada().getCRT() == 3) {
		 			  				//todo: retificar para calcular BCST = (VALOR PRODUTO  - IPI) * 1.MVA
		 			  				BCST = NumeroFormato.getNumero2digitos(BCST + (BCST * NumeroFormato.getNumero2digitos((impostoNcmEstado.getMvaAjustado() / 100))));
		 			  			} else {
		 			  				BCST = BCST + (BCST * ( impostoNcmEstado.getMva() / 100)); 
		 			  			}
		 			  			
		 			  		//BCST * VALOR ICMS		
		 			  		float valorICMSSTCadastrado = NumeroFormato.getNumero2digitos((float) BCST * ((float)listaImpostoNcmCadastrado.get(0).getNuPercentualImposto()/100));
		 			  		
		 			  		//BCST * AliqST - Alíquota da própria operacao
		 			  		float icmsST = NumeroFormato.getNumero2digitos(valorICMSSTCadastrado - (notaItemNota.getNotasValidadasAliquotas().get(0).getValorBCImposto() * (notaItemNota.getNotasValidadasAliquotas().get(0).getPercentualAliquota()/100)));
		 			  		
		 			  		float valorAliquotaICMSSTNota = notaItemNota.getNotasValidadasAliquotas().get(0).getValorAliquotaST(); 
		 			  		
		 			  		if ((valorAliquotaICMSSTNota  > icmsST + 0.01) || (valorAliquotaICMSSTNota < icmsST - 0.01)) {
		 			  		 mensagem.append("<b>Nota recusada. Valor do ICMS inconsistente.</b><br/> NCM do item:" + notaItemNota.getNcm().getNmNCM() + " valor da alíquota da nota: " + valorAliquotaICMSSTNota +
		 				 			  " valor cadastrado: " + icmsST + "<br/>");
		 			  		}
		 			  	}
		 			  	
		 			  	if (listaImpostoNcmCadastrado.get(0).getNcm().getCsts().get(0).getNmCST().equals("70")) {
		 			  		//Produto-ReduçãoX1.MVA% = Base de Calculo ICMS ST
		 			  		float BCSTReduzida = 0;
	 			  			if (notaItemNota.getNotaValidada().getCRT() == 1) {
	 			  				BCSTReduzida = (notaItemNota.getValorBrutoProduto() -(percentualReducao/100))  * ( impostoNcmEstado.getMva() / 100); 
	 			  			} else {
	 			  				BCSTReduzida = notaItemNota.getValorBrutoProduto() * (impostoNcmEstado.getMvaAjustado() / 100);
	 			  			}
	 			  			
	 			  			float valorICMSSTReduzida = NumeroFormato.getNumero2digitos((float) BCSTReduzida * (notaItemNota.getNotasValidadasAliquotas().get(0).getPercentualAliquotaST()/100));
	 			  			//float icmsSTRed = valorICMSSTReduzida - (notaItemNota.getNotasValidadasAliquotas().get(0).getValorBCImposto() * (notaItemNota.getNotasValidadasAliquotas().get(0).getPercentualAliquota()/100));
		 			  		
	 			  			
		 			  		//para cst 70 possui percentual de redução
		 			  		if ((valorIcmsNota > valorICMSSTReduzida + 0.01) || (valorIcmsNota < valorICMSSTReduzida - 0.01)) {
		 			  		 mensagem.append("<b>Nota recusada.Valor do ICMS Interestadual inconsistente.</b><br/> NCM do item:" + notaItemNota.getNcm().getNmNCM() + " valor da alíquota da nota: " + icmsSTCadastrado +
		 				 			  " valor cadastrado: " + valorIcmsNota + "<br/>");
		 			  		}
		 			  		
		 			  	}
		 			  	
		 		  } else {
		 			 float valorBCIcms =  (float) (notaItemNota.getValorBrutoProduto() - valorIPICadastrado) - (percentualReducao /100 * notaItemNota.getValorBrutoProduto());
			 		  float valorICMSCadastrado =  NumeroFormato.getNumero2digitos((float) (valorBCIcms * (listaImpostoNcmCadastrado.get(0).getNuPercentualImposto() /100)));
			 		  
			 		  if ((valorIcmsNota > valorICMSCadastrado + 0.01) || (valorIcmsNota < valorICMSCadastrado - 0.01)) {
			 		  		// inconsitência para gerar nota de complemento?
		 		  		  mensagem.append("<b>Nota recusada.Valor de ICMS inconsistente.</b><br/> NCM do item: " +  notaItemNota.getNcm().getNmNCM()  + "  valor ICMS da nota " + valorIcmsNota + " valor da alíquota ICMS cadastrada: " + 
		 		  				valorICMSCadastrado + "<br/>");
			 			  
			 		  }
			 	 
		 		  }
		 		  
		 		   
			 	 
			 	
			 	  // percentual da nota para PIS
			 	  float valorPISNota = notaItemNota.getNotasValidadasAliquotas().get(1).getValorAliquota();
			 	  
			 	  if (valorPISNota != 0) {
			 		  
			 		  	float valorPISCadastrado =  NumeroFormato.getNumero2digitos((float) (notaItemNota.getNotasValidadasAliquotas().get(1).getValorBCImposto() * (listaImpostoNcmCadastrado.get(1).getNuPercentualImposto()/100)));
			 		  	
			 		  if ((valorPISNota > valorPISCadastrado + 0.01) || (valorPISNota < valorPISCadastrado - 0.01)) {
			 			  mensagem.append("<b>Nota recusada.Valor do PIS inconsistente.</b><br/> NCM do item: " + notaItemNota.getNcm().getNmNCM() + " valor da alíquota nota: " + valorPISNota + " valor aliquota cadastrada: " +
			 					 valorPISCadastrado + "<br/>");
			 		  }
			 	  }
			 	  
			 	  
			 	  //COFINS
			 	 float valorCOFINSNota = notaItemNota.getNotasValidadasAliquotas().get(2).getValorAliquota();
			 	 
			 	 if (valorCOFINSNota != 0) {
			 		    float valorCOFINSCadastrado = NumeroFormato.getNumero2digitos((float) (notaItemNota.getValorBrutoProduto() * (listaImpostoNcmCadastrado.get(2).getNuPercentualImposto()/100)));
			 		    
			 		    
			 		    
			 		 if ((valorCOFINSNota > valorCOFINSCadastrado + 0.01) || (valorCOFINSNota < valorCOFINSCadastrado - 0.01) ) {
			 			 mensagem.append("<b>Nota recusada.Valor do COFINS inconsistente.</b><br/> Ncm do item: " + notaItemNota.getNcm().getNmNCM() + " valor da alíquota da nota: " + valorCOFINSNota + " valor alíquota cadastrada: " +
			 					valorCOFINSCadastrado + "<br/>");
			 		 }
			 	 }
			 	
			 	
			 	
		 }	 
			 	
			 
			  	 //todo: levantar como validar os valores dos impostos como ST(Substituicao Tributária)
			 	 
			 	  if (!getMensagem().toString().equals("")) {
			 		  	return false;
			 	  }
			 	  
		return true;
		
	}


	public Validacao getValidacao() {
		return validacao;
	}


	public void setValidacao(Validacao validacao) {
		this.validacao = validacao;
	}


	public StringBuilder getMensagem() {
		return mensagem;
	}
	
}
