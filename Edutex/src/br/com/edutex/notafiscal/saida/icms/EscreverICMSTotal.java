package br.com.edutex.notafiscal.saida.icms;

import java.util.List;
import java.util.Locale;

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
			NotaValidada notaValid, Element nodeTotal) {
		
		float valorICMS = 0,valorPIS = 0,valorCOFINS = 0,valorIPI = 0,valorICMSDeson = 0, valorST = 0, valorProd = 0,valorBC = 0;;
		
		
		List<NotaValidadaItem> listaNotaValidadaItem = notaValid.getNotasValidadaItem(); 
		
		for (NotaValidadaItem notaValidadaItem: listaNotaValidadaItem) {
			
			
			valorProd += notaValidadaItem.getValorBrutoProduto();
			
			
			for(NotaValidadaAliquota notaValidadaAliquota: notaValidadaItem.getNotasValidadasAliquotas()) {
				
				switch (notaValidadaAliquota.getTipoImpostoAliquota().getCdTipoImposto()){
					case 01:
						valorICMS += notaValidadaAliquota.getValorAliquota();
						valorICMS += notaValidadaAliquota.getValorImpostoSTRetidoAnteriormente();
						
						valorICMSDeson += notaValidadaAliquota.getValorAliquotaDesoneracao();
						valorBC += notaValidadaAliquota.getValorBCImposto();
						
						if (valorBC == 0) {
						valorBC += notaValidadaAliquota.getValorBCST();
						valorBC += notaValidadaAliquota.getValorBCSTRetidoAnteriormente();
						}
						
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
		vBC.setText(String.format(Locale.US,"%.2f",valorBC));
		
		Element vICMS = new Element("vICMS", NotaFiscalUtil.getNameSpace());
		vICMS.setText(String.format(Locale.US,"%.2f",valorICMS));
		
		Element vICMSDeson = new Element("vICMSDeson", NotaFiscalUtil.getNameSpace());
		vICMSDeson.setText(String.format(Locale.US,"%.2f",valorICMSDeson));
		
		Element vBCST = new Element("vBCST", NotaFiscalUtil.getNameSpace());
		vBCST.setText(String.format(Locale.US,"%.2f",valorBC));
		
		Element vST = new Element("vST", NotaFiscalUtil.getNameSpace());
		//como eu pego o valor de Substituição Tributári Total
		vST.setText(String.format(Locale.US,"%.2f",valorST));
		
		Element vProd = new Element("vProd",NotaFiscalUtil.getNameSpace());
		vProd.setText(String.format(Locale.US,"%.2f",valorProd));
		
		Element vFrete = new Element("vFrete", NotaFiscalUtil.getNameSpace());
		vFrete.setText(String.format(Locale.US,"%.2f",notaValidada.getValorFrete()));
		
		Element vSeg = new Element("vSeg", NotaFiscalUtil.getNameSpace());
		vSeg.setText(String.format(Locale.US,"%.2f",notaValidada.getValorSeguro()));
		
		Element vDesc = new Element("vDesc", NotaFiscalUtil.getNameSpace());
		vDesc.setText(String.format(Locale.US,"%.2f",notaValidada.getValorDescontoTotal()));
		
		Element vII = new Element("vII", NotaFiscalUtil.getNameSpace());
		vII.setText(String.format(Locale.US,"%.2f",notaValidada.getValorII()));
		
		Element vIPI = new Element("vIPI", NotaFiscalUtil.getNameSpace());
		vIPI.setText(String.format(Locale.US,"%.2f",valorIPI));
		
		Element vPIS = new Element("vPIS", NotaFiscalUtil.getNameSpace());
		vPIS.setText(String.format(Locale.US,"%.2f",valorPIS));
		
		Element vCOFINS = new Element("vCOFINS", NotaFiscalUtil.getNameSpace());
		vCOFINS.setText(String.format(Locale.US,"%.2f",valorCOFINS));
		
		Element vOutros = new Element("vOutro", NotaFiscalUtil.getNameSpace());
		vOutros.setText(String.format(Locale.US,"%.2f",notaValidada.getValorOutros()));
		
		Element vNF = new Element("vNF", NotaFiscalUtil.getNameSpace());
		vNF.setText(String.format(Locale.US,"%.2f",valorProd + notaValidada.getValorFrete() + notaValidada.getValorSeguro() - notaValidada.getValorDescontoTotal() + 
				notaValidada.getValorII()));
		
		Element vTotTrib = new Element("vTotTrib", NotaFiscalUtil.getNameSpace());
		//soma todos os impostos?
		vTotTrib.setText(String.format(Locale.US,"%.2f",notaValid.getValorTotalTributacao()));
		
		
		nodeTotal.removeContent();
		
		
		
		icmsTotal.addContent(vBC);
		icmsTotal.addContent(vICMS);
		icmsTotal.addContent(vICMSDeson);
		icmsTotal.addContent(vBCST);
		icmsTotal.addContent(vST);
		icmsTotal.addContent(vProd);
		icmsTotal.addContent(vFrete);
		icmsTotal.addContent(vSeg);
		icmsTotal.addContent(vDesc);
		icmsTotal.addContent(vII);
		icmsTotal.addContent(vIPI);
		icmsTotal.addContent(vPIS);
		icmsTotal.addContent(vCOFINS);
		icmsTotal.addContent(vOutros);
		icmsTotal.addContent(vNF);
		icmsTotal.addContent(vTotTrib);

		nodeTotal.addContent(icmsTotal);
				
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
