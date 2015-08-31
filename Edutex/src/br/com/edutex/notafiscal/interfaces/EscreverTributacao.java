package br.com.edutex.notafiscal.interfaces;

import org.jdom2.Element;

import br.com.edutex.logic.NotaValidadaAliquota;

/**
 * Esta classe define o contrato das ações ao ICMS
 * @author Bruno Mallia
 *
 */
public interface EscreverTributacao {
	
	Element escreverTributacaoNota(Element element, NotaValidadaAliquota notaValidadaAliquota);
	
}
