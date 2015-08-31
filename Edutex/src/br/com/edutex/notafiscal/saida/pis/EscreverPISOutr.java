package br.com.edutex.notafiscal.saida.pis;

import org.jdom2.Element;

import br.com.edutex.logic.NotaValidadaAliquota;
import br.com.edutex.notafiscal.interfaces.EscreverTributacao;
import br.com.edutex.notafiscal.util.NotaFiscalUtil;

public class EscreverPISOutr implements EscreverTributacao {

	public Element escreverTributacaoNota(Element element,
			NotaValidadaAliquota notaValidadaAliquota) {
		
	 Element elementoPIS = new Element("PISOutr",NotaFiscalUtil.getNameSpace());
		
	 Element cst = new Element("CST",NotaFiscalUtil.getNameSpace());
	 cst.addContent(String.valueOf(notaValidadaAliquota.getCst().getNmCST()));
	 
	 Element vBC = new Element("vBC",NotaFiscalUtil.getNameSpace());
	 vBC.setText(String.valueOf(notaValidadaAliquota.getValorBCImposto()));
	 
	 Element pPIS = new Element("pPIS",NotaFiscalUtil.getNameSpace());
	 pPIS.setText(String.valueOf(String.valueOf(notaValidadaAliquota.getPercentualAliquota())));
	 
	 Element vPIS = new Element("vpIS",NotaFiscalUtil.getNameSpace());
	 vPIS.setText(String.valueOf(notaValidadaAliquota.getValorAliquota()));
	 
	 element.removeContent();
	 elementoPIS.addContent(cst);
	 elementoPIS.addContent(vBC);
	 elementoPIS.addContent(pPIS);
	 elementoPIS.addContent(vPIS);
	 element.addContent(elementoPIS);
	 
	return element;
	
	}

}
