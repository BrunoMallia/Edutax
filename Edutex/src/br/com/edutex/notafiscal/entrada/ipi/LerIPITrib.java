package br.com.edutex.notafiscal.entrada.ipi;

import org.jdom2.Element;

import br.com.edutex.logic.CST;
import br.com.edutex.logic.NotaValidadaAliquota;
import br.com.edutex.logic.TipoImposto;
import br.com.edutex.notafiscal.interfaces.LerTributacaoNota;

public class LerIPITrib implements LerTributacaoNota {

	
	/* 
	 * Método que lê a tributação nos casos do cst ser:00,49,50,99
	 * @see br.com.edutex.notafiscal.interfaces.LerTributacaoNota#lerTributacaoNota(org.jdom2.Element)
	 */
	public NotaValidadaAliquota lerTributacaoNota(Element elemento) {
		
		NotaValidadaAliquota notaValidadaAliquota = new NotaValidadaAliquota();
		TipoImposto tipoImposto = new TipoImposto();
		tipoImposto.setCdTipoImposto(4);
		tipoImposto.setNmTipoImposto("IPI");
		notaValidadaAliquota.setTipoImpostoAliquota(tipoImposto);

		for (Element nodeIPI: elemento.getChildren()) {
			
			switch (nodeIPI.getName()) {
				case "CST":
					CST cst = new CST();
					cst.setNmCST(nodeIPI.getValue());
					notaValidadaAliquota.setCst(cst);
					break;
					
				case "vBC":
					notaValidadaAliquota.setValorBCImposto(Float.parseFloat(nodeIPI.getValue()));
					break;
					
				case "pIPI":
					notaValidadaAliquota.setPercentualAliquota(Float.parseFloat(nodeIPI.getValue()));
					break;
					
				case "qUnid":
					notaValidadaAliquota.setQuantidadeUnidadePadrao(Integer.parseInt(nodeIPI.getValue()));
					break;
					
				case "vUnid":
					notaValidadaAliquota.setValorUnidadeTributavel(Float.parseFloat(nodeIPI.getValue()));
					break;
					
				case "vIPI":
					notaValidadaAliquota.setValorAliquota(Float.parseFloat(nodeIPI.getValue()));
					break;
			}
		}
		
		return notaValidadaAliquota;
	}

}
