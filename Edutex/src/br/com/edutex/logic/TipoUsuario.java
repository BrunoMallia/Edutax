/**
 * 
 */
package br.com.edutex.logic;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

/**
 * Classe que classifica os tipos de usuario
 * @author bruno
 *
 */
@Entity
@SequenceGenerator(name="tipoUsuario_sequence", sequenceName="tipoUsuario_sequence",
initialValue = 1, allocationSize = 1)
public class TipoUsuario {
	
	@Id @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "tipoUsuario_sequence")
	private int cdTipoUsuario;
	
	@Column(nullable = false)
	private String nmTipoUsuario;

	/**
	 * @return the cdTipoUsuario
	 */
	public int getCdTipoUsuario() {
		return cdTipoUsuario;
	}

	/**
	 * @param cdTipoUsuario the cdTipoUsuario to set
	 */
	public void setCdTipoUsuario(int cdTipoUsuario) {
		this.cdTipoUsuario = cdTipoUsuario;
	}

	/**
	 * @return the nmTipoUsuario
	 */
	public String getNmTipoUsuario() {
		return nmTipoUsuario;
	}

	/**
	 * @param nmTipoUsuario the nmTipoUsuario to set
	 */
	public void setNmTipoUsuario(String nmTipoUsuario) {
		this.nmTipoUsuario = nmTipoUsuario;
	}
}
