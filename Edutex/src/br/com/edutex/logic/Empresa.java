/**
 * 
 */
package br.com.edutex.logic;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

/**
 * @author bruno
 * Classe que representa Empresa
 */
@Entity
@Table(
		name = "Empresa",
		uniqueConstraints = @UniqueConstraint(columnNames={"nmEmpresa"}, name = "UN_NMEMPRESA")
	)
@SequenceGenerator(name="emp_sequence", sequenceName="emp_sequence",
initialValue=2,allocationSize = 1)
public class Empresa {
	
	@Id @GeneratedValue(strategy= GenerationType.SEQUENCE, generator="emp_sequence")
	private int cdcnpj;
	
	private String nmEmpresa;
	
	private String cnpj;
	
	@ManyToOne(cascade = {CascadeType.REFRESH, CascadeType.MERGE, CascadeType.DETACH}, fetch = FetchType.LAZY)
	private TipoStatus tpStatus;
	
	@ManyToOne(cascade = { CascadeType.REFRESH, CascadeType.MERGE, CascadeType.DETACH})
	private RegimeTributarioEmpresa regimeTibutario;

	@OneToMany(mappedBy= "empresa",fetch = FetchType.LAZY)
	private List<Validacao> validacoes;
	
	@OneToMany(mappedBy="empresa",fetch = FetchType.LAZY)
	private List<NCM> ncm;
	
	@OneToMany(mappedBy="pk.empresa", fetch = FetchType.LAZY)
	private List<UsuarioEmpresa> usuarioEmpresas = new ArrayList<UsuarioEmpresa>();

	/**
	 * @return the nmEmpresa
	 */
	public String getNmEmpresa() {
		return nmEmpresa;
	}

	/**
	 * @param nmEmpresa the nmEmpresa to set
	 */
	public void setNmEmpresa(String nmEmpresa) {
		this.nmEmpresa = nmEmpresa;
	}

	/**
	 * @return the tpStatus
	 */
	public TipoStatus getTpStatus() {
		return tpStatus;
	}

	/**
	 * @param tpStatus the tpStatus to set
	 */
	public void setTpStatus(TipoStatus tpStatus) {
		this.tpStatus = tpStatus;
	}

	/**
	 * @return the validacoes
	 */
	public List<Validacao> getValidacoes() {
		return validacoes;
	}

	/**
	 * @param validacoes the validacoes to set
	 */
	public void setValidacoes(List<Validacao> validacoes) {
		this.validacoes = validacoes;
	}

	/**
	 * @return the regimeTibutario
	 */
	public RegimeTributarioEmpresa getRegimeTibutario() {
		return regimeTibutario;
	}

	/**
	 * @param regimeTibutario the regimeTibutario to set
	 */
	public void setRegimeTibutario(RegimeTributarioEmpresa regimeTibutario) {
		this.regimeTibutario = regimeTibutario;
	}

	
	/**
	 * @return the cnpj
	 */
	public String getCnpj() {
		return cnpj;
	}

	/**
	 * @param cnpj the cnpj to set
	 */
	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}

	/**
	 * @return the ncm
	 */
	public List<NCM> getNcm() {
		return ncm;
	}

	/**
	 * @param ncm the ncm to set
	 */
	public void setNcm(List<NCM> ncm) {
		this.ncm = ncm;
	}

	public int getCdcnpj() {
		return cdcnpj;
	}

	public void setCdcnpj(int cdcnpj) {
		this.cdcnpj = cdcnpj;
	}
	
}
