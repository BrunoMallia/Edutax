
package br.com.edutex.logic;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;


/**
 * Classe que representa os tipos de erro que podem 
 * na validacao da nota fiscal
 * @author bruno
 *
 */
@Entity
public class TipoErro {
	
	@Id 
	private int cdTipoErro;
	
	@Column(nullable = false)
	private String nmTipoErro;
	
	@OneToMany(mappedBy="tpErro", fetch = FetchType.EAGER, cascade = {CascadeType.ALL})
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
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + cdTipoErro;
		result = prime * result
				+ ((nmTipoErro == null) ? 0 : nmTipoErro.hashCode());
		result = prime * result
				+ ((validacoes == null) ? 0 : validacoes.hashCode());
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
		TipoErro other = (TipoErro) obj;
		if (cdTipoErro != other.cdTipoErro)
			return false;
		if (nmTipoErro == null) {
			if (other.nmTipoErro != null)
				return false;
		} else if (!nmTipoErro.equals(other.nmTipoErro))
			return false;
		if (validacoes == null) {
			if (other.validacoes != null)
				return false;
		} else if (!validacoes.equals(other.validacoes))
			return false;
		return true;
	}
	
}
