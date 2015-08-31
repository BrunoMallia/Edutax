package br.com.edutex.notafiscal.saida.ipi;

import org.jdom2.Element;

import br.com.edutex.logic.NotaValidadaAliquota;
import br.com.edutex.notafiscal.interfaces.EscreverTributacao;
import br.com.edutex.notafiscal.util.NotaFiscalUtil;

public class EscreverIPITrib implements EscreverTributacao {

	
	/* 
	 * Método que escrever a tributação do IPI para a condição cst 00,49,50,99 
	 * @see br.com.edutex.notafiscal.interfaces.EscreverTributacao#escreverTributacaoNota(org.jdom2.Element, br.com.edutex.logic.NotaValidadaAliquota)
	 */
	public Element escreverTributacaoNota(Element elemento,
			NotaValidadaAliquota notaValidadaAliquota) {
		
		Element elementoIPI = new Element("IPITrib", NotaFiscalUtil.getNameSpace());
		
		Element cst = new Element("CST", NotaFiscalUtil.getNameSpace());
		cst.setText(notaValidadaAliquota.getCst().getNmCST());
		
		Element vBC = new Element("vBC", NotaFiscalUtil.getNameSpace());
		vBC.setText(String.valueOf(notaValidadaAliquota.getValorBCImposto()));
		
		Element pIPI = new Element("pIPI", NotaFiscalUtil.getNameSpace());
		pIPI.setText(String.valueOf(notaValidadaAliquota.getPercentualAliquota()));
	
		Element qUnid = new Element("qUnid", NotaFiscalUtil.getNameSpace());
		qUnid.setText(String.valueOf(notaValidadaAliquota.getQuantidadeUnidadePadrao()));
		
		Element vUnid = new Element("vUnid", NotaFiscalUtil.getNameSpace());
		vUnid.setText(String.valueOf(notaValidadaAliquota.getValorUnidadeTributavel()));
		
		Element vIPI = new Element("vIPI", NotaFiscalUtil.getNameSpace());
		vIPI.setText(String.valueOf(notaValidadaAliquota.getValorAliquota()));
		
		elemento.removeContent();
		elementoIPI.addContent(cst);
		elementoIPI.addContent(qUnid);
		elementoIPI.addContent(vUnid);
		elementoIPI.addContent(vIPI);
		elemento.addContent(elementoIPI);
		
		
		return elemento;
	}

}
