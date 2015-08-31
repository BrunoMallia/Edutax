package br.com.edutex.notafiscal.saida.icms;

import org.jdom2.Element;

import br.com.edutex.logic.NotaValidadaAliquota;
import br.com.edutex.notafiscal.interfaces.EscreverTributacao;
import br.com.edutex.notafiscal.util.NotaFiscalUtil;

public class EscreverICMS51 implements EscreverTributacao {

	/* 
	 * Este método escreve os atributos para o ICMS quando o CST é igual a 51.
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
	   
		Element modBC = new Element("modBC",NotaFiscalUtil.getNameSpace());
		modBC.setText(String.valueOf(notaValidadaAliquota.getModBCImposto()));
		
		Element pRedBC = new Element("pRedBC",NotaFiscalUtil.getNameSpace());
		pRedBC.setText(String.valueOf(notaValidadaAliquota.getPercentualReducaoBC()));
		
		Element vBC = new Element("vBC",NotaFiscalUtil.getNameSpace());
		vBC.setText(String.valueOf(notaValidadaAliquota.getValorBCImposto()));
		
		Element pICMS = new Element("pICMS",NotaFiscalUtil.getNameSpace());
		pICMS.setText(String.valueOf(notaValidadaAliquota.getPercentualAliquota()));
		
		Element vICMSOp = new Element("vICMSOp",NotaFiscalUtil.getNameSpace());
		vICMSOp.setText(String.valueOf(notaValidadaAliquota.getValorOperacaoImposto()));
		
	 
	    element.removeContent();
	    elementICMS.addContent(origem);
	    elementICMS.addContent(cst);
	    elementICMS.addContent(modBC);
	    elementICMS.addContent(pRedBC);
	    elementICMS.addContent(vBC);
	    elementICMS.addContent(pICMS);
	    elementICMS.addContent(vICMSOp);
	    
	    element.addContent(elementICMS);
	    
	    return element;
	}

}
