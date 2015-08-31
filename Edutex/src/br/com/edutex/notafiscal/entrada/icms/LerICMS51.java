package br.com.edutex.notafiscal.entrada.icms;

import org.jdom2.Element;

import br.com.edutex.logic.CST;
import br.com.edutex.logic.NotaValidadaAliquota;
import br.com.edutex.logic.TipoImposto;
import br.com.edutex.notafiscal.interfaces.LerTributacaoNota;

public class LerICMS51 implements LerTributacaoNota {

	/*  Método que lê os campos de tributação ICMS para os casos do CST seja 51
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
				
				case "pRedBC":
					notaValidadaAliquota.setPercentualReducaoBC(Float.parseFloat(elementoICMS.getValue()));
					break;
					
				case "vBC":
					notaValidadaAliquota.setValorBCImposto(Float.parseFloat(elementoICMS.getValue()));
					break;
					
				
				case "pICMS":
					notaValidadaAliquota.setPercentualAliquota(Float.parseFloat(elementoICMS.getValue()));
					break;
				
				case "vICMSOp":
					notaValidadaAliquota.setValorOperacaoImposto(Float.parseFloat(elementoICMS.getValue()));
					break;
					
			 }
		 }
		 
		
		return notaValidadaAliquota;

			
	}

}
