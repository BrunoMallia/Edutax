/**
 * 
 */
package br.com.edutex.logic;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

/**
 * Classe que representa o tipo status 
 * @author bruno
 *
 */
@Entity
@Table(
		name = "TipoStatus",
		uniqueConstraints = @UniqueConstraint(columnNames={"nmStatus"}, name = "UN_NMSTATUS")
	)
@SequenceGenerator(name="tpStatus_sequence", sequenceName="tpStatus_sequence",
initialValue=1, allocationSize=1)
public class TipoStatus {
	
	@Id @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="tpStatus_sequence")
	private int cdStatus;
	
	private String nmStatus;
	
	@OneToMany(mappedBy="tpStatus",fetch = FetchType.LAZY)
	private List<CST> csts; 
	
	@OneToMany(mappedBy="tpStatus", fetch= FetchType.EAGER)
	private List<Empresa> empresas;
	
	@OneToMany(mappedBy="tpStatus", fetch= FetchType.LAZY)
	private List<Usuario> usuarios;
	
	@OneToMany(mappedBy="tpStatus", fetch= FetchType.LAZY)
	private List<NCM> ncms;
	
	
	
	/**
	 * @return the cdStatus
	 */
	public int getCdStatus() {
		return cdStatus;
	}
	/**
	 * Retorna o status
	 * @return the nmStatus
	 */
	public String getNmStatus() {
		return nmStatus;
	}
	/**
	 * Retorna o codigo de status
	 * @param cdStatus the cdStatus to set
	 */
	public void setCdStatus(int cdStatus) {
		this.cdStatus = cdStatus;
	}
	/**
	 * Seta o Status
	 * @param nmStatus the nmStatus to set
	 */
	public void setNmStatus(String nmStatus) {
		this.nmStatus = nmStatus;
	}
	/**
	 * Retorna lista de CSTS
	 * @return the csts
	 */
	public List<CST> getCsts() {
		return csts;
	}
	/**
	 * Seta lista de csts
	 * @param csts the csts to set
	 */
	public void setCsts(List<CST> csts) {
		this.csts = csts;
	}
	/**
	 * @return the usuarios
	 */
	public List<Usuario> getUsuarios() {
		return usuarios;
	}
	/**
	 * @param usuarios the usuarios to set
	 */
	public void setUsuarios(List<Usuario> usuarios) {
		this.usuarios = usuarios;
	}
	/**
	 * @return the ncms
	 */
	public List<NCM> getNcms() {
		return ncms;
	}
	/**
	 * @param ncms the ncms to set
	 */
	public void setNcms(List<NCM> ncms) {
		this.ncms = ncms;
	}
	
}
