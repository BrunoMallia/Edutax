/**
 * 
 */
package br.com.edutex.logic;

import java.io.Serializable;
import java.util.Calendar;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.SequenceGenerator;

/**
 * Classe que representa a Nota Fiscal Eletronica
 * @author bruno
 *
 */
@Entity
@SequenceGenerator(name="NFE_sequence", sequenceName="NFE_sequence",
initialValue = 1, allocationSize = 1)
public class NFE implements Serializable, Cloneable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -4562154992722486518L;

	@Id @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="NFE_sequence")
	private int cdNfe;
	
	@Column(nullable = false)
	private String nmNfe;
	
	@Column(nullable = false)
	private String nmFilePath;
	
	private Calendar dtUpload;
	
	@OneToOne(fetch= FetchType.EAGER, cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH}, orphanRemoval = true)
	@PrimaryKeyJoinColumn
	private NotaValidada notaValidada;
	
	private boolean notaComplementar;

	/**
	 * @return the cdNfe
	 */
	public int getCdNfe() {
		return cdNfe;
	}

	/**
	 * @param cdNfe the cdNfe to set
	 */
	public void setCdNfe(int cdNfe) {
		this.cdNfe = cdNfe;
	}

	/**
	 * @return the nmNfe
	 */
	public String getNmNfe() {
		return nmNfe;
	}

	/**
	 * @param nmNfe the nmNfe to set
	 */
	public void setNmNfe(String nmNfe) {
		this.nmNfe = nmNfe;
	}

	/**
	 * @return the nmFilePath
	 */
	public String getNmFilePath() {
		return nmFilePath;
	}

	/**
	 * @param nmFilePath the nmFilePath to set
	 */
	public void setNmFilePath(String nmFilePath) {
		this.nmFilePath = nmFilePath;
	}

	/**
	 * @return the dtUpload
	 */
	public Calendar getDtUpload() {
		return dtUpload;
	}

	/**
	 * @param dtUpload the dtUpload to set
	 */
	public void setDtUpload(Calendar dtUpload) {
		this.dtUpload = dtUpload;
	}

	public NotaValidada getNotaValidada() {
		return notaValidada;
	}

	public void setNotaValidada(NotaValidada notaValidada) {
		this.notaValidada = notaValidada;
	}
	

	public boolean isNotaComplementar() {
		return notaComplementar;
	}

	public void setNotaComplementar(boolean notaComplementar) {
		this.notaComplementar = notaComplementar;
	}

	public Object clone() throws CloneNotSupportedException{
		return super.clone();
	}
	
}
