package br.com.edutex.notafiscal.saida.cofins;

import org.jdom2.Element;

import br.com.edutex.logic.NotaValidadaAliquota;
import br.com.edutex.notafiscal.interfaces.EscreverTributacao;
import br.com.edutex.notafiscal.util.NotaFiscalUtil;

public class EscreverCOFINSNT implements EscreverTributacao {

	/*
	 * Método que escreve a tributação no caso de COFINS 
	 * Código de Situação Tributária do COFINS:
	04 - Operação Tributável - Tributação Monofásica - (Alíquota Zero);
	05 - Operação Tributável (ST);
	06 - Operação Tributável - Alíquota Zero;
	07 - Operação Isenta da contribuição;
	08 - Operação Sem Incidência da contribuição;
	09 - Operação com suspensão da contribuição;
	 * @see br.com.edutex.notafiscal.interfaces.EscreverTributacao#escreverTributacaoNota(org.jdom2.Element, br.com.edutex.logic.NotaValidadaAliquota)
	 */
	public Element escreverTributacaoNota(Element element,
			NotaValidadaAliquota notaValidadaAliquota) {

		Element elementoCOFINS = new Element("COFINSNT",NotaFiscalUtil.getNameSpace());
		
		Element cst = new Element("CST",NotaFiscalUtil.getNameSpace());
		cst.setText(notaValidadaAliquota.getCst().getNmCST());
		
		elementoCOFINS.addContent(cst);
		element.addContent(elementoCOFINS);
		
		return element;
		
	}

}
