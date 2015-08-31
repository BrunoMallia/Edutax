package br.com.edutex.notafiscal.saida.icms;

import org.jdom2.Element;

import br.com.edutex.logic.NotaValidadaAliquota;
import br.com.edutex.notafiscal.interfaces.EscreverTributacao;
import br.com.edutex.notafiscal.util.NotaFiscalUtil;

public class EscreverICMS60 implements EscreverTributacao {

	/* 
	 * Este método escreve os atributos para o ICMS quando o CST é igual a 60.
	 * 
	 * @see br.com.edutex.notafiscal.EscreveICMS#escreverICMSNota(org.jdom2.Element, br.com.edutex.logic.NotaValidadaAliquota)
	 */
	public Element escreverTributacaoNota(Element element,
			NotaValidadaAliquota notaValidadaAliquota) {
	
		
		Element elementICMS = new Element("ICMS"+ notaValidadaAliquota.getCst().getNmCST(),NotaFiscalUtil.getNameSpace());
       
	    Element origem = new Element("Origem",NotaFiscalUtil.getNameSpace());
	    origem.setText(String.valueOf(notaValidadaAliquota.getOrigem()));
	    
	    Element cst = new Element("CST",NotaFiscalUtil.getNameSpace());
	    cst.setText(notaValidadaAliquota.getCst().getNmCST());
	   
		Element vBCSRet = new Element("vBCSRet",NotaFiscalUtil.getNameSpace());
		vBCSRet.setText(String.valueOf(notaValidadaAliquota.getValorBCSTRetidoAnteriormente()));
	    
		Element vICMSRet = new Element("vICMSSTRet",NotaFiscalUtil.getNameSpace());
		vICMSRet.setText(String.valueOf(notaValidadaAliquota.getValorImpostoSTRetidoAnteriormente()));
		
		
	    element.removeContent();
	    elementICMS.addContent(origem);
	    elementICMS.addContent(cst);
	    elementICMS.addContent(vBCSRet);
	    elementICMS.addContent(vICMSRet);
	    
	    element.addContent(elementICMS);
	    
	    return element;
	}

}
