/**
 * 
 */
package br.com.edutex.logic;

import java.util.Calendar;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;

/**
 * Classe que representa o COdigo de situacao tributario
 * @author bruno
 *
 */
@Entity
@SequenceGenerator(name="cst_sequence", sequenceName="cst_sequence",
initialValue=1, allocationSize=1)
public class CST {
	
	@Id @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="cst_sequence")
	private int cdCst;
	
	private double nuPercentualReducao;
	
	private Calendar dtAtualizacao;
	
	private String nmCST;
	
	private Calendar dtCriacao;
	
	@ManyToOne
	private TipoImposto tpImposto;
	
	@ManyToOne
	private NCM ncm;
	
	@ManyToOne
	private TipoStatus tpStatus;
	
	@ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.REFRESH, CascadeType.MERGE})
	private Empresa empresa;
	

	/**
	 * Retorna o Codigo do cadastro de NCM
	 * @return the cdCst
c	 */
	public int getCdCst() {
		return cdCst;
	}

	/**
	 * Retorna o percentual de reducao do cst
	 * @return the nuPercentualReducao
	 */
	public double getNuPercentualReducao() {
		return nuPercentualReducao;
	}

	/**
	 * Retorna a data de atualizacao
	 * @return the dtAtualizacao
	 */
	public Calendar getDtAtualizacao() {
		return dtAtualizacao;
	}

	/**
	 * Retorna o Codigo de situacao tributaria
	 * @return the nmCST
	 */
	public String getNmCST() {
		return nmCST;
	}

	/**
	 * Retorna a data de criacao
	 * @return the dtCriacao
	 */
	public Calendar getDtCriacao() {
		return dtCriacao;
	}

	/**
	 * Seta o Codigo para cadastro de NCM
	 * @param cdCst the cdCst to set
	 */
	public void setCdCst(int cdCst) {
		this.cdCst = cdCst;
	}

	/**
	 * Seta o percentual de reducao
	 * @param nuPercentualReducao the nuPercentualReducao to set
	 */
	public void setNuPercentualReducao(double nuPercentualReducao) {
		this.nuPercentualReducao = nuPercentualReducao;
	}

	/**
	 * Seta a data de atualizacao do CST
	 * @param dtAtualizacao the dtAtualizacao to set
	 */
	public void setDtAtualizacao(Calendar dtAtualizacao) {
		this.dtAtualizacao = dtAtualizacao;
	}

	/**
	 * Seta o Codigo de CST
	 * @param nmCST the nmCST to set
	 */
	public void setNmCST(String nmCST) {
		this.nmCST = nmCST;
	}

	/**
	 * Seta Data de Criacao do CST
	 * @param dtCriacao the dtCriacao to set
	 */
	public void setDtCriacao(Calendar dtCriacao) {
		this.dtCriacao = dtCriacao;
	}

	/**
	 * @return the tpImposto
	 */
	public TipoImposto getTpImposto() {
		return tpImposto;
	}

	/**
	 * @param tpImposto the tpImposto to set
	 */
	public void setTpImposto(TipoImposto tpImposto) {
		this.tpImposto = tpImposto;
	}

	/**
	 * @return the ncm
	 */
	public NCM getNcm() {
		return ncm;
	}

	/**
	 * @param ncm the ncm to set
	 */
	public void setNcm(NCM ncm) {
		this.ncm = ncm;
	}

	/**
	 * @return the tpStatus
	 */
	public TipoStatus getTpStatus() {
		return tpStatus;
	}

	/**
	 * @param tpStatus the tpStatus to set
	 */
	public void setTpStatus(TipoStatus tpStatus) {
		this.tpStatus = tpStatus;
	}

	public Empresa getEmpresa() {
		return empresa;
	}

	public void setEmpresa(Empresa empresa) {
		this.empresa = empresa;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + cdCst;
		result = prime * result
				+ ((dtAtualizacao == null) ? 0 : dtAtualizacao.hashCode());
		result = prime * result
				+ ((dtCriacao == null) ? 0 : dtCriacao.hashCode());
		result = prime * result + ((ncm == null) ? 0 : ncm.hashCode());
		result = prime * result + ((nmCST == null) ? 0 : nmCST.hashCode());
		long temp;
		temp = Double.doubleToLongBits(nuPercentualReducao);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CST other = (CST) obj;
		if (cdCst != other.cdCst)
			return false;
		if (dtAtualizacao == null) {
			if (other.dtAtualizacao != null)
				return false;
		} else if (!dtAtualizacao.equals(other.dtAtualizacao))
			return false;
		if (dtCriacao == null) {
			if (other.dtCriacao != null)
				return false;
		} else if (!dtCriacao.equals(other.dtCriacao))
			return false;
		if (ncm == null) {
			if (other.ncm != null)
				return false;
		} else if (!ncm.equals(other.ncm))
			return false;
		if (nmCST == null) {
			if (other.nmCST != null)
				return false;
		} else if (!nmCST.equals(other.nmCST))
			return false;
		if (Double.doubleToLongBits(nuPercentualReducao) != Double
				.doubleToLongBits(other.nuPercentualReducao))
			return false;
		return true;
	}
	
}
