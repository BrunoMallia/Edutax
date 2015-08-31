package br.com.edutex.notafiscal.saida.cofins;

import org.jdom2.Element;

import br.com.edutex.logic.NotaValidadaAliquota;
import br.com.edutex.notafiscal.interfaces.EscreverTributacao;
import br.com.edutex.notafiscal.util.NotaFiscalUtil;

public class EscreverCOFINSNT implements EscreverTributacao {

	/*
	 * M�todo que escreve a tributa��o no caso de COFINS 
	 * C�digo de Situa��o Tribut�ria do COFINS:
	04 - Opera��o Tribut�vel - Tributa��o Monof�sica - (Al�quota Zero);
	05 - Opera��o Tribut�vel (ST);
	06 - Opera��o Tribut�vel - Al�quota Zero;
	07 - Opera��o Isenta da contribui��o;
	08 - Opera��o Sem Incid�ncia da contribui��o;
	09 - Opera��o com suspens�o da contribui��o;
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
