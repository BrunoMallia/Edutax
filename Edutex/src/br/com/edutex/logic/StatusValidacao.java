/**
 * 
 */
package br.com.edutex.logic;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

/**
 * Classe que representa o status de validacao
 * @author bruno
 *
 */
@Entity
@SequenceGenerator(name="statusValidacao_sequence", sequenceName="statusValidacao_sequence",
initialValue=1, allocationSize=1)
public class StatusValidacao {
	
	@Id @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="statusValidacao_sequence")
	private int idStatusValidacao;
	
	private String nmStatusValidacao;

	/**
	 * @return the idStatusValidacao
	 */
	public int getIdStatusValidacao() {
		return idStatusValidacao;
	}

	/**
	 * @param idStatusValidacao the idStatusValidacao to set
	 */
	public void setIdStatusValidacao(int idStatusValidacao) {
		this.idStatusValidacao = idStatusValidacao;
	}

	/**
	 * @return the nmStatusValidacao
	 */
	public String getNmStatusValidacao() {
		return nmStatusValidacao;
	}

	/**
	 * @param nmStatusValidacao the nmStatusValidacao to set
	 */
	public void setNmStatusValidacao(String nmStatusValidacao) {
		this.nmStatusValidacao = nmStatusValidacao;
	}
	
	
}
