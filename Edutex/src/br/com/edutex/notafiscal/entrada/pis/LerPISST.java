package br.com.edutex.notafiscal.entrada.pis;

import org.jdom2.Element;

import br.com.edutex.logic.NotaValidadaAliquota;
import br.com.edutex.logic.TipoImposto;
import br.com.edutex.notafiscal.interfaces.LerTributacaoNota;

public class LerPISST implements LerTributacaoNota {

	/* (non-Javadoc)
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

		 		
				case "vBC":
					notaValidadaAliquota.setValorBCImposto(Float.parseFloat(elementoPIS.getValue()));
					break;
				
				case "pPIS":
					notaValidadaAliquota.setPercentualAliquota(Float.parseFloat(elementoPIS.getValue()));
					break;

				case "qBCProd":
					notaValidadaAliquota.setQuantidadeBCProduto(Integer.parseInt(elementoPIS.getValue()));
					break;
				
				case "vPIS":
					notaValidadaAliquota.setValorAliquota(Float.parseFloat(elementoPIS.getValue()));
					break;
					
				case "vAliqProd":
					notaValidadaAliquota.setValorAliquotaProduto(Float.parseFloat(elementoPIS.getValue()));
					break;
					
			
			 }
		 }
		 
		return notaValidadaAliquota;

	}

}
