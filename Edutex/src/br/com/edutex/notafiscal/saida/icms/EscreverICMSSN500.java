package br.com.edutex.notafiscal.saida.icms;

import java.util.Locale;

import org.jdom2.Element;

import br.com.edutex.logic.NotaValidadaAliquota;
import br.com.edutex.notafiscal.interfaces.EscreverTributacao;
import br.com.edutex.notafiscal.util.NotaFiscalUtil;

public class EscreverICMSSN500 implements EscreverTributacao {

	/* 
	 * Este método escreve as tributação ICMS para simples nacional 500
	 * @see br.com.edutex.notafiscal.EscreveICMS#escreverICMSNota(org.jdom2.Element, br.com.edutex.logic.NotaValidadaAliquota)
	 */
	public Element escreverTributacaoNota(Element element,
			NotaValidadaAliquota notaValidadaAliquota) {

		
	Element elementICMS = new Element("ICMSSN"+ notaValidadaAliquota.getCsosn(),NotaFiscalUtil.getNameSpace());

    Element origem = new Element("orig",NotaFiscalUtil.getNameSpace());
    origem.setText(String.valueOf(notaValidadaAliquota.getOrigem()));
    
    Element CSOSN = new Element("CSOSN",NotaFiscalUtil.getNameSpace());
	CSOSN.setText(String.valueOf(notaValidadaAliquota.getCsosn()));
	
	Element vBCSTRet = new Element("vBCSTRet",NotaFiscalUtil.getNameSpace());
	vBCSTRet.setText(String.format(Locale.US,"%.2f",notaValidadaAliquota.getValorBCSTRetidoAnteriormente()));
	
	Element vICMSSTRet = new Element("vICMSSTRet",NotaFiscalUtil.getNameSpace());
	vICMSSTRet.setText(String.format(Locale.US,"%.2f",notaValidadaAliquota.getValorImpostoSTRetidoAnteriormente()));
	
	elementICMS.removeContent();
	elementICMS.addContent(origem);
	elementICMS.addContent(CSOSN);
	elementICMS.addContent(vBCSTRet);
	elementICMS.addContent(vICMSSTRet);
	element.addContent(elementICMS);
	
	return element;
	
	}

}
