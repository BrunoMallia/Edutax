package br.com.edutex.notafiscal.util;

import java.text.SimpleDateFormat;
import java.util.Date;

import br.com.edutex.logic.NFE;
import br.com.edutex.util.ConfiguracaoProp;

public class NotaFiscalUtil {
	
	
	private static String NAMESPACE_NOTA_FISCAL;
	
	/**
	 * O nome lógico da arquivo do da nota ,que será salvo num diretório parametrizado no servidor, 
	 *  é uma composição do campos cNF(número da nota) o nome do fornecedor (emit-Xname) 
	 *  e data(dd/MM/yyyy:hh:mm) e o finNFe (finalidade da emissão da nota).
	 * @param nfe
	 * @return
	 */
	public static String getNomeArquivo(NFE nfe) {
		
		StringBuilder nomeArquivo = new StringBuilder();
		
		

		SimpleDateFormat dt = new SimpleDateFormat("yyyymmddhhmm");
		Date data = new Date();
		setNamesSpace(ConfiguracaoProp.getPropriedades().get("notafiscal.diretorio").toString());
		nomeArquivo.append(NAMESPACE_NOTA_FISCAL + "\\");
		nomeArquivo.append(nfe.getCdNfe());
		nomeArquivo.append(nfe.getNotaValidada().getNmNFornecedor());
		nomeArquivo.append(dt.format(data));
		nomeArquivo.append(nfe.getNotaValidada().getFinNfe());
		nomeArquivo.append(".xml");
		
		return nomeArquivo.toString();
		
	}
	
	
	public static String getNomeLogico(NFE nfe) {
		
		StringBuilder nomeArquivo = new StringBuilder();
		
		

		SimpleDateFormat dt = new SimpleDateFormat("yyyymmddhhmm");
		Date data = new Date();
		nomeArquivo.append(nfe.getCdNfe());
		nomeArquivo.append(nfe.getNotaValidada().getNmNFornecedor());
		nomeArquivo.append(dt.format(data));
		nomeArquivo.append(nfe.getNotaValidada().getFinNfe());
		nomeArquivo.append(".xml");
		
		return nomeArquivo.toString();
		
	}
	
	/**
	 * Este método seta o NAMESPACE da nota fiscal
	 * @param nameSpace
	 */
	public static void setNamesSpace(String nameSpace){
		NAMESPACE_NOTA_FISCAL = nameSpace;
	}
	
	/**
	 * Este método recupera o valor do NAMESPACE da nota fiscal
	 * @return
	 */
	public static String getNameSpace(){
		return NAMESPACE_NOTA_FISCAL;
	}
	
}
