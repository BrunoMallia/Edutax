package br.com.edutex.notafiscal.saida.cofins;

import org.jdom2.Element;

import br.com.edutex.logic.NotaValidadaAliquota;
import br.com.edutex.notafiscal.interfaces.EscreverTributacao;
import br.com.edutex.notafiscal.util.NotaFiscalUtil;

public class EscreverCOFINSOutr implements EscreverTributacao {

	/* Método que escrreve os campos para o imposto COFINSOutr
	 * @see br.com.edutex.notafiscal.interfaces.EscreverTributacao#escreverTributacaoNota(org.jdom2.Element, br.com.edutex.logic.NotaValidadaAliquota)
	 */
	public Element escreverTributacaoNota(Element element,
			NotaValidadaAliquota notaValidadaAliquota) {
	
		Element elementoCOFINS = new Element("COFINSOutr",NotaFiscalUtil.getNameSpace());
		
		Element cst = new Element("CST",NotaFiscalUtil.getNameSpace());
		cst.setText(notaValidadaAliquota.getCst().getNmCST());
		
		Element vBC = new Element("vBC",NotaFiscalUtil.getNameSpace());
		vBC.setText(String.valueOf(notaValidadaAliquota.getValorBCImposto()));
		
		Element pCOFINS = new Element("pCOFINS",NotaFiscalUtil.getNameSpace());
		pCOFINS.setText(String.valueOf(notaValidadaAliquota.getPercentualAliquota()));
		
		Element qBCProd = new Element("qBCProd",NotaFiscalUtil.getNameSpace());
		qBCProd.setText(String.valueOf(notaValidadaAliquota.getQuantidadeBCProduto()));
	
		Element vAliqProd = new Element("vAliqProd",NotaFiscalUtil.getNameSpace());
		vAliqProd.setText(String.valueOf(notaValidadaAliquota.getValorAliquotaProduto()));
		
		Element vCOFINS = new Element("vCOFINS",NotaFiscalUtil.getNameSpace());
		vCOFINS.setText(String.valueOf(notaValidadaAliquota.getValorAliquota()));
		
		element.removeContent();
		elementoCOFINS.addContent(cst);
		elementoCOFINS.addContent(vBC);
		elementoCOFINS.addContent(pCOFINS);
		elementoCOFINS.addContent(qBCProd);
		elementoCOFINS.addContent(vAliqProd);
		elementoCOFINS.addContent(vCOFINS);
		element.addContent(elementoCOFINS);
		
		return element;
	}

}

