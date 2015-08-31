package br.com.edutex.notafiscal.entrada.icms;

import org.jdom2.Element;

import br.com.edutex.logic.NotaValidadaAliquota;
import br.com.edutex.logic.TipoImposto;
import br.com.edutex.notafiscal.interfaces.LerTributacaoNota;

public class LerICMSSN101 implements LerTributacaoNota {

	/*
	 * M�todo que l� o ICMS para simples nacional CSOSN=101
	 * @see br.com.edutex.notafiscal.interfaces.LerTributacaoNota#lerTributacaoNota(org.jdom2.Element)
	 */
	@Override
	public NotaValidadaAliquota lerTributacaoNota(Element elemento) {
		
		NotaValidadaAliquota notaValidadaAliquota = new NotaValidadaAliquota();
		TipoImposto tpImposto = new TipoImposto();
		tpImposto.setCdTipoImposto(1);
		tpImposto.setNmTipoImposto("ICMS");
		notaValidadaAliquota.setTipoImpostoAliquota(tpImposto); 
		
		for (Element elementoICMS: elemento.getChildren()) {
			  
			 
			 switch (elementoICMS.getName()) {

				case "orig":
					notaValidadaAliquota.setOrigem(Integer.parseInt(elementoICMS.getValue()));
			 		break;
			 		
				case "CSOSN":
					notaValidadaAliquota.setCsosn(Integer.parseInt(elementoICMS.getValue()));
					break;
				
				case "pCredSN":
					notaValidadaAliquota.setPercentualCreditoSN(Float.parseFloat(elementoICMS.getValue()));
					break;
				
				case "vCredICMSSN":
					notaValidadaAliquota.setValorCreditoSN(Float.parseFloat(elementoICMS.getValue()));
					break;
					
			 }
		 }
		
		return notaValidadaAliquota;

		
	}

}
