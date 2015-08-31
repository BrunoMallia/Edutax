/**
 * 
 */
package br.com.edutex.logic;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

/**
 * Classe que representa o regime de tributação das empresas
 * @author bruno
 *
 */
@Entity
@SequenceGenerator(name="regimeTibutario_sequence", sequenceName = "regimeTributario_sequence",
initialValue = 1, allocationSize = 1)
@Table(
		name = "RegimeTributarioEmpresa",
		uniqueConstraints = @UniqueConstraint(columnNames={"nmTipoImposto"}, name = "UN_REG_TR_EMP")
	)
public class RegimeTributarioEmpresa {
	
	@Id @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="regimeTibutario_sequence" )
	private int cdTipoImposto;
	
	private String nmTipoImposto;
	
    @OneToMany(mappedBy="regimeTibutario")
	private List<Empresa> empresas;

	/**
	 * @return the codTipoImposto
	 */
	public int getCodTipoImposto() {
		return cdTipoImposto;
	}

	/**
	 * @return the empresas
	 */
	public List<Empresa> getEmpresas() {
		return empresas;
	}

	/**
	 * @param empresas the empresas to set
	 */
	public void setEmpresas(List<Empresa> empresas) {
		this.empresas = empresas;
	}

	/**
	 * @param codTipoImposto the codTipoImposto to set
	 */
	public void setCodTipoImposto(int codTipoImposto) {
		this.cdTipoImposto = codTipoImposto;
	}

	/**
	 * @return the nmTipoImposto
	 */
	public String getNmTipoImposto() {
		return nmTipoImposto;
	}

	/**
	 * @param nmTipoImposto the nmTipoImposto to set
	 */
	public void setNmTipoImposto(String nmTipoImposto) {
		this.nmTipoImposto = nmTipoImposto;
	}
	
	

}
