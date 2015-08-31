package br.com.edutex.notafiscal.saida.icms;

import org.jdom2.Element;

import br.com.edutex.logic.NotaValidadaAliquota;
import br.com.edutex.notafiscal.interfaces.EscreverTributacao;
import br.com.edutex.notafiscal.util.NotaFiscalUtil;

public class EscreverICMSSN900 implements EscreverTributacao {

	/* 
	 * Este método escreve as tributação ICMS para simples nacional 900
	 * @see br.com.edutex.notafiscal.EscreveICMS#escreverICMSNota(org.jdom2.Element, br.com.edutex.logic.NotaValidadaAliquota)
	 */
	public Element escreverTributacaoNota(Element element,
			NotaValidadaAliquota notaValidadaAliquota) {

		
	Element elementICMS = new Element("ICMSSN"+ notaValidadaAliquota.getCsosn(),NotaFiscalUtil.getNameSpace());

	
    Element origem = new Element("orig",NotaFiscalUtil.getNameSpace());
    origem.setText(String.valueOf(notaValidadaAliquota.getOrigem()));
    
    Element CSOSN = new Element("CSOSN",NotaFiscalUtil.getNameSpace());
	CSOSN.setText(String.valueOf(notaValidadaAliquota.getCsosn()));
	
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
	
	Element pRedBCST = new Element("pRedBCST",NotaFiscalUtil.getNameSpace());
	pRedBCST.setText(String.valueOf(notaValidadaAliquota.getPercentualReducaoBCST()));

	Element vBCST = new Element("vBCST",NotaFiscalUtil.getNameSpace());
	vBCST.setText(String.valueOf(notaValidadaAliquota.getValorBCST()));
	
	Element pICMSST = new Element("pICMSST",NotaFiscalUtil.getNameSpace());
	pICMSST.setText(String.valueOf(notaValidadaAliquota.getPercentualAliquotaST()));
	
	Element vICMSST = new Element("vICMSST",NotaFiscalUtil.getNameSpace());
	vICMSST.setText(String.valueOf(notaValidadaAliquota.getValorAliquotaST()));
	
	Element pCredSN = new Element("pCredSN",NotaFiscalUtil.getNameSpace());
	pCredSN.setText(String.valueOf(notaValidadaAliquota.getPercentualCreditoSN()));
	
	Element vCredICMSSN = new Element("vCredICMSSN",NotaFiscalUtil.getNameSpace());
	vCredICMSSN.setText(String.valueOf(notaValidadaAliquota.getValorCreditoSN()));
	
	
	elementICMS.removeContent();
	elementICMS.addContent(origem);
	elementICMS.addContent(CSOSN);
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
	elementICMS.addContent(pCredSN);
	elementICMS.addContent(vCredICMSSN);
	element.addContent(elementICMS);
	
	return element;
	
	}

}
