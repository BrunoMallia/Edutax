/**
 * 
 */
package br.com.edutex.logic;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
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

	
}
