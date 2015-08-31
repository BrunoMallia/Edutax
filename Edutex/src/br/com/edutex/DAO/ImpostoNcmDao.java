package br.com.edutex.DAO;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.PersistenceException;

import br.com.edutex.logic.FinalidadeNfe;
import br.com.edutex.logic.ImpostoNcm;
import br.com.edutex.logic.NCM;
import br.com.edutex.logic.TipoImposto;
import br.com.edutex.util.LogUtil;

/**
 * Classe que representa os metodos de persistencia
 * para os objetos de negocio ImpostoNcm
 * @author bruno
 *
 */
public class ImpostoNcmDao  extends AbstractDao{
	
	private static ImpostoNcmDao instance;
	
	private ImpostoNcmDao(){
		factory = Persistence.
		          createEntityManagerFactory("Edutex");
	}
	
	public static ImpostoNcmDao getInstance(){
		
		if(instance == null)
			instance = new ImpostoNcmDao();
		
		
		return instance;
		
	}

	
	
	/**
	 * Encontra o imposto 
	 * @param cdNcm
	 * @return
	 */
	public ImpostoNcm findImpostoNcmICMS(int cdNcm) {
		// traz apenas as empresa ativas	
		 String query = "SELECT imp from ImpostoNcm AS imp WHERE imp.ncm.cdNcm = :cdNcm and imp.tipoImposto.cdTipoImposto = 1";
		 
		 return  (ImpostoNcm) getEntityManager().createQuery(query)
					.setParameter("cdNcm", cdNcm).getSingleResult();
			 
	}
	
	/**
	 * @param ncm
	 * @param cdFinalidadeNota
	 * @param tipoImposto
	 * @param cdEstado
	 * @return
	 */
	public List<ImpostoNcm> buscarImpostoNcm(String ncm, int cdFinalidadeNota, int cdEmpresa) {
		
		String query = "SELECT imp from ImpostoNcm AS imp WHERE imp.ncm.nmncm = :ncm and"
				+ " imp.finalidadeNfe.cdFinalidadeNfe = :cdFinalidade  ORDER BY imp.tipoImposto.cdTipoImposto";
		 
		 return  (List<ImpostoNcm>) getEntityManager().createQuery(query)
					.setParameter("ncm", ncm)
					.setParameter("cdFinalidade", cdFinalidadeNota)
					.getResultList();
		
	}

	
	public void atualizar(ImpostoNcm impostoNcm, int codigoFinalidade) throws Exception {
		EntityManager manager = getEntityManager();
		EntityTransaction transaction = manager.getTransaction();
		try{
			transaction.begin();
			manager.clear();
			impostoNcm.setFinalidadeNfe(manager.find(FinalidadeNfe.class, codigoFinalidade));
			impostoNcm.setTipoImposto(manager.find(TipoImposto.class, impostoNcm.getTipoImposto().getCdTipoImposto()));
			//manager.refresh(ncm);
			
			ImpostoNcm impResp = manager.merge(impostoNcm);
			
			manager.refresh(impResp);
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
	

 
}
