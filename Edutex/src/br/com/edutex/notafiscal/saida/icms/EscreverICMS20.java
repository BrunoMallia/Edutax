package br.com.edutex.notafiscal.saida.icms;

import org.jdom2.Element;

import br.com.edutex.logic.NotaValidadaAliquota;
import br.com.edutex.notafiscal.interfaces.EscreverTributacao;
import br.com.edutex.notafiscal.util.NotaFiscalUtil;

public class EscreverICMS20 implements EscreverTributacao {

	/* 
	 * Este método escreve os atributos para o ICMS quando o CST é igual a 20.
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
	    
	    Element  modBC = new Element("modBC",NotaFiscalUtil.getNameSpace());
	    modBC.setText( String.valueOf(notaValidadaAliquota.getModBCImposto()));
	    
	    Element pRedBC = new Element("pRedBC",NotaFiscalUtil.getNameSpace());
	    pRedBC.setText(String.valueOf(notaValidadaAliquota.getPercentualReducaoBC()));
	    
	    Element valorBC = new Element("vBC",NotaFiscalUtil.getNameSpace());
	    valorBC.setText(String.valueOf(notaValidadaAliquota.getValorBCImposto()));
		
	    Element percentualImposto = new Element("pICMS",NotaFiscalUtil.getNameSpace());
	    percentualImposto.setText(String.valueOf(notaValidadaAliquota.getPercentualAliquota()));
	    
	    Element valorImposto = new Element("vICMS",NotaFiscalUtil.getNameSpace());
	    valorImposto.setText(String.valueOf(notaValidadaAliquota.getValorAliquota()));
	    
	    Element vICMSDeson = new Element("vICMSDeson",NotaFiscalUtil.getNameSpace());
	    vICMSDeson.setText(String.valueOf(notaValidadaAliquota.getValorAliquotaDesoneracao()));
	   
	    Element motDesICMS = new Element("motDescICMS",NotaFiscalUtil.getNameSpace());
	    motDesICMS.setText(String.valueOf(notaValidadaAliquota.getMotivoDesoneracaoImposto()));
	 
	    
	    element.removeContent();
	    elementICMS.addContent(origem);
	    elementICMS.addContent(cst);
	    elementICMS.addContent(modBC);
	    elementICMS.addContent(pRedBC);
	    elementICMS.addContent(valorBC);
	    elementICMS.addContent(percentualImposto);
	    elementICMS.addContent(valorImposto);
	    elementICMS.addContent(vICMSDeson);
	    
	    element.addContent(elementICMS);
	    
	    return element;
	}

}
