package br.com.edutex.notafiscal.entrada.icms;

import org.jdom2.Element;

import br.com.edutex.logic.NotaValidadaAliquota;
import br.com.edutex.logic.TipoImposto;
import br.com.edutex.notafiscal.interfaces.LerTributacaoNota;

public class LerICMSSN202 implements LerTributacaoNota {

	/*
	 * M�todo que l� a tributa��o do ICMS para simples nacional csosn 202
	 * @see br.com.edutex.notafiscal.interfaces.LerTributacaoNota#lerTributacaoNota(org.jdom2.Element)
	 */
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
					
				case "modBCST":
					notaValidadaAliquota.setModBCSTImposto(Integer.parseInt(elementoICMS.getValue()));
					break;
					
				case "pMVAST":
					notaValidadaAliquota.setPercentualMargemValorAdicionadoST(Float.parseFloat(elementoICMS.getValue()));
					break;
					
				case "pRedBCST":
					notaValidadaAliquota.setPercentualReducaoBCST(Float.parseFloat(elementoICMS.getValue()));
					break;
					
				case "vBCST":
					notaValidadaAliquota.setValorBCST(Float.parseFloat(elementoICMS.getValue()));
					break;	
					
				case "pICMSST":
					notaValidadaAliquota.setPercentualAliquotaST(Float.parseFloat(elementoICMS.getValue()));
					break;	
					
				case "vICMSST":
					notaValidadaAliquota.setValorAliquotaST(Float.parseFloat(elementoICMS.getValue()));
					break;		
					
			 }
		 }
		 
		
		return notaValidadaAliquota;
	}

}
