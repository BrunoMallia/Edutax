package br.com.edutex.notafiscal.entrada.icms;

import org.jdom2.Element;

import br.com.edutex.logic.CST;
import br.com.edutex.logic.NotaValidadaAliquota;
import br.com.edutex.logic.TipoImposto;
import br.com.edutex.notafiscal.interfaces.LerTributacaoNota;

public class LerICMS40 implements LerTributacaoNota {

	/* 
	 *  Método que lê os campos de tributação ICMS para os casos do CST seja 40
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
					
				case "vICMSDeson":
					notaValidadaAliquota.setValorAliquotaDesoneracao(Float.parseFloat(elementoICMS.getValue()));
					break;
				
				case "motDesICMS":
					notaValidadaAliquota.setMotivoDesoneracaoImposto(Integer.parseInt(elementoICMS.getValue()));
					break;
			 }
		 }
		 
		
		return notaValidadaAliquota;
	}

}
