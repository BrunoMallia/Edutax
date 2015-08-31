/**
 * 
 */
package br.com.edutex.DAO;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 * Classe que controla o gereneiador de Entidades,
 * transacoes e todos os metodos comuns para as classes DAO
 * @author bruno
 *
 */
public class AbstractDao {
	
	protected EntityManager getEntityManager() {
		if(factory == null || !factory.isOpen()){
			factory = Persistence.createEntityManagerFactory("Edutex");
		}
	   
	    EntityManager ecm = factory.createEntityManager(); 
	    return ecm;
	} 
	
	protected EntityManagerFactory factory; 
	
	
	
}
