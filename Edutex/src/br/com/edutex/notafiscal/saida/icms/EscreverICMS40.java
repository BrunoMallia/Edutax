package br.com.edutex.notafiscal.saida.icms;

import java.util.Locale;

import org.jdom2.Element;

import br.com.edutex.logic.NotaValidadaAliquota;
import br.com.edutex.notafiscal.interfaces.EscreverTributacao;
import br.com.edutex.notafiscal.util.NotaFiscalUtil;

public class EscreverICMS40 implements EscreverTributacao {

	/* 
	 * Este método escreve os atributos para o ICMS quando o CST é igual a 30.
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
	   
	   Element pICMSST = new Element("pICMSST",NotaFiscalUtil.getNameSpace());
	   pICMSST.setText(String.format(Locale.US,"%.2f",notaValidadaAliquota.getPercentualAliquotaST()));
	   
	   Element vICMSST = new Element("vICMSST",NotaFiscalUtil.getNameSpace());
	   vICMSST.setText(String.format(Locale.US,"%.2f",notaValidadaAliquota.getValorAliquotaST()));
	   
	   Element vICMSDeson = new Element("vICMSDeson",NotaFiscalUtil.getNameSpace());
	   vICMSDeson.setText(String.format(Locale.US,"%.2f",notaValidadaAliquota.getValorAliquotaDesoneracao()));
	   
	   Element motDesICMS = new Element("motDesICMS",NotaFiscalUtil.getNameSpace());
	   motDesICMS.setText(String.valueOf(notaValidadaAliquota.getMotivoDesoneracaoImposto()));
	 
	    element.removeContent();
	    elementICMS.addContent(origem);
	    elementICMS.addContent(cst);
	    elementICMS.addContent(pICMSST);
	    elementICMS.addContent(vICMSST);
	    elementICMS.addContent(vICMSDeson);
	    elementICMS.addContent(motDesICMS);
	    element.addContent(elementICMS);
	    
	    return element;
	}

}
