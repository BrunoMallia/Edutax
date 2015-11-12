package br.com.edutex.DAO;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.PersistenceException;

import org.hibernate.exception.ConstraintViolationException;

import br.com.edutex.logic.FinalidadeNfe;
import br.com.edutex.logic.ImpostoNcm;
import br.com.edutex.logic.ImpostoNcmEstado;
import br.com.edutex.util.LogUtil;
import br.com.edutex.util.MensagemProp;

/**
 * Classe que representa os metodos de persistencia
 * para os objetos de negocio Empresa
 * @author bruno
 *
 */
public class ImpostoNcmEstadoDao  extends AbstractDao{
	
	private static ImpostoNcmEstadoDao instance;
	
	private ImpostoNcmEstadoDao(){
		factory = Persistence.
		          createEntityManagerFactory("Edutex");
	}
	
	public static ImpostoNcmEstadoDao getInstance(){
		
		if(instance == null)
			instance = new ImpostoNcmEstadoDao();
		
		
		return instance;
		
	}

	
	/**
	 * Este método insere o Objeto ImpostoNcmEstado no bancco de dados
	 * @param impostoEstado
	 * @throws Exception
	 */
	public void inserir(ImpostoNcmEstado impostoEstado) throws Exception {
		 EntityManager manager = factory.createEntityManager(); 
		 EntityTransaction transaction = manager.getTransaction();
		 try{
			 transaction.begin();
			 manager.persist(impostoEstado);
			 manager.flush();
			 transaction.commit();
		 } catch (PersistenceException e) {
			 manager.getTransaction().rollback();
			 e.printStackTrace();
			 LogUtil.getLog().error(e.getMessage(), e);
		 }  finally {
			 transaction = null;
		 }
	}
		
	
	/**
	 * Atualiza o objeto ImpostoNcmEstado
	 * @param impostoEstado
	 * @throws Exception
	 */
	public void atualizar(ImpostoNcmEstado impostoEstado) throws Exception {
		 EntityManager manager = getEntityManager(); 
		 EntityTransaction transaction = manager.getTransaction();
		 try{
			 transaction.begin();
			 manager.merge(impostoEstado);
			 manager.flush();
			 transaction.commit();
		 } catch (PersistenceException e) {
			 manager.getTransaction().rollback();
			 e.printStackTrace();
			 LogUtil.getLog().error(e.getMessage(), e);
		 }  finally {
			 factory.close();
			 transaction = null;
		 }
	}
 
	/**
	 * lista os impostosNCMEstado dado um código NCM
	 * @param cdNcm
	 * @return
	 */
	public List<ImpostoNcmEstado> listarImpostoNcmICMS(int cdNcm) {
		 String query = "SELECT imp from ImpostoNcmEstado AS imp WHERE imp.impostoNCM.ncm.cdNcm = :cdNcm";
		 
		 return   (List<ImpostoNcmEstado>) getEntityManager().createQuery(query)
					.setParameter("cdNcm", cdNcm).getResultList();
			 
	}
	
	/**
	 * Este método busca 
	 * @param cdNcm
	 * @param cdEstado
	 * @return
	 */
	public ImpostoNcmEstado getMVAjustadoImposto(String ncm,int cdEstado, int finalidade) throws javax.persistence.NoResultException {
		 String query = "SELECT imp from ImpostoNcmEstado AS imp WHERE imp.impostoNCM.ncm.nmncm = :ncm and imp.estado.cdEstado = :cdEstado and imp.impostoNCM.finalidadeNfe.cdFinalidadeNfe= :finalidade";
		 
		 return   (ImpostoNcmEstado) getEntityManager().createQuery(query)
					.setParameter("ncm", ncm)
					.setParameter("cdEstado",cdEstado)
					.setParameter("finalidade", finalidade)
					.getSingleResult();
			 
	}
	
}
