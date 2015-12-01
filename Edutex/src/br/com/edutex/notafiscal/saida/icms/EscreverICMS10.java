package br.com.edutex.notafiscal.saida.icms;

import java.util.Locale;

import org.jdom2.Element;

import br.com.edutex.logic.NotaValidadaAliquota;
import br.com.edutex.notafiscal.interfaces.EscreverTributacao;
import br.com.edutex.notafiscal.util.NotaFiscalUtil;

public class EscreverICMS10 implements EscreverTributacao {
	
	
	/*
	 * Este método escreve os atributos para o ICMS quando o CST é igual a 10.
	 * 
	 *  (non-Javadoc)
	 * @see br.com.edutex.notafiscal.EscreveICMS#escreverICMSNota(org.jdom2.Element, br.com.edutex.logic.NotaValidadaAliquota)
	 */
	public Element escreverTributacaoNota(Element element,
			NotaValidadaAliquota notaValidadaAliquota) {
		
		
		Element elementICMS = new Element("ICMS"+ notaValidadaAliquota.getCst().getNmCST(),NotaFiscalUtil.getNameSpace());
		
	    Element origem = new Element("Origem", NotaFiscalUtil.getNameSpace());
	    origem.setText(String.valueOf(notaValidadaAliquota.getOrigem()));
	    
	    Element cst = new Element("CST",NotaFiscalUtil.getNameSpace());
	    cst.setText(notaValidadaAliquota.getCst().getNmCST());
	    
	    Element  modBC = new Element("modBC",NotaFiscalUtil.getNameSpace());
	    modBC.setText( String.valueOf(notaValidadaAliquota.getModBCImposto()));
	    
	    Element valorBC = new Element("vBC",NotaFiscalUtil.getNameSpace());
	    valorBC.setText(String.format(Locale.US,"%.2f",notaValidadaAliquota.getValorBCImposto()));
		
	    Element percentualImposto = new Element("pICMS",NotaFiscalUtil.getNameSpace());
	    percentualImposto.setText(String.format(Locale.US,"%.2f",notaValidadaAliquota.getPercentualAliquota()));
	    
	    Element valorImposto = new Element("vICMS",NotaFiscalUtil.getNameSpace());
	    valorImposto.setText(String.format(Locale.US,"%.2f",notaValidadaAliquota.getValorAliquota()));
	    
	    Element modBaseCalculoST = new Element("modBCST",NotaFiscalUtil.getNameSpace());
	    modBaseCalculoST.setText(String.valueOf(notaValidadaAliquota.getModBCSTImposto()));
	    
	    Element pMVAST = new Element("pMVAST",NotaFiscalUtil.getNameSpace());
	    pMVAST.setText(String.format(Locale.US,"%.2f",notaValidadaAliquota.getPercentualMargemValorAdicionadoST()));
	    
	    Element pRedBCST = new Element("pRedBCST",NotaFiscalUtil.getNameSpace());
	    pRedBCST.setText(String.format(Locale.US,"%.2f",notaValidadaAliquota.getPercentualReducaoBCST()));
	    
	    Element vBCST = new Element("vBCST",NotaFiscalUtil.getNameSpace());
	    vBCST.setText(String.format(Locale.US,"%.2f",notaValidadaAliquota.getValorBCST()));
	    
	    Element pICMSST = new Element("pICMSST",NotaFiscalUtil.getNameSpace());
	    pICMSST.setText(String.format(Locale.US,"%.2f",notaValidadaAliquota.getPercentualAliquotaST()));
	    
	    element.removeContent();
	    elementICMS.addContent(origem);
	    elementICMS.addContent(cst);
	    elementICMS.addContent(modBC);
	    elementICMS.addContent(valorBC);
	    elementICMS.addContent(percentualImposto);
	    elementICMS.addContent(valorImposto);
	    elementICMS.addContent(modBaseCalculoST);
	    elementICMS.addContent(pRedBCST);
	    elementICMS.addContent(vBCST);
	    elementICMS.addContent(pICMSST);
	    
	    element.addContent(elementICMS);
		
		return element;
		
	}
	
}
