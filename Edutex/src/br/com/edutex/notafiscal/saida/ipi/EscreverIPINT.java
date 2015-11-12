package br.com.edutex.notafiscal.saida.ipi;

import org.jdom2.Element;

import br.com.edutex.logic.NotaValidadaAliquota;
import br.com.edutex.notafiscal.interfaces.EscreverTributacao;
import br.com.edutex.notafiscal.util.NotaFiscalUtil;

public class EscreverIPINT implements EscreverTributacao {

	/* (non-Javadoc)
	 * @see br.com.edutex.notafiscal.interfaces.EscreverTributacao#escreverTributacaoNota(org.jdom2.Element, br.com.edutex.logic.NotaValidadaAliquota)
	 */
	public Element escreverTributacaoNota(Element element,
			NotaValidadaAliquota notaValidadaAliquota) {
		
		Element elementCEnq = new Element("cEnq", NotaFiscalUtil.getNameSpace());
		elementCEnq.setText(String.valueOf(notaValidadaAliquota.getCodigoEnquadramento()));
		Element elementoIPINT = new Element("IPINT", NotaFiscalUtil.getNameSpace());
		
		Element cst = new Element("CST", NotaFiscalUtil.getNameSpace());
		cst.setText(notaValidadaAliquota.getCst().getNmCST());
		
		
		elementoIPINT.addContent(cst);
		
		element.removeContent();
		element.addContent(elementCEnq);
		element.addContent(elementoIPINT);
		
		return element;
	}

}
