/**
 * 
 */
package br.com.edutex.logic;

import java.io.Serializable;

import javax.persistence.Embeddable;
import javax.persistence.EmbeddedId;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;

/**
 * Classe primarykey composto da tabela associativa Usuário Empresa
 * @author bruno
 *
 */
@Embeddable
public class UsuarioEmpresaPK implements Serializable {

	private static final long serialVersionUID = 974354466729396524L;
	
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	private Usuario usuario;
	
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	private Empresa empresa;

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((getEmpresa() == null) ? 0 : getEmpresa().hashCode());
		result = prime * result + ((getUsuario() == null) ? 0 : getUsuario().hashCode());
		return result;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (!(obj instanceof UsuarioEmpresaPK)) {
			return false;
		}
		UsuarioEmpresaPK other = (UsuarioEmpresaPK) obj;
		if (getEmpresa() == null) {
			if (other.getEmpresa() != null) {
				return false;
			}
		} else if (!getEmpresa().equals(other.getEmpresa())) {
			return false;
		}
		if (getUsuario() == null) {
			if (other.getUsuario() != null) {
				return false;
			}
		} else if (!getUsuario().equals(other.getUsuario())) {
			return false;
		}
		return true;
	}

	/**
	 * @return the usuario
	 */
	public Usuario getUsuario() {
		return usuario;
	}

	/**
	 * @param usuario the usuario to set
	 */
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
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
	
	
}
