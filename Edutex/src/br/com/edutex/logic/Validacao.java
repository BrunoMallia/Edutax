/**
 * 
 */
package br.com.edutex.logic;

import java.util.Calendar;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * Classe que armazena a nota fiscal portada
 * e a gerada para validacao
 * @author bruno
 *
 */
@Entity
@SequenceGenerator(name="validacao_sequence", sequenceName="validacao_sequence",
initialValue = 1, allocationSize = 1)
public class Validacao {
	
	@Id @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "validacao_sequence")
	private int idValidacao;
	
	@ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.REFRESH, CascadeType.MERGE})
	private NFE nfeGerada;
	
	@ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.REFRESH, CascadeType.MERGE})
	private NFE nfeInicial;
	
	@ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.REFRESH, CascadeType.MERGE})
	private FinalidadeNfe finalidadeNfe;
	
	@ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.REFRESH, CascadeType.MERGE})
	private Empresa empresa;
	
	@Temporal(TemporalType.DATE)
	private Calendar dtValidacao;

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
	 * @return the nfeGerada
	 */
	public NFE getNfeGerada() {
		return nfeGerada;
	}

	/**
	 * @param nfeGerada the nfeGerada to set
	 */
	public void setNfeGerada(NFE nfeGerada) {
		this.nfeGerada = nfeGerada;
	}

	/**
	 * @return the nfeInicial
	 */
	public NFE getNfeInicial() {
		return nfeInicial;
	}

	/**
	 * @param nfeInicial the nfeInicial to set
	 */
	public void setNfeInicial(NFE nfeInicial) {
		this.nfeInicial = nfeInicial;
	}

	/**
	 * @return the finalidadeNfe
	 */
	public FinalidadeNfe getFinalidadeNfe() {
		return finalidadeNfe;
	}

	/**
	 * @param finalidadeNfe the finalidadeNfe to set
	 */
	public void setFinalidadeNfe(FinalidadeNfe finalidadeNfe) {
		this.finalidadeNfe = finalidadeNfe;
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

	/**
	 * @return the dtValidacao
	 */
	public Calendar getDtValidacao() {
		return dtValidacao;
	}

	/**
	 * @param dtValidacao the dtValidacao to set
	 */
	public void setDtValidacao(Calendar dtValidacao) {
		this.dtValidacao = dtValidacao;
	}

}
