package br.com.edutex.notafiscal.saida.icms;

import java.util.Locale;

import org.jdom2.Element;

import br.com.edutex.logic.NotaValidadaAliquota;
import br.com.edutex.notafiscal.interfaces.EscreverTributacao;
import br.com.edutex.notafiscal.util.NotaFiscalUtil;

public class EscreverICMS70 implements EscreverTributacao {

	/* 
	 * Este método escreve os atributos para o ICMS quando o CST é igual a 70.
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
	    pRedBC.setText(String.format(Locale.US,"%.2f",notaValidadaAliquota.getPercentualReducaoBC()));
	    
	    Element vBC = new Element("vBC",NotaFiscalUtil.getNameSpace());
	    vBC.setText(String.format(Locale.US,"%.2f",notaValidadaAliquota.getValorBCImposto()));
	    
	    Element pICMS = new Element("pICMS",NotaFiscalUtil.getNameSpace());
	    pICMS.setText(String.format(Locale.US,"%.2f",notaValidadaAliquota.getPercentualAliquota()));
	    
	    Element vICMS = new Element("vICMS",NotaFiscalUtil.getNameSpace());
	    vICMS.setText(String.format(Locale.US,"%.2f",notaValidadaAliquota.getValorAliquota()));
	    
	    Element modBCST = new Element("modBCST",NotaFiscalUtil.getNameSpace());
	    modBCST.setText(String.valueOf(notaValidadaAliquota.getModBCSTImposto()));
	    
	    Element pMVAST = new Element("pMVAST",NotaFiscalUtil.getNameSpace());
	    pMVAST.setText(String.format(Locale.US,"%.2f",notaValidadaAliquota.getPercentualMargemValorAdicionadoST()));
	    
	    Element pRedBCST = new Element("pRedBCST",NotaFiscalUtil.getNameSpace());
	    pRedBCST.setText(String.format(Locale.US,"%.2f",notaValidadaAliquota.getPercentualReducaoBCST()));
	   
	    Element vBCST = new Element("vBCST",NotaFiscalUtil.getNameSpace());
	    vBCST.setText(String.format(Locale.US,"%.2f",notaValidadaAliquota.getValorBCST()));
	    
	    Element pICMSST = new Element("pICMSST",NotaFiscalUtil.getNameSpace());
	    pICMSST.setText(String.format(Locale.US,"%.2f",notaValidadaAliquota.getPercentualAliquotaST()));
	    
	    Element vICMSST = new Element("vICMSST",NotaFiscalUtil.getNameSpace());
	    vICMSST.setText(String.format(Locale.US,"%.2f",notaValidadaAliquota.getValorAliquotaST()));
	    
	    Element vICMSDeson = new Element("vICMSDeson",NotaFiscalUtil.getNameSpace());
	    vICMSDeson.setText(String.format(Locale.US,"%.2f",notaValidadaAliquota.getValorAliquotaDesoneracao()));
	    
	    Element motDesICMS = new Element("motDesICMS");
	    motDesICMS.setText(String.valueOf(notaValidadaAliquota.getMotivoDesoneracaoImposto()));
	    
	    element.removeContent();
	    elementICMS.addContent(origem);
	    elementICMS.addContent(cst);
	    elementICMS.addContent(modBC);
	    elementICMS.addContent(vBC);
	    elementICMS.addContent(pRedBC);
	    elementICMS.addContent(pICMS);
	    elementICMS.addContent(vICMS);
	    elementICMS.addContent(modBCST);
	    elementICMS.addContent(pMVAST);
	    elementICMS.addContent(pRedBCST);
	    elementICMS.addContent(vBCST);
	    elementICMS.addContent(pICMSST);
	    elementICMS.addContent(vICMSST);
	    elementICMS.addContent(vICMSDeson);
	    elementICMS.addContent(motDesICMS);
	    
	    element.addContent(elementICMS);
	    
	    return element;
	}

}
