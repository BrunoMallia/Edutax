package br.com.edutex.DAO;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceException;

import org.hibernate.exception.ConstraintViolationException;

import br.com.edutex.logic.TipoErro;
import br.com.edutex.logic.Validacao;
import br.com.edutex.logic.ValidacaoErro;
import br.com.edutex.util.LogUtil;
import br.com.edutex.util.MensagemProp;

public class ValidacaoDao extends AbstractDao {
	
	private static ValidacaoDao instance;
	
	public static  ValidacaoDao getInstance() {
		if (instance == null)
			 instance = new ValidacaoDao();
		
		return instance;
	}
	
	public void salvarValidacao(Validacao validacao) {
		 EntityManager manager = getEntityManager(); 
		 EntityTransaction transaction = manager.getTransaction();
		 
		 try{
			 transaction.begin();
			 manager.flush();
			//Validacao validacaoAttach = manager.merge(validacao);
			 validacao.setEmpresa(manager.merge(validacao.getEmpresa()));
			 validacao.setFinalidadeNfe(manager.merge(validacao.getFinalidadeNfe()));
			 validacao.setNfeInicial(manager.merge(validacao.getNfeInicial()));
			 
			 //validacao.setNfeGerada(manager.merge(validacao.getNfeGerada()));
			//validacao.setValidacaoErro(manager.);
			 
			if (validacao.getValidacaoErro() != null) {
				for (ValidacaoErro validacaoErro: validacao.getValidacaoErro()) {
				 	
					 validacaoErro.setTpErro(manager.find(TipoErro.class, validacaoErro.getTpErro().getCdTipoErro()));
					 validacaoErro.setValidacao(validacao);
				 }
				
			}
			 
			  
			 manager.persist(validacao);
			 manager.flush();
			 transaction.commit();
		 }  catch(Exception e){
			 manager.getTransaction().rollback();
			 e.printStackTrace();
			 LogUtil.getLog().error(e.getMessage(), e);
			
		 } finally {
			 manager.close();
			 transaction = null;
		 }
		 
		
	}

}
