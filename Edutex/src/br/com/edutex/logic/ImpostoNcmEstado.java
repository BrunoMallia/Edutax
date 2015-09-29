/**
 * 
 */
package br.com.edutex.logic;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;


/**
 * Esta Classe representa os mvs e mvajustados
 * para cada estado do imposto ICMS
 * @author TCC
 *
 */
@Entity
public class ImpostoNcmEstado implements Serializable {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	@ManyToOne (cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH, CascadeType.DETACH})
	private ImpostoNcm impostoNCM;
	
	@EmbeddedId
	@ManyToOne (cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH, CascadeType.DETACH})
	private Estado estado;
	
	
	private float mva;
	
	private float mvaAjustado;
	
	
	/**
	 * Pega o valor do objeto ImpostoNCM
	 * @return ImpostoNCM
	 */
	public ImpostoNcm getImpostoNCM() {
		return impostoNCM;
	}
	
	/**
	 * Seta o valor do ImpostoNCM
	 * @param impostoNCM
	 */
	public void setImpostoNCM(ImpostoNcm impostoNCM) {
		this.impostoNCM = impostoNCM;
	}

	/**
	 * Pega o valor do objeto Estado
	 * @return
	 */
	public Estado getEstado() {
		return estado;
	}


	/**
	 * Seta o objeto estado
	 * @param estado
	 */
	public void setEstado(Estado estado) {
		this.estado = estado;
	}

	public float getMvaAjustado() {
		return mvaAjustado;
	}

	public void setMvaAjustado(float mvaAjustado) {
		this.mvaAjustado = mvaAjustado;
	}

	public float getMva() {
		return mva;
	}

	public void setMva(float mva) {
		this.mva = mva;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((estado == null) ? 0 : estado.hashCode());
		result = prime * result
				+ ((impostoNCM == null) ? 0 : impostoNCM.hashCode());
		result = prime * result + Float.floatToIntBits(mva);
		result = prime * result + Float.floatToIntBits(mvaAjustado);
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
		ImpostoNcmEstado other = (ImpostoNcmEstado) obj;
		if (estado == null) {
			if (other.estado != null)
				return false;
		} else if (!estado.equals(other.estado))
			return false;
		if (impostoNCM == null) {
			if (other.impostoNCM != null)
				return false;
		} else if (!impostoNCM.equals(other.impostoNCM))
			return false;
		if (Float.floatToIntBits(mva) != Float.floatToIntBits(other.mva))
			return false;
		if (Float.floatToIntBits(mvaAjustado) != Float
				.floatToIntBits(other.mvaAjustado))
			return false;
		return true;
	}

	
}
