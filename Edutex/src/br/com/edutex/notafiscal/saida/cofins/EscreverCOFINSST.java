package br.com.edutex.notafiscal.saida.cofins;

import org.jdom2.Element;

import br.com.edutex.logic.NotaValidadaAliquota;
import br.com.edutex.notafiscal.interfaces.EscreverTributacao;
import br.com.edutex.notafiscal.util.NotaFiscalUtil;

public class EscreverCOFINSST implements EscreverTributacao {

	/* 
	 * Método que escrreve os campos para o imposto COFINS
	 * @see br.com.edutex.notafiscal.interfaces.EscreverTributacao#escreverTributacaoNota(org.jdom2.Element, br.com.edutex.logic.NotaValidadaAliquota)
	 */
	public Element escreverTributacaoNota(Element element,
			NotaValidadaAliquota notaValidadaAliquota) {
	
		Element elementoCOFINS = new Element("COFINSST",NotaFiscalUtil.getNameSpace());
		
		Element vBC = new Element("vBC",NotaFiscalUtil.getNameSpace());
		vBC.setText(String.format("%.2f",notaValidadaAliquota.getValorBCImposto()));
		
		Element pCOFINS = new Element("pCOFINS",NotaFiscalUtil.getNameSpace());
		pCOFINS.setText(String.format("%.2f",notaValidadaAliquota.getPercentualAliquota()));
		
		Element qBCProd = new Element("qBCProd", NotaFiscalUtil.getNameSpace());
		qBCProd.setText(String.valueOf(notaValidadaAliquota.getQuantidadeBCProduto()));
		
		Element vAliqProd = new Element("vAliqProd",NotaFiscalUtil.getNameSpace());
		vAliqProd.setText(String.format("%.2f",notaValidadaAliquota.getValorAliquotaProduto()));
		
		Element vCOFINS = new Element("vCOFINS",NotaFiscalUtil.getNameSpace());
		vCOFINS.setText(String.format("%.2f",notaValidadaAliquota.getValorAliquota()));
		
		Element pBCOp = new Element("pBCOp", NotaFiscalUtil.getNameSpace());
		pBCOp.setText(String.format("%.2f",notaValidadaAliquota.getPercentualBCOperacao()));
		
		Element ufst = new Element("UFST", NotaFiscalUtil.getNameSpace());
		ufst.setText(String.valueOf(notaValidadaAliquota.getUfST()));
		

		elementoCOFINS.addContent(vBC);
		elementoCOFINS.addContent(pCOFINS);
		elementoCOFINS.addContent(qBCProd);
		elementoCOFINS.addContent(vAliqProd);
		elementoCOFINS.addContent(vCOFINS);
		elementoCOFINS.addContent(pBCOp);
		elementoCOFINS.addContent(ufst);
		element.addContent(elementoCOFINS);
		
		return element;
	}

}
