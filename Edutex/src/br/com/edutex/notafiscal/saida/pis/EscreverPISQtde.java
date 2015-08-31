package br.com.edutex.notafiscal.saida.pis;

import org.jdom2.Element;

import br.com.edutex.logic.NotaValidadaAliquota;
import br.com.edutex.notafiscal.interfaces.EscreverTributacao;
import br.com.edutex.notafiscal.util.NotaFiscalUtil;

public class EscreverPISQtde implements EscreverTributacao {

	public Element escreverTributacaoNota(Element element,
			NotaValidadaAliquota notaValidadaAliquota) {
		
	 Element elementoPIS = new Element("PISQtde",NotaFiscalUtil.getNameSpace());
		
	 Element cst = new Element("CST",NotaFiscalUtil.getNameSpace());
	 cst.addContent(String.valueOf(notaValidadaAliquota.getCst().getNmCST()));
	 
	 Element qBCProd = new Element("qBCProd",NotaFiscalUtil.getNameSpace());
	 qBCProd.setText(String.valueOf(notaValidadaAliquota.getQuantidadeBCProduto()));

	 Element vAliqProd = new Element("vAliqProd",NotaFiscalUtil.getNameSpace());
	 vAliqProd.setText(String.valueOf(notaValidadaAliquota.getValorAliquotaProduto()));

	 Element vPIS = new Element("vpIS",NotaFiscalUtil.getNameSpace());
	 vPIS.setText(String.valueOf(notaValidadaAliquota.getValorAliquota()));
	 
	 element.removeContent();
	 elementoPIS.addContent(cst);
	 elementoPIS.addContent(qBCProd);
	 elementoPIS.addContent(vAliqProd);
	 elementoPIS.addContent(vPIS);
	 element.addContent(elementoPIS);
	 
	return element;
	
	}

}
