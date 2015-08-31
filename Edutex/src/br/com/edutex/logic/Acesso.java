/**
 * 
 */
package br.com.edutex.logic;

import java.util.Calendar;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;

/**
 * Classe que representa o acesso da 
 * autenticacao do sistema
 * @author bruno
 *
 */
@Entity
@SequenceGenerator(name="acesso_sequence", sequenceName="acesso_sequence",
initialValue=1, allocationSize=1)
public class Acesso {
	
	@Id @GeneratedValue(strategy= GenerationType.SEQUENCE, generator = "acesso_sequence")
	private int idAcesso;
	
	
	@ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.REFRESH, CascadeType.MERGE})
	private UsuarioEmpresa usuarioEmpresa;
	
	private Calendar dtAcesso;

	/**
	 * @return the idAcesso
	 */
	public int getIdAcesso() {
		return idAcesso;
	}

	/**
	 * @param idAcesso the idAcesso to set
	 */
	public void setIdAcesso(int idAcesso) {
		this.idAcesso = idAcesso;
	}

	/**
	 * @return the usuarioEmpresa
	 */
	public UsuarioEmpresa getUsuarioEmpresa() {
		return usuarioEmpresa;
	}

	/**
	 * @param usuarioEmpresa the usuarioEmpresa to set
	 */
	public void setUsuarioEmpresa(UsuarioEmpresa usuarioEmpresa) {
		this.usuarioEmpresa = usuarioEmpresa;
	}

	/**
	 * @return the dtAcesso
	 */
	public Calendar getDtAcesso() {
		return dtAcesso;
	}

	/**
	 * @param dtAcesso the dtAcesso to set
	 */
	public void setDtAcesso(Calendar dtAcesso) {
		this.dtAcesso = dtAcesso;
	}
	
	
	
	
}
