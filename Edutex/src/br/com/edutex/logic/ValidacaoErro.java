/**
 * 
 */
package br.com.edutex.logic;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;

/**
 * @author bruno
 *
 */
@NamedQueries({
	@NamedQuery(name="ValidacaoErro.gerarRelatorioNotasRejeitadas", query="Select erro from ValidacaoErro erro INNER JOIN erro.validacao v INNER JOIN v.empresa emp where emp.cdcnpj= :cdcnpj group by emp.nmEmpresa, erro.idValidacaoErro"),
	@NamedQuery(name="ValidacaoErro.gerarRelatorioNotasRejeitadasPorEmpresa", query="Select erro from ValidacaoErro erro INNER JOIN erro.validacao v INNER JOIN v.empresa emp group by emp.nmEmpresa, erro.idValidacaoErro"),
	@NamedQuery(name="ValidacaoErro.gerarRelatorioNotasRejeitadasData", query="Select erro from ValidacaoErro erro LEFT JOIN erro.validacao v INNER JOIN v.empresa emp where emp.cdcnpj= :cdcnpj and v.dtValidacao between :dataInicial and :dataFinal  group by erro.idValidacaoErro"),
	@NamedQuery(name="ValidacaoErro.gerarRelatorioNotasRejeitadasDataEmpresa", query="Select erro from ValidacaoErro erro INNER JOIN erro.validacao v INNER JOIN v.empresa emp where v.dtValidacao between :dataInicial and :dataFinal  group by emp.nmEmpresa, erro.idValidacaoErro")
})
@Entity
@SequenceGenerator(name="validacaoErro_sequence", sequenceName="validacaoErro_sequence", 
initialValue = 1, allocationSize = 1)
public class ValidacaoErro {
	
	@Id @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "validacaoErro_sequence")
	private int idValidacaoErro;;
	
	
	@ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.REFRESH, CascadeType.MERGE, CascadeType.MERGE})
	private Validacao validacao;
	
	@ManyToOne(cascade= {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH, CascadeType.DETACH})
	private TipoErro tpErro;
	
	private String txtAuxiliar;

	/**
	 * @return the idValidacao
	 */
	public int getIdValidacaoErro() {
		return idValidacaoErro;
	}

	/**
	 * @param idValidacao the idValidacao to set
	 */
	public void setIdValidacaoErro(int idValidacaoErro) {
		this.idValidacaoErro = idValidacaoErro;
	}

	/**
	 * @return the tpErro
	 */
	public TipoErro getTpErro() {
		return tpErro;
	}

	/**
	 * @param tpErro the tpErro to set
	 */
	public void setTpErro(TipoErro tpErro) {
		this.tpErro = tpErro;
	}

	/**
	 * @return the txtAuxiliar
	 */
	public String getTxtAuxiliar() {
		return txtAuxiliar;
	}

	/**
	 * @param txtAuxiliar the txtAuxiliar to set
	 */
	public void setTxtAuxiliar(String txtAuxiliar) {
		this.txtAuxiliar = txtAuxiliar;
	}

	public Validacao getValidacao() {
		return validacao;
	}

	public void setValidacao(Validacao validacao) {
		this.validacao = validacao;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + idValidacaoErro;
		result = prime * result + ((tpErro == null) ? 0 : tpErro.hashCode());
		result = prime * result
				+ ((txtAuxiliar == null) ? 0 : txtAuxiliar.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ValidacaoErro other = (ValidacaoErro) obj;
		if (idValidacaoErro != other.idValidacaoErro)
			return false;
		if (tpErro == null) {
			if (other.tpErro != null)
				return false;
		} else if (!tpErro.equals(other.tpErro))
			return false;
		if (txtAuxiliar == null) {
			if (other.txtAuxiliar != null)
				return false;
		} else if (!txtAuxiliar.equals(other.txtAuxiliar))
			return false;
		return true;
	}
	
	
	
	
}
