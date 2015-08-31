package br.com.edutex.notafiscal.interfaces;

import org.jdom2.Element;

import br.com.edutex.logic.NotaValidadaAliquota;
import br.com.edutex.notafiscal.NotaFiscalAliquota;

public interface LerTributacaoNota {
	
	NotaValidadaAliquota lerTributacaoNota(Element elemento);
}
