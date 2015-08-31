package br.com.edutex.notafiscal.saida.icms;

import org.jdom2.Element;

import br.com.edutex.logic.NotaValidadaAliquota;
import br.com.edutex.notafiscal.interfaces.EscreverTributacao;
import br.com.edutex.notafiscal.util.NotaFiscalUtil;

public class EscreverICMS90 implements EscreverTributacao {

	/* 
	 * Este m�todo escreve os atributos para o ICMS quando o CST � igual a 90.
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
	    
	    Element vBC = new Element("vBC",NotaFiscalUtil.getNameSpace());
	    vBC.setText(String.valueOf(notaValidadaAliquota.getValorBCImposto()));
	    
	    Element pRedBC = new Element("pRedBC",NotaFiscalUtil.getNameSpace());
	    pRedBC.setText(String.valueOf(notaValidadaAliquota.getPercentualReducaoBC()));
	    
	    Element pICMS = new Element("pICMS",NotaFiscalUtil.getNameSpace());
	    pICMS.setText(String.valueOf(notaValidadaAliquota.getPercentualAliquota()));
	    
	    Element vICMS = new Element("vICMS",NotaFiscalUtil.getNameSpace());
	    vICMS.setText(String.valueOf(notaValidadaAliquota.getValorAliquota()));
	    
	    Element modBCST = new Element("modBCST",NotaFiscalUtil.getNameSpace());
	    modBCST.setText(String.valueOf(notaValidadaAliquota.getModBCSTImposto()));
	    
	    Element pMVAST = new Element("pMVAST",NotaFiscalUtil.getNameSpace());
	    pMVAST.setText(String.valueOf(notaValidadaAliquota.getPercentualMargemValorAdicionadoST()));
	    
	    Element vBCST = new Element("vBCST",NotaFiscalUtil.getNameSpace());
	    vBCST.setText(String.valueOf(notaValidadaAliquota.getValorBCST()));
	    
	    Element pRedBCST = new Element("pRedBCST",NotaFiscalUtil.getNameSpace());
	    pRedBCST.setText(String.valueOf(notaValidadaAliquota.getPercentualReducaoBCST()));
	    
	    Element pICMSST = new Element("pICMSST",NotaFiscalUtil.getNameSpace());
	    pICMSST.setText(String.valueOf(notaValidadaAliquota.getPercentualAliquotaST()));
	    
	    Element vICMSST = new Element("vICMSST",NotaFiscalUtil.getNameSpace());
	    vICMSST.setText(String.valueOf(notaValidadaAliquota.getValorAliquotaST()));
	    
	    Element vICMSDeson = new Element("vICMSDeson",NotaFiscalUtil.getNameSpace());
	    vICMSDeson.setText(String.valueOf(notaValidadaAliquota.getValorAliquotaDesoneracao()));
	    
	    Element motDesICMS = new Element("motDesICMS",NotaFiscalUtil.getNameSpace());
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
	    
	    return element;
	}

}
