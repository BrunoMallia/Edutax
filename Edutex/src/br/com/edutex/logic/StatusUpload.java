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
 * Classe que representa o status do arquivo que foi feito upload
 * @author bruno
 *
 */
@Entity
@SequenceGenerator(name="statusUpload_sequence", sequenceName="statusUpload_sequence",
initialValue=1, allocationSize=1)
public class StatusUpload {
	
	@Id @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="statusUpload_sequence")
	private int cdStatus;
	
	private String nmStatus;
	
	@OneToMany(mappedBy= "statusUpload", cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH})
	private List<UploadCSV> uploadCsv;

	/**
	 * 
	 * @return the cdStatus
	 */
	public int getCdStatus() {
		return cdStatus;
	}

	/**
	 * @param cdStatus the cdStatus to set
	 */
	public void setCdStatus(int cdStatus) {
		this.cdStatus = cdStatus;
	}

	/**
	 * @return the nmStatus
	 */
	public String getNmStatus() {
		return nmStatus;
	}

	/**
	 * @param nmStatus the nmStatus to set
	 */
	public void setNmStatus(String nmStatus) {
		this.nmStatus = nmStatus;
	}

	/**
	 * @return the uploadCsv
	 */
	public List<UploadCSV> getUploadCsv() {
		return uploadCsv;
	}

	/**
	 * @param uploadCsv the uploadCsv to set
	 */
	public void setUploadCsv(List<UploadCSV> uploadCsv) {
		this.uploadCsv = uploadCsv;
	}


	
}
