package br.com.edutex.notafiscal.entrada.pis;

import org.jdom2.Element;

import br.com.edutex.logic.CST;
import br.com.edutex.logic.NotaValidadaAliquota;
import br.com.edutex.logic.TipoImposto;
import br.com.edutex.notafiscal.interfaces.LerTributacaoNota;

public class LerPISOutr implements LerTributacaoNota {

	
	
	/*
	 *   Lê a nota o pIS na seguinte situações:
	 * 	  Código de Situação Tributária do PIS.99 - Outras Operações.
	 *	  @param elemento
	 *	  @return
	 * @see br.com.edutex.notafiscal.interfaces.LerTributacaoNota#lerTributacaoNota(org.jdom2.Element)
	 */
	public NotaValidadaAliquota lerTributacaoNota(Element elemento) {
		NotaValidadaAliquota notaValidadaAliquota = new NotaValidadaAliquota();
		TipoImposto tipoImposto = new TipoImposto();
		tipoImposto.setCdTipoImposto(2);
		tipoImposto.setNmTipoImposto("PIS");
		notaValidadaAliquota.setTipoImpostoAliquota(tipoImposto);
		 
		for (Element elementoPIS: elemento.getChildren()) {
			  
			switch (elementoPIS.getName()) {
			
			case "CST":
				CST cst = new CST();
				cst.setNmCST(elementoPIS.getValue());
				notaValidadaAliquota.setCst(cst);
				break;
				
			case "vBC":
				notaValidadaAliquota.setValorBCImposto(Float.parseFloat(elementoPIS.getValue()));
				break;
				
			case "pPIS":
				notaValidadaAliquota.setPercentualAliquota(Float.parseFloat(elementoPIS.getValue()));
				break;
			
			case "vPIS":
				notaValidadaAliquota.setValorAliquota(Float.parseFloat(elementoPIS.getValue()));
				break;
				
			}
			 
		 }
		 
		return notaValidadaAliquota;

	}

}
