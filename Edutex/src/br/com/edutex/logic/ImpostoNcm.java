/**
 * 
 */
package br.com.edutex.logic;

import java.io.Serializable;
import java.util.Calendar;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;



/**
 * @author bruno  
 * Classe que representa os valores de imposto de cada NCM
 *
 */
@Entity
public class ImpostoNcm implements Serializable  {
	

	private static final long serialVersionUID = -8980909406121191290L;
	
	@EmbeddedId
	@ManyToOne(cascade= {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH})
	private NCM ncm;
	
	@EmbeddedId
	@ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH})
	private TipoImposto tipoImposto;
	
	
	@EmbeddedId
	@ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH})
	private FinalidadeNfe finalidadeNfe;
	
	@Column(nullable= false)
	private double nuPercentualImposto;
	
	@Column(nullable = true)
	private Calendar dtAtualizacao;

	@ManyToOne(cascade = {CascadeType.REFRESH, CascadeType.MERGE})
	private Empresa empresa;
	
	@ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.REFRESH, CascadeType.MERGE})
	private TipoStatus tpStatus;

	
/*	@Column(nullable = true)
	@OneToMany(mappedBy="impostoNCM",  cascade = {CascadeType.ALL})
	private List<ImpostoNcmEstado> impostosNcmEstado;*/
	
	
	/**
	 * Retorna o numero percentual do imposto
	 * @return the nuPercentualImposto
	 */
	public double getNuPercentualImposto() {
		return nuPercentualImposto;
	}

	/**
	 * Seta o numero percentual do imposto
	 * @param nuPercentualImposto the nuPercentualImposto to set
	 */
	public void setNuPercentualImposto(double nuPercentualImposto) {
		this.nuPercentualImposto = nuPercentualImposto;
	}

	/**
	 * Retorna a data de atualizacao
	 * @return the dtAtualizacao
	 */
	public Calendar getDtAtualizacao() {
		return dtAtualizacao;
	}

	/**
	 * Seta a data de atualizacao
	 * @param dtAtualizacao the dtAtualizacao to set
	 */
	public void setDtAtualizacao(Calendar dtAtualizacao) {
		this.dtAtualizacao = dtAtualizacao;
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
	 * @return the tipoImposto
	 */
	public TipoImposto getTipoImposto() {
		return tipoImposto;
	}

	/**
	 * @param tipoImposto the tipoImposto to set
	 */
	public void setTipoImposto(TipoImposto tipoImposto) {
		this.tipoImposto = tipoImposto;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((ncm == null) ? 0 : ncm.hashCode());
		result = prime * result + ((tipoImposto == null) ? 0 : tipoImposto.hashCode());
		return result;
	}



	/**
	 * @return the finalidadeNfe
	 */
	public FinalidadeNfe getFinalidadeNfe() {
		return finalidadeNfe;
	}

	/**
	 * @param finalidadeNfe the finalidadeNfe to set
	 */
	public void setFinalidadeNfe(FinalidadeNfe finalidadeNfe) {
		this.finalidadeNfe = finalidadeNfe;
	}

	/**
	 * @return the empresa
	 */
	public Empresa getEmpresa() {
		return empresa;
	}

	/**
	 * @param empresa the empresa to set
	 */
	public void setEmpresa(Empresa empresa) {
		this.empresa = empresa;
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

	/*public List<ImpostoNcmEstado> getImpostosNcmEstado() {
		return impostosNcmEstado;
	}

	public void setImpostosNcmEstado(List<ImpostoNcmEstado> impostosNcmEstado) {
		this.impostosNcmEstado = impostosNcmEstado;
	}*/

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ImpostoNcm other = (ImpostoNcm) obj;
		if (ncm == null) {
			if (other.ncm != null)
				return false;
		} else if (!ncm.equals(other.ncm))
			return false;
		if (tipoImposto == null) {
			if (other.tipoImposto != null)
				return false;
		} else if (!tipoImposto.equals(other.tipoImposto))
			return false;
		return true;
	}


	
}
