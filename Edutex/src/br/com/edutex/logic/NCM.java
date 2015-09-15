/**
 * 
 */
package br.com.edutex.logic;

import java.util.Calendar;
import java.util.Collection;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;

/**
 * @author bruno
 * Está classe representa o NCM(Nomenclatura Comum Mercosul)
 * que classifica o tipo do produto
 * parâmetros: 
 * 			  		  		   	   	
 */
@Entity
@Table(
		name = "ncm",
		uniqueConstraints = @UniqueConstraint(columnNames={"nmncm", "finalidadenfe_cdfinalidadenfe"}, name = "UN_NMNCM")
	)
@SequenceGenerator(name="ncm_sequence", sequenceName="ncm_sequence",
initialValue=1,allocationSize = 1)
public class NCM {
	
	@Id @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="ncm_sequence")
	private int cdNcm;
	
	@Temporal(TemporalType.DATE)
	private Calendar dtCriacao;
	
	@Column(nullable = false)
	private String nmncm;
	
	private double nuValor;
	
	private Calendar dtAtualizacao;
	
	@OneToMany(mappedBy="ncm",cascade = CascadeType.ALL, fetch=FetchType.LAZY)
	private List<ImpostoNcm> impNcm;
	
	@OneToMany(mappedBy="ncm",cascade = CascadeType.ALL, fetch=FetchType.LAZY)
    private List<CST> csts;
	
	@ManyToOne(cascade = {CascadeType.REFRESH, CascadeType.MERGE, CascadeType.DETACH})
	private Empresa empresa;
	
	@ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.REFRESH, CascadeType.MERGE})
	private TipoStatus tpStatus;
	
	@ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH}, fetch= FetchType.EAGER)
	private FinalidadeNfe finalidadeNfe;

	/**
	 * @return the csts
	 */
	public List<CST> getCsts() {
		return csts;
	}

	/**
	 * @param csts the csts to set
	 */
	public void setCsts(List<CST> csts) {
		this.csts = csts;
	}

	/**
	 * @return cdNcm - codigo numerico incremental
	 */
	public int getCdNcm() {
		return cdNcm;
	}

	/**
	 * @param - codigo numerico incremental
	 */
	public void setCdNcm(int cdNcm) {
		this.cdNcm = cdNcm;
	}

	/**
	 * @return dtCriacao - Data da insercao do NCM no sistema
	 */
	public Calendar getDtCriacao() {
		return dtCriacao;
	}

	/**
	 * @param dtCriacao - Data da insercao do NCM no sistema
	 */
	public void setDtCriacao(Calendar dtCriacao) {
		this.dtCriacao = dtCriacao;
	}

	/**
	 * @return nmNCM - valor do codigo real do NCM
	 */
	public String getNmNCM() {
		return nmncm;
	}

	/**
	 * @param nmNCM - valor do codigo real do NCM
	 */
	public void setNmNCM(String nmNCM) {
		this.nmncm = nmNCM;
	}

	/**
	 * @return nuValor - Valor de imposto do NCM
	 */
	public double getNuValor() {
		return nuValor;
	}

	/**
	 * @param nuValor - Valor de imposto do NCM
	 */
	public void setNuValor(double nuValor) {
		this.nuValor = nuValor;
	}

	/**
	 * @return dtAtualizacao - Data em que o NCm foi atualizado
	 */
	public Calendar getDtAtualizacao() {
		return dtAtualizacao;
	}

	/**
	 * @param dtAtualizacao - Data em que o NCm foi atualizado
	 */
	public void setDtAtualizacao(Calendar dtAtualizacao) {
		this.dtAtualizacao = dtAtualizacao;
	}



	/**
	 * @return the impNcm
	 */
	public Collection<ImpostoNcm> getImpNcm() {
		return impNcm;
	}

	/**
	 * @param impNcm the impNcm to set
	 */
	public void setImpNcm(List<ImpostoNcm> impNcm) {
		this.impNcm = impNcm;
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

	public TipoStatus getTpStatus() {
		return tpStatus;
	}

	public void setTpStatus(TipoStatus tpStatus) {
		this.tpStatus = tpStatus;
	}

	public FinalidadeNfe getFinalidadeNfe() {
		return finalidadeNfe;
	}

	public void setFinalidadeNfe(FinalidadeNfe finalidadeNfe) {
		this.finalidadeNfe = finalidadeNfe;
	}

	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + cdNcm;
		result = prime * result + ((csts == null) ? 0 : csts.hashCode());
		result = prime * result
				+ ((dtAtualizacao == null) ? 0 : dtAtualizacao.hashCode());
		result = prime * result
				+ ((dtCriacao == null) ? 0 : dtCriacao.hashCode());
		result = prime * result + ((nmncm == null) ? 0 : nmncm.hashCode());
		long temp;
		temp = Double.doubleToLongBits(nuValor);
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
		NCM other = (NCM) obj;
		if (cdNcm != other.cdNcm)
			return false;
		if (csts == null) {
			if (other.csts != null)
				return false;
		} else if (!csts.equals(other.csts))
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
		if (nmncm == null) {
			if (other.nmncm != null)
				return false;
		} else if (!nmncm.equals(other.nmncm))
			return false;
		if (Double.doubleToLongBits(nuValor) != Double
				.doubleToLongBits(other.nuValor))
			return false;
		return true;
	}

}
