package br.com.edutex.notafiscal;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.List;

import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;
import org.jdom2.output.Format;
import org.jdom2.output.XMLOutputter;

import br.com.edutex.exceptions.NotaInvalidaException;
import br.com.edutex.logic.NCM;
import br.com.edutex.logic.NFE;
import br.com.edutex.logic.NotaValidada;
import br.com.edutex.logic.NotaValidadaAliquota;
import br.com.edutex.logic.NotaValidadaItem;
import br.com.edutex.notafiscal.entrada.cofins.LerCOFINSST;
import br.com.edutex.notafiscal.entrada.icms.LerICMSTotal;
import br.com.edutex.notafiscal.entrada.pis.LerPISST;
import br.com.edutex.notafiscal.saida.cofins.EscreverCOFINSST;
import br.com.edutex.notafiscal.saida.icms.EscreverICMSTotal;
import br.com.edutex.notafiscal.saida.pis.EscreverPISST;
import br.com.edutex.notafiscal.util.NotaFiscalUtil;
  

public class NotaFiscal {
	
	private NotaFiscalAliquota notaFiscalAliquota = new NotaFiscalAliquota();
	
	private NFE nfe;
	

	
	/**
	 * Este método esreve as tributações na nota fiscal
	 * @param nfe
	 * @throws NotaInvalidaException
	 * @throws NumberFormatException
	 * @throws IOException
	 */
	public OutputStream escreverXML(NFE nfe) throws NotaInvalidaException, NumberFormatException, IOException {

		SAXBuilder builder = new SAXBuilder();
		builder.setIgnoringBoundaryWhitespace(true);
		builder.setIgnoringElementContentWhitespace(true);
		File arquivo = new File(nfe.getNmFilePath());
		  OutputStream outPutStream = null;
		
		if (arquivo.isFile()) {
			try {
				Document document = (Document) builder.build(arquivo);
				Element root = document.getRootElement();
				NotaFiscalUtil.setNamesSpace(root.getNamespace().getURI());
				Element node = root.getChild("NFe",root.getNamespace());
				NotaValidada nota = nfe.getNotaValidada();
				List<NotaValidadaAliquota> notasAliquotas = null;
				
				if (node == null) {
					throw new NullPointerException("Elemento nfe da nota fiscal eletrônica inexistente");
				}

				node = node.getChild("infNFe", root.getNamespace());

				if (node == null) {
					throw new NullPointerException("Elemento infNFe (Informações da Nota Fiscal eletrônica) inexistente");
				}

				Element nodeIde = null;

				for (Element element : node.getChildren()) {
					if (element.getName().equals("ide")) {
						nodeIde = element;
						break;
					}

				}

				if (nodeIde == null) {
					 throw new NullPointerException("Elemento ide (Identificação da nota fiscal) inexistente");
				}

				Element nodecUF = null, nodecNF = null, nodeFinNFe = null, nodeIndFinal = null;

				for (Element ele : nodeIde.getChildren()) {

					switch (ele.getName()) {

					case "cUF":
						nodecUF = ele;
						break;
					case "cNF":
						nodecNF = ele;
						break;
					case "finNFe":
						nodeFinNFe = ele;
						break;
					case "indFinal":
						nodeIndFinal = ele;
						break;
					default:
						break;
					}

				}
				
				
				nodecUF.setText(String.valueOf(nota.getcUF()));
				nodecNF.setText(nfe.getNmNfe());
				nodeFinNFe.setText(String.valueOf(nota.getFinNfe()));
				// só existe node indFinal na versão 3.10 da nota fiscal eletrônica
				//nodeIndFinal.setText(String.valueOf(nota.getIndFinal()));
				
				Element nodeEmit = null;

				for (Element ele : node.getChildren()) {
					if (ele.getName().equals("emit"))
						nodeEmit = ele;

				}

				if (nodeEmit == null) {
					throw new NullPointerException("Nota inválida: elemento emit (identicação do emissor inexistente"); 
				}

				Element nodeXNome = null, nodeCRT = null;

				for (Element element : nodeEmit.getChildren()) {
					switch (element.getName()) {

					case "xNome":
						nodeXNome = element;
						break;
					case "CRT":
						nodeCRT = element;
						break;
					default:
						break;
					}
				}
				
				nodeXNome.setText(String.valueOf(nota.getNmNFornecedor()));
				nodeCRT.setText(String.valueOf(nota.getCRT()));
				
				for (Element ele : node.getChildren()) {
				  NotaValidadaItem notaItem = null;
					
				 if (ele.getName().equals("det")) {
					 
						Element prod = ele.getChild("prod",
								root.getNamespace());

						// ELEMENTO PROD para pegar NCM
						for (Element element : prod.getChildren()) {
							int count  = 0;
							 notaItem = nota.getNotasValidadaItem().get(count);
				
								
							switch (element.getName()) {
								
								case "NCM":
									element.setText(String.valueOf(notaItem.getNcm().getNmNCM()));
									break;
								
								case "CFOP":
									element.setText(String.valueOf(notaItem.getCFOP()));
									break;
									
								case "vProd":	
									element.setText(String.valueOf(notaItem.getValorBrutoProduto()));
									break;
								
							}

						}
						
						notasAliquotas = notaItem.getNotasValidadasAliquotas();
						
						Collections.sort(notasAliquotas);
						
				Element nodeICMS = null, nodePIS = null, nodeCOFINS = null, nodeIPI = null, nodePISST = null, nodeCOFINSST = null;
						
						
				Element imposto = ele.getChild("imposto",
						root.getNamespace());
						
						
					if (imposto != null) {
						
						for (Element element : imposto.getChildren()) {

							switch (element.getName()) {

							case "ICMS":
								nodeICMS = element;
								break; 	
							case "PIS":
								nodePIS = element;
								break;
							case "COFINS":
								nodeCOFINS = element;
								break;
							case "IPI":
								nodeIPI = element;
								break;
							case "PISST":
								nodePISST = element;
								break;
							case "COFINSST":
								nodeCOFINSST = element;
								break;
								
							}

						}
						
						NotaFiscalAliquota notaFiscalAliquota = new NotaFiscalAliquota();
						
						for (NotaValidadaAliquota notaValidadaAliquota: notasAliquotas) {
							
							switch (notaValidadaAliquota.getTipoImpostoAliquota().getCdTipoImposto()) {
								
							case 1:
								nodeICMS = notaFiscalAliquota.escolherEscritaTributacaoICMS(nodeICMS, notaValidadaAliquota);
								break;
							case 2:
								nodePIS = notaFiscalAliquota.escolherEscritaTributacaoPIS(nodePIS, notaValidadaAliquota);
								break;
							case 3:
								nodeCOFINS = notaFiscalAliquota.escolherEscritaTributacaoCOFINS(nodeCOFINS, notaValidadaAliquota);
								break;
							case 4:	
								nodeIPI = notaFiscalAliquota.escolherEscritaTributacaoIPI(nodeIPI, notaValidadaAliquota);
								break;
							case 5:
								notaFiscalAliquota.setTipoEscrita(new EscreverCOFINSST());
								nodeCOFINSST = notaFiscalAliquota.escreveTributacao.escreverTributacaoNota(nodeCOFINSST, notaValidadaAliquota);
								break;
							case 6:
								notaFiscalAliquota.setTipoEscrita(new EscreverPISST());
								nodePIS = notaFiscalAliquota.escreveTributacao.escreverTributacaoNota(nodePISST, notaValidadaAliquota);
								break;
								
							
							}
						}
						
					
						

						
					}	
					
					
					
				 }	
				 
				 
				 if (ele.getName().equals("total")) {
					
					 
					 Element icmsTotal = ele.getChild("ICMSTot",
								root.getNamespace());
					 
					 
					 if (icmsTotal != null) {
						 
						NotaFiscalAliquota notaFiscalAliquota = new NotaFiscalAliquota();
						notaFiscalAliquota.setEscreverTributacaoTotal(new EscreverICMSTotal());
						notaFiscalAliquota.getEscreverTributacaoTotal().escreverTributacaoTotalNota(nfe.getNotaValidada().getNotasValidadaItem(),ele);
						 
					 }
						 
					
				 }
				 
			}	 
				 
					XMLOutputter xmlOutput = new XMLOutputter();
	
				   xmlOutput.setFormat(Format.getPrettyFormat());
								
				    
				  String nomeArquivo = NotaFiscalUtil.getNomeArquivo(nfe);
				  nfe.setNmFilePath(nomeArquivo);
				  nfe.setDtUpload(Calendar.getInstance());
				  nfe.setNmNfe(NotaFiscalUtil.getNomeLogico(nfe));
				  xmlOutput.output(document, new FileWriter(nomeArquivo));
				  outPutStream = new ByteArrayOutputStream();
				  
				  xmlOutput.output(document, outPutStream);
				  
				  return outPutStream;
					
				 
				 
			

				} catch (JDOMException jdomex) {
				throw new NotaInvalidaException("Nota inválida" + jdomex.getMessage());
			} 

		} else {
			throw new IllegalArgumentException("Arquivo da nota fiscal não encontrado");
		}
	

	}
	
	
	/**
	 * @param nfe
	 * @return
	 * @throws NotaInvalidaException
	 * @throws NumberFormatException
	 * @throws IOException
	 */
	public NFE lerXML(File arquivo) throws NotaInvalidaException, NumberFormatException, IOException {
		
		SAXBuilder builder = new SAXBuilder();
		NotaValidada nota = new NotaValidada();
		nfe = new NFE();
		nfe.setNmFilePath(arquivo.getAbsolutePath());
		
		if (arquivo.isFile()) {
			try {
				Document document = (Document) builder.build(arquivo);
				Element root = document.getRootElement();
				Element node = root.getChild("NFe", root.getNamespace());

				if (node == null) {
					throw new NullPointerException("Elemento nfe da nota fisccal eletrônica inexistente");
				}

				node = node.getChild("infNFe", root.getNamespace());

				if (node == null) {
					throw new NullPointerException("Elemento infNFe (Informações da Nota Fiscal eletrônica) inexistente");
				}

				Element nodeIde = null;

				for (Element element : node.getChildren()) {
					if (element.getName().equals("ide")) {
						nodeIde = element;
						break;
					}

				}

				if (nodeIde == null) {
					 throw new NullPointerException("Elemento ide (Identificação da nota fiscal) inexistente");
				}

				Element nodecUF = null, nodecNF = null, nodeFinNFe = null, nodeIndFinal = null;

				for (Element ele : nodeIde.getChildren()) {

					switch (ele.getName()) {

					case "cUF":
						nodecUF = ele;
						if (nodecUF == null) {
							throw new NullPointerException("Elemento cUF (Código da UF do emitente do Documento Fiscal) inexistente");
						}
							

						break;
					case "cNF":
						nodecNF = ele;
						if (nodecNF == null) {
							throw new NullPointerException("Elemento cNF (Código da UF do emitente do Documento Fiscal) inexistente");
						}
						break;
					case "finNFe":
						nodeFinNFe = ele;
						if (nodeFinNFe == null) {
							throw new NullPointerException("Nota Inválida Elemento finNFe (Finalidade da emissão da NF-e) inexistente");

						}
						
					case "indFinal":	
						nodeIndFinal = ele;
						if (nodeIndFinal == null) {
							throw new NullPointerException("Nota Inválida Elemento indFinal (Indica operação com consumidor final) inexistente");
						}

					default:
						break;
					}

				}
				
				
				nota.setcUF(Integer.parseInt(nodecUF.getValue()));
				nfe.setNmNfe(nodecNF.getValue());
				nota.setFinNfe(Integer.parseInt(nodeFinNFe.getValue()));
				nota.setIndFinal(Integer.parseInt(nodeIndFinal.getValue()));

				Element nodeEmit = null;

				for (Element ele : node.getChildren()) {
					if (ele.getName().equals("emit"))
						nodeEmit = ele;

				}

				if (nodeEmit == null) {
					throw new NullPointerException("Nota inválida: elemento emit (identicação do emissor inexistente"); 
				}

				Element nodeXNome = null, nodeCRT = null;

				for (Element element : nodeEmit.getChildren()) {
					switch (element.getName()) {

					case "xNome":
						nodeXNome = element;
						if (nodeXNome == null) {
							throw new NullPointerException("Nota inválida: Elemento xNome (Razão Social ou Nome do emitente) inexistente");

						}
						break;
					case "CRT":
						nodeCRT = element;
						if (nodeCRT == null) {
							throw new NullPointerException("Nota Inválida: Elemento CRT (Código do Regime Trbituário) inexistente");

						}
						break;
					default:
						break;
					}
				}
				
				nota.setNmNFornecedor(nodeXNome.getValue());
				nota.setCRT(Integer.parseInt(nodeCRT.getValue()));
				
				Element nodeICMS = null, nodePIS = null, nodeCOFINS = null, nodeIPI = null, nodePISST = null, nodeCOFINSST = null;
				
				List<NotaValidadaItem> notasValidadaItem = new ArrayList<NotaValidadaItem>();
				
				for (Element ele : node.getChildren()) {
					
					NotaValidadaItem notaItem = new NotaValidadaItem();
				
					
				 if (ele.getName().equals("det")) {
					 
						Element prod = ele.getChild("prod",
								root.getNamespace());

						// ELEMENTO PROD para pegar NCM
						for (Element element : prod.getChildren()) {
							
							
							switch (element.getName()) {
							
								case "NCM":
									NCM ncm = new NCM();
									ncm.setNmNCM(element.getValue());
									notaItem.setNcm(ncm);
									break;
								
								case "CFOP":
									notaItem.setCFOP(Integer.parseInt(element.getValue()));
									break;
									
								case "vProd":	
									notaItem.setValorBrutoProduto(Float.parseFloat(element.getValue()));
									break;
								
							}

						}
						
						List<NotaValidadaAliquota> notasAliquotas = new ArrayList<NotaValidadaAliquota>();
					
							Element imposto = ele.getChild("imposto",
						root.getNamespace());
						
					if (imposto != null) {

						for (Element element : imposto.getChildren()) {

							switch (element.getName()) {

							case "ICMS":
								nodeICMS = element;
								break; 	
							case "PIS":
								nodePIS = element;
								break;
							case "COFINS":
								nodeCOFINS = element;
								break;
							case "IPI":
								nodeIPI = element;
								break;
							case "PISST":
								nodePISST = element;
								break;
							case "COFINSST":
								nodeCOFINSST = element;
								break;
								
							}

						}
						
						
				NotaValidadaAliquota notaAliquotaICMS = notaFiscalAliquota.escolherLeituraTributacaoICMS(nodeICMS);
				
				if (notaAliquotaICMS != null) {
					notasAliquotas.add(notaAliquotaICMS);
				}
				
			
				NotaValidadaAliquota notaAliquotaPIS = notaFiscalAliquota.escolherLeituraTributacaoPIS(nodePIS); 
			
				if (notaAliquotaPIS != null)
					notasAliquotas.add(notaAliquotaPIS);
				
				
				if (nodePISST != null) {
					notaFiscalAliquota.setTipoLeitura(new LerPISST());
					 NotaValidadaAliquota notaValidada = notaFiscalAliquota.tentarLer(nodePISST); 
					if(notaValidada != null)
						 notasAliquotas.add(notaValidada); 
				}
						
				NotaValidadaAliquota notaValidadaCOFINS = notaFiscalAliquota.escolherLeituraTributacaoCOFINS(nodeCOFINS);
				
				if (notaValidadaCOFINS != null) {
						notasAliquotas.add(notaValidadaCOFINS);
				}

				
				if (nodeCOFINSST != null) {
						notaFiscalAliquota.setTipoLeitura(new LerCOFINSST());
						notasAliquotas.add(notaFiscalAliquota.tentarLer(nodeCOFINSST));
					}
						
			
				if (nodeIPI != null) {
						  notasAliquotas.add(notaFiscalAliquota.escolherLeituraTributacaoIPI(nodeIPI));
				}
				
						notaItem.setNotasValidadasAliquotas(notasAliquotas);
						notasValidadaItem.add(notaItem);
						
						
					}
					
					nota.setNotasValidadaItem(notasValidadaItem);
					nfe.setNotaValidada(nota);
					
					
				 }	
				 
					//pegar o total
					if (ele.getName().equals("total")) {

						for (Element elementTotal : ele.getChildren()) {

							switch (elementTotal.getName()) {

							case "ICMSTot":
								LerICMSTotal lerICMSTotal = new LerICMSTotal();
								nota = lerICMSTotal.lerICMSTotal(elementTotal, nota);
								break;

							}

						}

					}

				}

			}  catch (JDOMException jdomex) {
				throw new NotaInvalidaException("Nota inválida:" + jdomex.getMessage());
			} 

		} else {
			throw new IllegalArgumentException("Arquivo da nota fiscal não encontrado");
		}
		
		return nfe;

	}
	

	
}
