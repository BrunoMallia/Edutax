/**
 * 
 */
package br.com.edutex.logic;

import java.util.Calendar;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * Classe que armazena a nota fiscal portada
 * e a gerada para validacao
 * @author bruno
 *
 */
@NamedQueries({
@NamedQuery(name="Validacao.gerarRelatorioNotasComplementares", query="SELECT v FROM Validacao v INNER JOIN v.nfeGerada nfe INNER JOIN v.empresa emp where emp.cdcnpj = :cdcnpj and nfe.notaComplementar = 't' group by emp.nmEmpresa,v.idValidacao order by emp.nmEmpresa"),
@NamedQuery(name="Validacao.gerarRelatorioNotasComplementaresPorEmpresa", query="SELECT v FROM Validacao v INNER JOIN v.nfeGerada nfe INNER JOIN v.empresa emp where  nfe.notaComplementar = 't' group by emp.nmEmpresa,v.idValidacao order by emp.nmEmpresa"),
@NamedQuery(name="Validacao.gerarRelatorioNotasComplementaresPorData", query="SELECT v FROM Validacao v INNER JOIN v.nfeGerada nfe INNER JOIN v.empresa emp where emp.cdcnpj = :cdcnpj and nfe.notaComplementar = 't' and nfe.dtUpload between :dataInicial and :dataFinal group by emp.nmEmpresa,v.idValidacao order by emp.nmEmpresa"),
@NamedQuery(name="Validacao.gerarRelatorioNotasComplementaresPorEmpresaData", query="SELECT v FROM Validacao v INNER JOIN v.nfeGerada nfe INNER JOIN v.empresa emp where nfe.notaComplementar = 't' and nfe.dtUpload between :dataInicial and :dataFinal group by emp.nmEmpresa,v.idValidacao order by emp.nmEmpresa"),
@NamedQuery(name="Validacao.gerarRelatorioNotasAceitas", query="SELECT v FROM ValidacaoErro ve RIGHT JOIN ve.validacao v INNER JOIN v.nfeGerada nfe INNER JOIN v.empresa emp  where emp.cdcnpj = :cdcnpj and ve IS NULL order by emp.nmEmpresa"),
@NamedQuery(name="Validacao.gerarRelatorioNotasAceitasPorEmpresa", query="SELECT v FROM ValidacaoErro ve RIGHT JOIN ve.validacao v INNER JOIN v.nfeGerada nfe INNER JOIN v.empresa emp  where  ve IS NULL order by emp.nmEmpresa"),
@NamedQuery(name="Validacao.gerarRelatorioNotasAceitasPorEmpresaData", query="SELECT v FROM ValidacaoErro ve RIGHT JOIN ve.validacao v INNER JOIN v.nfeGerada nfe INNER JOIN v.empresa emp  where  ve IS NULL and v.dtValidacao between :dataInicial and :dataFinal order by emp.nmEmpresa"),
@NamedQuery(name="Validacao.gerarRelatorioNotasAceitasPorData", query="SELECT v FROM ValidacaoErro ve RIGHT JOIN ve.validacao v INNER JOIN v.nfeGerada nfe INNER JOIN v.empresa emp where emp.cdcnpj = :cdcnpj and ve IS NULL and v.dtValidacao between :dataInicial and :dataFinal group by emp.nmEmpresa,v.idValidacao order by emp.nmEmpresa")
})
@Entity
@SequenceGenerator(name="validacao_sequence", sequenceName="validacao_sequence",
initialValue = 1, allocationSize = 1)
public class Validacao {
	
	@Id @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "validacao_sequence")
	private int idValidacao;
	
	@ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.REFRESH, CascadeType.MERGE})
	private NFE nfeGerada;
	
	@ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.REFRESH, CascadeType.MERGE})
	private NFE nfeInicial;
	
	@ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.REFRESH, CascadeType.MERGE})
	private FinalidadeNfe finalidadeNfe;
	
	@ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.REFRESH, CascadeType.MERGE})
	private Empresa empresa;
	
	@Temporal(TemporalType.DATE)
	private Calendar dtValidacao;
	
	@OneToMany(mappedBy="validacao", cascade = {CascadeType.PERSIST,CascadeType.MERGE, CascadeType.REFRESH, CascadeType.DETACH})
	@Column(nullable = true)
	private List<ValidacaoErro> validacaoErro;
	
	
	/**
	 * @return the idValidacao
	 */
	public int getIdValidacao() {
		return idValidacao;
	}

	/**
	 * @param idValidacao the idValidacao to set
	 */
	public void setIdValidacao(int idValidacao) {
		this.idValidacao = idValidacao;
	}

	/**
	 * @return the nfeGerada
	 */
	public NFE getNfeGerada() {
		return nfeGerada;
	}

	/**
	 * @param nfeGerada the nfeGerada to set
	 */
	public void setNfeGerada(NFE nfeGerada) {
		this.nfeGerada = nfeGerada;
	}

	/**
	 * @return the nfeInicial
	 */
	public NFE getNfeInicial() {
		return nfeInicial;
	}

	/**
	 * @param nfeInicial the nfeInicial to set
	 */
	public void setNfeInicial(NFE nfeInicial) {
		this.nfeInicial = nfeInicial;
	}

	/**
	 * @return the finalidadeNfe
	 */
	public FinalidadeNfe getFinalidadeNfe() {
		return finalidadeNfe;
	}

	/**
	 * @param finalidadeNfe the finalidadeNfe to set
	 */
	public void setFinalidadeNfe(FinalidadeNfe finalidadeNfe) {
		this.finalidadeNfe = finalidadeNfe;
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

	/**
	 * @return the dtValidacao
	 */
	public Calendar getDtValidacao() {
		return dtValidacao;
	}

	/**
	 * @param dtValidacao the dtValidacao to set
	 */
	public void setDtValidacao(Calendar dtValidacao) {
		this.dtValidacao = dtValidacao;
	}

	public List<ValidacaoErro> getValidacaoErro() {
		return validacaoErro;
	}

	public void setValidacaoErro(List<ValidacaoErro> validacaoErro) {
		this.validacaoErro = validacaoErro;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((dtValidacao == null) ? 0 : dtValidacao.hashCode());
		result = prime * result + idValidacao;
		result = prime * result
				+ ((nfeInicial == null) ? 0 : nfeInicial.hashCode());
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
		Validacao other = (Validacao) obj;
		if (dtValidacao == null) {
			if (other.dtValidacao != null)
				return false;
		} else if (!dtValidacao.equals(other.dtValidacao))
			return false;
		if (idValidacao != other.idValidacao)
			return false;
		if (nfeInicial == null) {
			if (other.nfeInicial != null)
				return false;
		} else if (!nfeInicial.equals(other.nfeInicial))
			return false;
		return true;
	}

	

}
