/**
 * 
 */
package br.com.edutex.logic;

import java.io.Serializable;
import java.util.List;

import javax.persistence.AssociationOverride;
import javax.persistence.AssociationOverrides;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Transient;

/**
 * Classe associativa de usuario e empresa
 * @author bruno
 *
 */
@Entity
@AssociationOverrides(
		 { @AssociationOverride(name = "pk.usuario", joinColumns = @JoinColumn(name = "cdUsuario")),
		 @AssociationOverride(name = "pk.empresa", joinColumns = @JoinColumn(name = "cdcnpj")) })
public class UsuarioEmpresa implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -1813493314282513132L;

	@EmbeddedId
	private UsuarioEmpresaPK pk = new UsuarioEmpresaPK();
	
	@OneToMany(mappedBy="usuarioEmpresa")
	private List<Acesso> acessos;
	
	@Transient
	public Empresa getEmpresa() {
		return getPk().getEmpresa();
	}
	
	@Transient
	public Usuario getUsuario()
	{
		return getPk().getUsuario();
	}


	
	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((getPk() == null) ? 0 : getPk().hashCode());
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
		if (!(obj instanceof UsuarioEmpresa)) {
			return false;
		}
		UsuarioEmpresa other = (UsuarioEmpresa) obj;
		if (getPk() == null) {
			if (other.getPk() != null) {
				return false;
			}
		} else if (!getPk().equals(other.getPk())) {
			return false;
		}
		return true;
	}

	/**
	 * @return the pk
	 */
	public UsuarioEmpresaPK getPk() {
		return pk;
	}

	/**
	 * @param pk the pk to set
	 */
	public void setPk(UsuarioEmpresaPK pk) {
		this.pk = pk;
	}

	/**
	 * @return the acessos
	 */
	public List<Acesso> getAcessos() {
		return acessos;
	}

	/**
	 * @param acessos the acessos to set
	 */
	public void setAcessos(List<Acesso> acessos) {
		this.acessos = acessos;
	}
	
}
