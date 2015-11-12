package br.com.edutex.logic;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;


/**
 * Classe que representa os valores de al�quotas e outras valores importantes na nota fiscal.
 * @author Bruno Mallia
 *
 */
@Entity
public class NotaValidadaAliquota implements Serializable, Comparable<NotaValidadaAliquota> {
	

	private static final long serialVersionUID = 1L;

	@EmbeddedId
	@ManyToOne (cascade={CascadeType.ALL})
	private TipoImposto tipoImpostoAliquota;
	
	@EmbeddedId
	@ManyToOne (cascade = {CascadeType.ALL})
	private NotaValidadaItem notaValidadaItem;
	
	@ManyToOne (cascade = {CascadeType.ALL})
	private CST cst;
	
	@Column(nullable = true)
	private float valorAliquota;
	
	@Column(nullable = true)
	private float percentualAliquota;

	private int origem;
	
	private int modBCImposto;
	
	private float valorBCImposto;
	
	private int modBCSTImposto;
	
	private float percentualMargemValorAdicionadoST;
	
	private float percentualReducaoBC;
	
	private float percentualReducaoBCST;
	
	private float valorBCST;
	
	private float valorAliquotaST;
	
	private float percentualAliquotaST;
	
	private float valorAliquotaDesoneracao;

	private int motivoDesoneracaoImposto;
	
	private float valorOperacaoImposto;
	
	private float valorBCSTRetidoAnteriormente;
	
	private float valorImpostoSTRetidoAnteriormente;
	
	private float percentualBCOperacao;
	
	private String ufST;
	
	private float valorBCSTDest;
	
	private int csosn;
	
	private float percentualCreditoSN;
	
	private float valorCreditoSN;
	
	private int quantidadeBCProduto;
	
	private int quantidadeUnidadePadrao;
	
	private float valorUnidadeTributavel;
	
	private float valorAliquotaProduto;
	
	private int codigoEnquadramento;
	
	/** pega o valor do objeto TipoImposto
	 * exemplos de valor: ICMS, PIS ,COFINS, IPI
	 * @return TipoImposto
	 */
	public TipoImposto getTipoImpostoAliquota() {
		return tipoImpostoAliquota;
	}
	
	/** seta o valor do objeto TipoImposto
	 * exemplos de valor: ICMS, PIS ,COFINS, IPI
	 * @param tipoImpostoAliquota
	 */
	public void setTipoImpostoAliquota(TipoImposto tipoImpostoAliquota) {
		this.tipoImpostoAliquota = tipoImpostoAliquota;
	}
	
	
	/** Pega o valor do C�digo de Situa��o Tribut�ria
	 * @return CST
	 */
	public CST getCst() {
		return cst;
	}


	/**Seta o valor do C�digo de Situa��o Tribut�ria
	 * @param cst
	 */
	public void setCst(CST cst) {
		this.cst = cst;
	}



	/** Pega o valor do objeto NotaValidadaItem
	 * NotaValidadaItem representa todos os campos associados por item do produto
	 * na nota fiscal
	 * @return
	 */
	public NotaValidadaItem getNotaValidadaItem() {
		return notaValidadaItem;
	}


	/**
	 *  Seta o valor do objeto NotaValidadaItem
	 * NotaValidadaItem representa todos os campos associados por item do produto
	 * @param notaValidadaItem
	 */
	public void setNotaValidadaItem(NotaValidadaItem notaValidadaItem) {
		this.notaValidadaItem = notaValidadaItem;
	}

	/** Pega o valor da origem da mercadoria: 0 - Nacional 
	1 - Estrangeira - Importa��o direta 
	2 - Estrangeira - Adquirida no mercado interno
	 * @return the origem
	 */
	public int getOrigem() {
		return origem;
	}

	/**
	 *Seta a  origem da mercadoria: 
		0 - Nacional 
		1 - Estrangeira - Importa��o direta 
		2 - Estrangeira - Adquirida no mercado interno
	 * @param origem the origem to set
	 */
	public void setOrigem(int origem) {
		this.origem = origem;
	}

	/**
	 * Pega a Modalidade de determina��o da BC do ICMS: 
		0 - Margem Valor Agregado (%);
		1 - Pauta (valor);
		2 - Pre�o Tabelado M�ximo (valor);
		3 - Valor da Opera��o.
	 * @return the modBC
	 */
	public int getModBCImposto() {
		return modBCImposto;
	}

	/**
	 * Seta a Modalidade de determina��o da BC do ICMS: 
		0 - Margem Valor Agregado (%);
		1 - Pauta (valor);
		2 - Pre�o Tabelado M�ximo (valor);
		3 - Valor da Opera��o.
	 * @param modBC the modBC to set
	 */
	public void setModBCImposto(int modBCImposto) {
		this.modBCImposto = modBCImposto;
	}

	/**
	 *Pega o  Valor da base de c�lculo do imposto
	 * @return the valorBCImposto
	 */
	public float getValorBCImposto() {
		return valorBCImposto;
	}

	/**
	 * Seta o Valor da base de c�lculo do imposto
	 * @param valorBCImposto the valorBCImposto to set
	 */
	public void setValorBCImposto(float valorBCImposto) {
		this.valorBCImposto = valorBCImposto;
	}

	/**
	 * Pega o Modo de Base de C�lculo do imposto 
	 *  Indicador de presen�a do comprador no estabelecimento comercial no momento da oepra��o
		0-N�o se aplica (ex.: Nota Fiscal complementar ou de ajuste;
		1-Opera��o presencial;
		2-N�o presencial,internet;
		3-N�o presencial, teleatendimento;
		4-NFC-e entrega em domic�lio;9-N�o presencial, outros)
	 * @return the modBCISTmposto
	 */
	public int getModBCSTImposto() {
		return modBCSTImposto;
	}

	/**
	 * Seta o Modo de Base de C�lculo do imposto 
	 *  Indicador de presen�a do comprador no estabelecimento comercial no momento da oepra��o
		0-N�o se aplica (ex.: Nota Fiscal complementar ou de ajuste;
		1-Opera��o presencial;
		2-N�o presencial,internet;
		3-N�o presencial, teleatendimento;
		4-NFC-e entrega em domic�lio;9-N�o presencial, outros)
	 * @param modBCISTmposto the modBCISTmposto to set
	 */
	public void setModBCSTImposto(int modBCSTImposto) {
		this.modBCSTImposto = modBCSTImposto;
	}

	/**
	 * Pega o Percentual da Margem de Valor Adicionado  ST
	 * @return the percentualMargemValorAdicionadoST
	 */
	public float getPercentualMargemValorAdicionadoST() {
		return percentualMargemValorAdicionadoST;
	}

	/**
	 * Seta Percentual da Margem de Valor Adicionado ICMS ST
	 * @param percentualMargemValorAdicionadoST the percentualMargemValorAdicionadoST to set
	 */
	public void setPercentualMargemValorAdicionadoST(
			float percentualMargemValorAdicionadoST) {
		this.percentualMargemValorAdicionadoST = percentualMargemValorAdicionadoST;
	}

	/**
	 * Pega o Percentual de redu��o da BC ICMS ST
	 * @return the percentualReducaoBCST
	 */
	public float getPercentualReducaoBCST() {
		return percentualReducaoBCST;
	}

	/**
	 * Seta o Percentual de redu��o da BC ICMS ST
	 * @param percentualReducaoBCST the percentualReducaoBCST to set
	 */
	public void setPercentualReducaoBCST(float percentualReducaoBCST) {
		this.percentualReducaoBCST = percentualReducaoBCST;
	}

	/** 
	 * Pega o Valor da BC do ICMS ST
	 * @return the valorBCST
	 */
	public float getValorBCST() {
		return valorBCST;
	}

	/**
	 * Seta o Valor da BC do ICMS ST
	 * @param valorBCST the valorBCST to set
	 */
	public void setValorBCST(float valorBCST) {
		this.valorBCST = valorBCST;
	}


	/**
	 * Pega o Percentual da Al�quota do imposto por substitui��o tribut�ria
	 * @return the percentualAliquotaST
	 */
	public float getPercentualAliquotaST() {
		return percentualAliquotaST;
	}

	/**
	 * Seta o Percentual da Al�quota do imposto por substitui��o tribut�ria
	 * @param percentualAliquotaST the percentualAliquotaST to set
	 */
	public void setPercentualAliquotaST(float percentualAliquotaST) {
		this.percentualAliquotaST = percentualAliquotaST;
	}

	
	/**
	 * Pega o valor da al�quota do imposto
	 * @return float
	 */
	public float getValorAliquota() {
		return valorAliquota;
	}
	
	/**
	 * Seta o valor da al�quota do imposto
	 * @param valorAliquota
	 */
	public void setValorAliquota(float valorAliquota) {
		this.valorAliquota = valorAliquota;
	}

	/**
	 * Seta o valor do percentual da al�quota do imposto
	 * @return
	 */
	public float getPercentualAliquota() {
		return percentualAliquota;
	}

	/**
	 * Seta o valor do percentual da al�quota do imposto
	 * @param percentualAliquota
	 */
	public void setPercentualAliquota(float percentualAliquota) {
		this.percentualAliquota = percentualAliquota;
	}

	/**
	 * Pega o valor da al�quota da substitui��o tribut�ria
	 * @return the valorAliquotaST
	 */
	public float getValorAliquotaST() {
		return valorAliquotaST;
	}

	/**
	 * Seta o valor da al�quota da substitui��o tribut�ria
	 * @param valorAliquotaST the valorAliquotaST to set
	 */
	public void setValorAliquotaST(float valorAliquotaST) {
		this.valorAliquotaST = valorAliquotaST;
	}

	/**
	 * Pega o valor do percentual de Redu��o da base de c�lculo
	 * @return the percentualReducaoBC
	 */
	public float getPercentualReducaoBC() {
		return percentualReducaoBC;
	}

	/**
	 * Seta o valor do percentual de Redu��o da base de c�lculo
	 * @param percentualReducaoBC the percentualReducaoBC to set
	 */
	public void setPercentualReducaoBC(float percentualReducaoBC) {
		this.percentualReducaoBC = percentualReducaoBC;
	}

	/**
	 * Pega Valor do ICMS de desonera��o
	 * Campo espec�fico para ICMS20.
	 * @return the valorAliquotaDesoneracao
	 */
	public float getValorAliquotaDesoneracao() {
		return valorAliquotaDesoneracao;
	}

	/**
	 *  Seta Valor do ICMS de desonera��o
	 * Campo espec�fico para ICMS20.
	 * @param valorAliquotaDesoneracao the valorAliquotaDesoneracao to set
	 */
	public void setValorAliquotaDesoneracao(float valorAliquotaDesoneracao) {
		this.valorAliquotaDesoneracao = valorAliquotaDesoneracao;
	}

	/**
	 * Pega o Motivo da desonera��o do ICMS:
	 * 3-Uso na agropecu�ria;
	 * 9-Outros;
	 * 12-Fomento agropecu�rio
	 * @return the motivoDesoneracaoImposto
	 */
	public int getMotivoDesoneracaoImposto() {
		return motivoDesoneracaoImposto;
	}

	/**
	 *  Seta o Motivo da desonera��o do ICMS:
	 * 3-Uso na agropecu�ria;
	 * 9-Outros;
	 * 12-Fomento agropecu�rio
	 * @param motivoDesoneracaoImposto the motivoDesoneracaoImposto to set
	 */
	public void setMotivoDesoneracaoImposto(int motivoDesoneracaoImposto) {
		this.motivoDesoneracaoImposto = motivoDesoneracaoImposto;
	}

	/**
	 * Pega o Valor do ICMS da Opera��o
	 * @return the valorOperacaoImposto
	 */
	public float getValorOperacaoImposto() {
		return valorOperacaoImposto;
	}

	/**
	 * Seta o Valor do ICMS da Opera��o
	 * @param valorOperacaoImposto the valorOperacaoImposto to set
	 */
	public void setValorOperacaoImposto(float valorOperacaoImposto) {
		this.valorOperacaoImposto = valorOperacaoImposto;
	}

	/**
	 * pega o valor da BC do ICMS ST retido na UF remetente
	 * @return the valorBCSTRetidoAnteriormente
	 */
	public float getValorBCSTRetidoAnteriormente() {
		return valorBCSTRetidoAnteriormente;
	}

	/**
	 * Seta o valor da BC do ICMS ST retido na UF remetente
	 * @param valorBCSTRetidoAnteriormente the valorBCSTRetidoAnteriormente to set
	 */
	public void setValorBCSTRetidoAnteriormente(float valorBCSTRetidoAnteriormente) {
		this.valorBCSTRetidoAnteriormente = valorBCSTRetidoAnteriormente;
	}

	/**
	 * Pega o valor do imposto ST retido na UF remetente 
	 * @return the valorImpostoSTRetidoAnteriormente
	 */
	public float getValorImpostoSTRetidoAnteriormente() {
		return valorImpostoSTRetidoAnteriormente;
	}

	/**
	 * Seta o valor do imposto ST retido na UF remetente
	 * @param valorImpostoSTRetidoAnteriormente the valorImpostoSTRetidoAnteriormente to set
	 */
	public void setValorImpostoSTRetidoAnteriormente(
			float valorImpostoSTRetidoAnteriormente) {
		this.valorImpostoSTRetidoAnteriormente = valorImpostoSTRetidoAnteriormente;
	}

	/**
	 * @return the percentualBCOperacao
	 */
	public float getPercentualBCOperacao() {
		return percentualBCOperacao;
	}

	/**
	 * @param percentualBCOperacao the percentualBCOperacao to set
	 */
	public void setPercentualBCOperacao(float percentualBCOperacao) {
		this.percentualBCOperacao = percentualBCOperacao;
	}

	/**
	 * @return the ufST
	 */
	public String getUfST() {
		return ufST;
	}

	/**
	 * @param ufST the ufST to set
	 */
	public void setUfST(String ufST) {
		this.ufST = ufST;
	}

	/**Pega o valor da BC do ICMS ST da UF destino
	 * @return the valorBCSTDest
	 */
	public float getValorBCSTDest() {
		return valorBCSTDest;
	}

	/**
	 * Seta o valor da BC do ICMS ST da UF destino
	 * @param valorBCSTDest the valorBCSTDest to set
	 */
	public void setValorBCSTDest(float valorBCSTDest) {
		this.valorBCSTDest = valorBCSTDest;
	}

	/**
	 * Pega o valor do CSOSN
	 * CSOSN - C�digo Situa��o sobre o Simples Nacional
	 * @return the csosn
	 */
	public int getCsosn() {
		return csosn;
	}

	/**
	 * Seta o valor do CSOSN
	 * CSOSN - C�digo Situa��o sobre o Simples Nacional
	 * @param csosn the csosn to set
	 */
	public void setCsosn(int csosn) {
		this.csosn = csosn;
	}

	/**
	 * Pega o valor do percentual de cr�dito Simples Nacional
	 * @return the percentualCreditoSN
	 */
	public float getPercentualCreditoSN() {
		return percentualCreditoSN;
	}

	/**
	 * Seta o valor do percentual de cr�dito Simples Nacional
	 * @param percentualCreditoSN the percentualCreditoSN to set
	 */
	public void setPercentualCreditoSN(float percentualCreditoSN) {
		this.percentualCreditoSN = percentualCreditoSN;
	}

	/**
	 * Pegar o valor cr�dito do ICMS que pode ser aproveitado nos termos do art. 23 da LC 123 (Simples Nacional)
	 * @return the valorCreditoSN
	 */
	public float getValorCreditoSN() {
		return valorCreditoSN;
	}

	/**
	 * Setar o valor cr�dito do ICMS que pode ser aproveitado nos termos do art. 23 da LC 123 (Simples Nacional) 
	 * @param valorCreditoSN the valorCreditoSN to set
	 */
	public void setValorCreditoSN(float valorCreditoSN) {
		this.valorCreditoSN = valorCreditoSN;
	}

	/**
	 * Pega a quantidade Vendida  (NT2011/004)
	 * @return the quantidadeBCProduto
	 */
	public int getQuantidadeBCProduto() {
		return quantidadeBCProduto;
	}

	/**
	 * Seta a quantidade Vendida  (NT2011/004)
	 * @param quantidadeBCProduto the quantidadeBCProduto to set
	 */
	public void setQuantidadeBCProduto(int quantidadeBCProduto) {
		this.quantidadeBCProduto = quantidadeBCProduto;
	}

	/**
	 * Pega o valor da al�quota do PIS (em reais) (NT2011/004)
	 * @return the valorAliquotaProduto
	 */
	public float getValorAliquotaProduto() {
		return valorAliquotaProduto;
	}

	/**
	 * Seta o valor al�quota do PIS (em reais) (NT2011/004)
	 * @param valorAliquotaProduto the valorAliquotaProduto to set
	 */
	public void setValorAliquotaProduto(float valorAliquotaProduto) {
		this.valorAliquotaProduto = valorAliquotaProduto;
	}

	@Override
	public int compareTo(NotaValidadaAliquota o) {
		if (this.tipoImpostoAliquota.getCdTipoImposto() < o.tipoImpostoAliquota.getCdTipoImposto()) {
			return -1;
		} else {
			if (this.tipoImpostoAliquota.getCdTipoImposto() > o.tipoImpostoAliquota.getCdTipoImposto()) {
				return 1;
			}
		}
		return 0;
	}

	/**
	 * M�todo que pega a quantidade total na unidade padr�o para tributa��o 
	 * @return
	 */
	public int getQuantidadeUnidadePadrao() {
		return quantidadeUnidadePadrao;
	}

	
	/**
	 * M�todo que seta a quanidade unidade padr�o para a tributa��o
	 * @param quantidadeUnidadePadrao
	 */
	public void setQuantidadeUnidadePadrao(int quantidadeUnidadePadrao) {
		this.quantidadeUnidadePadrao = quantidadeUnidadePadrao;
	}

	/**
	 * M�todo que pega o valor por Unidade Tribut�vel. Informar o valor do imposto
	 *  Pauta por unidade de medida.
	 * @return
	 */
	public float getValorUnidadeTributavel() {
		return valorUnidadeTributavel;
	}

	/**
	 * M�todo que seta o alor por Unidade Tribut�vel. Informar o valor do imposto
	 * Pauta por unidade de medida.
	 * @param valorUnidadeTributavel 
	 */
	public void setValorUnidadeTributavel(float valorUnidadeTributavel) {
		this.valorUnidadeTributavel = valorUnidadeTributavel;
	}

	public int getCodigoEnquadramento() {
		return codigoEnquadramento;
	}

	public void setCodigoEnquadramento(int codigoEnquadramento) {
		this.codigoEnquadramento = codigoEnquadramento;
	}

	
}
