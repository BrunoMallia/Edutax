/**
 * 
 */
package br.com.edutex.DAO;

import java.util.ArrayList;
import java.util.List;

import br.com.edutex.logic.TipoUsuario;

/**
 * Classe que representa os metodos da 
 * camada de persistencia da classe de 
 * negocio TipoUsuario
 * @author bruno
 *
 */
public class TipoUsuarioDao extends AbstractDao {

	private static TipoUsuarioDao instance;
	
	
	private TipoUsuarioDao()
	{}
	
	
	public static TipoUsuarioDao getInstance()
	{
		if (instance == null)
				instance = new TipoUsuarioDao();
	
		return instance;
	}		
	
	
	
	/**
	 * Lista todos os tipos de usuario cadastrados no 
	 * banco de dados
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<TipoUsuario> buscaListaTipoUsuario()
	{
		
		String query = "SELECT tpUsu from TipoUsuario tpUsu "
				+ "ORDER BY tpUsu.nmTipoUsuario";
		
		return (ArrayList<TipoUsuario>) getEntityManager().createQuery(query)
					.getResultList();
	}
	
	
	/**
	 * Busca lista de tipos de Usuario por I
	 * @param parametros
	 * @return List<TipoUsuario>
	 */
	public TipoUsuario buscaTipoUsuario(int id)
	{	
		
		return getEntityManager().find(TipoUsuario.class,id);
		
	}
	
}
