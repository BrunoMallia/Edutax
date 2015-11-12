package br.com.edutex.form;



import java.io.ByteArrayOutputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;






import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import br.com.edutex.DAO.ImpostoNcmDao;
import br.com.edutex.DAO.ImpostoNcmEstadoDao;
import br.com.edutex.DAO.ValidacaoDao;
import br.com.edutex.logic.CST;
import br.com.edutex.logic.Empresa;
import br.com.edutex.logic.ImpostoNcm;
import br.com.edutex.logic.ImpostoNcmEstado;
import br.com.edutex.logic.NFE;
import br.com.edutex.logic.NotaValidadaAliquota;
import br.com.edutex.logic.NotaValidadaItem;
import br.com.edutex.logic.TipoErro;
import br.com.edutex.logic.Validacao;
import br.com.edutex.logic.ValidacaoErro;
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
		OutputStream outPutDownload = null;
     
		/*Se a finalidade da nota for complemento
		 campo finNFE corresponde  a finalidade da nota
			1 - NFe normal 
			2 - NFe complementar
			3 - NFe de ajuste
			4 - Devolução/Retorno
		*/
		
		//para o caso da finalidade da nota for consumo final
		if (validacao != null) {
			
			//cNF corresponde ao Código Fiscal da Nota
			//lê cnf da nota.
			//Este método irá bater os valores das alíquotas com o que está cadastrado no EDUTAX
			
		if (!validarImpostos()) {
				//recusa nota
				ValidacaoDao.getInstance().salvarValidacao(validacao);
				request.setAttribute("erro", getMensagem());
				return mapping.findForward("erro");
			}
				 
		}
		
		//gera a saída da nota fiscal
		NotaFiscal notaFiscal = new NotaFiscal();
		
		validacao.setNfeGerada((NFE) validacao.getNfeInicial().clone());
		
	
		
		for (NotaValidadaItem notaItem: validacao.getNfeGerada().getNotaValidada().getNotasValidadaItem()) {
				
				
			
			for(NotaValidadaAliquota notaAliquota:notaItem.getNotasValidadasAliquotas()) {
							
						 if(notaAliquota != null) {
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
				}
			
						
	
		
		//para o caso da finalidade da nota fiscal ser industrialização
		if (validacao != null && validacao.getFinalidadeNfe().getCdFinalidadeNfe() == 1) {
			
			for (NotaValidadaItem notaItem: validacao.getNfeGerada().getNotaValidada().getNotasValidadaItem()) {
				
					for(NotaValidadaAliquota notaAliquota:notaItem.getNotasValidadasAliquotas()) {
						
						//verifica se é ICMS
						if (notaAliquota.getCst().getNmCST().equals("60") && notaAliquota.getTipoImpostoAliquota().getCdTipoImposto() == 1) {
								
							 List<ImpostoNcm> listaImpostoNcmCadastrado = ImpostoNcmDao.getInstance().buscarImpostoNcm(notaItem.getNcm().getNmNCM(),
							  			1, notaItem.getNcm().getEmpresa().getCdcnpj());
							
							 if (listaImpostoNcmCadastrado == null) {
									request.setAttribute("erro", "Imposto não foi cadastrado para o ncm: " + notaItem.getNcm().getNmNCM() + ",finalidade " 
							 				+ validacao.getFinalidadeNfe().getNmFinalidade());
									return mapping.findForward("erro");
							 }
							 
						//ATRIBUI O CST CADASTRADO NO SISTEMA A NOTA	 
						notaAliquota.getCst().setNmCST(listaImpostoNcmCadastrado.get(0).getNcm().getCsts().get(0).getNmCST());
						
						 switch (notaAliquota.getCst().getNmCST()) {
							
						 	case "00":
							    notaAliquota.setValorBCImposto((validacao.getNfeInicial().getNotaValidada().getValorProdutoTotal() + validacao.getNfeInicial().getNotaValidada().getValorFrete()));	
								notaAliquota.setPercentualAliquota((float) listaImpostoNcmCadastrado.get(0).getNuPercentualImposto());
								notaAliquota.setValorAliquota(NumeroFormato.getNumero2digitos((float)(notaAliquota.getPercentualAliquota()/100) * notaAliquota.getValorBCImposto()));
								break;
							
							case "10":
								
								break;
							
							case "60":
								 notaAliquota.setValorBCSTRetidoAnteriormente(NumeroFormato.getNumero2digitos((validacao.getNfeInicial().getNotaValidada().getValorProdutoTotal() + validacao.getNfeInicial().getNotaValidada().getValorFrete())));
								 notaAliquota.setValorImpostoSTRetidoAnteriormente(NumeroFormato.getNumero2digitos((float)listaImpostoNcmCadastrado.get(0).getNuPercentualImposto() * (notaAliquota.getValorBCSTRetidoAnteriormente()/100)));
								break;
							default:
								break;
							}
							
								
						}	
					
				}
			
			
		}
		
	 
	} else {
		
		for (NotaValidadaItem notaItem: validacao.getNfeGerada().getNotaValidada().getNotasValidadaItem()) {
			
			for(NotaValidadaAliquota notaAliquota:notaItem.getNotasValidadasAliquotas()) {
				
				 List<ImpostoNcm> listaImpostoNcmCadastrado = ImpostoNcmDao.getInstance().buscarImpostoNcm(notaItem.getNcm().getNmNCM(),
				  			validacao.getFinalidadeNfe().getCdFinalidadeNfe(), notaItem.getNcm().getEmpresa().getCdcnpj());
				
				
				// TRATAMENTO PARA A FINALIDADE DA NOTA FOR REVENDA(INDUSTRIALIZAÇÃO)
				if (validacao.getFinalidadeNfe().getCdFinalidadeNfe() == 3 && listaImpostoNcmCadastrado.get(0).getNcm().getCsts().get(0).getNmCST().equals("00")) {
			 		request.setAttribute("erro", "CST Cadastrado para o NCM " + notaItem.getNcm().getNmNCM() + ",finalidade " 
			 				+ validacao.getFinalidadeNfe().getNmFinalidade() + ", é diferente da nota, a nota rejeitada.");
					return mapping.findForward("erro");
		
				}
					
				if (!notaAliquota.getCst().getNmCST().equals("60")){
						
					
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
				
				//este algorítimo sempre zera as alíquotas e o CST/ CSOSN
		
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
									if (empSessao.getRegimeTibutario().getCodTipoImposto() == 3) {
										aliquotaNota.setCsosn(900);
									} else {
										aliquotaNota.getCst().setNmCST("90");
									}
									
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
							
							if (validacao.getFinalidadeNfe().getCdFinalidadeNfe() == 1 || validacao.getFinalidadeNfe().getCdFinalidadeNfe() == 3) {
								//pCredSN = valor permitido para crédito da nota
								
								
							}	
						
							
						}
				
				}
	

	}	
		//EMPRESA NÃO FOR LUCRO REAL
		if (empSessao.getRegimeTibutario().getCodTipoImposto() != 1) {
			for (NotaValidadaItem notaItem: validacao.getNfeGerada().getNotaValidada().getNotasValidadaItem()) {
			
				for (NotaValidadaAliquota aliquotaNota:notaItem.getNotasValidadasAliquotas()) {
					if (aliquotaNota.getTipoImpostoAliquota().getCdTipoImposto() == 2 || aliquotaNota.getTipoImpostoAliquota().getCdTipoImposto() == 3) {
						CST cst = new CST();
						cst.setNmCST("70");
						aliquotaNota.setCst(cst);
						aliquotaNota.setValorBCImposto(0);
						aliquotaNota.setPercentualAliquota(0);
						aliquotaNota.setValorAliquota(0);
						}
				}
					
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
		
		outPutDownload = notaFiscal.escreverXML(validacao.getNfeGerada());
	  	ValidacaoDao.getInstance().salvarValidacao(validacao);
		
	  	 
	  	if (validacao.getNfeGerada().isNotaComplementar()) {
	  		request.setAttribute("sucesso", "A nota fiscal " + validacao.getNfeGerada().getNmNfe() +  " foi gerada com sucesso no diretório " + validacao.getNfeGerada().getNmFilePath()
	  			+ ".Esta nota necessita de uma nota complementar");
	  	} else {
	  		request.setAttribute("sucesso", "A nota fiscal " + validacao.getNfeGerada().getNmNfe() +  " foi gerada com sucesso no diretório " + validacao.getNfeGerada().getNmFilePath());
	  	}
       
	  	//request.getSession(true).setAttribute("streamDownload", new String(byteArrayOutput.toByteArray()));
	  	request.getSession(true).setAttribute("nomeNfe", validacao.getNfeGerada().getNmNfe());
        request.getSession(true).setAttribute("streamDownload", outPutDownload);
        
        outPutDownload.close();
        /*ServletOutputStream servletOutput = response.getOutputStream();
        
        byteArrayOutput.writeTo(servletOutput);
        servletOutput.flush();
        servletOutput.close();
		*/  
		return mapping.findForward("sucesso");
	}
	
	
	/**
	 * 
	 * @param notaValidadaItem
	 * @return
	 */
	private boolean validarImpostos() {
		
		mensagem = new StringBuilder();
		 
		List<ValidacaoErro> listaValidacaoErro = new ArrayList<ValidacaoErro>();
		 
		 for (NotaValidadaItem notaItemNota :validacao.getNfeInicial().getNotaValidada().getNotasValidadaItem()) {
			 
			 
			 	
				//impostos cadastrados
			 List<ImpostoNcm> listaImpostoNcmCadastrado = ImpostoNcmDao.getInstance().buscarImpostoNcm(notaItemNota.getNcm().getNmNCM(),
					 validacao.getFinalidadeNfe().getCdFinalidadeNfe(), notaItemNota.getNcm().getEmpresa().getCdcnpj());
			 
			 float valorIPINota = 0;
		 	 //IPI
			 if (notaItemNota.getNotasValidadasAliquotas().size() > 3) {
				 
			
			 if (notaItemNota.getNotasValidadasAliquotas().get(3) != null)
				 valorIPINota = notaItemNota.getNotasValidadasAliquotas().get(3).getValorAliquota();
			 
			 }
			 
		 	 float valorIPICadastrado  = 0;
		 	 
		 	 if (valorIPINota != 0) {
		 		  valorIPICadastrado = NumeroFormato.getNumero2digitos((float) (notaItemNota.getNotasValidadasAliquotas().get(3).getValorBCImposto() * (listaImpostoNcmCadastrado.get(3).getNuPercentualImposto()/100)));
		 		 
		 		 if (valorIPINota > valorIPICadastrado || (valorIPINota > valorIPICadastrado + 0.01) ||
		 				(valorIPINota < valorIPICadastrado - 0.01)) {
		 			 ValidacaoErro validacaoErro = new ValidacaoErro();
		 			 validacaoErro.setTxtAuxiliar("NCM do item:" + notaItemNota.getNcm().getNmNCM() + " valor da alíquota da nota: " + valorIPINota +
		 			  " valor alíquota cadastrada: " + valorIPICadastrado + "<br/>" );
		 			 TipoErro tipoErro = new TipoErro();
		 			 validacaoErro.setTpErro(tipoErro);
		 			 //validacaoErro.setTpErro(TipoErroDao.getInstance().getTipoErro(-10));
		 			 listaValidacaoErro.add(validacaoErro);
		 			 
		 			 mensagem.append("<b>Nota recusada.Valor do IPI inconsistente.</b><br/> NCM do item:" + notaItemNota.getNcm().getNmNCM() + " valor da alíquota da nota: " + valorIPINota +
		 			  " valor alíquota cadastrada: " + valorIPICadastrado + "<br/>");
		 		 } else {
		 			 if (valorIPINota < valorIPICadastrado) {
		 			 validacao.getNfeGerada().setNotaComplementar(true);;
		 			 }
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
									notaItemNota.getNcm().getNmNCM(), 35, validacao.getFinalidadeNfe().getCdFinalidadeNfe());
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
		 			  		float valorICMSSTCadastrado = NumeroFormato.getNumero2digitos(BCST * ((float) listaImpostoNcmCadastrado.get(0).getNuPercentualImposto() /100));
		 			  		
		 			  		//BCST * AliqST - Alíquota da própria operacao
		 			  		float icmsST = NumeroFormato.getNumero2digitos(valorICMSSTCadastrado - (BCST * ((float)listaImpostoNcmCadastrado.get(4).getNuPercentualImposto()/100)));
		 			  		
		 			  		float valorAliquotaICMSSTNota = notaItemNota.getNotasValidadasAliquotas().get(0).getValorAliquotaST(); 
		 			  		
		 			  		
		 			  		if (NumeroFormato.getNumero2digitos((icmsST - valorAliquotaICMSSTNota)) < -0.01) {
		 			  		 ValidacaoErro validacaoErro = new ValidacaoErro();
				 			 validacaoErro.setTxtAuxiliar("NCM do item:" + notaItemNota.getNcm().getNmNCM() + " valor da alíquota da nota: " + valorAliquotaICMSSTNota +
		 				 			  " valor cadastrado: " + icmsST + "<br/>" );
				 			 TipoErro tpErro = new TipoErro();
				 			 tpErro.setCdTipoErro(-20);
				 			 validacaoErro.setTpErro(tpErro);
				 			 //validacaoErro.setTpErro(TipoErroDao.getInstance().getTipoErro(-20));
				 			 listaValidacaoErro.add(validacaoErro);
		 			  		 mensagem.append("<b>Nota recusada. Valor do ICMS inconsistente.</b><br/> NCM do item:" + notaItemNota.getNcm().getNmNCM() + " valor da alíquota da nota: " + valorAliquotaICMSSTNota +
		 				 			  " valor cadastrado: " + icmsST + "<br/>");
		 			  		} else {
		 			  			
		 			  			if (icmsST > valorAliquotaICMSSTNota) {
		 			  				validacao.getNfeInicial().setNotaComplementar(true);
		 			  				mensagem.append("<b>Uma nota complementar será necessária.</b>");
		 			  			}
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
		 			  		if (NumeroFormato.getNumero2digitos((valorICMSSTReduzida - valorIcmsNota)) < -0.01) {
		 			  		 ValidacaoErro validacaoErro = new ValidacaoErro();
				 			 validacaoErro.setTxtAuxiliar("NCM do item:" + notaItemNota.getNcm().getNmNCM() + " valor da alíquota da nota: " + icmsSTCadastrado +
	 				 			  " valor cadastrado: " + valorIcmsNota + "<br/>" );
				 			 TipoErro tpErro = new TipoErro();
				 			 tpErro.setCdTipoErro(-30);
				 			 validacaoErro.setTpErro(tpErro);
				 			// validacaoErro.setTpErro(TipoErroDao.getInstance().getTipoErro(-30));
				 			 listaValidacaoErro.add(validacaoErro);
		 			  		 mensagem.append("<b>Nota recusada.Valor do ICMS Interestadual inconsistente.</b><br/> NCM do item:" + notaItemNota.getNcm().getNmNCM() + " valor da alíquota da nota: " + icmsSTCadastrado +
		 				 			  " valor cadastrado: " + valorIcmsNota + "<br/>");
		 			  		} else {
		 			  			if (valorIcmsNota < valorICMSSTReduzida) {
		 			  				validacao.getNfeGerada().setNotaComplementar(true);
		 			  			}
		 			  		}
		 			  		
		 			  	}
		 			  	
		 		  } else {
		 			 float valorBCIcms =  (float) (notaItemNota.getValorBrutoProduto()) - (percentualReducao /100 * notaItemNota.getValorBrutoProduto());
			 		  float valorICMSCadastrado =  NumeroFormato.getNumero2digitos((float) (valorBCIcms * (listaImpostoNcmCadastrado.get(0).getNuPercentualImposto() /100)));
			 		 
			 		  if (NumeroFormato.getNumero2digitos((valorICMSCadastrado - valorIcmsNota)) < -0.01) {
				 			 ValidacaoErro validacaoErro = new ValidacaoErro();
				 			 validacaoErro.setTxtAuxiliar("NCM do item: " +  notaItemNota.getNcm().getNmNCM()  + "  valor ICMS da nota " + valorIcmsNota + " valor da alíquota ICMS cadastrada: " + 
		 		  				valorICMSCadastrado + "<br/>" );
				 			 TipoErro tipoErro = new TipoErro();
				 			 tipoErro.setCdTipoErro(-20);
				 			 validacaoErro.setTpErro(tipoErro);
				 			// validacaoErro.setTpErro(TipoErroDao.getInstance().getTipoErro(-20));
				 			 listaValidacaoErro.add(validacaoErro);
		 			  		
			 			  
		 		  		  mensagem.append("<b>Nota recusada.Valor de ICMS inconsistente.</b><br/> NCM do item: " +  notaItemNota.getNcm().getNmNCM()  + "  valor ICMS da nota " + valorIcmsNota + " valor da alíquota ICMS cadastrada: " + 
		 		  				valorICMSCadastrado + "<br/>");
			 			  
			 		  } else {
			 			  
			 			  if (valorIcmsNota > valorICMSCadastrado) {
			 				  validacao.getNfeInicial().setNotaComplementar(true);
			 			  }
			 		  }
			 	 
		 		  }
		 		  
		 		   
			 	 
			 	
			 	  // percentual da nota para PIS
			 	  float valorPISNota = notaItemNota.getNotasValidadasAliquotas().get(1).getValorAliquota();
			 	  
			 	  if (valorPISNota != 0) {
			 		  
			 		  	float valorPISCadastrado =  NumeroFormato.getNumero2digitos((float) (notaItemNota.getNotasValidadasAliquotas().get(1).getValorBCImposto() * (listaImpostoNcmCadastrado.get(1).getNuPercentualImposto()/100)));
			 		  	
			 		  if (NumeroFormato.getNumero2digitos((valorPISCadastrado - valorPISNota)) < -0.01) {
			 			 ValidacaoErro validacaoErro = new ValidacaoErro();
			 			 validacaoErro.setTxtAuxiliar("<b>Nota recusada.Valor do PIS inconsistente.</b><br/> NCM do item: " + notaItemNota.getNcm().getNmNCM() + " valor da alíquota nota: " + valorPISNota + " valor aliquota cadastrada: " +
			 					 valorPISCadastrado + "<br/>");
			 			 TipoErro tpErro = new TipoErro();
			 			 tpErro.setCdTipoErro(-40);
			 			 validacaoErro.setTpErro(tpErro);
			 			// validacaoErro.setTpErro(TipoErroDao.getInstance().getTipoErro(-40));
			 			 listaValidacaoErro.add(validacaoErro);
			 			  mensagem.append("<b>Nota recusada.Valor do PIS inconsistente.</b><br/> NCM do item: " + notaItemNota.getNcm().getNmNCM() + " valor da alíquota nota: " + valorPISNota + " valor aliquota cadastrada: " +
			 					 valorPISCadastrado + "<br/>");
			 		  } else {
			 			  if (valorPISNota < valorPISCadastrado) {
			 				  validacao.getNfeGerada().setNotaComplementar(true);
			 			  }
			 		  }
			 	  }
			 	  
			 	  
			 	  //COFINS
			 	 float valorCOFINSNota = notaItemNota.getNotasValidadasAliquotas().get(2).getValorAliquota();
			 	 
			 	 if (valorCOFINSNota != 0) {
			 		    float valorCOFINSCadastrado = NumeroFormato.getNumero2digitos((float) (notaItemNota.getValorBrutoProduto() * (listaImpostoNcmCadastrado.get(2).getNuPercentualImposto()/100)));
			 		    
			 		    
			 		 if (NumeroFormato.getNumero2digitos((valorCOFINSNota - valorCOFINSCadastrado)) < -0.01) {
			 			 ValidacaoErro validacaoErro = new ValidacaoErro();
			 			 validacaoErro.setTxtAuxiliar("Ncm do item: " + notaItemNota.getNcm().getNmNCM() + " valor da alíquota da nota: " + valorCOFINSNota + " valor alíquota cadastrada: " +
			 					valorCOFINSCadastrado + "<br/>");
			 			TipoErro tpErro = new TipoErro();
			 			tpErro.setCdTipoErro(-50);
			 			 validacaoErro.setTpErro(tpErro);
			 			 
			 			 //validacaoErro.setTpErro(TipoErroDao.getInstance().getTipoErro(-50));
			 			 listaValidacaoErro.add(validacaoErro);
			 			 mensagem.append("<b>Nota recusada.Valor do COFINS inconsistente.</b><br/> Ncm do item: " + notaItemNota.getNcm().getNmNCM() + " valor da alíquota da nota: " + valorCOFINSNota + " valor alíquota cadastrada: " +
			 					valorCOFINSCadastrado + "<br/>");
			 		 } else {
			 			 if (valorCOFINSNota < valorCOFINSCadastrado) {
			 				 validacao.getNfeInicial().setNotaComplementar(true);
			 			 }
			 		 }
			 	 }
			 	
			 	
			 	
		 }	 
			 	
			 	validacao.setValidacaoErro(listaValidacaoErro);
			 	
			  	 //todo: levantar como validar os valores dos impostos como ST(Substituicao Tributária)
			 	 
			 	  if (!getMensagem().toString().equals("") && !validacao.getNfeInicial().isNotaComplementar()) {
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
