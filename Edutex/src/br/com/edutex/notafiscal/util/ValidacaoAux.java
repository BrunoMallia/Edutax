package br.com.edutex.notafiscal.util;

import java.io.Serializable;
import java.util.Calendar;

import br.com.edutex.logic.Validacao;

public class ValidacaoAux implements Serializable  {

	
	
	private Calendar dataCriacao;
	
	private String nmNotaLogica;
	
	private String nmFornecedor;
	
	private float baseCalculoAtacadista;
	
	private float aliquotaIPI;
	
	private float valorIPI;
	
	private String finalidade;
	
	
	public ValidacaoAux(Calendar dataCriacao, String nmNotaLogica, String nmFornecedor,
			float baseCalculoAtacadista) {
		this.dataCriacao = dataCriacao;
		this.nmNotaLogica = nmNotaLogica;
		this.nmFornecedor = nmFornecedor;
		this.baseCalculoAtacadista = baseCalculoAtacadista;
		
		
	}
	
	public ValidacaoAux() {
		
	}
	
	public Calendar getDataCriacao() {
		return dataCriacao;
	}

	public void setDataCriacao(Calendar dataCriacao) {
		this.dataCriacao = dataCriacao;
	}

	public String getNmNotaLogica() {
		return nmNotaLogica;
	}

	public void setNmNotaLogica(String nmNotaLogica) {
		this.nmNotaLogica = nmNotaLogica;
	}

	public String getNmFornecedor() {
		return nmFornecedor;
	}

	public void setNmFornecedor(String nmFornecedor) {
		this.nmFornecedor = nmFornecedor;
	}

	public float getBaseCalculoAtacadista() {
		return baseCalculoAtacadista;
	}

	public void setBaseCalculoAtacadista(float baseCalculoAtacadista) {
		this.baseCalculoAtacadista = baseCalculoAtacadista;
	}

	public float getAliquotaIPI() {
		return aliquotaIPI;
	}

	public void setAliquotaIPI(float aliquotaIPI) {
		this.aliquotaIPI = aliquotaIPI;
	}

	public float getValorIPI() {
		return valorIPI;
	}

	public void setValorIPI(float valorIPI) {
		this.valorIPI = valorIPI;
	}

	public String getFinalidade() {
		return finalidade;
	}

	public void setFinalidade(String finalidade) {
		this.finalidade = finalidade;
	}


	
	
	
}
