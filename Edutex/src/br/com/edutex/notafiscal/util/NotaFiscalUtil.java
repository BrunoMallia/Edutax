package br.com.edutex.notafiscal.util;

import java.text.SimpleDateFormat;
import java.util.Date;

import br.com.edutex.logic.NFE;
import br.com.edutex.util.ConfiguracaoProp;

public class NotaFiscalUtil {
	
	
	private static String NAMESPACE_NOTA_FISCAL;
	
	/**
	 * O nome l�gico da arquivo do da nota ,que ser� salvo num diret�rio parametrizado no servidor, 
	 *  � uma composi��o do campos cNF(n�mero da nota) o nome do fornecedor (emit-Xname) 
	 *  e data(dd/MM/yyyy:hh:mm) e o finNFe (finalidade da emiss�o da nota).
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
	 * Este m�todo seta o NAMESPACE da nota fiscal
	 * @param nameSpace
	 */
	public static void setNamesSpace(String nameSpace){
		NAMESPACE_NOTA_FISCAL = nameSpace;
	}
	
	/**
	 * Este m�todo recupera o valor do NAMESPACE da nota fiscal
	 * @return
	 */
	public static String getNameSpace(){
		return NAMESPACE_NOTA_FISCAL;
	}
	
}
