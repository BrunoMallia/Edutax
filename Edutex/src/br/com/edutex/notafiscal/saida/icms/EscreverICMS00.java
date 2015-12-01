package br.com.edutex.notafiscal.saida.icms;

import java.util.Locale;

import org.jdom2.Element;

import br.com.edutex.logic.NotaValidadaAliquota;
import br.com.edutex.notafiscal.interfaces.EscreverTributacao;
import br.com.edutex.notafiscal.util.NotaFiscalUtil;
import br.com.edutex.util.NumeroFormato;

public class EscreverICMS00 implements EscreverTributacao {

	/* 
	 * Este método escreve os atributos para o ICMS quando o CST é igual a 0.
	 * 
	 * @see br.com.edutex.notafiscal.EscreveICMS#escreverICMSNota(org.jdom2.Element, br.com.edutex.logic.NotaValidadaAliquota)
	 */
	public Element escreverTributacaoNota(Element element,
			NotaValidadaAliquota notaValidadaAliquota) {
		
		Element elementICMS = new Element("ICMS"+ notaValidadaAliquota.getCst().getNmCST(), NotaFiscalUtil.getNameSpace());
		
	    Element origem = new Element("orig",NotaFiscalUtil.getNameSpace());
	    origem.setText(String.valueOf(notaValidadaAliquota.getOrigem()));
	    
	    Element cst = new Element("CST",NotaFiscalUtil.getNameSpace());
	    cst.setText(notaValidadaAliquota.getCst().getNmCST());
	    
	    Element  modBC = new Element("modBC", NotaFiscalUtil.getNameSpace());
	    modBC.setText( String.valueOf(notaValidadaAliquota.getModBCImposto()));
	    
	    Element valorBC = new Element("vBC",NotaFiscalUtil.getNameSpace());
	    valorBC.setText(String.format(Locale.US,"%.2f",notaValidadaAliquota.getValorBCImposto()));
		
	    Element percentualImposto = new Element("pICMS",NotaFiscalUtil.getNameSpace());
	    percentualImposto.setText(String.format(Locale.US,"%.2f",notaValidadaAliquota.getPercentualAliquota()));
	    
	    Element valorImposto = new Element("vICMS",NotaFiscalUtil.getNameSpace());
	    valorImposto.setText(String.format(Locale.US,"%.2f",notaValidadaAliquota.getValorAliquota()));
	    
	    element.removeContent();
	    elementICMS.addContent(origem);
	    elementICMS.addContent(cst);
	    elementICMS.addContent(modBC);
	    elementICMS.addContent(valorBC);
	    elementICMS.addContent(percentualImposto);
	    elementICMS.addContent(valorImposto);
	    element.addContent(elementICMS);
	    
	    return element;
	}

}
