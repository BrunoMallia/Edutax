
package br.com.edutex.logic;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;

/**
 * Classe que representa os tipos de erro que podem 
 * na validacao da nota fiscal
 * @author bruno
 *
 */
@Entity
@SequenceGenerator(name="tipoErro_sequence", sequenceName="tipoErro_sequence",
initialValue = 1, allocationSize = 1)
public class TipoErro {
	
	@Id @GeneratedValue(strategy =GenerationType.SEQUENCE, generator = "tipoErro_sequence")
	private int cdTipoErro;
	
	@Column(nullable = false)
	private String nmTipoErro;
	
	@OneToMany(mappedBy="tpErro", fetch = FetchType.LAZY )
	private List<ValidacaoErro> validacoes; 

	/**
	 * @return the cdTipoErro
	 */
	public int getCdTipoErro() {
		return cdTipoErro;
	}

	/**
	 * @param cdTipoErro the cdTipoErro to set
	 */
	public void setCdTipoErro(int cdTipoErro) {
		this.cdTipoErro = cdTipoErro;
	}

	/**
	 * @return the nmTipoErro
	 */
	public String getNmTipoErro() {
		return nmTipoErro;
	}

	/**
	 * @param nmTipoErro the nmTipoErro to set
	 */
	public void setNmTipoErro(String nmTipoErro) {
		this.nmTipoErro = nmTipoErro;
	}

	/**
	 * @return the validacoes
	 */
	public List<ValidacaoErro> getValidacoes() {
		return validacoes;
	}

	/**
	 * @param validacoes the validacoes to set
	 */
	public void setValidacoes(List<ValidacaoErro> validacoes) {
		this.validacoes = validacoes;
	}
	
	
}
