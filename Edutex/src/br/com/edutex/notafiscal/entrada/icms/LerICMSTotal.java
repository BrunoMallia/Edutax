package br.com.edutex.notafiscal.entrada.icms;

import org.jdom2.Element;

import br.com.edutex.logic.NotaValidada;

public class LerICMSTotal  {

	/**
	 * Método que lê da nota fiscal o total de tributos e campos que representam a soma
	 * dos valores 
	 * @param elemento
	 * @param notaValidada
	 * @return
	 */
	public NotaValidada lerICMSTotal(Element elemento, NotaValidada notaValidada) {
		
		for (Element elementoICMSTotal: elemento.getChildren()){
			
			switch (elementoICMSTotal.getName()) {
			case "vICMS":
				notaValidada.setValorICMSTotal(Float.parseFloat(elementoICMSTotal.getValue()));
				break;
				
			case "vICMSDeson":
				notaValidada.setValorICMSDesonTotal(Float.parseFloat(elementoICMSTotal.getValue()));
				break;
				
			case "vBCST":
			    notaValidada.setValorBCTotal(Float.parseFloat(elementoICMSTotal.getValue()));
				break;
			
			case "vST":
				notaValidada.setValorSTTotal(Float.parseFloat(elementoICMSTotal.getValue()));
				break;
			
				
			case "vProd":
				notaValidada.setValorProdutoTotal(Float.parseFloat(elementoICMSTotal.getValue()));
				break;

				
			case "vFrete":
				notaValidada.setValorFrete(Float.parseFloat(elementoICMSTotal.getValue()));
				break;
				
			case "vSeg":
				notaValidada.setValorSeguro(Float.parseFloat(elementoICMSTotal.getValue()));
				break;
				
			case "vDesc": 
				notaValidada.setValorDescontoTotal(Float.parseFloat(elementoICMSTotal.getValue()));
				break;
				
			case "vII":
				notaValidada.setValorII(Float.parseFloat(elementoICMSTotal.getValue()));
				break;
				
			case "vIPI":
				notaValidada.setValorIPITotal(Float.parseFloat(elementoICMSTotal.getValue()));
				break;

			case "vPIS":
				notaValidada.setValorPISTotal(Float.parseFloat(elementoICMSTotal.getValue()));
				break;

			case "vCOFINS":
				notaValidada.setValorCOFINSTotal(Float.parseFloat(elementoICMSTotal.getValue()));
				break;

			case "vOutro":
				notaValidada.setValorOutros(Float.parseFloat(elementoICMSTotal.getValue()));
				break;

				
			case "vNF":	
			  notaValidada.setValorNotaFiscal(Float.parseFloat(elementoICMSTotal.getValue()));
			  break;
			
			case "vTotTrib":
				notaValidada.setValorTotalTributacao(Float.parseFloat(elementoICMSTotal.getValue()));
				break;
				
			}
			
		}
		
		return notaValidada;
	}
	

}
