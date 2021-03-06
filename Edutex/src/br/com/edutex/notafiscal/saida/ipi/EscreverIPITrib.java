package br.com.edutex.notafiscal.saida.ipi;

import java.util.Locale;

import org.jdom2.Element;

import br.com.edutex.logic.NotaValidadaAliquota;
import br.com.edutex.notafiscal.interfaces.EscreverTributacao;
import br.com.edutex.notafiscal.util.NotaFiscalUtil;

public class EscreverIPITrib implements EscreverTributacao {

	
	/* 
	 * M�todo que escrever a tributa��o do IPI para a condi��o cst 00,49,50,99 
	 * @see br.com.edutex.notafiscal.interfaces.EscreverTributacao#escreverTributacaoNota(org.jdom2.Element, br.com.edutex.logic.NotaValidadaAliquota)
	 */
	public Element escreverTributacaoNota(Element element,
			NotaValidadaAliquota notaValidadaAliquota) {
		
		
		for (Element childrenElem: element.getChildren()) {
			if (childrenElem.getName().equals("IPITrib")) 
				element = childrenElem;
		}
		
		
		element.removeContent();
		
		
		Element cst = new Element("CST", NotaFiscalUtil.getNameSpace());
		cst.setText(notaValidadaAliquota.getCst().getNmCST());
		
		Element vBC = new Element("vBC", NotaFiscalUtil.getNameSpace());
		vBC.setText(String.format(Locale.US,"%.2f",notaValidadaAliquota.getValorBCImposto()));
		
		Element pIPI = new Element("pIPI", NotaFiscalUtil.getNameSpace());
		pIPI.setText(String.format(Locale.US,"%.2f",notaValidadaAliquota.getPercentualAliquota()));
	
		Element qUnid = new Element("qUnid", NotaFiscalUtil.getNameSpace());
		qUnid.setText(String.valueOf(notaValidadaAliquota.getQuantidadeUnidadePadrao()));
		
		Element vUnid = new Element("vUnid", NotaFiscalUtil.getNameSpace());
		vUnid.setText(String.format(Locale.US,"%.2f",notaValidadaAliquota.getValorUnidadeTributavel()));
		
		Element vIPI = new Element("vIPI", NotaFiscalUtil.getNameSpace());
		vIPI.setText(String.format(Locale.US,"%.2f",notaValidadaAliquota.getValorAliquota()));
		
	
		element.addContent(cst);
		element.addContent(qUnid);
		element.addContent(vUnid);
		element.addContent(vIPI);
		
		return element;
	}

}
