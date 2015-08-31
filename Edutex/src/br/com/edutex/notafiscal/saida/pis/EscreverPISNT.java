package br.com.edutex.notafiscal.saida.pis;

import org.jdom2.Element;

import br.com.edutex.logic.NotaValidadaAliquota;
import br.com.edutex.notafiscal.interfaces.EscreverTributacao;
import br.com.edutex.notafiscal.util.NotaFiscalUtil;

public class EscreverPISNT implements EscreverTributacao {

	public Element escreverTributacaoNota(Element element,
			NotaValidadaAliquota notaValidadaAliquota) {
		
	 Element elementoPIS = new Element("PISST",NotaFiscalUtil.getNameSpace());
	 
	 Element cst = new Element("CST",NotaFiscalUtil.getNameSpace());
	 cst.addContent(String.valueOf(notaValidadaAliquota.getCst().getNmCST()));
	 
	 element.removeContent();

	 elementoPIS.addContent(cst);

	 element.addContent(elementoPIS);
	 
	return element;
	
	}

}
