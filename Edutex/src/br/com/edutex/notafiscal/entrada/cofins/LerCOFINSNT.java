package br.com.edutex.notafiscal.entrada.cofins;

import org.jdom2.Element;

import br.com.edutex.logic.CST;
import br.com.edutex.logic.NotaValidadaAliquota;
import br.com.edutex.logic.TipoImposto;
import br.com.edutex.notafiscal.interfaces.LerTributacaoNota;

public class LerCOFINSNT implements LerTributacaoNota {

	/* 
	 * L� os campos da nota fiscal referente ao COFINS nas seguintes situa��a:
	 * C�digo de Situa��o Tribut�ria do COFINS:
		04 - Opera��o Tribut�vel - Tributa��o Monof�sica - (Al�quota Zero);
		06 -Opera��o Tribut�vel - Al�quota Zero;
		07 - Opera��o Isenta da contribui��o;
		08 - Opera��o Sem Incid�ncia da contribui��o;
		09 - Opera��o com suspens�o da contribui��o;
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
