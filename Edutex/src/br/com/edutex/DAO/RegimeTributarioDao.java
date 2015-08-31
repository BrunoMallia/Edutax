/**
 * 
 */
package br.com.edutex.DAO;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;

import br.com.edutex.logic.RegimeTributarioEmpresa;
import br.com.edutex.logic.Usuario;

/**
 * Classe representa o regime tributarios
 * das empresas
 * @author bruno
 *
 */
public class RegimeTributarioDao extends AbstractDao {
	
	private static RegimeTributarioDao instance;
	
	private RegimeTributarioDao(){
		factory = Persistence.
		          createEntityManagerFactory("Edutex");
	}
	
	public static RegimeTributarioDao getInstance(){
		if(instance == null){
			instance = new RegimeTributarioDao();
		}
		
		return instance;
	}
	
	
	/**
	 * Metodo que lista os regimes tributarios associados as empresas
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<RegimeTributarioEmpresa> getListaRegimeTributario(){
		 String str = "SELECT REG FROM RegimeTributarioEmpresa REG";
			return  ((List<RegimeTributarioEmpresa>) getEntityManager().createQuery(str).
					getResultList());
	}
	
	/**
	 * Metodo que busca empresa por Id.
	 * O id precisa ser long
	 * @param id - 
	 * @return
	 */
	public RegimeTributarioEmpresa buscaPorId(int id){
		EntityManager manager = factory.createEntityManager();
		return manager.find(RegimeTributarioEmpresa.class, id);
	}
	
}
