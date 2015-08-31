/**
 * 
 */
package br.com.edutex.logic;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
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
 * Classe que representa o usuario
 * @author bruno
 *
 */
@Entity
@Table(
	name = "Usuario",
	uniqueConstraints = @UniqueConstraint(columnNames={"nmLogin"}, name = "UN_LOGIN")
)
@SequenceGenerator(name="usuario_sequence", sequenceName="usuario_sequence",
initialValue = 2, allocationSize = 1)
public class Usuario {

	@Id @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "usuario_sequence")
	private int cdUsuario;
	
	@ManyToOne(cascade = {CascadeType.MERGE, CascadeType.REFRESH, CascadeType.DETACH})
	private TipoStatus tpStatus;
	
	@ManyToOne(cascade = {CascadeType.MERGE, CascadeType.REFRESH, CascadeType.DETACH}) 
	private TipoUsuario tpUsuario;
	
	@Column(nullable = false)
	private String nmUsuario;
	
	@Column(nullable = false)
	private String cdSenha;
	
	@Column(nullable = true)
	private Calendar dtCriacao;
	
	@Column(nullable = true)
	private Calendar dtAtualizacao;
	
	@Column(nullable = false)
	private String nmLogin;
	
	@OneToMany(mappedBy = "pk.usuario", fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH})
	private List<UsuarioEmpresa> usuarioEmpresas = new ArrayList<UsuarioEmpresa>();

	/**
	 * @return the cdUsuario
	 */
	public int getCdUsuario() {
		return cdUsuario;
	}

	/**
	 * @param cdUsuario the cdUsuario to set
	 */
	public void setCdUsuario(int cdUsuario) {
		this.cdUsuario = cdUsuario;
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
	 * @return the tpUsuario
	 */
	public TipoUsuario getTpUsuario() {
		return tpUsuario;
	}

	/**
	 * @param tpUsuario the tpUsuario to set
	 */
	public void setTpUsuario(TipoUsuario tpUsuario) {
		this.tpUsuario = tpUsuario;
	}

	/**
	 * @return the nmUsuario
	 */
	public String getNmUsuario() {
		return nmUsuario;
	}

	/**
	 * @param nmUsuario the nmUsuario to set
	 */
	public void setNmUsuario(String nmUsuario) {
		this.nmUsuario = nmUsuario;
	}

	/**
	 * @return the cdSenha
	 */
	public String getCdSenha() {
		return cdSenha;
	}

	/**
	 * @param cdSenha the cdSenha to set
	 */
	public void setCdSenha(String cdSenha) {
		this.cdSenha = cdSenha;
	}

	/**
	 * @return the dtCriacao
	 */
	public Calendar getDtCriacao() {
		return dtCriacao;
	}

	/**
	 * @param dtCriacao the dtCriacao to set
	 */
	public void setDtCriacao(Calendar dtCriacao) {
		this.dtCriacao = dtCriacao;
	}

	/**
	 * @return the dtAtualizacao
	 */
	public Calendar getDtAtualizacao() {
		return dtAtualizacao;
	}

	/**
	 * @param dtAtualizacao the dtAtualizacao to set
	 */
	public void setDtAtualizacao(Calendar dtAtualizacao) {
		this.dtAtualizacao = dtAtualizacao;
	}

	/**
	 * @return the nmLogin
	 */
	public String getNmLogin() {
		return nmLogin;
	}

	/**
	 * @param nmLogin the nmLogin to set
	 */
	public void setNmLogin(String nmLogin) {
		this.nmLogin = nmLogin;
	}
	

	/**
	 * @return the usuarioEmpresas
	 */
	public List<UsuarioEmpresa> getUsuarioEmpresas() {
		return usuarioEmpresas;
	}

	/**
	 * @param usuarioEmpresas the usuarioEmpresas to set
	 */
	public void setUsuarioEmpresas(List<UsuarioEmpresa> usuarioEmpresas) {
		this.usuarioEmpresas = usuarioEmpresas;
	}
}
