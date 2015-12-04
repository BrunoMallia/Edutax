/**
 * 
 */
package br.com.edutex.DAO;

import java.util.List;

import br.com.edutex.logic.Validacao;

/**
 * Classe que cont�m a camada de persist�ncia dos m�todos relattivos 
 * a gera��o do relat�rio
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
	
	
	@SuppressWarnings("unchecked")
	public List<Object[]> preencherRelatorioNotasComplementares(int cdCnpj) {
	   return  (List<Object[]>) getEntityManager()
			   .createNamedQuery("Validacao.gerarRelatorioNotasComplementaresPorEmpresa")
			   .setParameter("cdcnpj", cdCnpj).getResultList();
	}
	
}
