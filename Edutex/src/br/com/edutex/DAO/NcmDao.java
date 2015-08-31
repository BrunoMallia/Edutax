/**
 * 
 */
package br.com.edutex.DAO;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.PersistenceException;

import org.hibernate.exception.ConstraintViolationException;

import br.com.edutex.logic.CST;
import br.com.edutex.logic.Empresa;
import br.com.edutex.logic.FinalidadeNfe;
import br.com.edutex.logic.ImpostoNcm;
import br.com.edutex.logic.NCM;
import br.com.edutex.logic.TipoImposto;
import br.com.edutex.logic.TipoStatus;
import br.com.edutex.util.LogUtil;

/**
 * @author bruno
 *
 */
public class NcmDao extends AbstractDao {

	
	private static NcmDao instance;
	
	public static NcmDao getInstance(){
		if (instance == null) {
			instance = new NcmDao();
		}
		
		return instance;
	}
	
	private NcmDao(){
		factory = Persistence.
		          createEntityManagerFactory("Edutex");
	}
	
	/**
	 * busca ncm
	 * @param ncm
	 * @return NCM
	 */
	@SuppressWarnings("unchecked")
	public List<NCM> buscarNcms(String ncm) {
		String query = "SELECT ncm from NCM ncm "
				+ "WHERE ncm.nmncm = :ncm "
				+ "and ncm.tpStatus.cdStatus = 1" ;
		
		
		return  (ArrayList<NCM>) getEntityManager().createQuery(query)
				.setParameter("ncm",ncm)
				.getResultList();
	}
	
	/**
	 * Método da camada DAO que insere ncm
	 * @param ncm
	 * @return se insert foi realizado
	 */
	public String insereNcm (NCM ncm, int cdFinalidadeNfe) throws NullPointerException {
		EntityManager manager = getEntityManager();
		EntityTransaction transaction = manager.getTransaction();
		try{
			transaction.begin();
			
			ncm.setFinalidadeNfe(manager.find(FinalidadeNfe.class, cdFinalidadeNfe));
			ncm.setDtCriacao(Calendar.getInstance());
			
			Empresa emp = manager.merge(ncm.getEmpresa());
			
			TipoStatus tpStatus = manager.find(TipoStatus.class, 1);
			
			CST cst = ncm.getCsts().get(0);
			cst.setEmpresa(emp);
			cst.setTpStatus(tpStatus);
			cst.setNcm(ncm);
			
			for (ImpostoNcm imposto: ncm.getImpNcm()) {
				imposto.setFinalidadeNfe(manager.find(FinalidadeNfe.class, cdFinalidadeNfe));
				imposto.setTipoImposto(manager.find(TipoImposto.class, imposto.getTipoImposto().getCdTipoImposto()));
				imposto.setTpStatus(tpStatus);
				imposto.setEmpresa(emp);
				imposto.setNcm(ncm);
				
			}
			
			ncm.setTpStatus(tpStatus);	
			ncm.setEmpresa(emp);
			
			
			// por padrão o status é ativo na inserção
			manager.persist(ncm);
			manager.flush();
			
			transaction.commit();
			return "NCM inserido com sucesso";
		}catch (PersistenceException e) {
			transaction.rollback();
			 e.printStackTrace();
			 LogUtil.getLog().error(e.getMessage(), e);
			 
			 if (e.getCause().getClass().equals(ConstraintViolationException.class)) {
				  return "NCM já existente";
			 
			 } 
			 
			 return "Erro de persistência";
		} finally{
			factory.close();
			transaction = null;
		}
	}
	
	
	/**
	 * Método da camada DAO que atualiza NCM
	 * @param ncm
	 * @param codigoFinalidade
	 */
	public void atualizar(NCM ncm, int codigoFinalidade) throws Exception {
		EntityManager manager = getEntityManager();
		EntityTransaction transaction = manager.getTransaction();
		try{
			transaction.begin();
			
			ncm.setFinalidadeNfe(manager.find(FinalidadeNfe.class, codigoFinalidade));
			// por padrão o status é ativo na atualização
			 //ncm  = manager.merge(ncm);
			
			 
			//manager.refresh(ncm);
			
			manager.merge(ncm);
			transaction.commit();
		}catch (PersistenceException e) {
			transaction.rollback();
			 e.printStackTrace();
			 LogUtil.getLog().error(e.getMessage(), e);
			 
		} finally{
			factory.close();
			transaction = null;
		}
	}
	

	public List<NCM> buscarNcms(String ncm, int finalidade) {
		String query = "SELECT ncm from NCM ncm "
				+ "WHERE ncm.nmncm = :ncm and " 
				+  "ncm.finalidadeNfe.cdFinalidadeNfe = :finalidade "
				+ "and ncm.tpStatus.cdStatus = 1" ;
		
		
		return  (ArrayList<NCM>) getEntityManager().createQuery(query)
				.setParameter("ncm",ncm)
				.setParameter("finalidade", finalidade)
				.getResultList();
	}
	
}
