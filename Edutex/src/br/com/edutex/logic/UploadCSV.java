
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
 * Esta classe representa o os parâmetros dos
 * arquivos feitos upload
 * @author bruno
 *
 */
@Entity
@SequenceGenerator(name="upload_sequence", sequenceName="upload_sequence",
initialValue=1, allocationSize=1)
public class UploadCSV {
	
	@Id @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="upload_sequence")
	private int idUpload;
	
	private String nmArquivo;
	
	private String nmPath;
	
	private String txLog;
	
	@Temporal(TemporalType.DATE)
    private Calendar dtDataUpload;
    
    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH})
    private StatusUpload statusUpload;

	/**
	 * @return the idUpload
	 */
	public int getIdUpload() {
		return idUpload;
	}

	/**
	 * @param idUpload the idUpload to set
	 */
	public void setIdUpload(int idUpload) {
		this.idUpload = idUpload;
	}

	/**
	 * @return the nmArquivo
	 */
	public String getNmArquivo() {
		return nmArquivo;
	}

	/**
	 * @param nmArquivo the nmArquivo to set
	 */
	public void setNmArquivo(String nmArquivo) {
		this.nmArquivo = nmArquivo;
	}

	/**
	 * @return the nmPath
	 */
	public String getNmPath() {
		return nmPath;
	}

	/**
	 * @param nmPath the nmPath to set
	 */
	public void setNmPath(String nmPath) {
		this.nmPath = nmPath;
	}

	/**
	 * @return the txLog
	 */
	public String getTxLog() {
		return txLog;
	}

	/**
	 * @param txLog the txLog to set
	 */
	public void setTxLog(String txLog) {
		this.txLog = txLog;
	}

	/**
	 * @return the dtDataUpload
	 */
	public Calendar getDtDataUpload() {
		return dtDataUpload;
	}

	/**
	 * @param dtDataUpload the dtDataUpload to set
	 */
	public void setDtDataUpload(Calendar dtDataUpload) {
		this.dtDataUpload = dtDataUpload;
	}

	/**
	 * @return the statusUpload
	 */
	public StatusUpload getStatusUpload() {
		return statusUpload;
	}

	/**
	 * @param statusUpload the statusUpload to set
	 */
	public void setStatusUpload(StatusUpload statusUpload) {
		this.statusUpload = statusUpload;
	}
}
