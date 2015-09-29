/**
 * 
 */
package br.com.edutex.DAO;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collections;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.PersistenceException;

import org.hibernate.exception.ConstraintViolationException;

import com.sun.xml.internal.bind.v2.runtime.unmarshaller.XsiNilLoader.Array;

import br.com.edutex.logic.CST;
import br.com.edutex.logic.Empresa;
import br.com.edutex.logic.Estado;
import br.com.edutex.logic.FinalidadeNfe;
import br.com.edutex.logic.ImpostoNcm;
import br.com.edutex.logic.ImpostoNcmEstado;
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
			
			for (int j = 0; j < ncm.getImpNcm().size(); j++) {
				
				ncm.getImpNcm().get(j).setFinalidadeNfe(manager.find(FinalidadeNfe.class, cdFinalidadeNfe));
				ncm.getImpNcm().get(j).setTipoImposto(manager.find(TipoImposto.class, ncm.getImpNcm().get(j).getTipoImposto().getCdTipoImposto()));
				ncm.getImpNcm().get(j).setTpStatus(tpStatus);
				ncm.getImpNcm().get(j).setEmpresa(emp);
				ncm.getImpNcm().get(j).setNcm(ncm);
				
		
				
				if (j == 0) {
					for (int i = 0 ;i < ncm.getImpNcm().get(j).getImpostosNcmEstado().size(); i++) {
						ncm.getImpNcm().get(j).getImpostosNcmEstado().get(i).setEstado(manager.find(Estado.class,
								ncm.getImpNcm().get(j).getImpostosNcmEstado().get(i).getEstado().getCdEstado()));
					}
				}
				
				
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
			
			for (ImpostoNcmEstado impostoEstado: ncm.getImpNcm().get(0).getImpostosNcmEstado()) {
				 manager.merge(impostoEstado);
			
			}
			
			
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
