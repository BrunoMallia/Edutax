/**
 * 
 */
package br.com.edutex.DAO;

import java.util.ArrayList;
import java.util.List;

import br.com.edutex.logic.Estado;

/**
 * @author bruno
 *
 */
public class EstadoDao extends AbstractDao {

	
	private static EstadoDao instance;
	
	public static EstadoDao getInstance(){
		if (instance == null) {
			instance = new EstadoDao();
		}
		
		return instance;
	}
	
	private EstadoDao(){
		getEntityManager();
	}
	
	/**
	 * 
	 * metodo para buscar lista de estados
	 * @info 
	 * @return lista de estados 
	 */
	@SuppressWarnings("unchecked")
	public List<Estado> listaEstados() {
		String query = "SELECT est from Estado est ";
		
		return  (ArrayList<Estado>) getEntityManager().createQuery(query)
				.getResultList();
	}
	
	public Estado getEstado(int cdEstado) {
		return getEntityManager().find(Estado.class, cdEstado);
	}
	
	
}
