package br.com.edutex.DAO;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;












import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.PersistenceException;

import org.hibernate.exception.ConstraintViolationException;

import br.com.edutex.logic.Empresa;
import br.com.edutex.logic.RegimeTributarioEmpresa;
import br.com.edutex.logic.TipoStatus;
import br.com.edutex.logic.Usuario;
import br.com.edutex.util.LogUtil;
import br.com.edutex.util.MensagemProp;

/**
 * Classe que representa os metodos de persistencia
 * para os objetos de negocio Empresa
 * @author bruno
 *
 */
public class EmpresaDao  extends AbstractDao{
	
	private static EmpresaDao instance;
	
	private EmpresaDao(){
		factory = Persistence.
		          createEntityManagerFactory("Edutex");
	}
	
	public static EmpresaDao getInstance(){
		
		if(instance == null)
			instance = new EmpresaDao();
		
		
		return instance;
		
	}
	/**
	 * Metodo que busca lista de Empresas 
	 * usando como filtro o tipostatus
	 * @param codStatus
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<Empresa> buscaListaEmpresa() {
		// traz apenas as empresa ativas	
		 String query = "SELECT e from Empresa AS e WHERE"
		 		+ " e.tpStatus.nmStatus =  :status"
		 		+ " ORDER BY e.nmEmpresa";
		 
		 
		return (ArrayList<Empresa>)  getEntityManager().createQuery(query)
									.setParameter("status", "Ativo")
									.getResultList();
		
	}
	
	
	/**
	 * Metodo que insere Empresa no banco de dados
	 * @param emp Empresa
	 */
	public Object inseriEmpresa(Empresa emp, int codRes){

		 EntityManager manager =  getEntityManager(); 
		 EntityTransaction transaction = manager.getTransaction();
		 try{
			 transaction.begin();
			 emp.setRegimeTibutario(manager.find(RegimeTributarioEmpresa.class, codRes));
			 TipoStatus tpStatus = manager.merge(TipoStatusDao.getInstance().buscarTipoStatusAtivo());
			 emp.setTpStatus(tpStatus);
			 manager.persist(emp);
			 transaction.commit();
		 } catch (PersistenceException e){
			 if (transaction.isActive()) {
				 transaction.rollback();
			 }
			 
			 e.printStackTrace();
			 LogUtil.getLog().error(e.getMessage(), e);
			 
			 if (e.getCause().getCause().getClass().equals(ConstraintViolationException.class)) {
				  return MensagemProp.getPropriedades().getProperty("empresa.cadastro.existente");
			 } else {
				 return MensagemProp.getPropriedades().getProperty("empresa.inserido.erro");
			 }
			 
			 
		 } catch(Exception e) {
			 manager.getTransaction().rollback();
			 e.printStackTrace();
			 LogUtil.getLog().error(e.getMessage(), e);
			 return MensagemProp.getPropriedades().getProperty("empresa.inserido.erro");
		 } finally {
			 //factory.close();
			 transaction = null;
		 }
		 
		 return null;
	}
	
	
	
	/**
	 * Lista as empresas recebendo n parâmetros 
	 * @param empresasID
	 * @return
	 */
	public List<Empresa> listaEmpresaPorIds (Integer[] empresasID) {
		String query = "SELECT emp from Empresa emp where emp.cdcnpj in (:ids)";
		List<Integer> list = Arrays.asList(empresasID);
		return (ArrayList<Empresa>) getEntityManager().createQuery(query)
				.setParameter("ids",list)
				.getResultList();
	}
	
	
	/**
	 * Metodo que lista empresa por CNPJ
	 * @param cnpj
	 * @return
	 */
	public List<Empresa> listarEmpresaPorCnpj(String cnpj){
		String query = "SELECT emp from Empresa emp where emp.cnpj = :cnpj";
		return (ArrayList<Empresa>) getEntityManager().createQuery(query)
				.setParameter("cnpj",cnpj)
				.getResultList();
	}
	  
	
	/**
	 * Atualiza empresa deixando ela ativa como padrao
	 * @param emp
	 * @param codReg
	 * @return
	 */
	public boolean atualizarEmpresa(Empresa emp, int codReg, int codTpStatus){
		 boolean result = true;
		 
		EntityManager manager = getEntityManager();
		 EntityTransaction transaction = manager.getTransaction();
		 try{
			 transaction.begin();
			 Empresa emp1 = manager.merge(emp);
			 if(codReg != 0)
				 emp1.setRegimeTibutario(manager.find(RegimeTributarioEmpresa.class, codReg));
			 
			 if(codTpStatus != 0)
				 emp1.setTpStatus(manager.find(TipoStatus.class, codTpStatus));
				 
			 manager.persist(emp1);
			 transaction.commit();
		 }catch(Exception e){
			 manager.getTransaction().rollback();
			 result = false;
			 e.printStackTrace();
			 LogUtil.getLog().error(e.getMessage(), e);
		 } finally {
			 //factory.close();
			 transaction = null;
		 }
		 return result;
	}
	
 public Empresa getEmpresa(int codEmp){
	EntityManager manager = getEntityManager();
	Empresa emp =manager.find(Empresa.class, codEmp);
	manager.detach(emp);
	 return  emp;
 }
}
