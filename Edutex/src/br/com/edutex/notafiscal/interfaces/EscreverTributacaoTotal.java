package br.com.edutex.notafiscal.interfaces;

import java.util.List;

import org.jdom2.Element;

import br.com.edutex.logic.NotaValidadaItem;

public interface EscreverTributacaoTotal {
	
	/**
	 * Este método define a assinatura de como escrever os campos totais das tributações
	 * @param notaValidadaAliquota
	 * @param nodeTotal
	 * @return Element
	 */
	Element escreverTributacaoTotalNota(List<NotaValidadaItem> listaNotaValidadaItem,
			Element nodeTotal);
}
