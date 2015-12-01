package br.com.edutex.notafiscal.interfaces;


import org.jdom2.Element;

import br.com.edutex.logic.NotaValidada;

public interface EscreverTributacaoTotal {
	
	/**
	 * Este m�todo define a assinatura de como escrever os campos totais das tributa��es
	 * @param notaValidadaAliquota
	 * @param nodeTotal
	 * @return Element
	 */
	Element escreverTributacaoTotalNota(NotaValidada notaValidada,
			Element nodeTotal);
}
