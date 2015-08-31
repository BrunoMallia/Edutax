package br.com.edutex.notafiscal.saida.icms;

import java.util.List;

import org.jdom2.Element;

import br.com.edutex.logic.NotaValidada;
import br.com.edutex.logic.NotaValidadaAliquota;
import br.com.edutex.logic.NotaValidadaItem;
import br.com.edutex.notafiscal.entrada.icms.LerICMSTotal;
import br.com.edutex.notafiscal.interfaces.EscreverTributacaoTotal;
import br.com.edutex.notafiscal.util.NotaFiscalUtil;

public class EscreverICMSTotal implements EscreverTributacaoTotal {



	/* 
	 * Es método calcula os campos totais do ICMS
	 * 
	 * @see br.com.edutex.notafiscal.interfaces.EscreverTributacaoTotal#escreverTributacaoTotalNota(java.util.List, org.jdom2.Element)
	 */
	@Override
	public Element escreverTributacaoTotalNota(
			List<NotaValidadaItem> listaNotaValidadaItem, Element nodeTotal) {
		
		float valorICMS = 0,valorPIS = 0,valorCOFINS = 0,valorIPI = 0,valorICMSDeson = 0, valorST = 0, valorProd = 0;
		
		
		for (NotaValidadaItem notaValidadaItem: listaNotaValidadaItem) {
			
			
			valorProd += notaValidadaItem.getValorBrutoProduto();
			
			
			for(NotaValidadaAliquota notaValidadaAliquota: notaValidadaItem.getNotasValidadasAliquotas()) {
				
				switch (notaValidadaAliquota.getTipoImpostoAliquota().getCdTipoImposto()){
					case 01:
						valorICMS += notaValidadaAliquota.getValorAliquota();
						valorICMSDeson += notaValidadaAliquota.getValorAliquotaDesoneracao();
						break;
						
					case 02:
						valorPIS += notaValidadaAliquota.getValorAliquota();
						break;
						
					case 03:
						valorCOFINS += notaValidadaAliquota.getValorAliquota();
						break;
						
					case 04:
						valorIPI += notaValidadaAliquota.getValorAliquota();
						break;
				
				}
					
					
					valorST += notaValidadaAliquota.getValorAliquotaST();
					
				
				
			}
					
		}
		
		LerICMSTotal lerTributacaoTotal =  new LerICMSTotal();
		
		NotaValidada notaValidadaAliquota = new NotaValidada();
		
		NotaValidada notaValidada = lerTributacaoTotal.lerICMSTotal(nodeTotal,notaValidadaAliquota); 

		
		Element icmsTotal = new Element("ICMSTot", NotaFiscalUtil.getNameSpace());
		
		Element vBC = new Element("vBC", NotaFiscalUtil.getNameSpace());
		vBC.setText(String.valueOf(notaValidada.getValorICMSTotal()));
		
		Element vICMS = new Element("vICMS", NotaFiscalUtil.getNameSpace());
		vICMS.setText(String.valueOf(valorICMS));
		
		Element vICMSDeson = new Element("vICMSDeson", NotaFiscalUtil.getNameSpace());
		vICMSDeson.setText(String.valueOf(valorICMSDeson));
		
		Element vBCST = new Element("vBCST", NotaFiscalUtil.getNameSpace());
		vBCST.setText(String.valueOf(notaValidada.getValorBCTotal()));
		
		Element vST = new Element("vST", NotaFiscalUtil.getNameSpace());
		//como eu pego o valor de Substituição Tributári Total
		vST.setText(String.valueOf(notaValidada.getValorSTTotal()));
		
		Element vProd = new Element("vProd",NotaFiscalUtil.getNameSpace());
		vProd.setText(String.valueOf(valorProd));
		
		Element vFrete = new Element("vFrete", NotaFiscalUtil.getNameSpace());
		vFrete.setText(String.valueOf(notaValidada.getValorFrete()));
		
		Element vSeg = new Element("vSeg", NotaFiscalUtil.getNameSpace());
		vSeg.setText(String.valueOf(notaValidada.getValorSeguro()));
		
		Element vDesc = new Element("vDesc", NotaFiscalUtil.getNameSpace());
		vDesc.setText(String.valueOf(notaValidada.getValorDescontoTotal()));
		
		Element vII = new Element("vII", NotaFiscalUtil.getNameSpace());
		vII.setText(String.valueOf(notaValidada.getValorII()));
		
		Element vIPI = new Element("vIPI", NotaFiscalUtil.getNameSpace());
		vIPI.setText(String.valueOf(valorIPI));
		
		Element vPIS = new Element("vPIS", NotaFiscalUtil.getNameSpace());
		vPIS.setText(String.valueOf(valorPIS));
		
		Element vCOFINS = new Element("vCOFINS", NotaFiscalUtil.getNameSpace());
		vCOFINS.setText(String.valueOf(valorCOFINS));
		
		Element vOutros = new Element("vOutro", NotaFiscalUtil.getNameSpace());
		vOutros.setText(String.valueOf(notaValidada.getValorOutros()));
		
		Element vNF = new Element("vNF", NotaFiscalUtil.getNameSpace());
		vNF.setText(String.valueOf(notaValidada.getValorNotaFiscal()));
		
		Element vTotTrib = new Element("vTotTrib", NotaFiscalUtil.getNameSpace());
		//soma todos os impostos?
		vTotTrib.setText(String.valueOf(notaValidada.getValorTotalTributacao()));
		
		
		nodeTotal.removeContent();
		nodeTotal.addContent(icmsTotal);
		nodeTotal.addContent(vBC);
		nodeTotal.addContent(vICMS);
		nodeTotal.addContent(vICMSDeson);
		nodeTotal.addContent(vBCST);
		nodeTotal.addContent(vST);
		nodeTotal.addContent(vProd);
		nodeTotal.addContent(vFrete);
		nodeTotal.addContent(vSeg);
		nodeTotal.addContent(vDesc);
		nodeTotal.addContent(vII);
		nodeTotal.addContent(vIPI);
		nodeTotal.addContent(vPIS);
		nodeTotal.addContent(vCOFINS);
		nodeTotal.addContent(vOutros);
		nodeTotal.addContent(vNF);
		nodeTotal.addContent(vTotTrib);
		
		return nodeTotal;
	}

	/**
	 * Método que os valores totais dos campos de ICMSTotal
	 * @param listaNotaValidadaAliquota
	 * @return
	 */
	private NotaValidadaAliquota calcularValorICMSTotal(NotaValidadaItem notaValidadaItem){
		
		
		NotaValidadaAliquota notaValidadaAliquota = new NotaValidadaAliquota();
		
		List<NotaValidadaAliquota> listaNotaValidadaAliquota = notaValidadaItem.getNotasValidadasAliquotas();
		
		for (NotaValidadaAliquota nota: listaNotaValidadaAliquota) {
			notaValidadaAliquota.setValorAliquota(notaValidadaAliquota.getValorAliquota() + nota.getValorAliquota());
			notaValidadaAliquota.setValorAliquotaDesoneracao(notaValidadaAliquota.getValorAliquotaDesoneracao() + nota.getValorAliquotaDesoneracao());
			notaValidadaAliquota.setValorAliquotaST(notaValidadaAliquota.getValorAliquotaST() + nota.getValorAliquotaST());
			
			
		}
		
		return null;
	}
	

}
