/**
 * 
 */
package br.com.edutex.DAO;

import javax.persistence.Persistence;

import br.com.edutex.logic.TipoStatus;

/**
 * @author bruno
 *
 */
public class TipoStatusDao extends AbstractDao{

	
	private static TipoStatusDao instance;
	
	private TipoStatusDao (){
	   getEntityManager();
	}
	
	public static TipoStatusDao getInstance(){
		if(instance == null)
			instance = new TipoStatusDao();
		
		
		return instance;
	}
	
	/**
	 * Por padrao o codigo do tipo status Ativo e 1 contudo ira depender da carga inicial 
	 * do sistema
	 * @return
	 */
	public TipoStatus buscarTipoStatusAtivo() {
		
		String query = "SELECT tpStatus from TipoStatus tpStatus where tpStatus.nmStatus = :status";
		return (TipoStatus) getEntityManager().createQuery(query).setParameter("status", "Ativo")
					.getSingleResult();
	}
	
	
	/**
	 * Por padrao o codigo do tipo status Desativado e 2 contudo ira depender da carga inicial 
	 * do sistema
	 * @return
	 */
	public TipoStatus buscarTipoStatusDesativado(){
		String query = "SELECT tpStatus from TipoStatus tpStatus where tpStatus.nmStatus = :status";
		return (TipoStatus) getEntityManager().createQuery(query).setParameter("status", "Desativado")
				.getSingleResult();
	}
	
	public TipoStatus getStatus(int codStatus) {
		return getEntityManager().find(TipoStatus.class, codStatus);
	}
	
}
