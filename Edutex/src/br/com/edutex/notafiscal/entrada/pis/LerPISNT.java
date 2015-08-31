package br.com.edutex.notafiscal.entrada.pis;

import org.jdom2.Element;

import br.com.edutex.logic.CST;
import br.com.edutex.logic.NotaValidadaAliquota;
import br.com.edutex.logic.TipoImposto;
import br.com.edutex.notafiscal.interfaces.LerTributacaoNota;

public class LerPISNT implements LerTributacaoNota {


/**
	 * Lê na nota fiscal as situações:
	 * Código de Situação Tributária do PIS.
		04 - Operação Tributável - Tributação Monofásica - (Alíquota Zero);
		06 - Operação Tributável - Alíquota Zero;
		07 - Operação Isenta da contribuição;
		08 - Operação Sem Incidência da contribuição;
		09 - Operação com suspensão da contribuição;
	 * @param elemento
	 * @return
	 * @see br.com.edutex.notafiscal.interfaces.LerTributacaoNota#lerTributacaoNota(org.jdom2.Element)
	 */
	public NotaValidadaAliquota lerTributacaoNota(Element elemento) {
		NotaValidadaAliquota notaValidadaAliquota = new NotaValidadaAliquota();
		TipoImposto tipoImposto = new TipoImposto();
		tipoImposto.setCdTipoImposto(2);
		tipoImposto.setNmTipoImposto("PIS");
		notaValidadaAliquota.setTipoImpostoAliquota(tipoImposto);
		 
		for (Element elementoPIS: elemento.getChildren()) {
			  
			 
			 if (elementoPIS.getName().equals("CST")) {
				 CST cst = new CST();
					cst.setNmCST(elementoPIS.getValue());
					notaValidadaAliquota.setCst(cst);
			 		
			 }
		 }
		 
		return notaValidadaAliquota;
	}

}
