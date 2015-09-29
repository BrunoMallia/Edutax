package br.com.edutex.DAO;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;

import br.com.edutex.logic.FinalidadeNfe;

/**
 * Classe que representa os metodos de persistencia
 * para os objetos de negocio Empresa
 * @author bruno
 *
 */
public class FinalidadeNfeDao  extends AbstractDao{
	
	private static FinalidadeNfeDao instance;
	
	private FinalidadeNfeDao(){
		factory = Persistence.
		          createEntityManagerFactory("Edutex");
	}
	
	public static FinalidadeNfeDao getInstance(){
		
		if(instance == null)
			instance = new FinalidadeNfeDao();
		
		
		return instance;
		
	}
	/**
	 * Metodo que busca lista de Empresas 
	 * usando como filtro o tipostatus
	 * @param codStatus
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<FinalidadeNfe> buscaFinalidadesNfe() {
		// traz apenas as empresa ativas	
		 String query = "SELECT f from FinalidadeNfe AS f"
		 		+ " ORDER BY f.cdFinalidadeNfe";
		 
		 return  getEntityManager().createQuery(query).getResultList();
			 
	}
	
	/**
	 * Este método recupera o objeto FinalidadaeNfe de acordo com 
	 * o código de finalidade passada por parâmetro
	 * @param cdFinalidadeNfe
	 * @return
	 */
	public FinalidadeNfe getFinalidadeNfe(int cdFinalidadeNfe) {
		EntityManager manager = factory.createEntityManager();
		return manager.find(FinalidadeNfe.class, cdFinalidadeNfe);
		
	}
		
 
}
