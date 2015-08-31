package br.com.edutex.notafiscal.entrada.cofins;

import org.jdom2.Element;

import br.com.edutex.logic.CST;
import br.com.edutex.logic.NotaValidadaAliquota;
import br.com.edutex.logic.TipoImposto;
import br.com.edutex.notafiscal.interfaces.LerTributacaoNota;

public class LerCOFINSNT implements LerTributacaoNota {

	/* 
	 * Lê os campos da nota fiscal referente ao COFINS nas seguintes situaçõa:
	 * Código de Situação Tributária do COFINS:
		04 - Operação Tributável - Tributação Monofásica - (Alíquota Zero);
		06 -Operação Tributável - Alíquota Zero;
		07 - Operação Isenta da contribuição;
		08 - Operação Sem Incidência da contribuição;
		09 - Operação com suspensão da contribuição;
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
		
			 }
		}
		
		return notaValidadaAliquota;

	}

}
