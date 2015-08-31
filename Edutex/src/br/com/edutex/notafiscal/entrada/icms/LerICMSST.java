package br.com.edutex.notafiscal.entrada.icms;

import org.jdom2.Element;

import br.com.edutex.logic.CST;
import br.com.edutex.logic.NotaValidadaAliquota;
import br.com.edutex.logic.TipoImposto;
import br.com.edutex.notafiscal.interfaces.LerTributacaoNota;

public class LerICMSST implements LerTributacaoNota {

	/* 
	 * Método que lê os campos de ICMS para o Grupo de informação do ICMSST devido para a UF de destino, nas operações interestaduais de produtos que 
	 * tiveram retenção antecipada de ICMS por ST na UF do remetente. Repasse via Substituto Tributário.
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
			 		
				case "CST":
					CST cst = new CST();
					cst.setNmCST(elementoICMS.getValue());
					notaValidadaAliquota.setCst(cst);
					break;
					
				case "modBC":
					notaValidadaAliquota.setModBCImposto(Integer.parseInt(elementoICMS.getValue()));
					break;
				
				case "vBC":
					notaValidadaAliquota.setValorBCImposto(Float.parseFloat(elementoICMS.getValue()));
					break;
				
				case "vBCSTRet":
					notaValidadaAliquota.setValorBCSTRetidoAnteriormente(Float.parseFloat(elementoICMS.getValue()));
					break;
					
				case "vBCSTDest":
					notaValidadaAliquota.setValorBCSTDest(Float.parseFloat(elementoICMS.getValue()));
					break;
			 }
		 }
		 
		
		return notaValidadaAliquota;
	}

}
