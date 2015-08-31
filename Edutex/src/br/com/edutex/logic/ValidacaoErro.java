/**
 * 
 */
package br.com.edutex.logic;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;

/**
 * @author bruno
 *
 */
@Entity
@SequenceGenerator(name="validacaoErro_sequence", sequenceName="validacaoErro_sequence",
initialValue = 1, allocationSize = 1)
public class ValidacaoErro {
	
	@Id @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "validacaoErro_sequence")
	private int idValidacao;;
	
	
	@ManyToOne(cascade= {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH})
	private TipoErro tpErro;
	
	private String txtAuxiliar;

	/**
	 * @return the idValidacao
	 */
	public int getIdValidacao() {
		return idValidacao;
	}

	/**
	 * @param idValidacao the idValidacao to set
	 */
	public void setIdValidacao(int idValidacao) {
		this.idValidacao = idValidacao;
	}

	/**
	 * @return the tpErro
	 */
	public TipoErro getTpErro() {
		return tpErro;
	}

	/**
	 * @param tpErro the tpErro to set
	 */
	public void setTpErro(TipoErro tpErro) {
		this.tpErro = tpErro;
	}

	/**
	 * @return the txtAuxiliar
	 */
	public String getTxtAuxiliar() {
		return txtAuxiliar;
	}

	/**
	 * @param txtAuxiliar the txtAuxiliar to set
	 */
	public void setTxtAuxiliar(String txtAuxiliar) {
		this.txtAuxiliar = txtAuxiliar;
	}
	
	
}
