package br.com.edutex.DAO;

import br.com.edutex.logic.TipoErro;

/**
 * @author TCC
 *
 */
public class TipoErroDao extends AbstractDao {
	
	private static TipoErroDao instance;
	
	public static TipoErroDao getInstance() {
		
		if (instance == null) {
			instance = new TipoErroDao();
		}
		
		return instance;
		
	}
	
	/**
	 * M�todo da camada de persist�ncia que recupara o tipo de erro de acordo com o c�digo
	 * @param codErro
	 * @return TipoErro
	 */
	public TipoErro getTipoErro(int codErro) {
		return getEntityManager().find(TipoErro.class, codErro);
	}
	
}
