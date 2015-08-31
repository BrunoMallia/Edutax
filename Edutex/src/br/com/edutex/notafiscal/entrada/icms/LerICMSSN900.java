package br.com.edutex.notafiscal.entrada.icms;

import org.jdom2.Element;

import br.com.edutex.logic.NotaValidadaAliquota;
import br.com.edutex.logic.TipoImposto;
import br.com.edutex.notafiscal.interfaces.LerTributacaoNota;

public class LerICMSSN900 implements LerTributacaoNota {

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
					
				case "modBC":
					notaValidadaAliquota.setModBCImposto(Integer.parseInt(elementoICMS.getValue()));
					break;
					
				case "vBC":
					notaValidadaAliquota.setValorBCImposto(Float.parseFloat(elementoICMS.getValue()));
					break;
					
				case "pRedBC":
					notaValidadaAliquota.setPercentualAliquota(Float.parseFloat(elementoICMS.getValue()));
					break;
					
				case "pICMS":
					notaValidadaAliquota.setPercentualAliquota(Float.parseFloat(elementoICMS.getValue()));
					break;
					
				case "vICMS":
					notaValidadaAliquota.setValorAliquota(Float.parseFloat(elementoICMS.getValue()));
					break;
					
				case "modBCST":
					notaValidadaAliquota.setModBCSTImposto(Integer.parseInt(elementoICMS.getValue()));
					break;
					
				case "pMVAST":
					notaValidadaAliquota.setPercentualMargemValorAdicionadoST(Float.parseFloat(elementoICMS.getValue()));
					break;	
					
				case "pRedBCST":
					notaValidadaAliquota.setPercentualAliquota(Float.parseFloat(elementoICMS.getValue()));
					break;
					
				case "vBCST":
					notaValidadaAliquota.setValorBCST(Float.parseFloat(elementoICMS.getValue()));
					break;		
				
				case "pICMSST":
					notaValidadaAliquota.setPercentualAliquota(Float.parseFloat(elementoICMS.getValue()));
					break;	
					
				case "vICMSST":
					notaValidadaAliquota.setValorAliquotaST(Float.parseFloat(elementoICMS.getValue()));
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
