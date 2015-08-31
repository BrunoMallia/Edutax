package br.com.edutex.notafiscal.entrada.cofins;

import org.jdom2.Element;

import br.com.edutex.logic.CST;
import br.com.edutex.logic.NotaValidadaAliquota;
import br.com.edutex.logic.TipoImposto;
import br.com.edutex.notafiscal.interfaces.LerTributacaoNota;

public class LerCOFINSQtde implements LerTributacaoNota {

	/* (non-Javadoc)
	 * @see br.com.edutex.notafiscal.interfaces.LerTributacaoNota#lerTributacaoNota(org.jdom2.Element)
	 */
	public NotaValidadaAliquota lerTributacaoNota(Element elemento) {
		NotaValidadaAliquota notaValidadaAliquota = new NotaValidadaAliquota();
		TipoImposto tipoImposto = new TipoImposto();
		tipoImposto.setCdTipoImposto(3);
		tipoImposto.setNmTipoImposto("COFINS");
		notaValidadaAliquota.setTipoImpostoAliquota(tipoImposto);
		
		for (Element elementoCOFINS: elemento.getChildren()) {
			
			 switch (elementoCOFINS.getName()) {
			 	
			 case "CST":
				 CST cst = new CST();
				 cst.setNmCST(elementoCOFINS.getValue());
				 notaValidadaAliquota.setCst(cst);
				 break;
				
			 case "qBCProd":
				 notaValidadaAliquota.setQuantidadeBCProduto(Integer.parseInt(elementoCOFINS.getValue()));
				 break;
			 }
		}
		
		return notaValidadaAliquota;

	}

}
