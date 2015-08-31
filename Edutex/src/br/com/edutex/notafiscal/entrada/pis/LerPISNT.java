package br.com.edutex.notafiscal.entrada.pis;

import org.jdom2.Element;

import br.com.edutex.logic.CST;
import br.com.edutex.logic.NotaValidadaAliquota;
import br.com.edutex.logic.TipoImposto;
import br.com.edutex.notafiscal.interfaces.LerTributacaoNota;

public class LerPISNT implements LerTributacaoNota {


/**
	 * L� na nota fiscal as situa��es:
	 * C�digo de Situa��o Tribut�ria do PIS.
		04 - Opera��o Tribut�vel - Tributa��o Monof�sica - (Al�quota Zero);
		06 - Opera��o Tribut�vel - Al�quota Zero;
		07 - Opera��o Isenta da contribui��o;
		08 - Opera��o Sem Incid�ncia da contribui��o;
		09 - Opera��o com suspens�o da contribui��o;
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
