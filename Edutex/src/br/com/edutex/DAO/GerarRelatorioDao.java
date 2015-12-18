/**
 * 
 */
package br.com.edutex.DAO;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import javax.persistence.TemporalType;

import br.com.edutex.logic.Validacao;
import br.com.edutex.logic.ValidacaoErro;
import br.com.edutex.notafiscal.util.ValidacaoAux;

/**
 * Classe que contém a camada de persistência dos métodos relattivos 
 * a geração do relatório
 * @author TCC
 *
 */
public class GerarRelatorioDao extends AbstractDao {


	private static GerarRelatorioDao instance;
	
	public static GerarRelatorioDao getInstance(){
		
		if (instance == null)
			instance = new GerarRelatorioDao();
			
		return instance;
	}
	
	
	/**
	 * Método que lista as notas complementares por empresa
	 * @param cdcnpj
	 * @return List<Validacao>
	 */
	public List<Validacao> preencherRelatorioNotasComplementares(int cdcnpj) {
	   return  (List<Validacao>) getEntityManager()
			   .createNamedQuery("Validacao.gerarRelatorioNotasComplementaresPorEmpresa", Validacao.class)
			   .setParameter("cdcnpj", cdcnpj).getResultList();
	}
	
	/**
	 * Método que lista as notas rejeitadas por empresa
	 * @param cdcnpj
	 * @return List<ValidacaoErro>
	 */
	public List<ValidacaoErro> preencherRelatorioNotasRejeitadas(int cdcnpj) {
		return (List<ValidacaoErro>) getEntityManager().
				createNamedQuery("ValidacaoErro.gerarRelatorioNotasRejeitadas", ValidacaoErro.class).
				setParameter("cdcnpj", cdcnpj).getResultList();
	}
	
	/**
	 * Método que lista as notas complementares por emprea e com filtro por data
	 * @param cdcnpj
	 * @param dataInicial
	 * @param dataFinal
	 * @return List<Validacao>
	 */
	public List<Validacao> preencherRelatorioNotasComplementaresPorData(int cdcnpj, String dataInicial
			, String dataFinal) {
		
		String[] dataInicialArray = dataInicial.split("/");
		String[] dataFinalArray = null;
		
		if (dataFinal != null) {
			dataFinalArray = dataFinal.split("/");
		}
		
		
		return (List<Validacao>) getEntityManager().createNamedQuery("Validacao.gerarRelatorioNotasComplementaresPorEmpresaData", Validacao.class)
				.setParameter("cdcnpj", cdcnpj)
				.setParameter("dataInicial",new GregorianCalendar(Integer.parseInt(dataInicialArray[2]),Integer.parseInt(dataInicialArray[1]), Integer.parseInt(dataInicialArray[0])), TemporalType.DATE)
				.setParameter("dataFinal", !dataFinal.equals("")?new GregorianCalendar(Integer.parseInt(dataFinalArray[2]),Integer.parseInt(dataFinalArray[1]), Integer.parseInt(dataFinalArray[0])):Calendar.getInstance(), TemporalType.DATE)
				.getResultList();
	}
	
	
	/**
	 * método que lista as notas rejeitadas por empresa e com fitro de data
	 * @param cdcnpj
	 * @param dataInicial
	 * @param dataFinal
	 * @return List<ValidacaoErro>
	 */
	public List<ValidacaoErro> preencherRelatorioNotasRejeitadasPorData(int cdcnpj, String dataInicial
			, String dataFinal) {
		
		String[] dataInicialArray = dataInicial.split("/");
		String[] dataFinalArray = null;
		
		if (dataFinal != null) {
			dataFinalArray = dataFinal.split("/");
		}
		
		
		return (List<ValidacaoErro>) getEntityManager().createNamedQuery("ValidacaoErro.gerarRelatorioNotasRejeitadasData", ValidacaoErro.class)
				.setParameter("cdcnpj", cdcnpj)
				.setParameter("dataInicial",new GregorianCalendar(Integer.parseInt(dataInicialArray[2]),Integer.parseInt(dataInicialArray[1]), Integer.parseInt(dataInicialArray[0])), TemporalType.DATE)
				.setParameter("dataFinal", !dataFinal.equals("")?new GregorianCalendar(Integer.parseInt(dataFinalArray[2]),Integer.parseInt(dataFinalArray[1]), Integer.parseInt(dataFinalArray[0])):Calendar.getInstance(), TemporalType.DATE)
				.getResultList();
	}
	
	
	/**
	 * Método que lista os relatórios de notas aceitas
	 * @param cdCnpj
	 * @return List<Validacao
	 * >
	 */
	public List<Validacao> preencherRelatorioNotasAceitas(int cdCnpj) {
		return (List<Validacao>) getEntityManager().createNamedQuery("Validacao.gerarRelatorioNotasAceitasPorEmpresa", Validacao.class)
				.setParameter("cdcnpj", cdCnpj)
				.getResultList();
	}
	
	
	
	/**
	 * Método que lista as notas aceitas por empresa e filtro com data
	 * @param cdCnpj
	 * @param dataInicial
	 * @param dataFinal
	 * @return List<Validacao>
	 */
	public List<Validacao> preencherRelatorioNotasAceitas(int cdCnpj, String dataInicial, String dataFinal) {
		return (List<Validacao>) getEntityManager().createNamedQuery("Validacao.gerarRelatorioNotasAceitasPorEmpresaData", Validacao.class)
				.setParameter("dataInicial", dataInicial)
				.setParameter("dataFinal", !dataFinal.equals("")?dataFinal:Calendar.getInstance().toString())
				.getResultList();
	}	
	
	@SuppressWarnings("unchecked")
	public List<ValidacaoAux> preencherRelatorioNotasAtacadistaPorEmpresa(int cdCnpj) {
		return (List<ValidacaoAux>) getEntityManager().createQuery("SELECT new br.com.edutex.notafiscal.util.ValidacaoAux(nfe.dtUpload, nfe.nmNfe, nfe.notaValidada.nmFornecedor,(nfe.notaValidada.valorProdutoTotal / 2))"
				+ "from Validacao v INNER JOIN v.empresa emp INNER JOIN v.nfeGerada nfe INNER JOIN nfe.notavalidada notaValidada INNER JOIN notaValidada.notasValidadaItem notaValidadaItem INNER JOIN "
				+ " WHERE emp.cdcnpj = :cdcnpj group by nfe.dtUpload,nfe.nmNfe,nfe.notaValidada.nmFornecedor,v.idValidacao,nfe.notaValidada.valorProdutoTotal,emp.nmEmpresa order by emp.nmEmpresa"
				, ValidacaoAux.class)
				.setParameter("cdcnpj", cdCnpj)
				.getResultList();
	}
}
