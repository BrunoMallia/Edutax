/**
 * 
 */
package br.com.edutex.logic;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;

/**
 * Classe que representa o tipo de imposto
 * exemplo: ICMS,IPI,IOF
 * @author bruno
 *
 */
@Entity
@SequenceGenerator(name="tpImposto_sequence", sequenceName="tpImposto_sequence",
initialValue=1,allocationSize = 1)
public class TipoImposto {
	
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="tpImposto_sequence")
	private int cdTipoImposto;
	
	private String nmTipoImposto;
	
	@OneToMany(mappedBy="tipoImposto")
	private List<ImpostoNcm> impostoNCM;

	/**
	 * @return the cdTipoImposto
	 */
	public int getCdTipoImposto() {
		return cdTipoImposto;
	}

	/**
	 * @return the nmTipoImposto
	 */
	public String getNmTipoImposto() {
		return nmTipoImposto;
	}

	/**
	 * @param cdTipoImposto the cdTipoImposto to set
	 */
	public void setCdTipoImposto(int cdTipoImposto) {
		this.cdTipoImposto = cdTipoImposto;
	}

	/**
	 * @param nmTipoImposto the nmTipoImposto to set
	 */
	public void setNmTipoImposto(String nmTipoImposto) {
		this.nmTipoImposto = nmTipoImposto;
	}

	/**
	 * Retorna lista com os impostos do NCM
	 * @return the impostoNCM
	 */
	public List<ImpostoNcm> getImpostoNCM() {
		return impostoNCM;
	}

	/**
	 * Seta lista com os impostos do NCM
	 * @param impostoNCM the impostoNCM to set
	 */
	public void setImpostoNCM(List<ImpostoNcm> impostoNCM) {
		this.impostoNCM = impostoNCM;
	}
	
}
