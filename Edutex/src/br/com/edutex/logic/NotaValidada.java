package br.com.edutex.logic;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;

@Entity
@SequenceGenerator(name="NotaValidada_sequence", sequenceName="NotaValidada_sequence",
initialValue = 1, allocationSize = 1)
public class NotaValidada implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5424155261187719625L;

	@Id @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="NotaValidada_sequence")
	private int cdnfe;
	
	@Column (nullable = false)
	private String nmNFornecedor;
	
	@Column(nullable = true)
	private int cUF;
	
	@Column(nullable = false)
	private int finNfe;
	
	@Column(nullable = false)
	private int indFinal;

	@OneToMany (mappedBy = "notaValidada", fetch = FetchType.LAZY)
	private List<NotaValidadaItem> notasValidadaItem;
	
	@Column(nullable = true)
	private int CRT;
	
	private float valorBCTotal;
	
	@Column (nullable = true)
	private float ValorICMSTotal;
	
	@Column (nullable = true)
	private float valorICMSDesonTotal;
	
	@Column (nullable = true)
	private float valorBCSTTotal;
	
	@Column (nullable = true)
	private float valorSTTotal;
	
	@Column (nullable = true)
    private float valorProdutoTotal;
    
	@Column (nullable = true)
    private float valorFrete;
	
	@Column (nullable = true)
    private float valorSeguro;
    
	@Column (nullable = true)
    private float valorDescontoTotal;
    
	@Column (nullable = true)
    private float valorIPITotal;
    
	@Column (nullable = true)
    private float valorPISTotal;
    
	@Column (nullable = true)
    private float valorCOFINSTotal;
    
	@Column (nullable = true)
    private float valorNotaFiscal;
    
	@Column (nullable = true)
    private float valorTotalTributacao;
    
	@Column (nullable = true)
    private float valorOutros;
	
	private float valorII;
    
	
	public float getValorICMSDesonTotal() {
		return valorICMSDesonTotal;
	}

	public float getValorBCSTTotal() {
		return valorBCSTTotal;
	}

	public float getValorSTTotal() {
		return valorSTTotal;
	}

	public float getValorProdutoTotal() {
		return valorProdutoTotal;
	}

	public float getValorFrete() {
		return valorFrete;
	}

	public float getValorSeguro() {
		return valorSeguro;
	}

	public float getValorDescontoTotal() {
		return valorDescontoTotal;
	}

	public float getValorIPITotal() {
		return valorIPITotal;
	}

	public float getValorPISTotal() {
		return valorPISTotal;
	}

	public float getValorCOFINSTotal() {
		return valorCOFINSTotal;
	}

	public float getValorNotaFiscal() {
		return valorNotaFiscal;
	}

	public float getValorTotalTributacao() {
		return valorTotalTributacao;
	}

	public void setValorICMSDesonTotal(float valorICMSDesonTotal) {
		this.valorICMSDesonTotal = valorICMSDesonTotal;
	}

	public void setValorBCSTTotal(float valorBCSTTotal) {
		this.valorBCSTTotal = valorBCSTTotal;
	}

	public void setValorSTTotal(float valorSTTotal) {
		this.valorSTTotal = valorSTTotal;
	}

	public void setValorProdutoTotal(float valorProdutoTotal) {
		this.valorProdutoTotal = valorProdutoTotal;
	}

	public void setValorFrete(float valorFrete) {
		this.valorFrete = valorFrete;
	}

	public void setValorSeguro(float valorSeguro) {
		this.valorSeguro = valorSeguro;
	}

	public void setValorDescontoTotal(float valorDescontoTotal) {
		this.valorDescontoTotal = valorDescontoTotal;
	}

	public void setValorIPITotal(float valorIPITotal) {
		this.valorIPITotal = valorIPITotal;
	}

	public void setValorPISTotal(float valorPISTotal) {
		this.valorPISTotal = valorPISTotal;
	}

	public void setValorCOFINSTotal(float valorCOFINSTotal) {
		this.valorCOFINSTotal = valorCOFINSTotal;
	}

	public void setValorNotaFiscal(float valorNotaFiscal) {
		this.valorNotaFiscal = valorNotaFiscal;
	}

	public void setValorTotalTributacao(float valorTotalTributacao) {
		this.valorTotalTributacao = valorTotalTributacao;
	}




	public String getNmNFornecedor() {
		return nmNFornecedor;
	}

	public void setNmNFornecedor(String nmNFornecedor) {
		this.nmNFornecedor = nmNFornecedor;
	}


	public int getcUF() {
		return cUF;
	}

	public void setcUF(int cUF) {
		this.cUF = cUF;
	}

	public int getFinNfe() {
		return finNfe;
	}

	public void setFinNfe(int finNfe) {
		this.finNfe = finNfe;
	}

	public int getCRT() {
		return CRT;
	}

	public void setCRT(int cRT) {
		CRT = cRT;
	}

	/**
	 * @return the notasValidadaItem
	 */
	public List<NotaValidadaItem> getNotasValidadaItem() {
		return notasValidadaItem;
	}

	/**
	 * @param notasValidadaItem the notasValidadaItem to set
	 */
	public void setNotasValidadaItem(List<NotaValidadaItem> notasValidadaItem) {
		this.notasValidadaItem = notasValidadaItem;
	}

	/**
	 * Pega o valor da Base de Cálculo do ICMS
	 * @return the valorBCTotal
	 */
	public float getValorBCTotal() {
		return valorBCTotal;
	}

	/**
	 * Seta o Valor Total da Base de Cálculo do ICMS
	 * @param valorBCTotal the valorBCTotal to set
	 */
	public void setValorBCTotal(float valorBCTotal) {
		this.valorBCTotal = valorBCTotal;
	}

	/**
	 * @return the valorICMSTotal
	 */
	@Column(nullable = true)
	public float getValorICMSTotal() {
		return ValorICMSTotal;
	}

	/**
	 * @param valorICMSTotal the valorICMSTotal to set
	 */
	public void setValorICMSTotal(float valorICMSTotal) {
		ValorICMSTotal = valorICMSTotal;
	}

	/**
	 * @return the valorOutros
	 */
	public float getValorOutros() {
		return valorOutros;
	}

	/**
	 * @param valorOutros the valorOutros to set
	 */
	public void setValorOutros(float valorOutros) {
		this.valorOutros = valorOutros;
	}

	public float getValorII() {
		return valorII;
	}

	public void setValorII(float valorII) {
		this.valorII = valorII;
	}

	public int getIndFinal() {
		return indFinal;
	}

	public void setIndFinal(int indFinal) {
		this.indFinal = indFinal;
	}

	

}
