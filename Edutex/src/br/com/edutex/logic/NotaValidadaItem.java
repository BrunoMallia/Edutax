package br.com.edutex.logic;

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

@Entity
@SequenceGenerator(name="notavalidadaItem_sequence", sequenceName="notavalidadaItem_sequence",
initialValue=1, allocationSize=1)
public class NotaValidadaItem  {
	
	@Id  @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="notavalidadaItem_sequence")
	private int cdItem;
	
	@ManyToOne ( fetch=FetchType.LAZY, cascade = {CascadeType.ALL})
	private NCM ncm;
	
	@ManyToOne (fetch = FetchType.LAZY, cascade = {CascadeType.ALL})
	private NFE nfe;
	
	private int CFOP;
	
	private float valorBrutoProduto;
	
	private String descricaoProduto;
	
	@OneToMany( mappedBy = "notaValidadaItem", fetch = FetchType.LAZY)
	private List<NotaValidadaAliquota> notasValidadasAliquotas;
	
	
	@ManyToOne (fetch = FetchType.LAZY, cascade = {CascadeType.ALL})
	private NotaValidada notaValidada;
	
	
	public int getCdItem() {
		return cdItem;
	}

	public void setCdItem(int cdItem) {
		this.cdItem = cdItem;
	}

	public NCM getNcm() {
		return ncm;
	}

	public void setNcm(NCM ncm) {
		this.ncm = ncm;
	}

	public NFE getNfe() {
		return nfe;
	}

	public void setNfe(NFE nfe) {
		this.nfe = nfe;
	}



	public List<NotaValidadaAliquota> getNotasValidadasAliquotas() {
		return notasValidadasAliquotas;
	}

	public void setNotasValidadasAliquotas(List<NotaValidadaAliquota> notasValidadasAliquotas) {
		this.notasValidadasAliquotas = notasValidadasAliquotas;
	}

	public int getCFOP() {
		return CFOP;
	}

	public void setCFOP(int cFOP) {
		CFOP = cFOP;
	}

	/**
	 * @return the notaValidada
	 */
	public NotaValidada getNotaValidada() {
		return notaValidada;
	}

	/**
	 * @param notaValidada the notaValidada to set
	 */
	public void setNotaValidada(NotaValidada notaValidada) {
		this.notaValidada = notaValidada;
	}

	/**
	 * Pega o valor Bruto do produto ou serviço
	 * @return the valorBrutoProduto
	 */
	public float getValorBrutoProduto() {
		return valorBrutoProduto;
	}

	/**
	 * Seta o valor Bruto do produto ou serviço
	 * @param valorBrutoProduto the valorBrutoProduto to set
	 */
	public void setValorBrutoProduto(float valorBrutoProduto) {
		this.valorBrutoProduto = valorBrutoProduto;
	}

	/**
	 * Pega a descricao do Produto ou Serviço
	 * @return the descricaoProduto
	 */
	public String getDescricaoProduto() {
		return descricaoProduto;
	}

	/**
	 * Seta a descrição do Produto ou serviço
	 * @param descricaoProduto the descricaoProduto to set
	 */
	public void setDescricaoProduto(String descricaoProduto) {
		this.descricaoProduto = descricaoProduto;
	}

	
}
