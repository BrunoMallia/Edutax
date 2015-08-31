package br.com.edutex.notafiscal;


import org.jdom2.Element;

import br.com.edutex.logic.NotaValidadaAliquota;
import br.com.edutex.notafiscal.entrada.cofins.LerCOFINSAliq;
import br.com.edutex.notafiscal.entrada.cofins.LerCOFINSNT;
import br.com.edutex.notafiscal.entrada.cofins.LerCOFINSOutr;
import br.com.edutex.notafiscal.entrada.cofins.LerCOFINSQtde;
import br.com.edutex.notafiscal.entrada.icms.LerICMS00;
import br.com.edutex.notafiscal.entrada.icms.LerICMS10;
import br.com.edutex.notafiscal.entrada.icms.LerICMS20;
import br.com.edutex.notafiscal.entrada.icms.LerICMS30;
import br.com.edutex.notafiscal.entrada.icms.LerICMS40;
import br.com.edutex.notafiscal.entrada.icms.LerICMS51;
import br.com.edutex.notafiscal.entrada.icms.LerICMS60;
import br.com.edutex.notafiscal.entrada.icms.LerICMS70;
import br.com.edutex.notafiscal.entrada.icms.LerICMS90;
import br.com.edutex.notafiscal.entrada.icms.LerICMSPART;
import br.com.edutex.notafiscal.entrada.icms.LerICMSSN101;
import br.com.edutex.notafiscal.entrada.icms.LerICMSSN102;
import br.com.edutex.notafiscal.entrada.icms.LerICMSSN201;
import br.com.edutex.notafiscal.entrada.icms.LerICMSSN202;
import br.com.edutex.notafiscal.entrada.icms.LerICMSSN500;
import br.com.edutex.notafiscal.entrada.icms.LerICMSST;
import br.com.edutex.notafiscal.entrada.ipi.LerIPINT;
import br.com.edutex.notafiscal.entrada.ipi.LerIPITrib;
import br.com.edutex.notafiscal.entrada.pis.LerPISAliq;
import br.com.edutex.notafiscal.entrada.pis.LerPISNT;
import br.com.edutex.notafiscal.entrada.pis.LerPISOutr;
import br.com.edutex.notafiscal.entrada.pis.LerPISQtde;
import br.com.edutex.notafiscal.interfaces.EscreverTributacao;
import br.com.edutex.notafiscal.interfaces.LerTributacaoNota;
import br.com.edutex.notafiscal.saida.cofins.EscreverCOFINSAliq;
import br.com.edutex.notafiscal.saida.cofins.EscreverCOFINSNT;
import br.com.edutex.notafiscal.saida.cofins.EscreverCOFINSOutr;
import br.com.edutex.notafiscal.saida.cofins.EscreverCOFINSQtde;
import br.com.edutex.notafiscal.saida.icms.EscreverICMS00;
import br.com.edutex.notafiscal.saida.icms.EscreverICMS10;
import br.com.edutex.notafiscal.saida.icms.EscreverICMS20;
import br.com.edutex.notafiscal.saida.icms.EscreverICMS30;
import br.com.edutex.notafiscal.saida.icms.EscreverICMS40;
import br.com.edutex.notafiscal.saida.icms.EscreverICMS51;
import br.com.edutex.notafiscal.saida.icms.EscreverICMS60;
import br.com.edutex.notafiscal.saida.icms.EscreverICMS70;
import br.com.edutex.notafiscal.saida.icms.EscreverICMS90;
import br.com.edutex.notafiscal.saida.icms.EscreverICMSSN101;
import br.com.edutex.notafiscal.saida.icms.EscreverICMSSN102;
import br.com.edutex.notafiscal.saida.icms.EscreverICMSSN201;
import br.com.edutex.notafiscal.saida.icms.EscreverICMSSN202;
import br.com.edutex.notafiscal.saida.icms.EscreverICMSSN500;
import br.com.edutex.notafiscal.saida.icms.EscreverICMSSN900;
import br.com.edutex.notafiscal.saida.ipi.EscreverIPINT;
import br.com.edutex.notafiscal.saida.ipi.EscreverIPITrib;
import br.com.edutex.notafiscal.saida.pis.EscreverPISNT;
import br.com.edutex.notafiscal.saida.pis.EscreverPISOutr;
import br.com.edutex.notafiscal.saida.pis.EscreverPISQtde;
import br.com.edutex.notafiscal.saida.pis.EscreverPISST;

public class NotaFiscalAliquota {
	
	EscreverTributacao escreveTributacao;
	
	LerTributacaoNota lerTributacao;
	
	
	/**
	 * Método que lê as formas de tributação da nota fiscal
	 * @param elemento
	 */
	public NotaValidadaAliquota tentarLer(Element elemento) {
		return lerTributacao.lerTributacaoNota(elemento);
	}
	
	
	/**
	 * Setanto a classe que implementa a interface que lê os campos de tributação 
	 * da nota fiscal
	 * @param novaTributacaoNota
	 */
	public void setTipoLeitura(LerTributacaoNota novaTributacaoNota) {
		lerTributacao = novaTributacaoNota;
	}
	
	
	public void tentarEscrever(Element element, NotaValidadaAliquota notaValidadaAliquota) {
		escreveTributacao.escreverTributacaoNota(element, notaValidadaAliquota);
	}
	
	public void setTipoEscrita(EscreverTributacao novaNotaFiscal) {
		escreveTributacao = novaNotaFiscal;
	}
	
	
	
	public Element escolherEscritaTributacaoIPI(Element nodeIPI, NotaValidadaAliquota 
			notaValidadaAliquota) {
		
		switch (notaValidadaAliquota.getCst().getNmCST()) {
			
			case "00":
				setTipoEscrita(new EscreverIPITrib());
				break;
				
			case "01":
				setTipoEscrita(new EscreverIPINT());
				break;
				
			case "02":
				setTipoEscrita(new EscreverIPINT());
				break;
				
			case "03":
				setTipoEscrita(new EscreverIPINT());
				break;
				
			case "04":
				setTipoEscrita(new EscreverIPINT());
				break;
				
			case "05":
				setTipoEscrita(new EscreverIPINT());
				break;	
		
			case "49":
				setTipoEscrita(new EscreverIPITrib());
				break;
				
			case "50":
				setTipoEscrita(new EscreverIPITrib());
				break;
			
			case "51":
				setTipoEscrita(new EscreverIPINT());
				break;
				
			case "52":
				setTipoEscrita(new EscreverIPINT());
				break;
				
			case "53":
				setTipoEscrita(new EscreverIPINT());
				break;
				
			case "54":
				setTipoEscrita(new EscreverIPINT());
				break;
				
			case "55":
				setTipoEscrita(new EscreverIPINT());
				break;	
				
			case "99":
				setTipoEscrita(new EscreverIPITrib());
				break;
		
		}
		nodeIPI = escreveTributacao.escreverTributacaoNota(nodeIPI, notaValidadaAliquota);
		
		return nodeIPI;
	}
	
	
	public NotaValidadaAliquota escolherLeituraTributacaoIPI(Element nodeIPI) {
		
		NotaValidadaAliquota notaValidadaAliquota = null;
		
		for(Element node: nodeIPI.getChildren()) {
		
		switch (node.getName()) {
			case "IPITrib":
				setTipoLeitura(new LerIPITrib());
				notaValidadaAliquota = tentarLer(node);
				break;
				
			case "IPINT":
				setTipoLeitura(new LerIPINT());
				notaValidadaAliquota = tentarLer(node);
				break;
		}
		
	}
		
		
		return notaValidadaAliquota;
	}
	
	
	/**
	 * Este método seleciona o método de escrita para o PIS
	 * @param nodePIS
	 * @param notaValidadaAliquota
	 * @return
	 */
	public Element escolherEscritaTributacaoPIS(Element nodePIS, NotaValidadaAliquota notaValidadaAliquota) {
		
		Element elemento = null;
		
		switch (notaValidadaAliquota.getCst().getNmCST()) {
			
			case "01":
				setTipoEscrita(new EscreverPISST());
				break;
			
			case "02":
				setTipoEscrita(new EscreverPISST());
				break;	
				
			case "03":
				setTipoEscrita(new EscreverPISQtde());
				break;
				
			case "04":
				setTipoEscrita(new EscreverPISNT());
				break;
			
			case "05":
				setTipoEscrita(new EscreverPISNT());
				break;	
				
			case "06":
				setTipoEscrita(new EscreverPISNT());
				break;
				
			case "07":
				setTipoEscrita(new EscreverPISNT());
				break;
			
			case "08":
				setTipoEscrita(new EscreverPISNT());
				break;
				
			case "09":
				setTipoEscrita(new EscreverPISNT());
				break;
				
			case "49":
				setTipoEscrita(new EscreverPISOutr());
				break;
				
			case "50":
				setTipoEscrita(new EscreverPISOutr());
				break;
				
			case "51":
				setTipoEscrita(new EscreverPISOutr());
				break;
				
			case "52":
				setTipoEscrita(new EscreverPISOutr());
				break;
				
			case "53":
				setTipoEscrita(new EscreverPISOutr());
				break;
				
			case "54":
				setTipoEscrita(new EscreverPISOutr());
				break;
			
			case "55":
				setTipoEscrita(new EscreverPISOutr());
				break;
				
			case "56":
				setTipoEscrita(new EscreverPISOutr());
				break;
				
			case "60":
				setTipoEscrita(new EscreverPISOutr());
				break;
				
			case "61":
				setTipoEscrita(new EscreverPISOutr());
				break;
				
			case "62":
				setTipoEscrita(new EscreverPISOutr());
				break;
				
			case "63":
				setTipoEscrita(new EscreverPISOutr());
				break;
				
			case "64":
				setTipoEscrita(new EscreverPISOutr());
				break;
				
			case "65":
				setTipoEscrita(new EscreverPISOutr());
				break;	
				
			case "66":
				setTipoEscrita(new EscreverPISOutr());
				break;
				
			case "67":
				setTipoEscrita(new EscreverPISOutr());
				break;
				
			case "70":
				setTipoEscrita(new EscreverPISOutr());
				break;
			
			case "71":
				setTipoEscrita(new EscreverPISOutr());
				break;
				
			case "72":
				setTipoEscrita(new EscreverPISOutr());
				break;
				
			case "73":
				setTipoEscrita(new EscreverPISOutr());
				break;	
			
			case "74":
				setTipoEscrita(new EscreverPISOutr());
				break;
				
			case "75":
				setTipoEscrita(new EscreverPISOutr());
				break;
				
			case "98":
				setTipoEscrita(new EscreverPISOutr());
				break;
				
			case "99":
				setTipoEscrita(new EscreverPISOutr());
				break;	
				
				
		
		}
		
		elemento =  escreveTributacao.escreverTributacaoNota(nodePIS, notaValidadaAliquota);
		
		return elemento;
		
	}
	
	/**
	 * Este método alterada a forma de tributação ICMS de acordo com o valor do CST(Código de sistuação Tributária)
	 * @param nodeICMS
	 * @param notaValidadaAliquota
	 */
	public Element escolherEscritaTributacaoICMS(Element nodeICMS, NotaValidadaAliquota notaValidadaAliquota) {
			
		Element elemento = null;
		
		if (notaValidadaAliquota.getCsosn() == 0) {
			switch (notaValidadaAliquota.getCst().getNmCST()) {
			case "00":
				 setTipoEscrita(new EscreverICMS00());
				break;
			case "10":
				 setTipoEscrita(new EscreverICMS10());
				break;
			case "20":
				 setTipoEscrita(new EscreverICMS20());
				break;
			case "30":
				 setTipoEscrita(new EscreverICMS30());
				break;
			case "40":
				 setTipoEscrita(new EscreverICMS40());
				break;
			case "51":
				 setTipoEscrita(new EscreverICMS51());
				break;
			case "60":
				 setTipoEscrita(new EscreverICMS60());
				break;
			case "70":	
				 setTipoEscrita(new EscreverICMS70());
				break;
			case "90":
				 setTipoEscrita(new EscreverICMS90());
				break;
			}
			
		} else {
			switch (notaValidadaAliquota.getCsosn()) {
				case 101:
					 setTipoEscrita(new EscreverICMSSN101());
					break;
				case 102:
					 setTipoEscrita(new EscreverICMSSN102());
					break;
				case 201:
					 setTipoEscrita(new EscreverICMSSN201());
					break;
				case 202:
					 setTipoEscrita(new EscreverICMSSN202());
					break;
				case 500:
					 setTipoEscrita(new EscreverICMSSN500());
					break;
				case 900:
					 setTipoEscrita(new EscreverICMSSN900());
					break;
					
			}
		}
		
		elemento =  escreveTributacao.escreverTributacaoNota(nodeICMS, notaValidadaAliquota);
		
			return elemento;
	}
	
	
	/**
	 * Método que escolhe a forma de escrita para o tipo de imposto COFINS
	 * @param nodeCOFINS
	 * @param notaValidadaAliquota
	 * @return Element
	 */
	public Element escolherEscritaTributacaoCOFINS(Element nodeCOFINS, NotaValidadaAliquota notaValidadaAliquota) {
		
		Element elemento = null;
		
		if (notaValidadaAliquota.getCsosn() == 0) {
			switch (notaValidadaAliquota.getCst().getNmCST()) {
			
			case "01":
				 setTipoEscrita(new EscreverCOFINSAliq());
				break;
				
			case "02":
				setTipoEscrita(new EscreverCOFINSAliq());
				break;
				
			case "03":
				setTipoEscrita(new EscreverCOFINSQtde());
				break;
			
			case "04":
				setTipoEscrita(new EscreverCOFINSNT());
				break;
			
			case "05":
				setTipoEscrita(new EscreverCOFINSNT());
				break;
				
			case "06":
				setTipoEscrita(new EscreverCOFINSNT());
				break;
				
			case "07":
				setTipoEscrita(new EscreverCOFINSNT());
				break;
				
			case "08":
				setTipoEscrita(new EscreverCOFINSNT());
				break;	
				
			case "09":
				setTipoEscrita(new EscreverCOFINSNT());
				break;
			
			case "49":
				setTipoEscrita(new EscreverCOFINSOutr());
				break;
			
			case "50":
				setTipoEscrita(new EscreverCOFINSOutr());
				break;
				
			case "51":
				setTipoEscrita(new EscreverCOFINSOutr());
				break;
				
			case "52":
				setTipoEscrita(new EscreverCOFINSOutr());
				break;
				
			case "53":
				setTipoEscrita(new EscreverCOFINSOutr());
				break;
				
			case "54":
				setTipoEscrita(new EscreverCOFINSOutr());
				break;
				
			case "55":
				setTipoEscrita(new EscreverCOFINSOutr());
				break;
				
			case "56":
				setTipoEscrita(new EscreverCOFINSOutr());
				break;
				
			case "60":
				setTipoEscrita(new EscreverCOFINSOutr());
				break;
				
			case "61":
				setTipoEscrita(new EscreverCOFINSOutr());
				break;
				
			case "62":
				setTipoEscrita(new EscreverCOFINSOutr());
				break;
				
			case "63":
				setTipoEscrita(new EscreverCOFINSOutr());
				break;
				
			case "64":
				setTipoEscrita(new EscreverCOFINSOutr());
				break;
				
			case "65":
				setTipoEscrita(new EscreverCOFINSOutr());
				break;
				
			case "66":
				setTipoEscrita(new EscreverCOFINSOutr());
				break;
				
			case "67":
				setTipoEscrita(new EscreverCOFINSOutr());
				break;
				
			case "70":
				setTipoEscrita(new EscreverCOFINSOutr());
				break;
				
			case "71":
				setTipoEscrita(new EscreverCOFINSOutr());
				break;
				
			case "72":
				setTipoEscrita(new EscreverCOFINSOutr());
				break;
				
			case "73":
				setTipoEscrita(new EscreverCOFINSOutr());
				break;
				
			case "74":
				setTipoEscrita(new EscreverCOFINSOutr());
				break;
				
			case "75":
				setTipoEscrita(new EscreverCOFINSOutr());
				break;
				
			case "98":
				setTipoEscrita(new EscreverCOFINSOutr());
				break;
				
			case "99":
				setTipoEscrita(new EscreverCOFINSOutr());
				break;
				
				
			}
			
			
		}
		
		elemento =  escreveTributacao.escreverTributacaoNota(nodeCOFINS, notaValidadaAliquota);
		
		return elemento;
	}
	
	
	/**
	 * Método que seleciona o método de leitura para o ICMS
	 * @param nodeICMS
	 * @return NotaValidadaAliquota
	 */
	public NotaValidadaAliquota escolherLeituraTributacaoICMS(Element nodeICMS) {
		
		NotaValidadaAliquota notaValidadaAliquota = null;
		
		for (Element eleICMS: nodeICMS.getChildren()) {
			
			switch (eleICMS.getName()) {
				
			case "ICMS00":
				setTipoLeitura(new LerICMS00());
				notaValidadaAliquota = tentarLer(eleICMS);
				break;
			case "ICMS10":
				setTipoLeitura(new LerICMS10());
				notaValidadaAliquota = tentarLer(eleICMS);
				break;
			case "ICMS20":
				setTipoLeitura(new LerICMS20());
				notaValidadaAliquota = tentarLer(eleICMS);
				break;
			case "ICMS30":
				setTipoLeitura(new LerICMS30());
				notaValidadaAliquota = tentarLer(eleICMS);
				break;
			case "ICMS40":
				setTipoLeitura(new LerICMS40());
				notaValidadaAliquota = tentarLer(eleICMS);
				break;
			case "ICMS51":
				setTipoLeitura(new LerICMS51());
				notaValidadaAliquota = tentarLer(eleICMS);
				break;
			case "ICMS60":
				setTipoLeitura(new LerICMS60());
				notaValidadaAliquota = tentarLer(eleICMS);
				break;
			case "ICMS70":
				 setTipoLeitura(new LerICMS70());
				 notaValidadaAliquota = tentarLer(eleICMS);
				 break;
			case "ICMS90":
				setTipoLeitura(new LerICMS90());
				notaValidadaAliquota = tentarLer(eleICMS);
				break;
			case "ICMSPART":
				setTipoLeitura(new LerICMSPART());
				notaValidadaAliquota = tentarLer(eleICMS);
				break;
			case "ICMSST":
				setTipoLeitura(new LerICMSST());
				notaValidadaAliquota = tentarLer(eleICMS);
				break;
			case "ICMSSN101":
				 setTipoLeitura(new LerICMSSN101());
				 notaValidadaAliquota = tentarLer(eleICMS);
				 break;
			case "ICMSSN102":
				 setTipoLeitura(new LerICMSSN102());
				 notaValidadaAliquota = tentarLer(eleICMS);
				 break;
			case "ICMSSN201":
				setTipoLeitura(new LerICMSSN201());
				notaValidadaAliquota = tentarLer(eleICMS);
				break;
			case "ICMSSN202":
				setTipoLeitura(new LerICMSSN202());
				notaValidadaAliquota = tentarLer(eleICMS);
				break;
			case "ICMSSN500":
				setTipoLeitura(new LerICMSSN500());
				notaValidadaAliquota = tentarLer(eleICMS);
				break;
			case "ICMSSN900":
				setTipoLeitura(new LerICMSSN500());
				notaValidadaAliquota = tentarLer(eleICMS);
				break;
			default:
				break;
			}
			
		}
			
		return notaValidadaAliquota;
		 
	}
	
	/**
	 * Método para escolher o método de leitura para o PIS
	 * @param nodePIS
	 * @return NotaValidadaAliquota
	 */
	public NotaValidadaAliquota escolherLeituraTributacaoPIS(Element nodePIS) {
		
		NotaValidadaAliquota notaValidadaAliquota = null;
		
		for (Element elemPIS: nodePIS.getChildren()) {
			
				switch (elemPIS.getName()) {
				
				case "PISAliq":
					setTipoLeitura(new LerPISAliq());
					notaValidadaAliquota = tentarLer(elemPIS);
					break;
					
				case "PISQtde":
					setTipoLeitura(new LerPISQtde());
					notaValidadaAliquota = tentarLer(elemPIS);
					break;
					
				case "PISNT":
					setTipoLeitura(new LerPISNT());
					notaValidadaAliquota = tentarLer(elemPIS);
					break;
					
				case "PISOutr":
					setTipoLeitura(new LerPISOutr());
					notaValidadaAliquota = tentarLer(elemPIS);
					break;
					
				}
			
		}
		
		 return notaValidadaAliquota;
		
	}
	
	public NotaValidadaAliquota escolherLeituraTributacaoCOFINS(Element nodeCOFINS) {
		
	  Element node = null;
		
		for (Element elemCofins: nodeCOFINS.getChildren()) {
			
			switch (elemCofins.getName()) {
			
			case "COFINSAliq":
				setTipoLeitura(new LerCOFINSAliq());
				node = elemCofins;
				break;
				
			case "COFINSQtde":
				setTipoLeitura(new LerCOFINSQtde());
				node = elemCofins;
				break;
				
			case "COFINSNT":
				setTipoLeitura(new LerCOFINSNT());
				node = elemCofins;
				break;
				
			case "COFINSOutr":
				setTipoLeitura(new LerCOFINSOutr());
				node = elemCofins;
				break;
				
			}
			
		}
		
		return tentarLer(node);
	
	}
}
