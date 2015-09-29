/**
 * 
 */
package br.com.edutex.logic;


import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * Define A finalidade da Nota,
 * podendo ser industrial,comercial
 * @author bruno
 *
 */
@Entity
@SequenceGenerator(name="finalidadeNfe_sequence", sequenceName="finalidadeNFE_sequence",
initialValue = 1, allocationSize = 1)
public class FinalidadeNfe implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 7676166304408335450L;

	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="finalidadeNfe_sequence")
	private int cdFinalidadeNfe;
	
	@Column(nullable = false)
	private String nmFinalidade;
	
	@OneToMany(mappedBy="finalidadeNfe")
	private List<ImpostoNcm> impostos;

	@OneToMany(mappedBy="finalidadeNfe")
	private List<Validacao> validacoes;
	
	/**
	 * Código da Finalidade da nota fiscal
	 * @return the cdFinalidadeNfe
	 */
	public int getCdFinalidadeNfe() {
		return cdFinalidadeNfe;
	}

	/**
	 * Nome da Finalidade da nota fiscal
	 * @return the nmFinalidade
	 */
	public String getNmFinalidade() {
		return nmFinalidade;
	}

	/**
	 * Seta Código da Finalidade da nota fiscal
	 * @param cdFinalidadeNfe the cdFinalidadeNfe to set
	 */
	public void setCdFinalidadeNfe(int cdFinalidadeNfe) {
		this.cdFinalidadeNfe = cdFinalidadeNfe;
	}

	/**
	 * Seta nome da finalidade  
	 * @param nmFinalidade the nmFinalidade to set
	 */
	public void setNmFinalidade(String nmFinalidade) {
		this.nmFinalidade = nmFinalidade;
	}

	/**
	 * @return the impostos
	 */
	public List<ImpostoNcm> getImpostos() {
		return impostos;
	}

	/**
	 * @param impostos the impostos to set
	 */
	public void setImpostos(List<ImpostoNcm> impostos) {
		this.impostos = impostos;
	}

	/**
	 * @return the validacoes
	 */
	public List<Validacao> getValidacoes() {
		return validacoes;
	}

	/**
	 * @param validacoes the validacoes to set
	 */
	public void setValidacoes(List<Validacao> validacoes) {
		this.validacoes = validacoes;
	}

	
}
