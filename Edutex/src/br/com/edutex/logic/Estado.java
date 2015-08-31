/**
 * 
 */
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
 * Classe representa localidade do Imposto
 * @author bruno
 *
 */
@Entity
public class Estado {
	
	@Id 
	private int cdEstado;
	
	private String nomeEstado;

	@OneToMany(mappedBy="estado", fetch= FetchType.LAZY)
	private List<ImpostoNcmEstado> impostoNcmEstado;
	
	
	/**
	 * @return the cdEstado
	 */
	public int getCdEstado() {
		return cdEstado;
	}

	/**
	 * @param cdEstado the cdEstado to set
	 */
	public void setCdEstado(int cdEstado) {
		this.cdEstado = cdEstado;
	}

	/**
	 * @return the nmFinalidade
	 */
	@Column(nullable= false)
	public String getNomeEstado() {
		return nomeEstado;
	}

	/**
	 * @param nmFinalidade the nmFinalidade to set
	 */
	public void setNomeEstado(String nomeEstado) {
		this.nomeEstado = nomeEstado;
	}

	public List<ImpostoNcmEstado> getImpostoNCmEstado() {
		return impostoNcmEstado;
	}

	public void setImpostoEstado(List<ImpostoNcmEstado> impostoEstado) {
		this.impostoNcmEstado = impostoEstado;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + cdEstado;
		result = prime
				* result
				+ ((impostoNcmEstado == null) ? 0 : impostoNcmEstado.hashCode());
		result = prime * result
				+ ((nomeEstado == null) ? 0 : nomeEstado.hashCode());
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
		Estado other = (Estado) obj;
		if (cdEstado != other.cdEstado)
			return false;
		if (impostoNcmEstado == null) {
			if (other.impostoNcmEstado != null)
				return false;
		} else if (!impostoNcmEstado.equals(other.impostoNcmEstado))
			return false;
		if (nomeEstado == null) {
			if (other.nomeEstado != null)
				return false;
		} else if (!nomeEstado.equals(other.nomeEstado))
			return false;
		return true;
	}
}
