/**
 * 
 */
package br.com.edutex.DAO;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceException;

import org.hibernate.exception.ConstraintViolationException;

import br.com.edutex.logic.Estado;
import br.com.edutex.logic.NFE;
import br.com.edutex.logic.Usuario;
import br.com.edutex.util.LogUtil;
import br.com.edutex.util.MensagemProp;

/**
 * @author bruno
 *
 */
public class NfeDao extends AbstractDao {

	
	private static NfeDao instance;
	
	public static NfeDao getInstance(){
		if (instance == null) {
			instance = new NfeDao();
		}
		
		return instance;
	}
	
	private NfeDao(){
		getEntityManager();
	}
	
	/**
	 * Este método salva o objeto NFE no banco de dados
	 * @param nfe
	 * @return id do objeto criado
	 */
	public Object salvarNfe(NFE nfe) {

		 EntityManager manager = getEntityManager();
		 EntityTransaction transaction = manager.getTransaction();
		 int id = 0;
		 
		 try{
			 transaction.begin();
			 manager.persist(nfe);
			 transaction.commit();
		 } catch (PersistenceException e) {
			 manager.getTransaction().rollback();
			 e.printStackTrace();
			 LogUtil.getLog().error(e.getMessage(), e);
			 
			 if (e.getCause().getClass().equals(ConstraintViolationException.class)) {
				  return MensagemProp.getPropriedades().get("notafiscal.cadastro.erro");
			 }
		
		 
		 } catch(Exception e){
			 manager.getTransaction().rollback();
			 e.printStackTrace();
			 LogUtil.getLog().error(e.getMessage(), e);
			 return MensagemProp.getPropriedades().get("usuario.cadastro.erro");
		 } finally {
			 factory.close();
			 transaction = null;
		 }
		 
		 
		 return id;
	}
	
}
